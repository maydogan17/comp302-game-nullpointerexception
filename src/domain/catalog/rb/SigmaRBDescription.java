package domain.catalog.rb;

import domain.factories.ObjectType;
import domain.hitbox.Point;
import domain.kuvidgame.KuVidGame;
import domain.objects.path.Path;
import domain.objects.path.StraightLinePath;

public class SigmaRBDescription extends RbDescription {
	public SigmaRBDescription() {
		super();
		super.setRbType(ObjectType.SIGMA_RB);
		super.setDiameter(KuVidGame.getL()*2);
		this.setImageDirectory("images/sigmaRB.png");
	}

	@Override
	public Path getRBPath(Point currentPosition) {
		int wH = KuVidGame.getInstance().getWindowHeight();
		Path path;

		double step = 40 * (wH - currentPosition.getY()) / KuVidGame.getL();

		path = new StraightLinePath(currentPosition.getX(), currentPosition.getY(), currentPosition.getX(), wH,
				(int) step);

		return path;
	}
	
	@Override
	public boolean isMatchMol(ObjectType molType) {
		// TODO Auto-generated method stub
		return molType == ObjectType.SIGMA_M;
	}


	@Override
	public boolean isMatchAtom(ObjectType atomType) {
		// TODO Auto-generated method stub
		return atomType == ObjectType.SIGMA;
	}

}
