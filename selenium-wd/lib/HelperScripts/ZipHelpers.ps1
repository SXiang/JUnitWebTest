function Compress-Directory($sourceDirectoryName, $destinationArchiveFileName) {
    Add-Type -AssemblyName "System.IO.Compression.FileSystem"
    [System.IO.Compression.ZipFile]::CreateFromDirectory($sourceDirectoryName, $destinationArchiveFileName, 
        [System.IO.Compression.CompressionLevel]::Optimal, $false <#includeBaseDirectory#>)
}

function Decompress-ArchiveFile($sourceArchiveFileName, $destinationDirectoryName, $overwriteFiles) {
    Add-Type -AssemblyName "System.IO.Compression.FileSystem"
    if (-not $overwriteFiles) {
        # if overwrite is NOT specified simply ExtractToFolder
        [System.IO.Compression.ZipFile]::ExtractToDirectory($sourceArchiveFileName, $destinationDirectoryName)
    } else {
        # if overwrite is specified, read each file from archive and overwrite file.
        $zipArchive = [System.IO.Compression.ZipFile]::OpenRead($sourceArchiveFileName)
        $zipArchive.Entries | % {
            $entry = $_
            $destFile = [System.IO.Path]::Combine($destinationDirectoryName, $entry.FullName)
            [System.IO.Compression.ZipFileExtensions]::ExtractToFile($entry, $destFile, $true)
        }
    }
}