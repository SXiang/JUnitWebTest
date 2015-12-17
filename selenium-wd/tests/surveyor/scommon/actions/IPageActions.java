package surveyor.scommon.actions;

public interface IPageActions {
	boolean invokeAction(String actionName, String data, Integer dataRowID)  throws Exception;
}
