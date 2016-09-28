<#--------------------------------------------------------------------------------------------------------------------------------

 DESCRIPTION: 
  - This script contains helper functions to read and write from text files in an efficient manner.

----------------------------------------------------------------------------------------------------------------------------------#>

function Read-File($filePath) {
    $data = ""
    $streamReader = $null
    try {
        $streamReader = [System.IO.StreamReader] $filePath
        $data = $streamReader.ReadToEnd()
    } finally {
        if ($streamReader -ne $null) {
            $streamReader.Close()
            $streamReader.Dispose()
        }
    }
    $data
}

function Read-FileLines($filePath) {
    $fileLines = New-Object System.Collections.ArrayList
    $streamReader = $null
    try {
        $streamReader = [System.IO.StreamReader] $filePath
        while ($streamReader.Peek() -ge 0) {
            $null = $fileLines.Add($streamReader.ReadLine())
        }
    } finally {
        if ($streamReader -ne $null) {
            $streamReader.Close()
            $streamReader.Dispose()
        }
    }
    $fileLines
}


function Write-File($filePath, $fileContent) {
    $streamWriter = $null
    try {
        $streamWriter = [System.IO.StreamWriter] $filePath
        $streamWriter.Write($fileContent)
    } finally {
        if ($streamWriter -ne $null) {
            $streamWriter.Close()
            $streamWriter.Dispose()
        }
    }
}

function Write-FileLines($filePath, [System.Collections.ArrayList]$fileContentLines) {
    $streamWriter = $null
    try {
        $streamWriter = [System.IO.StreamWriter] $filePath
        $fileContentLines | % {
            $line = $_
            $streamWriter.WriteLine($line)
        }
    } finally {
        if ($streamWriter -ne $null) {
            $streamWriter.Close()
            $streamWriter.Dispose()
        }
    }
}