# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\Build-PicarroAppAPK.ps1 `
#           -BuildWorkingDir "C:\Repositories\PicarroApp"  `
#           -ArtifactoryBaseUrl "http://picarro.artifactoryonline.com/picarro" `
#           -ArtifactoryAPIKey "AKCp2VoGPeRruh1fKTMx8K99yceZRay15wBQHHuoFoDuAgib16cSrM8VuKaTtjznPeEC9QXGL" `   # NOTE: Not valid key. Replace with a valid API key.
#           -ArtifactoryRepository "picarro-generic-private" `
#           -ArtifactoryFolder "AndroidApp" `
#           -BuildScriptsBaseDir "C:\Repositories\surveyor-qa" `
#           -BuildFlavor "debug" `
#           -KeyStoreFileName "my-release-key.keystore" `
#           -CIBuildNumber "1"
# ---------------------------------------------------------------

param
(
  [Parameter(Mandatory=$true)]
  [string] $BuildWorkingDir,                # eg. "C:\Repositories\PicarroApp",

  [Parameter(Mandatory=$true)]
  [String] $ArtifactoryBaseUrl,             # Artifactory Base Url. For eg. http://picarro.artifactoryonline.com/picarro

  [Parameter(Mandatory=$true)]
  [String] $ArtifactoryAPIKey,              # Artifactory API Key. 

  [Parameter(Mandatory=$true)]
  [String] $ArtifactoryRepository,

  [Parameter(Mandatory=$true)]
  [String] $ArtifactoryFolder,

  [Parameter(Mandatory=$true)]
  [string] $BuildScriptsBaseDir,            # eg. "C:\Repositories\surveyor-qa",

  [Parameter(Mandatory=$true)]
  [string] $BuildFlavor,                    # 'release', 'debug' or 'both'. Use 'both' to build both 'release' and 'debug'

  [Parameter(Mandatory=$true)]
  [string] $KeyStoreFileName,               # keystore filename used for signing. eg. my-release-key.keystore

  [Parameter(Mandatory=$true)]
  [string] $CIBuildNumber                   # CI build number
)

. "$BuildScriptsBaseDir\selenium-wd\lib\ApplicationInstaller.ps1"

function Ensure-KeyStoreFilePresent() {
    $keystoreFilePath = "$BuildWorkingDir\android\app\$KeyStoreFileName"
    if (Test-path $keystoreFilePath) {
        Write-Host "Keystore file already found in location: ${keystoreFilePath}. SKIPPING download from Artifactory ..."
    } else {
        $Headers = @{
            "X-JFrog-Art-Api" = $ArtifactoryAPIKey
        }

        $downloadURL = "$ArtifactoryBaseUrl/$ArtifactoryRepository/$ArtifactoryFolder/$KeyStoreFileName"
        "Start downloading keystore file from->[$downloadURL], to->[$apkDownloadPath]"
        Invoke-WebRequest -Uri $downloadURL -Headers $Headers -OutFile $keystoreFilePath
        "DONE downloading keystore file."
    }
}

$BUILD_NUMBER_KEY = "ext.ciBuildNumber"
$BUILD_NUMBER_PLACEHOLDER = "%BUILD_NUMBER%"

cd "$BuildWorkingDir"

"Triggering NPM install (updating to latest npm) ..."
npm install npm@$CHOCO_NPM_VERSION -g
npm install

# 1. 
# Install NPM packages
Write-Host "[INSTALL_REACT_NATIVE]: Check/Install pre-requisite applications"
$installApplications = @{
    "01.react-native-cli"="react-native-cli"
}
InstallApplications-FromDictTable -installAppsDictTable $installApplications
Write-Host "[INSTALL_REACT_NATIVE]: Done installing pre-requisite applications"

# 2. 
# Set CI build number in build.gradle 
$gradeBuildFile = "$BuildWorkingDir\android\app\build.gradle"

(Get-Content -Path $gradeBuildFile).Replace("$BUILD_NUMBER_KEY = ""$BUILD_NUMBER_PLACEHOLDER""", "$BUILD_NUMBER_KEY = ""$CIBuildNumber""") | Set-Content $gradeBuildFile -Encoding Ascii

# 3. 
# Trigger build
cd "$BuildWorkingDir\android"
if ($BuildFlavor.ToLower() -eq "release") {
  "Starting Release build ..."
  Ensure-KeyStoreFilePresent
  .\gradlew assembleRelease
} elseif ($BuildFlavor.ToLower() -eq "debug") {
  "Starting Debug build ..."
  .\gradlew assembleDebug
} elseif ($BuildFlavor.ToLower() -eq "both") {
  Ensure-KeyStoreFilePresent
  "Starting Release build ..."
  .\gradlew assembleRelease
  "Starting Debug build ..."
  .\gradlew assembleDebug
} else {
  "ERROR: build flavor-'$BuildFlavor' specified is NOT supported."
}