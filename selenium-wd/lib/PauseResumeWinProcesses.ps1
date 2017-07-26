param (
   [Parameter(Mandatory=$false)]
   [string]$WorkingFolder="C:\repositories\surveyor-qa",      # eg. C:\repositories\surveyor-qa
 
   [Parameter(Mandatory=$false)]
   [string]$WindowTitles="OdorCallServer|SimLinearFitter|SimDataBroadcaster",       # '|' seperated list of process window titles. eg. 'OdorCallServer|SimLinearFitter|SimDataBroadcaster'
   
   [Parameter(Mandatory=$false)]
   [string]$IsResume="true"            # true or false
)

$exeFolder = "$WorkingFolder\selenium-wd\lib\Exe"
[string]$WindowTitles = [string]$WindowTitles
$titleArray = $WindowTitles.Split("|")
$processes = get-process | ? {$titleArray.Contains($_.MainWindowTitle)}
$processes | %{
    $procId = $_.Id
    $windowTitle = $_.MainWindowTitle
    cd "$exeFolder"
    if ($IsResume.toLower() -eq "true") {    
        Write-Host "Resuming process id = $procId, windowTitle = $windowTitle ..."
        $null = . $exeFolder\pssuspend.exe -r $procId 2>&1
    } else {
        Write-Host "Pausing process id = $procId, windowTitle = $windowTitle ..."
        $null = . $exeFolder\pssuspend.exe $procId 2>&1
    }
}