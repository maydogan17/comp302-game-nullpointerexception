package domain.saveload.helperobjects;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

import domain.factories.ObjectType;

public class HelperAtom extends HelperObject {

	private Map<ObjectType, Integer> shieldMap;

	public HelperAtom() {

	}

	@Override
	@JsonIgnore
	public HelperPoint getCenterPosition() {
		return centerPosition;
	}

	@Override
	@JsonIgnore
	public void setCenterPosition(HelperPoint centerPosition) {
		this.centerPosition = centerPosition;
	}

	public Map<ObjectType, Integer> getShieldMap() {
		return shieldMap;
	}

	public void setShieldMap(Map<ObjectType, Integer> shieldMap) {
		this.shieldMap = shieldMap;
	}

}
