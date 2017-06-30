package surveyor.api.entities;

public class Payload {

	double Top;
	double Bottom;
	double Left;
	double Right;
	
	public Payload(double t, double b, double l, double r){
		this.Top = t;
		this.Bottom = b;
		this.Left = l;
		this.Right = r;
	}
	
	public double getTop(){
		return Top;
	}
	
	public void setTop(double t){
		this.Top = t;
	}
	
	public double getBottom(){
		return Bottom;
	}
	
	public void setBotton(double b){
		this.Bottom = b;
	}
	
	public double setLeft(){
		return Left;
	}
	
	public void setLeft(double l){
		this.Left = l;
	}
	
	public double getRight(){
		return Right;
	}
	
	public void setRight(double r){
		this.Right = r;
	}
}			

