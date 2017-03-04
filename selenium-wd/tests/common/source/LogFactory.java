package common.source;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LogFactory {

	private static ThreadLocal<Logger> threadLocalLog = new ThreadLocal<Logger>() {
	    @Override
	    protected Logger initialValue() {
	    	return createDefaultLog();
	    }
	};

	private static ThreadLocal<Logger> threadLocalStashLog = new ThreadLocal<Logger>() {
	    @Override
	    protected Logger initialValue() {
	    	return createDefaultStashLog();
	    }
	};

	private static Logger createDefaultLog() {
		TestContext.INSTANCE.getTestSetup();
		try {
			System.setProperty("log4j.configurationFile", TestSetup.getExecutionPath(TestSetup.getRootPath()) + "log4j2" + File.separator + "log4j2.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return LogManager.getLogger(Log.class.getName());
	}

	private static Logger createDefaultStashLog() {
		TestContext.INSTANCE.getTestSetup();
		try {
			System.setProperty("log4j.configurationFile", TestSetup.getExecutionPath(TestSetup.getRootPath()) + "log4j2" + File.separator + "log4j2.xml");
		} catch (IOException e) {
			e.printStackTrace();
		}
		return LogManager.getLogger("logstash.json");
	}

	public static Logger getLog() {
		return threadLocalLog.get();
	}

	public static Logger getStashLog() {
		return threadLocalStashLog.get();
	}
}
