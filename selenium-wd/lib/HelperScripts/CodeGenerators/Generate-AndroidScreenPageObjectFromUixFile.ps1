<#------------------------------------------------------------------------------------------------
DESCRIPTION:
 - Script to create ScreenPageObject java class from UIAutomator .uix file for Picarro Android App UI automation.
   
EXAMPLE USAGE:   
 .\Generate-AndroidScreenPageObjectFromUixFile.ps1 -OutputFilePath "C:\temp\ScreenPageObjectClass.txt"  `
     -UixFilePath "C:\Repositories\surveyor-qa\selenium-wd\android\ui-dump\settings-screen\alarm-settings\dump_7186364136122891369.uix"   `
     -ScreenClassName "AndroidSettingsScreen"   `
     -DetectLabels:$false
------------------------------------------------------------------------------------------------#>

param(
   [Parameter(Mandatory=$true)]
   [string]$OutputFilePath,                  # eg. "C:\temp\ScreenPageObjectClass.txt"

   [Parameter(Mandatory=$true)]
   [string]$UixFilePath,                     # eg. "C:\Repositories\surveyor-qa\selenium-wd\android\ui-dump\settings-screen\alarm-settings\dump_7186364136122891369.uix"

   [Parameter(Mandatory=$true)]
   [string]$ScreenClassName,                 # eg. "AndroidSettingsScreen"

   [Parameter(Mandatory=$false)]
   [switch]$DetectLabels=$true
)

. "C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\CommonHelpers.ps1"

$script:elementMap = @{}      # [Map] : variableName => {variableName, xpath, isButton, isTextBox, isSelectBox, isSlider, isSliderContainer, isSliderLeftDelta}
$script:varNamesList = New-Object System.Collections.ArrayList

$script:foundButton = $false
$script:foundLabel = $false
$script:foundTextField = $false
$script:foundSelectBox = $false
$script:foundSlider = $false
$script:foundSliderContainer = $false
$script:foundSliderLeftDelta = $false

[System.Xml.XmlDocument]$doc = [xml](Get-Content -Path $UixFilePath)
[System.Xml.XmlNode] $root = $doc.FirstChild.NextSibling;

$rootClassNm = $root.Attributes["class"].Value
$rootXpath = "/$rootClassNm"

#------------------------------------------------------------------------------------
# Generates variable name from specified text.
#------------------------------------------------------------------------------------
function Build-UniqueVariableName([String] $text) {
    $text = $text.Replace(" ", "")
    $varName = To-PascalCase -text $text
    $varName = To-FirstCharLower -text $varName
    while ($script:varNamesList.Contains($varName)) {
        [string]$uuid = New-GuidNoDashes
        $uuid = $uuid.Substring(0, 3)
        $varName = "${varName}_$uuid"
    }

    $null = $script:varNamesList.add($varName)
    $varName
}

#------------------------------------------------------------------------------------
# Gets index of child at specified childIndex considering only elements of specific class.
#------------------------------------------------------------------------------------
function Get-ElementIndex([System.Xml.XmlNode] $node, [string] $elementClass, [int] $childIndex) {
    # move to parent. find index for type of element.
    $parent = $node.ParentNode
    $j=0; $idx=0
    while ($j -le $childIndex) {
        $elCls = $parent.ChildNodes[$j].Attributes["class"].Value
        if ($elCls -eq $elementClass) {
            $idx++
        }

        $j++
    }

    $idx
}

#------------------------------------------------------------------------------------
# Returns text of parents next sibling if it is a TextView.
#------------------------------------------------------------------------------------
function Find-NonEmptyParentNextSiblingText([System.Xml.XmlNode] $node) {
    $foundNd = $false
    $foundText = ""
    if (($node -ne $null) -and ($node.ParentNode -ne $null)) {
        $pNode = $node.ParentNode
        if ($pNode.NextSibling -ne $null) {
            if ($pNode.NextSibling.Attributes["class"].Value -eq "android.widget.TextView") {
                $foundText = Replace-SpecialChars -text $pNode.NextSibling.Attributes["text"].Value
            }
        }
    }

    $foundText
}

#------------------------------------------------------------------------------------
# Finds a sibling Text view which has text and returns its text.
#------------------------------------------------------------------------------------
function Find-NonEmptySiblingTextViewText([System.Xml.XmlNode] $node) {
    $foundNd = $false
    $foundText = ""
    if ($node -ne $null) {
        # search forward.
        $currNode = $node
        while ((-not $foundNd) -and ($currNode -ne $NULL)) {
            # search previous.
            $currNode = $node
            while ((-not $foundNd) -and ($currNode -ne $NULL)) {
                $siblingNode = $currNode.PreviousSibling
                $currNode = $siblingNode
                if ($siblingNode -ne $null -and $siblingNode.Attributes -ne $null) {
                    if ($siblingNode.Attributes["class"] -ne $null) { 
                        $clsVal = $siblingNode.Attributes["class"].Value 
                    }
                    if ($siblingNode.Attributes["text"] -ne $null) { 
                        $txtVal = $siblingNode.Attributes["text"].Value 
                    }
                    if (($clsVal -eq "android.widget.TextView") -and ($txtVal -ne $null -and $txtVal -ne "")) {
                        $foundNd = $true
                        $foundText = $txtVal
                    }
                }
            }
        }

        if (-not $foundNd) {
            $siblingNode = $currNode.NextSibling
            $currNode = $siblingNode
            if ($siblingNode -ne $null -and $siblingNode.Attributes -ne $null) {
                if ($siblingNode.Attributes["class"] -ne $null) { 
                    $clsVal = $siblingNode.Attributes["class"].Value 
                }
                if ($siblingNode.Attributes["text"] -ne $null) { 
                    $txtVal = $siblingNode.Attributes["text"].Value 
                }
                if (($clsVal -eq "android.widget.TextView") -and ($txtVal -ne $null -and $txtVal -ne "")) {
                    $foundNd = $true
                    $foundText = $txtVal
                }
            }
        }
    }

    $foundText
}

#------------------------------------------------------------------------------------
# Returns whether current node can be classifed as a Button.
# Classification Criteria:
#   TextView which has text, are within ViewGroup and do NOT have any siblings.
#------------------------------------------------------------------------------------
function Is-Button([System.Xml.XmlNode] $nodeElement) {
    $isBtn = $true
    $txt = $nodeElement.Attributes["text"].Value
    if (($txt -eq $null) -or ($txt -eq "")) {
        $isBtn = $false
    } else {
        $parentNd = $nodeElement.ParentNode
        if (($parentNd -eq $null) -or ($parentNd.Attributes["class"].Value -ne "android.view.ViewGroup")) {
            $isBtn = $false
        } else {
            if (($nodeElement.NextSibling -ne $null) -or ($nodeElement.PreviousSibling -ne $null)) {
                $isBtn = $false
            }
        }
    }

    $isBtn
}

#------------------------------------------------------------------------------------
# Returns whether current node can be classifed as a SelectBox.
# Classification Criteria:
#   Has single TextView child.
#------------------------------------------------------------------------------------
function Is-SelectBox([System.Xml.XmlNode] $nodeElement) {
    $isSelectBx = $true
    if (($nodeElement.ChildNodes -eq $null) -or ($nodeElement.ChildNodes.Count -gt 1)) {
        $isSelectBx = $false
    } else {
        $firstChild = $nodeElement.ChildNodes[0]
        if ($firstChild.Attributes["class"].Value -ne "android.widget.TextView") {
            $isSelectBx = $false
        }
    }

    $isSelectBx
}

#------------------------------------------------------------------------------------
# Returns whether current node can be classifed as a Slider.
# Classification Criteria:
#   - ViewGroups with parent ViewGroup, 3 sibling ViewGroups, no other child type. 
#------------------------------------------------------------------------------------
function Is-Slider([System.Xml.XmlNode] $nodeElement) {
    $isSlider = $true
    if (($nodeElement.NextSibling -ne $null) -or ($nodeElement.PreviousSibling -eq $null)) {
        $isSlider = $false
    } else {
        $prevSiblingCount = Get-PreviousSiblingsNodeCount -nodeElement $nodeElement
        $hasOtherElements = Has-SiblingsNotMatchingClassName -nodeElement $nodeElement -className "android.view.ViewGroup"
        $hasNonLeafChildren = Has-ChildrenThatAreNotLeaf -nodeElement $nodeElement
        if (($hasOtherElements) -or ($hasNonLeafChildren) -or ($prevSiblingCount -ne 3)) {
            $isSlider = $false
        }
    }

    $isSlider
}

#------------------------------------------------------------------------------------
# Returns whether current node is a Slider container.
# Classification Criteria:
#   - ViewGroups with 4 child ViewGroups. No other children. All children are leaf. 
#------------------------------------------------------------------------------------
function Is-SliderContainer([System.Xml.XmlNode] $nodeElement) {
    $isSliderContainer = $true
    if (($nodeElement.NextSibling -eq $null) -or ($nodeElement.PreviousSibling -ne $null)) {
        $isSliderContainer = $false
    } else {
        $parentElement = $nodeElement.ParentNode
        $totalChildrenCount = $parentElement.ChildNodes.Count
        $childrenCount = Get-CountOfChildrenByClassName -nodeElement $parentElement -className "android.view.ViewGroup"
        $hasNonLeafChildren = Has-ChildrenThatAreNotLeaf -nodeElement $parentElement
        if (($totalChildrenCount -ne $childrenCount) -or ($hasNonLeafChildren) -or ($childrenCount -ne 4)) {
            $isSliderContainer = $false
        }
    }

    $isSliderContainer
}

#------------------------------------------------------------------------------------
# Returns whether current node is slider left delta that determines how far the slider has been moved.
# Classification Criteria:
#   - Parent ViewGropu with 4 child ViewGroups. 2 prev sibling Viewgroups. 1 next sibling ViewGroup.
#------------------------------------------------------------------------------------
function Is-SliderLeftDelta([System.Xml.XmlNode] $nodeElement) {
    $isSliderLeftDelta = $true
    if (($nodeElement.NextSibling -eq $null) -or ($nodeElement.PreviousSibling -eq $null)) {
        $isSliderLeftDelta = $false
    } else {
        $parentElement = $nodeElement.ParentNode
        $totalChildrenCount = $parentElement.ChildNodes.Count
        $prevSiblingCount = Get-PreviousSiblingsNodeCount -nodeElement $nodeElement
        $nextSiblingCount = Get-NextSiblingsNodeCount -nodeElement $nodeElement
        $childrenCount = Get-CountOfChildrenByClassName -nodeElement $parentElement -className "android.view.ViewGroup"
        $hasNonLeafChildren = Has-ChildrenThatAreNotLeaf -nodeElement $parentElement
        if (($totalChildrenCount -ne $childrenCount) -or ($hasNonLeafChildren) -or ($childrenCount -ne 4) -or ($prevSiblingCount -ne 2) -or ($nextSiblingCount -ne 1)) {
            $isSliderLeftDelta = $false
        }
    }

    $isSliderLeftDelta
}

#------------------------------------------------------------------------------------
# Returns whether current node has children that are not leaf.
#------------------------------------------------------------------------------------
function Has-ChildrenThatAreNotLeaf([System.Xml.XmlNode] $nodeElement) {
    $hasNonLeafChildren = $false;
    if ($nodeElement.HasChildNodes) {
        for ($j=0; ($j -lt $nodeElement.ChildNodes.Count) -and (-not $hasNonLeafChildren); $j++) {
            if ($nodeElement.ChildNodes[$j].HasChildNodes) {
                $hasNonLeafChildren = $true;
            }
        }    
    }

    $hasNonLeafChildren
}

#------------------------------------------------------------------------------------
# Returns whether current node has siblings that are not of specified classname.
#------------------------------------------------------------------------------------
function Has-SiblingsNotMatchingClassName([System.Xml.XmlNode] $nodeElement, [string] $className) {
    $retVal = $false;
    $currNd = $nodeElement.PreviousSibling
    while (($retVal -eq $false) -and ($currNd -ne $null)) {
        $cls = $currNd.Attributes["class"].Value
        if ($cls -ne $className) {
            $retVal = $true;
        }

        $currNd = $currNd.PreviousSibling
    }

    if (-not $retVal) {
        $currNd = $nodeElement.NextSibling
        while (($retVal -eq $false) -and ($currNd -ne $null)) {
            $cls = $currNd.Attributes["class"].Value
            if ($cls -ne $className) {
                $retVal = $true;
            }

            $currNd = $currNd.NextSibling
        }
    }

    $retVal
}

#------------------------------------------------------------------------------------
# Returns number of children of specified classname.
#------------------------------------------------------------------------------------
function Get-CountOfChildrenByClassName([System.Xml.XmlNode] $nodeElement, [string] $className) {
    $cnt = 0;
    if ($nodeElement.HasChildNodes) {
        for ($j=0; $j -lt $nodeElement.ChildNodes.Count; $j++) {
            if ($nodeElement.ChildNodes[$j].Attributes["class"].Value -eq $className) {
                $cnt++
            }
        }    
    }

    $cnt
}

#------------------------------------------------------------------------------------
# Counts number of next siblings for the specified node.
#------------------------------------------------------------------------------------
function Get-NextSiblingsNodeCount([System.Xml.XmlNode] $nodeElement) {
    $cntSiblings = 0;
    $currNd = $nodeElement.NextSibling
    while ($currNd -ne $null) {
        $cntSiblings++
        $currNd = $currNd.NextSibling
    }

    $cntSiblings
}

#------------------------------------------------------------------------------------
# Counts number of previous siblings for the specified node.
#------------------------------------------------------------------------------------
function Get-PreviousSiblingsNodeCount([System.Xml.XmlNode] $nodeElement) {
    $cntSiblings = 0;
    $currNd = $nodeElement.PreviousSibling
    while ($currNd -ne $null) {
        $cntSiblings++
        $currNd = $currNd.PreviousSibling
    }

    $cntSiblings
}

#------------------------------------------------------------------------------------
# Builds the map with elements that we need to include in screen page objects.
#------------------------------------------------------------------------------------
function Build-ElementDetectorCode([System.Xml.XmlNode] $node, [int] $level, [string] $xpath) {
    if ($node -ne $null -and $node.HasChildNodes)
    {
        Write-Host "LEVEL = $level"
        for ($i=0; $i -lt $node.ChildNodes.Count; $i++)
        {
            $childNode = $node.ChildNodes[$i]
            $nodeName = $node.ChildNodes[$i].Name
            if ($node.ChildNodes[$i].Attributes -ne $null) {
                $className = $node.ChildNodes[$i].Attributes["class"].Value
                $text = $node.ChildNodes[$i].Attributes["text"].Value
            }

            $nextLevel = $level + 1
            $xIdx = Get-ElementIndex -node $node.ChildNodes[$i] -elementClass $className -childIndex $i
            $childXpath = "$xpath/$className[$xIdx]"

            # Look only for elements of interest in page object model class.
            if ((($className -eq "android.widget.TextView") -and ($text -ne $null -and $text -ne "")) -or 
                (($className -eq "android.widget.EditText")) -or 
                (($className -eq "android.view.ViewGroup")) -or 
                (($className -eq "android.widget.Spinner" -and $node.ChildNodes[$i].HasChildNodes))) {

                $variableName = ""
                if ($className -eq "android.widget.TextView") {
                    $variableName = Build-UniqueVariableName -text $text
                } elseif (($className -eq "android.widget.EditText") -or ($className -eq "android.widget.Spinner") -or ($className -eq "android.view.ViewGroup")) {
                    if ($className -eq "android.view.ViewGroup") {
                        $varText = Find-NonEmptyParentNextSiblingText -node $node.ChildNodes[$i]
                    } else {                    
                        $varText = Find-NonEmptySiblingTextViewText -node $node.ChildNodes[$i]
                    }

                    if ($varText -eq "") {
                        if ($className -eq "android.widget.EditText") {
                            $variableName = Build-UniqueVariableName -text "mTextField"
                        } elseif ($className -eq "android.widget.Spinner") {
                            $variableName = Build-UniqueVariableName -text "mSelectBox"
                        } elseif ($className -eq "android.view.ViewGroup") {
                            $variableName = Build-UniqueVariableName -text "mSlider"
                        }
                    } else {
                        $variableName = Build-UniqueVariableName -text $varText
                    }
                }

                $isButton = ($className -eq "android.widget.TextView") -and (Is-Button -nodeElement $node.ChildNodes[$i])
                $isLabel = ($className -eq "android.widget.TextView") -and (-not $isButton)
                $isTextBox = ($className -eq "android.widget.EditText")
                $isSelectBox = ($className -eq "android.widget.Spinner") -and (Is-SelectBox -nodeElement $node.ChildNodes[$i])
                $isSlider = ($className -eq "android.view.ViewGroup") -and (Is-Slider -nodeElement $node.ChildNodes[$i])
                $isSliderContainer = ($className -eq "android.view.ViewGroup") -and (Is-SliderContainer -nodeElement $node.ChildNodes[$i])
                $isSliderLeftDelta = ($className -eq "android.view.ViewGroup") -and (Is-SliderLeftDelta -nodeElement $node.ChildNodes[$i])

                # For textViews detected as button, use xpath of the parent node.
                $elXPath = $childXpath
                if ($isButton -or $isSliderContainer) {
                    $elXPath = "$xpath"
                }

                if ($isSliderContainer) {
                    $variableName = "${variableName}Container"
                }

                if ($isSliderLeftDelta) {
                    $variableName = "${variableName}LeftDelta"
                }

                $valueObject = New-Object PSObject -Property @{                                VariableName  = $variableName                                     XPath         = $elXPath                                  IsButton      = $isButton                    IsLabel       = $isLabel                                IsTextBox     = $isTextBox                    IsSelectBox   = $isSelectBox
                    IsSlider      = $isSlider
                    IsSliderContainer = $isSliderContainer
                    IsSliderLeftDelta = $isSliderLeftDelta
                }

                # Store element types which were found.
                if ($isButton) {
                    $script:foundButton = $true
                }
                if ($isLabel) {
                    $script:foundLabel = $true
                }
                if ($isTextBox) {
                    $script:foundTextField = $true
                }
                if ($isSelectBox) {
                    $script:foundSelectBox = $true
                }
                if ($isSlider) {
                    $script:foundSlider = $true
                }
                if ($isSliderContainer) {
                    $script:foundSliderContainer = $true
                }
                if ($isSliderLeftDelta) {
                    $script:foundSliderLeftDelta = $true
                }

                Write-Host "Node=[$nodeName] -> variableName=[$variableName];isButton=[$isButton];isLabel=[$isLabel];isTextBox=[$isTextBox];isSelectBox=[$isSelectBox];isSlider=[$isSlider];class=[$className];xpath=[$childXpath];text=[$text];level=[$level]"

                $script:elementMap.set_item($variableName, $valueObject)
            }

            Build-ElementDetectorCode -node $childNode -level $nextLevel -xpath $childXpath
        }
    }
}

# Build map containing nodes of interest.
Build-ElementDetectorCode -node $root -level 0 -xpath $rootXpath

#----------------------------------------------------------
# CODE GENERATION
#----------------------------------------------------------

$OUTFILE = New-Item -type file $OutputFilePath -force

# Generate page object class.
add-content $OUTFILE "package androidapp.screens.source;"
add-content $OUTFILE ""
add-content $OUTFILE "import org.openqa.selenium.WebDriver;"
add-content $OUTFILE "import org.openqa.selenium.WebElement;"
add-content $OUTFILE "import org.openqa.selenium.support.CacheLookup;"
add-content $OUTFILE ""
add-content $OUTFILE "import common.source.Log;"
add-content $OUTFILE "import io.appium.java_client.pagefactory.AndroidFindBy;"
add-content $OUTFILE ""
add-content $OUTFILE "public class $ScreenClassName extends AndroidBaseScreen {"
add-content $OUTFILE ""

###  Button Web Elements
if ($script:foundButton) {
    add-content $OUTFILE "	/****** Button elements ******/"
    add-content $OUTFILE ""
}

$script:elementMap.Keys | sort-object | % { 
    $varName = $_
    $obj = $script:elementMap.get_item($varName)
    $mXPath = $obj.XPath    $mIsButton = $obj.IsButton    if ($mIsButton) {
        add-content $OUTFILE "	@AndroidFindBy(xpath = ""$mXPath"")"
        add-content $OUTFILE "	@CacheLookup"
        add-content $OUTFILE "	private WebElement $varName;"
        add-content $OUTFILE ""
    }
}

###  Label Elements
if ($DetectLabels) {
    if ($script:foundLabel) {
        add-content $OUTFILE "	/****** Label elements ******/"
        add-content $OUTFILE ""
    }

    $script:elementMap.Keys | sort-object | % { 
        $varName = $_
        $obj = $script:elementMap.get_item($varName)
        $mXPath = $obj.XPath        $mIsLabel = $obj.IsLabel        if ($mIsLabel) {
            add-content $OUTFILE "	@AndroidFindBy(xpath = ""$mXPath"")"
            add-content $OUTFILE "	@CacheLookup"
            add-content $OUTFILE "	private WebElement $varName;"
            add-content $OUTFILE ""
        }
    }
}

###  TextField Web Elements

if ($script:foundTextField) {
    add-content $OUTFILE "	/****** TextField elements ******/"
    add-content $OUTFILE ""
}

$script:elementMap.Keys | sort-object | % { 
    $varName = $_
    $obj = $script:elementMap.get_item($varName)
    $mXPath = $obj.XPath    $mIsTextBox = $obj.IsTextBox    if ($mIsTextBox) {
        add-content $OUTFILE "	@AndroidFindBy(xpath = ""$mXPath"")"
        add-content $OUTFILE "	@CacheLookup"
        add-content $OUTFILE "	private WebElement $varName;"
        add-content $OUTFILE ""
    }
}

###  SelectBox Web Elements

if ($script:foundSelectBox) {
    add-content $OUTFILE "	/****** SelectBox elements ******/"
    add-content $OUTFILE ""
}

$script:elementMap.Keys | sort-object | % { 
    $varName = $_
    $obj = $script:elementMap.get_item($varName)
    $mXPath = $obj.XPath    $mIsSelectBox = $obj.IsSelectBox
    if ($mIsSelectBox) {
        add-content $OUTFILE "	@AndroidFindBy(xpath = ""$mXPath"")"
        add-content $OUTFILE "	@CacheLookup"
        add-content $OUTFILE "	private WebElement $varName;"
        add-content $OUTFILE ""
    }
}

###  Slider Web Elements

if ($script:foundSlider) {
    add-content $OUTFILE "	/****** Slider elements ******/"
    add-content $OUTFILE ""
}

$script:elementMap.Keys | sort-object | % { 
    $varName = $_
    $obj = $script:elementMap.get_item($varName)
    $mXPath = $obj.XPath    $mIsSlider = $obj.IsSlider
    if ($mIsSlider) {
        add-content $OUTFILE "	@AndroidFindBy(xpath = ""$mXPath"")"
        add-content $OUTFILE "	@CacheLookup"
        add-content $OUTFILE "	private WebElement $varName;"
        add-content $OUTFILE ""
    }
}

if ($script:foundSliderContainer) {
    add-content $OUTFILE "	/****** Slider container elements ******/"
    add-content $OUTFILE ""
}

$script:elementMap.Keys | sort-object | % { 
    $varName = $_
    $obj = $script:elementMap.get_item($varName)
    $mXPath = $obj.XPath    $mIsSliderContainer = $obj.IsSliderContainer
    if ($mIsSliderContainer) {
        add-content $OUTFILE "	@AndroidFindBy(xpath = ""$mXPath"")"
        add-content $OUTFILE "	@CacheLookup"
        add-content $OUTFILE "	private WebElement $varName;"
        add-content $OUTFILE ""
    }
}

if ($script:foundSliderLeftDelta) {
    add-content $OUTFILE "	/****** Slider left delta elements ******/"
    add-content $OUTFILE ""
}

$script:elementMap.Keys | sort-object | % { 
    $varName = $_
    $obj = $script:elementMap.get_item($varName)
    $mXPath = $obj.XPath    $mIsSliderLeftDelta = $obj.IsSliderLeftDelta
    if ($mIsSliderLeftDelta) {
        add-content $OUTFILE "	@AndroidFindBy(xpath = ""$mXPath"")"
        add-content $OUTFILE "	@CacheLookup"
        add-content $OUTFILE "	private WebElement $varName;"
        add-content $OUTFILE ""
    }
}


###  Constructor

add-content $OUTFILE "	public $ScreenClassName(WebDriver driver) {"
add-content $OUTFILE "		super(driver);"
add-content $OUTFILE "	}"
add-content $OUTFILE ""

### Button - onClick()

if ($script:foundButton) {
    add-content $OUTFILE "	/****** Button Methods ******/"
    add-content $OUTFILE ""
}

$script:elementMap.Keys | sort-object | % { 
    $varName = $_
    $obj = $script:elementMap.get_item($varName)
    $mXPath = $obj.XPath    $mIsButton = $obj.IsButton    $upperVarName = To-FirstCharUpper -text $varName
    if ($mIsButton) {
        add-content $OUTFILE "	public WebElement get${upperVarName}Button() {"
        add-content $OUTFILE "		Log.method(""get${upperVarName}Button"");"
        add-content $OUTFILE "		return ${varName};"
        add-content $OUTFILE "	}"
        add-content $OUTFILE ""
        add-content $OUTFILE "	public void clickOn${upperVarName}() {"
        add-content $OUTFILE "		Log.method(""clickOn${upperVarName}"");"
        add-content $OUTFILE "		tap(get${upperVarName}Button());"
        add-content $OUTFILE "	}"
        add-content $OUTFILE ""
    }
}

### Label - getText()
if ($DetectLabels) {
    if ($script:foundLabel) {
        add-content $OUTFILE "	/****** Label Methods ******/"
        add-content $OUTFILE ""
    }

    $script:elementMap.Keys | sort-object | % { 
        $varName = $_
        $obj = $script:elementMap.get_item($varName)
        $mXPath = $obj.XPath        $mIsLabel = $obj.IsLabel        $upperVarName = To-FirstCharUpper -text $varName
        if ($mIsLabel) {
            add-content $OUTFILE "	public String get${upperVarName}Text() {"
            add-content $OUTFILE "		Log.method(""get${upperVarName}Text"");"
            add-content $OUTFILE "		return ${varName}.getText();"
            add-content $OUTFILE "	}"
            add-content $OUTFILE ""
        }
    }
}


### TextField - getText()

if ($script:foundTextField) {
    add-content $OUTFILE "	/****** TextField Methods ******/"
    add-content $OUTFILE ""
}

$script:elementMap.Keys | sort-object | % { 
    $varName = $_
    $obj = $script:elementMap.get_item($varName)
    $mXPath = $obj.XPath    $mIsTextBox = $obj.IsTextBox    $upperVarName = To-FirstCharUpper -text $varName
    if ($mIsTextBox) {
        add-content $OUTFILE "	public String get${upperVarName}Text() {"
        add-content $OUTFILE "		Log.method(""get${upperVarName}Text"");"
        add-content $OUTFILE "		return ${varName}.getText();"
        add-content $OUTFILE "	}"
        add-content $OUTFILE ""
        add-content $OUTFILE "	public void enter${upperVarName}(String value) throws Exception {"
        add-content $OUTFILE "		Log.method(""enter${upperVarName}"");"
        add-content $OUTFILE "		sendKeys(${varName}, value);"
        add-content $OUTFILE "	}"
        add-content $OUTFILE ""
    }
}

###  Slider Events

if ($script:foundSlider) {
    add-content $OUTFILE "	/****** Slider Methods ******/"
    add-content $OUTFILE ""
}

$script:elementMap.Keys | sort-object | % { 
    $varName = $_
    $obj = $script:elementMap.get_item($varName)
    $mXPath = $obj.XPath    $mIsSlider = $obj.IsSlider
    $upperVarName = To-FirstCharUpper -text $varName
    if ($mIsSlider) {
        add-content $OUTFILE "	public void slideTo${upperVarName}(Integer percValue) {"
        add-content $OUTFILE "		Log.method(""slideTo${upperVarName}"");"
        add-content $OUTFILE "		slideBy(${varName}, ${varName}Container, ${varName}LeftDelta, percValue);"
        add-content $OUTFILE "	}"
        add-content $OUTFILE ""
        add-content $OUTFILE "	public Integer get${upperVarName}SliderLocationX() {"
        add-content $OUTFILE "		Log.method(""get${upperVarName}SliderLocationX"");"
        add-content $OUTFILE "		return ${varName}.getLocation().getX();"
        add-content $OUTFILE "	}"
        add-content $OUTFILE ""
        add-content $OUTFILE "	public Integer get${upperVarName}SliderLocationY() {"
        add-content $OUTFILE "		Log.method(""get${upperVarName}SliderLocationY"");"
        add-content $OUTFILE "		return ${varName}.getLocation().getY();"
        add-content $OUTFILE "	}"
        add-content $OUTFILE ""
    }
}

###  Slider Container Events

if ($script:foundSlider) {
    add-content $OUTFILE "	/****** Slider Container Methods ******/"
    add-content $OUTFILE ""
}

$script:elementMap.Keys | sort-object | % { 
    $varName = $_
    $obj = $script:elementMap.get_item($varName)
    $mXPath = $obj.XPath    $mIsSliderContainer = $obj.IsSliderContainer
    $upperVarName = To-FirstCharUpper -text $varName
    if ($mIsSliderContainer) {
        add-content $OUTFILE "	public Integer get${upperVarName}SliderWidth() {"
        add-content $OUTFILE "		Log.method(""get${upperVarName}SliderWidth"");"
        add-content $OUTFILE "		return ${varName}.getSize().getWidth();"
        add-content $OUTFILE "	}"
        add-content $OUTFILE ""
    }
}

###  Slider Left Delta Events

if ($script:foundSlider) {
    add-content $OUTFILE "	/****** Slider Left Delta Methods ******/"
    add-content $OUTFILE ""
}

$script:elementMap.Keys | sort-object | % { 
    $varName = $_
    $obj = $script:elementMap.get_item($varName)
    $mXPath = $obj.XPath    $mIsSliderLeftDelta = $obj.IsSliderLeftDelta
    $upperVarName = To-FirstCharUpper -text $varName
    if ($mIsSliderLeftDelta) {
        add-content $OUTFILE "	public Integer get${upperVarName}Width() {"
        add-content $OUTFILE "		Log.method(""get${upperVarName}Width"");"
        add-content $OUTFILE "		return ${varName}.getSize().getWidth();"
        add-content $OUTFILE "	}"
        add-content $OUTFILE ""
    }
}

add-content $OUTFILE "	@Override"
add-content $OUTFILE "	public Boolean screenLoadCondition() {"
add-content $OUTFILE "		Log.method(""screenLoadCondition"");"
add-content $OUTFILE "		return mainFrameLayout!=null && mainFrameLayout.isDisplayed();"
add-content $OUTFILE "	}"
add-content $OUTFILE "}"

Write-Host "Successfully created file"

ii $OUTFILE 