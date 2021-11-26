package ui.inaction;

import java.util.ArrayList;
import java.util.List;

import domain.objects.KuvidObject;
import domain.objects.ShootableListener;
import domain.objects.molecule.Molecule;
import domain.objects.powerups.Powerup;
import domain.objects.reactionblocker.Rb;
import ui.frame.SkyPanel;

public class PowerupInAction extends ShootableInAction implements ShootableListener {

	public PowerupInAction(SkyPanel sp, KuvidObject obj) {
		super(sp, obj);

	}

	public void initialize(Powerup pow) {
		pow.addShootableListener(this);
	}

	@Override
	public void onLocationChangeEvent() {

		getSuperPanel().repaint();
	}

	@Override
	public void onRemoveEvent() {

		getSuperPanel().removeObjectInAction(this);
		getSuperPanel().getPowerupInActionsInPanel().remove(this);
	}

	@Override
	public void updateShootableInActionInSkyPanel() {

		getSuperPanel().setShootableInActionInBullet(this);
	}

	@Override
	public List<Molecule> getMoleculesInPanel() {

		return null;
	}

	@Override
	public List<Rb> getReactionBlockersInPanel() {
		
		List<Rb> rb = new ArrayList<Rb>();
		for (ReactionBlockerInAction rbic : getSuperPanel().getRbInActionsInPanel()) {
			rb.add((Rb) rbic.getObject()); // sikinti yok
		}
		return rb;
		
	}

}
