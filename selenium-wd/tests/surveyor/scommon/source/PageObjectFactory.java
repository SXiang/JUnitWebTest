package surveyor.scommon.source;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

import common.source.TestContext;
import surveyor.scommon.source.AdministrationPage;
import surveyor.scommon.source.ComplianceReportsPage;
import surveyor.scommon.source.DriverViewPage;
import surveyor.scommon.source.EQReportsPage;
import surveyor.scommon.source.EULAPage;
import surveyor.scommon.source.FleetMapPage;
import surveyor.scommon.source.HomePage;
import surveyor.scommon.source.LoginPage;
import surveyor.scommon.source.ManageAnalyzersPage;
import surveyor.scommon.source.ManageCustomersPage;
import surveyor.scommon.source.ManageLocationsAdminPage;
import surveyor.scommon.source.ManageLocationsPage;
import surveyor.scommon.source.ManageRefGasBottlesAdminPage;
import surveyor.scommon.source.ManageRefGasBottlesPage;
import surveyor.scommon.source.ManageReleaseNotesPage;
import surveyor.scommon.source.ManageSurveyorAdminPage;
import surveyor.scommon.source.ManageSurveyorPage;
import surveyor.scommon.source.ManageUsersAdminPage;
import surveyor.scommon.source.ManageUsersPage;
import surveyor.scommon.source.MeasurementSessionsPage;
import surveyor.scommon.source.ObserverViewPage;
import surveyor.scommon.source.PreferencesPage;
import surveyor.scommon.source.SurveyorSystemsPage;
import surveyor.scommon.source.SurveyViewPage;

public class PageObjectFactory {
	private ThreadLocal<AdministrationPage> threadLocalAdministrationPage = new ThreadLocal<AdministrationPage>() {
	    @Override
	    protected AdministrationPage initialValue() {
	    	return createNewAdministrationPage();
	    }
	};

	private ThreadLocal<ComplianceReportsPage> threadLocalComplianceReportsPage = new ThreadLocal<ComplianceReportsPage>() {
	    @Override
	    protected ComplianceReportsPage initialValue() {
	    	return createNewComplianceReportsPage();
	    }
	};

	private ThreadLocal<DriverViewPage> threadLocalDriverViewPage = new ThreadLocal<DriverViewPage>() {
	    @Override
	    protected DriverViewPage initialValue() {
	    	return createNewDriverViewPage();
	    }
	};

	private ThreadLocal<EQReportsPage> threadLocalEqReportsPage = new ThreadLocal<EQReportsPage>() {
	    @Override
	    protected EQReportsPage initialValue() {
	    	return createNewEqReportsPage();
	    }
	};

	private ThreadLocal<FacilityEQReportsPage> threadLocalFacilityEqReportsPage = new ThreadLocal<FacilityEQReportsPage>() {
	    @Override
	    protected FacilityEQReportsPage initialValue() {
	    	return createNewFacilityEqReportsPage();
	    }
	};
	
	private ThreadLocal<EULAPage> threadLocalEULAPage = new ThreadLocal<EULAPage>() {
	    @Override
	    protected EULAPage initialValue() {
	    	return createNewEULAPage();
	    }
	};

	private ThreadLocal<FleetMapPage> threadLocalFleetMapPage = new ThreadLocal<FleetMapPage>() {
	    @Override
	    protected FleetMapPage initialValue() {
	    	return createNewFleetMapPage();
	    }
	};

	private ThreadLocal<HomePage> threadLocalHomePage = new ThreadLocal<HomePage>() {
	    @Override
	    protected HomePage initialValue() {
	    	return createNewHomePage();
	    }
	};

	private ThreadLocal<LoginPage> threadLocalLoginPage = new ThreadLocal<LoginPage>() {
	    @Override
	    protected LoginPage initialValue() {
	    	return createNewLoginPage();
	    }
	};

	private ThreadLocal<ManageAnalyzersPage> threadLocalManageAnalyzersPage = new ThreadLocal<ManageAnalyzersPage>() {
	    @Override
	    protected ManageAnalyzersPage initialValue() {
	    	return createNewManageAnalyzersPage();
	    }
	};

	private ThreadLocal<ManageCustomersPage> threadLocalManageCustomersPage = new ThreadLocal<ManageCustomersPage>() {
	    @Override
	    protected ManageCustomersPage initialValue() {
	    	return createNewManageCustomersPage();
	    }
	};

	private ThreadLocal<ManageLocationsAdminPage> threadLocalManageLocationsAdminPage = new ThreadLocal<ManageLocationsAdminPage>() {
	    @Override
	    protected ManageLocationsAdminPage initialValue() {
	    	return createNewManageLocationsAdminPage();
	    }
	};

	private ThreadLocal<ManageLocationsPage> threadLocalManageLocationsPage = new ThreadLocal<ManageLocationsPage>() {
	    @Override
	    protected ManageLocationsPage initialValue() {
	    	return createNewManageLocationsPage();
	    }
	};

	private ThreadLocal<ManageRefGasBottlesAdminPage> threadLocalManageRefGasBottlesAdminPage = new ThreadLocal<ManageRefGasBottlesAdminPage>() {
	    @Override
	    protected ManageRefGasBottlesAdminPage initialValue() {
	    	return createNewManageRefGasBottlesAdminPage();
	    }
	};

	private ThreadLocal<ManageRefGasBottlesPage> threadLocalManageRefGasBottlesPage = new ThreadLocal<ManageRefGasBottlesPage>() {
	    @Override
	    protected ManageRefGasBottlesPage initialValue() {
	    	return createNewManageRefGasBottlesPage();
	    }
	};

	private ThreadLocal<ManageReleaseNotesPage> threadLocalManageReleaseNotesPage = new ThreadLocal<ManageReleaseNotesPage>() {
	    @Override
	    protected ManageReleaseNotesPage initialValue() {
	    	return createNewManageReleaseNotesPage();
	    }
	};

	private ThreadLocal<ManageSurveyorAdminPage> threadLocalManageSurveyorAdminPage = new ThreadLocal<ManageSurveyorAdminPage>() {
	    @Override
	    protected ManageSurveyorAdminPage initialValue() {
	    	return createNewManageSurveyorAdminPage();
	    }
	};

	private ThreadLocal<ManageSurveyorPage> threadLocalManageSurveyorPage = new ThreadLocal<ManageSurveyorPage>() {
	    @Override
	    protected ManageSurveyorPage initialValue() {
	    	return createNewManageSurveyorPage();
	    }
	};

	private ThreadLocal<ManageUsersAdminPage> threadLocalManageUsersAdminPage = new ThreadLocal<ManageUsersAdminPage>() {
	    @Override
	    protected ManageUsersAdminPage initialValue() {
	    	return createNewManageUsersAdminPage();
	    }
	};

	private ThreadLocal<ManageUsersPage> threadLocalManageUsersPage = new ThreadLocal<ManageUsersPage>() {
	    @Override
	    protected ManageUsersPage initialValue() {
	    	return createNewManageUsersPage();
	    }
	};

	private ThreadLocal<MeasurementSessionsPage> threadLocalMeasurementSessionsPage = new ThreadLocal<MeasurementSessionsPage>() {
	    @Override
	    protected MeasurementSessionsPage initialValue() {
	    	return createNewMeasurementSessionsPage();
	    }
	};

	private ThreadLocal<ObserverViewPage> threadLocalObserverViewPage = new ThreadLocal<ObserverViewPage>() {
	    @Override
	    protected ObserverViewPage initialValue() {
	    	return createNewObserverViewPage();
	    }
	};

	private ThreadLocal<PreferencesPage> threadLocalPreferencesPage = new ThreadLocal<PreferencesPage>() {
	    @Override
	    protected PreferencesPage initialValue() {
	    	return createNewPreferencesPage();
	    }
	};

	private ThreadLocal<SurveyorSystemsPage> threadLocalSurveyorSystemsPage = new ThreadLocal<SurveyorSystemsPage>() {
	    @Override
	    protected SurveyorSystemsPage initialValue() {
	    	return createNewSurveyorSystemsPage();
	    }
	};

	private ThreadLocal<SurveyViewPage> threadLocalSurveyViewPage = new ThreadLocal<SurveyViewPage>() {
	    @Override
	    protected SurveyViewPage initialValue() {
	    	return createNewSurveyViewPage();
	    }
	};

	private ThreadLocal<AssessmentReportsPage> threadLocalAssessmentReportsPage = new ThreadLocal<AssessmentReportsPage>() {
	    @Override
	    protected AssessmentReportsPage initialValue() {
	    	return createNewAssessmentReportsPage();
	    }
	};
	
	private ThreadLocal<ServerLogPage> threadLocalServerLogPage = new ThreadLocal<ServerLogPage>() {
	    @Override
	    protected ServerLogPage initialValue() {
	    	return createNewServerLogPage();
	    }
	};

	protected AdministrationPage createNewAdministrationPage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		AdministrationPage administrationPage = new AdministrationPage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, administrationPage);
		return administrationPage;
	}

	protected ComplianceReportsPage createNewComplianceReportsPage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		ComplianceReportsPage complianceReportsPage = new ComplianceReportsPage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, complianceReportsPage);
		return complianceReportsPage;
	}

	protected DriverViewPage createNewDriverViewPage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		DriverViewPage driverViewPage = new DriverViewPage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, driverViewPage);
		return driverViewPage;
	}

	protected EQReportsPage createNewEqReportsPage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		EQReportsPage eqReportsPage = new EQReportsPage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, eqReportsPage);
		return eqReportsPage;
	}

	protected FacilityEQReportsPage createNewFacilityEqReportsPage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		FacilityEQReportsPage facilityEqReportsPage = new FacilityEQReportsPage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, facilityEqReportsPage);
		return facilityEqReportsPage;
	}
	
	protected EULAPage createNewEULAPage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		EULAPage eULAPage = new EULAPage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, eULAPage);
		return eULAPage;
	}

	protected FleetMapPage createNewFleetMapPage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		FleetMapPage fleetMapPage = new FleetMapPage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, fleetMapPage);
		return fleetMapPage;
	}

	protected HomePage createNewHomePage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		HomePage homePage = new HomePage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, homePage);
		return homePage;
	}

	protected LoginPage createNewLoginPage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		LoginPage loginPage = new LoginPage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, loginPage);
		return loginPage;
	}

	protected ManageAnalyzersPage createNewManageAnalyzersPage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		ManageAnalyzersPage manageAnalyzersPage = new ManageAnalyzersPage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, manageAnalyzersPage);
		return manageAnalyzersPage;
	}

	protected ManageCustomersPage createNewManageCustomersPage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		ManageCustomersPage manageCustomersPage = new ManageCustomersPage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, manageCustomersPage);
		return manageCustomersPage;
	}

	protected ManageLocationsAdminPage createNewManageLocationsAdminPage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		ManageLocationsAdminPage manageLocationsAdminPage = new ManageLocationsAdminPage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, manageLocationsAdminPage);
		return manageLocationsAdminPage;
	}

	protected ManageLocationsPage createNewManageLocationsPage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		ManageLocationsPage manageLocationsPage = new ManageLocationsPage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, manageLocationsPage);
		return manageLocationsPage;
	}

	protected ManageRefGasBottlesAdminPage createNewManageRefGasBottlesAdminPage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		ManageRefGasBottlesAdminPage manageRefGasBottlesAdminPage = new ManageRefGasBottlesAdminPage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, manageRefGasBottlesAdminPage);
		return manageRefGasBottlesAdminPage;
	}

	protected ManageRefGasBottlesPage createNewManageRefGasBottlesPage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		ManageRefGasBottlesPage manageRefGasBottlesPage = new ManageRefGasBottlesPage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, manageRefGasBottlesPage);
		return manageRefGasBottlesPage;
	}

	protected ManageReleaseNotesPage createNewManageReleaseNotesPage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		ManageReleaseNotesPage manageReleaseNotesPage = new ManageReleaseNotesPage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, manageReleaseNotesPage);
		return manageReleaseNotesPage;
	}

	protected ManageSurveyorAdminPage createNewManageSurveyorAdminPage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		ManageSurveyorAdminPage manageSurveyorAdminPage = new ManageSurveyorAdminPage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, manageSurveyorAdminPage);
		return manageSurveyorAdminPage;
	}

	protected ManageSurveyorPage createNewManageSurveyorPage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		ManageSurveyorPage manageSurveyorPage = new ManageSurveyorPage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, manageSurveyorPage);
		return manageSurveyorPage;
	}

	protected ManageUsersAdminPage createNewManageUsersAdminPage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		ManageUsersAdminPage manageUsersAdminPage = new ManageUsersAdminPage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, manageUsersAdminPage);
		return manageUsersAdminPage;
	}

	protected ManageUsersPage createNewManageUsersPage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		ManageUsersPage manageUsersPage = new ManageUsersPage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, manageUsersPage);
		return manageUsersPage;
	}

	protected MeasurementSessionsPage createNewMeasurementSessionsPage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		MeasurementSessionsPage measurementSessionsPage = new MeasurementSessionsPage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, measurementSessionsPage);
		return measurementSessionsPage;
	}

	protected ObserverViewPage createNewObserverViewPage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		ObserverViewPage observerViewPage = new ObserverViewPage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, observerViewPage);
		return observerViewPage;
	}

	protected PreferencesPage createNewPreferencesPage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		PreferencesPage preferencesPage = new PreferencesPage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, preferencesPage);
		return preferencesPage;
	}

	protected SurveyorSystemsPage createNewSurveyorSystemsPage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		SurveyorSystemsPage surveyorSystemsPage = new SurveyorSystemsPage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, surveyorSystemsPage);
		return surveyorSystemsPage;
	}

	protected SurveyViewPage createNewSurveyViewPage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		SurveyViewPage surveyViewPage = new SurveyViewPage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, surveyViewPage);
		return surveyViewPage;
	}

	protected AssessmentReportsPage createNewAssessmentReportsPage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		AssessmentReportsPage assessmentReportsPage = new AssessmentReportsPage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, assessmentReportsPage);
		return assessmentReportsPage;
	}
	
	protected ServerLogPage createNewServerLogPage() {
		WebDriver driver = TestContext.INSTANCE.getDriver();
		ServerLogPage serverLogPage = new ServerLogPage(driver, TestContext.INSTANCE.getBaseUrl(), TestContext.INSTANCE.getTestSetup());
		PageFactory.initElements(driver, serverLogPage);
		return serverLogPage;
	}

	public AdministrationPage getAdministrationPage() {
		return threadLocalAdministrationPage.get();
	}

	public ComplianceReportsPage getComplianceReportsPage() {
		return threadLocalComplianceReportsPage.get();
	}

	public DriverViewPage getDriverViewPage() {
		return threadLocalDriverViewPage.get();
	}

	public EQReportsPage getEqReportsPage() {
		return threadLocalEqReportsPage.get();
	}
	
	public FacilityEQReportsPage getFacilityEQReportsPage() {
		return threadLocalFacilityEqReportsPage.get();
	}
	
	public EULAPage getEULAPage() {
		return threadLocalEULAPage.get();
	}

	public FleetMapPage getFleetMapPage() {
		return threadLocalFleetMapPage.get();
	}

	public HomePage getHomePage() {
		return threadLocalHomePage.get();
	}

	public LoginPage getLoginPage() {
		return threadLocalLoginPage.get();
	}

	public ManageAnalyzersPage getManageAnalyzersPage() {
		return threadLocalManageAnalyzersPage.get();
	}

	public ManageCustomersPage getManageCustomersPage() {
		return threadLocalManageCustomersPage.get();
	}

	public ManageLocationsAdminPage getManageLocationsAdminPage() {
		return threadLocalManageLocationsAdminPage.get();
	}

	public ManageLocationsPage getManageLocationsPage() {
		return threadLocalManageLocationsPage.get();
	}

	public ManageRefGasBottlesAdminPage getManageRefGasBottlesAdminPage() {
		return threadLocalManageRefGasBottlesAdminPage.get();
	}

	public ManageRefGasBottlesPage getManageRefGasBottlesPage() {
		return threadLocalManageRefGasBottlesPage.get();
	}

	public ManageReleaseNotesPage getManageReleaseNotesPage() {
		return threadLocalManageReleaseNotesPage.get();
	}

	public ManageSurveyorAdminPage getManageSurveyorAdminPage() {
		return threadLocalManageSurveyorAdminPage.get();
	}

	public ManageSurveyorPage getManageSurveyorPage() {
		return threadLocalManageSurveyorPage.get();
	}

	public ManageUsersAdminPage getManageUsersAdminPage() {
		return threadLocalManageUsersAdminPage.get();
	}

	public ManageUsersPage getManageUsersPage() {
		return threadLocalManageUsersPage.get();
	}

	public MeasurementSessionsPage getMeasurementSessionsPage() {
		return threadLocalMeasurementSessionsPage.get();
	}

	public ObserverViewPage getObserverViewPage() {
		return threadLocalObserverViewPage.get();
	}

	public PreferencesPage getPreferencesPage() {
		return threadLocalPreferencesPage.get();
	}

	public SurveyorSystemsPage getSurveyorSystemsPage() {
		return threadLocalSurveyorSystemsPage.get();
	}

	public SurveyViewPage getSurveyViewPage() {
		return threadLocalSurveyViewPage.get();
	}

	public AssessmentReportsPage getAssessmentReportsPage() {
		return threadLocalAssessmentReportsPage.get();
	}
	
	public ServerLogPage getServerLogPage() {
		return threadLocalServerLogPage.get();
	}
}