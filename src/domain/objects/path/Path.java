package domain.objects.path;

import domain.hitbox.Point;

public interface Path {
	public void setHitWall(boolean isHitWall);
	
	public boolean hasMoreSteps();

	public Point nextPosition();
}
