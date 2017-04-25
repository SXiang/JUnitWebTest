# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\SetupAndroidBuildPreReqsStep02.ps1 `
#           -BuildWorkingDir "C:\Repositories\surveyor-qa" `
#           -AndroidSDKPackageIDs "2,13,15,38,166,173"  `
#           -ForceInstallAllSDKPackages "1"
# ---------------------------------------------------------------

param
(
  [Parameter(Mandatory=$true)]
  [string] $BuildWorkingDir,              # Eg. "C:\Repositories\surveyor-qa"

  [Parameter(Mandatory=$true)]
  [string] $AndroidSDKPackageIDs,         # Eg. $AndroidSDKPackageIDs = "2,13,15,38,166,173"    # comma-seperated list of ids from => android list sdk --all
                                                # Includes at the time of writing this script :->
                                                # IMPORTANT: The package IDs will need to be looked up during the execution time of the script as the IDs might have changed with new packages added to sdkmanager
                                                #      2 - Android SDK Platform-tools, revision 25.0.4 
                                                #     13 - Android SDK Build-tools, revision 23.0.3
                                                #     15 - Android SDK Build-tools, revision 23.0.1
                                                #     38 - SDK Platform Android 6.0, API 23, revision 3
                                                #    166 - Android Support Repository, revision 47
                                                #    173 - Google Repository, revision 46

   [Parameter(Mandatory=$true)]
   [string] $ForceInstallAllSDKPackages   # By default only missing SDK packages will be installed. To force install all the packages set this flag to "1". NOTE: We currently only detect packages that cause errors on re-install and prevent their reinstallation. Some packages might get reinstalled even if this flag is OFF.
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
[string]$missingPackages = $AndroidSDKPackageIDs
if ($ForceInstallAllSDKPackages -ne "1") {
    $missingPackages = Get-MissingPackageIDs -currentPackageIds 
}

# 2.
Write-Host "[INSTALL_PRE-REQS]: Check/Install pre-requisite applications"
$installApplications = @{
    "01.android-sdk-packages"="Android SDK Packages;$missingPackages"
}
InstallApplications-FromDictTable -installAppsDictTable $installApplications
Write-Host "[INSTALL_PRE-REQS]: Done installing pre-requisite applications"

# 3.
Write-Host "[PRE-REQS INSTALL SUCCESS]: Creating pre-req install success marker file..."
Create-PreReqsInstallSuccessMarkerFile
Write-Host "[PRE-REQS INSTALL SUCCESS]: Done creating pre-req install success marker file."
