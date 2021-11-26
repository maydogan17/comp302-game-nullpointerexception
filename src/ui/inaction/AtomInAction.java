package ui.inaction;

import java.util.ArrayList;
import java.util.List;

import domain.objects.ShootableListener;
import domain.objects.atoms.Atom;
import domain.objects.molecule.Molecule;
import domain.objects.reactionblocker.Rb;
import ui.frame.SkyPanel;

public class AtomInAction extends ShootableInAction implements ShootableListener {

	public AtomInAction(SkyPanel sp, Atom atom) {
		super(sp, atom);

	}

	public void initialize(Atom atom) {
		atom.addShootableListener(this);
	}
	@Override
	public List<Molecule> getMoleculesInPanel() {
		// Returns the molecules currently in the screen or panel
		List<Molecule> molecules = new ArrayList<Molecule>();
		for (MoleculeInAction molic : getSuperPanel().getMoleculeInActionsInPanel()) {
			molecules.add((Molecule) molic.getObject()); // sikinti yok
		}
		return molecules;

	}
	@Override
	public List<Rb> getReactionBlockersInPanel() {
		// Returns the molecules currently in the screen or panel
		List<Rb> rbs = new ArrayList<Rb>();
		for (ReactionBlockerInAction rbac : getSuperPanel().getRbInActionsInPanel()) {
			rbs.add((Rb) rbac.getObject()); // sikinti yok
		}
		return rbs;

	}

	@Override
	public void onLocationChangeEvent() {
		getSuperPanel().repaint();

	}

	@Override
	public void onRemoveEvent() {
		getSuperPanel().removeObjectInAction(this);
	}

	@Override
	public void updateShootableInActionInSkyPanel() {
		// TODO Auto-generated method stub
		getSuperPanel().setShootableInActionInBullet(this);
	}


}
