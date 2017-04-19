# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\SetupAndroidBuildPreReqsStep02.ps1 `
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
    "01.android-sdk-packages"="Android SDK Packages;$androidSDKPackageIDs"
}
InstallApplications-FromDictTable -installAppsDictTable $installApplications
Write-Host "[INSTALL_PRE-REQS]: Done installing pre-requisite applications"

# 2.
$PRE_INSTALL_CHECK_FILE = "C:\install-overrides.txt"
$OVERRIDE_TEXT = "android-pre-reqs-present=1"
Write-Host "[PRE-REQS INSTALL SUCCESS]: Creating pre-req install success marker file..."
if (Test-Path -Path $PRE_INSTALL_CHECK_FILE) {
    Remove-Item -Path $PRE_INSTALL_CHECK_FILE
}
$markerFile = New-Item -Path $PRE_INSTALL_CHECK_FILE -ItemType File -Force
Add-Content $markerFile $OVERRIDE_TEXT
Write-Host "[PRE-REQS INSTALL SUCCESS]: Done creating pre-req install success marker file."
