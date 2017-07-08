package androidapp.dataprovider;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;

import surveyor.scommon.source.SurveyorTestRunner;

public class AndroidDataProvider extends SurveyorTestRunner {

	public AndroidDataProvider(Class<?> klass) throws InitializationError {
		super(klass);
	}

	@Override
	public void run(RunNotifier notifier) {
		super.run(notifier);
	}
}
