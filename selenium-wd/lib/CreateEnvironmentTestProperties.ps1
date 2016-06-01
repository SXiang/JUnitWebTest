param
(
  [Parameter(Mandatory=$true)]
  [String] $testPropertiesRelativePath,

  [Parameter(Mandatory=$true)]
  [String] $baseURL,
  
  [Parameter(Mandatory=$true)]
  [String] $loginUser,

  [Parameter(Mandatory=$true)]
  [String] $loginPwd,

  [Parameter(Mandatory=$true)]
  [String] $dbIPAddress,

  [Parameter(Mandatory=$true)]
  [String] $dbPortNo,
  
  [Parameter(Mandatory=$true)]
  [String] $dbName,

  [Parameter(Mandatory=$true)]
  [String] $dbUser,

  [Parameter(Mandatory=$true)]
  [String] $dbPassword,

  [Parameter(Mandatory=$true)]
  [String] $testRunCategory,

  [Parameter(Mandatory=$true)]
  [String] $runEnvironment,

  [Parameter(Mandatory=$true)]
  [String] $complianceReport_collectReportJobPerfMetric,

  [Parameter(Mandatory=$true)]
  [String] $complianceReport_generateBaselineSSRSImages,

  [Parameter(Mandatory=$true)]
  [String] $complianceReport_generateBaselineViewImages,

  [Parameter(Mandatory=$true)]
  [String] $complianceReport_generateBaselineShapeFiles,

  [Parameter(Mandatory=$false)]
  [String] $complianceReport_executionTimesForLightLoadBaselineCollection,

  [Parameter(Mandatory=$false)]
  [String] $complianceReport_executionTimesForMediumLoadBaselineCollection,

  [Parameter(Mandatory=$false)]
  [String] $complianceReport_executionTimesForHighLoadBaselineCollection,

  [Parameter(Mandatory=$false)]
  [String] $complianceReport_executionTimesForUltraHighLoadBaselineCollection,

  [Parameter(Mandatory=$false)]
  [String] $surveyUpload_Enabled,

  [Parameter(Mandatory=$false)]
  [String] $surveyUpload_Surveys,

  [Parameter(Mandatory=$false)]
  [String] $surveyUpload_BaseUrl,

  [Parameter(Mandatory=$false)]
  [String] $ciWorkingDir
)

#get currently executing script command for executing script path and filename
$executingCommand = (get-Variable MyInvocation -Scope 0).Value.MyCommand
$executingScriptName = $executingCommand.Name
$testPropAbsPath = $executingCommand.Path.replace("lib\$executingScriptName", $testPropertiesRelativePath)
$testPropAbsCopyPath = $testPropAbsPath.replace(".properties", ".properties.copy")
$keysTable = @{}

# build the keys table.
Get-Content $testPropAbsPath | % { 
    $line = $_
    $line = $line.trim()
    if ($line.startsWith("#") -or $line.startsWith("<#")) { # skip commented line.
    } else {
        $parts = $line.split("=")
        if (($parts -ne $null) -and ($parts -ne "") -and ($parts.length -eq 2)) {
           $key = $parts[0]
           $value = $parts[1]
           $key = $key.trim()
           $value = $value.trim()
           
           $addToTable = $false
           
           if ($key -eq "complianceReport_executionTimesForLightLoadBaselineCollection") { 
               if ($PSBoundParameters.ContainsKey("complianceReport_executionTimesForLightLoadBaselineCollection")) {
                    $addToTable = $true
                    $value = $complianceReport_executionTimesForLightLoadBaselineCollection; 
               }
           } elseif ($key -eq "complianceReport_executionTimesForMediumLoadBaselineCollection") { 
               if ($PSBoundParameters.ContainsKey("complianceReport_executionTimesForMediumLoadBaselineCollection")) {
                    $addToTable = $true
                    $value = $complianceReport_executionTimesForMediumLoadBaselineCollection;
               }
           } elseif ($key -eq "complianceReport_executionTimesForHighLoadBaselineCollection") { 
               if ($PSBoundParameters.ContainsKey("complianceReport_executionTimesForHighLoadBaselineCollection")) {
                    $addToTable = $true
                    $value = $complianceReport_executionTimesForHighLoadBaselineCollection;  
               }
           } elseif ($key -eq "complianceReport_executionTimesForUltraHighLoadBaselineCollection") { 
               if ($PSBoundParameters.ContainsKey("complianceReport_executionTimesForUltraHighLoadBaselineCollection")) {
                    $addToTable = $true
                    $value = $complianceReport_executionTimesForUltraHighLoadBaselineCollection;
               }
           } elseif ($key -eq "surveyUpload.Enabled") { 
               if ($PSBoundParameters.ContainsKey("surveyUpload_Enabled")) {
                    $addToTable = $true
                    $value = $surveyUpload_Enabled;
               }
           } elseif ($key -eq "surveyUpload.Surveys") { 
               if ($PSBoundParameters.ContainsKey("surveyUpload_Surveys")) {
                    $addToTable = $true
                    $value = $surveyUpload_Surveys;
               }
           } elseif ($key -eq "surveyUpload.BaseUrl") { 
               if ($PSBoundParameters.ContainsKey("surveyUpload_BaseUrl")) {
                    $addToTable = $true
                    $value = $surveyUpload_BaseUrl;
               }
           } elseif ($key -eq "downloadPath") { 
               if ($PSBoundParameters.ContainsKey("ciWorkingDir")) {
                    $addToTable = $true
                    $value = "$ciWorkingDir\selenium-wd\data\downloads"
               }
           } else {
               $addToTable = $true
               switch ($key) {
                   "baseURL" { $value = $baseURL; } 
                   "loginUser" { $value = $loginUser; }  
                   "loginPwd" { $value = $loginPwd; } 
                   "dbIPAddress" { $value = $dbIPAddress; }  
                   "dbPortNo" { $value = $dbPortNo; }  
                   "dbName" { $value = $dbName; }  
                   "dbUser" { $value = $dbUser; } 
                   "dbPassword" { $value = $dbPassword; }  
                   "runEnvironment" { $value = $runEnvironment; }  
                   "testRunCategory" { $value = $testRunCategory; }  
                   "complianceReport_collectReportJobPerfMetric" { $value = $complianceReport_collectReportJobPerfMetric; }  
                   "complianceReport_generateBaselineSSRSImages" { $value = $complianceReport_generateBaselineSSRSImages; }  
                   "complianceReport_generateBaselineViewImages" { $value = $complianceReport_generateBaselineViewImages; }  
                   "complianceReport_generateBaselineShapeFiles" { $value = $complianceReport_generateBaselineShapeFiles; }  
               }
           }
           
           if ($addToTable -eq $true) {
                $keysTable.add($key, $value);
           }
        }
    }
}    

#create a copy of the original test properties file (if one does not already exist)
if ((Test-Path $testPropAbsCopyPath) -eq $false) {
    copy-item $testPropAbsPath $testPropAbsCopyPath
}

#write keys into test properties file
$stream = [System.IO.StreamWriter] $testPropAbsPath
$keysTable.Keys | sort-object | % {
    if ($_ -ne "") {
        $stream.WriteLine($_ + " = " + $keysTable.Get_Item($_))
    }
}
$stream.close()



