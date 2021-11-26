package domain.catalog.atom;

import java.util.HashMap;

import domain.factories.ObjectType;
import domain.kuvidgame.KuVidGame;
import domain.objects.atoms.Atom;

public class BetaAtomDescription extends AtomDescription {

	public BetaAtomDescription() {
		loadExchangeMap();
		super.setType(ObjectType.BETA);
		super.setImageDirectory("images/betaAtom.png");
		super.setDiameter(KuVidGame.getL() / 4); 
		initBehavior();
	}

	private void loadExchangeMap() {
		super.setExchangeRate(new HashMap<ObjectType, Integer>());
		super.getExchangeRate().put(ObjectType.ALPHA, 2);
	}

	@Override
	public boolean isMatch(ObjectType molType) {
		return molType == ObjectType.BETA_M;
	}

	private void initBehavior() {
		setStability(0.9);
		setProtonAmount(16);
		getNeutronNumbers().add(15);
		getNeutronNumbers().add(16);
		getNeutronNumbers().add(17);
		getNeutronNumbers().add(18);
		getNeutronNumbers().add(21);
	}	
	
	@Override
	public void calculateEfficiency(Atom atom) {
		double eff = getStability() - (0.5 * Math.abs(atom.getNeutronAmount() - getProtonAmount()) / getProtonAmount());
		atom.setEfficiency(eff);
	}

}
