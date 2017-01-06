param(
    [int] $pastXDays = 100,
    [float] $percentileValue = 95,
    [float] $changeFactor = 1.2,
    [switch] $isRunningOnServer = $false
)

. "C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\DatabaseHelpers.ps1"
. "C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\CommonHelpers.ps1"
. "C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\DateTimeHelpers.ps1"

$SEPERATOR = "|"

$LOCAL_MACHINE_CONN_STRING = "Server=PULIKKAL-850G2E\SQLEXPRESS;Database=AutomationReporting;Trusted_Connection=True;"
$BUILD_AGENT_CONN_STRING = "Server=WIN-JEPVISGJUTG\SQLSERVEREXP;Database=AutomationReporting;integrated security=True;"

$lastXDays = (-1) * $pastXDays

# Hash table.
$script:rptJobStatsTable = @{}

if ($isRunningOnServer) {
    # LOCAL machine connection string
    $connString = $BUILD_AGENT_CONN_STRING
} else {
    #Reporting machine connection string
    $connString = $LOCAL_MACHINE_CONN_STRING
}

# HashTable with ArrayList value
function AddTo-StatsMapTable($mapTable, $tcIDJobType, $value) 
{
    if (-not $mapTable.ContainsKey($tcIDJobType)) {
        $tcStatsList = New-Object System.Collections.ArrayList
        $null = $tcStatsList.Add($value)
        $null = $mapTable.Add($tcIDJobType, $tcStatsList)
    } else {
        $tcStatsList = [System.Collections.ArrayList]$mapTable.Get_Item($tcIDJobType)
        if (-not $tcStatsList.Contains($value)) {
            $null = $tcStatsList.Add($value)
        }
        $mapTable.Set_Item($tcIDJobType, $tcStatsList)
    }        
}

$query = "SELECT [ReportTitle],[ReportJobTypeId],[ReportJobTypeName],[ReportJobStartTime],[ReportJobEndTime],DATEDIFF(MILLISECOND,[ReportJobStartTime],[ReportJobEndTime]) AS ProcessingTimeInMSec,[TestExecutionStartDate],[TestExecutionEndDate],[TestCaseID] FROM [dbo].[PerfStatReportJob] WHERE [TestExecutionStartDate] > DATEADD(d, $lastXDays, getdate())"
$objReportStats = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
$i = 0

# FLATTEN all values to LIST.
$objReportStats | foreach { 
    if ($i -gt 0) {     # first row is length of array. datarows are from index=1
        $obj = $_; 
        $ReportTitle = $obj.ReportTitle
        $ReportJobTypeId = $obj.ReportJobTypeId
        $ReportJobTypeName = $obj.ReportJobTypeName
        $ReportJobStartTime = $obj.ReportJobStartTime
        $ReportJobEndTime = $obj.ReportJobEndTime
        $ProcessingTimeInMSec = $obj.ProcessingTimeInMSec
        $TestCaseID = $obj.TestCaseID                $key = "${TestCaseID}${SEPERATOR}${ReportJobTypeName}"        $valueObj = New-Object PSObject -Property @{                        ReportJobTypeId       = $ReportJobTypeId                             StartTime             = $ReportJobStartTime                          EndTime               = $ReportJobEndTime                        ProcessingTimeInMs    = $ProcessingTimeInMSec                    }

        AddTo-StatsMapTable -mapTable $script:rptJobStatsTable -tcIDJobType $key -value $valueObj
    }
    $i++
}

# Create files with stat for each test case.
$TEMP_PATH = [System.IO.Path]::GetTempPath()
$tempFolder = [guid]::NewGuid().toString().replace("-", "")
$outputFolder = [System.IO.Path]::Combine($TEMP_PATH, $tempFolder)

"Writing temporary files to $outputFolder"

$script:rptJobStatsTable.Keys | % {
    $key = $_
    $objList = $script:rptJobStatsTable.get_item($key)
    $tcId = $key.Split($SEPERATOR)[0]    
    $jobType = $key.Split($SEPERATOR)[1]    

    $objList | % {
        $obj = $_
        $ReportJobTypeId = $obj.ReportJobTypeId
        $StartTime = $obj.StartTime
        $EndTime = $obj.EndTime
        $ProcessingTimeInMs = $obj.ProcessingTimeInMs
            
        $outFileFullPath = [System.IO.Path]::Combine($outputFolder, "$tcId^$jobType.csv")
        if (-not (Test-Path $outFileFullPath)) {
            Write-Host "Generating temp CSV file at - $outFileFullPath"
            $OUTFILE = New-Item -type file $outFileFullPath -force
            # Write header.
            add-content $OUTFILE "ReportJobTypeId,StartTime,EndTime,ProcessingTimeInMs"
            #add-content $OUTFILE "`r`n"
        } 
        # Write value.
        add-content $OUTFILE "$ReportJobTypeId,$StartTime,$EndTime,$ProcessingTimeInMs"
        #add-content $OUTFILE "`r`n"
    }
}


# At this point OUTPUT folder has individual .csv files for each test case with processing times from all runs.
# Generate CSV files with Percentile.
$resultFolder = [System.IO.Path]::Combine($outputFolder, "RESULT")

if (-not (Test-Path $outputFolder)) {
    "No files created in $outputFolder. *** CHECK database has values for last $days days"
    exit
}

# Read each raw CSV file and compute the percentile CSV and avg CSV.
Get-ChildItem $outputFolder | % {
    $file = $_
    $fileName = $file.Name.Replace(".csv", "")
    $fileFullPath = $file.FullName
    $tcid = $fileName.Split("^")[0]
    $jobType = $fileName.Split("^")[1]
    $percentile = Get-NthPercentile -n $percentileValue -csvFilePath $fileFullPath -colHeaderForPercentile "ProcessingTimeInMs"
    $average = Get-Average -csvFilePath $fileFullPath -colHeaderForAverage "ProcessingTimeInMs"
    $percentile = [int]($changeFactor * $percentile)
    $average = [int]($changeFactor * $average)

    $lastLine = Get-LastLineFromCSV -csvFilePath $fileFullPath -colHeaderForSort "EndTime" -colHeaderForStats "ProcessingTimeInMs"
    $ReportJobTypeId = $lastLine.ReportJobTypeId
    $StartTime = $lastLine.StartTime
    $EndTime = $lastLine.EndTime
    $ProcessingTimeInMs = $lastLine.ProcessingTimeInMs

    $startDateTime = [System.DateTime]::Parse($StartTime)
    $endDateTime = [System.DateTime]::Parse($EndTime)

    $epochStartTime = ToUnixTime -dateTime $startDateTime
    $epochEndTime = ToUnixTime -dateTime $endDateTime

    # Write the resulting percentile to result CSV.
    $outFileFullPath1 = [System.IO.Path]::Combine($resultFolder, "$tcId-Percentile.csv")
    $outFileFullPath2 = [System.IO.Path]::Combine($resultFolder, "$tcId-Avg.csv")
    if (-not (Test-Path $outFileFullPath1)) {
        Write-Host "Generating result CSV file at - $outFileFullPath1"
        $OUTFILE1 = New-Item -type file $outFileFullPath1 -force
        # Write header.
        add-content $OUTFILE1 "ReportJobTypeId,StartTime,EndTime,ProcessingTimeInMs"
    } 
    if (-not (Test-Path $outFileFullPath2)) {
        Write-Host "Generating result CSV file at - $outFileFullPath2"
        $OUTFILE2 = New-Item -type file $outFileFullPath2 -force
        # Write header.
        add-content $OUTFILE2 "ReportJobTypeId,StartTime,EndTime,ProcessingTimeInMs"
    } 
    add-content $OUTFILE1 "$ReportJobTypeId,$epochStartTime,$epochEndTime,$percentile"
    add-content $OUTFILE2 "$ReportJobTypeId,$epochStartTime,$epochEndTime,$average"
}

