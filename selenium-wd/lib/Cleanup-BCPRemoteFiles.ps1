# ---------------------------------------------------------------
# PRE-REQUISITES:
#  This script cleans up .dat files copied to remote DB machine as part of BCP execution.
# SAMPLE USAGE:
#   .\Cleanup-BCPRemoteFiles.ps1 `
#           -BuildWorkingDir "C:\Repositories\surveyor-qa"  `
#           -DestMachineIPAddress "10.100.1.200"  `
#           -DestMachineUsername "Win2k12-Android\picarro" `
#           -DestMachinePassword "<password>" `
#           -FilesToDelete "C:\Repositories\surveyor-qa\selenium-wd\data\sql\Asset.BA.dat"
# ---------------------------------------------------------------

param
(
  [Parameter(Mandatory=$true)]
  [String] $BuildWorkingDir,

  [Parameter(Mandatory=$true)]
  [String] $DestMachineIPAddress,

  [Parameter(Mandatory=$true)]
  [String] $DestMachineUsername,

  [Parameter(Mandatory=$true)]
  [String] $DestMachinePassword,

  [Parameter(Mandatory=$true)]
  [String] $FilesToDelete
)

$files = $FilesToDelete.Split(",")

# Validation to protect against incorrect files getting deleted.
$files | %{
    [string]$fileDeleteLocation = $_
    $fileDeleteLocation = $fileDeleteLocation.Trim().ToLowerInvariant()
    Write-Host "[Delete File] -> $fileDeleteLocation"
    if ((-not $fileDeleteLocation.StartsWith("c:\repositories")) -and (-not $fileDeleteLocation.StartsWith("c:\buildagent"))) {
        Write-Host "[Error] : Unsupported filepath detected. fileDeleteLocation='$fileDeleteLocation'. Terminating script ..."
        exit
    }
}

$securePwd = ConvertTo-SecureString “$DestMachinePassword” -AsPlainText -Force
$credential = New-Object System.Management.Automation.PSCredential($DestMachineUsername, $securePwd)
$sOptions = New-PSSessionOption –SkipCACheck –SkipCNCheck –SkipRevocationCheck
Write-Host "Creating remote session to -> $DestMachineIPAddress"
$session = New-PSSession -ConfigurationName microsoft.powershell -ComputerName "$DestMachineIPAddress" -Credential $credential -SessionOption $sOptions -UseSSL
Write-Host "Successfully created remote session to -> $DestMachineIPAddress"


try {
    # Remote PSSession Script -> [Delete File]
    $ScriptBlockDeleteFile = { 
        $FileLocation = $args[0]

        Write-Host "[Remote DELETE] Check/Delete file-'$FileLocation'. Executing on $DestMachineIPAddress | File -> $FileLocation"
        if (Test-Path "$FileLocation" -PathType Leaf) {
            Write-Host "[Remote] Removing file -> $FileLocation ..."
            Remove-Item -Path $FileLocation -Force
        }         
    }

    $files | %{
        [string]$fileLocation = $_
        $fileLocation = $fileLocation.Trim()

        # Delete file on remote machine.
        Write-Host "Executing -> Delete File on remote machine - $DestMachineIPAddress"
        Invoke-Command -Session $session -ScriptBlock $ScriptBlockDeleteFile -ArgumentList $fileLocation
    }

} catch {
    $ex = $_.Exception
    Write-Error "Error occured in remote session. Exception: $ex"
}

# Disconnect PS sessions.
Get-PSSession | Disconnect-PSSession
