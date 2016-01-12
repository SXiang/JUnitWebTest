package surveyor.scommon.actions;

public interface IMethodInvoker extends IMethodSubject {
	boolean invokeMethod(IActions action, String actionName, MethodParams methodParams);
}

