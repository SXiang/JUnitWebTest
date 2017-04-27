# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\SetupAndroidEmulator.ps1 `
#           -BuildWorkingDir "C:\Repositories\PicarroApp" `
#           -BuildScriptsBaseDir "C:\Repositories\surveyor-qa" `
#           -SystemImage "23|google_apis|x86" `
#           -AndroidSDKPackageIDs "2,13,15,38,131" `
#           -HaxmPackageID "180"  `
#           -ForceInstallAllSDKPackages "0"
# ---------------------------------------------------------------

param
(
   [Parameter(Mandatory=$true)]
   [string] $BuildWorkingDir,                               # Eg. "C:\Repositories\PicarroApp",

   [Parameter(Mandatory=$true)]
   [string] $BuildScriptsBaseDir,                           # Eg. "C:\Repositories\surveyor-qa",

   [Parameter(Mandatory=$true)]
   [string] $SystemImage,                                   # Eg. "23|google_apis|x86",  # Use one of the [key] entries from supported images. Lookup configuration file - eg. https://dl-ssl.google.com/android/repository/sys-img/google_apis/sys-img.xml

   [Parameter(Mandatory=$true)]
   [string] $AndroidSDKPackageIDs,                          # Eg. "2,13,15,38,131", # comma-seperated list of ids from => android list sdk --all
                                                            # Includes at the time of writing this script :->
                                                            # IMPORTANT: The package IDs will need to be looked up during the execution time of the script as the IDs might have changed with new packages added to sdkmanager
                                                            #      2 - Android SDK Platform-tools, revision 25.0.4 
                                                            #     13 - Android SDK Build-tools, revision 23.0.3
                                                            #     15 - Android SDK Build-tools, revision 23.0.1
                                                            #     38 - SDK Platform Android 6.0, API 23, revision 3
                                                            #    131 - Google APIs, Android API 23, revision 1
   [Parameter(Mandatory=$true)]
   [string] $HaxmPackageID,                                 # Eg. "180", # id from => android list sdk --all
                                                            # IMPORTANT: This package ID will need to be looked up during the execution time of the script as the IDs might have changed with new packages added to sdkmanager
   [Parameter(Mandatory=$true)]
   [string] $ForceInstallAllSDKPackages                     # By default only missing SDK packages will be installed. To force install all the packages set this flag to "1". NOTE: We currently only detect packages that cause errors on re-install and prevent their reinstallation. Some packages might get reinstalled even if this flag is OFF.
)

. "$BuildScriptsBaseDir\selenium-wd\lib\SetupAndroidBuildPreReqsCommon.ps1"
. "$BuildScriptsBaseDir\selenium-wd\lib\ApplicationInstaller.ps1"

# 0.
$overrideSet = Pre-AutoInstallationCheck
if ($overrideSet) {
    Write-Host "Override found in $AUTO_PRE_INSTALL_CHECK_FILE to skip installation of Android automation pre-requisites"
    Write-Host "Android automation pre-requisites will NOT be installed."
    Write-Host "NOTE: If you wish to force installation of Android automation pre-requisites on the machine remove '$AUTO_OVERRIDE_TEXT' text from $AUTO_PRE_INSTALL_CHECK_FILE"
    exit
}

# 1.
[string]$missingPackages = $AndroidSDKPackageIDs
if ($ForceInstallAllSDKPackages -ne "1") {
    $missingPackages = Get-MissingPackageIDs -currentPackageIds $AndroidSDKPackageIDs
}

# 2.
cd $BuildWorkingDir
"Updating NPM to latest..."
npm install npm@$CHOCO_NPM_VERSION -g
npm install

# 3. 
Write-Host "[INSTALL_EMULATOR_AND_PREREQS]: Check/Install Android Build Tools, HAXM and Emulator System Image"
Write-Host "Missing SDK packages that will be installed - $missingPackages"
$installApplications = @{
    "01.appium-doctor"="Appium Doctor"
    "02.appium"="Appium"
    "03.android-sdk-packages"="Android SDK Packages;$missingPackages"
    "04.haxm"="HAXM Emulator Accelerator;$HaxmPackageID"
    "05.android-system-image"="Android System Image - $SystemImage;$SystemImage"
}
InstallApplications-FromDictTable -installAppsDictTable $installApplications
Write-Host "[INSTALL_EMULATOR_AND_PREREQS]: Done installing Android Build Tools, HAXM and Emulator System Image"

# 4. 
Write-Host "[CREATE_AVD]: Create Android AVD"
$ANDROIDHOME = $env:ANDROID_HOME
$imgParts = $SystemImage.split("|")
$apiLevel = $imgParts[0]
$tagId = $imgParts[1]
$abi = $imgParts[2]
$avdName = "android_${apiLevel}_${tagId}_${abi}"
Write-Host "Creating AVD - $avdName"
echo no | . "$ANDROIDHOME\tools\android.bat" create avd -t "Google Inc.:Google APIs:$apiLevel" --abi $abi --tag $tagId --name $avdName  # Create AVD
Write-Host "[CREATE_AVD]: Done creating Android AVD"

# 5.
Write-Host "[PRE-REQS INSTALL SUCCESS]: Creating pre-req install success marker file..."
Create-AutoPreReqsInstallSuccessMarkerFile
Write-Host "[PRE-REQS INSTALL SUCCESS]: Done creating pre-req install success marker file."
