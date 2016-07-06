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
import common.source.ShapeFileUtility;
import surveyor.dataaccess.source.Boundary;

public class BoundaryDataSet extends BaseDataSet {

	private List<Boundary> boundaryList = new ArrayList<Boundary>();
	
	public BoundaryDataSet() {
		boundaryList.add(new Boundary() {{
			setId("B5329E90-2ED2-4D14-8E0B-029053396064");
			setExternalId("TESTPlat-Auto-1.5km");
			setCustomerId("B1252204-04FB-4A67-82D4-3F4666FD855C");
			setDescription("TESTPlat-Auto-1.5km");
			setLevel(3);
			setShape(ShapeFileUtility.convertFromTextToBinary("POLYGON ((-121.98389 37.42062, -121.9726 37.42066, -121.97251 37.41571, -121.98389 37.41525, -121.98389 37.42062))"));
			setState("CA");
			setCustomerBoundaryTypeID("551CB7C0-005B-4E3E-BFAE-D19DA0ED7EFE");
		}});
		boundaryList.add(new Boundary() {{
			setId("015181BC-F95E-4C3F-81F2-66D7F8ABE24C");
			setExternalId("Q2750");
			setCustomerId("B1252204-04FB-4A67-82D4-3F4666FD855C");
			setDescription("Level 1");
			setLevel(1);
			setShape(ShapeFileUtility.convertFromTextToBinary("POLYGON ((-122.068685 37.438024, -121.834367 37.439523, -121.833509 37.354015, -122.065423 37.352378, -122.068685 37.438024))"));
			setState("CA");
			setCustomerBoundaryTypeID("024249AE-374B-4F6F-BD87-E8FDCACB48E1");
		}});
		boundaryList.add(new Boundary() {{
			setId("0F4E9FE1-E565-47B7-8E4F-68EBEE16BD81");
			setExternalId("Q2753");
			setCustomerId("B1252204-04FB-4A67-82D4-3F4666FD855C");
			setDescription("Level 2-AA");
			setLevel(3);
			setShape(ShapeFileUtility.convertFromTextToBinary("POLYGON ((-122.060617 37.436661, -121.995042 37.437888, -121.991952 37.393713, -122.062505 37.396577, -122.060617 37.436661))"));
			setState("CA");
			setCustomerBoundaryTypeID("551CB7C0-005B-4E3E-BFAE-D19DA0ED7EFE");
		}});
		boundaryList.add(new Boundary() {{
			setId("0F4E9FE1-E565-47B7-8E4F-68EBEE16BD82");
			setExternalId("Q2754");
			setCustomerId("B1252204-04FB-4A67-82D4-3F4666FD855C");
			setDescription("Level 2-AB");
			setLevel(3);
			setShape(ShapeFileUtility.convertFromTextToBinary("POLYGON ((-121.992113 37.393905, -121.993315 37.434535, -121.914694 37.430173, -121.914179 37.393223, -121.992113 37.393905))"));
			setState("CA");
			setCustomerBoundaryTypeID("551CB7C0-005B-4E3E-BFAE-D19DA0ED7EFE");
		}});
		boundaryList.add(new Boundary() {{
			setId("0F4E9FE1-E565-47B7-8E4F-68EBEE16BD91");
			setExternalId("Q2757");
			setCustomerId("B1252204-04FB-4A67-82D4-3F4666FD855C");
			setDescription("Level 2-BA");
			setLevel(3);
			setShape(ShapeFileUtility.convertFromTextToBinary("POLYGON ((-122.064222 37.389485, -121.994699 37.388394, -121.998132 37.357836, -122.059072 37.356199, -122.064222 37.389485))"));
			setState("CA");
			setCustomerBoundaryTypeID("551CB7C0-005B-4E3E-BFAE-D19DA0ED7EFE");
		}});
		boundaryList.add(new Boundary() {{
			setId("0F4E9FE1-E565-47B7-8E4F-68EBEE16BD92");
			setExternalId("Q2758");
			setCustomerId("B1252204-04FB-4A67-82D4-3F4666FD855C");
			setDescription("Level 2-BB");
			setLevel(3);
			setShape(ShapeFileUtility.convertFromTextToBinary("POLYGON ((-121.990922 37.393304, -121.90904 37.393031, -121.910413 37.358382, -121.985086 37.356744, -121.990922 37.393304))"));
			setState("CA");
			setCustomerBoundaryTypeID("551CB7C0-005B-4E3E-BFAE-D19DA0ED7EFE");
		}});
		boundaryList.add(new Boundary() {{
			setId("0F4E9FE1-E565-47B7-8E4F-68EBEE16BD93");
			setExternalId("Q2759");
			setCustomerId("B1252204-04FB-4A67-82D4-3F4666FD855C");
			setDescription("Level 2-BC");
			setLevel(3);
			setShape(ShapeFileUtility.convertFromTextToBinary("POLYGON ((-121.903032 37.394122, -121.837457 37.389485, -121.840718 37.354425, -121.911443 37.351559, -121.903032 37.394122))"));
			setState("CA");
			setCustomerBoundaryTypeID("551CB7C0-005B-4E3E-BFAE-D19DA0ED7EFE");
		}});
		boundaryList.add(new Boundary() {{
			setId("787BA2F5-7258-4F49-AA38-EBE4E057A37F");
			setExternalId("TestExternalId-1");
			setCustomerId("B1252204-04FB-4A67-82D4-3F4666FD855C");
			setDescription("TestPlat-Auto-01");
			setLevel(3);
			setShape(ShapeFileUtility.convertFromTextToBinary("POLYGON ((-121.98297074078368 37.4092809116535, -121.98275654130862 37.4249965263606, -121.96275700000001 37.424962622398468, -121.96314405291747 37.409314882050253, -121.98297074078368 37.4092809116535))"));
			setState("CA");
			setCustomerBoundaryTypeID("551CB7C0-005B-4E3E-BFAE-D19DA0ED7EFE");
		}});
	}
	
	public List<IDataSet> build() throws DataSetException {
		return build(null /*do not override customer id*/);		
	}

	public List<IDataSet> build(String customerId) throws DataSetException {
		List<IDataSet> dataSetList = new ArrayList<IDataSet>();
		DataSetBuilder builder = new DataSetBuilder();
		for (int i = 0; i < boundaryList.size(); i++) {
			Boundary boundary = boundaryList.get(i);
			builder.newRow(BoundaryColumnSpec.TABLE_NAME)
				.with(BoundaryColumnSpec.Col_Id, BaseHelper.isNullOrEmpty(customerId) ? boundary.getId() : UUID.randomUUID().toString())
				.with(BoundaryColumnSpec.Col_ExternalId, boundary.getExternalId())
				.with(BoundaryColumnSpec.Col_CustomerId, BaseHelper.isNullOrEmpty(customerId) ? boundary.getCustomerId() : customerId)
				.with(BoundaryColumnSpec.Col_Description, boundary.getDescription())
				.with(BoundaryColumnSpec.Col_Level, boundary.getLevel())
				.with(BoundaryColumnSpec.Col_Shape, (byte[])boundary.getShape())
				.with(BoundaryColumnSpec.Col_State, boundary.getState())
				.with(BoundaryColumnSpec.Col_CustomerBoundaryTypeID, boundary.getCustomerBoundaryTypeID())
				.add();
			
			if ((i+1) % BATCH_SIZE == 0 || i == boundaryList.size() - 1) {
				IDataSet dataSet = builder.build();
				dataSetList.add(dataSet);
				new XmlDataSetWriter(new PrintWriter(System.out)).write(dataSet);
				builder = new DataSetBuilder();
			}
		}
		return dataSetList;
	}
}
