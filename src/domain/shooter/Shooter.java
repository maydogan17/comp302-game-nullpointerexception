package domain.shooter;

import java.util.ArrayList;
import java.util.List;

import domain.hitbox.Dimension;
import domain.hitbox.HitBoxRectangle;
import domain.hitbox.Point;
import domain.hitbox.RotCenterLoc;
import domain.objects.WallType;

public class Shooter {

	private String imageDirectory;

	private Point ImagePosition;
	private Dimension ImageSize;

	private int turningSpeed;
	private double speed;
	private int health;

	private HitBoxRectangle shooterHitBox;

	private Bullet bullet;

	private List<ShooterListener> shooterListeners = new ArrayList<>();

	public Shooter(int turningSpeed, double speed, Dimension size, Point pos, int health, int L) {
		this.setTurningSpeed(turningSpeed);
		this.setSpeed(speed);
		this.setHealth(health);
		this.setImageDirectory("images/shooter.png");
		this.setShooterHitBox(new HitBoxRectangle(pos, size, 0, RotCenterLoc.BOTTOM_CENTER));
		this.setImageSize(new Dimension(L * 2, L * 2));

		updateImagePosition();
	}

	public void createBullet(int L) {
		this.setBullet(new Bullet(this));// No atom stored no location
	}

	public void updateImagePosition() {

		this.setImagePosition(new Point(
				getShooterHitBox().getRotatingCenter().getDoubleX() - this.getImageSize().getDoubleWidth() / 2,
				getShooterHitBox().getRotatingCenter().getDoubleY() - this.getImageSize().getDoubleHeight() / 2));

	}

	public void addShooterListener(ShooterListener s) {
		shooterListeners.add(s);
	}

	public void moveRight() {

		if (shooterHitBox.doHitBoxCollideWithWall() != WallType.WALL_RIGHT) {
			shooterHitBox.move(speed);
			getBullet().move(speed);
		}

		updateImagePosition();
		publishMoveEvent();

	}

	public void moveLeft() {

		if (shooterHitBox.doHitBoxCollideWithWall() != WallType.WALL_LEFT) {
			shooterHitBox.move(-speed);
			getBullet().move(-speed);

		}

		updateImagePosition();
		publishMoveEvent();

	}

	public void publishMoveEvent() {
		for (ShooterListener s : shooterListeners) {
			s.onMoveEvent();
		}
	}

	public void rotateLeft() {

		if ((shooterHitBox.doHitBoxCollideWithWall() != WallType.WALL_LEFT || shooterHitBox.getAngle() >= turningSpeed)
				&& shooterHitBox.getAngle() > -70) {
			shooterHitBox.rotate(-turningSpeed);
			getBullet().rotate(-turningSpeed);
		}

		publishRotateLeftEvent();
	}

	public void publishRotateLeftEvent() {
		for (ShooterListener s : shooterListeners) {
			s.onRotateLeftEvent();
		}
	}

	public void rotateRight() {
		// REQUIRES:
		// MODIFIES: this.shooterHitBox.getAngle(),
		// this.getBullet().getStoredAtom().getObjectHitbox().getCenter()
		// EFFECTS: rotates the shooter right with rotationSpeed and update the
		// connected bullet's position proportionally in the specific boundaries.
		// If it is on the borders it does not do any change on modifies.
		if ((shooterHitBox.doHitBoxCollideWithWall() != WallType.WALL_RIGHT
				|| shooterHitBox.getAngle() <= -turningSpeed) && shooterHitBox.getAngle() < 70) {
			shooterHitBox.rotate(turningSpeed);
			getBullet().rotate(turningSpeed);
		}

		publishRotateRightEvent();
	}

	public void publishRotateRightEvent() {
		for (ShooterListener s : shooterListeners) {
			s.onRotateRightEvent();
		}
	}

	public double getBulletEfficiency() {
		return getBullet().getStoredAtom().getEfficiency();
	}

	public String getImageDirectory() {
		return imageDirectory;
	}

	public void setImageDirectory(String directory) {
		this.imageDirectory = directory;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		
		this.health = health;
		
	}

	public int getTurningSpeed() {
		return turningSpeed;
	}

	public void setTurningSpeed(int turningSpeed) {
		this.turningSpeed = turningSpeed;
	}

	public double getSpeed() {
		return speed;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public Bullet getBullet() {
		return bullet;
	}

	public void setBullet(Bullet bullet) {
		this.bullet = bullet;
	}

	public HitBoxRectangle getShooterHitBox() {
		return shooterHitBox;
	}

	public void setShooterHitBox(HitBoxRectangle shooterHitBox) {
		this.shooterHitBox = shooterHitBox;
	}

	public Dimension getImageSize() {
		return ImageSize;
	}

	public void setImageSize(Dimension imageSize) {
		ImageSize = imageSize;
	}

	public Point getImagePosition() {
		return ImagePosition;
	}

	public void setImagePosition(Point imagePosition) {
		ImagePosition = imagePosition;
	}

}
