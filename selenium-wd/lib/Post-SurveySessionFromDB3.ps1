﻿param
(
  [Parameter(Mandatory=$true)]
  [String] $WorkingFolder,                                    # eg. "C:\Repositories\surveyor-qa"

  [Parameter(Mandatory=$true)]
  [String] $BaseURL,                                          # eg. "http://localhost:50796"

  [Parameter(Mandatory=$true)]
  [String] $AnalyzerSerialNumber,                             # Analyzer serial number. Eg. "SimAuto-Analyzer1"

  [Parameter(Mandatory=$true)]
  [String] $AnalyzerSharedKey,                                # Analyzer shared key. Eg. "SimAuto-AnalyzerKey1"

  [Parameter(Mandatory=$true)]
  [String] $Surveyor,                                         # Surveyor. Eg. "SimAuto-Surveyor1"       

  [Parameter(Mandatory=$true)]
  [String] $AnalyzerLogFilePath                               # Path to Analyzer log created by automation.
)

$libFolder = "selenium-wd\lib"
$authApiURL = "api/Analyzer/Authenticate"
$postContentType = "application/json; charset=UTF-8"

# First detect if Analyzer has sent Completed session. 
# Continue processing ONLY if Analyzer has NOT sent the completed session in allocated time.
$sessionProcessed = $false
$jsonParseError = $false;
Get-Content $AnalyzerLogFilePath | % {
    [string]$line = [string]$_
    if ($line.StartsWith("Sending completed session")) {
        $sessionProcessed = $true
    }
    if ($line.Contains("Newtonsoft.Json.JsonReaderException")) {
        $jsonParseError = $true;
    }
}

if ($jsonParseError) {
    $sessionProcessed = $false
}

if ($sessionProcessed -eq $true) {
    Write-Host "Analyzer has processed 'COMPLETED SESSION' event. SKIPPING ..." 

} else {
    Write-Host "Analyzer has NOT processed 'COMPLETED SESSION' event. Uploading Survey from DB3 ..." 
    # ------------------------------------------------------------
    # Authenticate Analyzer
    # ------------------------------------------------------------

    $Body = @{
      Username=$AnalyzerSerialNumber
      Password=$AnalyzerSharedKey
    }

    $jsonBody = (ConvertTo-Json $Body)
    Write-Host "Authenticate Analyzer Session ... Body -> $jsonBody" 
    $response = Invoke-WebRequest -Uri "$BaseURL/$authApiURL" -Headers $Headers -Method POST -Body $jsonBody -ContentType $postContentType -SessionVariable wSession
    Write-Host "Posting Authenticate Session successful!" 

    # ------------------------------------------------------------
    # Post Survey Sessions
    # ------------------------------------------------------------

    # Post session to Analyzer API
    $analyzerSessionUrl = "api/Analyzer/Session"

    . $WorkingFolder\$libFolder\HelperScripts\DateTimeHelpers.ps1
    . $WorkingFolder\$libFolder\HelperScripts\StabilityClassMapper.ps1
    . $WorkingFolder\$libFolder\HelperScripts\ReadWriteSQLite.ps1 -WorkingFolder $WorkingFolder

    # Read Surveys from DB3 and post to Analyzer API.
    # For each survey also read the SurveyConditions from DB3.
    $surveys = Get-Surveys -AnalyzerSerialNumber $AnalyzerSerialNumber
    if ($surveys -ne $null) {
        $surveys | % {
            $SurveyUUID = $_.ID
            $StartEpoch = $_.StartEpoch
            $EndEpoch = $_.EndEpoch
            $User = $_.User
            $Tag = $_.Tag
            $StabilityClass = $_.StabilityClass
            $SurveyType = $_.SurveyType
            $MinimumAmplitude = $_.MinimumAmplitude

            # Fill SurveyCondition array.
            $ConditionArray = @()

            $surveyConditions = Get-SurveyConditions -AnalyzerSerialNumber $AnalyzerSerialNumber -SurveyId $ID
            if ($surveyConditions -ne $null) {
                $surveyConditions | % {
                    $ID = $_.ID
                    $SurveyId = $_.SurveyId
                    $Name = $_.Name
                    $Value = $_.Value

                    $newGuid = [guid]::NewGuid().toString()
                    $Condition = @{
                       Id="$newGuid"
                       Name="$Name"
                       Value="$Value"
                    }
                    $ConditionArray += $Condition
                }
            }

            # If EndEpoch is NULL, post current epoch time as Survey end time.
            if ($EndEpoch -eq [System.DbNull]::Value) {
                $endDateTime = [System.DateTime]::UtcNow
                $EndEpoch = ToUnixTime -dateTime $endDateTime
            }

            # Map SurveyStabilityClass -> AnalyzerSurveyStabilityClass.
            $StabilityClass = MapTo-AnalyzerSurveyStabilityClass -surveyStabilityClass "$StabilityClass"

            $Body2 = @{
                Id="$SurveyUUID"
                User="$User"
                StartEpoch=$StartEpoch
                EndEpoch=$EndEpoch
                Tag="$Tag"
                StabilityClass="$StabilityClass"
                SurveyType="$SurveyType"
                MinimumAmplitude=$MinimumAmplitude
                Conditions=$ConditionArray
                Surveyor="$Surveyor"
                Analyzer="$AnalyzerSerialNumber"
            }

            $jsonBody2 = (ConvertTo-Json $Body2)
            Write-Host "Posting Survey Session ... Body -> $jsonBody2" 
            $response = Invoke-WebRequest -Uri "$BaseURL/$analyzerSessionUrl" -Headers $Headers -Method POST -Body $jsonBody2 -ContentType $postContentType -WebSession $wSession
            Write-Host "Posting survey session successful!"        
        }
    }
}