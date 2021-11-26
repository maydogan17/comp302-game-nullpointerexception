package domain.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import domain.catalog.Catalog;
import domain.catalog.atom.AtomDescription;
import domain.factories.AtomFactory;
import domain.factories.ObjectType;
import domain.inventory.exceptions.InvalidExchangeRateException;
import domain.inventory.exceptions.NotEnoughSourceAtomException;
import domain.kuvidgame.KuVidGame;
import domain.objects.atoms.Atom;

public class Blender {

	Map<ObjectType, Integer> atomMap = KuVidGame.getInstance().getInv().getStoredAtomsMap();
	Catalog catalog = KuVidGame.getInstance().getCatalog();
	Map<ObjectType, Integer> exchangeRateMap;
	Map<Integer, ObjectType> atomRank = new HashMap<Integer, ObjectType>();

	public Blender() {
		loadAtomRank();
	}

	public void runBlender(int sourceType, int targetType) {
		if (sourceType < targetType) {
			try {
				blendAtom(atomRank.get(sourceType), atomRank.get(targetType));
			} catch (NotEnoughSourceAtomException | InvalidExchangeRateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			try {
				breakAtom(atomRank.get(sourceType), atomRank.get(targetType));
			} catch (NotEnoughSourceAtomException | InvalidExchangeRateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void loadAtomRank() {
		atomRank.put(1, ObjectType.ALPHA);
		atomRank.put(2, ObjectType.BETA);
		atomRank.put(3, ObjectType.GAMMA);
		atomRank.put(4, ObjectType.SIGMA);
	}

	public void blendAtom(ObjectType sourceAtom, ObjectType targetAtom)
			throws NotEnoughSourceAtomException, InvalidExchangeRateException {
		// REQUIRES: sourceAtom's rank < targetAtom's rank
		// MODIFIES: KuVidGame.getInstance().getInv().getAtomList()
		// KuVidGame.getInstance().getInv().getStoredAtomMap()
		// EFFECTS: removes corresponding amount of source atom from inventory and adds
		// corresponding amount of target atom from inventory.
		// throws NotEnoughSourceAtomException if the required amount of source atom is
		// not available in the inventory.
		// throws InvalidExchange request if the sourceAtom's rank is higher than
		// targetAtom's rank.
		int amount = atomMap.get(sourceAtom);
		exchangeRateMap = catalog.getAtomDescription(targetAtom).getExchangeRate();

		if (doesExist(exchangeRateMap, sourceAtom)) {
			if (amount >= exchangeRateMap.get(sourceAtom)) {
				int decrease = exchangeRateMap.get(sourceAtom);
				decreaseAmount(KuVidGame.getInstance().getInv(), sourceAtom, decrease);
				increaseAmount(KuVidGame.getInstance().getInv(), targetAtom, 1);
			} else {
//				throw new NotEnoughSourceAtomException("No enough of source atom");
				System.out.println("No enough of source atom");
			}
		} else {
//			throw new InvalidExchangeRateException("Invalid exchange request");
			System.out.println("Invalid exchange request");
		}

	}

	public void breakAtom(ObjectType sourceAtom, ObjectType targetAtom)
			throws NotEnoughSourceAtomException, InvalidExchangeRateException { // breakAtom(SIGMA, ALPHA)
		// System.out.println(atomMap);
		int amount = atomMap.get(sourceAtom);
		exchangeRateMap = catalog.getAtomDescription(sourceAtom).getExchangeRate();

		if (doesExist(exchangeRateMap, targetAtom)) {
			if (amount >= 1) {
				int increase = exchangeRateMap.get(targetAtom);
				decreaseAmount(KuVidGame.getInstance().getInv(), sourceAtom, 1);
				increaseAmount(KuVidGame.getInstance().getInv(), targetAtom, increase);
			} else {
//				throw new NotEnoughSourceAtomException("No enough of source atom");
				System.out.println("No enough of source atom");
			}
		} else {
//			throw new InvalidExchangeRateException("Invalid exchange request");
			System.out.println("Invalid exchange request");
		}

	}

	private void decreaseAmount(Inventory inv, ObjectType type, int amount) {
		inv.getStoredAtomsMap().put(type, inv.getStoredAtomsMap().get(type) - amount);
		deleteAtomFromAtomList(inv, type, amount);
	}

	private void increaseAmount(Inventory inv, ObjectType type, int amount) {
		inv.getStoredAtomsMap().put(type, inv.getStoredAtomsMap().get(type) + amount);
		addAtomToAtomList(inv, type, amount);
	}

	private boolean doesExist(Map<ObjectType, Integer> map, ObjectType type) {
		return map.keySet().contains(type);
	}

	private void deleteAtomFromAtomList(Inventory inv, ObjectType type, int amount) {
		AtomDescription atomdesc = KuVidGame.getInstance().getCatalog().getAtomDescription(type);
		List<Atom> toBeRemoved = new ArrayList<Atom>();
		int count = 0;
		for (Atom atom : inv.getAtomList()) {
			if (atom.getAtomDescr().equals(atomdesc) && count < amount) {
				toBeRemoved.add(atom);
				count++;
			}
		}
		inv.getAtomList().removeAll(toBeRemoved);
	}

	private void addAtomToAtomList(Inventory inv, ObjectType type, int amount) {
		for (int i = 0; i < amount; i++) {
			inv.getAtomList().add(AtomFactory.getInstance().createObject(type));
		}
	}

	public Map<ObjectType, Integer> getAtomMap() {
		return atomMap;
	}

	public void setAtomMap(Map<ObjectType, Integer> atomMap) {
		this.atomMap = atomMap;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}
}
