# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\PauseResumeWinProcsOnRemoteMachine.ps1 `
#           -DestMachineIPAddress "10.100.3.68"  `
#           -DestMachineUsername "localhost\picarro" `
#           -DestMachinePassword "<password>" `
#           -IsResume "false"
# ---------------------------------------------------------------

param
(
  [Parameter(Mandatory=$true)]
  [string] $DestMachineIPAddress,           # destination machine IP eg. "10.100.3.68",

  [Parameter(Mandatory=$true)]
  [string] $DestMachineUsername,            # destination machine windows user eg. "localhost\picarro",

  [Parameter(Mandatory=$true)]
  [string] $DestMachinePassword,            # destination machine password

  [Parameter(Mandatory=$true)]
  [string] $IsResume                        # either 'true' or 'false'
)

$resume = ($IsResume.ToLower() -eq "true")

$securePwd = ConvertTo-SecureString “$DestMachinePassword” -AsPlainText -Force
$credential = New-Object System.Management.Automation.PSCredential($DestMachineUsername, $securePwd)
$sOptions = New-PSSessionOption –SkipCACheck –SkipCNCheck –SkipRevocationCheck
Write-Host "Creating remote session to -> $DestMachineIPAddress"
$session = New-PSSession -ConfigurationName microsoft.powershell -ComputerName "$DestMachineIPAddress" -Credential $credential -SessionOption $sOptions -UseSSL
Write-Host "Successfully created remote session to -> $DestMachineIPAddress"

try {
    # Remote Script -> [Pause/Resume processes script]
    $ScriptBlockPauseResumeProcs = { 
        $resume = $args[0]
        $exeFolder = "C:\AutomationTools"
        $processes = get-process -name odorcallserver
        $processes | %{
            $procId = $_.Id
            $procName = $_.Name
            cd "$exeFolder"
            if ($resume -eq $true) {    
                Write-Host "Resuming process id = $procId, name = $procName ..."
                $null = . $exeFolder\pssuspend.exe -r $procId 2>&1
            } else {
                Write-Host "Pausing process id = $procId, name = $procName ..."
                $null = . $exeFolder\pssuspend.exe $procId 2>&1
            }
        }
    }

    Write-Host "[Remote Execution] Executing pause/resume script on remote machine - $DestMachineIPAddress"
    Invoke-Command -Session $session -ScriptBlock $ScriptBlockPauseResumeProcs -ArgumentList $resume 

} catch {
    $ex = $_.Exception
    Write-Error "Error occured in remote session. Exception: $ex"
}

# Disconnect PS sessions.
Get-PSSession | Disconnect-PSSession