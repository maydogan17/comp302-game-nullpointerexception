package domain.catalog.molecule.linear;

import domain.hitbox.Dimension;

import domain.factories.ObjectType;
import domain.hitbox.Point;
import domain.kuvidgame.KuVidGame;
import domain.objects.molecule.MoleculeStruct;
import domain.objects.path.Path;
import domain.objects.path.StraightLinePath;
import domain.objects.path.ZigZagPath;

public class LinearBetaMoleculeDescription extends LinearMoleculeDescription {
	
	public LinearBetaMoleculeDescription() {
		super();
		super.setMolType(ObjectType.BETA_M);
		super.setMoleculeStruct(MoleculeStruct.LINEAR);
		super.setRotationSpeed(5);
		assignDirectory();
		super.setMoleculeDimension(new Dimension(KuVidGame.getL()*11/8, KuVidGame.getL()*1/4)); //11/8L, 1/4L
		
	}

	@Override
	public Path getMoleculePath(Point currentPosition) {
		int wH = KuVidGame.getInstance().getWindowHeight();
		Path path;
		if (currentPosition.getY() >= wH / 4) {
			double step = 40 * (wH - currentPosition.getY()) / KuVidGame.getL();
			// A molecule should go L/40 in each tick. Because each tick is in 25
			// miliseconds so 1/40 sec.
			// distance/step = L/40 because in each tick, molecule moves distance/step.

			double dev = KuVidGame.getL() / Math.sin(Math.toRadians(45)); // it is a mystery

			path = new ZigZagPath(currentPosition, wH , (int) dev, (int) dev, (int) step);
		} else {
			double step = 40 * (wH / 4 - currentPosition.getY()) / KuVidGame.getL();
			// A molecule should go L/40 in each tick. Because each tick is in 25
			// miliseconds so 1/40 sec.
			// distance/step = L/40 because in each tick, molecule moves distance/step.
			path = new StraightLinePath(currentPosition.getX(), currentPosition.getY(), currentPosition.getX(), wH / 4,
					(int) step);
		}
		return path;
	}

	@Override
	public void assignDirectory() {
		this.setImageDirectory("images/betaMolLin.png");

	}

}
