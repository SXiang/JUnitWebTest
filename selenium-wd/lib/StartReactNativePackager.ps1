param
(
  [Parameter(Mandatory=$false)]
  [string] $RootFolder="C:\Repositories"                      # Eg. "C:\Repositories"
)

$REACT_NATIVE_NODEJS_APP_BASEURL = "http://localhost:8081"
$MAX_ITERATIONS = 300

function IsEndPointAlive($url) {
    $retVal = $false
    $request = [System.Net.WebRequest]::Create($url)
    try {
        $response = $request.GetResponse()  
        $statusCode = $response.StatusCode
        $retVal = $statusCode -eq 200
    } catch {
        $retVal = $false
    }
    $retVal
}

cd "$RootFolder\PicarroApp"
Start-Process "react-native" -ArgumentList "start"

Write-Host "Waiting for react-native Packager to start ." -NoNewLine
$iteration = 0
$endPointAlive = $false
while((-not $endPointAlive) -and ($iteration -lt $MAX_ITERATIONS)) {
	$endPointAlive = IsEndPointAlive -url $REACT_NATIVE_NODEJS_APP_BASEURL
	$iteration++
    Write-Host "." -NoNewLine
	Start-Sleep -Milliseconds 1000
}

if ($endPointAlive) {
	Write-Host "React Native packager started successfully!" 
} else {
	Write-Host "React Native packager did NOT start in the allocated time of $MAX_ITERATIONS seconds" 
}