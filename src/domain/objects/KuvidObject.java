package domain.objects;


import domain.catalog.ObjectDescription;
import domain.hitbox.Dimension;
import domain.hitbox.HitBox;
import domain.hitbox.Point;
import domain.objects.behaviors.FallBehavior;
import domain.objects.behaviors.ShootBehavior;
import domain.objects.path.Path;

public abstract class KuvidObject implements DoAction{

	private HitBox objectHitbox; // It can be list late for implementation of hitboxes of molecules
	private Path path;
	private ShootBehavior shootBehavior;
	private FallBehavior fallBehavior;
	private int distanceTravelled;
	private ObjectDescription objDescr;
	private Dimension imageSize;
	private Point imagePosition;
	
	public abstract void destroy();

	public abstract void move();

	public abstract void bounce();
	
	public abstract void rotate();
	
	public void performShoot() {
		shootBehavior.shoot(this);
	}
	
	public void performFall() {
		fallBehavior.fall(this);
	}


	public int getDistanceTravelled() {
		return distanceTravelled;
	}

	public void setDistanceTravelled(int distanceTravelled) {
		this.distanceTravelled = distanceTravelled;
	}

	public HitBox getObjectHitbox() {
		return objectHitbox;
	}

	public void setObjectHitbox(HitBox objectHitbox) {
		this.objectHitbox = objectHitbox;
	}

	public Path getPath() {
		return path;
	}

	public void setPath(Path path) {
		this.path = path;
	}

	public ShootBehavior getShootBehavior() {
		return shootBehavior;
	}

	public void setShootBehavior(ShootBehavior shootBehavior) {
		this.shootBehavior = shootBehavior;
	}

	public FallBehavior getFallBehavior() {
		return fallBehavior;
	}

	public void setFallBehavior(FallBehavior fallBehavior) {
		this.fallBehavior = fallBehavior;
	}

	public ObjectDescription getObjDescr() {
		return objDescr;
	}

	public void setObjDescr(ObjectDescription objDescr) {
		this.objDescr = objDescr;
	}

	public Dimension getImageSize() {
		return imageSize;
	}

	public void setImageSize(Dimension imageSize) {
		this.imageSize = imageSize;
	}

	public Point getImagePosition() {
		return imagePosition;
	}

	public void setImagePosition(Point imagePosition) {
		this.imagePosition = imagePosition;
	}

}
