package common.source;

public class Redater {
	private double baseUnixTime;	
	private int iteration;
	private double firstTime;
	private double lastTime;
	
	public Redater(double baseUnixTime) {
		this.setFirstTime(baseUnixTime);
		this.setBaseUnixTime(baseUnixTime);
		setIteration(0);
	}

	public double getNextUnixTime() {
		double nextTime = this.baseUnixTime + this.iteration;
		this.setLastTime(nextTime);
		this.setIteration(this.iteration + 1);
		return nextTime;
	}

	private void setBaseUnixTime(double baseUnixTime) {
		this.baseUnixTime = baseUnixTime;
	}
	
	private void setIteration(int iteration) {
		this.iteration = iteration;
	}

	public double getFirstTime() {
		return firstTime;
	}

	public void setFirstTime(double firstTime) {
		this.firstTime = firstTime;
	}

	public double getLastTime() {
		return lastTime;
	}

	public void setLastTime(double lastTime) {
		this.lastTime = lastTime;
	}
}
