<#
 INSTRUCTIONS:
  Execute this script from the folder containing this script file to generate PageScreen code for all .uix files encountered in 'uixRootFolder'.
#>

param (
   [Parameter(Mandatory=$true)]
   [string]$uixRootFolder,                           # folder containing .uix files. eg. C:\Repositories\surveyor-qa\selenium-wd\android\ui-dump

   [Parameter(Mandatory=$true)]
   [string]$outputFolder                             # output folder when .java files will be created. Eg. C:\Temp\UIPageObjects   
)

function To-FirstCharUpper([string]$text) {
    $retVal = $text
    if ($retVal -ne "") {
        $retVal = $text.Substring(0,1).ToUpper() + $text.Substring(1)
    }

	$retVal
}

function To-PascalCase([string]$text) {
    $textParts = $text.Split("-")
    $retVal = ""
    $textParts | %{
        $part = $_    
        $part = To-FirstCharUpper -text $part
        $retVal += $part
    }

	$retVal
}

Get-ChildItem $uixRootFolder -Filter "*.uix" -Recurse | % {
    $file = $_
    $fileName = $file.Name
    $fileName = $fileName.Replace(".uix", "")
    $uixFilePath = $file.FullName

    $screenClassName = To-PascalCase -text $fileName
    $outputFilePath = "$outputFolder\Android${screenClassName}.java"
    $detectLabels = $true

    Write-Host "Generating page object code for screen - '$screenClassName'. UIX file -> '$uixFilePath'"
    .\Generate-AndroidScreenPageObjectFromUixFile.ps1 -OutputFilePath "$outputFilePath" -UixFilePath "$uixFilePath" -ScreenClassName "Android$screenClassName" -DetectLabels:$detectLabels
    Write-Host "Page object code generated at -> $outputFilePath"
}
