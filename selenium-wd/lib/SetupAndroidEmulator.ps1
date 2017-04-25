param
(
  [string] $buildWorkingDir = "C:\Repositories\surveyor-qa",
  [string] $systemImage = "23|google_apis|x86",             # use one of the [key] entries from supported images. Lookup configuration file - eg. https://dl-ssl.google.com/android/repository/sys-img/google_apis/sys-img.xml
  [string] $androidSDKPackageIDs = "2,13,15,38,131",        # comma-seperated list of ids from => android list sdk --all
                                                            # Includes at the time of writing this script :->
                                                            # IMPORTANT: The package IDs will need to be looked up during the execution time of the script as the IDs might have changed with new packages added to sdkmanager
                                                            #      2 - Android SDK Platform-tools, revision 25.0.4 
                                                            #     13 - Android SDK Build-tools, revision 23.0.3
                                                            #     15 - Android SDK Build-tools, revision 23.0.1
                                                            #     38 - SDK Platform Android 6.0, API 23, revision 3
                                                            #    131 - Google APIs, Android API 23, revision 1
  [string] $haxmPackageID = "180"                           # id from => android list sdk --all
                                                            # IMPORTANT: This package ID will need to be looked up during the execution time of the script as the IDs might have changed with new packages added to sdkmanager

)

. "$buildWorkingDir\selenium-wd\lib\ApplicationInstaller.ps1"

"Updating NPM to latest..."
npm install npm@latest -g

# 1. 
Write-Host "[INSTALL_EMULATOR_AND_PREREQS]: Check/Install Android Build Tools, HAXM and Emulator System Image"
$installApplications = @{
    "01.appium-doctor"="Appium Doctor"
    "02.appium"="Appium"
    "03.android-sdk-packages"="Android SDK Packages;$androidSDKPackageIDs"
    "04.haxm"="HAXM Emulator Accelerator;$haxmPackageID"
    "05.android-system-image"="Android System Image - $systemImage;$systemImage"
}
InstallApplications-FromDictTable -installAppsDictTable $installApplications
Write-Host "[INSTALL_EMULATOR_AND_PREREQS]: Done installing Android Build Tools, HAXM and Emulator System Image"

# 2. 
Write-Host "[CREATE_AVD]: Create Android AVD"
$ANDROIDHOME = $env:ANDROID_HOME
$imgParts = $systemImage.split("|")
$apiLevel = $imgParts[0]
$tagId = $imgParts[1]
$abi = $imgParts[2]
$avdName = "android_${apiLevel}_${tagId}_${abi}"
echo no | . "$ANDROIDHOME\tools\android.bat" create avd -t "Google Inc.:Google APIs:$apiLevel" --abi $abi --tag $tagId --name $avdName  # Create AVD
Write-Host "[CREATE_AVD]: Done creating Android AVD"
