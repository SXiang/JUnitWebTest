REM 'ogr2ogr' is a pre-requisite for running this command. Follow instructions from .\surveyor-qa\docs\readme-install-ogr2ogr.txt for installing 'ogr2ogr'
REM Parameters in this file should be passed from command line
REM    %1 - Full path to the output .geojson file
REM    %2 - Full path to the input .shp file
ogr2ogr -f GeoJSON -t_srs crs:84 %1 %2