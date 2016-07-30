package common.source;

public class Redater {
	private double baseUnixTime;	
	private int iteration;
	
	public Redater(double baseUnixTime) {
		this.setBaseUnixTime(baseUnixTime);
		setIteration(0);
	}

	public double getNextUnixTime() {
		double nextTime = this.baseUnixTime + this.iteration;
		setIteration(this.iteration + 1);
		return nextTime;
	}

	private void setBaseUnixTime(double baseUnixTime) {
		this.baseUnixTime = baseUnixTime;
	}
	
	private void setIteration(int iteration) {
		this.iteration = iteration;
	}
}
