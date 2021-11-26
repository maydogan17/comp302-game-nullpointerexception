package domain.objects.reactionblocker;

import java.util.ArrayList;
import java.util.List;

import domain.catalog.rb.RbDescription;
import domain.hitbox.Dimension;
import domain.hitbox.HitBox;
import domain.hitbox.HitBoxCircle;
import domain.hitbox.HitBoxRectangle;
import domain.hitbox.Point;
import domain.kuvidgame.KuVidGame;
import domain.objects.KuvidObject;
import domain.objects.behaviors.FallLikeAnAngel;
import domain.objects.behaviors.ShootNoWay;
import domain.objects.path.ZigZagPath;

public class Rb extends KuvidObject {

	private List<RbListener> rbListeners = new ArrayList<RbListener>();
	private HitBox innerHitbox;

	public Rb(RbDescription rbDescr, HitBox rbHitBox) {
		setObjectHitbox(rbHitBox);
		innerHitbox = new HitBoxCircle(rbHitBox.getCenter(), KuVidGame.getL() / 2);
		this.setObjDescr(rbDescr);

		this.setImageSize(new Dimension(getRbDescr().getDiameter(), getRbDescr().getDiameter()));

		this.setFallBehavior(new FallLikeAnAngel());
		this.setShootBehavior(new ShootNoWay());
	}

	public void updateImagePosition() {
		double x = this.getObjectHitbox().getCenter().getDoubleX() - this.getImageSize().getDoubleWidth() / 2;
		double y = this.getObjectHitbox().getCenter().getDoubleY() - this.getImageSize().getDoubleHeight() / 2;
		this.setImagePosition(new Point(x, y));
	}

	public void setRbCenter(Point rbCenter) {
		this.getObjectHitbox().setCenter(rbCenter);
		updateImagePosition();
	}

	public void addRbListener(RbListener rl) {
		this.getRbListeners().add(rl);
	}

	public void removeRbListener(RbListener rl) {
		this.getRbListeners().remove(rl);
	}

	public void initRbPath() {
		this.setPath(getRbDescr().getRBPath(getObjectHitbox().getCenter()));
	}

	public void updatePath() {
		if (getPath() == null || !getPath().hasMoreSteps()) {
			setPath(getRbDescr().getRBPath(getObjectHitbox().getCenter()));
		}
	}

	public List<KuvidObject> getHitObjects() {
		List<KuvidObject> objectsInPanel = new ArrayList<>();
		List<KuvidObject> collisionDetectedObjects = new ArrayList<>();

		for (RbListener al : getRbListeners()) {
			objectsInPanel.addAll(al.getObjectsInPanel());
		}

		for (KuvidObject obj : objectsInPanel) {
			if (obj.getObjectHitbox() instanceof HitBoxRectangle) {
				if (HitBox.doCollideRectangleAndCircle((HitBoxRectangle) obj.getObjectHitbox(),
						(HitBoxCircle) getObjectHitbox())) {
					collisionDetectedObjects.add(obj);
				}
			} else if (HitBox.doCollideTwoCircle((HitBoxCircle) getObjectHitbox(),
					(HitBoxCircle) obj.getObjectHitbox())) {
				collisionDetectedObjects.add(obj);
			}
		}

		return collisionDetectedObjects;

	}

	public void destroyObjectsInExplosionField() {
		List<KuvidObject> collisionDetectedObjects = getHitObjects();

		for (KuvidObject obj : collisionDetectedObjects) {
			if (!(obj.getObjectHitbox().getCenter().getDoubleY() <= 0)) {
				obj.destroy();
			}
		}

		destroy();

	}

	public void explodeWhenFallOnTheGround() {
		if (KuVidGame.getInstance().getWindowHeight() <= getObjectHitbox().getBiggestY()) {
			destroyObjectsInExplosionField();
			if (HitBox.doCollideRectangleAndCircle(KuVidGame.getInstance().getShooter().getShooterHitBox(),
					(HitBoxCircle) getObjectHitbox())) {
				damageShooter();
			}
		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		for (RbListener rl : getRbListeners()) {
			rl.onRemoveEvent();
		}

	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

	@Override
	public void bounce() {
		// TODO Auto-generated method stub
		if (getPath() instanceof ZigZagPath) {
			ZigZagPath p = (ZigZagPath) getPath();
			p.bounce();
		}
	}

	public void damageShooter() {
		int distanceBetweenTwo = Math.abs((KuVidGame.getInstance().getShooter().getShooterHitBox().getCenter().getX()
				- getObjectHitbox().getCenter().getX()));
		int damage = (KuVidGame.getInstance().getWindowWidth() / distanceBetweenTwo);
		if (damage > 20 || damage == 0) {
			damage = 20;
		}
		KuVidGame.getInstance().getShooter().setHealth(KuVidGame.getInstance().getShooter().getHealth() - damage);
	}

	public void collideWithShooter() {
		if (HitBox.doCollideRectangleAndCircle(KuVidGame.getInstance().getShooter().getShooterHitBox(),
				(HitBoxCircle) getInnerHitbox())) {
			damageShooter();
			if (KuVidGame.getInstance().getShooter().getBullet().getStoredShootable() != null) {
				KuVidGame.getInstance().getShooter().getBullet().getStoredShootable().decreaseFromInventory();
			}
			destroyObjectsInExplosionField();
			KuVidGame.getInstance().getShooter().getBullet().setStoredShootable(null);
		}
	}

	public void innerTravelWithHitbox() {
		getInnerHitbox().setCenter(getObjectHitbox().getCenter());
	}

	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		updatePath();
		performFall();
		updateImagePosition();
		innerTravelWithHitbox();

		collideWithShooter();
		explodeWhenFallOnTheGround();
	}

	public RbDescription getRbDescr() {
		try {
			return (RbDescription) getObjDescr();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<RbListener> getRbListeners() {
		return rbListeners;
	}

	public void setRbListeners(List<RbListener> rbListeners) {
		this.rbListeners = rbListeners;
	}

	@Override
	public void rotate() {
		// TODO Auto-generated method stub

	}

	public HitBox getInnerHitbox() {
		return innerHitbox;
	}

	public void setInnerHitbox(HitBox innerHitbox) {
		this.innerHitbox = innerHitbox;
	}

}
