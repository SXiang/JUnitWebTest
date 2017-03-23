$7ZIP_REG_PATH = 'HKCU:\Software\7-Zip'
$7ZIP_EXE_NAME = '7z.exe'
$7ZIP_DOWNLOAD_URL = 'http://www.7-zip.org/a/7z920-x64.msi'
$7ZIP_MSI_DEST = '${TEMP_PATH}7zip-x64.msi'
$7ZIP_INSTALL_LOG = '${TEMP_PATH}7zip-install.log'

$PYTHON_APP_INSTALL_TEXT = 'Python 2.7.'
$CHOCOLATEY_APP_INSTALL_TEXT = 'Chocolatey v'
$ANDROID_SDK_INSTALL_VERSION = '25.2.3'
$NODEJS_INSTALL_VERSION = '4.1.2'
$NODEJS_INSTALL_TEXT = $NODEJS_INSTALL_VERSION
$APPIUM_INSTALL_TEXT = 'appium'
$APPIUM_DOCTOR_INSTALL_TEXT = 'appium-doctor'
$INSTALLED_NPM_VERSION = '2.'
$CURL_APP_INSTALL_TEXT1 = 'curl'
$CURL_APP_INSTALL_TEXT2 = 'Protocols'
$CURL_APP_INSTALL_TEXT3 = 'Features'
$GRADLE_APP_INSTALL_TEXT1 = 'Gradle'
$GRADLE_APP_INSTALL_TEXT2 = 'Build time'
$GRADLE_APP_INSTALL_TEXT3 = 'Build number'

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
        $appCmd = choco 2>&1
        $appVer = $appCmd.ToString()
        $isInstalled = $appVer.StartsWith($CHOCOLATEY_APP_INSTALL_TEXT)
    } elseif ($application -eq 'nodejs') {
        $appCmd = npm --version 2>&1
        $appVer = $appCmd.ToString()
        $isInstalled = $appVer.StartsWith($INSTALLED_NPM_VERSION)
    } elseif ($application -eq 'appium-doctor') {
        $appCmd = npm list -g 2>&1
        $found = $false
        $appCmd | % {
            [string]$line = [string]$_
            if ($line.Contains($APPIUM_DOCTOR_INSTALL_TEXT)) {
                $found = $true
            }
        }
        $isInstalled = $found
    } elseif ($application -eq 'appium') {
        $appCmd = npm list -g 2>&1
        $found = $false
        $appCmd | % {
            [string]$line = [string]$_
            if ($line.Contains($APPIUM_INSTALL_TEXT) -and (-not $line.Contains("doctor"))) {
                $found = $true
            }
        }
        $isInstalled = $found
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
        $appCmd = curl --version 2>&1
        $isInstalled = ($appCmd[0].Contains($CURL_APP_INSTALL_TEXT1) -and $appCmd[1].Contains($CURL_APP_INSTALL_TEXT2) -and $appCmd[2].Contains($CURL_APP_INSTALL_TEXT3))
    } elseif ($application -eq 'gradle') {
        $appCmd = gradle --version 2>&1
        $isInstalled = ($appCmd[2].Contains($GRADLE_APP_INSTALL_TEXT1) -and $appCmd[5].Contains($GRADLE_APP_INSTALL_TEXT2) -and $appCmd[6].Contains($GRADLE_APP_INSTALL_TEXT3))
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
        # install python 2.7.6
        choco install python-x86_32 -version 2.7.6 -y --force
    } elseif ($application -eq 'choco') {
        iex ((new-object net.webclient).DownloadString($CHOCOLATEY_INSTALL_SCRIPT_PATH))
        SET PATH=%PATH%;%ALLUSERSPROFILE%\chocolatey\bin
    } elseif ($application -eq 'curl') {
        choco install curl -y --force
    } elseif ($application -eq 'nodejs') {
        choco install nodejs.install --version $NODEJS_INSTALL_VERSION -y --force
    } elseif ($application -eq 'appium') {
        npm install -g appium
    } elseif ($application -eq 'appium-doctor') {
        npm install -g appium-doctor
    } elseif ($application -eq 'android-sdk') {
        choco install android-sdk --version $ANDROID_SDK_INSTALL_VERSION -y --force
    } elseif ($application -eq 'android-sdk-packages') {
        $androidSDKPackageIDs = $param
        "Installing Android SDK packages. Package IDs - $androidSDKPackageIDs"
        echo y | . "$ANDROIDHOME\tools\android.bat" update sdk -u -a -t $androidSDKPackageIDs --force  # Build tools
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