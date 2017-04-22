
$PRE_INSTALL_CHECK_FILE = "C:\install-overrides.txt"
$OVERRIDE_TEXT = "android-pre-reqs-present=1"

# This check is added to override installation of pre-requisites on the machine once we have determined no re-install is necessary on the box.
# To force installation remove $OVERRIDE_TEXT from $PRE_INSTALL_CHECK_FILE
function Pre-InstallationCheck() {
    if (Test-path -Path $PRE_INSTALL_CHECK_FILE) {
        $override = $false
        Get-Content $PRE_INSTALL_CHECK_FILE | %{
            $line = $_
            if ($line -eq $OVERRIDE_TEXT) {
                $override = $true
            }
        }
    }
    
    $override    
}
