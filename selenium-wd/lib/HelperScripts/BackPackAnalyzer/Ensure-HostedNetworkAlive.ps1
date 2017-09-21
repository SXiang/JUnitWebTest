# ---------------------------------------------------------------
# SCRIPT: This is helper script to check status of hostednetwork and reset if stopped.
#         
# USAGE:
#   .\Ensure-HostedNetworkAlive.ps1 -CheckInterval 2
# ---------------------------------------------------------------

param (
    [Int32] $CheckInterval = 2   # seconds to pause before performing next check.  
)

function Get-HostedNetworkStatus() {
    $hostednetwork = netsh wlan show hostednetwork
    $foundHostedNetwork = $false
    $retVal = $false
    $hostednetwork | % {
        if (-not $retVal) {
            $val = $_
            if ($val.Contains("Hosted network status")) {
                $foundHostedNetwork = $true
            }

            if ($foundHostedNetwork) {
                if ($val -match "\s+(Status)\s+\:\s+(.+)") {
                     $status = $Matches[2]
                     if ($status -eq "Started") {
                        $retVal = $true
                     }
                }
            }
        }
    }

    $retVal
}

function Ensure-HostedNetworkAlive() {
    $isAlive = Get-HostedNetworkStatus
    if (-not $isAlive) {
        Write-Host "Resetting HostedNetwork ..."
        Write-Host "Stopping HostedNetwork ..."
        $null = netsh wlan stop hostednetwork
        Write-Host "Starting HostedNetwork ..."
        $null = netsh wlan start hostednetwork

        $isAlive = Get-HostedNetworkStatus
        if ($isAlive) {
            Write-Host "HostedNetwork started successfully!"
        } else {
            Write-Host "ERROR: HostedNetwork could NOT be started!"
        }

    } else {
        Write-Host "HostedNetwork is already Started. Taking no action ..."
    }
}

do {
    Ensure-HostedNetworkAlive
    sleep $CheckInterval

} while ($true)