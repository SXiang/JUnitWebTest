package common.source;

import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import org.xml.sax.SAXException;

public class ProductTestBinariesMapExecutor {
	public static void main(String[] args) {
		if (args != null && args.length > 0) {
			String binaryFilePath = args[0];
			try {
				TestSetup testSetup = new TestSetup(true /* initialize */);
				String rootPath = TestSetup.getRootPath();
				testSetup.loadTestProperties(rootPath);
				testSetup.initializeDBProperties();
				TestContext.INSTANCE.setTestSetup(testSetup);
				TestContext.INSTANCE.getTestSetup().postProductTestBinariesMap(binaryFilePath);
			} catch (IOException | ParserConfigurationException | SAXException e) {
				e.printStackTrace();
			}
		}
	}
}