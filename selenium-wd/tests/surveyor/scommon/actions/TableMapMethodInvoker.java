package surveyor.scommon.actions;

import java.util.ArrayList;
import java.util.List;

import common.source.ExceptionUtility;
import common.source.Log;

public class TableMapMethodInvoker implements IMethodInvoker {

	public List<IMethodObserver> methodObservers;
	
	public TableMapMethodInvoker() {
		methodObservers = new ArrayList<IMethodObserver>();
	}	

	@Override
	public boolean invokeMethod(IActions action, String actionName, MethodParams methodParams) throws Exception {
		boolean result = true;
		List<Integer> rowIDList = methodParams.getDataRowIDList();
		// Handle the case when single rowID is specified or no rowID is specified.
		if (rowIDList != null && rowIDList.size() <= 1) {
			Integer dataRowID = -1;
			if (rowIDList.size() == 1) {
				dataRowID = rowIDList.get(0);
			}
			try {
				result = action.invokeAction(actionName, methodParams.getMethodData(), dataRowID);
			} catch (Exception e) {
				Log.error("ERROR in action: " + actionName + ". Exception Details: " + ExceptionUtility.getStackTraceString(e));
				throw e;
			}
		} else {	// Multiple rowsIDs specified.	
			for (Integer dataRowID : rowIDList) {
				methodParams.setDataRowID(dataRowID);
				try {
					result = action.invokeAction(actionName, methodParams.getMethodData(), dataRowID);
				} catch (Exception e) {
					Log.error(e.toString());
					throw e;
				}
				
				if (!result) {
					// stop processing if result is FALSE.
					methodParams.setResult(false);
					notifyObservers(methodParams);
					return false;
				} else {
					// continue processing if result is TRUE.
					methodParams.setResult(true);
					notifyObservers(methodParams);
				}
			}
		}
		return result;
	}

	@Override
	public void registerObserver(IMethodObserver methodObserver) {
		methodObservers.add(methodObserver);
	}

	@Override
	public void deregisterObserver(IMethodObserver methodObserver) {
		methodObservers.remove(methodObserver);
	}

	@Override
	public void notifyObservers(MethodParams methodParam) {
		for (IMethodObserver observer : methodObservers) {
			observer.updateResult(methodParam);
		}
	}
}
