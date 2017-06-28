param (
   [Parameter(Mandatory=$true)]
   [string]$WorkingFolder,      # eg. C:\repositories\surveyor-qa
 
   [Parameter(Mandatory=$true)]
   [string]$WindowTitles,       # '|' seperated list of process window titles. eg. 'OdorCallServer|SimLinearFitter|SimDataBroadcaster'
   
   [Parameter(Mandatory=$true)]
   [string]$IsResume            # true or false
)

$exeFolder = "$WorkingFolder\selenium-wd\lib\Exe"
[string]$WindowTitles = [string]$WindowTitles
$titleArray = $WindowTitles.Split("|")
$processes = get-process | ? {$titleArray.Contains($_.MainWindowTitle)}
$processes | %{
    $procId = $_.Id
    cd "$exeFolder"
    if ($IsResume.toLower() -eq "true") {    
        Write-Host "Resuming process id = $procId ..."
        $null = . $exeFolder\pssuspend.exe -r $procId 2>&1
    } else {
        Write-Host "Pausing process id = $procId ..."
        $null = . $exeFolder\pssuspend.exe $procId 2>&1
    }
}