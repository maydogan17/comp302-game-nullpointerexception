package domain.shooter;

import domain.factories.AtomFactory;
import domain.factories.ObjectType;
import domain.factories.PowerupFactory;
import domain.hitbox.HitBoxCircle;
import domain.hitbox.Point;
import domain.inventory.Inventory;
import domain.kuvidgame.KuVidGame;
import domain.objects.KuvidObject;
import domain.objects.Shootable;
import domain.objects.atoms.Atom;
import domain.objects.atoms.shields.EtaShield;
import domain.objects.atoms.shields.LotaShield;
import domain.objects.atoms.shields.ThetaShield;
import domain.objects.atoms.shields.ZetaShield;
import domain.objects.path.ShootablePath;
import domain.objects.powerups.Powerup;

public class Bullet {

	private int speed;
	private Shooter shooter;

	private Shootable storedShootable;

	public Bullet(Shooter shooter) {
		this.setShooter(shooter);
		this.setSpeed(0);
	}

	public void getRandomAtomFromInv() {
		Inventory gameInv = KuVidGame.getInstance().getInv();
		if (gameInv.getRandomAtom() == null) {
			System.out.println("Atom inventory is empty.");
			setStoredShootable(null);
		} else {
			this.setStoredShootable(gameInv.getRandomAtom()); // I didn't include destruction of previous one no
																// location
			// this.setStoredAtom(gameInv.getRandomAtom()); // set for atom
			// Location set according to shooter and atom image is updated
			setLocationStoredShootable(getStoredAtom());
			getStoredShootable().updateImagePosition();
			getStoredShootable().createShootableInAction();
			getStoredShootable().publishShootableInBullet();
		}
	}

	public void setLocationStoredShootable(KuvidObject shootable) {
		HitBoxCircle shootableHitBox = null;
		try {
			shootableHitBox = (HitBoxCircle) shootable.getObjectHitbox();
		} catch (ClassCastException e) {
			e.printStackTrace();
		}

		try {
			double distance = getShooter().getShooterHitBox().getDim().getDoubleHeight() / 2
					+ shootableHitBox.getRadius();
			double shooterCenterX = getShooter().getShooterHitBox().getCenter().getDoubleX();
			double shooterCenterY = getShooter().getShooterHitBox().getCenter().getDoubleY();
			double angleInRadian = Math.toRadians(getShooter().getShooterHitBox().getAngle());
			double x = Math.sin(angleInRadian) * distance + shooterCenterX;
			double y = shooterCenterY - Math.cos(angleInRadian) * distance;
			shootableHitBox.setCenter(new Point(x, y));
		} catch (NullPointerException e) {
			e.printStackTrace();
		}

	}

	public void move(double distance) {
		// Moves the stored atom by distance
		if (storedShootable != null) {
			try {
				getStoredShootable().move(distance);
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}

	public void rotate(double angle) {
		// Moves the stored atom by distance
		if (storedShootable != null) {
			try {
				Point rotationCenter = getShooter().getShooterHitBox().getRotatingCenter();
				getStoredShootable().rotateAroundPoint(rotationCenter, angle);
			} catch (NullPointerException e) {
				e.printStackTrace();
			}
		}
	}


	public void removeStoredShootable() {

	}

	public void pickRandomAtom() {
		// REQUIRES: inventory is not empty
		// MODIFIES: this.storedAtom
		// EFFECTS: if inventory is empty throws NullPointerException,
		// else stored atom is set to random atom taken from atomList in inventory
		if (getStoredShootable() != null) {
			getStoredShootable().destroyFromPanel();
		}
		getRandomAtomFromInv();
	}

	public void shootShootable() { // old version int angle as parameter
		if (getStoredShootable() != null) {
			int angle = (int) Math.round(KuVidGame.getInstance().getShooter().getShooterHitBox().getAngle());
			ShootablePath myPath = new ShootablePath(getStoredShootable().getShootableHitBox().getCenter().getX(),
					getStoredShootable().getShootableHitBox().getCenter().getY(), 90 - angle,
					(int) getStoredShootable().getShootableDeltaH());
			getStoredShootable().setShootablePath(myPath);
		}
		if (storedShootable != null) {
			getStoredShootable().shoot();
			getStoredShootable().decreaseFromInventory();
		}
		getRandomAtomFromInv();
	}

	public void setPowerupToBullet(ObjectType type) {
		Inventory gameInv = KuVidGame.getInstance().getInv();
		if (gameInv.getStoredPWMap().get(type) == 0) {
			System.out.println("Selected " + type.toString().replaceAll("_POW", " Powerup") + " is empty.");
		} else {
			if (getStoredShootable() != null) {
				getStoredShootable().destroyFromPanel();
			}
			setStoredShootable(PowerupFactory.getInstance().createObject(type));
			setLocationStoredShootable(getStoredPowerup());
			getStoredPowerup().setFalling(false);
			// getStoredPowerup().shoot();
			getStoredShootable().updateImagePosition();
			getStoredShootable().createShootableInAction();
			getStoredShootable().publishShootableInBullet();
		}
	}

	public void addShield(ObjectType shieldType) throws WrongShieldTypeException {
		// REQUIRES: shieldType must be one of the four ObjectType's : ETA, THETA, ZETA,
		// LOTA
		// MODIFIES: KuVidGame.getInstance().getInv().getAtomList()
		// KuVidGame.getInstance().getInv().getStoredShieldMap()
		// KuVidGame.getInstance().getShooter().getBullet().getStoredAtom()
		// EFFECTS: adds one the given shieldType to storedAtom using Decorator pattern.
		// Shielded atoms efficiency increases and its deltaH (speed) decreases.
		// throws WrongShieldTypeException if the given shieldType is not a shield type.

		if (getStoredAtom() != null && getStoredAtom().getEfficiency() != 0) {
			if (!KuVidGame.getInstance().getInv().isStoredAtomMapEmpty() && isBulletAtom()) {
				if (shieldType.equals(ObjectType.ETA)) {
					if (KuVidGame.getInstance().getInv().getStoredShieldMap().get(ObjectType.ETA) != 0) {
						int index = KuVidGame.getInstance().getInv().getAtomList().indexOf(getStoredAtom());
						setStoredShootable(new EtaShield(getStoredAtom()));
						KuVidGame.getInstance().getInv().getAtomList().set(index, getStoredAtom());
						KuVidGame.getInstance().getInv().getStoredShieldMap().put(ObjectType.ETA,
								KuVidGame.getInstance().getInv().getStoredShieldMap().get(ObjectType.ETA) - 1);
					}
				} else if (shieldType.equals(ObjectType.THETA)) {
					if (KuVidGame.getInstance().getInv().getStoredShieldMap().get(ObjectType.THETA) != 0) {
						int index = KuVidGame.getInstance().getInv().getAtomList().indexOf(getStoredAtom());
						setStoredShootable(new ThetaShield(getStoredAtom()));
						KuVidGame.getInstance().getInv().getAtomList().set(index, getStoredAtom());
						KuVidGame.getInstance().getInv().getStoredShieldMap().put(ObjectType.THETA,
								KuVidGame.getInstance().getInv().getStoredShieldMap().get(ObjectType.THETA) - 1);
					}

				} else if (shieldType.equals(ObjectType.ZETA)) {
					if (KuVidGame.getInstance().getInv().getStoredShieldMap().get(ObjectType.ZETA) != 0) {
						int index = KuVidGame.getInstance().getInv().getAtomList().indexOf(getStoredAtom());
						setStoredShootable(new ZetaShield(getStoredAtom()));
						KuVidGame.getInstance().getInv().getAtomList().set(index, getStoredAtom());
						KuVidGame.getInstance().getInv().getStoredShieldMap().put(ObjectType.ZETA,
								KuVidGame.getInstance().getInv().getStoredShieldMap().get(ObjectType.ZETA) - 1);
					}

				} else if (shieldType.equals(ObjectType.LOTA)) {
					if (KuVidGame.getInstance().getInv().getStoredShieldMap().get(ObjectType.LOTA) != 0) {
						int index = KuVidGame.getInstance().getInv().getAtomList().indexOf(getStoredAtom());
						setStoredShootable(new LotaShield(getStoredAtom()));
						KuVidGame.getInstance().getInv().getAtomList().set(index, getStoredAtom());
						KuVidGame.getInstance().getInv().getStoredShieldMap().put(ObjectType.LOTA,
								KuVidGame.getInstance().getInv().getStoredShieldMap().get(ObjectType.LOTA) - 1);
					}

				} else {
					throw new WrongShieldTypeException("Invalid shield type.");
				}
			}
		}

	}

	public boolean isBulletAtom() {
		if (getStoredShootable() instanceof Atom)
			return true;
		else
			return false;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public Shooter getShooter() {
		return shooter;
	}

	public void setShooter(Shooter shooter) {
		this.shooter = shooter;
	}

	public Atom getStoredAtom() {
		Atom atom = null;
		try {
			if (storedShootable == null) {
				throw new ClassCastException();
			} else {
				atom = (Atom) storedShootable;
			}

		} catch (ClassCastException e) {
			atom = AtomFactory.getInstance().createObject(ObjectType.ALPHA); // dummy atom;
			atom.setEfficiency(0);
			// System.out.println("bullet.getStoredAtom() dummy atom döndürüyor");
		}
		return atom;
	}

	public Powerup getStoredPowerup() {
		return (Powerup) storedShootable;
	}

	public Shootable getStoredShootable() {
		return storedShootable;
	}

	public void setStoredShootable(Shootable storedShootable) {
		this.storedShootable = storedShootable;
	}

}
