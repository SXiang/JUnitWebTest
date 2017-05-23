# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\Download-GISSeed.ps1 -BuildWorkingDir "C:\Repositories\surveyor-qa" `
#       -ArtifactoryBaseUrl "http://picarro.artifactoryonline.com/picarro" `
#       -ArtifactoryAPIKey "AKCp2VoGPeRruh1fKTMx8K99yceZRay15wBQHHuoFoDuAgib16cSrM8VuKaTtjznPeEC9QXGL" ` # NOTE: Not valid key. Replace with a valid API key. 
#       -ArtifactoryRepository "picarro-generic-private-qa" `
#       -ArtifactoryFileRelativeUrl "seed-data/Asset.Boundary.BA.zip" `
#       -SeedDataFolder "selenium-wd\data\sql" `
#       -SeedDataFile "Asset.Boundary.BA.zip"
#

param
(
  [Parameter(Mandatory=$true)]
  [String] $BuildWorkingDir,                       # Path to working directory (for eg. C:\Repositories\surveyor)

  [Parameter(Mandatory=$true)]
  [String] $ArtifactoryBaseUrl,                    # Artifactory Base Url. For eg. http://picarro.artifactoryonline.com/picarro

  [Parameter(Mandatory=$true)]
  [String] $ArtifactoryAPIKey,                     # Artifactory API Key. 

  [Parameter(Mandatory=$true)]
  [String] $ArtifactoryRepository,

  [Parameter(Mandatory=$true)]
  [String] $ArtifactoryFileRelativeUrl,

  [Parameter(Mandatory=$true)]
  [String] $SeedDataFolder,

  [Parameter(Mandatory=$true)]
  [String] $SeedDataFile
)

# Download specified seed data file from Artifactory.
$Headers = @{
    "X-JFrog-Art-Api" = $ArtifactoryAPIKey
}

$seedFileDestFolder = "$BuildWorkingDir\$SeedDataFolder"
$seedFileDownloadPath = "$seedFileDestFolder\$SeedDataFile"

$downloadURL = "$ArtifactoryBaseUrl/$ArtifactoryRepository/$ArtifactoryFileRelativeUrl"
"Start downloading GIS seed data zip file from->[$downloadURL], to->[$seedFileDownloadPath]"
Invoke-WebRequest -Uri $downloadURL -Headers $Headers -OutFile $seedFileDownloadPath
"DONE downloading GIS seed data file."
