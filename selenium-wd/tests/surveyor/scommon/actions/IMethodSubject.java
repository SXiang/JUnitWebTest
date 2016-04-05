package surveyor.scommon.actions;

public interface IMethodSubject {
	void registerObserver(IMethodObserver methodObserver);
	void deregisterObserver(IMethodObserver methodObserver);
	void notifyObservers(MethodParams methodParam);
}
