package domain.objects.molecule;

import java.util.ArrayList;
import java.util.List;

import domain.catalog.molecule.MoleculeDescription;
import domain.catalog.molecule.linear.LinearMoleculeDescription;
import domain.hitbox.HitBox;
import domain.hitbox.HitBoxCircle;
import domain.hitbox.HitBoxRectangle;
import domain.hitbox.Point;
import domain.kuvidgame.KuVidGame;
import domain.objects.KuvidObject;
import domain.objects.behaviors.FallLikeAnAngel;
import domain.objects.behaviors.FallLikeBallerina;
import domain.objects.behaviors.ShootNoWay;
import domain.objects.path.ZigZagPath;
import domain.objects.reactionblocker.Rb;

public class Molecule extends KuvidObject {

	private List<MoleculeListener> molListeners = new ArrayList<MoleculeListener>();

	public Molecule(MoleculeDescription molDescr, HitBox molHitBox) {
		setObjectHitbox(molHitBox);
		setObjDescr(molDescr);

		initImageSize(molDescr);

		initFallBehavior();
		this.setShootBehavior(new ShootNoWay());
	}

	private void initFallBehavior() {
		if (getObjDescr() instanceof LinearMoleculeDescription) {
			if (KuVidGame.getInstance().isLinearSpinning()) {
				this.setFallBehavior(new FallLikeBallerina());
			} else {
				this.setFallBehavior(new FallLikeAnAngel());
			}
		} else {
			this.setFallBehavior(new FallLikeAnAngel());
		}
	}

	private void initImageSize(MoleculeDescription molDescr) {
		setImageSize(molDescr.getImageSize());
	}

	public void updateImagePosition() {
		double x = this.getObjectHitbox().getCenter().getDoubleX() - this.getImageSize().getDoubleWidth() / 2;
		double y = this.getObjectHitbox().getCenter().getDoubleY() - this.getImageSize().getDoubleHeight() / 2;
		this.setImagePosition(new Point(x, y));
	}

	public void setMoleculeCenter(Point molCenter) {
		this.getObjectHitbox().setCenterAndUpdate(molCenter);
		updateImagePosition();

	}

	public void addMoleculeListener(MoleculeListener ml) {
		this.getMolListeners().add(ml);
	}

	public void removeMoleculeListener(MoleculeListener ml) {
		this.getMolListeners().remove(ml);
	}

	public void initMolPath() {
		this.setPath(getMolDescr().getMoleculePath(getObjectHitbox().getCenter()));
	}

	public void updatePath() {
		if (getPath() == null || !getPath().hasMoreSteps()) {
			setPath(getMolDescr().getMoleculePath(getObjectHitbox().getCenter()));
		}
	}

	@Override
	public void destroy() {
		this.setPath(null);
		for (MoleculeListener ms : getMolListeners()) {
			ms.onRemoveEvent();
		}
	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

	@Override
	public void bounce() {
		if (getPath() instanceof ZigZagPath) {
			ZigZagPath p = (ZigZagPath) getPath();
			p.bounce();
		}
		if (getObjectHitbox() instanceof HitBoxRectangle) {
			HitBoxRectangle r = (HitBoxRectangle) getObjectHitbox();
			r.setRotationSpeed(-1 * r.getRotationSpeed());
		}

	}

	public void rotate() {
		if (getObjectHitbox() instanceof HitBoxRectangle) {
			HitBoxRectangle r = (HitBoxRectangle) getObjectHitbox();
			r.rotate();
			publishOnRotationEvent();
		}
	}

	public void publishOnRotationEvent() {
		for (MoleculeListener ms : getMolListeners()) {
			ms.onRotationEvent();
		}
	}

	public MoleculeDescription getMolDescr() {
		try {
			return (MoleculeDescription) getObjDescr();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<MoleculeListener> getMolListeners() {
		return molListeners;
	}

	public void setMolListeners(List<MoleculeListener> molListeners) {
		this.molListeners = molListeners;
	}

	public List<Rb> checkIfHitsRb() {
		List<Rb> rbsInPanel = new ArrayList<>();
		List<Rb> collisionDetectedRb = new ArrayList<>();

		for (MoleculeListener ml : getMolListeners()) {
			rbsInPanel.addAll(ml.getReactionBlockersInPanel());
		}

		for (Rb rb : rbsInPanel) {
			if (getMolDescr().getMoleculeStruct() == MoleculeStruct.TRIGONAL) {
				if (HitBox.doCollideTwoCircle((HitBoxCircle) getObjectHitbox(), (HitBoxCircle) rb.getObjectHitbox())) {
					collisionDetectedRb.add(rb);
				}
			} else {
				if (HitBox.doCollideRectangleAndCircle((HitBoxRectangle) getObjectHitbox(),
						(HitBoxCircle) rb.getObjectHitbox())) {
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
				if (rb.getRbDescr().isMatchMol(getMolDescr().getType())) {
					destroy();
				}
			}
		}
	}

	@Override
	public void doAction() {
		// REQUIRES: Molecule must have a hitbox whose position is set.
		// Molecule must have a valid molecule description.
		// MODIFIES: Molecule's path.
		// Molecule's hitbox, hitbox.center
		// Molecule's imagePosition
		// EFFECTS: When this method is run, molecules path is updated according to
		// the position on the screen. And center of hitbox is moved by path. If
		// molecule reaches the end. Molecule must be destroyed.

		updatePath();
		performFall();
		updateImagePosition();
		collideWithRb();
	}

}
