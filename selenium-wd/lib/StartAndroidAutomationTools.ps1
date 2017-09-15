param
(
  [Parameter(Mandatory=$true)]
  [string] $AvdName,                      # Eg. "android_23_google_apis_x86"

  [Parameter(Mandatory=$true)]
  [string] $NetworkArgs                   # Eg. "-netfast", "-netdelay evdo -netspeed umts -tcpdump '<filepath>'"
)

Write-Host "[ANDROID-TOOLS]: Starting Android Emulator ..."

$ANDROIDHOME = $env:ANDROID_HOME
Start-Process -FilePath "$ANDROIDHOME\tools\emulator.exe" -ArgumentList "-avd $AvdName -no-boot-anim $NetworkArgs -gpu on"