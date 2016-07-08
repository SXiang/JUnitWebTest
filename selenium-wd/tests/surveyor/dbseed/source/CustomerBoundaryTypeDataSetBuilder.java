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
import surveyor.dataaccess.source.CustomerBoundaryType;

public class CustomerBoundaryTypeDataSetBuilder extends BaseDataSetBuilder {

	private List<CustomerBoundaryType> customerBoundaryTypeList = new ArrayList<CustomerBoundaryType>();
	
	public CustomerBoundaryTypeDataSetBuilder() throws Exception {
		customerBoundaryTypeList.add(new CustomerBoundaryType() {{
			setId("551CB7C0-005B-4E3E-BFAE-D19DA0ED7EFE");
			setCustomerId("B1252204-04FB-4A67-82D4-3F4666FD855C");
			setFeatureClassDescription("Small Boundary");
			setColor("#B5DBF4");
			setLineWeight((short)2);
			setIsDotted(false);
			setZoomlevel((short)5);
			setIsReportable(true);
		}});
		customerBoundaryTypeList.add(new CustomerBoundaryType() {{
			setId("024249AE-374B-4F6F-BD87-E8FDCACB48E1");
			setCustomerId("B1252204-04FB-4A67-82D4-3F4666FD855C");
			setFeatureClassDescription("Big Boundary");
			setColor("#B5DBF4");
			setLineWeight((short)2);
			setIsDotted(false);
			setZoomlevel((short)5);
			setIsReportable(true);
		}});
	}
	
	public List<IDataSet> build() throws DataSetException {
		return build(null /*do not override customer id*/);		
	}

	public List<IDataSet> build(String customerId) throws DataSetException {
		List<IDataSet> dataSetList = new ArrayList<IDataSet>();
		DataSetBuilder builder = new DataSetBuilder();
		for (int i = 0; i < customerBoundaryTypeList.size(); i++) {
			CustomerBoundaryType customerBoundaryType = customerBoundaryTypeList.get(i);
			builder.newRow(CustomerBoundaryTypeColumnSpec.TABLE_NAME)
				.with(CustomerBoundaryTypeColumnSpec.Col_Id, BaseHelper.isNullOrEmpty(customerId) ? customerBoundaryType.getId() : UUID.randomUUID().toString())
				.with(CustomerBoundaryTypeColumnSpec.Col_CustomerId, BaseHelper.isNullOrEmpty(customerId) ? customerBoundaryType.getCustomerId() : customerId)
				.with(CustomerBoundaryTypeColumnSpec.Col_FeatureClassDescription, customerBoundaryType.getFeatureClassDescription())
				.with(CustomerBoundaryTypeColumnSpec.Col_Color, customerBoundaryType.getColor())
				.with(CustomerBoundaryTypeColumnSpec.Col_LineWeight, customerBoundaryType.getLineWeight())
				.with(CustomerBoundaryTypeColumnSpec.Col_IsDotted, customerBoundaryType.getIsDotted())
				.with(CustomerBoundaryTypeColumnSpec.Col_Zoomlevel, customerBoundaryType.getZoomlevel())
				.with(CustomerBoundaryTypeColumnSpec.Col_IsReportable, customerBoundaryType.getIsReportable())
				.add();
			
			if ((i+1) % BATCH_SIZE == 0 || i == customerBoundaryTypeList.size() - 1) {
				IDataSet dataSet = builder.build();
				dataSetList.add(dataSet);
				new XmlDataSetWriter(new PrintWriter(System.out)).write(dataSet);
				builder = new DataSetBuilder();
			}
		}
		return dataSetList;
	}
}
