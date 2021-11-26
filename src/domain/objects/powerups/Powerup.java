package domain.objects.powerups;

import domain.hitbox.Dimension;
import java.util.ArrayList;
import java.util.List;

import domain.catalog.powerup.PowerupDescription;
import domain.factories.PowerupFactory;
import domain.hitbox.HitBox;
import domain.hitbox.HitBoxCircle;
import domain.hitbox.Point;
import domain.kuvidgame.KuVidGame;
import domain.objects.KuvidObject;
import domain.objects.Shootable;
import domain.objects.ShootableListener;
import domain.objects.behaviors.FallLikeAnAngel;
import domain.objects.behaviors.ShootByShooter;
import domain.objects.path.ShootablePath;
import domain.objects.reactionblocker.Rb;

public class Powerup extends KuvidObject implements Shootable {

	private List<ShootableListener> powListeners = new ArrayList<ShootableListener>();
	private boolean isShooted = false;
	private boolean isFalling = true;
	private double deltaH = KuVidGame.getL() / 15;

	public Powerup(PowerupDescription powDescr, HitBox powHitBox) {
		setObjectHitbox(powHitBox);
		this.setObjDescr(powDescr);

		this.setImageSize(new Dimension(getPowDescr().getDiameter(), getPowDescr().getDiameter()));

		HitBoxCircle circularPowHitBox = null;

		try {
			circularPowHitBox = (HitBoxCircle) powHitBox;
			circularPowHitBox.setDiameter(powDescr.getDiameter());
		} catch (ClassCastException e) {
			e.printStackTrace();
		}

		setObjectHitbox(circularPowHitBox);

		this.setFallBehavior(new FallLikeAnAngel());
		this.setShootBehavior(new ShootByShooter());
	}

	public void updateImagePosition() {
		if (isFalling) {
			double x = this.getObjectHitbox().getCenter().getDoubleX() - this.getImageSize().getDoubleWidth() / 2;
			double y = this.getObjectHitbox().getCenter().getDoubleY() - this.getImageSize().getDoubleHeight() / 2;
			this.setImagePosition(new Point(x, y));
		} else {
			if (getImagePosition() == null) {
				this.setImagePosition(new Point());
			}
			try {
				int x = super.getObjectHitbox().getCenter().getX() - (int) getImageSize().getWidth() / 2;
				int y = super.getObjectHitbox().getCenter().getY() - (int) getImageSize().getHeight() / 2;
				this.getImagePosition().setIntCoordinates(x, y);
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}

	}

	public void setPowerupCenter(Point powCenter) {
		this.getObjectHitbox().setCenter(powCenter);
		updateImagePosition();
	}

	@Override
	public void addShootableListener(ShootableListener ml) {
		this.getPowListeners().add(ml);
	}

	public void removePowerupListener(PowerupListener ml) {
		this.getPowListeners().remove(ml);
	}

	public void move(double distance) {
		Point center = this.getObjectHitbox().getCenter();
		center.setDoubleX(center.getDoubleX() + distance);
		updateImagePosition();
		publishLocationChange();
	}

	public void rotateAroundPoint(Point rotationCenter, double angle) {
		Point center = this.getObjectHitbox().getCenter();
		this.getObjectHitbox().setCenter(HitBox.rotatePointAroundAnotherPointByAngle(center, rotationCenter, angle));
		updateImagePosition();
		publishLocationChange();
	}

	public void publishLocationChange() {
		for (ShootableListener as : getPowListeners()) {
			as.onLocationChangeEvent();
		}
	}

	public void publishShootableInBullet() {
		for (ShootableListener as : getPowListeners()) {
			as.updateShootableInActionInSkyPanel();
		}
	}

	public void shoot() {
		setShooted(true);
		setFalling(false);
	}

	public void fall() {
		setFalling(true);
		setShooted(false);
	}

	public void initPowPath() {
		this.setPath(getPowDescr().getPowerupFallPath(getObjectHitbox().getCenter()));
	}

	public void updatePath() {
		if (getPath() == null || !getPath().hasMoreSteps()) {
			setPath(getPowDescr().getPowerupFallPath(getObjectHitbox().getCenter()));
		}
	}

	public PowerupDescription getPowDescr() {
		try {
			return (PowerupDescription) getObjDescr();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Rb> checkIfHitsRb() {
		List<Rb> RbInPanel = new ArrayList<>();
		List<Rb> collisionDetectedRb = new ArrayList<>();

		for (ShootableListener al : getPowListeners()) {
			RbInPanel.addAll(al.getReactionBlockersInPanel());
		}

		for (Rb rb : RbInPanel) {
			if (HitBox.doCollideTwoCircle((HitBoxCircle) getObjectHitbox(), (HitBoxCircle) rb.getObjectHitbox())) {
				collisionDetectedRb.add(rb);
			}
		}

		return collisionDetectedRb;

	}

	public void collideWithRb() {
		List<Rb> collisionDetectedRb = checkIfHitsRb();

		for (Rb rb : collisionDetectedRb) {
			if (getPowDescr().isMatch(rb.getRbDescr().getType())) {
				if (!(rb.getObjectHitbox().getCenter().getDoubleY() <= 0)) {

				}
				rb.destroy();
				destroyFromPanel();
			}

		}
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		destroyFromPanel();
	}

	public void destroyFromPanel() {
		// TODO Auto-generated method stub
		for (ShootableListener ms : getPowListeners()) {
			ms.onRemoveEvent();
		}

	}

	@Override
	public void createShootableInAction() {
		PowerupFactory.getInstance().getFactoryListener().addShootableInActionToSkyPanel(this);

	}

	@Override
	public void move() {
		Point next = getPath().nextPosition();

		if (next.getX() > 0 && next.getX() < KuVidGame.getInstance().getWindowWidth()) {
			getObjectHitbox().setCenter(next);
		} else {
			getPath().setHitWall(true);
		}

		if (next.getY() < 0)
			destroy(); // if Y coordinate is out of frame destroy it.
	}

	@Override
	public void bounce() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		if (isShooted) {
			performShoot();
			updateImagePosition();
			publishLocationChange();
			collideWithRb();
		} else if (isFalling) {
			updatePath();
			performFall();
			updateImagePosition();
			collideWithShooter();
		}
	}

	@Override
	public HitBox getShootableHitBox() {
		return this.getObjectHitbox();

	}

	@Override
	public double getShootableDeltaH() {
		return this.getDeltaH();
	}

	@Override
	public void setShootablePath(ShootablePath path) {
		// TODO Auto-generated method stub
		this.setPath(path);
	}

	@Override
	public void decreaseFromInventory() {
		// TODO Auto-generated method stub
		KuVidGame.getInstance().getInv().decreasePowerup(this.getPowDescr());
	}

	public void collideWithShooter() {
		if (HitBox.doCollideRectangleAndCircle(KuVidGame.getInstance().getShooter().getShooterHitBox(),
				(HitBoxCircle) getObjectHitbox())) {
			KuVidGame.getInstance().getInv().storePowerup(getPowDescr().getType());
			destroy();
		}
	}

	public List<ShootableListener> getPowListeners() {
		return powListeners;
	}

	public void setPowListeners(List<ShootableListener> powListeners) {
		this.powListeners = powListeners;
	}

	public boolean isShooted() {
		return isShooted;
	}

	public void setShooted(boolean isShooted) {
		this.isShooted = isShooted;
	}

	public double getDeltaH() {
		return deltaH;
	}

	public void setDeltaH(double deltaH) {
		this.deltaH = deltaH;
	}

	public boolean isFalling() {
		return isFalling;
	}

	public void setFalling(boolean isFalling) {
		this.isFalling = isFalling;
	}

	@Override
	public void rotate() {
		// TODO Auto-generated method stub

	}

}
