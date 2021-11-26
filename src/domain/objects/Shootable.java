package domain.objects;

import domain.hitbox.HitBox;
import domain.hitbox.Point;
import domain.objects.path.ShootablePath;

public interface Shootable {
	
	public void move(double distance);
	public void rotateAroundPoint(Point rotationCenter, double angle);
	public void shoot();
	public void updateImagePosition();
	public void createShootableInAction();
	public void publishShootableInBullet();
	public void addShootableListener(ShootableListener shootableListener);
	public void destroyFromPanel();
	public HitBox getShootableHitBox();
	public double getShootableDeltaH();
	public void setShootablePath(ShootablePath path);	
	public void decreaseFromInventory();
}
