# ---------------------------------------------------------------
# SAMPLE USAGE:
#   .\GetGridNodesAvailability.ps1 -workingDir "C:\repositories\surveyor-qa" `
#                        -gridHost "54.213.165.135" `
#                        -gridPort "4444" `
#                        -testRunUUID "testRun1" `
#                        -browser "CHROME" `
#                        -OS "WIN8"     
# ---------------------------------------------------------------
param
(
  [Parameter(Mandatory=$true)]
  [String] $workingDir,              # for eg. C:\repositories\surveyor-qa

  [Parameter(Mandatory=$true)]
  [String] $gridHost,                # for eg. 54.213.165.135

  [Parameter(Mandatory=$true)]
  [String] $gridPort,                # for eg. 4444 

  [Parameter(Mandatory=$true)]
  [String] $testRunUUID,             # Run Unique ID
  
  [Parameter(Mandatory=$true)]
  [String] $browser,                 # Type of browser. Valid values = "CHROME", "FIREFOX", "IE"

  [Parameter(Mandatory=$true)]
  [String] $os                       # OS Type. Eg. "WIN8", "LINUX"
)

$script:testRunNodesTable = @{}

function AddTo-TestRunNodesTable($testRunID, [object] $nodeInfoObject) 
{
    if (-not $script:testRunNodesTable.ContainsKey($testRunID)) {
        $runNodesList = New-Object System.Collections.ArrayList
        $null = $runNodesList.Add($nodeInfoObject)
        $script:testRunNodesTable.Add($testRunID, $runNodesList)
    } else {
        $runNodesList = [System.Collections.ArrayList]$script:testRunNodesTable.Get_Item($testRunID)
        $null = $runNodesList.Add($nodeInfoObject)
        $script:testRunNodesTable.Set_Item($testRunID, $runNodesList)
    }       
}

function Get-MatchingRunNodesCount($runID, $browserType, $osType) {
    $nodesCount = 0
    $script:testRunNodesTable.Keys | % {
        $key = $_
        $ndObjectList = $script:testRunNodesTable.get_item($key)
        $ndObjectList | % {
            $ndObject = $_
            if (($ndObject.Browser -eq $browserType) -and ($ndObject.OS -eq $osType) -and ($ndObject.TestRunID -eq $runID)) {
                $nodesCount += $ndObject.NodesCount
            }
        }
    }
    $nodesCount
}


function Extract-TestRunNodes {
    param (
        [string]$htmlString
    )
    [string]$HTMLAgilityPath = "$workingDir\selenium-wd\lib\HTMLAgilityPack\HtmlAgilityPack.dll"
    Add-Type -Path $HTMLAgilityPath
    $htmlDoc = New-Object HtmlAgilityPack.HtmlDocument
    $htmlDoc.LoadHTML($htmlString)

    # process left and right nodes.
    $columnTypes = "leftColumn", "rightColumn"

    $columnTypes | % {
        $columnType = $_
        $runUUID = ""

        $osTypeElement = $htmlDoc.DocumentNode.SelectSingleNode("//*[@id='$columnType']/div[1]/p[2]")
        if ($osTypeElement -ne $null -and $osTypeElement -ne "") {
            [string]$osTyp = [string]$osTypeElement.InnerText
            $osTyp = $osTyp.Split(":")[4].Trim()
        }

        $uuidElement = $htmlDoc.DocumentNode.SelectSingleNode("//*[@id='$columnType']/div/div[2]/div[2]/p[6]")
        if ($uuidElement -ne $null -and $uuidElement -ne "") {
            $runUUID = $uuidElement.InnerText
            $runUUID = $runUUID.Split(":")[1]
        }

        $gridInstances = $htmlDoc.DocumentNode.SelectNodes("//*[@id='$columnType']/div")
        $totalInstancesCount = $gridInstances.Count
        for ($i=1; $i -le $totalInstancesCount; $i++) {
            $chromeNodesCount = 0
            $firefoxNodesCount = 0
            $ieNodesCount = 0

            $ieNodes = $htmlDoc.DocumentNode.SelectNodes("//*[@id='$columnType']/div[$i]/div[2]/div[1]/p[2]/img")
            if ($ieNodes -ne $null -and $ieNodes -ne "") {
                $ieNodesCount = $ieNodes.Count
            }

            $firefoxNodes = $htmlDoc.DocumentNode.SelectNodes("//*[@id='$columnType']/div[$i]/div[2]/div[1]/p[3]/img")
            if ($firefoxNodes -ne $null -and $firefoxNodes -ne "") {
                $firefoxNodesCount = $firefoxNodes.Count
            }
        
            $chromeNodes = $htmlDoc.DocumentNode.SelectNodes("//*[@id='$columnType']/div[$i]/div[2]/div[1]/p[4]/img")
            if ($chromeNodes -ne $null -and $chromeNodes -ne "") {
                $chromeNodesCount = $chromeNodes.Count
            }

            if ($ieNodesCount -gt 0) {
                $browserTyp = "IE"
                $nodeInfoObject = New-Object –TypeName PSObject –Prop (@{'TestRunID' = $runUUID; 'Browser'= $browserTyp; 'OS'=$osTyp; 'NodesCount' = $ieNodesCount;})
                AddTo-TestRunNodesTable -testRunID $runUUID -nodeInfoObject $nodeInfoObject
            }
            if ($firefoxNodesCount -gt 0) {
                $browserTyp = "FIREFOX"
                $nodeInfoObject = New-Object –TypeName PSObject –Prop (@{'TestRunID' = $runUUID; 'Browser'= $browserTyp; 'OS'=$osTyp; 'NodesCount' = $firefoxNodesCount;})
                AddTo-TestRunNodesTable -testRunID $runUUID -nodeInfoObject $nodeInfoObject
            }
            if ($chromeNodesCount -gt 0) {
                $browserTyp = "CHROME"
                $nodeInfoObject = New-Object –TypeName PSObject –Prop (@{'TestRunID' = $runUUID; 'Browser'= $browserTyp; 'OS'=$osTyp; 'NodesCount' = $chromeNodesCount;})
                AddTo-TestRunNodesTable -testRunID $runUUID -nodeInfoObject $nodeInfoObject
            }
        }
    }
}

#------------ MAIN CODE STARTS FROM HERE ---------------------

$gridConsoleURL = "http://${gridHost}:$gridPort/grid/console"
$webResponse = Invoke-WebRequest $gridConsoleURL
$html = $webResponse.RawContent

Extract-TestRunNodes -htmlString $html

$countOfFreeNodes = Get-MatchingRunNodesCount -runID $testRunUUID -browserType $browser -osType $os

Write-Host "Free Nodes Count=[$countOfFreeNodes]"
