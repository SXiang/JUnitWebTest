package surveyor.dbseed.source;

import java.util.Date;

import org.dbunit.dataset.builder.ColumnSpec;

public class AssetColumnSpec {
	public static String TABLE_NAME = "Asset";
	
	public static ColumnSpec<String> Col_Id = ColumnSpec.newColumn("Id");
	public static ColumnSpec<String> Col_ExternalId = ColumnSpec.newColumn("ExternalId");
	public static ColumnSpec<String> Col_CustomerId = ColumnSpec.newColumn("CustomerId");
	public static ColumnSpec<String> Col_AssetTypeId = ColumnSpec.newColumn("AssetTypeId");
	public static ColumnSpec<String> Col_Description = ColumnSpec.newColumn("Description");
	public static ColumnSpec<Object> Col_Shape = ColumnSpec.newColumn("Shape");
	public static ColumnSpec<Date> Col_Date = ColumnSpec.newColumn("Date");
	public static ColumnSpec<String> Col_State = ColumnSpec.newColumn("State");
	public static ColumnSpec<String> Col_CustomerMaterialTypeId = ColumnSpec.newColumn("CustomerMaterialTypeId");
}
