# 
# Sample Usage: 
#   ./ApplyTestConfiguration.ps1 -simulatorPrereqCmdRelativePath "lib\SetupSimulatorPreReqs.cmd" -invokeReplayScriptCmdRelativePath "lib\InvokeReplayScript.cmd" -defnFileRelativePath "data\defn\replay-db3.defn" -ciWorkingDir "C:\Repositories\surveyor-qa" -db3FileRelativePath "data\DB3\Surveyor.db3" -curlBatFileRelativePath "data\defn\replay-defn-curl-ci.bat"

param
(
  [Parameter(Mandatory=$true)]
  [String] $simulatorPrereqCmdRelativePath,

  [Parameter(Mandatory=$true)]
  [String] $invokeReplayScriptCmdRelativePath,

  [Parameter(Mandatory=$true)]
  [String] $defnFileRelativePath,

  [Parameter(Mandatory=$true)]
  [String] $ciWorkingDir,

  [String] $db3FileRelativePath,
  [String] $curlBatFileRelativePath

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
$defnFileCopyPath = $defnFileAbsPath.replace(".defn", "_" + $uniqueId + ".defn")
$defnFileName = split-path $defnFileCopyPath -leaf -resolve

$db3FileAbsPath = $executingCommand.Path.replace("lib\$executingScriptName", $db3FileRelativePath)
$curlBatFileAbsPath = $executingCommand.Path.replace("lib\$executingScriptName", $curlBatFileRelativePath)

#make sure that the files are always the original files and not the placeholder replaced files.
if ((Test-Path $simulatorPrereqCmdCopyPath) -eq $true) {
	copy-item $simulatorPrereqCmdCopyPath $simulatorPrereqCmdAbsPath -force
    remove-item $simulatorPrereqCmdCopyPath
}
copy-item $simulatorPrereqCmdAbsPath $simulatorPrereqCmdCopyPath

if ((Test-Path $invokeReplayScriptCmdCopyPath) -eq $true) {
	copy-item $invokeReplayScriptCmdCopyPath $invokeReplayScriptCmdAbsPath -force
    remove-item $invokeReplayScriptCmdCopyPath
}
copy-item $invokeReplayScriptCmdAbsPath $invokeReplayScriptCmdCopyPath

if ((Test-Path $defnFileCopyPath) -eq $true) {
	copy-item $defnFileCopyPath $defnFileAbsPath -force 
    remove-item $defnFileCopyPath
}
copy-item $defnFileAbsPath $defnFileCopyPath

$placeholdersTable = @{}
$placeholdersTable.add("%CI_WORKING_DIR%", $ciWorkingDir)
$placeholdersTable.add("%UNIQUE_IDENTIFIER%", $uniqueId)
$placeholdersTable.add("%DB3_FILE_PATH%", $db3FileAbsPath)
$placeholdersTable.add("%REPLAY_DEFN_FILE%", $$defnFileName)

function replacePlaceholders($absoluteFilePath) {
	#store each line of the file after making the replacement.
	$fileLines = New-Object System.Collections.ArrayList
	Get-Content $absoluteFilePath | % { 
		$line = $_
		$line = $line.trim()
		$placeholdersTable.Keys | % {
			if ($_ -ne "") {
				$line = $line.replace($_, $placeholdersTable.Get_Item($_))
			}
		}
		$fileLines.Add($line)
	}    

	#write replaced line text into file
	$stream = [System.IO.StreamWriter] $absoluteFilePath
	$fileLines | % {
		$stream.WriteLine($_)
	}
	$stream.close()
}

# replace placeholders in simulator pre-req command
replacePlaceholders -absoluteFilePath $simulatorPrereqCmdAbsPath

# replace placeholders in invoke replay script command
replacePlaceholders -absoluteFilePath $invokeReplayScriptCmdAbsPath

# replace placeholders in defn copy file
replacePlaceholders -absoluteFilePath $defnFileCopyPath

# replace placeholders in curl bat file
replacePlaceholders -absoluteFilePath $curlBatFileAbsPath
