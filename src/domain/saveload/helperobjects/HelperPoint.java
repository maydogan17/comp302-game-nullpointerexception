package domain.saveload.helperobjects;

import domain.hitbox.Point;

public class HelperPoint {

	private double x;
	private double y;

	public HelperPoint() {

	}

	public HelperPoint(Point point) {
		setX(point.getDoubleX());
		setY(point.getDoubleY());
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
}
