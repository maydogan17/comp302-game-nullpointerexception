package domain.catalog.rb;

import domain.catalog.ObjectDescription;
import domain.factories.ObjectType;
import domain.hitbox.Point;
import domain.objects.path.Path;

public abstract class RbDescription extends ObjectDescription {

	private ObjectType rbType;
	private int diameter;

	public abstract Path getRBPath(Point currentPosition);

	public ObjectType getType() {
		return rbType;
	}

	public void setRbType(ObjectType rbType) {
		this.rbType = rbType;
	}

	public int getDiameter() {
		return diameter;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}
	
	public abstract boolean isMatchMol(ObjectType molType);
	
	public abstract boolean isMatchAtom(ObjectType atomType);

}
