package ui.inaction;

import java.awt.Graphics;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

import domain.hitbox.HitBoxRectangle;
import domain.objects.molecule.Molecule;
import domain.objects.molecule.MoleculeListener;
import domain.objects.reactionblocker.Rb;
import ui.frame.SkyPanel;

public class MoleculeInAction extends ObjectInAction implements MoleculeListener {

	private double imageAngle;

	public MoleculeInAction(SkyPanel sp, Molecule mol) {
		super(sp, mol);
		setImageAngle(0);

	}

	public void initialize(Molecule mol) {
		mol.addMoleculeListener(this);
	}

	@Override
	public void onLocationChangeEvent() {
		getSuperPanel().repaint();

	}

	@Override
	public void onRemoveEvent() {
		getSuperPanel().removeObjectInAction(this);
		getSuperPanel().getMoleculeInActionsInPanel().remove(this);
	}

	@Override

	public void onRotationEvent() {
		HitBoxRectangle r = (HitBoxRectangle) getObject().getObjectHitbox();
		setImageAngle(r.getAngle());

	}

	@Override
	public void draw(Graphics g) {

		BufferedImage image = getObjectImage();
		double rotationRequired = Math.toRadians(getImageAngle());
		double locationX = image.getWidth(null) / 2;
		double locationY = image.getHeight(null) / 2;
		AffineTransform tx = AffineTransform.getRotateInstance(rotationRequired, locationX, locationY);
		AffineTransformOp op = new AffineTransformOp(tx, AffineTransformOp.TYPE_BILINEAR);

		g.drawImage(op.filter(image, null), getObject().getImagePosition().getX(),
				getObject().getImagePosition().getY(), getSuperPanel());

	}

	public double getImageAngle() {
		return imageAngle;
	}

	public void setImageAngle(double imageAngle) {
		this.imageAngle = imageAngle;
	}

	public List<Rb> getReactionBlockersInPanel() {
		// TODO Auto-generated method stub
		List<Rb> rbs = new ArrayList<Rb>();
		for (ReactionBlockerInAction rbac : getSuperPanel().getRbInActionsInPanel()) {
			rbs.add((Rb) rbac.getObject());
		}
		return rbs;
	}

}
