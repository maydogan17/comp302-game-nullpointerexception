package domain.catalog.rb;

import domain.factories.ObjectType;
import domain.hitbox.Point;
import domain.kuvidgame.KuVidGame;
import domain.objects.path.Path;
import domain.objects.path.StraightLinePath;
import domain.objects.path.ZigZagPath;

public class GammaRBDescription extends RbDescription {

	public GammaRBDescription() {
		super();
		super.setRbType(ObjectType.GAMMA_RB);
		super.setDiameter(KuVidGame.getL()*2);
		this.setImageDirectory("images/gammaRB.png");
	}

	@Override
	public Path getRBPath(Point currentPosition) {
		int wH = KuVidGame.getInstance().getWindowHeight();
		Path path;
		if (currentPosition.getY() >= wH / 2) {
			double step = 40 * (wH - currentPosition.getY()) / KuVidGame.getL();

			double dev = KuVidGame.getL() / Math.sin(Math.toRadians(45));

			path = new ZigZagPath(currentPosition, wH, (int) dev, (int) dev, (int) step);
		} else {
			double step = 40 * (wH / 2 - currentPosition.getY()) / KuVidGame.getL();

			path = new StraightLinePath(currentPosition.getX(), currentPosition.getY(), currentPosition.getX(), wH / 2,
					(int) step);
		}
		return path;
	}
	
	@Override
	public boolean isMatchMol(ObjectType molType) {
		// TODO Auto-generated method stub
		return molType == ObjectType.GAMMA_M;
	}


	@Override
	public boolean isMatchAtom(ObjectType atomType) {
		// TODO Auto-generated method stub
		return atomType == ObjectType.GAMMA;
	}

}
