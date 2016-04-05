package surveyor.scommon.actions;

public interface IActions {
	boolean invokeAction(String actionName, String data, Integer dataRowID) throws Exception;
}
