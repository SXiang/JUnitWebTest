# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\Build-PicarroAppAPK.ps1 `
#           -BuildWorkingDir "C:\Repositories\PicarroApp"  `
#           -BuildScriptsBaseDir "C:\Repositories\surveyor-qa" `
#           -BuildFlavor "debug" `
#           -CIBuildNumber "1"
# ---------------------------------------------------------------

param
(
  [Parameter(Mandatory=$true)]
  [string] $BuildWorkingDir,                # eg. "C:\Repositories\PicarroApp",

  [Parameter(Mandatory=$true)]
  [string] $BuildScriptsBaseDir,            # eg. "C:\Repositories\surveyor-qa",

  [Parameter(Mandatory=$true)]
  [string] $BuildFlavor,                    # either 'release' or 'debug'

  [Parameter(Mandatory=$true)]
  [string] $CIBuildNumber                   # CI build number
)

. "$BuildScriptsBaseDir\selenium-wd\lib\ApplicationInstaller.ps1"

$BUILD_NUMBER_KEY = "ext.ciBuildNumber"
$BUILD_NUMBER_PLACEHOLDER = "%BUILD_NUMBER%"

cd "$BuildWorkingDir"

# 1. 
# Install NPM packages
Write-Host "[INSTALL_REACT_NATIVE]: Check/Install pre-requisite applications"
$installApplications = @{
    "01.react-native-cli"="react-native-cli"
}
InstallApplications-FromDictTable -installAppsDictTable $installApplications
Write-Host "[INSTALL_REACT_NATIVE]: Done installing pre-requisite applications"

"Triggering NPM install (using latest) ..."
npm install npm@latest -g

# 2. 
# Set CI build number in build.gradle 
$gradeBuildFile = "$BuildWorkingDir\android\app\build.gradle"
(Get-Content -Path $gradeBuildFile).Replace("$BUILD_NUMBER_KEY = ""$BUILD_NUMBER_PLACEHOLDER""", "$BUILD_NUMBER_KEY = ""$CIBuildNumber""") | Set-Content $gradeBuildFile -Encoding Ascii

# 3. 
# Trigger build
cd "$BuildWorkingDir\android"
if ($BuildFlavor.ToLower() -eq "release") {
  "Starting Release build ..."
  .\gradlew assembleRelease
} else {
  "Starting Debug build ..."
  .\gradlew assembleDebug
}