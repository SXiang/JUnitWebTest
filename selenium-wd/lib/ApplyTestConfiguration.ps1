# 
# Sample Usage: 
#   ./ApplyTestConfiguration.ps1 -simulatorPrereqCmdRelativePath "lib\SetupSimulatorPreReqs.cmd" -invokeReplayScriptCmdRelativePath "lib\InvokeReplayScript.cmd" -defnFileRelativePath "data\defn\replay-db3.defn" -ciWorkingDir "C:\Repositories\surveyor-qa" 

param
(
  [Parameter(Mandatory=$true)]
  [String] $simulatorPrereqCmdRelativePath,

  [Parameter(Mandatory=$true)]
  [String] $invokeReplayScriptCmdRelativePath,

  [Parameter(Mandatory=$true)]
  [String] $defnFileRelativePath,
  
  [Parameter(Mandatory=$true)]
  [String] $ciWorkingDir
)

$uniqueId = [guid]::NewGuid()
$uniqueId = $uniqueId.toString()
$uniqueId = $uniqueId.replace("-", "")

#get currently executing script command for executing script path and filename
$executingCommand = (get-Variable MyInvocation -Scope 0).Value.MyCommand
$executingScriptName = $executingCommand.Name
$simulatorPrereqCmdAbsPath = $executingCommand.Path.replace("lib\$executingScriptName", $simulatorPrereqCmdRelativePath)
$simulatorPrereqCmdCopyPath = $simulatorPrereqCmdAbsPath.replace(".cmd", ".cmd.copy")

$invokeReplayScriptCmdAbsPath = $executingCommand.Path.replace("lib\$executingScriptName", $invokeReplayScriptCmdRelativePath)
$invokeReplayScriptCmdCopyPath = $invokeReplayScriptCmdAbsPath.replace(".cmd", ".cmd.copy")

$defnFileAbsPath = $executingCommand.Path.replace("lib\$executingScriptName", $defnFileRelativePath)
$defnFileCopyPath = $uniqueId + "_" + $defnFileAbsPath

#create a copy of the original cmd files (if one does not already exist)
if ((Test-Path $simulatorPrereqCmdCopyPath) -eq $false) {
    copy-item $simulatorPrereqCmdAbsPath $simulatorPrereqCmdCopyPath
}
if ((Test-Path $simulatorPrereqCmdCopyPath) -eq $false) {
    copy-item $invokeReplayScriptCmdAbsPath $invokeReplayScriptCmdCopyPath
}
if ((Test-Path $defnFileCopyPath) -eq $false) {
    copy-item $defnFileAbsPath $defnFileCopyPath
}

$placeholdersTable = @{}
$placeholdersTable.add("%CI_WORKING_DIR%", $ciWorkingDir)
$placeholdersTable.add("%UNIQUE_IDENTIFIER%", $uniqueId)

function replacePlaceholders($simulatorPrereqCmdAbsPath) {
	#store each line of the file after making the replacement.
	$fileLines = New-Object System.Collections.ArrayList
	Get-Content $simulatorPrereqCmdAbsPath | % { 
		$line = $_
		$line = $line.trim()
		$placeholdersTable.Keys | % {
			if ($_ -ne "") {
				$line = $line.replace($_, $placeholdersTable.Get_Item($_)
			}
		}
		$fileLines.Add($line)
	}    

	#write replaced line text into file
	$stream = [System.IO.StreamWriter] $simulatorPrereqCmdAbsPath
	$fileLines | % {
		$stream.WriteLine($_)
	}
	$stream.close()
}

# replace placeholders in simulator pre-req command
replacePlaceholders -absoluteFilePath $simulatorPrereqCmdAbsPath

# replace placeholders in invoke replay script command
replacePlaceholders -absoluteFilePath $invokeReplayScriptCmdAbsPath
