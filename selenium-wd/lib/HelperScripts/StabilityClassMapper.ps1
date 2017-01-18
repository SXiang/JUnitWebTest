# ------------------------------------------------------------------------
# NOTE: Keep this Map in-sync with 'StabilityClass' enum defined in product
#       at '\surveyor\Src\Core\Picarro.Surveyor.Core\Constants\Enums.cs'
# ------------------------------------------------------------------------
$stabilityClassMap = @{
    "0"="A"
    "1"="B"
    "2"="C"
    "3"="D"
    "4"="E"
    "5"="F"
}

function MapTo-AnalyzerSurveyStabilityClass {
    param (
        [String] $surveyStabilityClass
    )

    $stabilityClassMap.get_item($surveyStabilityClass)
}