package domain.hitbox;

public class Dimension {
	
	private double height;
	private double width;
	
	public Dimension() {
		this.height = 0;
		this.width = 0;
	}
	
	public Dimension(int width, int height) {
		this.height = height;
		this.width = width;
	}
	
	public Dimension(double width, double height) {
		this.height = height;
		this.width = width;
	}

	public int getHeight() {
		return (int)height;
	}

	public void setHeight(int height) {
		this.height = (double)height;
	}

	public int getWidth() {
		return (int)width;
	}

	public void setWidth(int width) {
		this.width = (double)width;
	}
	
	public double getDoubleHeight() {
		return height;
	}

	public void setDoubleHeight(double height) {
		this.height = height;
	}

	public double getDoubleWidth() {
		return width;
	}

	public void setDoubleWidth(double width) {
		this.width = width;
	}

}
