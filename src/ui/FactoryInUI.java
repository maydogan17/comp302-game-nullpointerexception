package ui;

import domain.factories.AtomFactory;
import domain.factories.FactoryListener;
import domain.factories.MoleculeFactory;
import domain.factories.PowerupFactory;
import domain.factories.ReactionBlockerFactory;
import domain.objects.Shootable;
import domain.objects.atoms.Atom;
import domain.objects.molecule.Molecule;
import domain.objects.powerups.Powerup;
import domain.objects.reactionblocker.Rb;
import ui.frame.SkyPanel;
import ui.inaction.AtomInAction;
import ui.inaction.MoleculeInAction;
import ui.inaction.PowerupInAction;
import ui.inaction.ReactionBlockerInAction;
import ui.inaction.ShootableInAction;

public class FactoryInUI implements FactoryListener {

	private SkyPanel skyPanel;
	private static FactoryInUI instance;

	public static FactoryInUI getInstance() {
		if (instance == null) {
			instance = new FactoryInUI();
			AtomFactory.getInstance().setFactoryListener(instance);
			MoleculeFactory.getInstance().setFactoryListener(instance);
			PowerupFactory.getInstance().setFactoryListener(instance);
			ReactionBlockerFactory.getInstance().setFactoryListener(instance);
		}
		return instance;
	}
	
	public void reset() {
		instance=null;
	}


	private FactoryInUI() {

	}

	public SkyPanel getSkyPanel() {
		return skyPanel;
	}

	public void setSkyPanel(SkyPanel skyPanel) {
		this.skyPanel = skyPanel;
	}

	@Override
	public void addMoleculeInActionToSkyPanel(Molecule mol) {
		MoleculeInAction molInAction = new MoleculeInAction(getSkyPanel(), mol);
		mol.addMoleculeListener(molInAction);
		getSkyPanel().addObjectInAction(molInAction);
	}

	@Override
	public void addShootableInActionToSkyPanel(Shootable shootable) {
		// TODO Auto-generated method stub
		ShootableInAction shootableInAction = null;
		if (shootable instanceof Powerup) {
			shootableInAction = new PowerupInAction(getSkyPanel(), (Powerup) shootable);
		} else if (shootable instanceof Atom) {
			shootableInAction = new AtomInAction(getSkyPanel(), (Atom) shootable);
		}

		shootable.addShootableListener(shootableInAction);
		getSkyPanel().addObjectInAction(shootableInAction);
	}

	@Override
	public void addRbInActionToSkyPanel(Rb rb) {
		// TODO Auto-generated method stub
		ReactionBlockerInAction rbInAction = new ReactionBlockerInAction(getSkyPanel(), rb);
		rb.addRbListener(rbInAction);
		getSkyPanel().addObjectInAction(rbInAction);

	}

}
