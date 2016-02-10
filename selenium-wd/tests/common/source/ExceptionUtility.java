package common.source;

public class ExceptionUtility {
	public static String getStackTraceString(Throwable ex) {
        String result = ex.toString() + System.getProperty("line.separator");
        StackTraceElement[] trace = ex.getStackTrace();
        for (int i=0;i<trace.length;i++) {
            result += trace[i].toString() + System.getProperty("line.separator");
        }
        return result;
    }
}
