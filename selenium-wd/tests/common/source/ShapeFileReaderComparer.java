package common.source;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.nocrala.tools.gis.data.esri.shapefile.ShapeFileReader;
import org.nocrala.tools.gis.data.esri.shapefile.header.ShapeFileHeader;
import org.nocrala.tools.gis.data.esri.shapefile.shape.AbstractShape;
import org.nocrala.tools.gis.data.esri.shapefile.shape.PointData;
import org.nocrala.tools.gis.data.esri.shapefile.shape.ShapeType;
import org.nocrala.tools.gis.data.esri.shapefile.shape.shapes.MultiPatchShape;
import org.nocrala.tools.gis.data.esri.shapefile.shape.shapes.MultiPointMShape;
import org.nocrala.tools.gis.data.esri.shapefile.shape.shapes.MultiPointPlainShape;
import org.nocrala.tools.gis.data.esri.shapefile.shape.shapes.MultiPointZShape;
import org.nocrala.tools.gis.data.esri.shapefile.shape.shapes.PointMShape;
import org.nocrala.tools.gis.data.esri.shapefile.shape.shapes.PointShape;
import org.nocrala.tools.gis.data.esri.shapefile.shape.shapes.PointZShape;
import org.nocrala.tools.gis.data.esri.shapefile.shape.shapes.PolygonMShape;
import org.nocrala.tools.gis.data.esri.shapefile.shape.shapes.PolygonShape;
import org.nocrala.tools.gis.data.esri.shapefile.shape.shapes.PolygonZShape;
import org.nocrala.tools.gis.data.esri.shapefile.shape.shapes.PolylineMShape;
import org.nocrala.tools.gis.data.esri.shapefile.shape.shapes.PolylineShape;
import org.nocrala.tools.gis.data.esri.shapefile.shape.shapes.PolylineZShape;

public class ShapeFileReaderComparer implements IShapeFileComparer {

	// NOTE: Turn DEBUG On to print debug statements for shape files comparison.
	private static boolean DEBUG = true;

	public ShapeFileReaderComparer() {
	}

	private void debugLog(String log) {
		if (DEBUG) {
			Log.info(log);
		}
	}
	
	/**
	 * Checks that the 2 specified shape files have the same shapeTypes
	 * and for each shapeType checks that it has the same properties.
	 */
	@Override
	public void assertEquals(String file1Path, String file2Path) throws Exception {
		FileInputStream fileStream1 = new FileInputStream(file1Path); FileInputStream fileStream2 = new FileInputStream(file2Path);
        ShapeFileReader shapeReader1 = new ShapeFileReader(fileStream1); ShapeFileReader shapeReader2 = new ShapeFileReader(fileStream2);
        ShapeFileHeader fileHeader1 = shapeReader1.getHeader(); ShapeFileHeader fileHeader2 = shapeReader2.getHeader();

        debugLog("[FILE-1]: The shape type of this files is " + fileHeader1.getShapeType());
        debugLog("[FILE-2]: The shape type of this files is " + fileHeader2.getShapeType());

        int total = 0;
        AbstractShape shape1 = null, shape2 = null;
        while ((shape1 = shapeReader1.next()) != null && (shape2 = shapeReader2.next()) != null) {
        	total++;
        	debugLog(String.format("[FILE-1,2]: Checking Shape[%d]", total));
        	ShapeType shapeType1 = shape1.getShapeType();
        	ShapeType shapeType2 = shape2.getShapeType();
        	if (shapeType1 != shapeType2) {
        		throwAssertFailure("FAILURE: Found ShapeTypes that are different!");
        	}

        	// ShapeTypes of both files are the same. 
            switch (shape1.getShapeType()) {
            case POINT:
	      	  	debugLog("[FILE-1,2]: Found a POINT");
	  	        PointShape aPoint1 = (PointShape) shape1;
	  	        PointShape aPoint2 = (PointShape) shape2;
	  	        assertPointShapeEquals(aPoint1, aPoint2);
	  	        break;
            case POINT_M:
	      	  	debugLog("[FILE-1,2]: Found a POINT_M");
	      	  	PointMShape aPointM1 = (PointMShape) shape1;
	      	  	PointMShape aPointM2 = (PointMShape) shape2;
	      	  	assertPointMShapeEquals(aPointM1, aPointM2);
	      	  	break;
            case POINT_Z:
	      	  	debugLog("[FILE-1,2]: Found a POINT_Z");
	  	        PointZShape aPointZ1 = (PointZShape) shape1;
	  	        PointZShape aPointZ2 = (PointZShape) shape2;
	  	        assertPointZShapeEquals(aPointZ1, aPointZ2);
	  	        break;
            case POLYLINE:
	      	  	debugLog("[FILE-1,2]: Found a POLYLINE");
	      	  	PolylineShape aPolyLine1 = (PolylineShape) shape1;
	      	  	PolylineShape aPolyLine2 = (PolylineShape) shape2;
	      	  	assertPolylineShapeEquals(aPolyLine1, aPolyLine2);
	  	        break;
            case POLYLINE_M:
	      	  	debugLog("[FILE-1,2]: Found a POLYLINE_M");
      	  		PolylineMShape aPolyLineM1 = (PolylineMShape) shape1;
      	  		PolylineMShape aPolyLineM2 = (PolylineMShape) shape2;
	      	  	assertPolylineMShapeEquals(aPolyLineM1, aPolyLineM2);
	  	        break;
            case POLYLINE_Z:
	      	  	debugLog("[FILE-1,2]: Found a POLYLINE_Z");
				PolylineZShape aPolyLineZ1 = (PolylineZShape) shape1;
				PolylineZShape aPolyLineZ2 = (PolylineZShape) shape2;
	      	  	assertPolylineZShapeEquals(aPolyLineZ1, aPolyLineZ2);
	  	        break;
            case MULTIPATCH:
				debugLog("[FILE-1,2]: Found a MULTIPATCH");
				MultiPatchShape aMultiPatch1 = (MultiPatchShape) shape1;
				MultiPatchShape aMultiPatch2 = (MultiPatchShape) shape2;
	      	  	assertMultiPatchShapeEquals(aMultiPatch1, aMultiPatch2);
				break;
            case MULTIPOINT:
				debugLog("[FILE-1,2]: Found a MULTIPOINT");
				MultiPointPlainShape aMultiPoint1 = (MultiPointPlainShape) shape1;
				MultiPointPlainShape aMultiPoint2 = (MultiPointPlainShape) shape2;
				assertMultiPointPlaneShapeEquals(aMultiPoint1, aMultiPoint2);
              break;
            case MULTIPOINT_M:
        	  	debugLog("[FILE-1,2]: Found a MULTIPOINT_M");
        	  	MultiPointZShape aMultiPointZ1 = (MultiPointZShape) shape1;
        	  	MultiPointZShape aMultiPointZ2 = (MultiPointZShape) shape2;
        	  	assertMultiPointZShapeEquals(aMultiPointZ1, aMultiPointZ2);
              break;
            case MULTIPOINT_Z:
        	  	debugLog("[FILE-1,2]: Found a MULTIPOINT_Z");
        	  	MultiPointMShape aMultiPointM1 = (MultiPointMShape) shape1;
        	  	MultiPointMShape aMultiPointM2 = (MultiPointMShape) shape2;
        	  	assertMultiPointMShapeEquals(aMultiPointM1, aMultiPointM2);
              break;
            case POLYGON:
        		debugLog("[FILE-1,2]: Found a POLYGON");
    			PolygonShape aPolygon1 = (PolygonShape) shape1;
    			PolygonShape aPolygon2 = (PolygonShape) shape2;
    			assertPolygonShapeEquals(aPolygon1, aPolygon2);
				break;
            case POLYGON_M:
        		debugLog("[FILE-1,2]: Found a POLYGON_M");
        		PolygonMShape aPolygonM1 = (PolygonMShape) shape1;
        		PolygonMShape aPolygonM2 = (PolygonMShape) shape2;
    			assertPolygonMShapeEquals(aPolygonM1, aPolygonM2);
				break;
            case POLYGON_Z:
        		debugLog("[FILE-1,2]: Found a POLYGON_Z");
        		PolygonZShape aPolygonZ1 = (PolygonZShape) shape1;
        		PolygonZShape aPolygonZ2 = (PolygonZShape) shape2;
    			assertPolygonZShapeEquals(aPolygonZ1, aPolygonZ2);
				break;
            default:
              Log.error("ERROR: Read other type of shape.");
            }
        }
        	
        if ((shape1 = shapeReader1.next()) != null) {
    		throwAssertFailure("FAILURE: Found more shapes in File1 than File2!");
        }
        if ((shape2 = shapeReader2.next()) != null) {
    		throwAssertFailure("FAILURE: Found more shapes in File2 than File1!");
        }
        
        debugLog("[FILE-1,2]: Total shapes read: " + total);
        fileStream1.close();
	}

	private void assertPolygonShapeEquals(PolygonShape aPolygon1, PolygonShape aPolygon2) throws Exception {
		debugLog("[FILE-1,2]: Checking Polygon");
		if (aPolygon1 == null && aPolygon2 != null ) {
			throwAssertFailure("FAILURE (assertPolygonShapeEquals): PolygonShape1 NULL. PolygonShape2 Not NULL!");
		}
		if (aPolygon1 != null && aPolygon2 == null ) {
			throwAssertFailure("FAILURE (assertPolygonShapeEquals): PolygonShape2 NULL. PolygonShape1 Not NULL!");
		}		
		debugLog(String.format("[FILE-1]: PolygonShape (NumPoints=%s,NumParts=%s)",
				aPolygon1.getNumberOfPoints(),aPolygon1.getNumberOfParts()));
		debugLog(String.format("[FILE-2]: PolygonShape (NumPoints=%s,NumParts=%s)",
				aPolygon1.getNumberOfPoints(),aPolygon2.getNumberOfParts()));

		if (aPolygon1.getNumberOfParts() != aPolygon2.getNumberOfParts()) {
			throwAssertFailure("FAILURE (assertPolygonShapeEquals): Found NumberOfParts that are different!");
		}
		if (aPolygon1.getNumberOfPoints() != aPolygon2.getNumberOfPoints()) {
			throwAssertFailure("FAILURE (assertPolygonShapeEquals): Found NumberOfPoints that are different!");
		}

		debugLog("[FILE-1]: MinX=" + aPolygon1.getBoxMinX() + ",MinY=" + aPolygon1.getBoxMinY() + 
				",MaxX=" + aPolygon1.getBoxMaxX() + ",MaxY=" + aPolygon1.getBoxMaxY());
		debugLog("[FILE-2]: MinX=" + aPolygon2.getBoxMinX() + ",MinY=" + aPolygon2.getBoxMinY() + 
				",MaxX=" + aPolygon2.getBoxMaxX() + ",MaxY=" + aPolygon2.getBoxMaxY());
		if ((aPolygon1.getBoxMinX() != aPolygon2.getBoxMinX()) 
				|| (aPolygon1.getBoxMinY() != aPolygon2.getBoxMinY())
				|| (aPolygon1.getBoxMaxX() != aPolygon2.getBoxMaxX())
				|| (aPolygon1.getBoxMaxY() != aPolygon2.getBoxMaxY())) {
			throwAssertFailure("FAILURE (assertPolygonShapeEquals): Found BoxMin/Max that are different!");
		}
		
		// Number of parts and number of points are the SAME.
		for (int i = 0; i < aPolygon1.getNumberOfParts(); i++) {
			PointData[] points1 = aPolygon1.getPointsOfPart(i);
			PointData[] points2 = aPolygon2.getPointsOfPart(i);
			debugLog("[FILE-1]: Part " + i + " has " + points1.length + " points.");
			debugLog("[FILE-2]: Part " + i + " has " + points2.length + " points.");
			assertPointDataArrayEquals(points1, points2);
		}
	}

	private void assertPolygonMShapeEquals(PolygonMShape aPolygonM1, PolygonMShape aPolygonM2) throws Exception {
		debugLog("[FILE-1,2]: Checking PolygonM");
		if (aPolygonM1 == null && aPolygonM2 != null ) {
			throwAssertFailure("FAILURE (assertPolygonMShapeEquals): PolygonMShape1 NULL. PolygonMShape2 Not NULL!");
		}
		if (aPolygonM1 != null && aPolygonM2 == null ) {
			throwAssertFailure("FAILURE (assertPolygonMShapeEquals): PolygonMShape2 NULL. PolygonMShape1 Not NULL!");
		}		
		debugLog(String.format("[FILE-1]: PolygonMShape (NumPoints=%s,NumParts=%s)",
				aPolygonM1.getNumberOfPoints(),aPolygonM1.getNumberOfParts()));
		debugLog(String.format("[FILE-2]: PolygonMShape (NumPoints=%s,NumParts=%s)",
				aPolygonM1.getNumberOfPoints(),aPolygonM2.getNumberOfParts()));

		if (aPolygonM1.getNumberOfParts() != aPolygonM2.getNumberOfParts()) {
			throwAssertFailure("FAILURE (assertPolygonMShapeEquals): Found NumberOfParts that are different!");
		}
		if (aPolygonM1.getNumberOfPoints() != aPolygonM2.getNumberOfPoints()) {
			throwAssertFailure("FAILURE (assertPolygonMShapeEquals): Found NumberOfPoints that are different!");
		}

		debugLog("[FILE-1]: MinM=" + aPolygonM1.getMinM() + ",MaxM=" + aPolygonM1.getMaxM());
		debugLog("[FILE-2]: MinM=" + aPolygonM2.getMinM() + ",MaxM=" + aPolygonM2.getMaxM());
		if ((aPolygonM1.getMinM() != aPolygonM2.getMinM()) 
				|| (aPolygonM1.getMaxM() != aPolygonM2.getMaxM())) {
			throwAssertFailure("FAILURE (assertPolygonMShapeEquals): Found MinM/MaxM that are different!");
		}

		debugLog("[FILE-1]: MinX=" + aPolygonM1.getBoxMinX() + ",MinY=" + aPolygonM1.getBoxMinY() + 
				",MaxX=" + aPolygonM1.getBoxMaxX() + ",MaxY=" + aPolygonM1.getBoxMaxY());
		debugLog("[FILE-2]: MinX=" + aPolygonM2.getBoxMinX() + ",MinY=" + aPolygonM2.getBoxMinY() + 
				",MaxX=" + aPolygonM2.getBoxMaxX() + ",MaxY=" + aPolygonM2.getBoxMaxY());
		if ((aPolygonM1.getBoxMinX() != aPolygonM2.getBoxMinX()) 
				|| (aPolygonM1.getBoxMinY() != aPolygonM2.getBoxMinY())
				|| (aPolygonM1.getBoxMaxX() != aPolygonM2.getBoxMaxX())
				|| (aPolygonM1.getBoxMaxY() != aPolygonM2.getBoxMaxY())) {
			throwAssertFailure("FAILURE (assertPolygonMShapeEquals): Found BoxMin/Max that are different!");
		}
		
		// Number of parts and number of points are the SAME.
		for (int i = 0; i < aPolygonM1.getNumberOfParts(); i++) {
			PointData[] points1 = aPolygonM1.getPointsOfPart(i);
			PointData[] points2 = aPolygonM2.getPointsOfPart(i);
			debugLog("[FILE-1]: Part " + i + " has " + points1.length + " points.");
			debugLog("[FILE-2]: Part " + i + " has " + points2.length + " points.");
			assertPointDataArrayEquals(points1, points2);
		}
	}

	private void assertPolygonZShapeEquals(PolygonZShape aPolygonZ1, PolygonZShape aPolygonZ2) throws Exception {
		debugLog("[FILE-1,2]: Checking PolygonZ");
		if (aPolygonZ1 == null && aPolygonZ2 != null ) {
			throwAssertFailure("FAILURE (assertPolygonZShapeEquals): PolygonZShape1 NULL. PolygonZShape2 Not NULL!");
		}
		if (aPolygonZ1 != null && aPolygonZ2 == null ) {
			throwAssertFailure("FAILURE (assertPolygonZShapeEquals): PolygonZShape2 NULL. PolygonZShape1 Not NULL!");
		}		
		debugLog(String.format("[FILE-1]: PolygonZShape (NumPoints=%s,NumParts=%s)",
				aPolygonZ1.getNumberOfPoints(),aPolygonZ1.getNumberOfParts()));
		debugLog(String.format("[FILE-2]: PolygonZShape (NumPoints=%s,NumParts=%s)",
				aPolygonZ1.getNumberOfPoints(),aPolygonZ2.getNumberOfParts()));

		if (aPolygonZ1.getNumberOfParts() != aPolygonZ2.getNumberOfParts()) {
			throwAssertFailure("FAILURE (assertPolygonZShapeEquals): Found NumberOfParts that are different!");
		}
		if (aPolygonZ1.getNumberOfPoints() != aPolygonZ2.getNumberOfPoints()) {
			throwAssertFailure("FAILURE (assertPolygonZShapeEquals): Found NumberOfPoints that are different!");
		}

		debugLog("[FILE-1]: MinM=" + aPolygonZ1.getMinM() + ",MaxM=" + aPolygonZ1.getMaxM() + 
				"MinZ=" + aPolygonZ1.getMinZ() + ",MaxZ=" + aPolygonZ1.getMaxZ());
		debugLog("[FILE-2]: MinM=" + aPolygonZ2.getMinM() + ",MaxM=" + aPolygonZ2.getMaxM() +
				"MinZ=" + aPolygonZ2.getMinZ() + ",MaxZ=" + aPolygonZ2.getMaxZ());
		if ((aPolygonZ1.getMinM() != aPolygonZ2.getMinM()) 
				|| (aPolygonZ1.getMaxM() != aPolygonZ2.getMaxM())
				|| (aPolygonZ1.getMinZ() != aPolygonZ2.getMinZ())
				|| (aPolygonZ1.getMaxZ() != aPolygonZ2.getMaxZ())) {
			throwAssertFailure("FAILURE (assertPolygonZShapeEquals): Found MinM/MaxM, MinZ/MaxZ that are different!");
		}

		debugLog("[FILE-1]: MinX=" + aPolygonZ1.getBoxMinX() + ",MinY=" + aPolygonZ1.getBoxMinY() + 
				",MaxX=" + aPolygonZ1.getBoxMaxX() + ",MaxY=" + aPolygonZ1.getBoxMaxY());
		debugLog("[FILE-2]: MinX=" + aPolygonZ2.getBoxMinX() + ",MinY=" + aPolygonZ2.getBoxMinY() + 
				",MaxX=" + aPolygonZ2.getBoxMaxX() + ",MaxY=" + aPolygonZ2.getBoxMaxY());
		if ((aPolygonZ1.getBoxMinX() != aPolygonZ2.getBoxMinX()) 
				|| (aPolygonZ1.getBoxMinY() != aPolygonZ2.getBoxMinY())
				|| (aPolygonZ1.getBoxMaxX() != aPolygonZ2.getBoxMaxX())
				|| (aPolygonZ1.getBoxMaxY() != aPolygonZ2.getBoxMaxY())) {
			throwAssertFailure("FAILURE (assertPolygonZShapeEquals): Found BoxMin/Max that are different!");
		}
		
		// Number of parts and number of points are the SAME.
		for (int i = 0; i < aPolygonZ1.getNumberOfParts(); i++) {
			PointData[] points1 = aPolygonZ1.getPointsOfPart(i);
			PointData[] points2 = aPolygonZ2.getPointsOfPart(i);
			debugLog("[FILE-1]: Part " + i + " has " + points1.length + " points.");
			debugLog("[FILE-2]: Part " + i + " has " + points2.length + " points.");
			assertPointDataArrayEquals(points1, points2);
		}
	}

	private void assertMultiPointMShapeEquals(MultiPointMShape aMultiPointM1, MultiPointMShape aMultiPointM2) throws Exception {
		debugLog("[FILE-1,2]: Checking MultiPointM SKIPPED");
		if (aMultiPointM1 == null && aMultiPointM2 != null ) {
			throwAssertFailure("FAILURE (assertMultiPointMShapeEquals): MultiPointShape1 NULL. MultiPointShape2 Not NULL!");
		}
		if (aMultiPointM1 != null && aMultiPointM2 == null ) {
			throwAssertFailure("FAILURE (assertMultiPointMShapeEquals): MultiPointShape2 NULL. MultiPointShape1 Not NULL!");
		}		
		debugLog(String.format("[FILE-1]: MultiPointShape (NumPoints=%s)", aMultiPointM1.getNumberOfPoints()));
		debugLog(String.format("[FILE-2]: MultiPointShape (NumPoints=%s)", aMultiPointM2.getNumberOfPoints()));
		if (aMultiPointM1.getNumberOfPoints() != aMultiPointM2.getNumberOfPoints()) {
			throwAssertFailure("FAILURE (assertMultiPointMShapeEquals): Found NumberOfPoints that are different!");
		}

		if ((aMultiPointM1.getMinM() != aMultiPointM2.getMinM()) 
				|| (aMultiPointM1.getMaxM() != aMultiPointM2.getMaxM())) {
			throwAssertFailure("FAILURE (assertMultiPointMShapeEquals): Found Min/Max M that are different!");
		}

		if ((aMultiPointM1.getBoxMinX() != aMultiPointM2.getBoxMinX()) 
				|| (aMultiPointM1.getBoxMinY() != aMultiPointM2.getBoxMinY())
				|| (aMultiPointM1.getBoxMaxX() != aMultiPointM2.getBoxMaxX())
				|| (aMultiPointM1.getBoxMaxY() != aMultiPointM2.getBoxMaxY())) {
			throwAssertFailure("FAILURE (assertMultiPointMShapeEquals): Found BoxMin/Max X/Y that are different!");
		}
		
		// Number of points are the SAME.
		PointData[] points1 = aMultiPointM1.getPoints();
		PointData[] points2 = aMultiPointM2.getPoints();
		debugLog("[FILE-1]: MultiPointM1 has " + points1.length + " points.");
		debugLog("[FILE-2]: MultiPointM1 has " + points2.length + " points.");
		assertPointDataArrayEquals(points1, points2);
	}

	private void assertMultiPointZShapeEquals(MultiPointZShape aMultiPointZ1, MultiPointZShape aMultiPointZ2) throws Exception {
		debugLog("[FILE-1,2]: Checking MultiPointZ SKIPPED");
		if (aMultiPointZ1 == null && aMultiPointZ2 != null ) {
			throwAssertFailure("FAILURE (assertMultiPointZShapeEquals): MultiPointShape1 NULL. MultiPointShape2 Not NULL!");
		}
		if (aMultiPointZ1 != null && aMultiPointZ2 == null ) {
			throwAssertFailure("FAILURE (assertMultiPointZShapeEquals): MultiPointShape2 NULL. MultiPointShape1 Not NULL!");
		}		
		debugLog(String.format("[FILE-1]: MultiPointShape (NumPoints=%s)", aMultiPointZ1.getNumberOfPoints()));
		debugLog(String.format("[FILE-2]: MultiPointShape (NumPoints=%s)", aMultiPointZ2.getNumberOfPoints()));
		if (aMultiPointZ1.getNumberOfPoints() != aMultiPointZ2.getNumberOfPoints()) {
			throwAssertFailure("FAILURE (assertMultiPointZShapeEquals): Found NumberOfPoints that are different!");
		}

		if ((aMultiPointZ1.getMinM() != aMultiPointZ2.getMinM()) 
				|| (aMultiPointZ1.getMinZ() != aMultiPointZ2.getMinZ())
				|| (aMultiPointZ1.getMaxM() != aMultiPointZ2.getMaxM())
				|| (aMultiPointZ1.getMaxZ() != aMultiPointZ2.getMaxZ())) {
			throwAssertFailure("FAILURE (assertMultiPointZShapeEquals): Found Min/Max M/Z that are different!");
		}

		if ((aMultiPointZ1.getBoxMinX() != aMultiPointZ2.getBoxMinX()) 
				|| (aMultiPointZ1.getBoxMinY() != aMultiPointZ2.getBoxMinY())
				|| (aMultiPointZ1.getBoxMaxX() != aMultiPointZ2.getBoxMaxX())
				|| (aMultiPointZ1.getBoxMaxY() != aMultiPointZ2.getBoxMaxY())) {
			throwAssertFailure("FAILURE (assertMultiPointZShapeEquals): Found BoxMin/Max X/Y that are different!");
		}
		
		// Number of points are the SAME.
		PointData[] points1 = aMultiPointZ1.getPoints();
		PointData[] points2 = aMultiPointZ2.getPoints();
		debugLog("[FILE-1]: MultiPointZ1 has " + points1.length + " points.");
		debugLog("[FILE-2]: MultiPointZ1 has " + points2.length + " points.");
		assertPointDataArrayEquals(points1, points2);
	}

	private void assertMultiPointPlaneShapeEquals(MultiPointPlainShape aMultiPoint1,
			MultiPointPlainShape aMultiPoint2) throws Exception {
		debugLog("[FILE-1,2]: Checking MultiPoint SKIPPED");
		if (aMultiPoint1 == null && aMultiPoint2 != null ) {
			throwAssertFailure("FAILURE (assertMultiPointShapeEquals): MultiPointShape1 NULL. MultiPointShape2 Not NULL!");
		}
		if (aMultiPoint1 != null && aMultiPoint2 == null ) {
			throwAssertFailure("FAILURE (assertMultiPointShapeEquals): MultiPointShape2 NULL. MultiPointShape1 Not NULL!");
		}		
		debugLog(String.format("[FILE-1]: MultiPointShape (NumPoints=%s)", aMultiPoint1.getNumberOfPoints()));
		debugLog(String.format("[FILE-2]: MultiPointShape (NumPoints=%s)", aMultiPoint2.getNumberOfPoints()));
		if (aMultiPoint1.getNumberOfPoints() != aMultiPoint2.getNumberOfPoints()) {
			throwAssertFailure("FAILURE (assertMultiPointShapeEquals): Found NumberOfPoints that are different!");
		}

		if ((aMultiPoint1.getBoxMinX() != aMultiPoint2.getBoxMinX()) 
				|| (aMultiPoint1.getBoxMinY() != aMultiPoint2.getBoxMinY())
				|| (aMultiPoint1.getBoxMaxX() != aMultiPoint2.getBoxMaxX())
				|| (aMultiPoint1.getBoxMaxY() != aMultiPoint2.getBoxMaxY())) {
			throwAssertFailure("FAILURE (assertMultiPointShapeEquals): Found BoxMin/Max X/Y that are different!");
		}
		
		// Number of points are the SAME.
		PointData[] points1 = aMultiPoint1.getPoints();
		PointData[] points2 = aMultiPoint2.getPoints();
		debugLog("[FILE-1]: MultiPoint1 has " + points1.length + " points.");
		debugLog("[FILE-2]: MultiPoint1 has " + points2.length + " points.");
		assertPointDataArrayEquals(points1, points2);
	}

	private void assertMultiPatchShapeEquals(MultiPatchShape aMultiPatch1, MultiPatchShape aMultiPatch2) throws Exception {
		debugLog("[FILE-1,2]: Checking MultiPatch SKIPPED");
		if (aMultiPatch1 == null && aMultiPatch2 != null ) {
			throwAssertFailure("FAILURE (assertMultiPatchShapeEquals): MultiPatchShape1 NULL. MultiPatchShape2 Not NULL!");
		}
		if (aMultiPatch1 != null && aMultiPatch2 == null ) {
			throwAssertFailure("FAILURE (assertMultiPatchShapeEquals): MultiPatchShape2 NULL. MultiPatchShape1 Not NULL!");
		}		
		debugLog(String.format("[FILE-1]: MultiPatchShape (NumPoints=%s,NumParts=%s)",
				aMultiPatch1.getNumberOfPoints(),aMultiPatch1.getNumberOfParts()));
		debugLog(String.format("[FILE-2]: MultiPatchShape (NumPoints=%s,NumParts=%s)",
				aMultiPatch2.getNumberOfPoints(),aMultiPatch2.getNumberOfParts()));
		if (aMultiPatch1.getNumberOfParts() != aMultiPatch2.getNumberOfParts()) {
			throwAssertFailure("FAILURE (assertMultiPatchShapeEquals): Found NumberOfParts that are different!");
		}
		if (aMultiPatch1.getNumberOfPoints() != aMultiPatch2.getNumberOfPoints()) {
			throwAssertFailure("FAILURE (assertMultiPatchShapeEquals): Found NumberOfPoints that are different!");
		}

		if ((aMultiPatch1.getMinM() != aMultiPatch2.getMinM()) 
				|| (aMultiPatch1.getMinZ() != aMultiPatch2.getMinZ())
				|| (aMultiPatch1.getMaxM() != aMultiPatch2.getMaxM())
				|| (aMultiPatch1.getMaxZ() != aMultiPatch2.getMaxZ())) {
			throwAssertFailure("FAILURE (assertMultiPatchShapeEquals): Found Min/Max M/Z that are different!");
		}

		if ((aMultiPatch1.getBoxMinX() != aMultiPatch2.getBoxMinX()) 
				|| (aMultiPatch1.getBoxMinY() != aMultiPatch2.getBoxMinY())
				|| (aMultiPatch1.getBoxMaxX() != aMultiPatch2.getBoxMaxX())
				|| (aMultiPatch1.getBoxMaxY() != aMultiPatch2.getBoxMaxY())) {
			throwAssertFailure("FAILURE (assertMultiPatchShapeEquals): Found BoxMin/Max X/Y that are different!");
		}
		
		// Number of parts and number of points are the SAME.
		for (int i = 0; i < aMultiPatch1.getNumberOfParts(); i++) {
			PointData[] points1 = aMultiPatch1.getPointsOfPart(i);
			PointData[] points2 = aMultiPatch2.getPointsOfPart(i);
			debugLog("[FILE-1]: Part " + i + " has " + points1.length + " points.");
			debugLog("[FILE-2]: Part " + i + " has " + points2.length + " points.");
			assertPointDataArrayEquals(points1, points2);
		}
	}

	private void assertPolylineShapeEquals(PolylineShape aPolyLine1, PolylineShape aPolyLine2) throws Exception {
		debugLog("[FILE-1,2]: Checking Polyline");
		if (aPolyLine1 == null && aPolyLine2 != null ) {
			throwAssertFailure("FAILURE (assertPolylineShapeEquals): PolylineShape1 NULL. PolylineShape2 Not NULL!");
		}
		if (aPolyLine1 != null && aPolyLine2 == null ) {
			throwAssertFailure("FAILURE (assertPolylineShapeEquals): PolylineShape2 NULL. PolylineShape1 Not NULL!");
		}		
		debugLog(String.format("[FILE-1]: PolylineShape (NumPoints=%s,NumParts=%s)",
				aPolyLine1.getNumberOfPoints(),aPolyLine1.getNumberOfParts()));
		debugLog(String.format("[FILE-2]: PolylineShape (NumPoints=%s,NumParts=%s)",
				aPolyLine2.getNumberOfPoints(),aPolyLine2.getNumberOfParts()));
		if (aPolyLine1.getNumberOfParts() != aPolyLine2.getNumberOfParts()) {
			throwAssertFailure("FAILURE (assertPolylineShapeEquals): Found NumberOfParts that are different!");
		}
		if (aPolyLine1.getNumberOfPoints() != aPolyLine2.getNumberOfPoints()) {
			throwAssertFailure("FAILURE (assertPolylineShapeEquals): Found NumberOfPoints that are different!");
		}
		
		if ((aPolyLine1.getBoxMinX() != aPolyLine2.getBoxMinX()) 
				|| (aPolyLine1.getBoxMinY() != aPolyLine2.getBoxMinY())
				|| (aPolyLine1.getBoxMaxX() != aPolyLine2.getBoxMaxX())
				|| (aPolyLine1.getBoxMaxY() != aPolyLine2.getBoxMaxY())) {
			throwAssertFailure("FAILURE (assertPolylineShapeEquals): Found BoxMin/Max that are different!");
		}
		
		// Number of parts and number of points are the SAME.
		for (int i = 0; i < aPolyLine1.getNumberOfParts(); i++) {
			PointData[] points1 = aPolyLine1.getPointsOfPart(i);
			PointData[] points2 = aPolyLine2.getPointsOfPart(i);
			debugLog("[FILE-1]: Part " + i + " has " + points1.length + " points.");
			debugLog("[FILE-2]: Part " + i + " has " + points2.length + " points.");
			assertPointDataArrayEquals(points1, points2);
		}
	}

	private void assertPolylineMShapeEquals(PolylineMShape aPolylineM1, PolylineMShape aPolylineM2) throws Exception {
		debugLog("[FILE-1,2]: Checking PolylineM");
		if (aPolylineM1 == null && aPolylineM2 != null ) {
			throwAssertFailure("FAILURE (assertPolylineMShapeEquals): PolylineMShape1 NULL. PolylineMShape2 Not NULL!");
		}
		if (aPolylineM1 != null && aPolylineM2 == null ) {
			throwAssertFailure("FAILURE (assertPolylineMShapeEquals): PolylineMShape2 NULL. PolylineMShape1 Not NULL!");
		}		
		debugLog(String.format("[FILE-1]: PolylineMShape (NumPoints=%s,NumParts=%s)",
				aPolylineM1.getNumberOfPoints(),aPolylineM1.getNumberOfParts()));
		debugLog(String.format("[FILE-2]: PolylineMShape (NumPoints=%s,NumParts=%s)",
				aPolylineM2.getNumberOfPoints(),aPolylineM2.getNumberOfParts()));
		if (aPolylineM1.getNumberOfParts() != aPolylineM2.getNumberOfParts()) {
			throwAssertFailure("FAILURE (assertPolylineMShapeEquals): Found NumberOfParts that are different!");
		}
		if (aPolylineM1.getNumberOfPoints() != aPolylineM2.getNumberOfPoints()) {
			throwAssertFailure("FAILURE (assertPolylineMShapeEquals): Found NumberOfPoints that are different!");
		}
		
		debugLog("[FILE-1]: MinM=" + aPolylineM1.getMinM() + ",MaxM=" + aPolylineM1.getMaxM());
		debugLog("[FILE-2]: MinM=" + aPolylineM2.getMinM() + ",MaxM=" + aPolylineM2.getMaxM());
		if ((aPolylineM1.getMinM() != aPolylineM2.getMinM()) 
				|| (aPolylineM1.getMaxM() != aPolylineM2.getMaxM())) {
			throwAssertFailure("FAILURE (assertPolylineMShapeEquals): Found MinM/MaxM that are different!");
		}
		
		if ((aPolylineM1.getBoxMinX() != aPolylineM2.getBoxMinX()) 
				|| (aPolylineM1.getBoxMinY() != aPolylineM2.getBoxMinY())
				|| (aPolylineM1.getBoxMaxX() != aPolylineM2.getBoxMaxX())
				|| (aPolylineM1.getBoxMaxY() != aPolylineM2.getBoxMaxY())) {
			throwAssertFailure("FAILURE (assertPolylineMShapeEquals): Found BoxMin/Max that are different!");
		}
		
		// Number of parts and number of points are the SAME.
		for (int i = 0; i < aPolylineM1.getNumberOfParts(); i++) {
			PointData[] points1 = aPolylineM1.getPointsOfPart(i);
			PointData[] points2 = aPolylineM2.getPointsOfPart(i);
			debugLog("[FILE-1]: Part " + i + " has " + points1.length + " points.");
			debugLog("[FILE-2]: Part " + i + " has " + points2.length + " points.");
			assertPointDataArrayEquals(points1, points2);
		}
	}

	private void assertPolylineZShapeEquals(PolylineZShape aPolylineZ1, PolylineZShape aPolylineZ2) throws Exception {
		debugLog("[FILE-1,2]: Checking PolylineZ");
		if (aPolylineZ1 == null && aPolylineZ2 != null ) {
			throwAssertFailure("FAILURE (assertPolylineZShapeEquals): PolylineZShape1 NULL. PolylineZShape2 Not NULL!");
		}
		if (aPolylineZ1 != null && aPolylineZ2 == null ) {
			throwAssertFailure("FAILURE (assertPolylineZShapeEquals): PolylineZShape2 NULL. PolylineZShape1 Not NULL!");
		}		
		debugLog(String.format("[FILE-1]: PolylineZShape (NumPoints=%s,NumParts=%s)",
				aPolylineZ1.getNumberOfPoints(),aPolylineZ1.getNumberOfParts()));
		debugLog(String.format("[FILE-2]: PolylineZShape (NumPoints=%s,NumParts=%s)",
				aPolylineZ2.getNumberOfPoints(),aPolylineZ2.getNumberOfParts()));
		if (aPolylineZ1.getNumberOfParts() != aPolylineZ2.getNumberOfParts()) {
			throwAssertFailure("FAILURE (assertPolylineZShapeEquals): Found NumberOfParts that are different!");
		}
		if (aPolylineZ1.getNumberOfPoints() != aPolylineZ2.getNumberOfPoints()) {
			throwAssertFailure("FAILURE (assertPolylineZShapeEquals): Found NumberOfPoints that are different!");
		}
		
		debugLog("[FILE-1]: MinM=" + aPolylineZ1.getMinM() + ",MaxM=" + aPolylineZ1.getMaxM() + 
				"MinZ=" + aPolylineZ1.getMinZ() + ",MaxZ=" + aPolylineZ1.getMaxZ());
		debugLog("[FILE-2]: MinM=" + aPolylineZ2.getMinM() + ",MaxM=" + aPolylineZ2.getMaxM() +
				"MinZ=" + aPolylineZ2.getMinZ() + ",MaxZ=" + aPolylineZ2.getMaxZ());
		if ((aPolylineZ1.getMinM() != aPolylineZ2.getMinM()) 
				|| (aPolylineZ1.getMaxM() != aPolylineZ2.getMaxM())
				|| (aPolylineZ1.getMinZ() != aPolylineZ2.getMinZ())
				|| (aPolylineZ1.getMaxZ() != aPolylineZ2.getMaxZ())) {
			throwAssertFailure("FAILURE (assertPolylineZShapeEquals): Found MinM/MaxM, MinZ/MaxZ that are different!");
		}
		
		if ((aPolylineZ1.getBoxMinX() != aPolylineZ2.getBoxMinX()) 
				|| (aPolylineZ1.getBoxMinY() != aPolylineZ2.getBoxMinY())
				|| (aPolylineZ1.getBoxMaxX() != aPolylineZ2.getBoxMaxX())
				|| (aPolylineZ1.getBoxMaxY() != aPolylineZ2.getBoxMaxY())) {
			throwAssertFailure("FAILURE (assertPolylineZShapeEquals): Found BoxMin/Max that are different!");
		}
		
		// Number of parts and number of points are the SAME.
		for (int i = 0; i < aPolylineZ1.getNumberOfParts(); i++) {
			PointData[] points1 = aPolylineZ1.getPointsOfPart(i);
			PointData[] points2 = aPolylineZ2.getPointsOfPart(i);
			debugLog("[FILE-1]: Part " + i + " has " + points1.length + " points.");
			debugLog("[FILE-2]: Part " + i + " has " + points2.length + " points.");
			assertPointDataArrayEquals(points1, points2);
		}
	}

	private void assertPointDataArrayEquals(PointData[] points1, PointData[] points2) throws Exception {
		debugLog("[FILE-1,2]: Checking PointDataArray");
		if (points1 == null && points2 != null) {
			throwAssertFailure("FAILURE (assertPointDataArrayEquals): PointsData1 NULL. PointsData2 Not NULL!");
		}
		if (points1 != null && points2 == null) {
			throwAssertFailure("FAILURE (assertPointDataArrayEquals): PointsData2 NULL. PointsData1 Not NULL!");
		}
		debugLog("[FILE-1]: Found " + points1.length + " points.");
		debugLog("[FILE-2]: Found " + points2.length + " points.");
		if (points1.length != points2.length) {
			throwAssertFailure("FAILURE (assertPointDataArrayEquals): PointsData1 length != PointsData2 length");
		}
		for (int i = 0; i < points1.length; i++) {
			PointData pointData1 = points1[i];
			PointData pointData2 = points2[i];
			assertPointDataEquals(pointData1, pointData2);
		}
	}

	private void assertPointDataEquals(PointData pointData1, PointData pointData2) throws Exception {
		debugLog("[FILE-1,2]: Checking PointData");
		if (pointData1 == null && pointData2 != null ) {
			throwAssertFailure("FAILURE (assertPointDataEquals): PointData1 NULL. PointData2 Not NULL!");
		}
		if (pointData1 != null && pointData2 == null ) {
			throwAssertFailure("FAILURE (assertPointDataEquals): PointData2 NULL. PointData1 Not NULL!");
		}
		debugLog(String.format("[FILE-1]: Point (X=%s,Y=%s)",pointData1.getX(),pointData1.getY()));
		debugLog(String.format("[FILE-2]: Point (X=%s,Y=%s)",pointData2.getX(),pointData2.getY()));
		if ((pointData1.getX() != pointData2.getX()) 
				|| (pointData1.getY() != pointData2.getY())) {
			throwAssertFailure("FAILURE (assertPointDataEquals): Found PointData that are different!");
		}
	}

	private void assertPointZShapeEquals(PointZShape aPointZ1, PointZShape aPointZ2) throws Exception {
		debugLog("[FILE-1,2]: Checking PointZ");
		if (aPointZ1 == null && aPointZ2 != null ) {
			throwAssertFailure("FAILURE (assertPointZShapeEquals): PointZShape1 NULL. PointZShape2 Not NULL!");
		}
		if (aPointZ1 != null && aPointZ2 == null ) {
			throwAssertFailure("FAILURE (assertPointZShapeEquals): PointZShape2 NULL. PointZShape1 Not NULL!");
		}
		debugLog(String.format("[FILE-1]: Point (X=%s,Y=%s,Z=%s,M=%s)",
				aPointZ1.getX(),aPointZ1.getY(),aPointZ1.getZ(),aPointZ1.getM()));
		debugLog(String.format("[FILE-2]: Point (X=%s,Y=%s,Z=%s,M=%s)",
				aPointZ2.getX(),aPointZ2.getY(),aPointZ2.getZ(),aPointZ2.getM()));
		if ((aPointZ1.getX() != aPointZ2.getX()) 
				|| (aPointZ1.getY() != aPointZ2.getY())
				|| (aPointZ1.getM() != aPointZ2.getM())
				|| (aPointZ1.getZ() != aPointZ2.getZ())) {
			throwAssertFailure("FAILURE (assertPointZShapeEquals): Found Points that are different!");
		}
	}

	private void assertPointMShapeEquals(PointMShape aPointM1, PointMShape aPointM2) throws Exception {
		debugLog("[FILE-1,2]: Checking PointM");
		if (aPointM1 == null && aPointM2 != null ) {
			throwAssertFailure("FAILURE (assertPointMShapeEquals): PointMShape1 NULL. PointMShape2 Not NULL!");
		}
		if (aPointM1 != null && aPointM2 == null ) {
			throwAssertFailure("FAILURE (assertPointMShapeEquals): PointMShape2 NULL. PointMShape1 Not NULL!");
		}
		debugLog(String.format("[FILE-1]: Point (X=%s,Y=%s,M=%s)",
				aPointM1.getX(),aPointM1.getY(),aPointM1.getM()));
		debugLog(String.format("[FILE-2]: Point (X=%s,Y=%s,M=%s)",
				aPointM2.getX(),aPointM2.getY(),aPointM2.getM()));
		if ((aPointM1.getX() != aPointM2.getX()) 
				|| (aPointM1.getY() != aPointM2.getY())
				|| (aPointM1.getM() != aPointM2.getM())) {
			throwAssertFailure("FAILURE (assertPointMShapeEquals): Found Points that are different!");
		}
	}

	private void assertPointShapeEquals(PointShape aPoint1, PointShape aPoint2) throws Exception {
		debugLog("[FILE-1,2]: Checking Point");
		if (aPoint1 == null && aPoint2 != null ) {
			throwAssertFailure("FAILURE (assertPointShapeEquals): PointShape1 NULL. PointShape2 Not NULL!");
		}
		if (aPoint1 != null && aPoint2 == null ) {
			throwAssertFailure("FAILURE (assertPointShapeEquals): PointShape2 NULL. PointShape1 Not NULL!");
		}
		debugLog(String.format("[FILE-1]: Point (X=%s,Y=%s)", aPoint1.getX(),aPoint1.getY()));
		debugLog(String.format("[FILE-2]: Point (X=%s,Y=%s)", aPoint2.getX(),aPoint2.getY()));
		if ((aPoint1.getX() != aPoint2.getX()) || (aPoint1.getY() != aPoint2.getY())) {
			throwAssertFailure("FAILURE (assertPointShapeEquals): Found Points that are different!");
		}
	}
	
	private void throwAssertFailure(String errorMsg) throws Exception {
		Log.error(errorMsg);
		throw new Exception("Assert FAILURE!");
	}

	public static void main(String[] args) throws Exception {
		Log.info("Running test - testShapeFileComparison_assertEquals_Success() ...");
		testShapeFileComparison_assertEquals_Success();
		Log.info("Running test - testShapeFileComparison_assertEquals_Failure() ...");
		testShapeFileComparison_assertEquals_Failure();
	}

	private static void testShapeFileComparison_assertEquals_Success() throws Exception {
		Path shpDirectory = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data\\test-data\\shapefileutility-tests\\shape-compare-data\\01");
		List<String> shpFilesInDirectory = FileUtility.getFilesInDirectory(shpDirectory, "*.shp");
		for (String filePath : shpFilesInDirectory) {
			String file1Path = filePath;
			String file2Path = file1Path.replace("\\01\\", "\\01-Copy\\");
			Log.info("---------------------------------------------------------------------");
			Log.info("Comparing Shape Files... ");
			Log.info(String.format("File1=[%s]", file1Path));
			Log.info(String.format("File2=[%s]", file2Path));
			ShapeFileReaderComparer comparer = new ShapeFileReaderComparer();
			comparer.assertEquals(file1Path, file2Path);
			Log.info("---------------------------------------------------------------------");
		}
	}

	private static void testShapeFileComparison_assertEquals_Failure() throws Exception {
		Path shpDirectory = Paths.get(TestSetup.getExecutionPath(TestSetup.getRootPath()), "data\\test-data\\shapefileutility-tests");
		String file1Path = shpDirectory.toString() + File.separator + "CR-0AEE84-FOV.shp";
		String file2Path = shpDirectory.toString() + File.separator + "CR-0AEE84-BreadCrumb.shp";
		ShapeFileReaderComparer comparer = new ShapeFileReaderComparer();
		comparer.assertEquals(file1Path, file2Path);
	}
}
