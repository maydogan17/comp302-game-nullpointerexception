package ui.inaction;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import domain.kuvidgame.KuVidGame;
import domain.shooter.Shooter;
import domain.shooter.ShooterListener;
import ui.Resize;

public class ShooterInAction implements Drawable, ShooterListener {

	private BufferedImage rawShooterImage;
	private JPanel superPanel;

	public ShooterInAction(JPanel sp) {
		this.superPanel = sp;

		initialize(KuVidGame.getInstance().getShooter());
		initShooterImage();

	}

	public void initShooterImage() {
		Shooter shooter = KuVidGame.getInstance().getShooter();
		try {
			rawShooterImage = ImageIO.read(new File(shooter.getImageDirectory()));

			rawShooterImage = Resize.resize(rawShooterImage, 2 * KuVidGame.getL(),
					2 * KuVidGame.getL());

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void initialize(Shooter s) {
		s.addShooterListener(this);
	}

	@Override
	public void onMoveEvent() {

		superPanel.repaint();

	}

	@Override
	public void onRotateRightEvent() {

		superPanel.repaint();
	}

	@Override
	public void onRotateLeftEvent() {

		superPanel.repaint();
	}

	@Override
	public void draw(Graphics g) {

		Shooter shooter = KuVidGame.getInstance().getShooter();

		double rotationRequired = Math.toRadians(shooter.getShooterHitBox().getAngle());
		double locationX = rawShooterImage.getWidth(null) / 2;
		double locationY = rawShooterImage.getHeight(null) / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		g.drawImage(op.filter(rawShooterImage, null), shooter.getImagePosition().getX(),
				shooter.getImagePosition().getY(), superPanel);

	}

}
