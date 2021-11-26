package domain.objects.behaviors;

import domain.objects.KuvidObject;

public class ShootByShooter implements ShootBehavior {

	@Override
	public void shoot(KuvidObject object) {
		// TODO Auto-generated method stub
		object.move();
	}
	
	
}
