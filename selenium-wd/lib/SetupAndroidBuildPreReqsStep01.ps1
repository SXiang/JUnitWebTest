﻿# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\SetupAndroidBuildPreReqsStep01.ps1 `
#           -BuildWorkingDir "C:\Repositories\surveyor-qa"
# ---------------------------------------------------------------

param
(
  [Parameter(Mandatory=$true)]
  [string] $BuildWorkingDir              # Eg. "C:\Repositories\surveyor-qa"
)

. "$BuildWorkingDir\selenium-wd\lib\SetupAndroidBuildPreReqsCommon.ps1"
. "$BuildWorkingDir\selenium-wd\lib\ApplicationInstaller.ps1"

# 0.
$overrideSet = Pre-InstallationCheck
if ($overrideSet) {
    Write-Host "Override found in $PRE_INSTALL_CHECK_FILE to skip installation of Android pre-requisites"
    Write-Host "Android pre-requisites will NOT be installed."
    Write-Host "NOTE: If you wish to force installation of Android pre-requisites on the machine remove '$OVERRIDE_TEXT' text from $PRE_INSTALL_CHECK_FILE"
    exit
}

# 1.
Write-Host "[INSTALL_PRE-REQS]: Check/Install pre-requisite applications"
$installApplications = @{
    "01.choco"="Chocolatey"
    "02.7zip"="7-Zip"
    "03.nodejs"="NodeJS"
    "04.gradle"="Gradle"
    "05.android-sdk"="Android SDK"
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