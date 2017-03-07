param
(
  [Parameter(Mandatory=$true)]
  [String] $inDirectory,                    # eg. $inDirectory="C:\temp\IntegratedPythonPipeline\OriginalCSVs",

  [Parameter(Mandatory=$true)]
  [String] $fileExtFilter,                  # eg. $fileExtFilter="*.csv",

  [Parameter(Mandatory=$true)]
  [String] $outDirectory                    # eg. $outDirectory="C:\temp\IntegratedPythonPipeline\ReplacedCSVs"
)

. C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\FileReadWriteHelper.ps1

$columnsToDrop= @{
    "Survey" = "LeakRate"
    "Peak"="SurveyModeTypeId"
    "FieldOfView"="SurveyModeTypeId"
}

# Create out directory if it does NOT exist.
if (-not (Test-Path -Path $outDirectory)) {
    Write-host "Directory does NOT exist - $outDirectory . Creating NEW directory ..."
    New-Item -ItemType Directory -Path $outDirectory
} else {
    #Path already exists. Remove existing files.
    Remove-Item "$outDirectory\*" -recurse
}

$tablesToDropColumn = ""
$tablesToDropColumnList = New-Object System.Collections.ArrayList

$i = 0
$columnsToDrop.Keys | % {
    $key = $_    
    $tablesToDropColumnList.Add($key + "-")     # add '-' at end to prevent prefix overlap.
    if ($i -eq 0) {
        $tablesToDropColumn = $key
    } else {    
        $tablesToDropColumn += "," + $key
    }
    $i++
}

Write-Host "####--------------------------------------------------------------####"
Write-Host "#### Removing column data for tables: $tablesToDropColumn         ####"
Write-Host "####--------------------------------------------------------------####"

Split-Path -Path "$inDirectory\$fileExtFilter" -Leaf -Resolve | % {
    [string]$fileName = [string]$_
    $fileFullPath = "$inDirectory\$fileName"
    $newFileFullPath = "$outDirectory\$fileName"
    $idx = $fileName.IndexOf('-')
    $fileNamePrefix = $fileName.Substring(0, $idx + 1)

    if ($tablesToDropColumnList.Contains("$fileNamePrefix")) {

        Write-Host "[DROP_COLUMN] -> Processing file - $fileFullPath ..."
        $fileLinesList = New-Object System.Collections.ArrayList

        $csvData = Import-Csv "$fileFullPath"

        $dropColumnName = $columnsToDrop.get_item($fileNamePrefix.Replace("-", ""))

        # get headers in the same order and Write header line to fileLinesList.
        $headers = $csvData[0].psObject.Properties | foreach { $_.Name }
        $len = $headers.Length   
            
        # First write the Header line excluding the column to drop.
        $k = 0                
        $lineText = ""             
        $headers | % { 
            $headerName = $_
            if ($headerName -ne $dropColumnName) {
                $lineText += $headerName
                if ($k -ne $len - 1) {
                    $lineText += ","
                }
            }
            $k++
        }

        # Add line to ArrayList
        $null = $fileLinesList.Add($lineText)

        # Next write the line text excluding the column to drop.
        $i = 0
        $csvData | % {
            $line = $_

            # Build line text from csvdata.
            $lineText = ""
            $k = 0
            $headers | % { 
                $headerName = $_
                if ($headerName -ne $dropColumnName) {
                    $lineText += $line."$headerName"
                    if ($k -ne $len - 1) {
                        $lineText += ","
                    }
                }
                $k++ 
            }            

            # Add line to ArrayList
            $null = $fileLinesList.Add("$lineText")

            if ($i % 250 -eq 0) {
                Write-Host "[DROP_COLUMN] -> File='$fileName', Processing LINE-$i"
            }

            $i++
        }

        Write-Host "[DROP_COLUMN] -> Start writing replaced content to FILE for - '$newFileFullPath'"
        Write-FileLines -filePath $newFileFullPath -fileContentLines $fileLinesList
        Write-Host "[DROP_COLUMN] -> DONE writing replaced content to FILE for - '$newFileFullPath'"
    } 
}

Write-Host "####--------------------------------------------------------------####"
Write-Host "#### DONE DROPPING COLUMN DATA. Replaced files located in : $outDirectory ####"
Write-Host "####--------------------------------------------------------------####"