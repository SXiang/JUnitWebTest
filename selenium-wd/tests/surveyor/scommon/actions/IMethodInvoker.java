package surveyor.scommon.actions;

public interface IMethodInvoker extends IMethodSubject {
	boolean invokeMethod(IPageActions pageAction, String actionName, MethodParams methodParams);
}

