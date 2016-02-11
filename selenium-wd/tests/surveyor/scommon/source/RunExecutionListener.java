package surveyor.scommon.source;

import org.junit.runner.Description;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.junit.runner.notification.RunListener;

import com.relevantcodes.extentreports.LogStatus;

import common.source.ExceptionUtility;

public class RunExecutionListener extends RunListener {
    /**
     * Called when a Test Case Set = Test Class is started by the executor. 
     * 
     * @param description specified the just started test class 
     * @throws Exception in case of problem 
     */ 
    @Override 
    public void testRunStarted(final Description description) throws Exception { 
    }
    
    /**
     * Called when a Test method is started by the executor. 
     * 
     * @param description specifies the just started test method 
     * @throws Exception in case of problem 
     */ 
    @Override 
    public void testStarted(final Description description) throws Exception { 
    }
    
    /**
     * Called when a Test method is failed. 
     * 
     * @param failure holds the problem 
     * @throws Exception in case of problem 
     */ 
    @Override 
    public void testFailure(final Failure failure) throws Exception { 
    	SurveyorBaseTest.getExtentTest().log(LogStatus.FAIL, 
    			ExceptionUtility.getStackTraceString(failure.getException()));
    }
    
    /**
     * Called when a Test method is ignored/not executed. 
     * 
     * @param description specifies the just ignored test method 
     * @throws Exception in case of problem 
     */ 
    @Override 
    public void testIgnored(final Description description) throws Exception {
    	
    }
    
    /**
     * Called when a Test method execution is finished. 
     * 
     * @param description specifies the just executed test method 
     * @throws Exception in case of problem 
     */ 
    @Override 
    public void testFinished(final Description description) throws Exception {
    	
    }
    
    /**
     * Called when a Test Case Set = TestClass execution is finished by the executor. 
     * 
     * @param result gives info on the test class execution 
     * @throws Exception in case of problem 
     */ 
    @Override 
    public void testRunFinished(final Result result) throws Exception {
    	
    }
}