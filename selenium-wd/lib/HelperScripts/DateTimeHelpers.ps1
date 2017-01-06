<#--------------------------------------------------------------------------------------------------------------------------------
 DESCRIPTION: 
  - This script contains datetime helper functions.
----------------------------------------------------------------------------------------------------------------------------------#>

# ------------------------------------------------------------
# Converts epochTime to .NET dateTime object.
# ------------------------------------------------------------
function FromUnixTime([double] $epochTime) {
    $epoch = New-Object System.DateTime -ArgumentList (1970,1,1,0,0,0,0)
    $dt = $epoch.AddSeconds($epochTime)
    $dt
}

# ------------------------------------------------------------
# Converts .NET datetime to Unix epoch time
# ------------------------------------------------------------
function ToUnixTime([System.DateTime] $dateTime) {
    $referenceTime = New-Object System.DateTime -ArgumentList (1970,1,1,0,0,0,0)
    [System.TimeSpan]$timeSpan = $dateTime - $referenceTime;
    $timeSpan.TotalSeconds
}