# 
# Sample Usage: 
#   ./CreateBuildRunnerRun.ps1 - buildWorkingDir "C:\repositories\surveyor-qa" -buildXmlPath "selenium-wd\build.xml" -testTarget "testdriverview"

param
(
  [Parameter(Mandatory=$true)]
  [String] $buildWorkingDir,

  [Parameter(Mandatory=$true)]
  [String] $buildXmlPath,

  [Parameter(Mandatory=$true)]
  [String] $testTarget
)

$buildFilesLocation = "C:\BuildRunnerService\Builds"

$uniqueId = [guid]::NewGuid()
$uniqueId = $uniqueId.toString()
$uniqueId = $uniqueId.replace("-", "")

#craete buildFilesLocation folder if it does NOT exist.
if ((Test-Path $buildFilesLocation) -eq $false) {
    new-item $buildFilesLocation -type directory
}

#create folder for the run
$newBuildFolder = $buildFilesLocation + "\" + $uniqueId
new-item $newBuildFolder -type directory

$newBuildPropertiesFile = $newBuildFolder + "\" + "TeamCityBuild.properties"

#create the .properties file
$stream = [System.IO.StreamWriter] $newBuildPropertiesFile
$stream.WriteLine("build.working.dir=$buildWorkingDir")
$stream.WriteLine("build.xml.path=$buildXmlPath")
$stream.WriteLine("tests.target=$testTarget")
$stream.close()
