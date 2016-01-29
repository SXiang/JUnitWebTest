package surveyor.dataprovider;

import java.lang.reflect.Method;

public class DataAnnotations {
	public static String getRunAsUsers(Class<?> classObject, String methodName) {
		String users = null;
		Method[] methods = classObject.getMethods();
		if (methods != null) {
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					RunAs[] runAsAnnotation = method.getAnnotationsByType(RunAs.class);
					if (runAsAnnotation != null && runAsAnnotation.length > 0) {
						RunAs runAs = runAsAnnotation[0];
						users = runAs.users();
					}
					break;
				}
			}
		}
		return users;
	}
}
