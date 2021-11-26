package domain.objects.atoms.shields;

import java.util.Random;

import domain.factories.ObjectType;
import domain.objects.atoms.Atom;

public class ThetaShield extends ShieldDecorator {

	Random rand = new Random();

	public ThetaShield(Atom atom) {
		super(atom);
		// TODO Auto-generated constructor stub
		setBoost(0.05 + rand.nextDouble() / 10);
		atom.getShieldMap().put(ObjectType.THETA, atom.getShieldMap().get(ObjectType.THETA) + 1);
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
		return 0.91 * getAtom().getDeltaH();
	}

}
