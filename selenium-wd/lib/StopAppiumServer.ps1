$procs = Get-WmiObject Win32_process -Filter "name='node.exe' and commandline like '%appium%'"
if ($procs -ne $NULL) {
	$procs | % {
		$procid = $_.ProcessId
		TASKKILL /F /pid $procid
	}
}