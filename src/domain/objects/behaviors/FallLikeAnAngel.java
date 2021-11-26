package domain.objects.behaviors;

import domain.objects.KuvidObject;
import domain.objects.WallType;

public class FallLikeAnAngel implements FallBehavior {

	@Override
	public void fall(KuvidObject obj) {
		// TODO Auto-generated method stub
		if (obj.getPath().hasMoreSteps()) {
			obj.getObjectHitbox().setCenterAndUpdate(obj.getPath().nextPosition());
		}
		if (obj.getObjectHitbox().doHitBoxCollideWithWall() == WallType.WALL_RIGHT || 
				obj.getObjectHitbox().doHitBoxCollideWithWall() == WallType.WALL_LEFT) {
			obj.bounce();
		}
		if (obj.getObjectHitbox().doHitBoxCollideWithWall() == WallType.WALL_BOTTOM) {
			obj.destroy();
		}
	}

}
