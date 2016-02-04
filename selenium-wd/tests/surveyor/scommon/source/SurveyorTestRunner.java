package surveyor.scommon.source;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

/**
 * This is a custom test runner created to add the RunExecutionListener as a 
 * Listener to test runs.
 * Using RunExecutionListener we can get notified of when test passes, fails, etc
 * and get more information about the test failures (for eg. error callstack)
 * 
 * @author spulikkal
 *
 */
public class SurveyorTestRunner extends BlockJUnit4ClassRunner {

    public SurveyorTestRunner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override 
    public void run(RunNotifier notifier){
        notifier.addListener(new RunExecutionListener());
        super.run(notifier);
    }
}