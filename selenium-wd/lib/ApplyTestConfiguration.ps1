# 
# Sample Usage: 
#   ./ApplyTestConfiguration.ps1 -simulatorPrereqCmdRelativePath "lib\SetupSimulatorPreReqs.cmd" -ciWorkingDir "C:\Repositories\surveyor-qa" 

param
(
  [Parameter(Mandatory=$true)]
  [String] $simulatorPrereqCmdRelativePath,

  [Parameter(Mandatory=$true)]
  [String] $ciWorkingDir
)

#get currently executing script command for executing script path and filename
$executingCommand = (get-Variable MyInvocation -Scope 0).Value.MyCommand
$executingScriptName = $executingCommand.Name
$simulatorPrereqCmdAbsPath = $executingCommand.Path.replace("lib\$executingScriptName", $simulatorPrereqCmdRelativePath)
$simulatorPrereqCmdCopyPath = $simulatorPrereqCmdAbsPath.replace(".cmd", ".cmd.copy")

#create a copy of the original cmd file (if one does not already exist)
if ((Test-Path $simulatorPrereqCmdAbsPath) -eq $false) {
    copy-item $simulatorPrereqCmdAbsPath $simulatorPrereqCmdCopyPath
}

#store each line of the file after making the replacement.
$fileLines = New-Object System.Collections.ArrayList
Get-Content $simulatorPrereqCmdAbsPath | % { 
    $line = $_
    $line = $line.trim()
    $line = $line.replace("%CI_WORKING_DIR%", $ciWorkingDir)
    $fileLines.Add($line)
}    

#write replaced line text into file
$stream = [System.IO.StreamWriter] $simulatorPrereqCmdAbsPath
$fileLines | % {
    $stream.WriteLine($_)
}
$stream.close()



