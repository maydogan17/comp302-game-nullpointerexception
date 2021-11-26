package domain.catalog.molecule.trigonal;

import domain.factories.ObjectType;
import domain.hitbox.Point;
import domain.kuvidgame.KuVidGame;
import domain.objects.molecule.MoleculeStruct;
import domain.objects.path.Path;
import domain.objects.path.ZigZagPath;

public class TrigonalAlphaMoleculeDescription extends TrigonalMoleculeDescription {

	public TrigonalAlphaMoleculeDescription() {
		super();
		super.setMolType(ObjectType.ALPHA_M);
		super.setMoleculeStruct(MoleculeStruct.TRIGONAL);

		assignDirectory();
		super.setMoleculeDiameter(KuVidGame.getL() * 3 / 4); // 3/4L is better but supposed to be L/4

	}

	public void assignDirectory() {
		this.setImageDirectory("images/alphaMolTri.png");

	}

	@Override
	public Path getMoleculePath(Point currentPosition) {
		int wH = KuVidGame.getInstance().getWindowHeight();

		double step = 40 * (wH - currentPosition.getY()) / KuVidGame.getL();
		// A molecule should go L/40 in each tick. Because each tick is in 25
		// miliseconds so 1/40 sec.
		// distance/step = L/40 because in each tick, molecule moves distance/step.

		double dev = KuVidGame.getL() / Math.sin(Math.toRadians(45)); // it is a mystery

		return new ZigZagPath(currentPosition, wH, (int) dev, (int) dev, (int) step);
	}

}
