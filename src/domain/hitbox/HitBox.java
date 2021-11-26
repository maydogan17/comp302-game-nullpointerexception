package domain.hitbox;

import domain.kuvidgame.KuVidGame;
import domain.objects.WallType;

public abstract class HitBox {
	private Point center;

	public abstract int getBiggestX();

	public abstract int getSmallestX();

	public abstract int getBiggestY();

	public abstract int getSmallestY();
	
	public abstract void setCenterAndUpdate(Point center);

	public static Point rotatePointAroundAnotherPointByAngle(Point point, Point rotatingCenter, double angle) {
		// This method rotates a point around the center by angle
		double angleInRadian = Math.toRadians(angle);
		double newX = Math.cos(angleInRadian) * (point.getDoubleX() - rotatingCenter.getDoubleX())
				- Math.sin(angleInRadian) * (point.getDoubleY() - rotatingCenter.getDoubleY())
				+ rotatingCenter.getDoubleX();
		double newY = Math.sin(angleInRadian) * (point.getDoubleX() - rotatingCenter.getDoubleX())
				+ Math.cos(angleInRadian) * (point.getDoubleY() - rotatingCenter.getDoubleY())
				+ rotatingCenter.getDoubleY();

		return new Point(newX, newY);
	}

	public WallType doHitBoxCollideWithWall() {
		// If it hits a wall returns that wall otherwise returns null;

		int pW = KuVidGame.getInstance().getWindowWidth();
		int pH = KuVidGame.getInstance().getWindowHeight();
		
		
		
		if (getBiggestY() >= pH) {
			return WallType.WALL_BOTTOM;
		}
		if (getSmallestX() <= 0) {
			return WallType.WALL_LEFT;
		}
		if (getBiggestX() >= pW) {
			return WallType.WALL_RIGHT;
		}
		if (getSmallestY() <= 0) {
			return WallType.WALL_TOP;
		}
		
		return null;

	}

	public static double findDistanceBetweenPoints(Point c1, Point c2) {
		return Math.sqrt(Math.pow(Math.abs(c1.getDoubleX() - c2.getDoubleX()), 2)
				+ Math.pow(Math.abs(c1.getDoubleY() - c2.getDoubleY()), 2));
	}
	
	

	public static boolean doCollideTwoCircle(HitBoxCircle c1, HitBoxCircle c2) {
		double distanceBetweenCenters = findDistanceBetweenPoints(c1.getCenter(), c2.getCenter());

		return (int) distanceBetweenCenters <= (c1.getDiameter() + c2.getDiameter()) / 2;
	}

	public static boolean doCollideRectangleAndCircle(HitBoxRectangle r, HitBoxCircle c) {
		// the logic here is to rearrange their relative positions to O angle and
		// calculate then.
		Point newCenter; // center of the circle which is rotated angle around the rectangles center.
		newCenter = rotatePointAroundAnotherPointByAngle(c.getCenter(), r.getCenter(), r.getAngle());

		Point closestPoint = new Point(0, 0);
		double rSmallestX = r.getCenter().getDoubleX() - r.getDim().getDoubleWidth() / 2;
		double rBiggestX = r.getCenter().getDoubleX() + r.getDim().getDoubleWidth() / 2;
		double rSmallestY = r.getCenter().getDoubleY() - r.getDim().getDoubleHeight() / 2;
		double rBiggestY = r.getCenter().getDoubleY() + r.getDim().getDoubleHeight() / 2;

		// Find the closest x point on rectangle from center of unrotated(rotated
		// -angle) circle
		if (newCenter.getDoubleX() < rSmallestX)
			closestPoint.setDoubleX(rSmallestX);
		else if (newCenter.getDoubleX() > rBiggestX)
			closestPoint.setDoubleX(rBiggestX);
		else
			closestPoint.setDoubleX(newCenter.getDoubleX());

		// Find the closest y point on rectangle from center of unrotated(rotated
		// -angle) circle
		if (newCenter.getDoubleY() < rSmallestY)
			closestPoint.setDoubleY(rSmallestY);
		else if (newCenter.getDoubleY() > rBiggestY)
			closestPoint.setDoubleY(rBiggestY);
		else
			closestPoint.setDoubleY(newCenter.getDoubleY());

		// Determine collision
		boolean collision = false;

		double distance = findDistanceBetweenPoints(newCenter, closestPoint);
		if (distance < c.getRadius())
			collision = true; // Collision
		else
			collision = false;

		return collision;
	}
	
	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

}
