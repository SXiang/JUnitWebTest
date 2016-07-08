package surveyor.dbseed.source;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.builder.DataSetBuilder;
import org.dbunit.dataset.xml.XmlDataSetWriter;

import common.source.BaseHelper;
import surveyor.dataaccess.source.Asset;

public class AssetDataSetBuilder extends AssetDataSetBuilderBase2 {

	public AssetDataSetBuilder() throws Exception {
		super();
	}

	public List<IDataSet> build() throws DataSetException {
		return build(null /*do not override customer id*/);		
	}

	public List<IDataSet> build(String customerId) throws DataSetException {
		List<IDataSet> dataSetList = new ArrayList<IDataSet>();
		DataSetBuilder builder = new DataSetBuilder();
		for (int i = 0; i < assetList.size(); i++) {
			Asset asset = assetList.get(i);
			builder.newRow(AssetColumnSpec.TABLE_NAME)
				.with(AssetColumnSpec.Col_Id, BaseHelper.isNullOrEmpty(customerId) ? asset.getId() : UUID.randomUUID().toString())
				.with(AssetColumnSpec.Col_ExternalId, asset.getExternalId())
				.with(AssetColumnSpec.Col_CustomerId, BaseHelper.isNullOrEmpty(customerId) ? asset.getCustomerId() : customerId)
				.with(AssetColumnSpec.Col_AssetTypeId, asset.getAssetTypeId())
				.with(AssetColumnSpec.Col_Description, asset.getDescription())
				.with(AssetColumnSpec.Col_Shape, asset.getShape())
				.with(AssetColumnSpec.Col_Date, asset.getDate())
				.with(AssetColumnSpec.Col_State, asset.getState())
				.with(AssetColumnSpec.Col_CustomerMaterialTypeId, asset.getCustomerMaterialTypeId())
				.add();
			
			if ((i+1) % BATCH_SIZE == 0 || i == assetList.size() - 1) {
				IDataSet dataSet = builder.build();
				dataSetList.add(dataSet);
				new XmlDataSetWriter(new PrintWriter(System.out)).write(dataSet);
				builder = new DataSetBuilder();
			}
		}
		return dataSetList;
	}
}