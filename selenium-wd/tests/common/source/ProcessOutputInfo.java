package common.source;

public class ProcessOutputInfo {
	private Process process;
	private String output;
	private String error;
	
	public ProcessOutputInfo(Process process, String output, String error) {
		this.process = process;
		this.output = output;
		this.error = error;
	}
	
	public Process getProcess() {
		return process;
	}
	public void setProcess(Process process) {
		this.process = process;
	}
	public String getOutput() {
		return output;
	}
	public void setOutput(String output) {
		this.output = output;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
}
