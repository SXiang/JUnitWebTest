[UInt32] $MaxUserPort = 65500
[UInt32] $TcpTimedWaitDelay = 30

$itemProps = Get-ItemProperty -Path Registry::HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\Tcpip\Parameters

if ($itemProps.MaxUserPort -eq $NULL) {
    Write-Host "Did not find MaxUserPort property 'HKLM\..\Tcpip\Parameters'. Creating new ..."
    New-ItemProperty -Path Registry::HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\Tcpip\Parameters -Name MaxUserPort -PropertyType DWord -Value $MaxUserPort
} else {
    Write-Host "MaxUserPort property exists in 'HKLM\..\Tcpip\Parameters'"
}

if ($itemProps.TcpTimedWaitDelay -eq $NULL) {
    Write-Host "Did not find TcpTimedWaitDelay property in reg key 'HKLM\..\Tcpip\Parameters'. Creating new ..."
    New-ItemProperty -Path Registry::HKEY_LOCAL_MACHINE\SYSTEM\CurrentControlSet\Services\Tcpip\Parameters -Name TcpTimedWaitDelay -PropertyType DWord -Value $TcpTimedWaitDelay
} else {
    Write-Host "TcpTimedWaitDelay property exists in 'HKLM\..\Tcpip\Parameters'"
}
