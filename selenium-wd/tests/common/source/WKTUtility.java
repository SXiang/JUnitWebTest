package common.source;

import java.util.List;

import surveyor.scommon.source.Coordinates;

public class WKTUtility {

	public static class LineString {

		private List<Coordinates> coordinates;

		public LineString(List<Coordinates> coordinates) {
			this.coordinates = coordinates;
		}

		@Override
		public String toString() {
			if (coordinates == null || coordinates.size() == 0) {
				return "";
			}

			int i = 0;
			StringBuilder builder = new StringBuilder();
			for (Coordinates coord : coordinates) {
				builder.append(String.format("%s %s", String.valueOf(coord.getX()), String.valueOf(coord.getY())));
				if (i != coordinates.size()-1) {
					builder.append(", ");
				}

				i++;
			}

			return String.format("LINESTRING (%s)", builder.toString());
		}
	}

	public static String buildShapeWKTForLineSegments(List<List<Coordinates>> lineSegments) {
		if (lineSegments == null || lineSegments.size() == 0) {
			return "";
		}

		int i = 0;
		StringBuilder builder = new StringBuilder();
		for (List<Coordinates> coordsList : lineSegments) {
			builder.append(new LineString(coordsList).toString());
			if (i != lineSegments.size()-1) {
				builder.append(", ");
			}

			i++;
		}

		return String.format("GEOMETRYCOLLECTION (%s)", builder.toString());
	}
}
