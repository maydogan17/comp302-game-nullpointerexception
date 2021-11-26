package domain.catalog.molecule.trigonal;

import domain.factories.ObjectType;
import domain.hitbox.Point;
import domain.kuvidgame.KuVidGame;
import domain.objects.molecule.MoleculeStruct;
import domain.objects.path.Path;
import domain.objects.path.StraightLinePath;

public class SigmaMoleculeDescription extends TrigonalMoleculeDescription {

	public SigmaMoleculeDescription() {
		super();
		super.setMolType(ObjectType.SIGMA_M);
		super.setMoleculeStruct(MoleculeStruct.TRIGONAL);
		assignDirectory();
		super.setMoleculeDiameter(KuVidGame.getL() * 9 / 8); // 9/8L is better but supposed to be L/4

	}

	public void assignDirectory() {
		this.setImageDirectory("images/sigmaMolTri.png");
	}

	@Override
	public Path getMoleculePath(Point currentPosition) {
		int wH = KuVidGame.getInstance().getWindowHeight();
		Path path;

		double step = 40 * (wH - currentPosition.getY()) / KuVidGame.getL();
		// A molecule should go L/40 in each tick. Because each tick is in 25
		// miliseconds so 1/40 sec.
		// distance/step = L/40 because in each tick, molecule moves distance/step.

		path = new StraightLinePath(currentPosition.getX(), currentPosition.getY(), currentPosition.getX(), wH,
				(int) step);

		return path;
	}


}
