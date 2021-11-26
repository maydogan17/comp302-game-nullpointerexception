package domain.objects.atoms.shields;

import domain.factories.ObjectType;
import domain.objects.atoms.Atom;

public class ZetaShield extends ShieldDecorator {

	public ZetaShield(Atom atom) {
		super(atom);
		// TODO Auto-generated constructor stub
		setBoost(0.2);
		atom.getShieldMap().put(ObjectType.ZETA, atom.getShieldMap().get(ObjectType.ZETA) + 1);
	}

	@Override
	public double getEfficiency() {
		// TODO Auto-generated method stub

		double neut = getAtom().getNeutronAmount();
		double prot = getAtom().getAtomDescr().getProtonAmount();

		if (neut == prot) {
			double result = (1 - getAtom().getEfficiency()) * getBoost();

			result += getAtom().getEfficiency();

			return result;
		} else {
			return getAtom().getEfficiency();
		}

	}
	
	@Override
	public double getDeltaH() {
		return 0.89 * getAtom().getDeltaH();
	}

}
