function Import-Excel {
    <#
    .SYNOPSIS
        Read the entries from specified sheet in excel file and return imported entries in CSV format.

    .DESCRIPTION
        Read the entries from specified sheet in excel file and return imported entries in CSV format.
        - Internally this method will create a .csv for data within the excelsheet and call Import-CSV on it.

    .PARAMETER FilePath
        Path of the excel file.

    .PARAMETER SheetName
        Name of the Sheet within the excel file.

    #>
    Param
    (
        [string]$FilePath, 
        [string]$SheetName = ""
    )

    $csvFile = Join-Path $env:temp ("{0}.csv" -f (Get-Item -path $FilePath).BaseName)
    if (Test-Path -path $csvFile) { Remove-Item -path $csvFile }
    # convert Excel file to CSV file
    $xlCSVType = 6 # SEE: http://msdn.microsoft.com/en-us/library/bb241279.aspx
    $excelObject = New-Object -ComObject Excel.Application 
    $excelObject.Visible = $false
    $workbookObject = $excelObject.Workbooks.Open($FilePath)
    Set-ActiveSheet $workbookObject $SheetName | Out-Null
    $workbookObject.SaveAs($csvFile,$xlCSVType)
    $workbookObject.Saved = $true
    $workbookObject.Close()
 
    # Cleanup
    Cleanup-Workbook $workbookObject $excelObject

    # now import and return the data
    Import-Csv -path $csvFile
}

function Cleanup-Workbook {
    <#
    .SYNOPSIS
        Cleanup workbook object.

    .DESCRIPTION
        Cleanup excel workbook object. Quit excel. Release com object and invoke garbage collection.

    .PARAMETER workbook
        Workbook object

    .PARAMETER excelObject
        Excel object

    #>
    Param
    (
        $workbook, 
        $excelObject
    )

    # cleanup
    [System.Runtime.Interopservices.Marshal]::ReleaseComObject($workbook) | Out-Null
    $excelObject.Quit()
    [System.Runtime.Interopservices.Marshal]::ReleaseComObject($excelObject) | Out-Null
    [System.GC]::Collect()
    [System.GC]::WaitForPendingFinalizers()
}

function Find-Sheet {
    <#
    .SYNOPSIS
        Return index of specified sheet in excel by sheetname.

    .DESCRIPTION
        Returns the sheet number index of the specified sheet in excel by sheetname.

    .PARAMETER workbook
        Workbook object

    .PARAMETER SecondParameter
        Sheet name

    #>
    Param
    (
        [Object]$workbook, 
        [string]$name
    )

    $sheetNumber = 0
    for ($i=1; $i -le $workbook.Sheets.Count; $i++) {
        if ($name -eq $workbook.Sheets.Item($i).Name) { $sheetNumber = $i; break }
    }
    return $sheetNumber
}

function Set-ActiveSheet {
    <#
    .SYNOPSIS
        Sets the specified sheet as active sheet in Excel.

    .DESCRIPTION
        Sets the specified sheet as active sheet in Excel.

    .PARAMETER workbook
        Workbook object.

    .PARAMETER name
        Name of sheet to be set as Active.

    #>
    Param
    (
        [Object]$workbook, 
        [string]$name
    )

    if (!$name) { return }
    $sheetNumber = Find-Sheet $workbook $name
    if ($sheetNumber -gt 0) { $workbook.Worksheets.Item($sheetNumber).Activate() }
    return ($sheetNumber -gt 0)
}

function Invoke {
    <#
    .SYNOPSIS
        Invokes the specified method using the specified parameters.

    .DESCRIPTION
        Invokes the specified method using the specified parameters.
        - Sets current culture to en-US before invoking the method. 

    .PARAMETER m
        Object to invoke method on.

    .PARAMETER SecondParameter
        Method to invoke.

    .PARAMETER SecondParameter
        Parameters for the method to invoke.

    #>
    Param
    (
        [object]$m, 
        [string]$method, 
        $parameters
    )

    [System.Threading.Thread]::CurrentThread.CurrentCulture = New-Object "System.Globalization.CultureInfo" "en-US"
    $ciUS = [System.Globalization.CultureInfo]'en-US'
    [system.threading.Thread]::CurrentThread.CurrentCulture = $ciUS
    $m.GetType().InvokeMember($method, [Reflection.BindingFlags]::InvokeMethod, $null, $m, $parameters,$ciUS)
}

function Get-Workbook {
    <#
    .SYNOPSIS
        Returns the excel COM object and workbook object.

    .DESCRIPTION
        Returns the excel COM object and workbook object for the specified excel file.

    .PARAMETER FilePath
        Path of excel file.

    #>
    Param
    (
        [string]$FilePath
    )

    $excelObject = New-Object -ComObject Excel.Application 
    $excelObject.Visible = $false
    $workbookObject = Invoke -m $excelObject.Workbooks -method "Open" -parameters $FilePath
    return $excelObject,$workbookObject;
}

function Get-SheetCount {
    <#
    .SYNOPSIS
        Returns the number of sheets in the excel workbook.

    .DESCRIPTION
        Returns the number of sheets in the excel workbook.

    .PARAMETER workbook
        Workbook object.

    #>
    Param
    (
        [Object]$workbook
    )

    return $workbook.Sheets.Count;
}

function Get-SheetName {
    <#
    .SYNOPSIS
        Gets the name of the sheet at the specified index.

    .DESCRIPTION
        Gets the name of the sheet at the specified index from the excel workbook.

    .PARAMETER workbook
        Workbook object.

    .PARAMETER sheetNumber
        Index of the sheet.

    #>
    Param
    (
        [Object]$workbook, 
        $sheetNumber
    )

    return $workbook.Sheets.Item($sheetNumber).Name;
}

function Set-RowColor {
    <#
    .SYNOPSIS
        Sets the backcolor for the specified row in excel workbook.

    .DESCRIPTION
        Sets the backcolor for the specified row in excel workbook.

    .PARAMETER workbookObject
        Workbook object

    .PARAMETER rowIdx
        Row index

    .PARAMETER backColorIdx
        Cell interior color index

    #>
    Param
    (
        [Object]$workbookObject, 
        $rowIdx, 
        $backColorIdx
    )

    if ($backColorIdx -ne -1) {
        $workbookObject.ActiveSheet.Range("A$rowIdx`:Z$rowIdx").Interior.ColorIndex = $backColorIdx
    }
}

function Set-CellColor {
    <#
    .SYNOPSIS
        Sets the backcolor and forecolor for a specified cell.

    .DESCRIPTION
        Sets the backcolor and forecolor for a cell specified by row index and column index.

    .PARAMETER workbookObject
        Workbook object

    .PARAMETER rowIdx
        Row index of the cell

    .PARAMETER colIdx
        Column index of the cell

    .PARAMETER backColorIdx
        Cell interior color index

    .PARAMETER foreColorIdx
        Cell font color index

    #>
    Param
    (
        [Object]$workbookObject, 
        $rowIdx, 
        $colIdx, 
        $backColorIdx, 
        $foreColorIdx
    )

    $cl = $workbookObject.ActiveSheet.Cells.Item($rowIdx,$colIdx)
    if ($cl -ne $null) {
        if ($backColorIdx -ne -1) {
           $cl.Interior.ColorIndex = $backColorIdx
        }
        if ($foreColorIdx -ne -1) {
           $cl.Font.ColorIndex = $foreColorIdx
           $cl.Font.Bold=$True
        }
    }
}

function SaveAndCleanup-Workbook {
    <#
    .SYNOPSIS
        Save, close and cleanup the workbook object.

    .DESCRIPTION
        Save, close and cleanup the workbook object.

    .PARAMETER workbookObject
        Workbook object

    .PARAMETER excelObject
        Excel object

    #>
    Param
    (
        $workbookObject, 
        $excelObject
    )

    Invoke -m $workbookObject -method "Save" > $null 
    Invoke -m $workbookObject -method Close > $null 
    Cleanup-Workbook -workbook $workbookObject -excel $excelObject
}