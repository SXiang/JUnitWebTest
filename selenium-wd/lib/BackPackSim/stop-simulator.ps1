#---------------------------------------------------------------------------------------------------
# DESCRIPTION:
# Matches 3 python processes and the multiprocessing.forking process and terminates them:
#  1. CommandLine                : "c:\python27\python.exe"  simDataManagerBroadcaster.py
#  2. CommandLine                : "c:\python27\python.exe"  simLinearFitterBroadcaster.py
#  3. CommandLine                : "c:\python27\python.exe"  odorcallServer.py
#  4. multiprocessing.forking
#---------------------------------------------------------------------------------------------------

$simDMHandle = -1
$simLFHandle = -1
$ocServerHandle = -1
$mpProcessHandle = -1
$dumInstrMgrHandle = -1
$dumLnFitAlarmHandle = -1

$procs = Get-WmiObject Win32_Process | Where-Object { $_.name -match "python.exe" }
$procs | %{
    $proc = $_
    [string]$cmdLine = [string]$proc.CommandLine
    if ($cmdLine.Trim().ToLowerInvariant().EndsWith("simdatamanagerbroadcaster.py")) {
        $simDMHandle = $proc.Handle        
    }
    if ($cmdLine.Trim().ToLowerInvariant().EndsWith("simlinearfitterbroadcaster.py")) {
        $simLFHandle = $proc.Handle        
    }
    if ($cmdLine.Trim().ToLowerInvariant().EndsWith("odorcallserver.py")) {
        $ocServerHandle = $proc.Handle        
    }
    if ($cmdLine.Trim().ToLowerInvariant().Contains("multiprocessing.forking")) {
        $mpProcessHandle = $proc.Handle        
    }
    if ($cmdLine.Trim().ToLowerInvariant().EndsWith("dummyinstrmgr.py")) {
        $dumInstrMgrHandle = $proc.Handle        
    }
    if ($cmdLine.Trim().ToLowerInvariant().EndsWith("dummylinearfitteralarm.py")) {
        $dumLnFitAlarmHandle = $proc.Handle        
    }
}

if ($simDMHandle -ne -1) {
    taskkill /F /pid $simDMHandle
}

if ($simLFHandle -ne -1) {
    taskkill /F /pid $simLFHandle
}

if ($ocServerHandle -ne -1) {
    taskkill /F /pid $ocServerHandle
}

if ($mpProcessHandle -ne -1) {
    taskkill /F /pid $mpProcessHandle
}

if ($dumInstrMgrHandle -ne -1) {
    taskkill /F /pid $dumInstrMgrHandle
}

if ($dumLnFitAlarmHandle -ne -1) {
    taskkill /F /pid $dumLnFitAlarmHandle
}