﻿# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\SetupAndroidEmulator.ps1 `
#           -BuildWorkingDir "C:\Repositories\surveyor-qa" `
#           -SystemImage "23|google_apis|x86" `
#           -AndroidSDKPackageIDs "2,13,15,38,131" `
#           -HaxmPackageID "180"
# ---------------------------------------------------------------

param
(
   [Parameter(Mandatory=$true)]
   [string] $BuildWorkingDir,                               # Eg. "C:\Repositories\surveyor-qa",

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
   [string] $HaxmPackageID                                  # Eg. "180", # id from => android list sdk --all
                                                            # IMPORTANT: This package ID will need to be looked up during the execution time of the script as the IDs might have changed with new packages added to sdkmanager

)

. "$BuildWorkingDir\selenium-wd\lib\SetupAndroidBuildPreReqsCommon.ps1"
. "$BuildWorkingDir\selenium-wd\lib\ApplicationInstaller.ps1"

# 0.
$overrideSet = Pre-AutoInstallationCheck
if ($overrideSet) {
    Write-Host "Override found in $AUTO_PRE_INSTALL_CHECK_FILE to skip installation of Android automation pre-requisites"
    Write-Host "Android automation pre-requisites will NOT be installed."
    Write-Host "NOTE: If you wish to force installation of Android automation pre-requisites on the machine remove '$AUTO_OVERRIDE_TEXT' text from $AUTO_PRE_INSTALL_CHECK_FILE"
    exit
}

"Updating NPM to latest..."
npm install npm@latest -g

# 1. 
Write-Host "[INSTALL_EMULATOR_AND_PREREQS]: Check/Install Android Build Tools, HAXM and Emulator System Image"
$installApplications = @{
    "01.appium-doctor"="Appium Doctor"
    "02.appium"="Appium"
    "03.android-sdk-packages"="Android SDK Packages;$AndroidSDKPackageIDs"
    "04.haxm"="HAXM Emulator Accelerator;$HaxmPackageID"
    "05.android-system-image"="Android System Image - $SystemImage;$SystemImage"
}
InstallApplications-FromDictTable -installAppsDictTable $installApplications
Write-Host "[INSTALL_EMULATOR_AND_PREREQS]: Done installing Android Build Tools, HAXM and Emulator System Image"

# 2. 
Write-Host "[CREATE_AVD]: Create Android AVD"
$ANDROIDHOME = $env:ANDROID_HOME
$imgParts = $SystemImage.split("|")
$apiLevel = $imgParts[0]
$tagId = $imgParts[1]
$abi = $imgParts[2]
$avdName = "android_${apiLevel}_${tagId}_${abi}"
echo no | . "$ANDROIDHOME\tools\android.bat" create avd -t "Google Inc.:Google APIs:$apiLevel" --abi $abi --tag $tagId --name $avdName  # Create AVD
Write-Host "[CREATE_AVD]: Done creating Android AVD"

# 3.
Write-Host "[PRE-REQS INSTALL SUCCESS]: Creating pre-req install success marker file..."
Create-AutoPreReqsInstallSuccessMarkerFile
Write-Host "[PRE-REQS INSTALL SUCCESS]: Done creating pre-req install success marker file."
