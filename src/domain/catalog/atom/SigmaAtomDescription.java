package domain.catalog.atom;

import java.util.HashMap;

import domain.factories.ObjectType;
import domain.kuvidgame.KuVidGame;
import domain.objects.atoms.Atom;

public class SigmaAtomDescription extends AtomDescription {

	public SigmaAtomDescription() {
		loadExchangeMap();
		super.setType(ObjectType.SIGMA);
		super.setImageDirectory("images/sigmaAtom.png");
		super.setDiameter(KuVidGame.getL() / 4); 
		initBehavior();

	}

	private void loadExchangeMap() {
		super.setExchangeRate(new HashMap<ObjectType, Integer>());
		super.getExchangeRate().put(ObjectType.ALPHA, 4);
		super.getExchangeRate().put(ObjectType.BETA, 3);
		super.getExchangeRate().put(ObjectType.GAMMA, 2);
	}

	@Override
	public boolean isMatch(ObjectType molType) {
		return molType == ObjectType.SIGMA_M;
	}

	private void initBehavior() {
		setStability(0.7);
		setProtonAmount(64);
		getNeutronNumbers().add(63);
		getNeutronNumbers().add(64);
		getNeutronNumbers().add(67);
	}

	@Override
	public void calculateEfficiency(Atom atom) {
		double eff = ((1 + getStability()) / 2)
				+ (Math.abs(atom.getNeutronAmount() - getProtonAmount()) / getProtonAmount());
		atom.setEfficiency(eff);
	}

}
