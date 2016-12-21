param
(
  [Parameter(Mandatory=$false)]
  [String] $buildWorkingDir = "C:\Repositories\surveyor-qa",

  [Parameter(Mandatory=$false)]
  [String] $dbSeedReportFileRelativePath = "selenium-wd\reports\sanity\*DbSeedExecutorSanityTest.html",

  [Parameter(Mandatory=$false)]
  [int] $WaitTimeInMinutesForDBSeedPush = 40
)

$helperScriptFolder = "selenium-wd\lib\HelperScripts"

. $BuildWorkingDir\$helperScriptFolder\ZipHelpers.ps1

# Before starting the script ensure seed data push is complete and report file is generated.
$iteration = 1
$dbSeedPushComplete = $false
$DBSEED_REPORT_HTML_FILEPATTERN = "$BuildWorkingDir\$dbSeedReportFileRelativePath"
$WaitTimeInSecondsForDBSeedPush = 60 * $WaitTimeInMinutesForDBSeedPush
while ($WaitTimeInSecondsForDBSeedPush -gt 0) {
    if (Test-Path $DBSEED_REPORT_HTML_FILEPATTERN) {
        Write-Host "DB seed data push COMPLETE. Start publishing DBSeedJAR dependencies ..."
        $dbSeedPushComplete = $true
        break
    } else {
        Write-Host "[Iteration-$iteration] Waiting for seed data push to complete... Remaining wait time = $WaitTimeInSecondsForDBSeedPush seconds ..."
        Start-Sleep -Seconds 1
        $WaitTimeInSecondsForDBSeedPush--
        $iteration++
    }
}

if ($dbSeedPushComplete -eq $false) {
    Write-Host "Timeout waiting for DBSeed report file to be generated. NOT found -> $DBSEED_REPORT_HTML_FILEPATTERN "
    exit
}

# validate input -> workingDir folder 
$PUBLISH_DIR = "$buildWorkingDir\publish"
$DEV_MACHINE_WORK_DIR_SUBSTR = "surveyor-qa"
$CI_MACHINE_WORK_DIR_SUBSTR = "work"
if ($buildWorkingDir.Contains($CI_MACHINE_WORK_DIR_SUBSTR) -or $buildWorkingDir.Contains($DEV_MACHINE_WORK_DIR_SUBSTR)) {    # protect against incorrect folder deletion.
    if (Test-Path $PUBLISH_DIR) {
        Remove-Item -Path $PUBLISH_DIR -Recurse -Force
    }
} else {
    Write-Host "Incorrect working directory specified. EXITING.. WorkingDir = $buildWorkingDir"
    exit
}

# copy dependency jars (TODO: Retrieve from Artifactory or external public repository for eg. Maven).
$depJars = "common.jar",
    "commons-csv-*.jar","hamcrest-all-*.jar","junit-?.*.jar","log4j-api-*.jar","log4j-core-*.jar","sqljdbc42.jar",
    "extentreports-java-*.jar","freemarker-*.jar","joda-time-*.jar", "jsoup-*.jar"
$dest = "$PUBLISH_DIR\selenium-wd\lib\"
$depJars | % {
    $depJar = $_
    $source = "$buildWorkingDir\selenium-wd\lib\$depJar"
    xcopy $source $dest
}

# copy seed data csvs.
# copy GIS csv files.
$dest = "$PUBLISH_DIR\selenium-wd\data\sql\GisSeedData\"
$source = "$buildWorkingDir\selenium-wd\data\sql\GisSeedData\*.csv"
xcopy $source $dest
# copy sanity test survey CSV files.
$sanitySurveyCsvTags = "assessment-1", 
    "EthaneStnd", "stnd-pic"
$dest = "$PUBLISH_DIR\selenium-wd\data\sql\SurveySeedData\"
$sanitySurveyCsvTags | % {
    $csvTag = $_
    $source = "$buildWorkingDir\selenium-wd\data\sql\SurveySeedData\*-$csvTag.csv"
    xcopy $source $dest
}

# copy test.properties file.
$dest = "$PUBLISH_DIR\selenium-wd\tests\surveyor\"
$source = "$buildWorkingDir\selenium-wd\tests\surveyor\*.properties"
xcopy $source $dest

# copy log4j2.xml file.
$dest = "$PUBLISH_DIR\selenium-wd\log4j2\"
$source = "$buildWorkingDir\selenium-wd\log4j2\log4j2.xml"
xcopy $source $dest

# copy extent-config.xml file.
$dest = "$PUBLISH_DIR\selenium-wd\tests\"
$source = "$buildWorkingDir\selenium-wd\tests\extent-config.xml"
xcopy $source $dest

# copy dbseed JAR file.
$dest = "$PUBLISH_DIR\"
$source = "$buildWorkingDir\selenium-wd\lib\dbseed-*.jar"
xcopy $source $dest

$version = ""
$DBSEED_PREFIX = "dbseed-"
Get-ChildItem $dest | % {
    $file = $_
    if ($file.Name.Contains($DBSEED_PREFIX)) {
        $version = $file.Name.Replace($DBSEED_PREFIX, "").Replace(".jar", "")
    }
}

# compress the 'selenium-wd' folder
#  (delete existing archive file if it exists)
$archiveFile = "$PUBLISH_DIR\selenium-wd-$version.zip"
$archiveFilePattern = "$PUBLISH_DIR\selenium-wd*.zip"
if (Test-Path $archiveFilePattern) {
    Remove-item $archiveFilePattern -Force
}

Compress-Directory -sourceDirectoryName "$PUBLISH_DIR\selenium-wd" -destinationArchiveFileName "$archiveFile"

# remove 'selenium-wd' folder
Remove-Item -Path "$PUBLISH_DIR\selenium-wd" -Recurse -Force
