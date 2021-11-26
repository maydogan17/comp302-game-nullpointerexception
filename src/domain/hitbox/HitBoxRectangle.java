package domain.hitbox;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HitBoxRectangle extends HitBox {
	
	private Dimension dim;
	private double angle;
	private Point rotatingCenter; //shooter rotates around this point.
	private RotCenterLoc rotCenLoc;
	private double rotationSpeed;
	
	public HitBoxRectangle(Dimension dim, RotCenterLoc rotCenLoc) {
		
		super.setCenter(null); // Center of the rectangle 
		this.setDim(dim); // Dimension of rectangle
		this.setAngle(0); // Angle of rectangle
		setRotCenLoc(rotCenLoc);
		setRotationSpeed(0);
		
	}
	
	public HitBoxRectangle(Point center, Dimension dim, int angle, RotCenterLoc rotCenLoc) {
		
		super.setCenter(center); // Center of the rectangle 
		this.setDim(dim); // Dimension of rectangle
		this.setAngle(angle); // Angle of rectangle
		setRotCenLoc(rotCenLoc);
		initRotatingCenter();
		setRotationSpeed(0);
	}
	
	private void initRotatingCenter() {
		// On this center that rectangle will rotate
				
		if(getRotCenLoc() == RotCenterLoc.BOTTOM_CENTER) {
			this.setRotatingCenter(new Point(getCenter().getDoubleX(),getCenter().getDoubleY() + getDim().getDoubleHeight()/2)); 
			
		}else {
			this.setRotatingCenter(new Point(getCenter().getDoubleX(), getCenter().getDoubleY()));
		}
	}
		
	private List<Point> getCornerPoints(Point calculationCenter){
		// This methods returns the corner points of the rectangle
		// It takes an argument calculation center because calculation should based on center that rotation occurs
		
		// Corner Points without calculating angle, when angle is 0
		List<Point> noncalcCornerPoints = new ArrayList<Point>();
		if(getRotCenLoc() == RotCenterLoc.BOTTOM_CENTER) {
			noncalcCornerPoints.add(new Point(calculationCenter.getDoubleX() + getDim().getDoubleWidth()/2, calculationCenter.getDoubleY() - getDim().getDoubleHeight())); //top right corner
			noncalcCornerPoints.add(new Point(calculationCenter.getDoubleX() - getDim().getDoubleWidth()/2, calculationCenter.getDoubleY() - getDim().getDoubleHeight())); //top left corner
			noncalcCornerPoints.add(new Point(calculationCenter.getDoubleX() + getDim().getDoubleWidth()/2, calculationCenter.getDoubleY())); //bottom right corner
			noncalcCornerPoints.add(new Point(calculationCenter.getDoubleX() - getDim().getDoubleWidth()/2, calculationCenter.getDoubleY())); //bottom left corner
		}else {
			noncalcCornerPoints.add(new Point(calculationCenter.getDoubleX() + getDim().getDoubleWidth()/2, calculationCenter.getDoubleY() - getDim().getDoubleHeight()/2)); //top right corner
			noncalcCornerPoints.add(new Point(calculationCenter.getDoubleX() - getDim().getDoubleWidth()/2, calculationCenter.getDoubleY() - getDim().getDoubleHeight()/2)); //top left corner
			noncalcCornerPoints.add(new Point(calculationCenter.getDoubleX() + getDim().getDoubleWidth()/2, calculationCenter.getDoubleY() + getDim().getDoubleHeight()/2)); //bottom right corner
			noncalcCornerPoints.add(new Point(calculationCenter.getDoubleX() - getDim().getDoubleWidth()/2, calculationCenter.getDoubleY() + getDim().getDoubleHeight()/2)); //bottom left corner
		}
		
		// Corner points after angle taken into account
		List<Point> cornerPoints = noncalcCornerPoints.stream().map(p -> HitBox.rotatePointAroundAnotherPointByAngle(p, calculationCenter, getAngle())).collect(Collectors.toList());
		
		return cornerPoints;
	}
	
		
	public void move(double speed) {
		this.getRotatingCenter().setDoubleCoordinates(getRotatingCenter().getDoubleX() + speed, getRotatingCenter().getDoubleY());
		this.getCenter().setDoubleCoordinates(getCenter().getDoubleX() + speed, getCenter().getDoubleY());

	}
	
	public void rotate(double turningSpeed) {
		this.setAngle(getAngle() + turningSpeed);

		this.setCenter(HitBox.rotatePointAroundAnotherPointByAngle(getCenter(), getRotatingCenter(), turningSpeed));
	}
	
	public void rotate() {
		rotate(getRotationSpeed());
	}
	

	@Override
	public void setCenterAndUpdate(Point center) {
		setCenter(center);
		initRotatingCenter();
		
	}

	@Override
	public int getBiggestX() {
		// returns biggest x coor in the hitbox
		List<Point> points = getCornerPoints(getRotatingCenter());
		Point storedP = points.get(0);
		for (Point p : points) {
			if(storedP.getDoubleX() <= p.getDoubleX()) {
				storedP = p;
			}
		}
		return storedP.getX();
	}

	@Override
	public int getSmallestX() {
		// returns smallest x coor in the hitbox
		List<Point> points = getCornerPoints(getRotatingCenter());
		Point storedP = points.get(0);
		for (Point p : points) {
			if(storedP.getDoubleX() >= p.getDoubleX()) {
				storedP = p;
			}
		}
		return storedP.getX();
	}

	@Override
	public int getBiggestY() {
		// returns biggest y coor in the hitbox
		List<Point> points = getCornerPoints(getRotatingCenter());
		Point storedP = points.get(0);
		for (Point p : points) {
			if(storedP.getDoubleY() <= p.getDoubleY()) {
				storedP = p;
			}
		}
		return storedP.getY();
	}

	@Override
	public int getSmallestY() {
		// returns smallest y coor in the hitbox
		List<Point> points = getCornerPoints(getRotatingCenter());
		Point storedP = points.get(0);
		for (Point p : points) {
			if(storedP.getDoubleY() >= p.getDoubleY()) {
				storedP = p;
			}
		}
		return storedP.getY();
	}
	
	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public Dimension getDim() {
		return dim;
	}

	public void setDim(Dimension dim) {
		this.dim = dim;
	}

	public Point getRotatingCenter() {
		return rotatingCenter;
	}

	public void setRotatingCenter(Point rotatingCenter) {
		this.rotatingCenter = rotatingCenter;
	}


	public RotCenterLoc getRotCenLoc() {
		return rotCenLoc;
	}

	public void setRotCenLoc(RotCenterLoc rotCenLoc) {
		this.rotCenLoc = rotCenLoc;
	}

	public double getRotationSpeed() {
		return rotationSpeed;
	}

	public void setRotationSpeed(double rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
	}
	


}
