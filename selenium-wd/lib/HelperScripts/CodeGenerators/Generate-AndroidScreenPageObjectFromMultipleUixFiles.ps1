param (
   [Parameter(Mandatory=$false)]
   [string]$uixRootFolder = "C:\Repositories\surveyor-qa\selenium-wd\android\ui-dump",      # folder containing .uix files. eg. C:\Repositories\surveyor-qa\selenium-wd\android\ui-dump

   [Parameter(Mandatory=$false)]
   [string]$outputFolder = "C:\Temp\UIPageObjects"            # output folder when .java files will be created. Eg. C:\Temp\UIPageObjects   
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
