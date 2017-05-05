package surveyor.unittest.source;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import common.source.BaseHelper;
import common.source.Constants;
import common.source.FileUtility;
import common.source.Log;
import common.source.PDFUtility;
import common.source.PollManager;
import common.source.ProcessUtility;
import common.source.RegexUtility;
import common.source.TestSetup;
import surveyor.dataaccess.source.ConnectionFactory;
import surveyor.dataaccess.source.Report;
import surveyor.dataaccess.source.ReportJob;
import surveyor.scommon.source.ReportsBasePage;

public class CompareReportsFromMultipleEnvTest {

	private static final String PDF_ZIP_FILE_DOWNLOAD_COMPLIANCE_URL = "Reports/DownloadPdf?reportId=%s&ReportType=Compliance";
	private static final String PDF_ZIP_FILE_DOWNLOAD_ASSESSMENT_URL = "Reports/DownloadPdf?reportId=%s&ReportType=Assessment";
	private static final String VIEW_FILE_DOWNLOAD_URL = "Reports/DownloadReportView?id=%s";
	private static final String DOWNLOAD_FILE_CMD = "DownloadFile.cmd";

	private static final String ENV1_DB_IP_ADDRESS = "20.20.64.100";
	private static final String ENV1_DB_PORT_NO = "1433";
	private static final String ENV1_DB_NAME = "SurveyorDevelopment_blankDB_20170504";
	private static final String ENV1_DB_USER = "awssa";
	private static final String ENV1_DB_PASSWORD = "j!RuL1Gd7A";
	private static final String ENV1_LOGIN_USER = "AutomationAdmin";
	private static final String ENV1_LOGIN_PWD = "sqa#Picarro$0";
	private static final String ENV1_BASE_URL = "https://p3dev.picarro.com";

	private static final String ENV2_DB_IP_ADDRESS = "20.20.130.210";
	private static final String ENV2_DB_PORT_NO = "1433";
	private static final String ENV2_DB_NAME = "SurveyorSQAAuto_blankDB_20170420";
	private static final String ENV2_DB_USER = "awssa";
	private static final String ENV2_DB_PASSWORD = "3Vf763pSg2";
	private static final String ENV2_LOGIN_USER = "AutomationAdmin";
	private static final String ENV2_LOGIN_PWD = "sqa#Picarro$0";
	private static final String ENV2_BASE_URL = "https://p3sqaauto.picarro.com";

	private static final String COMPLIANCE_REPORT_TYPE_ID = "00000000-0000-0000-0001-000000000000";
	private static final String ASSESSMENT_REPORT_TYPE_ID = "00000000-0000-0000-0006-000000000000";
	private static final String EQ_REPORT_TYPE_ID = "00000000-0000-0000-0005-000000000000";

	private static final String MAP_REPORT_JOB_TYPE_ID = "00000000-0000-0000-0001-000000000000";
	private static final String EQMAP_REPORT_JOB_TYPE_ID = "00000000-0000-0000-0004-000000000000";

	private static TestSetup testSetup = null;
	private static ReportsBasePage reportsBasePage = null;
	private static PDFUtility pdfUtility = null;

	private static Connection env1Connection;
	private static Connection env2Connection;
	private static Environment environment1;
	private static Environment environment2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		initialize();

		reportsBasePage = new ReportsBasePage(null, null, testSetup, null);
		pdfUtility = new PDFUtility();

		// Initialize TestSetup to instantiate the TestContext.
		testSetup = new TestSetup(false /* skip initialization */);
		String rootPath;
		try {
			rootPath = TestSetup.getRootPath();
			testSetup.loadTestProperties(rootPath);
		} catch (IOException e) {
			Log.error(e.getMessage());
		}
		testSetup.initialize();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testCompareComplianceReportImages() {
		downloadExtractReportFiles(COMPLIANCE_REPORT_TYPE_ID, MAP_REPORT_JOB_TYPE_ID, "CR");
	}

	@Test
	public void testCompareAssessmentReportImages() {
		downloadExtractReportFiles(ASSESSMENT_REPORT_TYPE_ID, MAP_REPORT_JOB_TYPE_ID, "AS");
	}

	@Test
	public void testCompareEQReportImages() {
		downloadExtractReportFiles(EQ_REPORT_TYPE_ID, EQMAP_REPORT_JOB_TYPE_ID, "EQ");
	}

	/** Internal inner classes */

	private static class ReportInfo {
		private String reportTitle;
		private String reportId;
		private List<ReportJobInfo> reportJobInfos;

		public String getReportTitle() { return reportTitle; }
		public void setReportTitle(String reportTitle) { this.reportTitle = reportTitle; }

		public String getReportId() { return reportId; }
		public void setReportId(String reportId) { this.reportId = reportId; }

		public List<ReportJobInfo> getReportJobInfos() { return reportJobInfos; }
		public void setReportJobInfos(List<ReportJobInfo> reportJobInfos) { this.reportJobInfos = reportJobInfos; }

		public ReportInfo(String rptId, String rptTitle) {
			this.setReportId(rptId);
			this.setReportTitle(rptTitle);
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
		}
	}

	private static class ReportJobInfo {
		private String reportId;
		private String reportJobId;

		public String getReportId() { return reportId; }
		public void setReportId(String reportId) { this.reportId = reportId; }

		public String getReportJobId() { return reportJobId; }
		public void setReportJobId(String reportJobId) { this.reportJobId = reportJobId; }

		public ReportJobInfo(String rptJobId, String rptId) {
			this.setReportJobId(rptJobId);
			this.setReportId(rptId);
		}

		@Override
		public String toString() {
			return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
		}
	}

	private static class Environment {
		private String dbIPAddress;
		private String dbPortNo;
		private String dbName;
		private String dbUser;
		private String dbPassword;
		private String loginUser;
		private String loginPwd;
		private String baseUrl;

		public String getDbIPAddress() { return dbIPAddress; }
		public void setDbIPAddress(String dbIPAddress) { this.dbIPAddress = dbIPAddress; }

		public String getDbPortNo() { return dbPortNo;}
		public void setDbPortNo(String dbPortNo) { this.dbPortNo = dbPortNo;}

		public String getDbName() { return dbName; }
		public void setDbName(String dbName) { this.dbName = dbName; }

		public String getDbUser() { return dbUser; }
		public void setDbUser(String dbUser) { this.dbUser = dbUser; }

		public String getDbPassword() { return dbPassword; }
		public void setDbPassword(String dbPassword) { this.dbPassword = dbPassword; }

		public String getLoginUser() { return loginUser; }
		public void setLoginUser(String loginUser) { this.loginUser = loginUser; }

		public String getLoginPwd() { return loginPwd; }
		public void setLoginPwd(String loginPwd) { this.loginPwd = loginPwd; }

		public String getBaseUrl() { return baseUrl; }
		public void setBaseUrl(String baseUrl) { this.baseUrl = baseUrl; }

		public Environment(String ipAddress, String portNo, String dbName, String dbUser, String dbPassword, String loginUser, String loginPwd, String baseUrl) {
			this.setBaseUrl(baseUrl);
			this.setDbIPAddress(ipAddress);
			this.setDbName(dbName);
			this.setDbPassword(dbPassword);
			this.setDbPortNo(portNo);
			this.setDbUser(dbUser);
			this.setLoginPwd(loginPwd);
			this.setLoginUser(loginUser);
		}
	}

	/** Helper Methods */

	private static Connection createConnection(Environment environment) {
		String dbIpAddress = environment.getDbIPAddress();
		String dbPortNo = environment.getDbPortNo();
		String dbName = environment.getDbName();
		String dbUser = environment.getDbUser();
		String dbPassword = environment.getDbPassword();
		String connectionUrl = String.format(ConnectionFactory.JDBC_SQLSERVER_CONNECTION_STRING,
				dbIpAddress, dbPortNo, dbName);
		return ConnectionFactory.createNewConnection(dbUser, dbPassword, connectionUrl);
	}

	private static void initialize() {
		environment1 = new Environment(ENV1_DB_IP_ADDRESS, ENV1_DB_PORT_NO, ENV1_DB_NAME, ENV1_DB_USER, ENV1_DB_PASSWORD, ENV1_LOGIN_USER, ENV1_LOGIN_PWD, ENV1_BASE_URL);
		environment2 = new Environment(ENV2_DB_IP_ADDRESS, ENV2_DB_PORT_NO, ENV2_DB_NAME, ENV2_DB_USER, ENV2_DB_PASSWORD, ENV2_LOGIN_USER, ENV2_LOGIN_PWD, ENV2_BASE_URL);
		env1Connection = createConnection(environment1);
		env2Connection = createConnection(environment2);
	}

	private void downloadExtractPDFImages(Environment environment, Set<ReportInfo> reportInfosEnv, String reportPrefix) {
		String envShortName = RegexUtility.split(environment.getBaseUrl(), RegexUtility.DOT_SPLIT_REGEX_PATTERN).get(0).replace("https://", "").toUpperCase();
		reportInfosEnv.forEach(ri -> {
			List<String> downloadURLs = new ArrayList<String>();
			String downloadFileRelativeUrl = String.format(PDF_ZIP_FILE_DOWNLOAD_COMPLIANCE_URL, ri.getReportId());
			if (reportPrefix.equals("AS")) {
				downloadFileRelativeUrl = String.format(PDF_ZIP_FILE_DOWNLOAD_ASSESSMENT_URL, ri.getReportId());
				downloadURLs.add(downloadFileRelativeUrl);
			} else if (reportPrefix.equals("EQ")) {
				ri.getReportJobInfos().forEach(j -> {
					String relativeUrl = String.format(VIEW_FILE_DOWNLOAD_URL, j.getReportJobId());
					downloadURLs.add(relativeUrl);
				});
			}

			downloadURLs.stream().forEach(url -> {
				downloadExtractFileFromUrl(environment, ri, reportPrefix, envShortName, url);
			});
		});
	}

	private void downloadExtractFileFromUrl(Environment environment, ReportInfo rptInfo, String reportPrefix, String envShortName, String url) {
		try {
			String outputFileName = null;
			String subFolderName = null;
			String outputFileFullPath = null;
			String downloadFolder = null;
			List<String> filesInDir = null;
			if (reportPrefix.equals("EQ")) { // For EQ download the reportView images instead.
				outputFileName = getReportViewPDFFileName(environment, reportPrefix, rptInfo.getReportTitle(), true /* includeExtension */);
				subFolderName = outputFileName.replace(".pdf", "").substring(0, outputFileName.lastIndexOf('-'));
				outputFileFullPath = Paths.get(testSetup.getDownloadPath(), subFolderName, outputFileName).toString();
				FileUtility.createDirectoryIfNotExists(Paths.get(testSetup.getDownloadPath(), subFolderName).toString());
				downloadFolder = Paths.get(testSetup.getDownloadPath(), subFolderName).toString();
			} else {
				outputFileName = getReportPDFZipFileName(environment, reportPrefix, rptInfo.getReportTitle(), rptInfo.getReportId(), 0, true /* includeExtension */);
				subFolderName = outputFileName.replace(".zip", "");
				outputFileFullPath = Paths.get(testSetup.getDownloadPath(), outputFileName).toString();
				downloadFolder = testSetup.getDownloadPath();
			}

			downloadFile(environment, url, outputFileFullPath);
			waitForFileDownloadToComplete(outputFileName, downloadFolder);

			if (reportPrefix.equals("EQ")) {
				filesInDir = FileUtility.getFilesInDirectory(Paths.get(downloadFolder), true /*includeFullPath*/);
			} else {
				BaseHelper.deCompressZipFile(subFolderName, downloadFolder);
				filesInDir = FileUtility.getFilesInDirectory(Paths.get(downloadFolder, subFolderName), true /*includeFullPath*/);
			}

			final String subDirectoryName = subFolderName;
			String testCaseId = rptInfo.getReportTitle().substring(0, (rptInfo.getReportTitle().indexOf('-')>0) ? rptInfo.getReportTitle().indexOf('-') : rptInfo.getReportTitle().length());
			for (int i = 0; i < filesInDir.size(); i++) {
				String fileFullPath = filesInDir.get(i);
				String outputFolder = pdfUtility.extractPDFImages(fileFullPath, String.format("%s_%s-%d", envShortName, testCaseId, i));
				Log.info(String.format("Extracted to [%s] images from [%s] ", outputFolder, fileFullPath));
				// Move files back to base folder.
				List<String> outputFiles = FileUtility.getFilesInDirectory(Paths.get(outputFolder), true /*includeFullPath*/);
				outputFiles.forEach(f -> {
					try {
						Path path = Paths.get(f);
						Path inFolder = Paths.get(testSetup.getDownloadPath(), subDirectoryName);
						FileUtility.copyFile(f, Paths.get(inFolder.toString(), "images", path.getFileName().toString()).toString());
					} catch (Exception e) {
						e.printStackTrace();
					}
				});
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private static void downloadFile(Environment environment, String downloadFileRelativeUrl, String outputFileFullPath) {
		try {
			String workingFolder = TestSetup.getRootPath();
			String downloadFileCmdFolder = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "lib";
			String downloadFileCmdFullPath = downloadFileCmdFolder + File.separator + DOWNLOAD_FILE_CMD;
			String command = "cd \"" + downloadFileCmdFolder + "\" && " + downloadFileCmdFullPath +
					String.format(" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\" \"%s\"",
							workingFolder, environment.getBaseUrl(), downloadFileRelativeUrl, outputFileFullPath,
							environment.getLoginUser(),
							environment.getLoginPwd());
			Log.info("Downloading File. Command -> " + command);
			ProcessUtility.executeProcess(command, /* isShellCommand */ true, /* waitForExit */ true);
		} catch (IOException e) {
			Log.error(e.toString());
		}
	}

	private String getReportName(String reportId) {
		return reportId.substring(0, 10);
	}

	private void waitForFileDownloadToComplete(String fileName, String downloadPath) {
		PollManager.poll(() -> (!reportsBasePage.checkFileExists(fileName, downloadPath)),
				Constants.DEFAULT_WAIT_BETWEEN_POLL_IN_MSEC, Constants.DEFAULT_MAX_RETRIES);
	}

	private String getReportPDFZipFileName(Environment environment, String reportFilePrefix, String reportTitle, String reportId, int zipIndex, boolean includeExtension) {
		String envShortName = RegexUtility.split(environment.getBaseUrl(), RegexUtility.DOT_SPLIT_REGEX_PATTERN).get(0).replace("https://", "").toUpperCase();
		String reportName = envShortName + "_" + reportFilePrefix + "-" + getReportName(reportTitle) + "-PDF";
		reportName = getZipFileNameWithIndex(reportName, zipIndex);
		if (includeExtension) {
			reportName += ".zip";
		}
		return reportName;
	}

	private String getReportViewPDFFileName(Environment environment, String reportFilePrefix, String reportTitle, boolean includeExtension) {
		String envShortName = RegexUtility.split(environment.getBaseUrl(), RegexUtility.DOT_SPLIT_REGEX_PATTERN).get(0).replace("https://", "").toUpperCase();
		String reportViewName = envShortName + "_" + reportFilePrefix + "-" + getReportName(reportTitle) + "-View-" + TestSetup.getUUIDString();
		if (includeExtension) {
			reportViewName += ".pdf";
		}
		return reportViewName;
	}

	private String getZipFileNameWithIndex(String name, int zipIndex) {
		return zipIndex == 0 ? name : name + " (" + zipIndex + ")";
	}

	private boolean collectionContainsReport(Collection<ReportJobInfo> reportJobInfos, String reportId) {
		for (ReportJobInfo reportJobInfo : reportJobInfos) {
			if (reportJobInfo.getReportId().equals(reportId)) {
				return true;
			}
		}

		return false;
	}

	private List<ReportJobInfo> getMatchingReportJobInfos(Collection<ReportJobInfo> reportJobInfos, String reportId) {
		return reportJobInfos.stream()
			.filter(ri -> ri.getReportId().equals(reportId))
			.collect(Collectors.toList());
	}

	private List<ReportJobInfo> getReportJobInfosForReportInfo(ReportInfo rptInfo, String reportJobTypeId, Connection connection) {
		List<ReportJobInfo> rptJobInfoList = null;
		List<ReportJob> reportJobs = new ReportJob(connection).get(rptInfo.getReportId());
		if (reportJobs != null && reportJobs.size() > 0) {
			rptJobInfoList = new ArrayList<ReportJobInfo>(reportJobs.size());
			for (ReportJob reportJob : reportJobs) {
				if (reportJobTypeId.equals(String.valueOf(reportJob.getReportJobTypeId()))) {
					rptJobInfoList.add(new ReportJobInfo(String.valueOf(reportJob.getId()), String.valueOf(reportJob.getReportId())));
				}
			}
		}

		return rptJobInfoList;
	}

	// NOTE: This method will download all reports from environment1.
	//       To download only specific reports modify method to add additional filters.
	private void downloadExtractReportFiles(String reportTypeId, String reportJobTypeId, String reportPrefix) {
		// ENV1 -> Get ReportJobInfos for jobTypeId.
		Set<ReportJobInfo> reportJobInfosEnv1 = new ReportJob(env1Connection).getAll().stream()
			.filter(r -> r.getReportJobTypeId().equals(reportJobTypeId))
			.map(r -> new ReportJobInfo(String.valueOf(r.getId()), String.valueOf(r.getReportId())))
			.collect(Collectors.toSet());

		// ENV1 -> Get ReportInfos for ReportJobInfos.
		Set<ReportInfo> reportInfosEnv1 = new Report(env1Connection).getAll().stream()
			.filter(r -> r.getReportTypeId().equals(reportTypeId))
			.filter(r -> collectionContainsReport(reportJobInfosEnv1, r.getId()))
			.map(r -> new ReportInfo(r.getId(), r.getReportTitle()))
			.collect(Collectors.toSet());

		// ENV2 -> Get ReportInfos for ENV1 ReportInfos.
		Set<ReportInfo> reportInfosEnv2 = reportInfosEnv1.stream()
			.map(r -> r.getReportTitle().substring(0, (r.getReportTitle().indexOf('-')>0) ? r.getReportTitle().indexOf('-') : r.getReportTitle().length()))
			.map(rptTitle -> {Report rpt = new Report(env2Connection).getTitleLike(rptTitle); if (rpt != null) { return new ReportInfo(rpt.getId(), rpt.getReportTitle());} return null; })
			.collect(Collectors.toSet());

		// If EQ report populate ReportInfos w/ ReportJobInfo object.
		if (reportPrefix.equals("EQ")) {
			// ENV1
			for (Iterator<ReportInfo> it = reportInfosEnv1.iterator(); it.hasNext();) {
				ReportInfo reportInfo = it.next();
				reportInfo.setReportJobInfos(getMatchingReportJobInfos(reportJobInfosEnv1, reportInfo.getReportId()));
			}

			// ENV2
			for (Iterator<ReportInfo> it = reportInfosEnv2.iterator(); it.hasNext();) {
				ReportInfo reportInfo = it.next();
				reportInfo.setReportJobInfos(getReportJobInfosForReportInfo(reportInfo, reportJobTypeId, env2Connection));
			}
		}

		// ENV1 -> Save results for comparison
		downloadExtractPDFImages(environment1, reportInfosEnv1, reportPrefix);

		// ENV2 -> Save results for comparison
		downloadExtractPDFImages(environment2, reportInfosEnv2, reportPrefix);
	}
}