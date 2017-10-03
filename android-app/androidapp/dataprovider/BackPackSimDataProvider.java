package androidapp.dataprovider;

import org.junit.runners.model.InitializationError;

import com.tngtech.java.junit.dataprovider.DataProvider;

public class BackPackSimDataProvider extends AndroidDataProvider {
	public static final String BACKPACK_SIM_DATA_PROVIDER_TC2875 = "dataProviderBackPackSim_TC2875";
	public static final String BACKPACK_SIM_DATA_PROVIDER_TC2876 = "dataProviderBackPackSim_TC2876";
	public static final String BACKPACK_SIM_DATA_PROVIDER_TC2877 = "dataProviderBackPackSim_TC2877";

	public static final String DISPOSITION_1 = "1.0000000000E+00";
	public static final String DISPOSITION_2 = "2.0000000000E+00";
	public static final String DISPOSITION_3 = "3.0000000000E+00";
	public static final String DISPOSITION_4 = "4.0000000000E+00";
	public static final String DISPOSITION_5 = "5.0000000000E+00";

	public static final String ETHANE_RATIO_SDEV = "EthaneRatioSdev";
	public static final String ETHANE_RATIO = "EthaneRatio";
	public static final String SPECTRUM_ID = "SpectrumID";
	public static final String DISPOSITION = "Disposition";
	public static final String TIME = "TIME";
	public static final String CH4 = "CH4";

	private static final String ETHANE_RATIO_SDEV_0049 = "0.0049000000E+00";
	private static final String ETHANE_RATIO_MINUS_8 = "-8.0000000000E+00";
	private static final String SPECTRUM_ID_170 = "1.7000000000E+02";
	private static final String CH4_MINUS_1 = "-1.0000000000E+00";
	private static final String CH4_0 = "0.0000000000E+00";

	public BackPackSimDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@DataProvider
	public static Object[][] dataProviderBackPackSim_TC2875() {
		return new Object[][] {
			{ "TC2875" /* TestCaseID */, 18 /* userDataRowID */, 302 /* reportDataRowID1 */, new String[] { TIME } /* dataColumnName */ },
			{ "TC2875" /* TestCaseID */, 18 /* userDataRowID */, 302 /* reportDataRowID1 */, new String[] { CH4 } /* dataColumnName */ },
			{ "TC2875" /* TestCaseID */, 18 /* userDataRowID */, 302 /* reportDataRowID1 */, new String[] { ETHANE_RATIO } /* dataColumnName */ },
			{ "TC2875" /* TestCaseID */, 18 /* userDataRowID */, 302 /* reportDataRowID1 */, new String[] { ETHANE_RATIO_SDEV } /* dataColumnName */ },
			{ "TC2875" /* TestCaseID */, 18 /* userDataRowID */, 302 /* reportDataRowID1 */, new String[] { SPECTRUM_ID } /* dataColumnName */ }
		};
	}

	@DataProvider
	public static Object[][] dataProviderBackPackSim_TC2876() {
		return new Object[][] {
			{ "TC2876" /* TestCaseID */, 18 /* userDataRowID */, 303 /* reportDataRowID1 */, new String[] { DISPOSITION, SPECTRUM_ID, ETHANE_RATIO, ETHANE_RATIO_SDEV } /* dataColumnName */, new String[] { DISPOSITION_1, SPECTRUM_ID_170, ETHANE_RATIO_MINUS_8, ETHANE_RATIO_SDEV_0049 } /* dataColumnValue */ },
			{ "TC2876" /* TestCaseID */, 18 /* userDataRowID */, 303 /* reportDataRowID1 */, new String[] { DISPOSITION, SPECTRUM_ID, ETHANE_RATIO, ETHANE_RATIO_SDEV } /* dataColumnName */, new String[] { DISPOSITION_2, SPECTRUM_ID_170, ETHANE_RATIO_MINUS_8, ETHANE_RATIO_SDEV_0049 } /* dataColumnValue */ },
			{ "TC2876" /* TestCaseID */, 18 /* userDataRowID */, 303 /* reportDataRowID1 */, new String[] { DISPOSITION, SPECTRUM_ID, ETHANE_RATIO, ETHANE_RATIO_SDEV } /* dataColumnName */, new String[] { DISPOSITION_3, SPECTRUM_ID_170, ETHANE_RATIO_MINUS_8, ETHANE_RATIO_SDEV_0049 } /* dataColumnValue */ },
			{ "TC2876" /* TestCaseID */, 18 /* userDataRowID */, 303 /* reportDataRowID1 */, new String[] { DISPOSITION, SPECTRUM_ID, ETHANE_RATIO, ETHANE_RATIO_SDEV } /* dataColumnName */, new String[] { DISPOSITION_4, SPECTRUM_ID_170, ETHANE_RATIO_MINUS_8, ETHANE_RATIO_SDEV_0049 } /* dataColumnValue */ },
			{ "TC2876" /* TestCaseID */, 18 /* userDataRowID */, 303 /* reportDataRowID1 */, new String[] { DISPOSITION, SPECTRUM_ID, ETHANE_RATIO, ETHANE_RATIO_SDEV } /* dataColumnName */, new String[] { DISPOSITION_5, SPECTRUM_ID_170, ETHANE_RATIO_MINUS_8, ETHANE_RATIO_SDEV_0049 } /* dataColumnValue */ }
		};
	}

	@DataProvider
	public static Object[][] dataProviderBackPackSim_TC2877() {
		return new Object[][] {
			{ "TC2877" /* TestCaseID */, 18 /* userDataRowID */, 304 /* reportDataRowID1 */, new String[] { CH4 } /* dataColumnName */, new String[] { CH4_0 } /* dataColumnValue */ },
			{ "TC2877" /* TestCaseID */, 18 /* userDataRowID */, 304 /* reportDataRowID1 */, new String[] { CH4 } /* dataColumnName */, new String[] { CH4_MINUS_1 } /* dataColumnValue */ }
		};
	}
}