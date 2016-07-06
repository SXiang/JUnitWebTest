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
import surveyor.dataaccess.source.CustomerMaterialType;

public class CustomerMaterialTypeDataSet extends BaseDataSet {

	private List<CustomerMaterialType> customerMaterialTypeList = new ArrayList<CustomerMaterialType>();
	
	public CustomerMaterialTypeDataSet() throws Exception {
		customerMaterialTypeList.add(new CustomerMaterialType() {{
			setId("D08FC87F-F979-4131-92A9-3D82F37F4BBA");
			setCustomerId("B1252204-04FB-4A67-82D4-3F4666FD855C");
			setDescription("Copper");
			setColor("#B5DBF4");
			setLineWeight((short)2);
			setIsDotted(false);
		}});
		customerMaterialTypeList.add(new CustomerMaterialType() {{
			setId("F3955E82-DD13-4842-84F7-502BCDA6B57A");
			setCustomerId("B1252204-04FB-4A67-82D4-3F4666FD855C");
			setDescription("Un-protected Steel");
			setColor("#B2DF8A");
			setLineWeight((short)2);
			setIsDotted(false);
		}});
		customerMaterialTypeList.add(new CustomerMaterialType() {{
			setId("44353E68-0694-4F05-85CB-84D753EA278C");
			setCustomerId("B1252204-04FB-4A67-82D4-3F4666FD855C");
			setDescription("Protected Steel");
			setColor("#FB9A99");
			setLineWeight((short)2);
			setIsDotted(false);
		}});
		customerMaterialTypeList.add(new CustomerMaterialType() {{
			setId("96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0");
			setCustomerId("B1252204-04FB-4A67-82D4-3F4666FD855C");
			setDescription("Cast Iron");
			setColor("#E31A1C");
			setLineWeight((short)2);
			setIsDotted(false);
		}});
		customerMaterialTypeList.add(new CustomerMaterialType() {{
			setId("AD701312-C470-482A-BE45-EF37770E2CE6");
			setCustomerId("B1252204-04FB-4A67-82D4-3F4666FD855C");
			setDescription("Other Plastic");
			setColor("#FDE36F");
			setLineWeight((short)2);
			setIsDotted(false);
		}});
		customerMaterialTypeList.add(new CustomerMaterialType() {{
			setId("F14735DE-6C9B-4423-8533-F243A7FE4E90");
			setCustomerId("B1252204-04FB-4A67-82D4-3F4666FD855C");
			setDescription("PE Plastic");
			setColor("#FDE36F");
			setLineWeight((short)2);
			setIsDotted(false);
		}});
	}
	
	public List<IDataSet> build() throws DataSetException {
		return build(null /*do not override customer id*/);		
	}

	public List<IDataSet> build(String customerId) throws DataSetException {
		List<IDataSet> dataSetList = new ArrayList<IDataSet>();
		DataSetBuilder builder = new DataSetBuilder();
		for (int i = 0; i < customerMaterialTypeList.size(); i++) {
			CustomerMaterialType customerMaterialType = customerMaterialTypeList.get(i);
			builder.newRow(CustomerMaterialTypeColumnSpec.TABLE_NAME)
				.with(CustomerMaterialTypeColumnSpec.Col_Id, BaseHelper.isNullOrEmpty(customerId) ? customerMaterialType.getId() : UUID.randomUUID().toString())
				.with(CustomerMaterialTypeColumnSpec.Col_CustomerId, BaseHelper.isNullOrEmpty(customerId) ? customerMaterialType.getCustomerId() : customerId)
				.with(CustomerMaterialTypeColumnSpec.Col_Description, customerMaterialType.getDescription())
				.with(CustomerMaterialTypeColumnSpec.Col_Color, customerMaterialType.getColor())
				.with(CustomerMaterialTypeColumnSpec.Col_LineWeight, customerMaterialType.getLineWeight())
				.with(CustomerMaterialTypeColumnSpec.Col_IsDotted, customerMaterialType.getIsDotted())
				.add();
			
			if ((i+1) % BATCH_SIZE == 0 || i == customerMaterialTypeList.size() - 1) {
				IDataSet dataSet = builder.build();
				dataSetList.add(dataSet);
				new XmlDataSetWriter(new PrintWriter(System.out)).write(dataSet);
				builder = new DataSetBuilder();
			}
		}
		return dataSetList;
	}
}
