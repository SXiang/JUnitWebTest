# 
# Sample Usage: 
#   .\Inject-BuildInformation.ps1 -buildWorkingDir "C:\Repositories\surveyor-qa" `
#       -buildXmlPath "selenium-wd\build.xml" `
#       -buildNumberFilePath "selenium-wd\build.number" `
#       -buildNumber "1" `
#       -vcsBuildNumber "50d57b8a274b9d1e04ff23d5dd58df6051f2e982" `
#       -vcsBranchName "master" `
#       -major "2" `
#       -minor "4" `
#       -environmentUrl "https://p3sqaauto.picarro.com" `
#       -AutomationReportingAPIBaseUrl "http://localhost:63087"

param
(
  [Parameter(Mandatory=$true)]
  [String] $buildWorkingDir,

  [Parameter(Mandatory=$true)]
  [String] $buildXmlPath,

  [Parameter(Mandatory=$true)]
  [String] $buildNumberFilePath,

  [Parameter(Mandatory=$true)]
  [String] $buildNumber,

  [Parameter(Mandatory=$true)]
  [String] $vcsBuildNumber,

  [Parameter(Mandatory=$true)]
  [String] $vcsBranchName,

  [Parameter(Mandatory=$true)]
  [String] $major,

  [Parameter(Mandatory=$true)]
  [String] $minor,

  [Parameter(Mandatory=$true)]
  [String] $environmentUrl,

  [Parameter(Mandatory=$true)]
  [String] $AutomationReportingAPIBaseUrl         # Path to AutomationReporting API Base Url. For eg. http://localhost:63087
)

# Get environment information from automation reporting API.
$libFolder = "selenium-wd\lib"
$environmentBuildInfoApiUrl = "api/EnvironmentBuildDetails?environmentUrl=$environmentUrl"
$response = . $BuildWorkingDir\$libFolder\Get-ReportingAPIResponse.ps1 -BuildWorkingDir $buildWorkingDir -AutomationReportingAPIBaseUrl $AutomationReportingAPIBaseUrl -APIRelativeUrl $environmentBuildInfoApiUrl

if ($response.StatusCode -ne 200) {
    Write-Error "Invalid Response -> $response"
    return
}

$jsonContent = $response.Content | ConvertFrom-Json
$gitHash = $vcsBuildNumber.Substring(0, 7)
$gitBranch = $vcsBranchName
$envName = $jsonContent.Environment.EnvironmentName
$envDBName = $jsonContent.Environment.DBName
$envBuildVersion = $jsonContent.Build.BuildNumber
$buildVersion = "${major}.${minor}.${buildNumber}.${gitHash}"

# Update build.xml properties required for JAR version
$buildXmlFile = [System.IO.Path]::Combine($buildWorkingDir, $buildXmlPath)
$buildXmlFileContent = (Get-Content $buildXmlFile) -as [Xml]
$versionNode = $buildXmlFileContent.project.property | ? {$_.name -eq 'version'}
$gitHashNode = $buildXmlFileContent.project.property | ? {$_.name -eq 'git_hash'}
$gitBranchNode = $buildXmlFileContent.project.property | ? {$_.name -eq 'git_branch'}
$versionNode.value = "${major}.${minor}"
$gitHashNode.value = $gitHash
$gitBranchNode.value = $gitBranch
$buildXmlFileContent.Save($buildXmlFile)

# Create build.number file next to build.xml with specified build number
$buildNumberFile = [System.IO.Path]::Combine($buildWorkingDir, $buildNumberFilePath)
New-Item $buildNumberFile -Type file -Force -Value "build.number=$buildNumber"


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
$manifestXmlFile = [System.IO.Path]::Combine($buildWorkingDir, $buildXmlPath.Replace("build.xml", "manifest.xml"))
$xmlTextWriter = new-object System.Xml.XmlTextWriter($manifestXmlFile, [System.Text.Encoding]::ASCII)
$xmlTextWriter.Formatting = [System.Xml.Formatting]::Indented
$xmlTextWriter.WriteStartElement("Manifest")
$xmlTextWriter.WriteElementString("EnvironmentUrl", $environmentUrl)
$xmlTextWriter.WriteElementString("EnvironmentDBName", $envDBName)
$xmlTextWriter.WriteElementString("EnvironmentBuildVersion", $envBuildVersion)
$xmlTextWriter.WriteElementString("TestBinaryMajor", $major)
$xmlTextWriter.WriteElementString("TestBinaryMinor", $minor)
$xmlTextWriter.WriteElementString("TestBinaryBuildNumber", $buildVersion)
$xmlTextWriter.WriteElementString("TestBinaryGitHash", $gitHash)
$xmlTextWriter.WriteElementString("TestBranch", $gitBranch)
$xmlTextWriter.WriteEndElement()
$xmlTextWriter.Close()

# Finally service message build number to TeamCity
echo "teamcity[buildNumber '$buildVersion']"