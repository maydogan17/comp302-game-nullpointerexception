package domain.objects.atoms.shields;

import java.util.List;
import java.util.Map;

import domain.catalog.ObjectDescription;
import domain.catalog.atom.AtomDescription;
import domain.factories.ObjectType;
import domain.hitbox.Dimension;
import domain.hitbox.HitBox;
import domain.hitbox.Point;
import domain.objects.ShootableListener;
import domain.objects.atoms.Atom;
import domain.objects.atoms.AtomListener;
import domain.objects.behaviors.FallBehavior;
import domain.objects.behaviors.ShootBehavior;
import domain.objects.molecule.Molecule;
import domain.objects.path.Path;

public abstract class ShieldDecorator extends Atom {

	private Atom atom;
	private double boost;

	public ShieldDecorator(Atom atom) {
		//super(atom.getObjectHitbox());
		this.setAtom(atom);
		// TODO Auto-generated constructor stub
	}

	//public abstract double getEfficiency();


	public double getBoost() {
		return boost;
	}

	public void setBoost(double boost) {
		this.boost = boost;
	}

	public Atom getAtom() {
		return atom;
	}

	public void setAtom(Atom atom) {
		this.atom = atom;
	}
	
	public void updateImagePosition() {
		atom.updateImagePosition();
	}

	public void addAtomListener(AtomListener as) {
		atom.addShootableListener(as);
	}

	public void updateCenter(Point newCenter) {
		// updates the center of atom
		atom.updateCenter(newCenter);
	}

	public void move(double distance) {
		atom.move(distance);
	}

	public void rotateAroundPoint(Point rotationCenter, double angle) {
		atom.rotateAroundPoint(rotationCenter, angle);
	}

	public void publishLocationChange() {
		atom.publishLocationChange();
	}
	
	public void publishAtomInBullet() {
		atom.publishShootableInBullet();
	}


	/*public void updateImagePositionByPath() {
		performShoot();
		updateImagePosition();
		publishLocationChange();
	}*/

	public void shoot() {
		atom.shoot();

	}

	@Override
	public void destroy() {
		atom.destroy();

	}

	@Override
	public void move() {
		// TODO Auto-generated method stub

	}

	@Override
	public void bounce() {
		// TODO Auto-generated method stub

	}
	
	public AtomDescription getAtomDescr() {
		return atom.getAtomDescr();
	}

	public List<Molecule> checkIfHitsMol() {
	
		return atom.checkIfHitsMol();
		
	}
	
	public void collideWithMolecules() {
		atom.collideWithMolecules();
//		if(!collisionDetectedMolecules.isEmpty()) {
//			destroy();
//		}
		
	}


	public List<ShootableListener> getAtomListeners() {
		return atom.getAtomListeners();
	}

	public void setAtomListeners(List<ShootableListener> atomListeners) {
		atom.setAtomListeners(atomListeners);
	}


	public boolean isShooted() {
		return atom.isShooted();
	}

	public void setShooted(boolean isShooted) {
		atom.setShooted(isShooted);
	}

	public double getNeutronAmount() {
		return atom.getNeutronAmount();
	}

	public void setNeutronAmount(double neutronAmount) {
		atom.setNeutronAmount(neutronAmount);
	}

	public double getEfficiency() {
		return atom.getEfficiency();
	}

	public void setEfficiency(double efficiency) {
		atom.setEfficiency(efficiency);
	}

	@Override
	public void doAction() {
		// TODO Auto-generated method stub
		atom.doAction();
		
	}

	public double getDeltaH() {
		return atom.getDeltaH();
	}

	public void setDeltaH(double deltaH) {
		atom.setDeltaH(deltaH);
	}

	public Map<ObjectType, Integer> getShieldMap() {
		return atom.getShieldMap();
	}

	public void setShieldMap(Map<ObjectType, Integer> shieldMap) {
		atom.setShieldMap(shieldMap);
	}
	
	
	////////
	

	public void performShoot() {
		atom.performShoot();
	}
	
	public void performFall() {
		atom.performFall();
	}


	public int getDistanceTravelled() {
		return atom.getDistanceTravelled();
	}

	public void setDistanceTravelled(int distanceTravelled) {
		atom.setDistanceTravelled(distanceTravelled);
	}

	public HitBox getObjectHitbox() {
		return atom.getObjectHitbox();
	}

	public void setObjectHitbox(HitBox objectHitbox) {
		atom.setObjectHitbox(objectHitbox);
	}

	public Path getPath() {
		return atom.getPath();
	}

	public void setPath(Path path) {
		atom.setPath(path);
	}

	public ShootBehavior getShootBehavior() {
		return atom.getShootBehavior();
	}

	public void setShootBehavior(ShootBehavior shootBehavior) {
		atom.setShootBehavior(shootBehavior);
	}

	public FallBehavior getFallBehavior() {
		return atom.getFallBehavior();
	}

	public void setFallBehavior(FallBehavior fallBehavior) {
		atom.setFallBehavior(fallBehavior);
	}

	public ObjectDescription getObjDescr() {
		return atom.getObjDescr();
	}

	public void setObjDescr(ObjectDescription objDescr) {
		atom.setObjDescr(objDescr);
	}

	public Dimension getImageSize() {
		return atom.getImageSize();
	}

	public void setImageSize(Dimension imageSize) {
		atom.setImageSize(imageSize);
	}

	public Point getImagePosition() {
		return atom.getImagePosition();
	}

	public void setImagePosition(Point imagePosition) {
		atom.setImagePosition(imagePosition);
	}

	
}
