package domain.hitbox;

public class Point {
	// this class holds values in double if wanted can return int

	private double x;
	private double y;

	public Point() {
		this.setDoubleX(0);
		this.setDoubleY(0);
	}

	public Point(double x, double y) {
		this.setDoubleX(x);
		this.setDoubleY(y);
	}

	public Point(int x, int y) {
		this.setX(x);
		this.setY(y);
	}

	public void setDoubleCoordinates(double x, double y) {
		this.setDoubleX(x);
		this.setDoubleY(y);
	}

	public void setIntCoordinates(int x, int y) {
		this.setX(x);
		this.setY(y);
	}


	public int getX() {
		return (int) x;
	}


	public void setX(int x) {
		this.x = (double) x;
	}


	public int getY() {
		return (int) y;
	}


	public void setY(int y) {
		this.y = (double) y;
	}


	public double getDoubleX() {
		return x;
	}

	public void setDoubleX(double x) {
		this.x = x;
	}


	public double getDoubleY() {
		return y;
	}

	public void setDoubleY(double y) {
		this.y = y;
	}



}
