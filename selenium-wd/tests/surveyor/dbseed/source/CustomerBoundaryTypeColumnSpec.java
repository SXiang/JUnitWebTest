package surveyor.dbseed.source;

import org.dbunit.dataset.builder.ColumnSpec;

public class CustomerBoundaryTypeColumnSpec {
	public static String TABLE_NAME = "CustomerBoundaryType";
	
	public static ColumnSpec<String> Col_Id = ColumnSpec.newColumn("Id");
	public static ColumnSpec<String> Col_CustomerId = ColumnSpec.newColumn("CustomerId");
	public static ColumnSpec<String> Col_FeatureClassDescription = ColumnSpec.newColumn("FeatureClassDescription");
	public static ColumnSpec<String> Col_Color = ColumnSpec.newColumn("Color");
	public static ColumnSpec<Short> Col_LineWeight = ColumnSpec.newColumn("LineWeight");
	public static ColumnSpec<Boolean> Col_IsDotted = ColumnSpec.newColumn("IsDotted");
	public static ColumnSpec<Short> Col_Zoomlevel = ColumnSpec.newColumn("Zoomlevel");
	public static ColumnSpec<Boolean> Col_IsReportable = ColumnSpec.newColumn("IsReportable");
}
