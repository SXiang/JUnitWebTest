param
(
  [bool] $isRunningLocally = $false,
  [bool] $overwriteHostExeFiles = $false,
  [bool] $overwriteReplayFiles = $false,
  [bool] $overwriteDB3File = $true,              # By default always overwrite the .db3 file
  [bool] $overwritePicarroAnalyzer = $true       # By default always overwrite the Picarro Analyzer files
)

# -------- These values could be supplied from Caller --------
$TEST_ENV_DOMAIN = "p3sqaauto.picarro.com"

# -------- Static Constants -------------

$TEMP_PATH = [System.IO.Path]::GetTempPath()

# 1.
$7ZIP_REG_PATH = 'HKCU:\Software\7-Zip'
$7ZIP_EXE_NAME = '7z.exe'
$7ZIP_DOWNLOAD_URL = 'http://www.7-zip.org/a/7z920-x64.msi'
$7ZIP_MSI_DEST = '${TEMP_PATH}7zip-x64.msi'
$7ZIP_INSTALL_LOG = '${TEMP_PATH}7zip-install.log'
$7ZIP_FOLDER = "C:\Program Files\7-Zip"

$PYTHON_APP_INSTALL_TEXT = 'Python 2.7.'
$CHOCOLATEY_APP_INSTALL_TEXT = 'Chocolatey v'
$CURL_APP_INSTALL_TEXT1 = 'curl'
$CURL_APP_INSTALL_TEXT2 = 'Protocols'
$CURL_APP_INSTALL_TEXT3 = 'Features'
$GRADLE_APP_INSTALL_TEXT1 = 'Gradle'
$GRADLE_APP_INSTALL_TEXT2 = 'Build time'
$GRADLE_APP_INSTALL_TEXT3 = 'Build number'

$CHOCOLATEY_INSTALL_SCRIPT_PATH = 'https://chocolatey.org/install.ps1'

# 2.
# $TODO: After checkin to GIT this can be changed to pull from GIT.
$HOST_EXE_FOLDER_PATH = "C:\Picarro\G2000\HostExe"
$DATAMANAGERPUBLISHER_EXE = "DataManagerPublisher.exe"
$SUPERVISOR_EXE = "supervisor.exe"

$DATAMANAGERPUBLISHER_EXE_SRC = "\\p-netapp2\temp_space\SurveyorSimulatorAutomation\hostsim\HostSim\DataManagerPublisher.exe"
$SUPERVISOR_EXE_SRC = "\\p-netapp2\temp_space\SurveyorSimulatorAutomation\hostsim\HostSim\supervisor.exe"

# 3.
# $TODO: After checkin to GIT this can be changed to pull from GIT.
$DB3_FILE_SRC = "\\p-netapp2\temp_space\SurveyorSimulatorAutomation\Surveyor.db3"
$DB3_FILENAME = "Surveyor.db3"
$DB3_FILE_DEST = "C:\PicarroAnalyzerSimAutomation\"

# 4.
$7ZIP_INSTALL_FILE = "https://$TEST_ENV_DOMAIN/Content/Analyzer/Install.7z"
$7ZIP_INSTALL_DEST = "${TEMP_PATH}Install.7z"
$ANALYZER_FOLDER_PATH = "C:\PicarroAnalyzer"
$PICARRO_SURVEYOR_ANALYZER_EXE = "Picarro.Surveyor.Analyzer.exe"

# 5.
# ASSUMPTIONS:
#  Analyzer with these properties ($SIMAUTO_ANALYZER1, $SIMAUTO_ANALYZERKEY1) exists in the Test Environment
$PICARRO_SURVEYOR_ANALYZER_EXE_CONFIG = "Picarro.Surveyor.Analyzer.exe.config"
$SIMAUTO_ANALYZER1 = "SimAuto-Analyzer1"
$SIMAUTO_ANALYZERKEY1 = "SimAuto-AnalyzerKey1"

# 6.
$ANALYZER_SIM_AUTOMATION_SRC = "\\p-netapp2\temp_space\SurveyorSimulatorAutomation\hostsim\Example"
$ANALYZER_SIM_AUTOMATION_DEST = "C:\PicarroAnalyzerSimAutomation"
$REPLAY_DB3_DEFN = "replay-db3.defn"
$REPLAY_DB3_CURL_BAT = "replay-db3-curl.bat"


#---------------------------------------------------------
# Checks if specified application is installed or not.
# Returns TRUE if installed, FALSE otherwise. 
#---------------------------------------------------------
function IsInstalled($application) {
    $isInstalled = $false
    
    if ($application -eq 'python') {
        # check if python 2.7.x is installed.
        $appCmd = python --version 2>&1
        $appVer = $appCmd.ToString()    
        $isInstalled = $appVer.StartsWith($PYTHON_APP_INSTALL_TEXT)
    } elseif ($application -eq 'choco') {
        $appCmd = choco 2>&1
        $appVer = $appCmd.ToString()
        $isInstalled = $appVer.StartsWith($CHOCOLATEY_APP_INSTALL_TEXT)
    } elseif ($application -eq 'curl') {
        $appCmd = curl --version 2>&1
        $isInstalled = ($appCmd[0].Contains($CURL_APP_INSTALL_TEXT1) -and $appCmd[1].Contains($CURL_APP_INSTALL_TEXT2) -and $appCmd[2].Contains($CURL_APP_INSTALL_TEXT3))
    } elseif ($application -eq 'gradle') {
        $appCmd = gradle --version 2>&1
        $isInstalled = ($appCmd[2].Contains($GRADLE_APP_INSTALL_TEXT1) -and $appCmd[5].Contains($GRADLE_APP_INSTALL_TEXT2) -and $appCmd[6].Contains($GRADLE_APP_INSTALL_TEXT3))
    } elseif ($application -eq '7zip') {
        # get Install folder from Registry and check for presence of 7zip EXE in the folder.
        if ((Test-Path $7ZIP_REG_PATH -PathType Any) -eq $true) {
        	$7ZipFolder = (Get-ItemProperty -Path $7ZIP_REG_PATH).Path		
        	if (Test-Path "${7ZipFolder}${7ZIP_EXE_NAME}") {
                $isInstalled = $true
        	}
        }
    }
    
    return $isInstalled
}

#---------------------------------------------------------
# Installs the specified application. 
#---------------------------------------------------------
function InstallApplication($application) {
    if ($application -eq 'python') {
        # install python 2.7.6
        choco install python -version 2.7.6 -y 
    } elseif ($application -eq 'choco') {
        iex ((new-object net.webclient).DownloadString($CHOCOLATEY_INSTALL_SCRIPT_PATH))
        SET PATH=%PATH%;%ALLUSERSPROFILE%\chocolatey\bin
    } elseif ($application -eq 'curl') {
        choco install curl
    } elseif ($application -eq 'gradle') {
        choco install gradle -y
    } elseif ($application -eq '7zip') {
        # Download msi and trigger quiet install.
        "Start downloading 7-zip .msi to $7zipDest"
        (new-object System.Net.WebClient).DownloadFile($7ZIP_DOWNLOAD_URL, $7ZIP_MSI_DEST)
        "Successfully downloaded 7-zip"
        if (Test-Path $7ZIP_MSI_DEST) {
            "Start 7-zip install - $7ZIP_MSI_DEST"
            Start-Process -FilePath $7ZIP_MSI_DEST -ArgumentList '/qn /l*v $7ZIP_INSTALL_LOG' | Wait-Process            
            "Installed 7-zip. View logs at - '$7ZIP_INSTALL_LOG'"
        }
    }
}

# ---------------------- Install pre-reqs (if NOT installed) ---------------------------------

# ----- STEP 1: Install pre-reqs -------

Write-Host "[STEP 1]: Check and Install pre-reqs"

# Check if choco is installed. If NOT install
Write-Host "Checking if Chocolatey is installed ..."
$chocoInstalled = IsInstalled -application 'choco'
if ($chocoInstalled -eq $false) {
    "Chocolatey NOT installed. Installing ..."
    InstallApplication -application 'choco'

} else {
    "Chocolatey is already installed. Skipping install."
}

# Check if correct version of python is installed. If NOT install
Write-Host "Checking if Python 2.7.x version is installed ..."
$pythonInstalled = IsInstalled -application 'python'
if ($pythonInstalled -eq $false) {
    "Python 2.7.x NOT installed. Installing ..."
    InstallApplication -application 'python'
    
} else {
    "Python 2.7.x is already installed. Skipping install."
}

# Check if curl is installed. If NOT install
Write-Host "Checking if curl is installed ..."
$curlInstalled = IsInstalled -application 'curl'
if ($curlInstalled -eq $false) {
    "Curl NOT installed. Installing ..."
    InstallApplication -application 'curl'
    
} else {
    "Curl is already installed. Skipping install."
}


# Check if gradle is installed. If NOT install
Write-Host "Checking if gradle is installed ..."
$gradleInstalled = IsInstalled -application 'gradle'
if ($gradleInstalled -eq $false) {
    "Gradle NOT installed. Installing ..."
    InstallApplication -application 'gradle'    
    
} else {
    "Gradle is already installed. Skipping install."
}

# Check if 7-Zip is installed. If NOT install
Write-Host "Checking if 7-Zip is installed ..."
$7zipInstalled = IsInstalled -application '7zip'
if ($7zipInstalled -eq $false) {
    "7-zip NOT installed. Installing ..."
    InstallApplication -application '7zip'    
    
} else {
    "7-zip is already installed. Skipping install."
}

# ---------------------- Setup Environment (Most Steps here NEEDED for Local Run) ---------------------------------

if ($isRunningLocally -eq $false) {
    # NOT running locally. RETURN.
    return;
}

# Local Run. Proceed ahead.

# ----- STEP 2: Deploy HostEXE files -------

Write-Host "[STEP 2]: Deploy hostEXE files"

if (-not (Test-Path $HOST_EXE_FOLDER_PATH)) {
    "Creating directory - $HOST_EXE_FOLDER_PATH"
    new-item -type directory -path $HOST_EXE_FOLDER_PATH
}

if (($overwriteHostExeFiles) -or (-not (Test-Path "$HOST_EXE_FOLDER_PATH\$DATAMANAGERPUBLISHER_EXE"))) {
    "Copying $DATAMANAGERPUBLISHER_EXE ..."
    copy-item $DATAMANAGERPUBLISHER_EXE_SRC -destination $HOST_EXE_FOLDER_PATH -Force
}

if (($overwriteHostExeFiles) -or (-not (Test-Path "$HOST_EXE_FOLDER_PATH\$DATAMANAGERPUBLISHER_EXE"))) {
    "Copying $SUPERVISOR_EXE ..."
    copy-item $SUPERVISOR_EXE_SRC -destination $HOST_EXE_FOLDER_PATH -Force
}

# ----- STEP 3: Copy .db3 to %TEMP% -------

Write-Host "[STEP 3]: Copy .db3 file"

if (($overwriteDB3File) -or (-not (Test-Path "${TEMP_PATH}$DB3_FILENAME"))) {
    "Copying $DB3_FILENAME to $DB3_FILE_DEST"
    copy-item $DB3_FILE_SRC -destination $DB3_FILE_DEST -Force
}

# ----- STEP 4: Deploy Picarro.Surveyor.Analyzer.exe -------

Write-Host "[STEP 4]: Deploy Picarro.Surveyor.Analyzer.exe"

$analyzerExists = $true
if (-not (Test-Path $ANALYZER_FOLDER_PATH)) {
	$analyzerExists = $false
    "Creating directory - $ANALYZER_FOLDER_PATH"
    new-item -type directory -path $ANALYZER_FOLDER_PATH
}

# Delete existing Install.7z file if it already exists in $TEMP_PATH
if (Test-Path "$7ZIP_INSTALL_DEST") {
    "Deleting $7ZIP_INSTALL_DEST ..."
    Remove-Item "$7ZIP_INSTALL_DEST" -Force
}

if (($analyzerExists -eq $false) -or ($overwritePicarroAnalyzer -eq $true)) {
	# Download and unzip Analyzer .7z file.
	"Start downloading $7ZIP_INSTALL_FILE"
	(new-object System.Net.WebClient).DownloadFile($7ZIP_INSTALL_FILE, $7ZIP_INSTALL_DEST)
	"Successfully downloaded Analyzer .7z file"
	if (Test-Path $7ZIP_INSTALL_DEST) {
		if (($overwritePicarroAnalyzer) -or (-not (Test-Path "$ANALYZER_FOLDER_PATH\$PICARRO_SURVEYOR_ANALYZER_EXE"))) {
			"Start unzipping .7z from - $7ZIP_INSTALL_DEST"
			Start-Process -FilePath "$7ZIP_FOLDER\7z" -ArgumentList 'e Test.7z -o{$ANALYZER_FOLDER_PATH}' | Wait-Process            
			"Unzipped files at - '$ANALYZER_FOLDER_PATH'"
		}
	}
}

# ----- STEP 5: Set Analyzer.exe.config values -------

Write-Host "[STEP 5]: Set Analyzer.exe.config values"

$exeConfig = new-object Xml
$exeConfig.load("$ANALYZER_FOLDER_PATH\$PICARRO_SURVEYOR_ANALYZER_EXE_CONFIG")

"Updating 'P3Url' in .exe.config to https://$TEST_ENV_DOMAIN "
$exeConfig.configuration.SelectSingleNode("appSettings/add[@key='P3Url']").value = "https://$TEST_ENV_DOMAIN"

"Updating 'SerialNumber' in .exe.config to $SIMAUTO_ANALYZERKEY1 "
$exeConfig.configuration.SelectSingleNode("appSettings/add[@key='SerialNumber']").value = $SIMAUTO_ANALYZER1

"Updating 'SharedKey' in .exe.config to $SIMAUTO_ANALYZERKEY1 "
$exeConfig.configuration.SelectSingleNode("appSettings/add[@key='SharedKey']").value = $SIMAUTO_ANALYZERKEY1

$exeConfig.save("$ANALYZER_FOLDER_PATH\$PICARRO_SURVEYOR_ANALYZER_EXE_CONFIG")

# ----- STEP 6: Copy replay script files -------

Write-Host "[STEP 6]: Copy replay script files"

if (-not (Test-Path $ANALYZER_SIM_AUTOMATION_DEST)) {
    "Creating directory - $ANALYZER_SIM_AUTOMATION_DEST"
    new-item -type directory -path $ANALYZER_SIM_AUTOMATION_DEST
}

if (($overwriteReplayFiles) -or (-not (Test-Path "$ANALYZER_SIM_AUTOMATION_DEST\$REPLAY_DB3_DEFN"))) {
    "Copying $REPLAY_DB3_DEFN to $ANALYZER_SIM_AUTOMATION_DEST"
    copy-item "$ANALYZER_SIM_AUTOMATION_SRC\$REPLAY_DB3_DEFN" -destination $ANALYZER_SIM_AUTOMATION_DEST -Force
}

if (($overwriteReplayFiles) -or (-not (Test-Path "$ANALYZER_SIM_AUTOMATION_DEST\$REPLAY_DB3_CURL_BAT"))) {
    "Copying $REPLAY_DB3_CURL_BAT to $ANALYZER_SIM_AUTOMATION_DEST"
    copy-item "$ANALYZER_SIM_AUTOMATION_SRC\$REPLAY_DB3_CURL_BAT" -destination $ANALYZER_SIM_AUTOMATION_DEST -Force
}

