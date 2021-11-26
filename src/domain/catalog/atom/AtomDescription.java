package domain.catalog.atom;

import java.util.ArrayList;
import java.util.HashMap;

import domain.catalog.ObjectDescription;
import domain.factories.ObjectType;
import domain.objects.atoms.Atom;

public abstract class AtomDescription extends ObjectDescription {

	private ObjectType type; // Type of the Atom
	private HashMap<ObjectType, Integer> exchangeRate; // Exchange rate is used in blending and breaking atoms
	private double stability;
	private double protonAmount;

	private ArrayList<Integer> neutronNumbers = new ArrayList<Integer>();


	private int diameter; 

	public abstract void calculateEfficiency(Atom atom);

	public ObjectType getType() {
		return type;
	}

	public void setType(ObjectType type) {
		this.type = type;
	}

	public HashMap<ObjectType, Integer> getExchangeRate() {
		return exchangeRate;
	}

	public void setExchangeRate(HashMap<ObjectType, Integer> exchangeRate) {
		this.exchangeRate = exchangeRate;
	}

	public int getDiameter() {
		return diameter;
	}

	public void setDiameter(int diameter) {
		this.diameter = diameter;
	}

	public abstract boolean isMatch(ObjectType molType);

	public double getStability() {
		return stability;
	}

	public void setStability(double stability) {
		this.stability = stability;
	}

	public double getProtonAmount() {
		return protonAmount;
	}

	public void setProtonAmount(int protonAmount) {
		this.protonAmount = protonAmount;
	}

	public ArrayList<Integer> getNeutronNumbers() {
		return neutronNumbers;
	}

	public void setNeutronNumbers(ArrayList<Integer> neutronNumbers) {
		this.neutronNumbers = neutronNumbers;
	}

}
