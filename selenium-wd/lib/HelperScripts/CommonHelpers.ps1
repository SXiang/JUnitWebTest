<#--------------------------------------------------------------------------------------------------------------------------------

 DESCRIPTION: 
  - This script contains generic helper functions.

----------------------------------------------------------------------------------------------------------------------------------#>

<#--------------------------------------------------------------------------------------------------------------------------------
- Generic helper methods.
----------------------------------------------------------------------------------------------------------------------------------#>

function Get-LastLineFromCSV($csvFilePath, $colHeaderForSort, $colHeaderForStats) {
    $csvLines = Import-Csv $csvFilePath | Sort-Object $colHeaderForSort
    $stats = $csvLines | Measure-Object "$colHeaderForStats" -Average -Minimum -Maximum
    $linesCount = $stats.Count
    $csvLines[$linesCount-1]
}

function Convert-StringArrayToNumericArray($strArray) {
    $intArray = @()
    if ($strArray) {
        $strArray | % {
            $intArray += @([int]$_)
        }
    }
    $intArray
}

<#--------------------------------------------------------------------------------------------------------------------------------
- Statistic computation helper methods.
----------------------------------------------------------------------------------------------------------------------------------#>

function Get-Average($csvFilePath, $colHeaderForAverage) {
    $csvLines = Import-Csv $csvFilePath
    $stats = $csvLines | Measure-Object "$colHeaderForAverage" -Average -Minimum -Maximum
    $stats.Average
}

function Get-NthPercentile($n, $csvFilePath, $colHeaderForPercentile) {
    $csvLines = Import-Csv $csvFilePath
    $stats = $csvLines | Measure-Object "$colHeaderForPercentile" -Average -Minimum -Maximum
    $linesCount = $stats.Count
    $csvColLines = $csvLines."$colHeaderForPercentile"
    if ($csvColLines.GetType().Name -eq "String") {
        [int]$csvColLines
    } else {
        $sortedLines = Convert-StringArrayToNumericArray -strArray $csvColLines | Sort-Object
        $upperBound = [int]($linesCount * ($n/100))
        [int]$sortedLines[$upperBound-1]
    }
}