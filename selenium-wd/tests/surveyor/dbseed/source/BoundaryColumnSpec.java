package surveyor.dbseed.source;

import org.dbunit.dataset.builder.ColumnSpec;

public class BoundaryColumnSpec {
	public static String TABLE_NAME = "Boundary";
	
	public static ColumnSpec<String> Col_Id = ColumnSpec.newColumn("Id");
	public static ColumnSpec<String> Col_ExternalId = ColumnSpec.newColumn("ExternalId");
	public static ColumnSpec<String> Col_CustomerId = ColumnSpec.newColumn("CustomerId");
	public static ColumnSpec<String> Col_Description = ColumnSpec.newColumn("Description");
	public static ColumnSpec<Integer> Col_Level = ColumnSpec.newColumn("Level");
	public static ColumnSpec<byte[]> Col_Shape = ColumnSpec.newColumn("Shape");
	public static ColumnSpec<String> Col_State = ColumnSpec.newColumn("State");
	public static ColumnSpec<String> Col_CustomerBoundaryTypeID = ColumnSpec.newColumn("CustomerBoundaryTypeID");
}
