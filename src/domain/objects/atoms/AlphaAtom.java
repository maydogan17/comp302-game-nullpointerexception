package domain.objects.atoms;


import domain.catalog.atom.AlphaAtomDescription;
import domain.factories.ObjectType;
import domain.hitbox.Dimension;
import domain.hitbox.HitBox;
import domain.hitbox.HitBoxCircle;
import domain.objects.behaviors.FallNoWay;
import domain.objects.behaviors.ShootByShooter;

public class AlphaAtom extends Atom {

	public AlphaAtom(AlphaAtomDescription atomDescr, HitBox atomHitBox) {
		this.setObjDescr(atomDescr);
		this.setImageSize(new Dimension(atomDescr.getDiameter(), atomDescr.getDiameter())); 
		this.setImagePosition(null);

		HitBoxCircle circularAtomHitBox = null;

		try {
			circularAtomHitBox = (HitBoxCircle) atomHitBox;
			circularAtomHitBox.setDiameter(atomDescr.getDiameter());
		} catch (ClassCastException e) {
			e.printStackTrace();
		}

		setObjectHitbox(circularAtomHitBox);
		setFallBehavior(new FallNoWay());
		setShootBehavior(new ShootByShooter());
		setPath(null);
		
		
		getShieldMap().put(ObjectType.ETA, 0);
		getShieldMap().put(ObjectType.THETA, 0);
		getShieldMap().put(ObjectType.LOTA, 0);
		getShieldMap().put(ObjectType.ZETA, 0);
	}

}
