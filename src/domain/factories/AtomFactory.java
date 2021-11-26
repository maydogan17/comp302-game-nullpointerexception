package domain.factories;

import java.util.Random;

import domain.catalog.atom.AlphaAtomDescription;
import domain.catalog.atom.BetaAtomDescription;
import domain.catalog.atom.GammaAtomDescription;
import domain.catalog.atom.SigmaAtomDescription;
import domain.hitbox.HitBoxCircle;
import domain.objects.atoms.AlphaAtom;
import domain.objects.atoms.Atom;
import domain.objects.atoms.BetaAtom;
import domain.objects.atoms.GammaAtom;
import domain.objects.atoms.SigmaAtom;

public class AtomFactory implements ObjectFactory<Atom> {

	private static AtomFactory atomFactory = null;
	private FactoryListener factoryListener;

	public static AtomFactory getInstance() {
		if (atomFactory == null) {
			atomFactory = new AtomFactory();
		}
		return atomFactory;
	}

	@Override
	public void setFactoryListener(FactoryListener fl) {
		factoryListener = fl;
	}

	public FactoryListener getFactoryListener() {
		return factoryListener;
	}

	private Atom createAtom(ObjectType type) {
		Atom atom = createAtomWithType(type); 
		setEfficiencyInDescription(atom);
		return atom;
	}
	
	private void setEfficiencyInDescription(Atom atom) {
		Random rand = new Random();
		int neutron = atom.getAtomDescr().getNeutronNumbers().get(rand.nextInt(atom.getAtomDescr().getNeutronNumbers().size()));
		atom.setNeutronAmount(neutron);
		atom.getAtomDescr().calculateEfficiency(atom);
	}

	@Override
	public Atom createObject(ObjectType type) {
		return createAtom(type);
	}
	
	private Atom createAtomWithType(ObjectType type) {
		switch(type) {
		case ALPHA:
			return new AlphaAtom(new AlphaAtomDescription(), new HitBoxCircle());
		case BETA:
			return new BetaAtom(new BetaAtomDescription(), new HitBoxCircle());
		case GAMMA:
			return new GammaAtom(new GammaAtomDescription(), new HitBoxCircle());
		case SIGMA:
			return new SigmaAtom(new SigmaAtomDescription(), new HitBoxCircle());
		default:
			return null;
		}
	}

}
