$7ZIP_REG_PATH = 'HKCU:\Software\7-Zip'
$7ZIP_EXE_NAME = '7z.exe'
$7ZIP_DOWNLOAD_URL = 'http://www.7-zip.org/a/7z920-x64.msi'
$7ZIP_MSI_DEST = '${TEMP_PATH}7zip-x64.msi'
$7ZIP_INSTALL_LOG = '${TEMP_PATH}7zip-install.log'

$PYTHON_APP_INSTALL_TEXT = 'Python 2.7.'
$CHOCOLATEY_APP_INSTALL_TEXT = 'Chocolatey v'
$ANDROID_SDK_INSTALL_VERSION = '25.2.3'
$APPIUM_INSTALL_TEXT = 'appium'
$APPIUM_DOCTOR_INSTALL_TEXT = 'appium-doctor'
$HAXM_INSTALL_TEXT = 'HAXM is installed'
$INSTALLED_NPM_VERSION = '4.'
$CURL_APP_INSTALL_TEXT1 = 'curl'
$CURL_APP_INSTALL_TEXT2 = 'Protocols'
$CURL_APP_INSTALL_TEXT3 = 'Features'
$GRADLE_APP_INSTALL_TEXT1 = 'Gradle'
$GRADLE_APP_INSTALL_TEXT2 = 'Build time'
$REACT_NATIVE_CLI_PACKAGE = "react-native-cli"

$CHOCOLATEY_INSTALL_SCRIPT_PATH = 'https://chocolatey.org/install.ps1'

$LOCALAPPDATA = $env:LOCALAPPDATA
$PROGRAMFILES = $env:ProgramFiles
$PROGRAMFILES86 = ${env:ProgramFiles(x86)}
$ANDROIDHOME = $env:ANDROID_HOME

#---------------------------------------------------------
# Checks if specified application is installed or not.
# Returns TRUE if installed, FALSE otherwise.
#---------------------------------------------------------
function IsInstalled($application) {
    $isInstalled = $false

    if ($application -eq 'python') {
        # check if python 2.7.x is installed.
        $appCmd = python --version 2>&1
        $appVer = $appCmd.ToString()
        $isInstalled = $appVer.StartsWith($PYTHON_APP_INSTALL_TEXT)
    } elseif ($application -eq 'choco') {
        $appCmdLines = choco 2>&1
        $isInstalled = Array-Contains -arrayLines $appCmdLines -positivematchText "$CHOCOLATEY_APP_INSTALL_TEXT" -negativematchText "ERROR"
    } elseif ($application -eq 'nodejs') {
        $appCmd = npm --version 2>&1
        $appVer = $appCmd.ToString()
        $isInstalled = $appVer.StartsWith($INSTALLED_NPM_VERSION)
    } elseif ($application -eq 'appium') {
        $appCmdLines = npm list --depth=0 -g 2>&1
        $isInstalled = Array-Contains -arrayLines $appCmdLines -positivematchText "$APPIUM_INSTALL_TEXT" -negativematchText "doctor"
    } elseif ($application -eq 'appium-doctor') {
        $appCmdLines = npm list --depth=0 -g 2>&1
        $isInstalled = Array-Contains -arrayLines $appCmdLines -positivematchText "$APPIUM_DOCTOR_INSTALL_TEXT"
    } elseif ($application -eq 'haxm') {
        $appCmd = . "$ANDROIDHOME\tools\emulator" -accel-check 2>&1
        $isInstalled = Array-Contains -arrayLines $appCmdLines -positivematchText "$HAXM_INSTALL_TEXT"
    } elseif ($application -eq 'android-sdk') {
        $uiautomatorPresent = (Test-Path "$LOCALAPPDATA\Android\sdk\tools\bin\uiautomatorviewer.bat") `
            -or (Test-Path "$PROGRAMFILES\Android\android-sdk\tools\uiautomatorviewer.bat") `
            -or (Test-Path "$PROGRAMFILES86\Android\android-sdk\tools\uiautomatorviewer.bat")
        $adbPresent = (Test-Path "$LOCALAPPDATA\Android\sdk\platform-tools\adb.exe") `
            -or (Test-Path "$PROGRAMFILES\Android\android-sdk\tools\adb.exe") `
            -or (Test-Path "$PROGRAMFILES86\Android\android-sdk\tools\adb.exe")
        $isInstalled = ($uiautomatorPresent -and $adbPresent)
    } elseif ($application -eq 'android-sdk-packages') {
        $buildToolsFolderPresent = (Test-Path "$LOCALAPPDATA\Android\sdk\build-tools") `
            -or (Test-Path "$PROGRAMFILES\Android\android-sdk\build-tools") `
            -or (Test-Path "$PROGRAMFILES86\Android\android-sdk\build-tools")
        $platformFolderPresent = (Test-Path "$LOCALAPPDATA\Android\sdk\platform") `
            -or (Test-Path "$PROGRAMFILES\Android\android-sdk\platform") `
            -or (Test-Path "$PROGRAMFILES86\Android\android-sdk\platform")
        $platformToolsFolderPresent = (Test-Path "$LOCALAPPDATA\Android\sdk\platform-tools") `
            -or (Test-Path "$PROGRAMFILES\Android\android-sdk\platform-tools") `
            -or (Test-Path "$PROGRAMFILES86\Android\android-sdk\platform-tools")
        $isInstalled = ($buildToolsFolderPresent -and $platformFolderPresent -and $platformToolsFolderPresent)
    } elseif ($application -eq 'android-system-image') {
        $isInstalled = $false   # bypass. let sdkmanager do the check when installing.
    } elseif ($application -eq 'curl') {
        $response = curl https://www.google.com 2>&1
        $isInstalled = (($response -ne $null) -and ($response.StatusCode -eq 200))
    } elseif ($application -eq 'gradle') {
        $appCmd = gradle --version 2>&1
        $isInstalled = ($appCmd[2].Contains($GRADLE_APP_INSTALL_TEXT1) -and $appCmd[5].Contains($GRADLE_APP_INSTALL_TEXT2))
    } elseif ($application -eq 'react-native-cli') {
        $isInstalled = IsNodePackageInstalled -packageName $REACT_NATIVE_CLI_PACKAGE
    } elseif ($application -eq '7zip') {
        # get Install folder from Registry and check for presence of 7zip EXE in the folder.
        if ((Test-Path $7ZIP_REG_PATH -PathType Any) -eq $true) {
        	$7ZipFolder = (Get-ItemProperty -Path $7ZIP_REG_PATH).Path
        	if (Test-Path "${7ZipFolder}\${7ZIP_EXE_NAME}") {
                $isInstalled = $true
        	}
        }
    }

    return $isInstalled
}

#---------------------------------------------------------
# Installs the specified application.
#---------------------------------------------------------
function InstallApplication($application, $param) {
    if ($application -eq 'python') {
        # install python 2.7.6 (x86)
        choco install python.x86 -version 2.7.6 -y --force
    } elseif ($application -eq 'choco') {
        iex ((new-object net.webclient).DownloadString($CHOCOLATEY_INSTALL_SCRIPT_PATH))
    } elseif ($application -eq 'curl') {
        choco install curl -y --force
    } elseif ($application -eq 'nodejs') {
        choco install nodejs.install -y --force
    } elseif ($application -eq 'appium') {
        # Update package manager in case of issues => 'npm i -g npm'
        npm install -g appium@1.6.3
    } elseif ($application -eq 'appium-doctor') {
        npm install -g appium-doctor@1.4.2
    } elseif ($application -eq 'haxm') {
        $haxmPackageID = $param
        "Installing HAXM. Android Package ID - $haxmPackageID"
        echo y | . "$ANDROIDHOME\tools\android.bat" update sdk -u -a -t $haxmPackageID
        "Done installing HAXM"
    } elseif ($application -eq 'android-sdk') {
        choco install android-sdk --version $ANDROID_SDK_INSTALL_VERSION -y
    } elseif ($application -eq 'android-sdk-packages') {
        $androidSDKPackageIDs = $param
        "Installing Android SDK packages. Package IDs - $androidSDKPackageIDs"
        echo y | . "$ANDROIDHOME\tools\android.bat" update sdk -u -a -t $androidSDKPackageIDs
        "Done installing Android SDK packages"
    } elseif ($application -eq 'android-system-image') {
        $systemImage = $param
        $imgParts = $systemImage.split("|")
        $apiLevel = $imgParts[0]
        $tagId = $imgParts[1]
        $abi = $imgParts[2]
        "Installing system image - android-$apiLevel;$tagId;$abi"
        echo y | . "$ANDROIDHOME\tools\bin\sdkmanager" "system-images;android-$apiLevel;$tagId;$abi"
        "Done system image install"
    } elseif ($application -eq 'gradle') {
        choco install gradle -y --force
    } elseif ($application -eq 'react-native-cli') {
        npm install -g react-native-cli@2.0.1
    } elseif ($application -eq '7zip') {
        # Download msi and trigger quiet install.
        "Start downloading 7-zip .msi to $7zipDest"
        (new-object System.Net.WebClient).DownloadFile($7ZIP_DOWNLOAD_URL, $7ZIP_MSI_DEST)
        "Successfully downloaded 7-zip"
        if (Test-Path $7ZIP_MSI_DEST) {
            "Start 7-zip install - $7ZIP_MSI_DEST"
            Start-Process -FilePath $7ZIP_MSI_DEST -ArgumentList '/qn /l*v $7ZIP_INSTALL_LOG' | Wait-Process
            "Installed 7-zip. View logs at - '$7ZIP_INSTALL_LOG'"
        }
    }
}

#---------------------------------------------------------
# Check/Install the applications specified in the dictionary.
#---------------------------------------------------------
function InstallApplications-FromDictTable($installAppsDictTable) {
    # check/install each of the pre-requisite application.
    $installAppsDictTable.Keys | Sort-Object | %{
        $key = $_
        $appKey = $EMPTY
        if ($key -match "\d+\.(.+)") {
            $appKey = $matches[1]
        } else {
            Write-Error "Ensure installApplication key is in correct format. Format -> 'XX.<name_of_app>' "
        }

        if ($appKey -ne $EMPTY) {
            [string]$appValue = [string]$installApplications.get_item($key)
            $appValueParts = @($appValue.Split(";"))
            $appName = $appValueParts[0]
            $installArgs = $null
            if ($appValueParts.Length -gt 1) {
                $installArgs = $appValueParts[1]
            }

            Write-Host "Checking if $appName is installed ..."
            $appInstalled = IsInstalled -application "$appKey"
            if ($appInstalled -eq $false) {
                "$appName NOT installed. Installing ..."
                InstallApplication -application "$appKey" -param $installArgs
            } else {
                "$appName is already installed. Skipping install."
            }
        }
    }
}

# NOTE: Restart-Computer is needed after disabling user access control.
function Windows-DisableUserAccessControl() {
    "Windows - Disabling user access control."
    Set-ItemProperty -Path registry::HKEY_LOCAL_MACHINE\SOFTWARE\Microsoft\Windows\CurrentVersion\Policies\System -Name EnableLUA -Value 0
    "Windows - User access control disabled."
}

function Array-Contains($arrayLines, $positivematchText, $negativematchText) {
    $found = $false
    $arrayLines | % {
        [string]$line = [string]$_
        if ($line.Contains($positivematchText) -and (($negativematchText -eq $null) -or (-not $line.Contains($negativematchText)))) {
            $found = $true
        }
    }
    $found
}

function IsNodePackageInstalled($packageName) {
    $appCmdLines = npm list -g --depth=0 $packageName 2>&1
    $installed = Array-Contains -arrayLines $appCmdLines -positivematchText "$packageName" -negativematchText "(empty)"
    $installed
}