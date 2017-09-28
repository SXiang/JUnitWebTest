$ALLOWED_INI_CH_REGEX = "[a-zA-Z0-9_-]"

# Returns values from .ini files as a map of map
function Read-IniFile($iniFile) {
    $iniMap = New-Object System.Collections.Hashtable

    switch -regex -File $iniFile
    { 
        "\[($ALLOWED_INI_CH_REGEX+)\]" { 
            $header = $Matches[1]
        } 
        
        "($ALLOWED_INI_CH_REGEX+)\s*=\s*(.+)" {
            $key, $value = $Matches[1..2]
            if (-not $iniMap.ContainsKey($header)) {
                $newMap = New-Object System.Collections.Hashtable
                $null = $newMap.Add($key, $value)
                $null = $iniMap.Add($header, $newMap)
            } else {
                [System.Collections.Hashtable] $map = [System.Collections.Hashtable]$iniMap.get_item($header)
                $null = $map.set_item($key, $value)
                $iniMap.set_item($header, $map)
            }
        } 
    }

    $iniMap
}
