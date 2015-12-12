package surveyor.regression.source;

import org.junit.Test;

import surveyor.scommon.actions.ActionsExecutionEngine;

public class ActionsExecutionDriver {
	private ActionsExecutionEngine executionEngine = null;
	
	public ActionsExecutionDriver() {
		executionEngine = new ActionsExecutionEngine();
	}
	
	@Test
	public void startActionsTests() {
		System.out.println("Starting actions execution engine.");
		try {
			executionEngine.startExecution();
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}
		System.out.println("Done executing actions execution engine!");
	}
}
