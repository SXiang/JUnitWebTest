package surveyor.scommon.source;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import common.source.BaseHelper;
import common.source.TestContext;

// [TODO]:
//    1. This implementation uses a random seed to avoid conflicts when multiple tests are executing at the same time.
//       Currently the number of test executions using this class is low and making this solution almost feasible.
//       The chances of conflict will increase as number of simultaneous test executions increase.
// [FUTURE IMPROVEMENT]:
//       Store state of Analyzers from pool in use in Automation DB and implement a locking mechanism
//       to guarantee no conflicts.
// [NOTES]:
//    1. For new Analyzers created using PageAction classes to fetch serial number from this pool
//       call function 'GetAnalyzerSerialNumberFromPool(-1)' in TestCaseData.xlsx|Analyzer tab
public enum AnalyzerSerialNumberPool {
	INSTANCE;

	private Integer runningIdx = 0;
	private final String SERIAL_NUM_PREFIX = "AutoTestAnalyzer";
	private List<String> analyzerPool = Collections.synchronizedList(new ArrayList<String>());

	private AnalyzerSerialNumberPool() {
		int num = Integer.valueOf(TestContext.INSTANCE.getTestSetup().getNumAnalyzersInPool());
		int minSize = 3;
		for (int i = 1; i <= num; i++) {
			String strI = String.valueOf(i);
			int len = strI.length();
			if (len < minSize) {
				strI = BaseHelper.prependStringWithChar(strI, '0', minSize - len);
			}
			analyzerPool.add(String.format("%s%s", SERIAL_NUM_PREFIX, strI));
		}
	}

	public String getAtIndex(int index) {
		if (analyzerPool.size() > index) {
			return analyzerPool.get(index);
		}

		return "";
	}

	public String fetchNext() {
		if (analyzerPool.size() == 0) {
			return "";
		}

		// grab random serial number from pool.
		runningIdx = new Random().nextInt(analyzerPool.size());

		if (runningIdx + 1 > analyzerPool.size()) {
			runningIdx = 0;
		}

		runningIdx++;
		return analyzerPool.get(runningIdx);
	}
}