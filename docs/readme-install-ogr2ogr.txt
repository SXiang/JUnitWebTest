/*****************************************************
 * Installation instructions for ogr2ogr
 ****************************************************/

'ogr2ogr' can be installed on Windows machine by installing gdal. 

Follow these STEPS to install gdal on Windows:
	Reference: <http://cartometric.com/blog/2011/10/17/install-gdal-on-windows/> 
	
	a. Install Python 2.7 (32-bit) from https://www.python.org/download/releases/2.7/ using X86 MSI installer. Then  from Python shell note down these values:
		· “MSC v.1500” -- Your value could be different. Note it down. You'll use the corresponding installer in step b) i. and ii.  
		· “on win32”
	b. From http://www.gisinternals.com/release.php :
		i. Download and Install: http://download.gisinternals.com/sdk/downloads/release-1500-gdal-1-11-3-mapserver-6-4-2/gdal-111-1500-core.msi   -- Match '1500' value from step a)
		ii. Next download and install MSI for matching python version:
		http://download.gisinternals.com/sdk/downloads/release-1500-gdal-1-11-3-mapserver-6-4-2/GDAL-1.11.3.win32-py2.7.msi
	c. Add to PATH environment variable the following path:
		i. c:\Program Files (x86)\GDAL
	d. Create two new System Environment variables:
		i. GDAL_DATA and set value to:
			C:\Program Files (x86)\GDAL\gdal-data
		ii. GDAL_DRIVER_PATH and set value to:
			C:\Program Files (x86)\GDAL\gdalplugins

---------------
Once you have successfully installed 'ogr2ogr' should be detected on a command prompt.

To convert .shp to .geojson you can use this command:
 "ogr2ogr -f GeoJSON -t_srs crs:84 [name].geojson [name].shp"
