package surveyor.scommon.actions;

/**
 * Stores objects that need to be retained by PageAction classes.
 * @author spulikkal
 *
 */
public enum PageActionsStore {
	INSTANCE;

	/**
	 * Clears all the stored objects in page action classes.
	 * NOTES: 
	 * 1. This method is currently not thread-safe. 
	 * 2. For parallel execution we'll need to provide a separate space in the store for each test case.
	 */
	public void clearStore() {
		ComplianceReportsPageActions.clearStoredObjects();
		DriverViewPageActions.clearStoredObjects();
		LoginPageActions.clearStoredObjects();
		ManageCustomerPageActions.clearStoredObjects();
		ManageLocationPageActions.clearStoredObjects();
		ManageUsersPageActions.clearStoredObjects();
	}
}
