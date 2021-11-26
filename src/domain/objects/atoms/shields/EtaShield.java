package domain.objects.atoms.shields;

import domain.factories.ObjectType;
import domain.objects.atoms.Atom;

public class EtaShield extends ShieldDecorator{

	public EtaShield(Atom atom) {
		super(atom);
		// TODO Auto-generated constructor stub
		setBoost(0.05);
		atom.getShieldMap().put(ObjectType.ETA, atom.getShieldMap().get(ObjectType.ETA) + 1);
	}


	@Override
	public double getEfficiency() {
		// TODO Auto-generated method stub
		double neut = getAtom().getNeutronAmount();
		double prot = getAtom().getAtomDescr().getProtonAmount();
		
		double result = 0.0;
		
		if(neut != prot) {
			result = (1 - getAtom().getEfficiency()) * (Math.abs(neut - prot)/prot);
		}
		else {
			result = (1- getAtom().getEfficiency()) * getBoost();
		}
		
		result += getAtom().getEfficiency();

		return result;
	}
	
	@Override
	public double getDeltaH() {
		return 0.95 * getAtom().getDeltaH();
	}

}
