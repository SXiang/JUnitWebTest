package surveyor.scommon.actions;

import common.source.TestContext;

public class ActionFunctions {

	public String GenerateRandomEmail(Integer size) {
		String emailPart = "@email.com";
		Integer genSize = size - emailPart.length();
		return String.format("%s%s", TestContext.INSTANCE.getTestSetup().getFixedSizePseudoRandomString(genSize), emailPart);
	}

	public String GenerateRandomString(Integer size) {
		return TestContext.INSTANCE.getTestSetup().getFixedSizePseudoRandomString(size);
	}

	public String GenerateRandomNumber(Integer size) {
		return TestContext.INSTANCE.getTestSetup().getFixedSizeRandomNumber(size);
	}
}
