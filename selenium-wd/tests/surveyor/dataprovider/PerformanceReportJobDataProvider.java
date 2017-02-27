package surveyor.dataprovider;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

public class PerformanceReportJobDataProvider extends ReportDataProvider {

	public enum ReportJobTestCategory {
		Light ("Light"),
		Medium ("Medium"),
		High ("High"),
		UltraHigh ("UltraHigh"),
		LargeArea ("LargeArea"),
		LargePipes ("LargePipes"),
		AssetBoxHighlightJob ("AssetBoxHighlightJob");

		private final String name;

		ReportJobTestCategory(String nm) {
			name = nm;
		}

		public String toString() {
			return this.name;
		}
	}

	private static final int WARMUP_USER_ROW_ID = 10;
	private static final int WARMUP_REPORT_DATA_ROW_ID = 155;
	private static final int WARMUP_EXECUTIONS_FOR_BASELINES = 1;

	private static final int LIGHT_LOAD3_USER_ROW_ID = 10;
	private static final int LIGHT_LOAD3_REPORT_DATA_ROW_ID = 10;
	private static final int LIGHT_LOAD3_EXECUTIONS_FOR_BASELINES = 20;

	private static final int MEDIUM_LOAD3_USER_ROW_ID = 10;
	private static final int MEDIUM_LOAD3_REPORT_DATA_ROW_ID = 11;
	private static final int MEDIUM_LOAD3_EXECUTIONS_FOR_BASELINES = 15;

	private static final int HIGH_LOAD2_USER_ROW_ID = 15;
	private static final int HIGH_LOAD2_REPORT_DATA_ROW_ID = 12;
	private static final int HIGH_LOAD2_EXECUTIONS_FOR_BASELINES = 10;

	private static final int HIGH_LOAD4_USER_ROW_ID = 15;
	private static final int HIGH_LOAD4_REPORT_DATA_ROW_ID = 106;
	private static final int HIGH_LOAD4_EXECUTIONS_FOR_BASELINES = 10;

	private static final int HIGH_LOAD5_USER_ROW_ID = 15;
	private static final int HIGH_LOAD5_REPORT_DATA_ROW_ID = 108;
	private static final int HIGH_LOAD5_EXECUTIONS_FOR_BASELINES = 10;

	private static final int HIGH_LOAD6_USER_ROW_ID = 10;
	private static final int HIGH_LOAD6_REPORT_DATA_ROW_ID = 110;
	private static final int HIGH_LOAD6_EXECUTIONS_FOR_BASELINES = 10;

	private static final int HIGH_LOAD7_USER_ROW_ID = 15;
	private static final int HIGH_LOAD7_REPORT_DATA_ROW_ID = 144;
	private static final int HIGH_LOAD7_EXECUTIONS_FOR_BASELINES = 10;

	private static final int HIGH_LOAD8_USER_ROW_ID = 10;
	private static final int HIGH_LOAD8_REPORT_DATA_ROW_ID = 147;
	private static final int HIGH_LOAD8_EXECUTIONS_FOR_BASELINES = 10;

	private static final int ULTRA_HIGH_LOAD1_USER_ROW_ID = 10;
	private static final int ULTRA_HIGH_LOAD1_REPORT_DATA_ROW_ID = 13;
	private static final int ULTRA_HIGH_LOAD1_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ULTRA_HIGH_LOAD4_USER_ROW_ID = 10;
	private static final int ULTRA_HIGH_LOAD4_REPORT_DATA_ROW_ID = 107;
	private static final int ULTRA_HIGH_LOAD4_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ULTRA_HIGH_LOAD5_USER_ROW_ID = 10;
	private static final int ULTRA_HIGH_LOAD5_REPORT_DATA_ROW_ID = 109;
	private static final int ULTRA_HIGH_LOAD5_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ULTRA_HIGH_LOAD6_USER_ROW_ID = 10;
	private static final int ULTRA_HIGH_LOAD6_REPORT_DATA_ROW_ID = 111;
	private static final int ULTRA_HIGH_LOAD6_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ULTRA_HIGH_LOAD7_USER_ROW_ID = 10;
	private static final int ULTRA_HIGH_LOAD7_REPORT_DATA_ROW_ID = 145;
	private static final int ULTRA_HIGH_LOAD7_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ULTRA_HIGH_LOAD8_USER_ROW_ID = 10;
	private static final int ULTRA_HIGH_LOAD8_REPORT_DATA_ROW_ID = 146;
	private static final int ULTRA_HIGH_LOAD8_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ULTRA_HIGH_LOAD9_USER_ROW_ID = 10;
	private static final int ULTRA_HIGH_LOAD9_REPORT_DATA_ROW_ID = 148;
	private static final int ULTRA_HIGH_LOAD9_EXECUTIONS_FOR_BASELINES = 5;

	private static final int LARGE_AREA1_USER_ROW_ID = 23;
	private static final int LARGE_AREA1_REPORT_DATA_ROW_ID = 149;
	private static final int LARGE_AREA1_EXECUTIONS_FOR_BASELINES = 5;

	private static final int LARGE_AREA2_USER_ROW_ID = 24;
	private static final int LARGE_AREA2_REPORT_DATA_ROW_ID = 150;
	private static final int LARGE_AREA2_EXECUTIONS_FOR_BASELINES = 5;

	private static final int LARGE_AREA3_USER_ROW_ID = 23;
	private static final int LARGE_AREA3_REPORT_DATA_ROW_ID = 151;
	private static final int LARGE_AREA3_EXECUTIONS_FOR_BASELINES = 5;

	private static final int LARGE_PIPES1_USER_ROW_ID = 24;
	private static final int LARGE_PIPES1_REPORT_DATA_ROW_ID = 152;
	private static final int LARGE_PIPES1_EXECUTIONS_FOR_BASELINES = 5;

	private static final int LARGE_PIPES2_USER_ROW_ID = 25;
	private static final int LARGE_PIPES2_REPORT_DATA_ROW_ID = 153;
	private static final int LARGE_PIPES2_EXECUTIONS_FOR_BASELINES = 5;

	private static final int LARGE_PIPES3_USER_ROW_ID = 25;
	private static final int LARGE_PIPES3_REPORT_DATA_ROW_ID = 154;
	private static final int LARGE_PIPES3_EXECUTIONS_FOR_BASELINES = 5;

	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_WARMUP_SCRIPT = "dataProviderReportJobPerformanceWarmup";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_LIGHT_LOAD = "dataProviderReportJobPerformanceLight";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_MEDIUM_LOAD = "dataProviderReportJobPerformanceMedium";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_HIGH_LOAD = "dataProviderReportJobPerformanceHigh";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ULTRAHIGH_LOAD = "dataProviderReportJobPerformanceUltraHigh";

	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_LARGEAREA = "dataProviderReportJobPerformanceLargeArea";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_LARGEPIPES = "dataProviderReportJobPerformanceLargePipes";

	// asset box highlight dataproviders
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX1 = "dataProviderReportJobPerformanceAssetBox1";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX2 = "dataProviderReportJobPerformanceAssetBox2";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX3 = "dataProviderReportJobPerformanceAssetBox3";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX4 = "dataProviderReportJobPerformanceAssetBox4";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX5 = "dataProviderReportJobPerformanceAssetBox5";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX6 = "dataProviderReportJobPerformanceAssetBox6";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX7 = "dataProviderReportJobPerformanceAssetBox7";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX8 = "dataProviderReportJobPerformanceAssetBox8";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX9 = "dataProviderReportJobPerformanceAssetBox9";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX10 = "dataProviderReportJobPerformanceAssetBox10";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX11 = "dataProviderReportJobPerformanceAssetBox11";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX12 = "dataProviderReportJobPerformanceAssetBox12";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX13 = "dataProviderReportJobPerformanceAssetBox13";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX14 = "dataProviderReportJobPerformanceAssetBox14";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX15 = "dataProviderReportJobPerformanceAssetBox15";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX16 = "dataProviderReportJobPerformanceAssetBox16";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX17 = "dataProviderReportJobPerformanceAssetBox17";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX18 = "dataProviderReportJobPerformanceAssetBox18";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX19 = "dataProviderReportJobPerformanceAssetBox19";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX20 = "dataProviderReportJobPerformanceAssetBox20";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX21 = "dataProviderReportJobPerformanceAssetBox21";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX22 = "dataProviderReportJobPerformanceAssetBox22";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX23 = "dataProviderReportJobPerformanceAssetBox23";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX24 = "dataProviderReportJobPerformanceAssetBox24";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX25 = "dataProviderReportJobPerformanceAssetBox25";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX26 = "dataProviderReportJobPerformanceAssetBox26";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX27 = "dataProviderReportJobPerformanceAssetBox27";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX28 = "dataProviderReportJobPerformanceAssetBox28";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX29 = "dataProviderReportJobPerformanceAssetBox29";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX30 = "dataProviderReportJobPerformanceAssetBox30";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX31 = "dataProviderReportJobPerformanceAssetBox31";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX32 = "dataProviderReportJobPerformanceAssetBox32";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX33 = "dataProviderReportJobPerformanceAssetBox33";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX34 = "dataProviderReportJobPerformanceAssetBox34";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX35 = "dataProviderReportJobPerformanceAssetBox35";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX36 = "dataProviderReportJobPerformanceAssetBox36";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX37 = "dataProviderReportJobPerformanceAssetBox37";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX38 = "dataProviderReportJobPerformanceAssetBox38";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX39 = "dataProviderReportJobPerformanceAssetBox39";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX40 = "dataProviderReportJobPerformanceAssetBox40";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX41 = "dataProviderReportJobPerformanceAssetBox41";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX42 = "dataProviderReportJobPerformanceAssetBox42";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX43 = "dataProviderReportJobPerformanceAssetBox43";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX44 = "dataProviderReportJobPerformanceAssetBox44";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX45 = "dataProviderReportJobPerformanceAssetBox45";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX46 = "dataProviderReportJobPerformanceAssetBox46";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX47 = "dataProviderReportJobPerformanceAssetBox47";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX48 = "dataProviderReportJobPerformanceAssetBox48";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX49 = "dataProviderReportJobPerformanceAssetBox49";
	public static final String REPORT_JOB_PERFORMANCE_PROVIDER_ASSETBOX50 = "dataProviderReportJobPerformanceAssetBox50";

	// asset box highlight test data rows
	private static final int ASSET_BOX_HIGHLIGHT1_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT1_REPORT_DATA_ROW_ID = 156;
	private static final int ASSET_BOX_HIGHLIGHT1_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT2_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT2_REPORT_DATA_ROW_ID = 157;
	private static final int ASSET_BOX_HIGHLIGHT2_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT3_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT3_REPORT_DATA_ROW_ID = 158;
	private static final int ASSET_BOX_HIGHLIGHT3_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT4_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT4_REPORT_DATA_ROW_ID = 159;
	private static final int ASSET_BOX_HIGHLIGHT4_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT5_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT5_REPORT_DATA_ROW_ID = 160;
	private static final int ASSET_BOX_HIGHLIGHT5_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT6_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT6_REPORT_DATA_ROW_ID = 161;
	private static final int ASSET_BOX_HIGHLIGHT6_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT7_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT7_REPORT_DATA_ROW_ID = 162;
	private static final int ASSET_BOX_HIGHLIGHT7_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT8_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT8_REPORT_DATA_ROW_ID = 163;
	private static final int ASSET_BOX_HIGHLIGHT8_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT9_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT9_REPORT_DATA_ROW_ID = 164;
	private static final int ASSET_BOX_HIGHLIGHT9_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT10_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT10_REPORT_DATA_ROW_ID = 165;
	private static final int ASSET_BOX_HIGHLIGHT10_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT11_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT11_REPORT_DATA_ROW_ID = 166;
	private static final int ASSET_BOX_HIGHLIGHT11_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT12_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT12_REPORT_DATA_ROW_ID = 167;
	private static final int ASSET_BOX_HIGHLIGHT12_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT13_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT13_REPORT_DATA_ROW_ID = 168;
	private static final int ASSET_BOX_HIGHLIGHT13_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT14_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT14_REPORT_DATA_ROW_ID = 169;
	private static final int ASSET_BOX_HIGHLIGHT14_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT15_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT15_REPORT_DATA_ROW_ID = 170;
	private static final int ASSET_BOX_HIGHLIGHT15_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT16_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT16_REPORT_DATA_ROW_ID = 171;
	private static final int ASSET_BOX_HIGHLIGHT16_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT17_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT17_REPORT_DATA_ROW_ID = 172;
	private static final int ASSET_BOX_HIGHLIGHT17_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT18_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT18_REPORT_DATA_ROW_ID = 173;
	private static final int ASSET_BOX_HIGHLIGHT18_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT19_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT19_REPORT_DATA_ROW_ID = 174;
	private static final int ASSET_BOX_HIGHLIGHT19_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT20_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT20_REPORT_DATA_ROW_ID = 175;
	private static final int ASSET_BOX_HIGHLIGHT20_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT21_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT21_REPORT_DATA_ROW_ID = 176;
	private static final int ASSET_BOX_HIGHLIGHT21_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT22_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT22_REPORT_DATA_ROW_ID = 177;
	private static final int ASSET_BOX_HIGHLIGHT22_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT23_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT23_REPORT_DATA_ROW_ID = 178;
	private static final int ASSET_BOX_HIGHLIGHT23_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT24_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT24_REPORT_DATA_ROW_ID = 179;
	private static final int ASSET_BOX_HIGHLIGHT24_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT25_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT25_REPORT_DATA_ROW_ID = 180;
	private static final int ASSET_BOX_HIGHLIGHT25_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT26_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT26_REPORT_DATA_ROW_ID = 181;
	private static final int ASSET_BOX_HIGHLIGHT26_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT27_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT27_REPORT_DATA_ROW_ID = 182;
	private static final int ASSET_BOX_HIGHLIGHT27_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT28_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT28_REPORT_DATA_ROW_ID = 183;
	private static final int ASSET_BOX_HIGHLIGHT28_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT29_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT29_REPORT_DATA_ROW_ID = 184;
	private static final int ASSET_BOX_HIGHLIGHT29_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT30_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT30_REPORT_DATA_ROW_ID = 185;
	private static final int ASSET_BOX_HIGHLIGHT30_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT31_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT31_REPORT_DATA_ROW_ID = 186;
	private static final int ASSET_BOX_HIGHLIGHT31_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT32_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT32_REPORT_DATA_ROW_ID = 187;
	private static final int ASSET_BOX_HIGHLIGHT32_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT33_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT33_REPORT_DATA_ROW_ID = 188;
	private static final int ASSET_BOX_HIGHLIGHT33_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT34_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT34_REPORT_DATA_ROW_ID = 189;
	private static final int ASSET_BOX_HIGHLIGHT34_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT35_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT35_REPORT_DATA_ROW_ID = 190;
	private static final int ASSET_BOX_HIGHLIGHT35_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT36_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT36_REPORT_DATA_ROW_ID = 191;
	private static final int ASSET_BOX_HIGHLIGHT36_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT37_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT37_REPORT_DATA_ROW_ID = 192;
	private static final int ASSET_BOX_HIGHLIGHT37_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT38_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT38_REPORT_DATA_ROW_ID = 193;
	private static final int ASSET_BOX_HIGHLIGHT38_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT39_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT39_REPORT_DATA_ROW_ID = 194;
	private static final int ASSET_BOX_HIGHLIGHT39_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT40_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT40_REPORT_DATA_ROW_ID = 195;
	private static final int ASSET_BOX_HIGHLIGHT40_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT41_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT41_REPORT_DATA_ROW_ID = 196;
	private static final int ASSET_BOX_HIGHLIGHT41_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT42_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT42_REPORT_DATA_ROW_ID = 197;
	private static final int ASSET_BOX_HIGHLIGHT42_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT43_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT43_REPORT_DATA_ROW_ID = 198;
	private static final int ASSET_BOX_HIGHLIGHT43_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT44_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT44_REPORT_DATA_ROW_ID = 199;
	private static final int ASSET_BOX_HIGHLIGHT44_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT45_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT45_REPORT_DATA_ROW_ID = 200;
	private static final int ASSET_BOX_HIGHLIGHT45_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT46_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT46_REPORT_DATA_ROW_ID = 201;
	private static final int ASSET_BOX_HIGHLIGHT46_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT47_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT47_REPORT_DATA_ROW_ID = 202;
	private static final int ASSET_BOX_HIGHLIGHT47_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT48_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT48_REPORT_DATA_ROW_ID = 203;
	private static final int ASSET_BOX_HIGHLIGHT48_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT49_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT49_REPORT_DATA_ROW_ID = 204;
	private static final int ASSET_BOX_HIGHLIGHT49_EXECUTIONS_FOR_BASELINES = 5;

	private static final int ASSET_BOX_HIGHLIGHT50_USER_ROW_ID = 23;
	private static final int ASSET_BOX_HIGHLIGHT50_REPORT_DATA_ROW_ID = 205;
	private static final int ASSET_BOX_HIGHLIGHT50_EXECUTIONS_FOR_BASELINES = 5;

	public PerformanceReportJobDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	public void run(RunNotifier notifier) {
		super.run(notifier);
	}

	@DataProvider
	public static Object[][] dataProviderReportJobPerformanceLight() {

		return new Object[][] {
			{ "TC1841" /*TestCaseID*/, LIGHT_LOAD3_USER_ROW_ID  /*userDataRowID*/,
			   LIGHT_LOAD3_REPORT_DATA_ROW_ID /*reportDataRowID*/,
			   LIGHT_LOAD3_EXECUTIONS_FOR_BASELINES /*number of times to execute this test when generating baselines*/,
			   ReportJobTestCategory.Light.toString()},
		};
	}

	@DataProvider
	public static Object[][] dataProviderReportJobPerformanceMedium() {

		return new Object[][] {
			{ "TC1842", MEDIUM_LOAD3_USER_ROW_ID, MEDIUM_LOAD3_REPORT_DATA_ROW_ID, MEDIUM_LOAD3_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.Medium.toString() },
		};
	}

	@DataProvider
	public static Object[][] dataProviderReportJobPerformanceWarmup() {

		return new Object[][] {
			{ "PerfWarmupScript", WARMUP_USER_ROW_ID, WARMUP_REPORT_DATA_ROW_ID, WARMUP_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.Medium.toString() },
		};
	}

	@DataProvider
	public static Object[][] dataProviderReportJobPerformanceHigh() {

		return new Object[][] {
			{ "TC1843-31", HIGH_LOAD8_USER_ROW_ID, HIGH_LOAD8_REPORT_DATA_ROW_ID, HIGH_LOAD8_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.High.toString() },
			{ "TC1843-11", HIGH_LOAD7_USER_ROW_ID, HIGH_LOAD7_REPORT_DATA_ROW_ID, HIGH_LOAD7_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.High.toString() },
			{ "TC1843-3", HIGH_LOAD6_USER_ROW_ID, HIGH_LOAD6_REPORT_DATA_ROW_ID, HIGH_LOAD6_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.High.toString() },
			// Disabling. DE2212 prevents this test case from completing.
			//{ "TC1843-2", HIGH_LOAD5_USER_ROW_ID, HIGH_LOAD5_REPORT_DATA_ROW_ID, HIGH_LOAD5_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.High.toString() },
			{ "TC1843-1", HIGH_LOAD4_USER_ROW_ID, HIGH_LOAD4_REPORT_DATA_ROW_ID, HIGH_LOAD4_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.High.toString() },
			{ "TC1843", HIGH_LOAD2_USER_ROW_ID, HIGH_LOAD2_REPORT_DATA_ROW_ID, HIGH_LOAD2_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.High.toString() }
		};
	}

	@DataProvider
	public static Object[][] dataProviderReportJobPerformanceUltraHigh() {

		return new Object[][] {
			{ "TC1844-31", ULTRA_HIGH_LOAD9_USER_ROW_ID, ULTRA_HIGH_LOAD9_REPORT_DATA_ROW_ID, ULTRA_HIGH_LOAD9_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.UltraHigh.toString() },
			{ "TC1844-21", ULTRA_HIGH_LOAD8_USER_ROW_ID, ULTRA_HIGH_LOAD8_REPORT_DATA_ROW_ID, ULTRA_HIGH_LOAD8_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.UltraHigh.toString() },
			{ "TC1844-11", ULTRA_HIGH_LOAD7_USER_ROW_ID, ULTRA_HIGH_LOAD7_REPORT_DATA_ROW_ID, ULTRA_HIGH_LOAD7_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.UltraHigh.toString() },
			{ "TC1844-3", ULTRA_HIGH_LOAD6_USER_ROW_ID, ULTRA_HIGH_LOAD6_REPORT_DATA_ROW_ID, ULTRA_HIGH_LOAD6_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.UltraHigh.toString() },
			{ "TC1844-2", ULTRA_HIGH_LOAD5_USER_ROW_ID, ULTRA_HIGH_LOAD5_REPORT_DATA_ROW_ID, ULTRA_HIGH_LOAD5_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.UltraHigh.toString() },
			{ "TC1844-1", ULTRA_HIGH_LOAD4_USER_ROW_ID, ULTRA_HIGH_LOAD4_REPORT_DATA_ROW_ID, ULTRA_HIGH_LOAD4_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.UltraHigh.toString() },
			{ "TC1844", ULTRA_HIGH_LOAD1_USER_ROW_ID, ULTRA_HIGH_LOAD1_REPORT_DATA_ROW_ID, ULTRA_HIGH_LOAD1_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.UltraHigh.toString() }
		};
	}

	@DataProvider
	public static Object[][] dataProviderReportJobPerformanceLargeArea() {

		return new Object[][] {
			// TODO: Disabled test case until DE2750 is fixed.
			//{ "TC2315-1", LARGE_AREA1_USER_ROW_ID, LARGE_AREA1_REPORT_DATA_ROW_ID, LARGE_AREA1_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.LargeArea.toString() },
			{ "TC2315-2", LARGE_AREA2_USER_ROW_ID, LARGE_AREA2_REPORT_DATA_ROW_ID, LARGE_AREA2_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.LargeArea.toString() },
			{ "TC2315-3", LARGE_AREA3_USER_ROW_ID, LARGE_AREA3_REPORT_DATA_ROW_ID, LARGE_AREA3_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.LargeArea.toString() },
		};
	}

	@DataProvider
	public static Object[][] dataProviderReportJobPerformanceLargePipes() {

		return new Object[][] {
			{ "TC2316-1", LARGE_PIPES1_USER_ROW_ID, LARGE_PIPES1_REPORT_DATA_ROW_ID, LARGE_PIPES1_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.LargePipes.toString() },
			{ "TC2316-2", LARGE_PIPES2_USER_ROW_ID, LARGE_PIPES2_REPORT_DATA_ROW_ID, LARGE_PIPES2_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.LargePipes.toString() },
			{ "TC2316-3", LARGE_PIPES3_USER_ROW_ID, LARGE_PIPES3_REPORT_DATA_ROW_ID, LARGE_PIPES3_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.LargePipes.toString() },
		};
	}

	// asset box highlight dataProvider methods
	@DataProvider
	public static Object[][] dataProviderReportJobPerformanceAssetBox1() {

		return new Object[][] {
			{ "TC2318-1", ASSET_BOX_HIGHLIGHT1_USER_ROW_ID, ASSET_BOX_HIGHLIGHT1_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT1_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-2", ASSET_BOX_HIGHLIGHT2_USER_ROW_ID, ASSET_BOX_HIGHLIGHT2_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT2_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-3", ASSET_BOX_HIGHLIGHT3_USER_ROW_ID, ASSET_BOX_HIGHLIGHT3_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT3_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-4", ASSET_BOX_HIGHLIGHT4_USER_ROW_ID, ASSET_BOX_HIGHLIGHT4_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT4_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-5", ASSET_BOX_HIGHLIGHT5_USER_ROW_ID, ASSET_BOX_HIGHLIGHT5_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT5_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-6", ASSET_BOX_HIGHLIGHT6_USER_ROW_ID, ASSET_BOX_HIGHLIGHT6_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT6_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-7", ASSET_BOX_HIGHLIGHT7_USER_ROW_ID, ASSET_BOX_HIGHLIGHT7_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT7_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-8", ASSET_BOX_HIGHLIGHT8_USER_ROW_ID, ASSET_BOX_HIGHLIGHT8_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT8_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-9", ASSET_BOX_HIGHLIGHT9_USER_ROW_ID, ASSET_BOX_HIGHLIGHT9_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT9_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-10", ASSET_BOX_HIGHLIGHT10_USER_ROW_ID, ASSET_BOX_HIGHLIGHT10_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT10_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
		};
	}

	@DataProvider
	public static Object[][] dataProviderReportJobPerformanceAssetBox2() {

		return new Object[][] {
			{ "TC2318-11", ASSET_BOX_HIGHLIGHT11_USER_ROW_ID, ASSET_BOX_HIGHLIGHT11_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT11_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-12", ASSET_BOX_HIGHLIGHT12_USER_ROW_ID, ASSET_BOX_HIGHLIGHT12_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT12_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-13", ASSET_BOX_HIGHLIGHT13_USER_ROW_ID, ASSET_BOX_HIGHLIGHT13_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT13_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-14", ASSET_BOX_HIGHLIGHT14_USER_ROW_ID, ASSET_BOX_HIGHLIGHT14_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT14_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-15", ASSET_BOX_HIGHLIGHT15_USER_ROW_ID, ASSET_BOX_HIGHLIGHT15_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT15_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-16", ASSET_BOX_HIGHLIGHT16_USER_ROW_ID, ASSET_BOX_HIGHLIGHT16_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT16_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-17", ASSET_BOX_HIGHLIGHT17_USER_ROW_ID, ASSET_BOX_HIGHLIGHT17_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT17_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-18", ASSET_BOX_HIGHLIGHT18_USER_ROW_ID, ASSET_BOX_HIGHLIGHT18_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT18_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-19", ASSET_BOX_HIGHLIGHT19_USER_ROW_ID, ASSET_BOX_HIGHLIGHT19_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT19_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-20", ASSET_BOX_HIGHLIGHT20_USER_ROW_ID, ASSET_BOX_HIGHLIGHT20_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT20_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
		};
	}

	@DataProvider
	public static Object[][] dataProviderReportJobPerformanceAssetBox3() {

		return new Object[][] {
			{ "TC2318-21", ASSET_BOX_HIGHLIGHT21_USER_ROW_ID, ASSET_BOX_HIGHLIGHT21_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT21_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-22", ASSET_BOX_HIGHLIGHT22_USER_ROW_ID, ASSET_BOX_HIGHLIGHT22_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT22_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-23", ASSET_BOX_HIGHLIGHT23_USER_ROW_ID, ASSET_BOX_HIGHLIGHT23_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT23_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-24", ASSET_BOX_HIGHLIGHT24_USER_ROW_ID, ASSET_BOX_HIGHLIGHT24_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT24_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-25", ASSET_BOX_HIGHLIGHT25_USER_ROW_ID, ASSET_BOX_HIGHLIGHT25_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT25_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-26", ASSET_BOX_HIGHLIGHT26_USER_ROW_ID, ASSET_BOX_HIGHLIGHT26_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT26_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-27", ASSET_BOX_HIGHLIGHT27_USER_ROW_ID, ASSET_BOX_HIGHLIGHT27_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT27_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-28", ASSET_BOX_HIGHLIGHT28_USER_ROW_ID, ASSET_BOX_HIGHLIGHT28_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT28_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-29", ASSET_BOX_HIGHLIGHT29_USER_ROW_ID, ASSET_BOX_HIGHLIGHT29_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT29_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-30", ASSET_BOX_HIGHLIGHT30_USER_ROW_ID, ASSET_BOX_HIGHLIGHT30_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT30_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
		};
	}

	@DataProvider
	public static Object[][] dataProviderReportJobPerformanceAssetBox4() {

		return new Object[][] {
			{ "TC2318-31", ASSET_BOX_HIGHLIGHT31_USER_ROW_ID, ASSET_BOX_HIGHLIGHT31_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT31_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-32", ASSET_BOX_HIGHLIGHT32_USER_ROW_ID, ASSET_BOX_HIGHLIGHT32_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT32_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-33", ASSET_BOX_HIGHLIGHT33_USER_ROW_ID, ASSET_BOX_HIGHLIGHT33_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT33_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-34", ASSET_BOX_HIGHLIGHT34_USER_ROW_ID, ASSET_BOX_HIGHLIGHT34_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT34_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-35", ASSET_BOX_HIGHLIGHT35_USER_ROW_ID, ASSET_BOX_HIGHLIGHT35_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT35_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-36", ASSET_BOX_HIGHLIGHT36_USER_ROW_ID, ASSET_BOX_HIGHLIGHT36_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT36_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-37", ASSET_BOX_HIGHLIGHT37_USER_ROW_ID, ASSET_BOX_HIGHLIGHT37_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT37_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-38", ASSET_BOX_HIGHLIGHT38_USER_ROW_ID, ASSET_BOX_HIGHLIGHT38_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT38_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-39", ASSET_BOX_HIGHLIGHT39_USER_ROW_ID, ASSET_BOX_HIGHLIGHT39_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT39_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-40", ASSET_BOX_HIGHLIGHT40_USER_ROW_ID, ASSET_BOX_HIGHLIGHT40_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT40_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
		};
	}

	@DataProvider
	public static Object[][] dataProviderReportJobPerformanceAssetBox5() {

		return new Object[][] {
			{ "TC2318-41", ASSET_BOX_HIGHLIGHT41_USER_ROW_ID, ASSET_BOX_HIGHLIGHT41_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT41_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-42", ASSET_BOX_HIGHLIGHT42_USER_ROW_ID, ASSET_BOX_HIGHLIGHT42_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT42_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-43", ASSET_BOX_HIGHLIGHT43_USER_ROW_ID, ASSET_BOX_HIGHLIGHT43_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT43_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-44", ASSET_BOX_HIGHLIGHT44_USER_ROW_ID, ASSET_BOX_HIGHLIGHT44_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT44_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-45", ASSET_BOX_HIGHLIGHT45_USER_ROW_ID, ASSET_BOX_HIGHLIGHT45_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT45_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-46", ASSET_BOX_HIGHLIGHT46_USER_ROW_ID, ASSET_BOX_HIGHLIGHT46_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT46_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-47", ASSET_BOX_HIGHLIGHT47_USER_ROW_ID, ASSET_BOX_HIGHLIGHT47_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT47_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-48", ASSET_BOX_HIGHLIGHT48_USER_ROW_ID, ASSET_BOX_HIGHLIGHT48_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT48_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-49", ASSET_BOX_HIGHLIGHT49_USER_ROW_ID, ASSET_BOX_HIGHLIGHT49_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT49_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
			{ "TC2318-50", ASSET_BOX_HIGHLIGHT50_USER_ROW_ID, ASSET_BOX_HIGHLIGHT50_REPORT_DATA_ROW_ID, ASSET_BOX_HIGHLIGHT50_EXECUTIONS_FOR_BASELINES, ReportJobTestCategory.AssetBoxHighlightJob.toString() },
		};
	}
}