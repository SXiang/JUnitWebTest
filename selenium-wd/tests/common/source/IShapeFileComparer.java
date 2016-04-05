package common.source;

public interface IShapeFileComparer {
	void assertEquals(String file1Path, String file2Path) throws Exception;
}
