package domain.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import domain.catalog.atom.AtomDescription;
import domain.catalog.powerup.PowerupDescription;
import domain.factories.AtomFactory;
import domain.factories.ObjectType;
import domain.objects.atoms.Atom;

public class Inventory {

	private Map<ObjectType, Integer> storedAtomsMap = new HashMap<ObjectType, Integer>();
	private Map<ObjectType, Integer> storedPWMap = new HashMap<ObjectType, Integer>();
	private Map<ObjectType, Integer> storedShieldMap = new HashMap<ObjectType, Integer>();
	private List<Atom> atomList = new ArrayList<Atom>();

	private Blender blender;

	public Inventory(int atom, int shield) {
		storedAtomsMap.put(ObjectType.ALPHA, atom / 4);
		storedAtomsMap.put(ObjectType.BETA, atom / 4);
		storedAtomsMap.put(ObjectType.GAMMA, atom / 4);
		storedAtomsMap.put(ObjectType.SIGMA, atom / 4);
		initAtomList(storedAtomsMap);

		storedShieldMap.put(ObjectType.ETA, shield / 4);
		storedShieldMap.put(ObjectType.THETA, shield / 4);
		storedShieldMap.put(ObjectType.LOTA, shield / 4);
		storedShieldMap.put(ObjectType.ZETA, shield / 4);

		storedPWMap.put(ObjectType.ALPHA_POW, 0);
		storedPWMap.put(ObjectType.BETA_POW, 0);
		storedPWMap.put(ObjectType.GAMMA_POW, 0);
		storedPWMap.put(ObjectType.SIGMA_POW, 0);
	}

	//this constructor is to use when loading the game!!
	public Inventory(Map<ObjectType, Integer> storedAtomMap, Map<ObjectType, Integer> storedShieldMap, 
			Map<ObjectType, Integer> storedPWMap, List<Atom> atomList) {
		setStoredAtomsMap(storedAtomMap);
		setAtomList(atomList);
		setStoredPWMap(storedPWMap);
		setStoredShieldMap(storedShieldMap);
	}

	public Atom getRandomAtom() {
		Random r = new Random();
		int index;
		if (atomList.size() != 0) {
			index = r.nextInt(atomList.size());
			return atomList.get(index);
		}
		return null;
	}

	public void deleteAtomFromList(Atom atom) {
		atomList.remove(atom);
		decreaseAtom(atom.getAtomDescr());
	}

	public void decreaseAtom(AtomDescription desc) {
		if (!isStoredAtomMapEmpty()) {
			if (storedAtomsMap.get(desc.getType()) > 0) {
				storedAtomsMap.put(desc.getType(), storedAtomsMap.get(desc.getType()) - 1);
			} else {
				System.out.println("Not enough Atom Type");
			}
		}
	}

	public void decreasePowerup(PowerupDescription desc) {
		if (!isStoredPowerupMapEmpty()) {
			if (storedPWMap.get(desc.getType()) > 0) {
				storedPWMap.put(desc.getType(), storedPWMap.get(desc.getType()) - 1);
			} 
		}else {
			System.out.println("Not enough Powerup Type");
		}
	}

	public boolean isStoredAtomMapEmpty() {
		boolean isEmpty = true; // assumes inv is empty
		for (ObjectType a : storedAtomsMap.keySet()) {
			if (storedAtomsMap.get(a) != 0)
				isEmpty = false; // if one of the atomtype is not empty isEmpty equals false.
		}
		return isEmpty;
	}

	public boolean isStoredPowerupMapEmpty() {
		boolean isEmpty = true; // assumes inv is empty
		for (ObjectType a : storedPWMap.keySet()) {
			if (storedPWMap.get(a) != 0)
				isEmpty = false; // if one of the atomtype is not empty isEmpty equals false.
		}
		return isEmpty;
	}


	public void initAtomList(Map<ObjectType, Integer> storedAtomMap) {
		for (ObjectType type : storedAtomMap.keySet()) {
			for (int i = 0; i < storedAtomMap.get(type); i++) {
				atomList.add(AtomFactory.getInstance().createObject(type));
			}
		}
	}
	

	public void printInfo() {
		for (ObjectType x : storedAtomsMap.keySet()) {
			System.out.print(x);
			System.out.print(" -> ");
			System.out.println(storedAtomsMap.get(x));
		}

		for (ObjectType x : storedPWMap.keySet()) {
			System.out.print(x);
			System.out.print(" -> ");
			System.out.println(storedPWMap.get(x));
		}
	}

	public void storePowerup(ObjectType type) { // in case, shooter catches powerup, this increases the number of
												// powerup given as input by 1
		storedPWMap.put(type, storedPWMap.get(type) + 1);
	}

	public Map<ObjectType, Integer> getStoredAtomsMap() {
		return storedAtomsMap;
	}

	public void setStoredAtomsMap(Map<ObjectType, Integer> storedAtomsMap) {
		this.storedAtomsMap = storedAtomsMap;
	}

	public Map<ObjectType, Integer> getStoredPWMap() {
		return storedPWMap;
	}

	public void setStoredPWMap(Map<ObjectType, Integer> storedPWMap) {
		this.storedPWMap = storedPWMap;
	}

	public Blender getBlender() {
		return blender;
	}

	public void setBlender(Blender blender) {
		this.blender = blender;
	}

	public Map<ObjectType, Integer> getStoredShieldMap() {
		return storedShieldMap;
	}

	public void setStoredShieldMap(Map<ObjectType, Integer> storedShieldMap) {
		this.storedShieldMap = storedShieldMap;
	}

	public List<Atom> getAtomList() {
		return atomList;
	}

	public void setAtomList(List<Atom> atomList) {
		this.atomList = atomList;
	}

}
