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
  [String] $complianceReport_executionTimesForLargeAreaPipesBaselineCollection,

  [Parameter(Mandatory=$false)]
  [String] $surveyUpload_Enabled,

  [Parameter(Mandatory=$false)]
  [String] $surveyUpload_Surveys,

  [Parameter(Mandatory=$false)]
  [String] $surveyUpload_BaseUrl,

  [Parameter(Mandatory=$false)]
  [String] $automationReportingEnabled,

  [Parameter(Mandatory=$false)]
  [String] $pushDBSeed_Enabled,
  
  [Parameter(Mandatory=$false)]
  [String] $pushDBSeed_BaseUrl,

  [Parameter(Mandatory=$false)]
  [String] $parallelBuild_Enabled,

  [Parameter(Mandatory=$false)]
  [String] $parallelBuild_RequiredNodes,

  [Parameter(Mandatory=$false)]
  [String] $isRunningOnRemoteServer,

  [Parameter(Mandatory=$false)]
  [String] $gridRemoteServerHost,

  [Parameter(Mandatory=$false)]
  [String] $ciWorkingDir                
)

#get currently executing script command for executing script path and filename
$executingCommand = (get-Variable MyInvocation -Scope 0).Value.MyCommand
$executingScriptName = $executingCommand.Name
$testPropAbsPath = $executingCommand.Path.replace("lib\$executingScriptName", $testPropertiesRelativePath)
$testPropAbsCopyPath = $testPropAbsPath.replace(".properties", ".properties.copy")
$keysTable = @{}

$isParallelBuildTurnedOn = $false
$isRunOnSeleniumGridTurnedOn = $false

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
           
           if ($key -eq "complianceReport_executionTimesForLightLoadBaselineCollection") { 
               if ($PSBoundParameters.ContainsKey("complianceReport_executionTimesForLightLoadBaselineCollection")) {
                    $value = $complianceReport_executionTimesForLightLoadBaselineCollection; 
               }
           } elseif ($key -eq "complianceReport_executionTimesForMediumLoadBaselineCollection") { 
               if ($PSBoundParameters.ContainsKey("complianceReport_executionTimesForMediumLoadBaselineCollection")) {
                    $value = $complianceReport_executionTimesForMediumLoadBaselineCollection;
               }
           } elseif ($key -eq "complianceReport_executionTimesForHighLoadBaselineCollection") { 
               if ($PSBoundParameters.ContainsKey("complianceReport_executionTimesForHighLoadBaselineCollection")) {
                    $value = $complianceReport_executionTimesForHighLoadBaselineCollection;  
               }
           } elseif ($key -eq "complianceReport_executionTimesForUltraHighLoadBaselineCollection") { 
               if ($PSBoundParameters.ContainsKey("complianceReport_executionTimesForUltraHighLoadBaselineCollection")) {
                    $value = $complianceReport_executionTimesForUltraHighLoadBaselineCollection;
               }
           } elseif ($key -eq "complianceReport_executionTimesForLargeAreaPipesBaselineCollection") { 
               if ($PSBoundParameters.ContainsKey("complianceReport_executionTimesForLargeAreaPipesBaselineCollection")) {
                    $value = $complianceReport_executionTimesForLargeAreaPipesBaselineCollection;
               }
           } elseif ($key -eq "surveyUpload.Enabled") { 
               if ($PSBoundParameters.ContainsKey("surveyUpload_Enabled")) {
                    $value = $surveyUpload_Enabled;
               }
           } elseif ($key -eq "surveyUpload.Surveys") { 
               if ($PSBoundParameters.ContainsKey("surveyUpload_Surveys")) {
                    $value = $surveyUpload_Surveys;
               }
           } elseif ($key -eq "surveyUpload.BaseUrl") { 
               if ($PSBoundParameters.ContainsKey("surveyUpload_BaseUrl")) {
                    $value = $surveyUpload_BaseUrl;
               }
           } elseif ($key -eq "downloadPath") { 
               if ($PSBoundParameters.ContainsKey("ciWorkingDir")) {
                    $value = "$ciWorkingDir\selenium-wd\data\downloads"
                    $value = $value.Replace("\", "\\")
               }
           } elseif ($key -eq "automationReporting.APIEnabled") { 
               if ($PSBoundParameters.ContainsKey("automationReportingEnabled")) {
                    $value = $automationReportingEnabled
               }
           } elseif ($key -eq "pushDBSeed.Enabled") { 
               if ($PSBoundParameters.ContainsKey("pushDBSeed_Enabled")) {
                    $value = $pushDBSeed_Enabled;
               }
           } elseif ($key -eq "pushDBSeed.BaseUrl") { 
               if ($PSBoundParameters.ContainsKey("pushDBSeed_BaseUrl")) {
                    $value = $pushDBSeed_BaseUrl;
               }
           } elseif ($key -eq "parallelBuild.Enabled") { 
               if ($PSBoundParameters.ContainsKey("parallelBuild_Enabled")) {
                    $value = $parallelBuild_Enabled;
                    if ($parallelBuild_Enabled.ToUpperInvariant() -eq "TRUE") {
                        $isParallelBuildTurnedOn = $true
                    }
               }
           } elseif ($key -eq "parallelBuild.RequiredNodes") { 
               if ($PSBoundParameters.ContainsKey("parallelBuild_RequiredNodes")) {
                    $value = $parallelBuild_RequiredNodes;
               }
           } elseif ($key -eq "runningOnRemoteServer") { 
               if ($PSBoundParameters.ContainsKey("isRunningOnRemoteServer")) {
                    $value = $isRunningOnRemoteServer;
                    if ($isRunningOnRemoteServer.ToUpperInvariant() -eq "TRUE") {
                        $isRunOnSeleniumGridTurnedOn = $true
                    }
               }
           } elseif ($key -eq "remoteServerHost") { 
               if ($PSBoundParameters.ContainsKey("gridRemoteServerHost")) {
                    $value = $gridRemoteServerHost;
               }
           } else {
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
           
           $keysTable.add($key, $value);
        }
    }
}

# If Parallel build is ENABLED and if running on Selenium Grid, generate new UUID for each run.
if ($isParallelBuildTurnedOn -and $isRunOnSeleniumGridTurnedOn) {
    if ($keysTable.ContainsKey("parallelBuild.UUID")) {
        $keysTable.set_item("parallelBuild.UUID", [guid]::NewGuid().ToString().replace("-", ""))
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



