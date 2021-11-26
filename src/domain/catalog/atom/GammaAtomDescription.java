package domain.catalog.atom;

import java.util.HashMap;

import domain.factories.ObjectType;
import domain.kuvidgame.KuVidGame;
import domain.objects.atoms.Atom;

public class GammaAtomDescription extends AtomDescription {

	public GammaAtomDescription() {
		loadExchangeMap();
		super.setType(ObjectType.GAMMA);
		super.setImageDirectory("images/gammaAtom.png");
		super.setDiameter(KuVidGame.getL() / 4); 
		initBehavior();
	}

	private void loadExchangeMap() {
		super.setExchangeRate(new HashMap<ObjectType, Integer>());
		super.getExchangeRate().put(ObjectType.ALPHA, 3);
		super.getExchangeRate().put(ObjectType.BETA, 2);
	}

	@Override
	public boolean isMatch(ObjectType molType) {
		return molType == ObjectType.GAMMA_M;
	}

	private void initBehavior() {
		setStability(0.8);
		setProtonAmount(32);
		getNeutronNumbers().add(29);
		getNeutronNumbers().add(32);
		getNeutronNumbers().add(33);
	}
	
	@Override
	public void calculateEfficiency(Atom atom) {
		double eff = getStability() + (Math.abs(atom.getNeutronAmount() - getProtonAmount()) / (2 * getProtonAmount()));
		atom.setEfficiency(eff);
	}
}
