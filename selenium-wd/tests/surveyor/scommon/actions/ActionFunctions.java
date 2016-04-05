package surveyor.scommon.actions;

import common.source.TestContext;

public class ActionFunctions {
	public String GenerateRandomString(Integer size) {
		return TestContext.INSTANCE.getTestSetup().getFixedSizePseudoRandomString(size);
	}

	public String GenerateRandomNumber(Integer size) {
		return TestContext.INSTANCE.getTestSetup().getFixedSizeRandomNumber(size);
	}
}
