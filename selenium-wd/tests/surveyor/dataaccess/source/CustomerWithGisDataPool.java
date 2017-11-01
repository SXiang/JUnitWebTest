package surveyor.dataaccess.source;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import common.source.ApiUtility;
import common.source.ApiUtility.HttpApiResult;
import common.source.Log;
import common.source.TestContext;
import surveyor.scommon.entities.CustomerWithGISData;
import surveyor.scommon.source.SurveyorConstants.Environment;

public class CustomerWithGisDataPool {
	// stores locationId used in seed data for customers with GIS data.
	private static Map<String, String> customerWithGISDataLocationMap = null;

	static {
		populateCustomerWithGISDataLocationMap();
	}

	public static String getSeedLocationIdForCustomer(String customerId) {
		customerId = customerId.toLowerCase();
		if (customerWithGISDataLocationMap.containsKey(customerId)) {
			return customerWithGISDataLocationMap.get(customerId);
		}

		return null;
	}

	public static Customer acquireCustomer() {
		Customer customer = null;
		CustomerWithGISData customerWithGisData = executeAcquireCustomerAPIGetResponse();
		if (customerWithGisData != null) {
			customer = Customer.getCustomer(customerWithGisData.Name);
		}

		return customer;
	}

	public static void releaseCustomer(String customerName) {
		Integer environmentId = getEnvironmentId();
		String body = String.format("{\\\"CustomerName\\\":\\\"%s\\\"}", customerName);
		ApiUtility.postAutomationApiResponse(String.format(ApiUtility.RELEASE_GIS_CUSTOMER_API_RELATIVE_URL, environmentId), body);
	}

	private static CustomerWithGISData executeAcquireCustomerAPIGetResponse() {
		Integer environmentId = getEnvironmentId();
		String body = String.format("{\\\"RunUUID\\\":\\\"%d\\\"}", TestContext.INSTANCE.getRunUniqueId());
		HttpApiResult apiResult = ApiUtility.postAutomationApiResponse(String.format(ApiUtility.ACQUIRE_GIS_CUSTOMER_API_RELATIVE_URL, environmentId), body);
		if (apiResult.getStatusCode().equals(404)) {
			return null;
		}

		String apiResponse = apiResult.getResponseBody();
		Log.info(String.format("API Response -> %s", apiResponse));
		Log.info("Creating gson Builder...");
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.create();
		Log.info("Getting CustomerWithGISData from gson.fromJson()...");
		CustomerWithGISData customerWithGisData = gson.fromJson(apiResponse, CustomerWithGISData.class);
		Log.info(String.format("Successfully returned CustomerWithGISData object -> %s", customerWithGisData.toString()));
		return customerWithGisData;
	}

	private static Integer getEnvironmentId() {
		Environment environment = Environment.getEnvironmentFromUrl(TestContext.INSTANCE.getBaseUrl());
		Integer environmentId = environment.getIndex();
		return environmentId;
	}

	private static void populateCustomerWithGISDataLocationMap() {
		customerWithGISDataLocationMap = Collections.synchronizedMap(new HashMap<>());

		// guids are customerId, locationId from 'AutomationSeedScript-GISSingleCustomer.sql.template'
		customerWithGISDataLocationMap.put("1c1063e4-7bc4-b700-dede-39e23737567f", "ff5c0836-d585-2dcd-f5c3-39e237399df8");
		customerWithGISDataLocationMap.put("d2b9c144-76d2-5205-e798-39e1655fc294", "1fa42e13-7d53-5647-217b-39e1656019e4");
		customerWithGISDataLocationMap.put("fa6bca33-5836-b680-04f8-39e22cea9846", "1ef0fb36-bfcc-56ad-c660-39e22cecdb97");
		customerWithGISDataLocationMap.put("8da641df-c53c-4b4a-c3b6-39e15e9f3466", "aa364dba-55c9-4164-bfd2-39e15ea15b66");
		customerWithGISDataLocationMap.put("42c38c4f-c7fd-f177-d577-39e1896f2e57", "8872a2cd-a721-2187-6488-39e1896f8852");
		customerWithGISDataLocationMap.put("81004af3-0cdd-31d6-4104-39e1b1221939", "9f5fadf5-79e0-22ee-854d-39e1b1244dcb");
		customerWithGISDataLocationMap.put("6e67a405-91f0-9100-a243-39e21723709e", "1e868919-4343-975f-7993-39e21725b46b");
		customerWithGISDataLocationMap.put("ee9e44f0-f185-5e7a-8706-39e1a700d496", "0a7c49ca-34b0-2852-720a-39e1a7030575");
		customerWithGISDataLocationMap.put("c75e3343-5c0f-2fed-4d40-39e256257597", "3cca958c-3231-4ebb-b94f-39e25627c284");
		customerWithGISDataLocationMap.put("d0f6a395-94ea-2d57-7355-39e1d542a051", "2a825845-5e87-46aa-6ad6-39e1d544d8db");
		customerWithGISDataLocationMap.put("1210df93-a433-4c9b-e051-39e1e9f6db9d", "22129c12-25b5-5467-6488-39e1e9f91815");
		customerWithGISDataLocationMap.put("f2170979-e081-7e11-ee53-39e250f8438e", "f7fedc9b-8c92-151d-6ffe-39e250fa8f08");
		customerWithGISDataLocationMap.put("948e22d9-85b4-d724-ed3f-39e2659b722f", "ce45f606-7e14-924c-7498-39e2659dc301");
		customerWithGISDataLocationMap.put("a521c5c1-96d7-1cc3-a014-39e21d73943b", "d5f86e2f-3b2e-943a-583b-39e21d75d68b");
		customerWithGISDataLocationMap.put("ebbeba32-68a2-4185-005a-39e19762dd00", "05c670df-607e-cd18-8ba9-39e197650be6");
		customerWithGISDataLocationMap.put("ef5bc973-aa51-4ba4-997c-39e182adb262", "58b62dd1-b8dc-a418-ad70-39e182afdecf");
		customerWithGISDataLocationMap.put("cb734061-8cd6-85db-6238-39e25b4c97e7", "23710b40-e8fc-9437-7107-39e25b4ee5f0");
		customerWithGISDataLocationMap.put("036d207a-9c01-f6ec-81e5-39e260724f70", "59284b5c-8049-284e-683a-39e260749eb9");
		customerWithGISDataLocationMap.put("837021d1-764b-cff8-2bc6-39e246b06ba8", "8654e78a-83bf-6217-f89c-39e246b2b4f5");
		customerWithGISDataLocationMap.put("2767ea04-032e-34ca-727c-39e2229dbf0a", "d6ee39f4-cb2a-c188-76c5-39e222a0005d");
		customerWithGISDataLocationMap.put("e3eee267-8ace-f482-436a-39e1d01d432d", "e1e10721-89f0-85dc-4c51-39e1d01f7b65");
		customerWithGISDataLocationMap.put("0191b2f3-023f-6c1a-62cc-39e18e6e2400", "9cb47d09-5c3e-64b6-4277-39e18e6e7d20");
		customerWithGISDataLocationMap.put("9eb91768-0f30-c59a-4db9-39e149bf86df", "550d8b83-c4f4-bb54-8db9-39e149c1a956");
		customerWithGISDataLocationMap.put("654e0a3d-ddbb-67dd-51f7-39e1b64a57b3", "809f2caf-9ba4-713a-187f-39e1b64c8b94");
		customerWithGISDataLocationMap.put("01b88814-a0e2-6ccb-fd86-39e24bd306c3", "3e1dee9a-0806-bbb3-3afa-39e24bd550c9");
		customerWithGISDataLocationMap.put("86d0e7df-b1d8-18d4-3636-39e23f12ffef", "145abc41-0c0c-bf4e-0c77-39e23f15492f");
		customerWithGISDataLocationMap.put("8b1af892-7a0b-436b-756e-39e14f314223", "2759bdd3-1f21-1031-a846-39e14f336744");
		customerWithGISDataLocationMap.put("eafdafff-de98-db0e-7d0f-39e187cf6ca4", "15ecd2f9-b6a7-46c9-4ec7-39e187d198f3");
		customerWithGISDataLocationMap.put("c84482a0-c888-eccf-1fcd-39e16e0a9fae", "35648228-821d-dbaa-b7f3-39e16e0cc868");
		customerWithGISDataLocationMap.put("f4f892cc-42e0-04e0-0ecf-39e168f074dd", "184235b0-839d-94a0-6890-39e168f29c9d");
		customerWithGISDataLocationMap.put("1e54f5c0-849b-e583-687a-39e1caeb7cc3", "ad4ab986-bb47-a0f7-f5e8-39e1caedb4ad");
		customerWithGISDataLocationMap.put("8a24d383-4070-9f33-b6f9-39e1c5c2917e", "e8027e5f-8cc2-25a6-cdfc-39e1c5c4c7c6");
		customerWithGISDataLocationMap.put("62e635ec-7e95-e579-8bc1-39e1ac2bd1f0", "35a04a75-3a99-464a-3ff7-39e1ac2e0494");
		customerWithGISDataLocationMap.put("896dee4b-4431-001f-69a7-39e17d82b5b6", "b7c9e090-5545-d425-db1d-39e17d84dfdd");
		customerWithGISDataLocationMap.put("02e6822f-0938-de98-4fb6-39e17332d489", "b2321928-3dd3-30e6-b4f3-39e17334fff4");
		customerWithGISDataLocationMap.put("329b4aec-1835-af38-3a5b-39e18cfc8a33", "51efe684-8029-be60-3b35-39e18cfeb61a");
		customerWithGISDataLocationMap.put("08d93469-40ca-a87b-8ced-39e1f711d9e6", "40247e30-5a25-6b74-c996-39e1f7141896");
		customerWithGISDataLocationMap.put("198af2b1-561f-2227-eef6-39e1da6704ff", "df7c6907-ae64-063e-99b6-39e1da6940b9");
		customerWithGISDataLocationMap.put("f03d9b29-077b-6f31-6574-39e1929b8251", "f8432e69-4360-09db-4aca-39e1929db042");
		customerWithGISDataLocationMap.put("f1a42a5e-bdad-df08-a1d9-39e1d015ba5c", "b16fbc9d-114c-2f5a-5655-39e1d018e060");
		customerWithGISDataLocationMap.put("30a55690-5ad1-005f-e7f7-39e2320e3910", "e355fe72-3e45-54c2-3ba0-39e232107d80");
		customerWithGISDataLocationMap.put("fbe8bddf-105e-f19d-2a9b-39e1ef1db870", "45ddecea-f0a6-b25e-0c1c-39e1ef1ff420");
		customerWithGISDataLocationMap.put("9ea8d045-7bd1-dfaa-3288-39e1c09804c5", "7006fbe2-0914-41c9-4b42-39e1c09a3a26");
		customerWithGISDataLocationMap.put("9e0577d2-1c66-70c1-2c01-39e241aee5b5", "17ac7921-935a-2542-ff44-39e241b12f09");
		customerWithGISDataLocationMap.put("72f4e425-fc81-9e4c-4db1-39e1a1dab153", "0dfdc7c4-c5f6-10d0-00af-39e1a1dce2c5");
		customerWithGISDataLocationMap.put("17d4e89c-5856-81bc-cda5-39e227bbb0e0", "7ebad2fa-9ec4-fcd0-0ac4-39e227be87f6");
		customerWithGISDataLocationMap.put("c2d97ab3-f87a-f69f-1d7c-39e227c323b2", "b06718d4-b40d-4f5a-9fd0-39e227c56786");
		customerWithGISDataLocationMap.put("15fe6dd1-2def-0f45-2885-39e19cb504b1", "6c021901-53d0-50c1-a959-39e19cb7352a");
		customerWithGISDataLocationMap.put("ccf1f061-1690-4438-134f-39e185186053", "75fdf2ed-4c5b-eda1-bda1-39e1851a8eb1");
		customerWithGISDataLocationMap.put("95343337-4f82-7504-24d8-39e163c8f399", "7f44355b-5a68-b024-bc09-39e163cb1b4a");
		customerWithGISDataLocationMap.put("5cf781a4-8f1c-faa7-8e86-39e18b07bc90", "d69344c6-3f15-943d-252a-39e18b0814ee");
		customerWithGISDataLocationMap.put("91c4ea82-ed7e-0100-c4bc-39e1785eea93", "3da7fe2d-563d-5af9-745b-39e17861157d");
		customerWithGISDataLocationMap.put("3a74fbe3-afed-5218-2445-39e18470d8fa", "70d2cb27-71be-0792-49c9-39e1847132bb");
		customerWithGISDataLocationMap.put("015e191a-d030-3a64-ca22-39e1e4d4bf88", "2555f535-fc24-5b53-a33b-39e1e4d6fa4b");
		customerWithGISDataLocationMap.put("14bb8521-6af6-e44c-af0c-39e1f70a8aae", "346ac02f-52c3-48d9-90f1-39e1f70d5a9b");
		customerWithGISDataLocationMap.put("937a94e1-f232-d7b6-0ab2-39e1544d426e", "6f895503-3777-3f4d-82b6-39e1544f6ce1");
		customerWithGISDataLocationMap.put("026a34c6-0ddf-c24c-e4cf-39e1b8632427", "d99254a0-549b-3bc5-3167-39e1b8638838");
		customerWithGISDataLocationMap.put("ef7e9d60-04d3-d267-3d7f-39e15986650f", "38dff272-a7a4-17f0-9b6a-39e159888c32");
		customerWithGISDataLocationMap.put("4551fac5-cafe-c117-8a73-39e1bb7ad27d", "e3931c9f-4967-54b1-c72d-39e1bb7d07a9");
		customerWithGISDataLocationMap.put("29afd9a9-f164-4971-5037-39e189a5df5b", "35c6b70a-aedb-6d48-cdf0-39e189a63a40");
		customerWithGISDataLocationMap.put("2dfb8821-e9f1-bedd-8a24-39e18410b127", "e88fe23f-d132-30e7-9f32-39e184110b00");
	}
}
