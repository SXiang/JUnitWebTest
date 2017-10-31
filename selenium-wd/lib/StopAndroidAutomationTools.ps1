# stop processes
Write-Host "stopping node process..."
$null = taskkill /F /IM node.exe 2>&1
Write-Host "stopping emulator exe..."
$null = taskkill /F /IM emulator64-crash-service.exe 2>&1
$null = taskkill /F /IM emulator.exe 2>&1
$null = taskkill /F /IM emulator-crash-service.exe 2>&1
$null = taskkill /F /IM emulator-x86.exe 2>&1
Write-Host "stopping emulator interface..."
$null = taskkill /F /IM qemu-system-i386.exe 2>&1
