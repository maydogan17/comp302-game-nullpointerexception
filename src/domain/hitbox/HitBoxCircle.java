package domain.hitbox;

public class HitBoxCircle extends HitBox {

	private double diameter;

	public HitBoxCircle(Point center, int diameter) {
		super.setCenter(center);
		this.setDiameter(diameter);
	}

	public HitBoxCircle(int diameter) {
		super.setCenter(null);
		this.setDiameter(diameter);
	}

	public HitBoxCircle(Point center) {
		super.setCenter(center);
		this.setDiameter(0);
	}

	public HitBoxCircle() {
		super.setCenter(null);
		this.setDiameter(0);
	}

	@Override
	public int getBiggestX() {
		// returns biggest x coor in the hitbox
		return getCenter().getX() + getDiameter() / 2;
	}

	@Override
	public int getSmallestX() {
		// returns smallest x coor in the hitbox
		return getCenter().getX() - getDiameter() / 2;
	}

	@Override
	public int getBiggestY() {
		// returns biggest y coor in the hitbox
		return getCenter().getY() + getDiameter() / 2;
	}

	@Override
	public int getSmallestY() {
		// returns smallest y coor in the hitbox
		return getCenter().getY() - getDiameter() / 2;
	}

	public int getRadius() {
		return (int) diameter / 2;
	}

	public int getDiameter() {
		return (int) diameter;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}

	@Override
	public void setCenterAndUpdate(Point center) {
		setCenter(center);
		
	}

}
