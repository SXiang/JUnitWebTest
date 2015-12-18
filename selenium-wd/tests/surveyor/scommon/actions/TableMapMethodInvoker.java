package surveyor.scommon.actions;

import java.util.ArrayList;
import java.util.List;

public class TableMapMethodInvoker implements IMethodInvoker {

	public List<IMethodObserver> methodObservers;
	
	public TableMapMethodInvoker() {
		methodObservers = new ArrayList<IMethodObserver>();
	}	

	@Override
	public boolean invokeMethod(IPageActions pageAction, String actionName, MethodParams methodParams) {
		boolean result = true;
		List<Integer> rowIDList = methodParams.getDataRowIDList();
		// Handle the case when single rowID is specified or no rowID is specified.
		if (rowIDList != null && rowIDList.size() <= 1) {
			Integer dataRowID = -1;
			if (rowIDList.size() == 1) {
				dataRowID = rowIDList.get(0);
			}
			try {
				result = pageAction.invokeAction(actionName, methodParams.getMethodData(), dataRowID);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {	// Multiple rowsIDs specified.	
			for (Integer dataRowID : rowIDList) {
				methodParams.setDataRowID(dataRowID);
				try {
					result = pageAction.invokeAction(actionName, methodParams.getMethodData(), dataRowID);
				} catch (Exception e) {
					e.printStackTrace();
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