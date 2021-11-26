package domain.objects.atoms.shields;

import domain.factories.ObjectType;
import domain.objects.atoms.Atom;

public class LotaShield extends ShieldDecorator{

	public LotaShield(Atom atom) {
		super(atom);
		// TODO Auto-generated constructor stub
		setBoost(0.1);
		atom.getShieldMap().put(ObjectType.LOTA, atom.getShieldMap().get(ObjectType.LOTA) + 1);
	}
	
	@Override
	public double getEfficiency() {
		// TODO Auto-generated method stub

		double result = (1 - getAtom().getEfficiency()) * getBoost();

		result += getAtom().getEfficiency();

		return result;
	}
	
	@Override
	public double getDeltaH() {
		return 0.93 * getAtom().getDeltaH();
	}
}
