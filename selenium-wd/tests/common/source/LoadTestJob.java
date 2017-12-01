package common.source;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import common.source.LoadTestExecutor.HttpMethod;

public class LoadTestJob {
	private String testCaseName;
	private String apiURL;
	private String contentType;
	private String username;
	private String password;
	private HttpMethod method;
	private Integer concurrentRequests;
	private Integer requestsInOneSession;
	private Integer numPrimingRuns;
	private Integer expectedResponseContentLength;

	public String getTestCaseName() {
		return testCaseName;
	}
	public LoadTestJob setTestCaseName(String testCaseName) {
		this.testCaseName = testCaseName;
		return this;
	}
	public String getApiURL() {
		return apiURL;
	}
	public LoadTestJob setApiURL(String apiURL) {
		this.apiURL = apiURL;
		return this;
	}
	public String getUsername() {
		return username;
	}
	public LoadTestJob setUsername(String username) {
		this.username = username;
		return this;
	}
	public String getPassword() {
		return password;
	}
	public LoadTestJob setPassword(String password) {
		this.password = password;
		return this;
	}
	public String getContentType() {
		return contentType;
	}
	public LoadTestJob setContentType(String contentType) {
		this.contentType = contentType;
		return this;
	}
	public HttpMethod getMethod() {
		return method;
	}
	public LoadTestJob setMethod(HttpMethod method) {
		this.method = method;
		return this;
	}
	public Integer getConcurrentRequests() {
		return concurrentRequests;
	}
	public LoadTestJob setConcurrentRequests(Integer concurrentRequests) {
		this.concurrentRequests = concurrentRequests;
		return this;
	}
	public Integer getRequestsInOneSession() {
		return requestsInOneSession;
	}
	public LoadTestJob setRequestsInOneSession(Integer requestsInOneSession) {
		this.requestsInOneSession = requestsInOneSession;
		return this;
	}
	public Integer getNumPrimingRuns() {
		return numPrimingRuns;
	}
	public LoadTestJob setNumPrimingRuns(Integer numPrimingRuns) {
		this.numPrimingRuns = numPrimingRuns;
		return this;
	}
	public Integer getExpectedResponseContentLength() {
		return expectedResponseContentLength;
	}
	public LoadTestJob setExpectedResponseContentLength(Integer expectedResponseContentLength) {
		this.expectedResponseContentLength = expectedResponseContentLength;
		return this;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.DEFAULT_STYLE);
	}
}
