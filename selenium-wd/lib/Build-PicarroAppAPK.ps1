param
(
  [string] $buildWorkingDir = "C:\Repositories\PicarroApp",
  [string] $buildFlavor = "debug"    # either 'release' or 'debug'
)

# trigger build
cd "$buildWorkingDir\android"
if ($buildFlavor.ToLower() -eq "release") {
  "Starting Release build ..."
  .\gradlew assembleRelease
} else {
  "Starting Debug build ..."
  .\gradlew assembleDebug
}
