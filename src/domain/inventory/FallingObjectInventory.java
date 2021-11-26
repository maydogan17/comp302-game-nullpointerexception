package domain.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import domain.catalog.molecule.MoleculeDescription;
import domain.catalog.powerup.PowerupDescription;
import domain.catalog.rb.RbDescription;
import domain.factories.MoleculeFactory;
import domain.factories.ObjectType;
import domain.factories.PowerupFactory;
import domain.factories.ReactionBlockerFactory;
import domain.hitbox.Point;
import domain.kuvidgame.KuVidGame;
import domain.kuvidgame.NotEnoughMoleculesException;
import domain.kuvidgame.NotEnoughPowerupsException;
import domain.kuvidgame.NotEnoughReactionBlockerException;
import domain.objects.molecule.Molecule;
import domain.objects.powerups.Powerup;
import domain.objects.reactionblocker.Rb;

public class FallingObjectInventory {

	// This class creates and controls the remaining number of falling objects.
	private Random rand = new Random();
	private Map<ObjectType, Integer> storedMolMap = new HashMap<ObjectType, Integer>();
	List<ObjectType> molTypes = new ArrayList<>();
	private Molecule randomMolecule;

	private Map<ObjectType, Integer> storedPowMap = new HashMap<ObjectType, Integer>();
	List<ObjectType> powTypes = new ArrayList<>();
	private Powerup randomPowerup;

	private Map<ObjectType, Integer> storedRbMap = new HashMap<ObjectType, Integer>();
	List<ObjectType> rbTypes = new ArrayList<>();
	private Rb randomRb;

	///////////////////////////// MOLECULES////////////////////////////////////

	public FallingObjectInventory(int molNumber, int powNumber, int rbNumber) {
		this.getStoredMolMap().put(ObjectType.ALPHA_M, molNumber / 4);
		this.getStoredMolMap().put(ObjectType.BETA_M, molNumber / 4);
		this.getStoredMolMap().put(ObjectType.GAMMA_M, molNumber / 4);
		this.getStoredMolMap().put(ObjectType.SIGMA_M, molNumber / 4);

		this.getStoredPowMap().put(ObjectType.ALPHA_POW, powNumber / 4);
		this.getStoredPowMap().put(ObjectType.BETA_POW, powNumber / 4);
		this.getStoredPowMap().put(ObjectType.GAMMA_POW, powNumber / 4);
		this.getStoredPowMap().put(ObjectType.SIGMA_POW, powNumber / 4);

		this.getStoredRbMap().put(ObjectType.ALPHA_RB, rbNumber / 4);
		this.getStoredRbMap().put(ObjectType.BETA_RB, rbNumber / 4);
		this.getStoredRbMap().put(ObjectType.GAMMA_RB, rbNumber / 4);
		this.getStoredRbMap().put(ObjectType.SIGMA_RB, rbNumber / 4);

		initMolTypes();
		initPowTypes();
		initRbTypes();
	}

	public FallingObjectInventory(Map<ObjectType, Integer> remainingMolMap, Map<ObjectType, Integer> remainingPowMap,
			Map<ObjectType, Integer> remainingRbMap) {

		this.setStoredMolMap(remainingMolMap);
		this.setStoredPowMap(remainingPowMap);
		this.setStoredRbMap(remainingRbMap);

		initMolTypes();
		initPowTypes();
		initRbTypes();

	}

	public void sendMolecule() throws NotEnoughMoleculesException {
		// creates molecules randomly and according to specified molecule numbers
		if (!isMoleculesFinished()) {
			Molecule mol = getRandomStoredMolecule();
			decreaseMol(mol.getMolDescr());
			mol.setMoleculeCenter(randomFallingPoint(mol));
			mol.initMolPath();
			KuVidGame.setMolNumber(KuVidGame.getMolNumber() - 1);

		}

	}

	public void decreaseMol(MoleculeDescription desc) {
		if (!isMoleculesFinished()) {
			if (storedMolMap.get(desc.getType()) > 0) {
				storedMolMap.put(desc.getType(), storedMolMap.get(desc.getType()) - 1);

			} else {
				System.out.println("Not enough Molecule Type");
			}
		}
	}

	public Molecule getRandomStoredMolecule() {
		ObjectType chosen = getRandomMoleculeType();

		if (storedMolMap.get(chosen) > 0) {
			randomMolecule = MoleculeFactory.getInstance().createObject(chosen);

		} else {
			if (isMoleculesFinished()) {
				System.out.println("Molecules are finished");
			} else {
				getRandomStoredMolecule();
			}
		}
		return randomMolecule;
	}

	public ObjectType getRandomMoleculeType() {
		ObjectType chosen = null;
		Random r = new Random();
		int random = r.nextInt(4);
		switch (random) {
		case 0:
			chosen = ObjectType.ALPHA_M;
			break;
		case 1:
			chosen = ObjectType.BETA_M;
			break;
		case 2:
			chosen = ObjectType.GAMMA_M;
			break;
		case 3:
			chosen = ObjectType.SIGMA_M;
			break;
		}
		return chosen;
	}

	@SuppressWarnings("unused")
	public Point randomFallingPoint(Molecule mol) {
		// gives random starting point where molecules start to fall
		// this random point is at the top of the panel
		int wW = KuVidGame.getInstance().getWindowWidth();
		int molMaxWidth = mol.getMolDescr().getImageSize().getWidth();
		double dev = KuVidGame.getL() / Math.sin(Math.toRadians(45)); // it is a mystery

		int x = (molMaxWidth / 2) + rand.nextInt((wW - molMaxWidth));
		// returns ints between molDiameter/2 and
		// windowWidth-molDiameter/2
		// so Molecule won't be out of panel

		int y = -molMaxWidth; // outside of panel
//		int y = 200; // deneme
		return new Point(x, y);

	}

	private void initMolTypes() {
		molTypes.add(ObjectType.ALPHA_M);
		molTypes.add(ObjectType.BETA_M);
		molTypes.add(ObjectType.GAMMA_M);
		molTypes.add(ObjectType.SIGMA_M);
	}

	private boolean isMoleculesFinished() {
		boolean isEmpty = true; // assumes molecules are finished
		for (ObjectType a : storedMolMap.keySet()) {
			if (storedMolMap.get(a) != 0)
				isEmpty = false; // if one of the molTypes is not empty isEmpty equals false.
		}
		return isEmpty;
	}

	public Map<ObjectType, Integer> getStoredMolMap() {
		return storedMolMap;
	}

	public void setStoredMolMap(Map<ObjectType, Integer> storedMolMap) {
		this.storedMolMap = storedMolMap;
	}

	/////////////////////////////////////// MOLECULES//////////////////////////////////////////////////
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	////////////////////////////////////// POWERUPS//////////////////////////////////////////////////
	public Map<ObjectType, Integer> getStoredPowMap() {
		return storedPowMap;
	}

	public void setStoredPowMap(Map<ObjectType, Integer> storedPowMap) {
		this.storedPowMap = storedPowMap;
	}

	private void initPowTypes() {
		powTypes.add(ObjectType.ALPHA_POW);
		powTypes.add(ObjectType.BETA_POW);
		powTypes.add(ObjectType.GAMMA_POW);
		powTypes.add(ObjectType.SIGMA_POW);
	}

	public void sendPowerups() throws NotEnoughPowerupsException {
		if (!isPowerupsFinished()) {
			Powerup pow = getRandomStoredPowerup();
			decreasePow(pow.getPowDescr());
			pow.setPowerupCenter(randomFallingPointPowerup(pow));
			pow.initPowPath();
			KuVidGame.setPowNumber(KuVidGame.getPowNumber() - 1);
		}

	}

	private boolean isPowerupsFinished() {
		boolean isEmpty = true;
		for (ObjectType a : storedPowMap.keySet()) {
			if (storedPowMap.get(a) != 0)
				isEmpty = false;
		}
		return isEmpty;
	}

	public Powerup getRandomStoredPowerup() {
		ObjectType chosen = getRandomPowerupType();

		if (storedPowMap.get(chosen) > 0) {
			randomPowerup = PowerupFactory.getInstance().createObject(chosen);

		} else {
			if (isPowerupsFinished()) {
				System.out.println("Powerups are finished");
			} else {
				getRandomStoredPowerup();
			}
		}
		return randomPowerup;
	}

	public ObjectType getRandomPowerupType() {
		ObjectType chosen = null;
		Random r = new Random();
		int random = r.nextInt(4);
		switch (random) {
		case 0:
			chosen = ObjectType.ALPHA_POW;
			break;
		case 1:
			chosen = ObjectType.BETA_POW;
			break;
		case 2:
			chosen = ObjectType.GAMMA_POW;
			break;
		case 3:
			chosen = ObjectType.SIGMA_POW;
			break;
		}
		return chosen;
	}

	public void decreasePow(PowerupDescription desc) {
		if (!isPowerupsFinished()) {
			if (storedPowMap.get(desc.getType()) > 0) {
				storedPowMap.put(desc.getType(), storedPowMap.get(desc.getType()) - 1);
			} else {
				System.out.println("Not enough Powerup Type");
			}
		}
	}

	@SuppressWarnings("unused")
	public Point randomFallingPointPowerup(Powerup pow) {
		int wW = KuVidGame.getInstance().getWindowWidth();
		int powDiameter = pow.getPowDescr().getDiameter();
		double dev = KuVidGame.getL() / Math.sin(Math.toRadians(45));

		int x = (powDiameter / 2) + rand.nextInt((wW - powDiameter));

		int y = -powDiameter;
		return new Point(x, y);

	}

	////////////////////////////////////// POWERUPS////////////////////////////////////////////////////
	// ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
	////////////////////////////////////// REACTION/////////////////////////////////////////
	////////////////////////////////////// BLOCKERS/////////////////////////////////////////

	public Map<ObjectType, Integer> getStoredRbMap() {
		return storedRbMap;
	}

	public void setStoredRbMap(Map<ObjectType, Integer> storedRbMap) {
		this.storedRbMap = storedRbMap;
	}

	private void initRbTypes() {
		rbTypes.add(ObjectType.ALPHA_RB);
		rbTypes.add(ObjectType.BETA_RB);
		rbTypes.add(ObjectType.GAMMA_RB);
		rbTypes.add(ObjectType.SIGMA_RB);
	}

	public void sendReactionBlockers() throws NotEnoughReactionBlockerException {
		if (!isRbFinished()) {
			Rb rb = getRandomStoredRb();
			decreaseRb(rb.getRbDescr());
			rb.setRbCenter(randomFallingPointRb(rb));
			rb.initRbPath();
			KuVidGame.setRbNumber(KuVidGame.getRbNumber() - 1);
		}

	}

	public Rb getRandomStoredRb() {
		ObjectType chosen = getRandomRbType();

		if (storedRbMap.get(chosen) > 0) {
			randomRb = ReactionBlockerFactory.getInstance().createObject(chosen);

		} else {
			if (isRbFinished()) {
				System.out.println("Reaction Blockers are finished");
			} else {
				getRandomStoredRb();
			}
		}
		return randomRb;
	}

	public ObjectType getRandomRbType() {
		ObjectType chosen = null;
		Random r = new Random();
		int random = r.nextInt(4);
		switch (random) {
		case 0:
			chosen = ObjectType.ALPHA_RB;
			break;
		case 1:
			chosen = ObjectType.BETA_RB;
			break;
		case 2:
			chosen = ObjectType.GAMMA_RB;
			break;
		case 3:
			chosen = ObjectType.SIGMA_RB;
			break;
		}
		return chosen;
	}

	public void decreaseRb(RbDescription desc) {
		if (!isRbFinished()) {
			if (storedRbMap.get(desc.getType()) > 0) {
				storedRbMap.put(desc.getType(), storedRbMap.get(desc.getType()) - 1);
			} else {
				System.out.println("Not enough Reaction Blocker Type");
			}
		}
	}

	private boolean isRbFinished() {
		boolean isEmpty = true;
		for (ObjectType a : storedRbMap.keySet()) {
			if (storedRbMap.get(a) != 0)
				isEmpty = false;
		}
		return isEmpty;
	}

	@SuppressWarnings("unused")
	public Point randomFallingPointRb(Rb rb) {
		int wW = KuVidGame.getInstance().getWindowWidth();
		int rbDiameter = rb.getRbDescr().getDiameter();
		double dev = KuVidGame.getL() / Math.sin(Math.toRadians(45));

		int x = (rbDiameter / 2) + rand.nextInt((wW - rbDiameter));

		int y = -rbDiameter;
		return new Point(x, y);

	}

	////////////////////////////////////// REACTION/////////////////////////////////////////
	////////////////////////////////////// BLOCKERS/////////////////////////////////////////
}
