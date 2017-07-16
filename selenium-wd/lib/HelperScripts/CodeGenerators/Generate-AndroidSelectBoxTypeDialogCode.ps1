<#------------------------------------------------------------------------------------------------
DESCRIPTION:
 - Use this script to create Typed ListControl java class code for given list types.
------------------------------------------------------------------------------------------------#>

param(
   [Parameter(Mandatory=$true)]
   [string]$OutputFilePath,                # eg. "C:\temp\SurfaceOverLeakType.java"

   [Parameter(Mandatory=$true)]
   [string]$InputFileWithListItems,        # eg. "C:\temp\listItems.txt"

   [Parameter(Mandatory=$true)]
   [string]$TypeName                       # eg. "SurfaceOverLeakType"
)

. "C:\Repositories\surveyor-qa\selenium-wd\lib\HelperScripts\CommonHelpers.ps1"

$OUTFILE = New-Item -type file "$OutputFilePath" -force

add-content $OUTFILE "package androidapp.screens.source;"
add-content $OUTFILE ""
add-content $OUTFILE "import org.openqa.selenium.WebDriver;"
add-content $OUTFILE "import org.openqa.selenium.WebElement;"
add-content $OUTFILE ""
add-content $OUTFILE "import common.source.Log;"
add-content $OUTFILE "import common.source.TestContext;"
add-content $OUTFILE "import io.appium.java_client.pagefactory.AndroidFindBy;"
add-content $OUTFILE ""
add-content $OUTFILE "public class Android${TypeName}ListControl extends AndroidBaseScreen {"

$lineCount = 0
Get-Content $InputFileWithListItems | % {
    $lineCount++
    $type = $_
    $varName = To-FirstCharLower -text $type
    $varName = $varName.Replace("-", "").replace(".", "_").replace(" ", "").replace("%", "_")
    add-content $OUTFILE ""
    add-content $OUTFILE "	@AndroidFindBy(uiAutomator = ""new UiSelector().text(\""$type\"")"")"
    add-content $OUTFILE "	private WebElement ${varName}ListItem;"
}

add-content $OUTFILE ""
add-content $OUTFILE "	public Android${TypeName}ListControl(WebDriver driver) {"
add-content $OUTFILE "		super(driver);"
add-content $OUTFILE "	}"
add-content $OUTFILE ""

add-content $OUTFILE "	public enum ${TypeName} {"
$i=0
Get-Content $InputFileWithListItems | % {
    $i++
    $type = $_    
    $enumName = $type.Replace("-", "").replace(".", "_").replace(" ", "").replace("%", "Pct")
    if ($i -lt $lineCount) {
        $type = "		" + $enumName + " (""$type""),"
    } else {
        $type = "		" + $enumName + " (""$type"");"
    }

    add-content $OUTFILE $type
}
add-content $OUTFILE "		private final String name;";
add-content $OUTFILE "";
add-content $OUTFILE "		${TypeName}(String nm) {";
add-content $OUTFILE "			name = nm;";
add-content $OUTFILE "		}";
add-content $OUTFILE "		";
add-content $OUTFILE "		public String toString() {";
add-content $OUTFILE "			return this.name;";
add-content $OUTFILE "		}";
add-content $OUTFILE "	}";

$argName = To-FirstCharLower -text $TypeName

add-content $OUTFILE ""
add-content $OUTFILE "	public void select${TypeName}(${TypeName} ${argName}) {"
add-content $OUTFILE "		Log.method(""select${TypeName}"", ${argName}.toString());"

$i=0
Get-Content $InputFileWithListItems | % {
    $i++
    $type = $_    
    $enumName = $type.Replace("-", "").replace(".", "_").replace(" ", "").replace("%", "Pct")
    $varName = To-FirstCharLower -text $type
    $varName = $varName.Replace("-", "").replace(".", "_").replace(" ", "").replace("%", "_")
    if ($i -eq 1) {
        add-content $OUTFILE "		if (${argName} == ${TypeName}.${enumName}) {"
        add-content $OUTFILE "			${varName}ListItem.click();"
    } else {
        add-content $OUTFILE "		} else if (${argName} == ${TypeName}.${enumName}) {"
        add-content $OUTFILE "			${varName}ListItem.click();"
    }

    if ($i -eq $lineCount) {
        add-content $OUTFILE "		}"
    }
}

add-content $OUTFILE ""
add-content $OUTFILE "		// allow time for dropdown value to get selected on screen."
add-content $OUTFILE "		TestContext.INSTANCE.stayIdle(2);"
add-content $OUTFILE "	}"
add-content $OUTFILE "}"

ii $OUTFILE