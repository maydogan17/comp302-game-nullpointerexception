package domain.catalog.molecule.trigonal;

import domain.factories.ObjectType;
import domain.hitbox.Point;
import domain.kuvidgame.KuVidGame;
import domain.objects.molecule.MoleculeStruct;
import domain.objects.path.Path;
import domain.objects.path.StraightLinePath;
import domain.objects.path.ZigZagPath;

public class GammaMoleculeDescription extends TrigonalMoleculeDescription {

	public GammaMoleculeDescription() {
		super();
		super.setMolType(ObjectType.GAMMA_M);
		super.setMoleculeStruct(MoleculeStruct.TRIGONAL);

		assignDirectory();
		super.setMoleculeDiameter(KuVidGame.getL()); // L is better but supposed to be L/4

	}

	public void assignDirectory() {
		this.setImageDirectory("images/gammaMolTri.png");
	}

	@Override
	public Path getMoleculePath(Point currentPosition) {
		int wH = KuVidGame.getInstance().getWindowHeight();
		Path path;
		if (currentPosition.getY() >= wH / 2) {
			double step = 40 * (wH - currentPosition.getY()) / KuVidGame.getL();
			// A molecule should go L/40 in each tick. Because each tick is in 25
			// miliseconds so 1/40 sec.
			// distance/step = L/40 because in each tick, molecule moves distance/step.

			double dev = KuVidGame.getL() / Math.sin(Math.toRadians(45)); // it is a mystery

			path = new ZigZagPath(currentPosition, wH, (int) dev, (int) dev, (int) step);
		} else {
			double step = 40 * (wH / 2 - currentPosition.getY()) / KuVidGame.getL();
			// A molecule should go L/40 in each tick. Because each tick is in 25
			// miliseconds so 1/40 sec.
			// distance/step = L/40 because in each tick, molecule moves distance/step.

			path = new StraightLinePath(currentPosition.getX(), currentPosition.getY(), currentPosition.getX(), wH / 2,
					(int) step);
		}
		return path;
	}

}
