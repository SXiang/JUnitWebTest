$PRE_INSTALL_CHECK_FILE = "C:\install-overrides.txt"
$OVERRIDE_TEXT = "android-pre-reqs-present=1"

$AUTO_PRE_INSTALL_CHECK_FILE = "C:\automation-install-overrides.txt"
$AUTO_OVERRIDE_TEXT = "android-automation-pre-reqs-present=1"

$SDK_MANAGER_PACKAGE_INSTALL_CHECK_MAP = @{
    "Android Support Repository"="extras\android\m2repository"
    "Google Play services"="extras\google\"
    "Google Repository"="extras\google\m2repository"
    "Google APIs, Android API 23"="add-ons\addon"
}

# This check is added to override installation of pre-requisites on the machine once we have determined no re-install is necessary on the box.
# To force installation remove $OVERRIDE_TEXT from $PRE_INSTALL_CHECK_FILE
function Execute-InstallationCheck($installCheckFile, $overrideText) {
    $override = $false
    if (Test-path -Path $installCheckFile) {
        Get-Content $installCheckFile | %{
            $line = $_
            if ($line -eq $overrideText) {
                $override = $true
            }
        }
    }
    
    $override    
}

function Generate-SuccessMarkerFile($installCheckFile, $overrideText) {
    if (Test-Path -Path $installCheckFile) {
        Remove-Item -Path $installCheckFile
    }
    $markerFile = New-Item -Path $installCheckFile -ItemType File -Force
    Add-Content $markerFile $overrideText
}

function Pre-InstallationCheck() {
    Execute-InstallationCheck -installCheckFile $PRE_INSTALL_CHECK_FILE -overrideText $OVERRIDE_TEXT
}

function Pre-AutoInstallationCheck() {
    Execute-InstallationCheck -installCheckFile $AUTO_PRE_INSTALL_CHECK_FILE -overrideText $AUTO_OVERRIDE_TEXT
}

function Create-PreReqsInstallSuccessMarkerFile() {
    Generate-SuccessMarkerFile -installCheckFile $PRE_INSTALL_CHECK_FILE -overrideText $OVERRIDE_TEXT
}

function Create-AutoPreReqsInstallSuccessMarkerFile() {
    Generate-SuccessMarkerFile -installCheckFile $AUTO_PRE_INSTALL_CHECK_FILE -overrideText $AUTO_OVERRIDE_TEXT
}

$PACKAGE_NAME_PATTERN = "\s+(\d+)-\s+(.+),\s+revision\s+(.+)"
$SDK_PLATFORM_VERSION_PATTERN = "(.+)(\d+\.\d+),\s+API\s+(\d+)"

$packageMap = @{}

function Get-PackageNameAndVersionForPackageId([int]$packageId) {
    $retObj = $null
    if ($packageMap.ContainsKey($packageId)) {
        $retObj = $packageMap.get_item($packageId)
    } else {    
        $cmdLines = android list sdk --all 2>&1
        $packageName = ""
        $packageVersion = ""
        $cmdLines | %{
            $line = $_
	        if ($line -match $PACKAGE_NAME_PATTERN) {
		        [int]$pid = [int]$Matches[1]
                if ($pid -eq $packageId) {
                    $packageName = $Matches[2]
                    $packageVersion = $Matches[3]
                }
	        }
        }

        $retObj = New-Object PSObject -Property @{                        PackageId       = $packageId                             PackageName     = $packageName                          PackageVersion  = $packageVersion    
        }

        $packageMap.set_item($packageId, $retObj)
    }

    $retObj
}

# valid package names:
#  Android SDK - checks whether android sdk is installed   
#  Android SDK Build-tools - checks whether android build tools is installed (version format=x.x.x)
#  SDK Platform Android - checks whether android platform is installed   (version name deduced from $packageName. Sample package name=[SDK Platform Android 6.0, API 23])
#  Android SDK Platform-tools - checks whether android platform tools is installed
#  Android SDK Tools - checks whether android tools is installed
#  Intel x86 Emulator Accelerator - checks whether Hardware_Accelerated_Execution_Manager is installed
function Get-AndroidPackageDirectory([string]$packageName, [string]$packageVersion) {
    $andHome = $env:ANDROID_HOME
    $packageDirName = ""
    if ($andHome -ne $NULL -and $andHome -ne "") {
        if ($packageName -eq "Android SDK") {
            $packageDirName = $andHome
        } elseif ($packageName -eq "Android SDK Build-tools") {
            $packageDirName = "$andHome\build-tools\$packageVersion"
        } elseif ($packageName.StartsWith("SDK Platform Android")) {
            if ($packageName -match $SDK_PLATFORM_VERSION_PATTERN) {
                $version = $Matches[3]
                $packageDirName = "$andHome\platforms\android-$version"
            }
        } elseif ($packageName -eq "Android SDK Platform-tools") {
            $packageDirName = "$andHome\platform-tools"
        } elseif ($packageName -eq "Android SDK Tools") {
            $packageDirName = "$andHome\tools"
        } elseif ($packageName -eq "Intel x86 Emulator Accelerator") {
            $packageDirName = "$andHome\extras\intel\Hardware_Accelerated_Execution_Manager"
        } else {
            throw [System.Exception] "PackageName-$packageName' NOT supported."
        }
    }

    Write-Host "Android Package Directory for package=[$packageName], version=[$packageVersion] -> [$packageDirName]"
    $packageDirName
}

# valid package names:
#  Android SDK - checks whether android sdk is installed   
#  Android SDK Build-tools - checks whether android build tools is installed (version format=x.x.x)
#  SDK Platform Android - checks whether android platform is installed   (version name deduced from $packageName)
#  Android SDK Platform-tools - checks whether android platform tools is installed
#  Android SDK Tools - checks whether android tools is installed
#  Intel x86 Emulator Accelerator - checks whether Hardware_Accelerated_Execution_Manager is installed
function IsInstalled-AndroidSDKPackage($packageName, $packageVersion) {
    $isInstalled = $false
    if ($SDK_MANAGER_PACKAGE_INSTALL_CHECK_MAP.ContainsKey($packageName)) {
        $andHome = $env:ANDROID_HOME
        $instPkgs = . "$andHome\tools\bin\sdkmanager.bat" --list
        $pkgLocation = $SDK_MANAGER_PACKAGE_INSTALL_CHECK_MAP.get_item($packageName)
        $instPkgs | % {
            [string]$line = [string]$_; 
            if ($line.tolower().contains($packageName.tolower()) -and $line.contains($pkgLocation)) { 
                $isInstalled = $true
            } 
        }
    } else {
        $packageDirectory = Get-AndroidPackageDirectory -packageName $packageName -packageVersion $packageVersion
        if (($packageDirectory -ne "") -and (Test-Path $packageDirectory)) {
            $itemsInDir = (Get-ChildItem $packageDirectory | Measure-Object).Count
            if ($itemsInDir -gt 0) {
                $isInstalled = $true
            }
        }
    }

    $isInstalled
}

function Get-MissingPackageIDs($currentPackageIds) {
    $pIdValues = $currentPackageIds -split ","
    [string]$missingPackages = ""
    $pIdValues | %{
        [int]$pckId = [int]$_
        $pckObj = Get-PackageNameAndVersionForPackageId -packageId $pckId
        $pckName = $pckObj.PackageName
        $pckVersion = $pckObj.PackageVersion
        Write-Host "Checking if package id=[$pckId] is installed..."
        $installed = IsInstalled-AndroidSDKPackage -packageName "$pckName" -packageVersion "$pckVersion"
        Write-Host "Installed=[$installed]"
        if (-not $installed) {
            if ($missingPackages -eq "") {
                $missingPackages = "$pckId"
            } else {
                $missingPackages = "${missingPackages},${pckId}" 
            }
        }
    }

    $missingPackages
}