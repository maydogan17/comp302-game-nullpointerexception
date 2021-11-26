
package domain.catalog.molecule;

import domain.hitbox.Dimension;
import domain.catalog.ObjectDescription;
import domain.factories.ObjectType;
import domain.hitbox.HitBox;
import domain.hitbox.Point;
import domain.objects.molecule.MoleculeStruct;
import domain.objects.path.Path;

public abstract class MoleculeDescription extends ObjectDescription{

	private ObjectType molType;
	private MoleculeStruct moleculeStruct;

	public abstract Path getMoleculePath(Point currentPosition);
	public abstract void assignDirectory();
	public abstract HitBox createHitBoxWithInformationInDescription();
	public abstract Dimension getImageSize();

	public ObjectType getType() {
		return molType;
	}

	public void setMolType(ObjectType molType) {
		this.molType = molType;
	}
	
	public MoleculeStruct getMoleculeStruct() {
		return moleculeStruct;
	}
	
	public void setMoleculeStruct(MoleculeStruct molStruct) {
		moleculeStruct = molStruct;
	}

}
