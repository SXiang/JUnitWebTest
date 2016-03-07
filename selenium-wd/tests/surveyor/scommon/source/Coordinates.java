package surveyor.scommon.source;

public class Coordinates {
	
	private int _x;
	private int _y;
	
	public Coordinates (int x_, int y_){
		this._x = x_;
		this._y = y_;
	}
	/**
	 * @return the x
	 */
	public int getX() {
		return _x;
	}
	/**
	 * @param x the x to set
	 */
	public void setX(int x) {
		this._x = x;
	}
	/**
	 * @return the y
	 */
	public int getY() {
		return _y;
	}
	/**
	 * @param y the y to set
	 */
	public void setY(int y) {
		this._y = y;
	}
	
	

}
