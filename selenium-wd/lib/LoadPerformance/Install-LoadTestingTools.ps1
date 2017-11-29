# ---------------------------------------------------------------
# DESCRIPTION: Script detects and installs load testing tools (Apache Benchmark)
#
# NOTES: Script assumes 7-zip is already installed on build agent machine.
#
# SAMPLE USAGE:
#   .\Install-LoadTestingTools.ps1 -BuildWorkingDir "C:\Repositories\surveyor-qa" `
#       -ArtifactoryBaseUrl "http://picarro.artifactoryonline.com/picarro" `
#       -ArtifactoryAPIKey "AKCp2VoGPeRruh1fKTMx8K99yceZRay15wBQHHuoFoDuAgib16cSrM8VuKaTtjznPeEC9QXGL" `   # NOTE: Not valid key. Replace with a valid API key.
#       -ArtifactoryRepository "picarro-generic-private-qa" `
#       -ArtifactoryFileRelativePath "test-artifacts/apache-benchmark/httpd-2.4.29-Win64-VC15.zip" `
#       -InstallFolder "c:\Tools\Apache Benchmark"
#

param
(
  [Parameter(Mandatory=$true)]
  [String] $BuildWorkingDir,                       # Path to working directory (for eg. C:\Repositories\surveyor)

  [Parameter(Mandatory=$true)]
  [String] $ArtifactoryBaseUrl,                    # Artifactory Base Url. For eg. http://picarro.artifactoryonline.com/picarro

  [Parameter(Mandatory=$true)]
  [String] $ArtifactoryAPIKey,                     # Artifactory API Key. 

  [Parameter(Mandatory=$true)]
  [String] $ArtifactoryRepository,                 # Artifactory respository. eg. picarro-generic-private-qa 

  [Parameter(Mandatory=$true)]
  [String] $ArtifactoryFileRelativePath,

  [Parameter(Mandatory=$true)]
  [String] $InstallFolder
)

$7ZIP_FOLDER = "C:\Program Files\7-Zip"
$AB_ZIP_FILE_NAME = "ApacheBenchmark.zip"

# 1. Detect if ab.exe is already installed.
$abExePath = "$InstallFolder\Apache24\bin\ab.exe"
if (Test-Path $abExePath) {
    Write-Host "WARNING: Installation skipped. ab.exe was already found in location -> $InstallFolder\Apache24\bin"
    exit
}

if (-not (Test-Path $InstallFolder)) {
    New-Item -Path $InstallFolder -ItemType Directory
}

# 2. Download installer zip from Artifactory.
$Headers = @{
    "X-JFrog-Art-Api" = $ArtifactoryAPIKey
}

$downloadFilePath = "$InstallFolder\$AB_ZIP_FILE_NAME"

if (Test-path $downloadFilePath) {
    Write-Host "Installer ZIP already found in location: ${apkDownloadPath}. Removing existing file ..."
    Remove-Item $downloadFilePath
} 

$downloadURL = "$ArtifactoryBaseUrl/$ArtifactoryRepository/$ArtifactoryFileRelativePath"
"Start downloading Apache Benchmark Installer from->[$downloadURL], to->[$downloadFilePath]"
Invoke-WebRequest -Headers $Headers -Uri $downloadURL -OutFile $downloadFilePath
"DONE downloading Apache Benchmark installer."

# 3. Unzip to install folder
$apacheBenchmarkFilePath = "$InstallFolder\$AB_ZIP_FILE_NAME"
"Start unzipping runner.7z from - $apacheBenchmarkFilePath .. Existing files will be overwritten."
Start-Process -FilePath "$7ZIP_FOLDER\7z" -ArgumentList "x -o""$InstallFolder"" ""$apacheBenchmarkFilePath"" -aoa " -Wait
"Unzipped files at - '$InstallFolder'"

# 4. Verify ab.exe is found post installation.
if (Test-Path $abExePath) {
    Write-Host "[SUCCESS]: Installation was successful."
} else {
    Write-Host "[ERROR]: Installation failed. '$abExePath' was NOT found after installation."
}
