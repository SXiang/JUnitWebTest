param
(
  [string] $buildWorkingDir = "C:\Repositories\surveyor-qa"
)

. "$buildWorkingDir\selenium-wd\lib\ApplicationInstaller.ps1"

$EMPTY = "EMPTY"

# 1.
Write-Host "[INSTALL_PRE-REQS]: Check/Install pre-requisite applications"
$installApplications = @{
    "01.choco"="Chocolatey"
    "02.7zip"="7-Zip"
    "03.nodejs"="NodeJS"
    "04.react-native-cli"="react-native-cli"
    "05.gradle"="Gradle"
    "06.android-sdk"="Android SDK"
}
InstallApplications-FromDictTable -installAppsDictTable $installApplications
Write-Host "[INSTALL_PRE-REQS]: Done installing pre-requisite applications"

# 2.
Write-Host "[SETUP_ENVIRONMENT_VARIABLES]: Ensuring correct environment variables"
[string]$envPath = [string]$env:Path
[string]$envAndroidHome = [string]$env:ANDROID_HOME
if (-not $envPath.Contains($envAndroidHome)) {
    Write-Host "Setting ANDROID_HOME to PATH"
    [Environment]::SetEnvironmentVariable("Path", "$envPath;$envAndroidHome", "Machine")
}
Write-Host "[SETUP_ENVIRONMENT_VARIABLES]: Done setting up environment variables"
