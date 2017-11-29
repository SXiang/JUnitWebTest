package surveyor.dataprovider;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

import common.source.LoadTestJob;
import common.source.LoadTestExecutor.HttpMethod;

public class LoadAPITestDataProvider extends ReportDataProvider {

	public LoadAPITestDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	public static final String LOAD_TEST_API_PROVIDER_GEO_SERVER_DRIVERVIEW_API = "dataProviderProdGeoServerDriverViewAPITest";
	public static final String LOAD_TEST_API_PROVIDER_GEO_SERVER_MULTIPLE_CUSTOMERS_IN_PARALLEL = "dataProviderProdGeoServerMultipleCustomersInParallelAPITest";

	@DataProvider
	public static Object[][] dataProviderProdGeoServerDriverViewAPITest() {

		final String contentType = "application/x-www-form-urlencoded";
		final String requestBody = "username=PICARRO_VIEWER&password=PICARRO_VIEWER";
		final HttpMethod method = HttpMethod.POST;
		final Integer concurrentRequests = 100;
		final Integer requestsInOneSession = 10;
		final Integer numPrimingRuns = 1;

		final String testCaseName_1 = "GeoServer-DriverViewAPI-1";
		final String apiURL_1 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-122.003173828125+37.4138003506629%2c+-121.9921875+37.4138003506629%2c+-121.9921875+37.4050737501769%2c+-122.003173828125+37.4050737501769%2c+-122.003173828125+37.4138003506629)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_1 = 221354;

		final String testCaseName_2 = "GeoServer-DriverViewAPI-2";
		final String apiURL_2 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-121.981201171875+37.4050737501769%2c+-121.97021484375+37.4050737501769%2c+-121.97021484375+37.3963461331892%2c+-121.981201171875+37.3963461331892%2c+-121.981201171875+37.4050737501769)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_2 = 61252;

		final String testCaseName_3 = "GeoServer-DriverViewAPI-3";
		final String apiURL_3 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-122.003173828125+37.4050737501769%2c+-121.9921875+37.4050737501769%2c+-121.9921875+37.3963461331892%2c+-122.003173828125+37.3963461331892%2c+-122.003173828125+37.4050737501769)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_3 = 143152;

		final String testCaseName_4 = "GeoServer-DriverViewAPI-4";
		final String apiURL_4 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-121.9921875+37.4050737501769%2c+-121.981201171875+37.4050737501769%2c+-121.981201171875+37.3963461331892%2c+-121.9921875+37.3963461331892%2c+-121.9921875+37.4050737501769)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_4 = 81431;

		final String testCaseName_5 = "GeoServer-DriverViewAPI-5";
		final String apiURL_5 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-121.9921875+37.4138003506629%2c+-121.981201171875+37.4138003506629%2c+-121.981201171875+37.4050737501769%2c+-121.9921875+37.4050737501769%2c+-121.9921875+37.4138003506629)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_5 = 136672;

		final String testCaseName_6 = "GeoServer-DriverViewAPI-6";
		final String apiURL_6 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-121.981201171875+37.4138003506629%2c+-121.97021484375+37.4138003506629%2c+-121.97021484375+37.4050737501769%2c+-121.981201171875+37.4050737501769%2c+-121.981201171875+37.4138003506629)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_6 = 61902;

		final String testCaseName_7 = "GeoServer-DriverViewAPI-7";
		final String apiURL_7 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-121.97021484375+37.4050737501769%2c+-121.959228515625+37.4050737501769%2c+-121.959228515625+37.3963461331892%2c+-121.97021484375+37.3963461331892%2c+-121.97021484375+37.4050737501769)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_7 = 115056;

		final String testCaseName_8 = "GeoServer-DriverViewAPI-8";
		final String apiURL_8 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-121.97021484375+37.4138003506629%2c+-121.959228515625+37.4138003506629%2c+-121.959228515625+37.4050737501769%2c+-121.97021484375+37.4050737501769%2c+-121.97021484375+37.4138003506629)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_8 = 95760;

		final String testCaseName_9 = "GeoServer-DriverViewAPI-9";
		final String apiURL_9 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-121.959228515625+37.4050737501769%2c+-121.9482421875+37.4050737501769%2c+-121.9482421875+37.3963461331892%2c+-121.959228515625+37.3963461331892%2c+-121.959228515625+37.4050737501769)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_9 = 122128;

		final String testCaseName_10 = "GeoServer-DriverViewAPI-10";
		final String apiURL_10 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-121.959228515625+37.4138003506629%2c+-121.9482421875+37.4138003506629%2c+-121.9482421875+37.4050737501769%2c+-121.959228515625+37.4050737501769%2c+-121.959228515625+37.4138003506629)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_10 = 59212;

		final String testCaseName_11 = "GeoServer-DriverViewAPI-11";
		final String apiURL_11 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-121.959228515625+37.3963461331893%2c+-121.9482421875+37.3963461331893%2c+-121.9482421875+37.3876174997839%2c+-121.959228515625+37.3876174997839%2c+-121.959228515625+37.3963461331893)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_11 = 135301;

		final String testCaseName_12 = "GeoServer-DriverViewAPI-12";
		final String apiURL_12 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-121.97021484375+37.3963461331893%2c+-121.959228515625+37.3963461331893%2c+-121.959228515625+37.3876174997839%2c+-121.97021484375+37.3876174997839%2c+-121.97021484375+37.3963461331893)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_12 = 117647;

		final String testCaseName_13 = "GeoServer-DriverViewAPI-13";
		final String apiURL_13 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-121.9482421875+37.3963461331893%2c+-121.937255859375+37.3963461331893%2c+-121.937255859375+37.3876174997839%2c+-121.9482421875+37.3876174997839%2c+-121.9482421875+37.3963461331893)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_13 = 92076;

		final String testCaseName_14 = "GeoServer-DriverViewAPI-14";
		final String apiURL_14 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-121.9482421875+37.4050737501769%2c+-121.937255859375+37.4050737501769%2c+-121.937255859375+37.3963461331892%2c+-121.9482421875+37.3963461331892%2c+-121.9482421875+37.4050737501769)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_14 = 69114;

		final String testCaseName_15 = "GeoServer-DriverViewAPI-15";
		final String apiURL_15 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-121.937255859375+37.4050737501769%2c+-121.92626953125+37.4050737501769%2c+-121.92626953125+37.3963461331892%2c+-121.937255859375+37.3963461331892%2c+-121.937255859375+37.4050737501769)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_15 = 49426;

		final String testCaseName_16 = "GeoServer-DriverViewAPI-16";
		final String apiURL_16 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-121.937255859375+37.3963461331893%2c+-121.92626953125+37.3963461331893%2c+-121.92626953125+37.3876174997839%2c+-121.937255859375+37.3876174997839%2c+-121.937255859375+37.3963461331893)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_16 = 33629;

		final String testCaseName_17 = "GeoServer-DriverViewAPI-17";
		final String apiURL_17 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-121.9482421875+37.3876174997839%2c+-121.937255859375+37.3876174997839%2c+-121.937255859375+37.3788878500453%2c+-121.9482421875+37.3788878500453%2c+-121.9482421875+37.3876174997839)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_17 = 49028;

		final String testCaseName_18 = "GeoServer-DriverViewAPI-18";
		final String apiURL_18 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-121.959228515625+37.3876174997839%2c+-121.9482421875+37.3876174997839%2c+-121.9482421875+37.3788878500453%2c+-121.959228515625+37.3788878500453%2c+-121.959228515625+37.3876174997839)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_18 = 336396;

		final String testCaseName_19 = "GeoServer-DriverViewAPI-19";
		final String apiURL_19 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-121.981201171875+37.3876174997839%2c+-121.97021484375+37.3876174997839%2c+-121.97021484375+37.3788878500453%2c+-121.981201171875+37.3788878500453%2c+-121.981201171875+37.3876174997839)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_19 = 320846;

		final String testCaseName_20 = "GeoServer-DriverViewAPI-20";
		final String apiURL_20 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-121.97021484375+37.3876174997839%2c+-121.959228515625+37.3876174997839%2c+-121.959228515625+37.3788878500453%2c+-121.97021484375+37.3788878500453%2c+-121.97021484375+37.3876174997839)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_20 = 336104;

		final String testCaseName_21 = "GeoServer-DriverViewAPI-21";
		final String apiURL_21 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-121.981201171875+37.3963461331893%2c+-121.97021484375+37.3963461331893%2c+-121.97021484375+37.3876174997839%2c+-121.981201171875+37.3876174997839%2c+-121.981201171875+37.3963461331893)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_21 = 107221;

		final String testCaseName_22 = "GeoServer-DriverViewAPI-22";
		final String apiURL_22 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-121.9921875+37.3963461331893%2c+-121.981201171875+37.3963461331893%2c+-121.981201171875+37.3876174997839%2c+-121.9921875+37.3876174997839%2c+-121.9921875+37.3963461331893)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_22 = 301053;

		final String testCaseName_23 = "GeoServer-DriverViewAPI-23";
		final String apiURL_23 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-121.9921875+37.3876174997839%2c+-121.981201171875+37.3876174997839%2c+-121.981201171875+37.3788878500453%2c+-121.9921875+37.3788878500453%2c+-121.9921875+37.3876174997839)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_23 = 303420;

		final String testCaseName_24 = "GeoServer-DriverViewAPI-24";
		final String apiURL_24 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-122.003173828125+37.3963461331893%2c+-121.9921875+37.3963461331893%2c+-121.9921875+37.3876174997839%2c+-122.003173828125+37.3876174997839%2c+-122.003173828125+37.3963461331893)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_24 = 350390;

		final String testCaseName_25 = "GeoServer-DriverViewAPI-25";
		final String apiURL_25 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-121.9921875+37.3788878500453%2c+-121.981201171875+37.3788878500453%2c+-121.981201171875+37.3701571840575%2c+-121.9921875+37.3701571840575%2c+-121.9921875+37.3788878500453)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_25 = 86124;

		final String testCaseName_26 = "GeoServer-DriverViewAPI-26";
		final String apiURL_26 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-122.003173828125+37.3788878500453%2c+-121.9921875+37.3788878500453%2c+-121.9921875+37.3701571840575%2c+-122.003173828125+37.3701571840575%2c+-122.003173828125+37.3788878500453)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_26 = 125951;

		final String testCaseName_27 = "GeoServer-DriverViewAPI-27";
		final String apiURL_27 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-122.003173828125+37.3876174997839%2c+-121.9921875+37.3876174997839%2c+-121.9921875+37.3788878500453%2c+-122.003173828125+37.3788878500453%2c+-122.003173828125+37.3876174997839)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_27 = 118394;

		final String testCaseName_28 = "GeoServer-DriverViewAPI-28";
		final String apiURL_28 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-121.981201171875+37.3788878500453%2c+-121.97021484375+37.3788878500453%2c+-121.97021484375+37.3701571840575%2c+-121.981201171875+37.3701571840575%2c+-121.981201171875+37.3788878500453)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_28 = 96800;

		final String testCaseName_29 = "GeoServer-DriverViewAPI-29";
		final String apiURL_29 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-122.01416015625+37.3788878500453%2c+-122.003173828125+37.3788878500453%2c+-122.003173828125+37.3701571840575%2c+-122.01416015625+37.3701571840575%2c+-122.01416015625+37.3788878500453)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_29 = 109130;

		final String testCaseName_30 = "GeoServer-DriverViewAPI-30";
		final String apiURL_30 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-122.01416015625+37.3876174997839%2c+-122.003173828125+37.3876174997839%2c+-122.003173828125+37.3788878500453%2c+-122.01416015625+37.3788878500453%2c+-122.01416015625+37.3876174997839)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_30 = 88291;

		final String testCaseName_31 = "GeoServer-DriverViewAPI-31";
		final String apiURL_31 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-122.025146484375+37.3876174997839%2c+-122.01416015625+37.3876174997839%2c+-122.01416015625+37.3788878500453%2c+-122.025146484375+37.3788878500453%2c+-122.025146484375+37.3876174997839)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_31 = 124389;

		final String testCaseName_32 = "GeoServer-DriverViewAPI-32";
		final String apiURL_32 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-122.025146484375+37.3788878500453%2c+-122.01416015625+37.3788878500453%2c+-122.01416015625+37.3701571840575%2c+-122.025146484375+37.3701571840575%2c+-122.025146484375+37.3788878500453)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_32 = 108033;

		final String testCaseName_33 = "GeoServer-DriverViewAPI-33";
		final String apiURL_33 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-122.0361328125+37.3788878500453%2c+-122.025146484375+37.3788878500453%2c+-122.025146484375+37.3701571840575%2c+-122.0361328125+37.3701571840575%2c+-122.0361328125+37.3788878500453)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_33 = 112756;

		final String testCaseName_34 = "GeoServer-DriverViewAPI-34";
		final String apiURL_34 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-122.047119140625+37.3876174997839%2c+-122.0361328125+37.3876174997839%2c+-122.0361328125+37.3788878500453%2c+-122.047119140625+37.3788878500453%2c+-122.047119140625+37.3876174997839)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_34 = 145831;

		final String testCaseName_35 = "GeoServer-DriverViewAPI-35";
		final String apiURL_35 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-122.0361328125+37.3876174997839%2c+-122.025146484375+37.3876174997839%2c+-122.025146484375+37.3788878500453%2c+-122.0361328125+37.3788878500453%2c+-122.0361328125+37.3876174997839)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_35 = 138099;

		final String testCaseName_36 = "GeoServer-DriverViewAPI-36";
		final String apiURL_36 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-122.047119140625+37.3788878500453%2c+-122.0361328125+37.3788878500453%2c+-122.0361328125+37.3701571840575%2c+-122.047119140625+37.3701571840575%2c+-122.047119140625+37.3788878500453)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_36 = 206401;

		final String testCaseName_37 = "GeoServer-DriverViewAPI-37";
		final String apiURL_37 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-122.05810546875+37.3876174997839%2c+-122.047119140625+37.3876174997839%2c+-122.047119140625+37.3788878500453%2c+-122.05810546875+37.3788878500453%2c+-122.05810546875+37.3876174997839)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_37 = 230332;

		final String testCaseName_38 = "GeoServer-DriverViewAPI-38";
		final String apiURL_38 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-122.05810546875+37.3788878500453%2c+-122.047119140625+37.3788878500453%2c+-122.047119140625+37.3701571840575%2c+-122.05810546875+37.3701571840575%2c+-122.05810546875+37.3788878500453)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_38 = 206569;

		return new Object[][] {
			{ testCaseName_1, apiURL_1, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_1 },
			{ testCaseName_2, apiURL_2, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_2 },
			{ testCaseName_3, apiURL_3, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_3 },
			{ testCaseName_4, apiURL_4, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_4 },
			{ testCaseName_5, apiURL_5, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_5 },
			{ testCaseName_6, apiURL_6, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_6 },
			{ testCaseName_7, apiURL_7, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_7 },
			{ testCaseName_8, apiURL_8, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_8 },
			{ testCaseName_9, apiURL_9, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_9 },
			{ testCaseName_10, apiURL_10, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_10 },
			{ testCaseName_11, apiURL_11, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_11 },
			{ testCaseName_12, apiURL_12, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_12 },
			{ testCaseName_13, apiURL_13, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_13 },
			{ testCaseName_14, apiURL_14, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_14 },
			{ testCaseName_15, apiURL_15, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_15 },
			{ testCaseName_16, apiURL_16, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_16 },
			{ testCaseName_17, apiURL_17, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_17 },
			{ testCaseName_18, apiURL_18, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_18 },
			{ testCaseName_19, apiURL_19, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_19 },
			{ testCaseName_20, apiURL_20, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_20 },
			{ testCaseName_21, apiURL_21, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_21 },
			{ testCaseName_22, apiURL_22, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_22 },
			{ testCaseName_23, apiURL_23, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_23 },
			{ testCaseName_24, apiURL_24, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_24 },
			{ testCaseName_25, apiURL_25, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_25 },
			{ testCaseName_26, apiURL_26, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_26 },
			{ testCaseName_27, apiURL_27, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_27 },
			{ testCaseName_28, apiURL_28, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_28 },
			{ testCaseName_29, apiURL_29, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_29 },
			{ testCaseName_30, apiURL_30, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_30 },
			{ testCaseName_31, apiURL_31, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_31 },
			{ testCaseName_32, apiURL_32, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_32 },
			{ testCaseName_33, apiURL_33, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_33 },
			{ testCaseName_34, apiURL_34, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_34 },
			{ testCaseName_35, apiURL_35, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_35 },
			{ testCaseName_36, apiURL_36, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_36 },
			{ testCaseName_37, apiURL_37, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_37 },
			{ testCaseName_38, apiURL_38, contentType, requestBody, method, concurrentRequests, requestsInOneSession, numPrimingRuns, expectedResponseContentLength_38 },
		};
	}

	@DataProvider
	public static Object[][] dataProviderProdGeoServerMultipleCustomersInParallelAPITest() {

		final String contentType = "application/x-www-form-urlencoded";
		final String requestBody = "username=PICARRO_VIEWER&password=PICARRO_VIEWER";
		final HttpMethod method = HttpMethod.POST;
		final Integer concurrentRequests = 100;
		final Integer requestsInOneSession = 10;
		final Integer numPrimingRuns = 1;

		final String testCaseName_1 = "GeoServer-DriverViewAPI-1";
		final String apiURL_1 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-122.003173828125+37.4138003506629%2c+-121.9921875+37.4138003506629%2c+-121.9921875+37.4050737501769%2c+-122.003173828125+37.4050737501769%2c+-122.003173828125+37.4138003506629)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_1 = 221354;

		final String testCaseName_2 = "GeoServer-DriverViewAPI-2";
		final String apiURL_2 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-121.981201171875+37.4050737501769%2c+-121.97021484375+37.4050737501769%2c+-121.97021484375+37.3963461331892%2c+-121.981201171875+37.3963461331892%2c+-121.981201171875+37.4050737501769)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_2 = 61252;

		final String testCaseName_3 = "GeoServer-DriverViewAPI-3";
		final String apiURL_3 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-122.003173828125+37.4050737501769%2c+-121.9921875+37.4050737501769%2c+-121.9921875+37.3963461331892%2c+-122.003173828125+37.3963461331892%2c+-122.003173828125+37.4050737501769)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_3 = 143152;

		final String testCaseName_4 = "GeoServer-DriverViewAPI-4";
		final String apiURL_4 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-121.9921875+37.4050737501769%2c+-121.981201171875+37.4050737501769%2c+-121.981201171875+37.3963461331892%2c+-121.9921875+37.3963461331892%2c+-121.9921875+37.4050737501769)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_4 = 81431;

		final String testCaseName_5 = "GeoServer-DriverViewAPI-5";
		final String apiURL_5 = "http://30.30.150.198:8080/geoserver/Picarro/ows?service=WFS&&outputFormat=csv&version=1.0.0&service=WFS&request=GetFeature&typeName=Picarro:Asset&cql_filter=Intersects(the_geom%2cPOLYGON+((-121.9921875+37.4138003506629%2c+-121.981201171875+37.4138003506629%2c+-121.981201171875+37.4050737501769%2c+-121.9921875+37.4050737501769%2c+-121.9921875+37.4138003506629)))++AND+CustomerMa+IN+(%27%7bD08FC87F-F979-4131-92A9-3D82F37F4BBA%7d%27%2c%27%7bF3955E82-DD13-4842-84F7-502BCDA6B57A%7d%27%2c%27%7b44353E68-0694-4F05-85CB-84D753EA278C%7d%27%2c%27%7b96CAF1F5-D5C5-461D-9CE3-D210C20A1BB0%7d%27%2c%27%7bAD701312-C470-482A-BE45-EF37770E2CE6%7d%27%2c%27%7bF14735DE-6C9B-4423-8533-F243A7FE4E90%7d%27)";
		final Integer expectedResponseContentLength_5 = 136672;

		LoadTestJob testJob1 = new LoadTestJob();
		testJob1.setApiURL(apiURL_1)
				.setConcurrentRequests(concurrentRequests)
				.setContentType(contentType)
				.setExpectedResponseContentLength(expectedResponseContentLength_1)
				.setMethod(method)
				.setNumPrimingRuns(numPrimingRuns)
				.setRequestBody(requestBody)
				.setRequestsInOneSession(requestsInOneSession)
				.setTestCaseName(testCaseName_1);

		LoadTestJob testJob2 = new LoadTestJob();
		testJob2.setApiURL(apiURL_2)
				.setConcurrentRequests(concurrentRequests)
				.setContentType(contentType)
				.setExpectedResponseContentLength(expectedResponseContentLength_2)
				.setMethod(method)
				.setNumPrimingRuns(numPrimingRuns)
				.setRequestBody(requestBody)
				.setRequestsInOneSession(requestsInOneSession)
				.setTestCaseName(testCaseName_2);

		LoadTestJob testJob3 = new LoadTestJob();
		testJob3.setApiURL(apiURL_3)
				.setConcurrentRequests(concurrentRequests)
				.setContentType(contentType)
				.setExpectedResponseContentLength(expectedResponseContentLength_3)
				.setMethod(method)
				.setNumPrimingRuns(numPrimingRuns)
				.setRequestBody(requestBody)
				.setRequestsInOneSession(requestsInOneSession)
				.setTestCaseName(testCaseName_3);

		LoadTestJob testJob4 = new LoadTestJob();
		testJob4.setApiURL(apiURL_4)
				.setConcurrentRequests(concurrentRequests)
				.setContentType(contentType)
				.setExpectedResponseContentLength(expectedResponseContentLength_4)
				.setMethod(method)
				.setNumPrimingRuns(numPrimingRuns)
				.setRequestBody(requestBody)
				.setRequestsInOneSession(requestsInOneSession)
				.setTestCaseName(testCaseName_4);

		LoadTestJob testJob5 = new LoadTestJob();
		testJob5.setApiURL(apiURL_5)
				.setConcurrentRequests(concurrentRequests)
				.setContentType(contentType)
				.setExpectedResponseContentLength(expectedResponseContentLength_5)
				.setMethod(method)
				.setNumPrimingRuns(numPrimingRuns)
				.setRequestBody(requestBody)
				.setRequestsInOneSession(requestsInOneSession)
				.setTestCaseName(testCaseName_5);

		LoadTestJob[] jobs = { testJob1, testJob2, testJob3, testJob4, testJob5 };
		return new Object[][] {
			{ Arrays.asList(jobs) }
		};
	}
}