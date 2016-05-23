package common.source;

public class ExceptionUtility {
	public static String getStackTraceString(Throwable ex) {
        String lineSeperator = BaseHelper.getLineSeperator();
		String result = ex.toString() + lineSeperator;
        StackTraceElement[] trace = ex.getStackTrace();
        for (int i=0;i<trace.length;i++) {
            result += trace[i].toString() + lineSeperator;
        }
        return result;
    }
}
