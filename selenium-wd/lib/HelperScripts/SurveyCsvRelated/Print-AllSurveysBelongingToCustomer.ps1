<#--------------------------------------------------------------------------------------------------------------------------------
 DESCRIPTION:
  - Use script to print lis tof survey filetags for specified customer.

 Sample Run Script:
   .\Print-AllSurveysBelongingToCustomer.ps1 `
        -SurveyCsvFolder "C:\Temp\RegenerateSurveys-SQACUS-20171106_02\OutputInSingleFolder"  `
        -CustomerName "sqacus"  `
        -OutputFolder "C:\temp"
----------------------------------------------------------------------------------------------------------------------------------#>

param(
    [Parameter(Mandatory=$true)]
    [String] $SurveyCsvFolder,

    [Parameter(Mandatory=$true)]    
    [String] $CustomerName,

    [Parameter(Mandatory=$true)]    
    [String] $OutputFolder
)

function Is-SurveyForPicarro($surveyFilename) {
    $isPicarroSurvey = $surveyFilename.StartsWith("Survey-") -and (-not $surveyFilename.Contains("sqacus"))        
    $isPicarroSurvey
}

function Is-SurveyForCustomer($surveyFilename, $custName) {
    $custName = $custName.ToLower()
    $isCustSurvey = $surveyFilename.StartsWith("Survey-") -and ($surveyFilename.Contains("$custName"))        
    $isCustSurvey
}

$OUTFILE = New-Item "$OutputFolder\surveyList.txt" -Force

$script:filenameList = New-Object System.Collections.ArrayList

get-childItem $SurveyCsvFolder -Filter "*.csv" | % {
    $file = $_
    $fileName = $file.Name
    $fileFullpath = $file.FullName
    $fileDirPath = $file.DirectoryName
    
    $isMatch = $false
    if ($fileName.StartsWith("Survey-")) {
        if ($CustomerName.ToLower() -eq "picarro") {
            $isMatch = Is-SurveyForPicarro -surveyFilename $fileName
        } else {
            $isMatch = Is-SurveyForCustomer -surveyFilename $fileName -custName $CustomerName
        }

        if ($isMatch) {
            $fileName -match "Survey-(.+)\.csv"
            $filenameTag = $Matches[1]            
            $null = $script:filenameList.Add($filenameTag)
        }
    }
}

$script:filenameList | Sort-Object | % { 
    Add-Content $OUTFILE $_
}

ii $OUTFILE