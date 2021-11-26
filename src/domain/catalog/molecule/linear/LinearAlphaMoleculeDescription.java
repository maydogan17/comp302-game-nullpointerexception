package domain.catalog.molecule.linear;

import domain.hitbox.Dimension;

import domain.factories.ObjectType;
import domain.hitbox.Point;
import domain.kuvidgame.KuVidGame;
import domain.objects.molecule.MoleculeStruct;
import domain.objects.path.Path;
import domain.objects.path.ZigZagPath;

public class LinearAlphaMoleculeDescription extends LinearMoleculeDescription {
	
	public LinearAlphaMoleculeDescription() {
		super();
		super.setMolType(ObjectType.ALPHA_M);
		super.setMoleculeStruct(MoleculeStruct.LINEAR);
		super.setRotationSpeed(5);
		assignDirectory();
		super.setMoleculeDimension(new Dimension(KuVidGame.getL()*9/8, KuVidGame.getL()*3/8)); // 9/8L , 3/8L
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

	@Override
	public void assignDirectory() {
		this.setImageDirectory("images/alphaMolLin.png");

	}

}
