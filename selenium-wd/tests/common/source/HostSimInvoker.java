package common.source;

import java.io.File;
import java.io.IOException;

import groovy.lang.GroovyClassLoader;
import groovy.lang.GroovyObject;

public class HostSimInvoker {
	public void startReplay(String defFileName) throws InstantiationException, IllegalAccessException, IOException {
		String definition = loadDefinition(defFileName);
		this.invokeMethod("startReplay", definition);
	}
	
	public void startReplayDb3File(String db3FilePath) throws InstantiationException, IllegalAccessException, IOException {
		this.invokeMethod("startReplayDb3File", db3FilePath);
	}

	public void stopReplay() throws InstantiationException, IllegalAccessException, IOException {
		this.invokeMethod("stopReplay", null);
	}

	public void stop() throws InstantiationException, IllegalAccessException, IOException {
		this.invokeMethod("stop", null);
	}

	public void invokeMethod(String methodName, Object arg) throws IOException, InstantiationException, IllegalAccessException {
		@SuppressWarnings("resource")
		GroovyClassLoader classLoader = new GroovyClassLoader();
		@SuppressWarnings("rawtypes")
		String filePath = TestSetup.getExecutionPath(TestSetup.getRootPath()) + "tests" + File.separator + 
			"common"  + File.separator + "source"  + File.separator + "HostSim.groovy";
		
		Class parseClass = classLoader.parseClass(new File(filePath));
		GroovyObject newInstance = (GroovyObject)parseClass.newInstance();
		newInstance.invokeMethod(methodName, arg);
	}

	private String loadDefinition(String defFileName) throws IOException {
		File propertyfile = new File(".");
		String rootPath = propertyfile.getCanonicalPath();
		String defFilePath = rootPath + File.separator + "selenium-wd" + File.separator +"data" + File.separator + defFileName;		
		return FileUtility.readFileContents(defFilePath);		
	}
}
