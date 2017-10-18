param
(
  [Parameter(Mandatory=$true)]
  [String] $inDirectory,              # $inDirectory="C:\temp\Tahoe-CSVs\Original",

  [Parameter(Mandatory=$true)]
  [String] $fileExtFilter,            # $fileExtFilter="*.csv",

  [Parameter(Mandatory=$true)]
  [String] $outDirectory,             # $outDirectory="C:\temp\Tahoe-CSVs\Cleaned",

  [Parameter(Mandatory=$true)]
  [String] $logFilePath               # $logFilePath="C:\temp\Tahoe-CSVs\Logs\logs.txt"
)

. C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\FileReadWriteHelper.ps1

$primaryKeyMap = @{
    "Survey"="Snapped:0"
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

$addNewColumnTbl = ""
$addNewColumnTblList = New-Object System.Collections.ArrayList

$i = 0
$primaryKeyMap.Keys | % {
    $key = $_    
    $addNewColumnTblList.Add($key + "-")     # add '-' at end to prevent prefix overlap.
    if ($i -eq 0) {
        $addNewColumnTbl = $key
    } else {    
        $addNewColumnTbl += "," + $key
    }
    $i++
}

Write-Host "####--------------------------------------------------------------####"
Write-Host "#### Adding NEW rows for tables: $addNewColumnTbl                 ####"
Write-Host "####--------------------------------------------------------------####"

Split-Path -Path "$inDirectory\$fileExtFilter" -Leaf -Resolve | % {
    [string]$fileName = [string]$_
    $fileFullPath = "$inDirectory\$fileName"
    $newFileFullPath = "$outDirectory\$fileName"
    $idx = $fileName.IndexOf('-')
    $fileNamePrefix = $fileName.Substring(0, $idx + 1)

    if ($addNewColumnTblList.Contains("$fileNamePrefix")) {

        Write-Host "[ADD_NEW_COLUMN] -> Processing file - $fileFullPath ..."
        $fileLinesList = New-Object System.Collections.ArrayList

        $csvData = Import-Csv "$fileFullPath"

        $pkColumnNamesList = New-Object System.Collections.ArrayList
        $primaryKeyColumns = $primaryKeyMap.get_item($fileNamePrefix.Replace("-", ""))
        $newColumnsForCurrTable = $primaryKeyColumns -split ","
        $newColumnsForCurrTable | % {
            $colParts = $_ -split ":"
            $columnName = $colParts[0]
            $null = $pkColumnNamesList.Add($columnName)
        }

        # get headers in the same order and Write header line to fileLinesList.
        $headers = $csvData[0].psObject.Properties | foreach { $_.Name }

        $originalLen = $headers.Length

        # add the new columns to headers.
        $pkColumnNamesList | %{
            $headers += $_
        }

        $len = $headers.Length
            
        # First write the original Header line.
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

        # Next write the newly added Columns to header line.

        # Add line to ArrayList
        $null = $fileLinesList.Add($lineText)

        # Next write the line text and include values for new column.
        $i = 0
        $lineText = ""
        $csvData | % {
            $line = $_

            # Include existing columns.
            $k = 0
            $headers | % { 
                if ($k -lt $originalLen) {                
                    $headerName = $_
                    $lineText += $line."$headerName"
                    if ($k -ne $originalLen - 1) {
                        $lineText += ","
                    }
                }

                $k++
            }            

            # Add default values for new column.
            $newColumnsForCurrTable | %{
                $colParts = $_ -split ":"
                $columnValue = $colParts[1]
                $lineText += "," + $columnValue
            }

            # Add line to ArrayList
            $null = $fileLinesList.Add("$lineText")

            if ($i % 250 -eq 0) {
                Write-Host "[ADD_COLUMN] -> File='$fileName', Processing LINE-$i"
            }

            $i++
        }

        Write-Host "[ADD_NEW_COLUMN] -> Start writing replaced content to FILE for - '$newFileFullPath'"
        Write-FileLines -filePath $newFileFullPath -fileContentLines $fileLinesList
        Write-Host "[ADD_NEW_COLUMN] -> DONE writing replaced content to FILE for - '$newFileFullPath'"
    } 
}

if ($logLinesList.Count -gt 0) {
    Write-FileLines -filePath $logFilePath -fileContentLines $logLinesList
    Write-Host "####--------------------------------------------------------------####"
    Write-Host "#### WARNINGS ENCOUNTERED. Refer: $logFilePath                    ####"
    Write-Host "####--------------------------------------------------------------####"    
}

Write-Host "####--------------------------------------------------------------####"
Write-Host "#### DONE ADDING NEW COLUMN DATA. Replaced files in : $outDirectory ####"
Write-Host "####--------------------------------------------------------------####"