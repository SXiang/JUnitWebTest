# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\SetupAndroidBuildPreReqs.ps1 `
#           -BuildWorkingDir "C:\Repositories\PicarroApp"
# ---------------------------------------------------------------

param
(
  [Parameter(Mandatory=$true)]
  [string] $BuildWorkingDir              # Eg. "C:\Repositories\surveyor-qa"
)

$PRE_INSTALL_CHECK_FILE = "C:\install-overrides.txt"
$OVERRIDE_TEXT = "android-pre-reqs-present=1"

# This check is added to override installation of pre-requisites on the machine once we have determined no re-install is necessary on the box.
# To force installation remove $OVERRIDE_TEXT from $PRE_INSTALL_CHECK_FILE
function Pre-InstallationCheck() {
    if (Test-path -Path $PRE_INSTALL_CHECK_FILE) {
        $override = $false
        Get-Content $PRE_INSTALL_CHECK_FILE | %{
            $line = $_
            if ($line -eq $OVERRIDE_TEXT) {
                $override = $true
            }
        }
    }
    
    $override    
}

# 0.
$overrideSet = Pre-InstallationCheck
if ($overrideSet) {
    Write-Host "Override found in $PRE_INSTALL_CHECK_FILE to skip installation of Android pre-requisites"
    Write-Host "Android pre-requisites will NOT be installed."
    Write-Host "NOTE: If you wish to force installation of Android pre-requisites on the machine remove '$OVERRIDE_TEXT' text from $PRE_INSTALL_CHECK_FILE"
    exit
}

. "$BuildWorkingDir\selenium-wd\lib\ApplicationInstaller.ps1"
$androidSDKPackageIDs = "2,12,14,37,165,172"    # comma-seperated list of ids from => android list sdk --all
                                                # Includes :->
                                                #      2 - Android SDK Platform-tools, revision 25.0.4 
                                                #     12 - Android SDK Build-tools, revision 23.0.3
                                                #     14 - Android SDK Build-tools, revision 23.0.1
                                                #     37 - SDK Platform Android 6.0, API 23, revision 3
                                                #    165 - Android Support Repository, revision 47
                                                #    172 - Google Repository, revision 46
# 1.
Write-Host "[INSTALL_PRE-REQS]: Check/Install pre-requisite applications"
$installApplications = @{
    "01.choco"="Chocolatey"
    "02.7zip"="7-Zip"
    "03.nodejs"="NodeJS"
    "04.gradle"="Gradle"
    "05.android-sdk"="Android SDK"
    "06.android-sdk-packages"="Android SDK Packages;$androidSDKPackageIDs"
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
