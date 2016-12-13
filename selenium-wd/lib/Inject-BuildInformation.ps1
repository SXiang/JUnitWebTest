# 
# Sample Usage: 
#   .\Inject-BuildInformation.ps1 -buildWorkingDir "C:\repositories\surveyor-qa" -buildXmlPath "selenium-wd\build.xml" -buildNumber "100"

param
(
  [Parameter(Mandatory=$true)]
  [String] $buildWorkingDir,

  [Parameter(Mandatory=$true)]
  [String] $buildXmlPath,

  [Parameter(Mandatory=$true)]
  [String] $buildNumber
)

# Read major/minor version to match execution environment <major>.<minor>
# Git hash matches commit hash in qa branch.
# NOTE: This information should be retrieved from Automation Reporting API by reading TeamCity build version.
$major = "2"
$minor = "4"
$gitHash = "4e7e36a"
$gitBranch = "master"

$buildVersion = "${major}.${minor}.${buildNumber}.${gitHash}"

# Update build.xml properties required for JAR version


# Create build.number file next to build.xml with specified build number


# Create manifest.xml to store [product]<->[test] code version mapping
# Following info to be stored in manifest.xml :-
#   <EnvironmentUrl />
#   <EnvironmentDBName />
#   <EnvironmentBuildVersion />
#   <TestBinaryMajor />
#   <TestBinaryMinor />
#   <TestBinaryBuildNumber />
#   <TestBinaryGitHash />
#   <TestBranch />


# Finally service message build number to TeamCity
echo "teamcity[buildNumber '$buildVersion']"
