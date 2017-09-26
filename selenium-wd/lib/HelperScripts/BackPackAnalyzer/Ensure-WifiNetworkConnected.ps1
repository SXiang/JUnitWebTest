# ---------------------------------------------------------------
# SCRIPT: This is helper script to check if machine is connected to a wifi network and connect if not connected.
#         Script executes in a loop and will pause for number of seconds specified in 'CheckInterval' before next check.
#         
# USAGE:
#   .\Ensure-WifiNetworkConnected.ps1 -CheckInterval 3 -wifiSSID "Nomad4001v"
# ---------------------------------------------------------------

param (
    [Int32] $CheckInterval = 3,           # seconds to pause before performing next check.  
    [String]$WifiSSID = "Nomad4001v"      # ssid of network
)

function Get-ConnectionStatusForNetwork($ssid) {
    $interfaces = netsh wlan show interfaces
    $foundNetwork = $false
    $state = ""
    $retVal = $false
    $interfaces | % {
        if (-not $retVal) {
            $val = $_
            if ($val -match "\s+State\s+\:\s+(.+)") {
                $state = $Matches[1]
            }

            if ($val -match "\s+SSID\s+\:\s+$ssid") {
                $foundNetwork = $true
            }

            if ($foundNetwork) {
                if ($state -eq "connected") {
                    $retVal = $true
                }
            }
        }
    }

    $retVal
}

function Ensure-NetworkConnected($ssid) {
    $isConnected = Get-ConnectionStatusForNetwork -ssid "$ssid"
    if (-not $isConnected) {
        Write-Host "Reconnecting to network - $ssid ..."
        $null = netsh wlan connect name=$ssid

        $isConnected = Get-ConnectionStatusForNetwork -ssid "$ssid"
        if ($isConnected) {
            Write-Host "Connected to network $ssid succesfully!"
        } else {
            Write-Host "ERROR: Could not connect to network - $ssid!"
        }

    } else {
        Write-Host "Already connected to network - $ssid. Taking no action ..."
    }
}

do {
    Ensure-NetworkConnected -ssid $WifiSSID
    sleep $CheckInterval

} while ($true)
