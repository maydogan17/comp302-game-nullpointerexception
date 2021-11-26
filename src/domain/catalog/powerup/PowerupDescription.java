package domain.catalog.powerup;

import domain.catalog.ObjectDescription;
import domain.factories.ObjectType;
import domain.hitbox.Point;
import domain.objects.path.Path;

public abstract class PowerupDescription extends ObjectDescription{
	private ObjectType powType;
	private int diameter;
	
	public abstract Path getPowerupFallPath(Point currentPosition);



	public int getDiameter() {
		return diameter;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}

	public abstract boolean isMatch(ObjectType molType);

	public ObjectType getType() {
		return powType;
	}



	public void setPowType(ObjectType powType) {
		this.powType = powType;
	}


}
