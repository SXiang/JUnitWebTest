param
(
  [Parameter(Mandatory=$true)]
  [String] $inDirectory,               # $inDirectory="C:\temp\IntegratedPythonPipeline\DupCSVs",

  [Parameter(Mandatory=$true)]
  [String] $fileExtFilter,             # $fileExtFilter="*.csv",

  [Parameter(Mandatory=$true)]
  [String] $outDirectory,              # $outDirectory="C:\temp\IntegratedPythonPipeline\DupsRemovedCSVs",

  [Parameter(Mandatory=$true)]
  [String] $logFilePath                # $logFilePath="C:\temp\IntegratedPythonPipeline\Logs\logs.txt"
)

. C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\FileReadWriteHelper.ps1

$primaryKeyMap = @{
    "Peak"="AnalyzerId,EpochTime"
    "FieldOfView"="AnalyzerId,EpochTime"
}

$logLinesList = New-Object System.Collections.ArrayList

# Create out directory if it does NOT exist.
if (-not (Test-Path -Path $outDirectory)) {
    Write-host "Directory does NOT exist - $outDirectory . Creating NEW directory ..."
    New-Item -ItemType Directory -Path $outDirectory
} else {
    #Path already exists. Remove existing files.
    Remove-Item "$outDirectory\*" -recurse
}

$removeDupColumnTbl = ""
$removeDupColumnTblList = New-Object System.Collections.ArrayList

$i = 0
$primaryKeyMap.Keys | % {
    $key = $_    
    $removeDupColumnTblList.Add($key + "-")     # add '-' at end to prevent prefix overlap.
    if ($i -eq 0) {
        $removeDupColumnTbl = $key
    } else {    
        $removeDupColumnTbl += "," + $key
    }
    $i++
}

Write-Host "####--------------------------------------------------------------####"
Write-Host "#### Removing DUPLICATE rows for tables: $removeDupColumnTbl      ####"
Write-Host "####--------------------------------------------------------------####"

Split-Path -Path "$inDirectory\$fileExtFilter" -Leaf -Resolve | % {
    [string]$fileName = [string]$_
    $fileFullPath = "$inDirectory\$fileName"
    $newFileFullPath = "$outDirectory\$fileName"
    $idx = $fileName.IndexOf('-')
    $fileNamePrefix = $fileName.Substring(0, $idx + 1)

    if ($removeDupColumnTblList.Contains("$fileNamePrefix")) {

        Write-Host "[REMOVE_DUPLICATE_ROWS] -> Processing file - $fileFullPath ..."
        $fileLinesList = New-Object System.Collections.ArrayList

        $csvData = Import-Csv "$fileFullPath"

        $pkColumnNamesList = New-Object System.Collections.ArrayList
        $primaryKeyColumns = $primaryKeyMap.get_item($fileNamePrefix.Replace("-", ""))
        $arrValues = $primaryKeyColumns -split ","
        $arrValues | %{
            $null = $pkColumnNamesList.Add($_)
        }

        # get headers in the same order and Write header line to fileLinesList.
        $headers = $csvData[0].psObject.Properties | foreach { $_.Name }
        $len = $headers.Length   
            
        # First write the Header line.
        $k = 0                
        $lineText = ""             
        $headers | % { 
            $headerName = $_
            $lineText += $headerName
            if ($k -ne $len - 1) {
                $lineText += ","
            }
            $k++
        }

        # Add line to ArrayList
        $null = $fileLinesList.Add($lineText)

        # Detect duplicate rows and try resolution. 
        # When duplicate rows are EXACTLY same keep first row.
        # When duplicate rows are NOT EXACTLY same warn and write to WARNING file.
        $i = 0
        [String]$prevPKItem = ""
        [String]$prevLineItem = ""
        [String]$currPKItem = ""
        [String]$currLineItem = ""
        $csvData | % {
            $line = $_

            $prevPKItem = $currPKItem
            $prevLineItem = $currLineItem

            # Build line text from csvdata.
            $lineText = ""
            $k = 0
            $currPKItem = ""
            $headers | % { 
                $headerName = $_
                if ($pkColumnNamesList.Contains($headerName)) {
                    $currPKItem += $line."$headerName"
                }

                $lineText += $line."$headerName"
                if ($k -ne $len - 1) {
                    $lineText += ","
                }
                $k++ 
            }

            $currLineItem = $lineText

            $foundDupRow = $false
            $canResolveDup = $true
            if ($currPKItem.Equals($prevPKItem)) {
                $foundDupRow = $true
                if (!$currLineItem.Equals($prevLineItem)) {
                    $canResolveDup = $false
                }
            }

            if ($foundDupRow) {
                # skip adding to row list.
                if ($canResolveDup -eq $false) {
                    # write to warning log.
                    $null = $logLinesList.Add("Found an ENTRY that cannot be resolved. File = $fileName ")
                    $null = $logLinesList.Add("    Row-1=[$prevLineItem]")
                    $null = $logLinesList.Add("    Row-2=[$currLineItem]")
                }
            } else {
                # Add line to ArrayList
                $null = $fileLinesList.Add("$lineText")
            }

            if ($i % 250 -eq 0) {
                Write-Host "[REMOVE_DUPLICATE_ROWS] -> File='$fileName', Processing LINE-$i"
            }

            $i++
        }

        Write-Host "[REMOVE_DUPLICATE_ROWS] -> Start writing replaced content to FILE for - '$newFileFullPath'"
        Write-FileLines -filePath $newFileFullPath -fileContentLines $fileLinesList
        Write-Host "[REMOVE_DUPLICATE_ROWS] -> DONE writing replaced content to FILE for - '$newFileFullPath'"
    } 
}

if ($logLinesList.Count -gt 0) {
    Write-FileLines -filePath $logFilePath -fileContentLines $logLinesList
    Write-Host "####--------------------------------------------------------------####"
    Write-Host "#### WARNINGS ENCOUNTERED. Refer: $logFilePath                    ####"
    Write-Host "####--------------------------------------------------------------####"    
}

Write-Host "####--------------------------------------------------------------####"
Write-Host "#### DONE REMOVING DUPLICATE DATA. Replaced files in : $outDirectory ####"
Write-Host "####--------------------------------------------------------------####"