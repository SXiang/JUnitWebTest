param
(
  [Parameter(Mandatory=$true)]
  [string] $WorkingDirectory,                      # Eg. "C:\Repositories\Surveyor-QA"

  [Parameter(Mandatory=$true)]
  [string] $StoreLocation,                         # Eg. "C:\Automation\AndroidAppPerfData"

  [Parameter(Mandatory=$false)]
  [string] $StoreType="FileSystem",                # Eg. 'FileSystem', 'Database'. Currently handled FileSystem store type.  

  [Parameter(Mandatory=$true)]
  [double] $StartEpoch
)

. "$WorkingDirectory\selenium-wd\lib\HelperScripts\DateTimeHelpers.ps1"
. "$WorkingDirectory\selenium-wd\lib\HelperScripts\ZipHelpers.ps1"

function New-GuidNoDashes() {
    [guid]::NewGuid().ToString().Replace("-", "").ToUpper()
}

function Create-Directory($DirPath) {
    Write-Host "Creating new directory -> $DirPath ..."
    New-Item -Path $DirPath -ItemType Directory -Force
    Write-Host "Created new directory -> $DirPath"
}

function RemoveFile-IfExists($filePath) {
    if (Test-Path $filePath) {
        Remove-Item "$filePath" -Force
    }
}

$AUTOMATION_LOGS_FOLDER = "C:\QATestLogs"

$tempFolder = [System.IO.Path]::GetTempPath()
$guid = New-GuidNoDashes
$srcFolder = "${tempFolder}$guid"

# Ensure store location exists
if (-not (Test-Path $StoreLocation)) {
    Create-Directory -DirPath $StoreLocation
}

if (-not (Test-Path "$StoreLocation\$StartEpoch")) {
    Create-Directory -DirPath "$StoreLocation\$StartEpoch"
}

if (-not (Test-Path "$srcFolder")) {
    Create-Directory -DirPath "$srcFolder"
}

# Determine result files to be published
$startTime = FromUnixTime -epochTime $StartEpoch
$startTime = $startTime.ToLocalTime()
Get-ChildItem $AUTOMATION_LOGS_FOLDER | ? {$_.LastWriteTime -gt $startTime } | %{
    $file = $_
    $fileName = $file.Name
    if (($filename.EndsWith(".dat") -and $filename.Contains(".perf.")) -or ($filename.EndsWith(".mp4")) -or ($filename.EndsWith(".md"))) {
        $filePath = $file.FullName    
        Write-host "[PREPARE] - Copying $filePath to $srcFolder\$fileName ..."
        Copy-item "$filePath" "$srcFolder\$fileName"
    }
}

# Compress result files 
$archiveFilename = "AndroidAppPerfTestResults-$StartEpoch.zip"
$archiveFilePath = "$tempFolder\AndroidAppPerfResults-$StartEpoch.zip"
RemoveFile-IfExists -filePath $archiveFilePath

Write-host "[PREPARE] Compressing files in $srcFolder to $archiveFilePath"
Compress-Directory -sourceDirectoryName $srcFolder -destinationArchiveFileName $archiveFilePath

# Publish to store
$destinationFile = "$StoreLocation\$StartEpoch\$archiveFilename"
RemoveFile-IfExists -filePath $destinationFile
if ($StoreType -eq "FileSystem") {
    Write-host "[PUBLISH] - Publishing $archiveFilePath to $destinationFile ..."
    Move-Item -Path "$archiveFilePath" -Destination "$destinationFile" -Force
} else {
    Write-Error "[ERROR] : Specified store type-'$StoreType' NOT supported."
}

# Cleanup
Write-Host "[CLEANUP] - Removing files from $srcFolder ..."
Remove-Item $srcFolder\* -recurse
Write-Host "[CLEANUP] - Removing folder $srcFolder ..."
Remove-Item $srcFolder