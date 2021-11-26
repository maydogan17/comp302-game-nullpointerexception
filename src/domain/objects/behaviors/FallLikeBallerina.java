package domain.objects.behaviors;

import domain.objects.KuvidObject;
import domain.objects.WallType;

public class FallLikeBallerina implements FallBehavior {


	@Override
	public void fall(KuvidObject obj) {
		if (obj.getPath().hasMoreSteps()) {
			obj.getObjectHitbox().setCenterAndUpdate(obj.getPath().nextPosition());
			obj.rotate();
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
