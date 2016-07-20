package common.source;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.nocrala.tools.gis.data.esri.shapefile.ShapeFileReader;
import org.nocrala.tools.gis.data.esri.shapefile.ValidationPreferences;
import org.nocrala.tools.gis.data.esri.shapefile.exception.InvalidShapeFileException;
import org.nocrala.tools.gis.data.esri.shapefile.header.ShapeFileHeader;
import org.nocrala.tools.gis.data.esri.shapefile.shape.AbstractShape;
import org.nocrala.tools.gis.data.esri.shapefile.shape.PointData;
import org.nocrala.tools.gis.data.esri.shapefile.shape.shapes.MultiPatchShape;
import org.nocrala.tools.gis.data.esri.shapefile.shape.shapes.MultiPointMShape;
import org.nocrala.tools.gis.data.esri.shapefile.shape.shapes.MultiPointPlainShape;
import org.nocrala.tools.gis.data.esri.shapefile.shape.shapes.MultiPointZShape;
import org.nocrala.tools.gis.data.esri.shapefile.shape.shapes.PointMShape;
import org.nocrala.tools.gis.data.esri.shapefile.shape.shapes.PointShape;
import org.nocrala.tools.gis.data.esri.shapefile.shape.shapes.PointZShape;
import org.nocrala.tools.gis.data.esri.shapefile.shape.shapes.PolygonShape;
import org.nocrala.tools.gis.data.esri.shapefile.shape.shapes.PolylineShape;

/**
 * *** NOTES ***: 
 * We have integrated 2 mechanisms for comparing Shape files:
 * 1. By converting shape files to geojson and then asserting that the json created is equal.
 * 2. By integrating a ShapeFile reader and checking different properties of the geometries in the shape file.
 * 
 * When the Shape file for comparison is BIG (~ > 100MB), use Method 2.
 * For smaller shape file (< 100MB) comparison use Method 1.
 * 
 * @author spulikkal
 *
 */
public class ShapeFileUtility {

	private IShapeFileComparer shapeFileComparer;

	public ShapeFileUtility() {
		// By default use the GeoJson Shape file comparer, if not specified by the caller.
		this(getDefaultComparer());
	}

	public ShapeFileUtility(IShapeFileComparer shapeFileComparer) {
		this.shapeFileComparer = shapeFileComparer;
	}
	
	private static GeoJsonShapeFileComparer getDefaultComparer() {
		return new GeoJsonShapeFileComparer();
	}
	
	public void assertEquals(String shapeFilePath1, String shapeFilePath2) throws Exception {
		shapeFileComparer.assertEquals(shapeFilePath1, shapeFilePath2);
	}
	
	public void assertDirectoryEquals(String shapeFolder1, String shapeFolder2) throws Exception {
		Log.info(String.format("Calling assertDirectoryEquals() --> shapeFolder1 = [%s], shapeFolder2 = [%s], ", shapeFolder1, shapeFolder2));
		List<String> shpFilesInDirectory = FileUtility.getFilesInDirectory(Paths.get(shapeFolder1), "*.shp");
		for (String filePath : shpFilesInDirectory) {
			String fileName = Paths.get(filePath).getFileName().toString();
			String shapeFilePath1 = filePath;
			String shapeFilePath2 = Paths.get(shapeFolder2, fileName).toString();
			Log.info(String.format("Assert shape files are same. [%s] <==> [%s]", shapeFilePath1, shapeFilePath2));
			shapeFileComparer.assertEquals(shapeFilePath1, shapeFilePath2);
		}	
	}

	public static void main(String[] args) throws IOException, InvalidShapeFileException {
		Path shpDirectory = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data\\test-data\\shapefileutility-tests");
		List<String> shpFilesInDirectory = FileUtility.getFilesInDirectory(shpDirectory, "*.shp");
		Log.info("**********************************************************");
		Log.info(" Applying Reading Method 1: ");
		for (String filePath : shpFilesInDirectory) {
			testShapeFileReading1(filePath);	
		}

		Log.info("");
		Log.info("**********************************************************");
		Log.info(" Applying Reading Method 2: ");
		for (String filePath : shpFilesInDirectory) {
			testShapeFileReading2(filePath);	
		}
	}
	
	private static void testShapeFileReading1(String filePath) throws FileNotFoundException, InvalidShapeFileException, IOException {
		Log.info("---------------------------------------------------");
		Log.info("Reading file - " + filePath);
	    FileInputStream is = new FileInputStream(filePath);
        ShapeFileReader r = new ShapeFileReader(is);

        ShapeFileHeader h = r.getHeader();
        Log.info("The shape type of this files is " + h.getShapeType());

        int total = 0;
        AbstractShape s;
        while ((s = r.next()) != null) {
          switch (s.getShapeType()) {
          case POINT:
    	  	Log.info("Found a POINT");
	        PointShape aPoint = (PointShape) s;
	        // Do something with the point shape...
	        break;
          case POINT_M:
    	  	Log.info("Found a POINT_M");
    	  	PointMShape aPointM = (PointMShape) s;
	        // Do something with the point shape...
	        break;
          case POINT_Z:
    	  	Log.info("Found a POINT_Z");
	        PointZShape aPointZ = (PointZShape) s;
	        // Do something with the point shape...
	        break;
          case POLYLINE:
    	  	Log.info("Found a POLYLINE");
    	  	PolylineShape aPolyLine = (PolylineShape) s;
	        // Do something with the point shape...
	        break;
          case MULTIPATCH:
      	  	Log.info("Found a MULTIPATCH");
      	  MultiPatchShape aMultiPatch = (MultiPatchShape) s;
            // Do something with the MultiPointZ shape...
            break;
          case MULTIPOINT:
      	  	Log.info("Found a MULTIPOINT");
      	  	MultiPointPlainShape aMultiPoint = (MultiPointPlainShape) s;
            // Do something with the MultiPointZ shape...
            break;
          case MULTIPOINT_M:
      	  	Log.info("Found a MULTIPOINT_Z");
            MultiPointZShape aMultiPointZ = (MultiPointZShape) s;
            // Do something with the MultiPointZ shape...
            break;
          case MULTIPOINT_Z:
      	  	Log.info("Found a MULTIPOINT_Z");
      	  MultiPointMShape aMultiPointM = (MultiPointMShape) s;
            // Do something with the MultiPointZ shape...	
            break;
          case POLYGON:
            PolygonShape aPolygon = (PolygonShape) s;
            Log.info("Reading a Polygon with "
                + aPolygon.getNumberOfParts() + " parts and "
                + aPolygon.getNumberOfPoints() + " points");
            for (int i = 0; i < aPolygon.getNumberOfParts(); i++) {
              PointData[] points = aPolygon.getPointsOfPart(i);
              Log.info("- part " + i + " has " + points.length
                  + " points.");
            }
            break;
          default:
            Log.info("Read other type of shape.");
          }
          total++;
        }

        Log.info("Total shapes read: " + total);
        is.close();	
    }

	private static void testShapeFileReading2(String filePath) throws FileNotFoundException, InvalidShapeFileException, IOException {
		Log.info("---------------------------------------------------");
		Log.info("Reading file - " + filePath);
		FileInputStream is = new FileInputStream(filePath);
		
		// This file has shapes with more than 10000 points each. Therefore, we need
		// to change the validation preferences to increase the limit of points per
		// shape beyond that number. If we don't use the customized preferences, the
		// reader will throw an InvalidShapeFileException.
		ValidationPreferences prefs = new ValidationPreferences();
		prefs.setMaxNumberOfPointsPerShape(16650);
		ShapeFileReader r = new ShapeFileReader(is, prefs);
	
		ShapeFileHeader h = r.getHeader();
		Log.info("The shape type of this files is " + h.getShapeType());
	
		int total = 0;
		AbstractShape s;
		while ((s = r.next()) != null) {
	
		  switch (s.getShapeType()) {
		  case POLYLINE:
		    PolylineShape aPolyline = (PolylineShape) s;
		    Log.info("Reading a Polyline with "
		        + aPolyline.getNumberOfParts() + " parts and "
		        + aPolyline.getNumberOfPoints() + " points");
		    for (int i = 0; i < aPolyline.getNumberOfParts(); i++) {
		      PointData[] points = aPolyline.getPointsOfPart(i);
		      Log.info("- part " + i + " has " + points.length
		          + " points.");
		    }
		    break;
		  default:
		    Log.info("Read other type of shape.");
		  }
		  total++;
		}
	
		Log.info("Total shapes read: " + total);
	
		is.close();
	}
}
