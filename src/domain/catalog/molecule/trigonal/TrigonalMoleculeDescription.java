package domain.catalog.molecule.trigonal;

import domain.hitbox.Dimension;

import domain.catalog.molecule.MoleculeDescription;
import domain.hitbox.HitBox;
import domain.hitbox.HitBoxCircle;

public abstract class TrigonalMoleculeDescription extends MoleculeDescription {

	private int moleculeDiameter;
	
	@Override
	public HitBox createHitBoxWithInformationInDescription() {
		return new HitBoxCircle(moleculeDiameter);
	}
	
	public int getMoleculeDiameter() {
		return moleculeDiameter;
	}

	public void setMoleculeDiameter(int moleculeDiameter) {
		this.moleculeDiameter = moleculeDiameter;
	}
	
	
	public Dimension getImageSize() {
		return new Dimension(moleculeDiameter, moleculeDiameter);
	}


}
