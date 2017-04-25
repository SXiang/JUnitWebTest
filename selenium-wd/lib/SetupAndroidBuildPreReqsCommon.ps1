$PRE_INSTALL_CHECK_FILE = "C:\install-overrides.txt"
$OVERRIDE_TEXT = "android-pre-reqs-present=1"

$AUTO_PRE_INSTALL_CHECK_FILE = "C:\automation-install-overrides.txt"
$AUTO_OVERRIDE_TEXT = "android-automation-pre-reqs-present=1"

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