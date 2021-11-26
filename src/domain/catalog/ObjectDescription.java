package domain.catalog;

import domain.factories.ObjectType;

public abstract class ObjectDescription {
	private String imageDirectory;

	public String getImageDirectory() {
		return imageDirectory;
	}

	public void setImageDirectory(String imageDirectory) {
		this.imageDirectory = imageDirectory;
	}

	public abstract ObjectType getType();
}
