package domain.objects.path;

import domain.hitbox.Point;
import domain.kuvidgame.KuVidGame;

public class ShootablePath implements Path {
	int startX, startY, endX, endY, currentX, currentY, steps, angle, deltaH;
	int currentStep = -1; // This makes the first step 0
	int bounceCount = 0;
	int distanceX, distanceY;
	double deltaX, deltaY;
	boolean isHitWall = false;

	public ShootablePath(int sX, int sY, int angle, int deltaH) {
		startX = sX;
		startY = sY;
		this.angle = angle;
		this.deltaH = deltaH;
		updateEndPoint(angle);
		calculateDeltaXY();

	}

	@Override
	public boolean hasMoreSteps() {
		// TODO Auto-generated method stub
		if (currentStep > steps)
			return false;
		return true;
	}

	@Override
	public Point nextPosition() {
		// TODO Auto-generated method stub
		currentX = (int) (startX + (deltaX * currentStep));
		currentY = (int) (startY + (deltaY * currentStep));

		currentStep++;

		if (isHitWall) {
			bounceCount++;
			if (bounceCount % 2 == 1) {
				startX = currentX;
				startY = currentY;
				deltaX = -deltaX;
				updateEndPoint(180 - this.angle);
			} else if (bounceCount % 2 == 0) {
				startX = currentX;
				startY = currentY;
				deltaX = -deltaX;
				updateEndPoint(this.angle);
			}
			isHitWall = false;
			// updateDelta();
			currentStep = 1;

		}
		return new Point((int) (startX + (deltaX * currentStep)), (int) (startY + (deltaY * currentStep)));
	}

	private void updateEndPoint(int angle) {
		double angleInRadian = Math.toRadians(angle);
		distanceX = KuVidGame.getInstance().getWindowWidth() + 100;
		distanceY = (int) Math.round((distanceX * Math.tan(angleInRadian)));

		if (angle > 90) {
			endY = startY + distanceY;
			endX = startX - distanceX;
		} else if (angle < 90) {
			endY = startY - distanceY;
			endX = startX + distanceX;
		} else {
			endY = startY - KuVidGame.getInstance().getWindowHeight();
			endX = startX;
		}
	}

	private void calculateDeltaXY() {
		double angleInRadian = Math.toRadians(angle);
		deltaX = (int) Math.round(Math.cos(angleInRadian) * deltaH);
		deltaY = -(int) Math.round(Math.sin(angleInRadian) * deltaH);

	}

	public boolean getIsHitWall() {
		return isHitWall;
	}

	public int getAngle() {
		return angle;
	}

	public void setAngle(int angle) {
		this.angle = angle;
	}

	public int getBounceCount() {
		return bounceCount;
	}

	public void setBounceCount(int bounceCount) {
		this.bounceCount = bounceCount;
	}

	@Override
	public void setHitWall(boolean hit) {
		// TODO Auto-generated method stub
		isHitWall=hit;
	}

}
