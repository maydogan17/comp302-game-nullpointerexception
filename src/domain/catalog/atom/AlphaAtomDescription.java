package domain.catalog.atom;

import java.util.HashMap;

import domain.factories.ObjectType;
import domain.kuvidgame.KuVidGame;
import domain.objects.atoms.Atom;

public class AlphaAtomDescription extends AtomDescription {

	public AlphaAtomDescription() {
		loadExchangeMap();
		super.setType(ObjectType.ALPHA);
		setImageDirectory("images/alphaAtom.png");
		super.setDiameter(KuVidGame.getL() / 4); 
		initBehavior();
	}

	private void loadExchangeMap() {
		super.setExchangeRate(new HashMap<ObjectType, Integer>());
	}

	@Override
	public boolean isMatch(ObjectType molType) {
		return molType == ObjectType.ALPHA_M;
	}

	private void initBehavior() {
		setStability(0.85);
		setProtonAmount(8);
		getNeutronNumbers().add(7);
		getNeutronNumbers().add(8);
		getNeutronNumbers().add(9);
	}
	
	@Override
	public void calculateEfficiency(Atom atom) {
		double eff = (1 - (Math.abs(atom.getNeutronAmount() - getProtonAmount()) / getProtonAmount())) * getStability();
		
		atom.setEfficiency(eff);
	}

}
