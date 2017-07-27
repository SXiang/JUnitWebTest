<#
 SAMPLE USAGE:
  ./Setup-SimulatorExtras.ps1 -ScriptRootFolder "C:\Repositories\surveyor-qa" -BroadcastConstantValuesInSimLinearFitter "true"
#>

param(
   [Parameter(Mandatory=$true)]
   [string] $ScriptRootFolder,

   [Parameter(Mandatory=$true)]
   [string] $BroadcastConstantValuesInSimLinearFitter
)

# deduce if running in CI or dev environment. set hostRootFolder accordingly.
$isRunningInCI = $false
$hostFolderInCI = "$ScriptRootFolder\host"
if (Test-path $hostFolderInCI -PathType Container) {
    $isRunningInCI = $true
}

if ($isRunningInCI) {
    $HostRootFolder = $hostFolderInCI
    Write-Host "Detected run in CI. Host backpack server folder is - $HostRootFolder"
} else {
    $HostRootFolder = $ScriptRootFolder.Replace("surveyor-qa", "host")
    Write-Host "Detected run on development machine. Host root folder is - '$HostRootFolder'"
}

# ensure extra simulator files are present in host backpack server folder.
Write-Host "Ensuring BackPack Simulator extra files are present on the system."
$backpackServerFolder = "$HostRootFolder\src\main\python\Host\Utilities\BackpackServer"
$artifactsFolder = "$ScriptRootFolder\selenium-wd\lib\BackPackSim\TestArtifacts"
Get-ChildItem $artifactsFolder | %{
    $file = $_
    $filename = $file.Name
    $fileFullPath = $file.FullName
    $destFile = [System.IO.Path]::Combine($backpackServerFolder, $filename)
    if (-not (Test-Path $destFile)) {
        Write-Host "Copying $fileFullPath -> $backpackServerFolder"
        Copy-Item $fileFullPath $backpackServerFolder
    } else {
        Write-Host "File $destFile is already present"
    }
}

if ($BroadcastConstantValuesInSimLinearFitter.ToLower() -eq "true") {
    $linearFitterFile = "$HostRootFolder\src\main\python\Host\Utilities\BackpackServer\TestUtilities\simLinearFitterBroadcaster.py"
    $findString = "uniform(0.0, 10.0)"
    $replaceString = "uniform(2.0, 2.0)"
    $fileContent = Get-Content $linearFitterFile
    $findStringFound = $false
    $fileContent | % {
        [string]$line = [string]$_
        if ($line.Contains($findString)) {
            $findStringFound = $true
        }
    }
    
    if ($findStringFound) {
        Write-host "Updating -> $linearFitterFile"
        $fileContent.Replace($findString, $replaceString) |  Set-Content $linearFitterFile -Encoding Ascii
    } else {
        Write-host "String -> '$findString' NOT found in $linearFitterFile.. NOT updating"
    }
}