package domain.catalog.rb;

import domain.factories.ObjectType;
import domain.hitbox.Point;
import domain.kuvidgame.KuVidGame;
import domain.objects.path.Path;
import domain.objects.path.ZigZagPath;

public class AlphaRBDescription extends RbDescription {

	public AlphaRBDescription() {
		super();
		super.setRbType(ObjectType.ALPHA_RB);
		super.setDiameter(KuVidGame.getL()*2);
		this.setImageDirectory("images/alphaRB.png");
	}


	@Override
	public Path getRBPath(Point currentPosition) {
		int wH = KuVidGame.getInstance().getWindowHeight();
		
		double step = 40*(wH - currentPosition.getY())/KuVidGame.getL();
		
		double dev = KuVidGame.getL()/Math.sin(Math.toRadians(45));
		
		return new ZigZagPath(currentPosition, wH, (int)dev, (int) dev, (int)step);
	}



	@Override
	public boolean isMatchMol(ObjectType molType) {
		// TODO Auto-generated method stub
		return molType == ObjectType.ALPHA_M;
	}


	@Override
	public boolean isMatchAtom(ObjectType atomType) {
		// TODO Auto-generated method stub
		return atomType == ObjectType.ALPHA;
	}


}
