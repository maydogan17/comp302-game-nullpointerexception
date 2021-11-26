package domain.objects.atoms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.catalog.atom.AtomDescription;
import domain.factories.AtomFactory;
import domain.factories.ObjectType;
import domain.hitbox.HitBox;
import domain.hitbox.HitBoxCircle;
import domain.hitbox.HitBoxRectangle;
import domain.hitbox.Point;
import domain.kuvidgame.KuVidGame;
import domain.objects.KuvidObject;
import domain.objects.Shootable;
import domain.objects.ShootableListener;
import domain.objects.molecule.Molecule;
import domain.objects.molecule.MoleculeStruct;
import domain.objects.path.ShootablePath;
import domain.objects.reactionblocker.Rb;

public abstract class Atom extends KuvidObject implements Shootable {

	private double efficiency;
	private double neutronAmount;

	private boolean isShooted = false;

	private List<ShootableListener> atomListeners = new ArrayList<>();
	private Map<ObjectType, Integer> shieldMap = new HashMap<ObjectType, Integer>();

	private double deltaH = KuVidGame.getL() / 8; // Supposed to be L/40 but it makes the game unplayable.

	public void updateImagePosition() {
		// updates image position, it is used after atom moves
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

	public void addShootableListener(ShootableListener as) {
		this.getAtomListeners().add(as);
	}

	public void updateCenter(Point newCenter) {
		// updates the center of atom
		this.getObjectHitbox().setCenter(newCenter);
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
		for (ShootableListener as : getAtomListeners()) {
			as.onLocationChangeEvent();
		}
	}

	public void publishShootableInBullet() {
		for (ShootableListener as : getAtomListeners()) {
			as.updateShootableInActionInSkyPanel();
		}
	}

	public void shoot() {
		setShooted(true);

	}

	@Override
	public void destroy() {
		destroyFromPanel();
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

	public void destroyFromPanel() {
		for (ShootableListener as : getAtomListeners()) {
			as.onRemoveEvent();
		}
	}

	public AtomDescription getAtomDescr() {
		try {
			return (AtomDescription) getObjDescr();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<Molecule> checkIfHitsMol() {
		List<Molecule> moleculesInPanel = new ArrayList<>();
		List<Molecule> collisionDetectedMolecules = new ArrayList<>();

		for (ShootableListener al : getAtomListeners()) {
			moleculesInPanel.addAll(al.getMoleculesInPanel());
		}

		for (Molecule mol : moleculesInPanel) {

			if (mol.getMolDescr().getMoleculeStruct() == MoleculeStruct.TRIGONAL) {
				if (HitBox.doCollideTwoCircle((HitBoxCircle) getObjectHitbox(), (HitBoxCircle) mol.getObjectHitbox())) {
					collisionDetectedMolecules.add(mol);
				}
			} else {
				if (HitBox.doCollideRectangleAndCircle((HitBoxRectangle) mol.getObjectHitbox(),
						(HitBoxCircle) getObjectHitbox())) {
					collisionDetectedMolecules.add(mol);
				}
			}

		}

		return collisionDetectedMolecules;

	}

	public void collideWithMolecules() {
		List<Molecule> collisionDetectedMolecules = checkIfHitsMol();

		for (Molecule mol : collisionDetectedMolecules) {
			if (getAtomDescr().isMatch(mol.getMolDescr().getType())) {
				if (!(mol.getObjectHitbox().getCenter().getDoubleY() <= 0)) {
					double temp = 1 + this.getEfficiency()
							+ (KuVidGame.getL() / mol.getObjectHitbox().getCenter().getDoubleY());
					KuVidGame.getInstance().addscore(temp);
				}
				mol.destroy();
				destroyFromPanel();
			}

		}

	}

	public List<Rb> checkIfHitsRb() {
		List<Rb> rbsInPanel = new ArrayList<>();
		List<Rb> collisionDetectedRb = new ArrayList<>();

		for (ShootableListener al : getAtomListeners()) {
			rbsInPanel.addAll(al.getReactionBlockersInPanel());
		}

		for (Rb rb : rbsInPanel) {
			if (HitBox.doCollideTwoCircle((HitBoxCircle) getObjectHitbox(), (HitBoxCircle) rb.getObjectHitbox())) {
				if (isShooted) {
					collisionDetectedRb.add(rb);
				}
			}
		}

		return collisionDetectedRb;

	}

	public void collideWithRb() {
		List<Rb> collisionDetectedRb = checkIfHitsRb();

		for (Rb rb : collisionDetectedRb) {
			if (!(rb.getObjectHitbox().getCenter().getDoubleY() <= 0)) {
				if (rb.getRbDescr().isMatchAtom(getAtomDescr().getType())) {
					destroy();
				}

			}

		}

	}

	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		if (isShooted) {
			performShoot();
			updateImagePosition();
			publishLocationChange();
			collideWithMolecules();
		}
		collideWithRb();

	}

	public void createShootableInAction() {
		AtomFactory.getInstance().getFactoryListener().addShootableInActionToSkyPanel(this);
	}

	public List<ShootableListener> getAtomListeners() {
		return atomListeners;
	}

	public void setAtomListeners(List<ShootableListener> atomListeners) {
		this.atomListeners = atomListeners;
	}

	public boolean isShooted() {
		return isShooted;
	}

	public void setShooted(boolean isShooted) {
		this.isShooted = isShooted;
	}

	public double getNeutronAmount() {
		return neutronAmount;
	}

	public void setNeutronAmount(double neutronAmount) {
		this.neutronAmount = neutronAmount;
	}

	public double getEfficiency() {
		return efficiency;
	}

	public void setEfficiency(double efficiency) {
		this.efficiency = efficiency;
	}

	@Override
	public void rotate() {

	}

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
		KuVidGame.getInstance().getInv().deleteAtomFromList(this);

	}

	public double getDeltaH() {
		return deltaH;
	}

	public void setDeltaH(double deltaH) {
		this.deltaH = deltaH;
	}

	public Map<ObjectType, Integer> getShieldMap() {
		return shieldMap;
	}

	public void setShieldMap(Map<ObjectType, Integer> shieldMap) {
		this.shieldMap = shieldMap;
	}

}
