package domain.catalog.molecule.linear;

import domain.catalog.molecule.MoleculeDescription;
import domain.hitbox.Dimension;
import domain.hitbox.HitBox;
import domain.hitbox.HitBoxRectangle;
import domain.hitbox.RotCenterLoc;

public abstract class LinearMoleculeDescription extends MoleculeDescription {
	
	private Dimension moleculeDimension;
	private double rotationSpeed;

	
	@Override
	public HitBox createHitBoxWithInformationInDescription() {
		HitBoxRectangle r =  new HitBoxRectangle(getMoleculeDimension(), RotCenterLoc.CENTER);
		r.setRotationSpeed(getRotationSpeed());
		return r;
	}

	public Dimension getMoleculeDimension() {
		return moleculeDimension;
	}

	public void setMoleculeDimension(Dimension moleculeDimension) {
		this.moleculeDimension = moleculeDimension;
	}
	
	public Dimension getImageSize() {
		double max = Math.max(moleculeDimension.getDoubleHeight(), moleculeDimension.getDoubleWidth());
		return new Dimension((int)max, (int)max);
	}

	public double getRotationSpeed() {
		return rotationSpeed;
	}

	public void setRotationSpeed(double rotationSpeed) {
		this.rotationSpeed = rotationSpeed;
	}
	

}
