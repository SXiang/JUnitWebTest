package surveyor.dbseed.source;

import org.dbunit.dataset.builder.ColumnSpec;

public class CustomerMaterialTypeColumnSpec {
	public static String TABLE_NAME = "CustomerMaterialType";
	
	public static ColumnSpec<String> Col_Id = ColumnSpec.newColumn("Id");
	public static ColumnSpec<String> Col_CustomerId = ColumnSpec.newColumn("CustomerId");
	public static ColumnSpec<String> Col_Description = ColumnSpec.newColumn("Description");
	public static ColumnSpec<String> Col_Color = ColumnSpec.newColumn("Color");
	public static ColumnSpec<Short> Col_LineWeight = ColumnSpec.newColumn("LineWeight");
	public static ColumnSpec<Boolean> Col_IsDotted = ColumnSpec.newColumn("IsDotted");
}
