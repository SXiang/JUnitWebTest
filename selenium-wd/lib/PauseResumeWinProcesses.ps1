param (
   [string]$WorkingFolder = "C:\Repositories\surveyor-qa",
   [string]$WindowTitles = "OdorCallServer|SimLinearFitter|SimDataBroadcaster",
   [string]$IsResume = "true"
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