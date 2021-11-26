package domain.saveload.helperobjects;

import domain.factories.ObjectType;

public class HelperObject {

	ObjectType type;
	HelperPoint centerPosition;

	public HelperObject() {

	}

	public ObjectType getType() {
		return type;
	}

	public void setType(ObjectType type) {
		this.type = type;
	}

	public HelperPoint getCenterPosition() {
		return centerPosition;
	}

	public void setCenterPosition(HelperPoint centerPosition) {
		this.centerPosition = centerPosition;
	}

}
