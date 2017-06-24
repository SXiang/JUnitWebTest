# ---------------------------------------------------------------
# PRE-REQUISITES:
#  This script assumes that WinRM PS Remoting is enabled on the destination machine.
#    MSDN: https://msdn.microsoft.com/en-us/library/ee677232(v=azure.10).aspx
# SAMPLE USAGE:
#   .\File-Transfer.ps1 `
#           -BuildWorkingDir "C:\Repositories\surveyor-qa"  `
#           -DestMachineIPAddress "10.100.1.200"  `
#           -DestMachineUsername "Win2k12-Android\picarro" `
#           -DestMachinePassword "<password>" `
#           -SourceFileLocation "C:\Repositories\surveyor-qa\selenium-wd\data\sql\Asset.BA.dat" `
#           -DestFileLocation "C:\temp\Asset.BA.dat"
# ---------------------------------------------------------------

param
(
  [Parameter(Mandatory=$true)]
  [String] $BuildWorkingDir,

  [Parameter(Mandatory=$true)]
  [String] $DestMachineIPAddress,

  [Parameter(Mandatory=$true)]
  [String] $DestMachineUsername,

  [Parameter(Mandatory=$true)]
  [String] $DestMachinePassword,

  [Parameter(Mandatory=$true)]
  [String] $SourceFileLocation,

  [Parameter(Mandatory=$true)]
  [String] $DestFileLocation
)

<#
 REFERENCES:
 This function to send file across machines has been adapted from PowerShell function:
  https://raw.githubusercontent.com/adbertram/Random-PowerShell-Work/master/File-Folder%20Management/Send-File.ps1
#>
function Send-File
{
	<#
	.SYNOPSIS
		This function sends a file (or folder of files recursively) to a destination WinRm session. This function was originally
		built by Lee Holmes (http://poshcode.org/2216) but has been modified to recursively send folders of files as well
		as to support UNC paths.

	.PARAMETER Path
		The local or UNC folder path that you'd like to copy to the session. This also support multiple paths in a comma-delimited format.
		If this is a UNC path, it will be copied locally to accomodate copying.  If it's a folder, it will recursively copy
		all files and folders to the destination.

	.PARAMETER Destination
		The local path on the remote computer where you'd like to copy the folder or file.  If the folder does not exist on the remote
		computer it will be created.

	.PARAMETER Session
		The remote session. Create with New-PSSession.

	.EXAMPLE
		$session = New-PSSession -ComputerName MYSERVER
		Send-File -Path C:\test.txt -Destination C:\ -Session $session

		This example will copy the file C:\test.txt to be C:\test.txt on the computer MYSERVER

	.INPUTS
		None. This function does not accept pipeline input.

	.OUTPUTS
		System.IO.FileInfo
	#>
	[CmdletBinding()]
	param
	(
		[Parameter(Mandatory)]
		[ValidateNotNullOrEmpty()]
		[string[]]$Path,
		
		[Parameter(Mandatory)]
		[ValidateNotNullOrEmpty()]
		[string]$Destination,
		
		[Parameter(Mandatory)]
		[System.Management.Automation.Runspaces.PSSession]$Session
	)
	process
	{
		foreach ($p in $Path)
		{
			try
			{
				if ($p.StartsWith('\\'))
				{
					Write-Verbose -Message "[$($p)] is a UNC path. Copying locally first"
					Copy-Item -Path $p -Destination ([environment]::GetEnvironmentVariable('TEMP', 'Machine'))
					$p = "$([environment]::GetEnvironmentVariable('TEMP', 'Machine'))\$($p | Split-Path -Leaf)"
				}
				if (Test-Path -Path $p -PathType Container)
				{
					Write-Log -Source $MyInvocation.MyCommand -Message "[$($p)] is a folder. Sending all files"
					$files = Get-ChildItem -Path $p -File -Recurse
					$sendFileParamColl = @()
					foreach ($file in $Files)
					{
						$sendParams = @{
							'Session' = $Session
							'Path' = $file.FullName
						}
						if ($file.DirectoryName -ne $p) ## It's a subdirectory
						{
							$subdirpath = $file.DirectoryName.Replace("$p\", '')
							$sendParams.Destination = "$Destination\$subDirPath"
						}
						else
						{
							$sendParams.Destination = $Destination
						}
						$sendFileParamColl += $sendParams
					}
					foreach ($paramBlock in $sendFileParamColl)
					{
						Send-File @paramBlock
					}
				}
				else
				{
					Write-Verbose -Message "Starting WinRM copy of [$($p)] to [$($Destination)]"
					# Get the source file, and then get its contents
					$sourceBytes = [System.IO.File]::ReadAllBytes($p);
					$streamChunks = @();
					
					# Now break it into chunks to stream.
					$streamSize = 1MB;
					for ($position = 0; $position -lt $sourceBytes.Length; $position += $streamSize)
					{
						$remaining = $sourceBytes.Length - $position
						$remaining = [Math]::Min($remaining, $streamSize)
						
						$nextChunk = New-Object byte[] $remaining
						[Array]::Copy($sourcebytes, $position, $nextChunk, 0, $remaining)
						$streamChunks +=, $nextChunk
					}
					$remoteScript = {
						if (-not (Test-Path -Path $using:Destination -PathType Container))
						{
							$null = New-Item -Path $using:Destination -Type Directory -Force
						}
						$fileDest = "$using:Destination\$($using:p | Split-Path -Leaf)"
						## Create a new array to hold the file content
						$destBytes = New-Object byte[] $using:length
						$position = 0
						
						## Go through the input, and fill in the new array of file content
						foreach ($chunk in $input)
						{
							[GC]::Collect()
							[Array]::Copy($chunk, 0, $destBytes, $position, $chunk.Length)
							$position += $chunk.Length
						}
						
						[IO.File]::WriteAllBytes($fileDest, $destBytes)
						
						Get-Item $fileDest
						[GC]::Collect()
					}
					
					# Stream the chunks into the remote script.
					$Length = $sourceBytes.Length
					$streamChunks | Invoke-Command -Session $Session -ScriptBlock $remoteScript
					Write-Verbose -Message "WinRM copy of [$($p)] to [$($Destination)] complete"
				}
			}
			catch
			{
				Write-Error $_.Exception.Message
			}
		}
	}
	
}

function New-GuidNoDashes() {
    [guid]::NewGuid().ToString().Replace("-", "")
}

. "$BuildWorkingDir\selenium-wd\lib\ApplicationInstaller.ps1"
. "$BuildWorkingDir\selenium-wd\lib\HelperScripts\ZipHelpers.ps1"

# Ensure file splitter tool is installed.
Write-Host "Checking if File Splitter is installed ..."
$curlInstalled = IsInstalled -application 'curl'
if ($curlInstalled -eq $false) {
    "File Splitter NOT installed. Installing ..."
    InstallApplication -application 'split'
    
} else {
    "File Splitter is already installed. Skipping install."
}

# 1.
# Compress file and split to files < 50MB
$SPLIT_FILE_CHUNK_SIZE = 4428800
$TEMP_PATH = [System.IO.Path]::GetTempPath()
$newDir = New-GuidNoDashes
$TEMP_DIR  = [System.IO.Path]::Combine($TEMP_PATH, $newDir)
if (-not (Test-Path $TEMP_DIR)) {
    Write-Host "Creating directory -> $TEMP_DIR"
    New-Item -ItemType Directory $TEMP_DIR -Force
}

$SourceFileName = [System.IO.Path]::GetFileName($SourceFileLocation)
$sourceArchiveFileName = [System.IO.Path]::Combine($TEMP_DIR, "${SourceFileName}.zip")
if (Test-Path $sourceArchiveFileName) {
    Write-Host "Cleaning up existing archive file -> $sourceArchiveFileName"
    Remove-Item $sourceArchiveFileName -Force
}

Write-Host "Compressing $SourceFileLocation to $sourceArchiveFileName"
Compress-File -sourceFileName $SourceFileLocation -destinationArchiveFileName $sourceArchiveFileName
Write-Host "Done with file compression."

Write-Host "Splitting file->$sourceArchiveFileName into chunks of size=$SPLIT_FILE_CHUNK_SIZE"
split $sourceArchiveFileName $SPLIT_FILE_CHUNK_SIZE
Write-Host "Done splitting file"

# 2. 
# Remote copy each file file chunk to server machine.
$securePwd = ConvertTo-SecureString “$DestMachinePassword” -AsPlainText -Force
$credential = New-Object System.Management.Automation.PSCredential($DestMachineUsername, $securePwd)
$sOptions = New-PSSessionOption –SkipCACheck –SkipCNCheck –SkipRevocationCheck
Write-Host "Creating remote session to -> $DestMachineIPAddress"
$session = New-PSSession -ConfigurationName microsoft.powershell -ComputerName "$DestMachineIPAddress" -Credential $credential -SessionOption $sOptions -UseSSL
Write-Host "Successfully created remote session to -> $DestMachineIPAddress"

try {
    # Remote PSSession Script -> [Pre-Send Checks]
    $ScriptBlockPreSendFileChecks = { 
        $DestFileLocation = $args[0]

        Write-Host "[Remote CHECK] If remote machine has a file same as folder we are trying to create, delete it. Executing on $DestMachineIPAddress | File -> $DestFileLocation"
        if (Test-Path "$DestFileLocation" -PathType Leaf) {
            Write-Host "[Remote] Removing file -> $DestFileLocation ..."
            Remove-Item -Path $DestFileLocation -Force
        }         

        Write-Host "[Remote CHECK] Ensuring destination folder exists in remote machine. Executing on $DestMachineIPAddress | Folder -> $DestFileLocation"
        if (-not (Test-Path "$DestFileLocation" -PathType Container)) {
            Write-Host "[Remote] Creating directory -> $DestFileLocation ..."
            New-Item -Path $DestFileLocation -ItemType Directory
        }         

        Write-Host "[Remote CHECK] Ensuring destination folder is EMPTY in remote machine. Executing on $DestMachineIPAddress | Folder -> $DestFileLocation"
        if (Test-Path $DestFileLocation) {
            Write-Host "[Remote] Removing files from directory -> $DestFileLocation ..."
            Remove-Item "$DestFileLocation\*" -Recurse -Force 
        }        
    }

    # Pre-Checks before sending file.
    Write-Host "Executing checks on remote machine $DestMachineIPAddress"
    Invoke-Command -Session $session -ScriptBlock $ScriptBlockPreSendFileChecks -ArgumentList $DestFileLocation

    # Send file.
    Get-ChildItem -Path $TEMP_DIR | % { 
        $file = $_
        $filename = $file.Name
        $fileFullPath = $file.FullName
        $fileExt = $file.Extension    
        if ($fileExt -ne ".zip") {
            $srcFileFullPath = $fileFullPath
            Write-Host "Sending file -> Source : [$srcFileFullPath], Destination : [$DestFileLocation]"
            Send-File -Path $srcFileFullPath -Destination $DestFileLocation -Session $session
            Write-Host "Successfully sent file!"
        }
    }

    # Remote PSSession Script -> [Join files script executed on remote server.]
    $ScriptBlockJoinFilesOnServer = { 
        $DestFileLocation = $args[0]
        $joinFileName = [System.IO.Path]::GetFileName($DestFileLocation)
        $zipFileName = "${joinFileName}.zip" 
        $destRootFolder = [System.IO.Path]::GetDirectoryName($DestFileLocation)
        $outFilePath = "$DestFileLocation\$zipFileName"
        if (Test-Path "$outFilePath") {
	        Remove-Item "$outFilePath" -Force
        }

        Write-Host "[Remote] Initializing file writer ..."
        $fWriter = new-object System.IO.FileStream($outFilePath, [System.IO.FileMode]::CreateNew)
        $writer = new-object System.IO.BinaryWriter($fWriter)
        try {
            $fileNamesMap = @{}
            Get-childItem -Path "$DestFileLocation" | %{
	            $file = $_
	            [string]$filename = [string]$file.Name
	            $fileFullPath = $file.FullName	
	            if (-not ($filename.EndsWith(".zip"))) {	
		            $parts = $filename.Split(".")
		            $ext = $parts[$parts.length-1]
		            [int]$key = [int]$ext
		            $fileNamesMap.set_item($key, $fileFullPath)
	            }
            }
            $fileNamesMap.keys | sort-object | %{
	            $key = $_
	            [string]$fileFullPath = [string]$fileNamesMap.get_item($key)
	            Write-Host "[Remote] Reading '$fileFullPath' for joining"
	            $fReader = new-object System.IO.FileStream($fileFullPath, [System.IO.FileMode]::Open, [System.IO.FileAccess]::Read)
	            $reader = new-object System.IO.BinaryReader($fReader)
	            $writer.write($reader.ReadBytes($fReader.Length))
	            $reader.close()
	            $fReader.close()
            }
        } catch {
            $ex = $_.Exception
            Write-Host "[WARNING] Error occured when writing joined file. Exception: $ex"
        }
        $writer.close()
        $fWriter.close()
        Write-Host "[Remote] Done writing joined file."

        Write-Host "[Remote] Copying $outFilePath -> $destRootFolder ..."
        Copy-Item "$outFilePath" "$destRootFolder" -Force

        # 4. 
        # Cleanup files along with directory before unzipping to avoid access denied error.
        Write-Host "[Remote] Cleaning folder and files on remote server | Folder -> $DestFileLocation ..."
        if (Test-Path $DestFileLocation) {
            Remove-Item "$DestFileLocation\*" -Recurse -Force 
        }        
        Remove-Item $DestFileLocation -Force

        # 5. 
        # Decompress files on server.
        #Decompress-ArchiveFile($sourceArchiveFileName, $destinationDirectoryName, $overwriteFiles)
        $sourceArchiveFileName = "$destRootFolder\$zipFileName"
        $destinationDirectoryName = $destRootFolder
        Write-Host "[Remote] Decompressing file - [$sourceArchiveFileName] to [$destinationDirectoryName]."
        Add-Type -AssemblyName "System.IO.Compression.FileSystem"
        $zipArchive = [System.IO.Compression.ZipFile]::OpenRead($sourceArchiveFileName)
        try {
            $zipArchive.Entries | % {
                $entry = $_
                $destFile = [System.IO.Path]::Combine($destinationDirectoryName, $entry.FullName)
                [System.IO.Compression.ZipFileExtensions]::ExtractToFile($entry, $destFile, $true)
            } 
        } catch {
            $ex = $_.Exception
            Write-Host "[WARNING] Error occured when decompressing files in remote session. Exception: $ex"
        }
        $zipArchive.Dispose()
        Write-Host "[Remote] Done decompressing file."

        # 6. 
        # Cleanup files on the server.
        Write-Host "[Remote] Cleaning up file on remote server| File -> $sourceArchiveFileName ..."
        if (Test-Path $sourceArchiveFileName) {
            Remove-Item $sourceArchiveFileName -Force
        }        
    }

    # 3.
    # Join files on the server.
    Write-Host "JOINING files on remote machine $DestMachineIPAddress | Folder -> $DestFileLocation"
    Invoke-Command -Session $session -ScriptBlock $ScriptBlockJoinFilesOnServer -ArgumentList $DestFileLocation

} catch {
    $ex = $_.Exception
    Write-Error "Error occured in remote session. Exception: $ex"
}

# Disconnect PS sessions.
Get-PSSession | Disconnect-PSSession

# 5.
# [Client] Cleanup temporary files.
Write-Host "Cleaning up temporary files on current machine"
Remove-Item "$TEMP_DIR\*" -Recurse -Force 
Write-Host "Done cleanup of temporary files on current machine"

