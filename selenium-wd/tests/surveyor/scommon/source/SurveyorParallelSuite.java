package surveyor.scommon.source;

import org.junit.runner.notification.RunNotifier;
import org.junit.runners.model.InitializationError;
import org.junit.runners.model.RunnerBuilder;

import com.googlecode.junittoolbox.ParallelSuite;

public class SurveyorParallelSuite extends ParallelSuite {

    public SurveyorParallelSuite(Class<?> klass, RunnerBuilder builder) throws InitializationError {
    	super(klass, builder);
    }

    @Override 
    public void run(RunNotifier notifier){
        notifier.addListener(new RunExecutionListener());
        super.run(notifier);
    }
}