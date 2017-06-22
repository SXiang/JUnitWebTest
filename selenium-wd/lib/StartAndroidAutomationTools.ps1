param
(
  [Parameter(Mandatory=$true)]
  [string] $AvdName                      # Eg. "android_23_google_apis_x86"
)

Write-Host "[ANDROID-TOOLS]: Starting Android Emulator ..."

$ANDROIDHOME = $env:ANDROID_HOME
Start-Process -FilePath "$ANDROIDHOME\tools\emulator.exe" -ArgumentList "-avd $AvdName -no-boot-anim -netfast -gpu on"