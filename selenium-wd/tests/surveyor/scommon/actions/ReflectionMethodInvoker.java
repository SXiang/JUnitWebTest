package surveyor.scommon.actions;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class ReflectionMethodInvoker implements IMethodInvoker {

	public List<IMethodObserver> methodObservers;
	public Method method[];
	
	public ReflectionMethodInvoker() {
		methodObservers = new ArrayList<IMethodObserver>();
	}
	
	@Override
	public boolean invokeMethod(IPageActions pageAction, String actionName, MethodParams methodParams) {
		Object result = null;
		method = pageAction.getClass().getMethods();	
		for (int i=0; i< method.length; i++) {
			if(method[i].getName().equals(actionName)){
				// Execute the method for specified dataRowIds
				for (Integer dataRowID : methodParams.getDataRowIDList()) {
					methodParams.setDataRowID(dataRowID);
					
					try {
						result = method[i].invoke(pageAction, methodParams.getMethodData(), dataRowID);
					} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
						result = false;
					}
					
					if (!((boolean)result)) {
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
		}
		
		return true;
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
