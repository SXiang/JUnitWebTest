param
(
  [string] $buildWorkingDir = "C:\Repositories\surveyor-qa",
  [string] $systemImage = "23|google_apis|x86",             # use one of the [key] entries from supported images.
  [string] $androidSDKPackageIDs = "2,12,14,37",            # comma-seperated list of ids from => android list sdk --all
                                                            # Includes :->
                                                            #      2 - Android SDK Platform-tools, revision 25.0.4 
                                                            #     12 - Android SDK Build-tools, revision 23.0.3
                                                            #     14 - Android SDK Build-tools, revision 23.0.1
                                                            #     37 - SDK Platform Android 6.0, API 23, revision 3
  [string] $haxmPackageID = "179"                           # id from => android list sdk --all
)

. "$buildWorkingDir\selenium-wd\lib\ApplicationInstaller.ps1"

$EMPTY = "EMPTY"

# 1. 
Write-Host "[INSTALL_EMULATOR_AND_PREREQS]: Check/Install Android Build Tools, HAXM and Emulator System Image"
$installApplications = @{
    "01.android-sdk-packages"="Android SDK Packages;$androidSDKPackageIDs"
    "02.haxm"="HAXM Emulator Accelerator;$haxmPackageID"
    "03.android-system-image"="Android System Image - $systemImage;$systemImage"
}
InstallApplications-FromDictTable -installAppsDictTable $installApplications
Write-Host "[INSTALL_EMULATOR_AND_PREREQS]: Done installing Android Build Tools, HAXM and Emulator System Image"

# 2. 
Write-Host "[CREATE_AVD]: Create Android AVD"
$imgParts = $systemImage.split("|")
$apiLevel = $imgParts[0]
$tagId = $imgParts[1]
$abi = $imgParts[2]
$avdName = "android_$apiLevel_$tagId_$abi"
echo no | . "$ANDROIDHOME\tools\android.bat" create avd -t android-$apiLevel --abi $abi --tag $tagId --name $avdName  # Create AVD
Write-Host "[CREATE_AVD]: Done creating Android AVD"
