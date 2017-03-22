param
(
  [string] $buildWorkingDir = "C:\Repositories\surveyor-qa",
  [string] $systemImage = "25|google_apis|x86_64",         # use one of the [key] entries from supported images.
  [string] $androidSDKPackageIDs = "5"                     # comma-seperated list. 5= Build tools (version 25)  
)

. "$buildWorkingDir\selenium-wd\lib\ApplicationInstaller.ps1"

$EMPTY = "EMPTY"

# 1. 
Write-Host "[INSTALL_PRE-REQS]: Check/Install pre-requisite applications"
$installApplications = @{
    "01.choco"="Chocolatey"
    "02.7zip"="7-Zip"
    "03.nodejs"="NodeJS"
    "04.appium-doctor"="Appium Doctor"
    "05.appium"="Appium"
    "06.android-sdk"="Android SDK"
}

# check/install each of the pre-requisite application.
$installApplications.Keys | Sort-Object | %{
    $key = $_
    $appKey = $EMPTY
    if ($key -match "\d+\.(.+)") {
        $appKey = $matches[1]
    } else {
        Write-Error "Ensure installApplication key is in correct format. Format -> 'XX.<name_of_app>' "
    }

    if ($appKey -ne $EMPTY) {
        $appName = $installApplications.get_item($key)

        Write-Host "Checking if $appName is installed ..."
        $appInstalled = IsInstalled -application "$appKey"
        if ($appInstalled -eq $false) {
            "$appName NOT installed. Installing ..."
            InstallApplication -application "$appKey"

        } else {
            "$appName is already installed. Skipping install."
        }
    }
}
Write-Host "[INSTALL_PRE-REQS]: Done installing pre-requisite applications"

# 2. 
Write-Host "[INSTALL_ANDROID_SDK_PACKAGES]: Start installing Android SDK packages"
$ANDROIDHOME = $env:ANDROID_HOME
echo y | . "$ANDROIDHOME\tools\android.bat" update sdk -u -a -t $androidSDKPackageIDs --force  # Build tools and Google USB Driver
Write-Host "[INSTALL_ANDROID_SDK_PACKAGES]: Done installing Android SDK packages"

# 3. 
Write-Host "[INSTALL_ANDROID_SYSTEM_IMAGE]: Installing Requested Android System Image"
$imgParts = $systemImage.split("|")
$apiLevel = $imgParts[0]
$tagId = $imgParts[1]
$abi = $imgParts[2]
echo y | . "$ANDROIDHOME\tools\bin\sdkmanager" "system-images;android-$apiLevel;$tagId;$abi"
Write-Host "[INSTALL_ANDROID_SYSTEM_IMAGE]: Done Installing Requested Android System Image"

# 4. 
Write-Host "[SETUP_ENVIRONMENT_VARIABLES]: Ensuring correct environment variables"
[string]$envPath = [string]$env:Path
[string]$envAndroidHome = [string]$env:ANDROID_HOME
if (-not $envPath.Contains($envAndroidHome)) {
    Write-Host "Setting ANDROID_HOME to PATH"
    [Environment]::SetEnvironmentVariable("Path", "$envPath;$envAndroidHome", "Machine")
}
Write-Host "[SETUP_ENVIRONMENT_VARIABLES]: Done setting up environment variables"
