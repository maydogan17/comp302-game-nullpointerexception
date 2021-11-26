package domain.objects.path;

import domain.hitbox.Point;

public class ZigZagPath implements Path {

	private int startX, startY, endY, xDeviation, yDeviation, numSteps;
	private int currentSteps = 0, lastBouncedStep = 0;
	private Point currentPosition;
	private double deltaY, deltaX;

	public ZigZagPath(Point startingPoint, int endY, int xDeviation, int yDeviation, int numSteps) {
		this.setStartX(startingPoint.getX());
		this.setStartY(startingPoint.getY());
		this.setEndY(endY);
		this.setxDeviation(xDeviation);
		this.setyDeviation(yDeviation);
		this.setNumSteps(numSteps);
		this.setCurrentSteps(0);
		this.setCurrentPosition(startingPoint);
		this.setDeltas();
	}

	private void setDeltas() {
		this.setDeltaY((double) (getEndY() - getStartY()) / (double) getNumSteps());
		this.setDeltaX((((double) (getEndY() - getStartY()) / (double) getyDeviation()) * (double) getxDeviation())
				/ (double) getNumSteps());
	}

	private int getXLowerBound() {
		return getStartX() - getxDeviation();
	}

	private int getXUpperBound() {
		return getStartX() + getxDeviation();
	}

	@Override
	public boolean hasMoreSteps() {
		if (currentSteps < numSteps) {
			return true;
		} else {

			return false;
		}
	}
	
	public void bounce() {
		if(currentSteps - lastBouncedStep > 3) {
			lastBouncedStep = currentSteps;
			this.setDeltaX(-1 * getDeltaX());
		}
	}

	@Override
	public Point nextPosition() {
		double newY = getCurrentPosition().getDoubleY() + getDeltaY();
		double newX;
		if (getCurrentPosition().getX() + (int) getDeltaX() > getXUpperBound()
				|| getCurrentPosition().getX() + (int) getDeltaX() < getXLowerBound()) {
			this.setDeltaX(-1 * getDeltaX());
		}
		newX = getCurrentPosition().getDoubleX() + getDeltaX();
		getCurrentPosition().setDoubleX(newX);
		getCurrentPosition().setDoubleY(newY);
		setCurrentSteps(getCurrentSteps() + 1);
		return new Point(getCurrentPosition().getX(), getCurrentPosition().getY()); // To make it health
	}

	public int getStartX() {
		return startX;
	}

	public void setStartX(int startX) {
		this.startX = startX;
	}

	public int getStartY() {
		return startY;
	}

	public void setStartY(int startY) {
		this.startY = startY;
	}

	public int getEndY() {
		return endY;
	}

	public void setEndY(int endY) {
		this.endY = endY;
	}

	public int getxDeviation() {
		return xDeviation;
	}

	public void setxDeviation(int xDeviation) {
		this.xDeviation = xDeviation;
	}

	public int getNumSteps() {
		return numSteps;
	}

	public void setNumSteps(int numSteps) {
		this.numSteps = numSteps;
	}

	public int getCurrentSteps() {
		return currentSteps;
	}

	public void setCurrentSteps(int currentSteps) {
		this.currentSteps = currentSteps;
	}

	public double getDeltaY() {
		return deltaY;
	}

	public void setDeltaY(double deltaY) {
		this.deltaY = deltaY;
	}

	public double getDeltaX() {
		return deltaX;
	}

	public void setDeltaX(double deltaX) {
		this.deltaX = deltaX;
	}

	public int getyDeviation() {
		return yDeviation;
	}

	public void setyDeviation(int yDeviation) {
		this.yDeviation = yDeviation;
	}

	public Point getCurrentPosition() {
		return currentPosition;
	}

	public void setCurrentPosition(Point currentPosition) {
		this.currentPosition = currentPosition;
	}

	@Override
	public void setHitWall(boolean isHitWall) {
		// TODO Auto-generated method stub
		
	}

}
