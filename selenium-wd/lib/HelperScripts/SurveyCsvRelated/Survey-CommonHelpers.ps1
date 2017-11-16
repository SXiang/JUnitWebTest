<#--------------------------------------------------------------------------------------------------------------------------------
 DESCRIPTION:
  - Common functions used in survey CSV related scripts.
----------------------------------------------------------------------------------------------------------------------------------#>

function Get-OtherCustomerSurvey($filename, $fileDirectory, $customerName) { 
    $otherCustSrv = ""
    $match = $false
    if ($filename -match "(.+)\-?(\d+?)\.csv") {
        $match = $true
    } elseif ($filename -match "(.+)\-?(\d+)?\.csv") {
        $match = $true
    }

    if ($match) {
        $fname = $Matches[1]
        $fIdx = ""
        if ($Matches.Count -gt 2) {
            $fIdx = $Matches[2]
        }

        $otherCustFNamePattern1 = "${fname}${customerName}-${fIdx}.csv"
        $otherCustFNamePattern2 = "${fname}${fIdx}-${customerName}.csv"
        $otherCustFNamePattern3 = "${fname}-${customerName}.csv"
        if (Test-Path -PathType Leaf "$fileDirectory\$otherCustFNamePattern1") {
            $otherCustSrv = $otherCustFNamePattern1
        } elseif (Test-Path -PathType Leaf "$fileDirectory\$otherCustFNamePattern2") {
            $otherCustSrv = $otherCustFNamePattern2
        } elseif (Test-Path -PathType Leaf "$fileDirectory\$otherCustFNamePattern3") {
            $otherCustSrv = $otherCustFNamePattern3
        }
    }

    $otherCustSrv
}

#----------------------------------------------------------------------------------------
# PRE-REQ: ".\selenium-wd\lib\HelperScripts\DatabaseHelpers.ps1" included.
#----------------------------------------------------------------------------------------
function Get-HardwareCapabilityTypes($analyzerId) {
    $ahctList = New-Object System.Collections.ArrayList
    
    $connString = "Server=$DatabaseIP;Database=$DatabaseName;User Id=$DatabaseUser;Password=$DatabasePwd;"

    $i1=0
    Write-Host "Fetching HardwareCapabilityTypes for Analyzer: $analyzerId"
    $query = "SELECT [AnalyzerId],[HardwareCapabilityTypeId]  FROM [$DatabaseName].[dbo].[AnalyzerHardwareCapabilityType] WHERE AnalyzerId='$analyzerId'"
    $objAnalyzerHardwareCapabilityType = Get-DatabaseData -connectionString $connString -query $query -isSQLServer:$true
    $objAnalyzerHardwareCapabilityType | foreach {
        if ($i1 -gt 0) {     # first row is length of array. datarows are from index=1
            Write-Host "Table -> AnalyzerHardwareCapabilityType, Analyzer -> $analyzerId - Processing row - $i1"
                    
            $objAne = $_;
            $aneAnalyzerId = $objAne.AnalyzerId;
            [Int64]$aneHardwareCapTypeId = $objAne.HardwareCapabilityTypeId;

            $null = $ahctList.Add($aneHardwareCapTypeId);
        }

        $i1++
    }

    $ahctList
}
