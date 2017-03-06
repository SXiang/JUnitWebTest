package common.source;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import common.source.Log.LogField;

public class LogData {
	private String indexID;
	private String testMethod;
	private String testClass;
	private String testEnvironment;
	private String testBaseUrl;
	private String testCategory;

	public LogData() {
	}

	public String getIndexID() {
		return indexID;
	}

	public void setIndexID(String indexID) {
		this.indexID = indexID;
	}

	public String getTestMethod() {
		return testMethod;
	}

	public void setTestMethod(String testMethod) {
		this.testMethod = testMethod;
	}

	public String getTestClass() {
		return testClass;
	}

	public void setTestClass(String testClass) {
		this.testClass = testClass;
	}

	public String getTestEnvironment() {
		return testEnvironment;
	}

	public void setTestEnvironment(String testEnvironment) {
		this.testEnvironment = testEnvironment;
	}

	public String getTestBaseUrl() {
		return testBaseUrl;
	}

	public void setTestBaseUrl(String baseUrl) {
		this.testBaseUrl = baseUrl;
	}

	public String getTestCategory() {
		return testCategory;
	}

	public void setTestCategory(String testCategory) {
		this.testCategory = testCategory;
	}

	public Map<String,Object> toMap() {
		Map<String,Object> map = Collections.synchronizedMap(new HashMap<String,Object>());
		map.put(LogField.INDEX_ID.toString(), getIndexID());
		map.put(LogField.TEST_METHOD.toString(), getTestMethod());
		map.put(LogField.TEST_CLASS.toString(), getTestClass());
		map.put(LogField.TEST_ENVIROMENT.toString(), getTestEnvironment());
		map.put(LogField.TEST_URL.toString(), getTestBaseUrl());
		map.put(LogField.TEST_CATEGORY.toString(), getTestCategory());
		return map;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
}