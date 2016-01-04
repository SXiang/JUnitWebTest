package surveyor.regression.source;

import org.junit.Test;

import common.source.Constants;
import common.source.Log;
import surveyor.scommon.actions.ActionsExecutionEngine;
import surveyor.scommon.source.SurveyorBaseTest;

public class ActionsExecutionDriver extends SurveyorBaseTest {
	private ActionsExecutionEngine executionEngine = null;
	
	public ActionsExecutionDriver() {
		executionEngine = new ActionsExecutionEngine();
	}
	
	@Test
	public void TC_SimulatorTest_StartActionsEngineTests() {
		Log.info("Starting actions execution engine Driver View tests.");
		try {
			executionEngine.setTestGroupsToExecute(new String[] {Constants.DRIVER_VIEW_TEST_CASES} );
			executionEngine.startExecution();
		} catch (Exception e) {
			Log.error(e.toString());
			return;
		}
		Log.info("Done executing actions execution engine Driver View tests!");
	}

	@Test
	public void TC_ComplianceReportTests_StartActionsEngineTests() {
		Log.info("Starting actions execution engine Compliance Report tests.");
		try {
			executionEngine.setTestGroupsToExecute(new String[] {Constants.COMPLIANCE_REPORTS_TEST_CASES} ); 
			executionEngine.startExecution();
		} catch (Exception e) {
			Log.error(e.toString());
			return;
		}
		Log.info("Done executing actions execution engine Compliance Report tests!");
	}
}
