package domain.saveload;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import domain.factories.AtomFactory;
import domain.factories.MoleculeFactory;
import domain.factories.ObjectType;
import domain.factories.PowerupFactory;
import domain.factories.ReactionBlockerFactory;
import domain.hitbox.Point;
import domain.inventory.FallingObjectInventory;
import domain.inventory.Inventory;
import domain.kuvidgame.KuVidGame;
import domain.objects.atoms.Atom;
import domain.objects.atoms.shields.EtaShield;
import domain.objects.atoms.shields.LotaShield;
import domain.objects.atoms.shields.ThetaShield;
import domain.objects.atoms.shields.ZetaShield;
import domain.objects.molecule.Molecule;
import domain.objects.powerups.Powerup;
import domain.objects.reactionblocker.Rb;
import domain.saveload.helperobjects.HelperAtom;
import domain.saveload.helperobjects.HelperGame;
import domain.saveload.helperobjects.HelperMolecule;
import domain.saveload.helperobjects.HelperObject;
import domain.saveload.helperobjects.HelperPowerup;
import domain.saveload.helperobjects.HelperReactionBlocker;

public class GameLoadHelper {

	private HelperGame helperGame;
	private LoaderListener loaderListener;

	public GameLoadHelper(HelperGame helperGame, LoaderListener loaderListener) {
		this.helperGame = helperGame;
		this.loaderListener = loaderListener;
	}

	public void initGame() {

		KuVidGame.setL(helperGame.getL());
		KuVidGame.setMolNumber(helperGame.getRemainingMolNumber());
		KuVidGame.setPowNumber(helperGame.getRemainingPowNumber());
		KuVidGame.setRbNumber(helperGame.getRemainingRbNumber());
		KuVidGame.setValuesAccordingToDifficultyAndRemainingSeconds(helperGame.getRemainingSeconds(),
				helperGame.getDifficulty());
		KuVidGame.getInstance().setDifficulty(helperGame.getDifficulty());
		KuVidGame.getInstance().setAlphaMolStruct(helperGame.getAlphaMolStruct());
		KuVidGame.getInstance().setBetaMolStruct(helperGame.getBetaMolStruct());
		KuVidGame.getInstance().setLinearSpinning(helperGame.isLinearSpinning());
		KuVidGame.getInstance().setUserName(helperGame.getUsername());
		KuVidGame.getInstance().setScore(helperGame.getScore());
		KuVidGame.getInstance().setWindowHeight(helperGame.getWindowHeight());
		KuVidGame.getInstance().setWindowWidth(helperGame.getWindowWidth());
		KuVidGame.getInstance().setSongName(helperGame.getSongName());

		
		Inventory newInv = new Inventory(helperGame.getStoredAtomsMap(), helperGame.getStoredShieldMap(),
				helperGame.getStoredPWMap(), initAtomList(helperGame.getAtomList()));


		KuVidGame.getInstance().setInv(newInv);

		FallingObjectInventory newFallInv = new FallingObjectInventory(helperGame.getRemainingMolMap(),
				helperGame.getRemainingPowMap(), helperGame.getRemainingRbMap());

		KuVidGame.getInstance().setFallInv(newFallInv);

		KuVidGame.getInstance().createShooter();
		KuVidGame.getInstance().createBlender();
		KuVidGame.getInstance().getClock().start();

		
		KuVidGame.getInstance().setHealth(helperGame.getHealth());
		

		loaderListener.startGameFrame();

		List<HelperObject> objectsOnPanel = new ArrayList<>();
		objectsOnPanel.addAll(helperGame.getMoleculesOnPanel());
		objectsOnPanel.addAll(helperGame.getPowerupsOnPanel());
		objectsOnPanel.addAll(helperGame.getReactionBlockersOnPanel());

		initObjectsOnPanel(objectsOnPanel);

	}

	public List<Atom> initAtomList(List<HelperAtom> helperAtomList) {
		List<Atom> atomList = new ArrayList<Atom>();

		for (HelperAtom ha : helperAtomList) {
			Atom newAtom = AtomFactory.getInstance().createObject(ha.getType());
			initShields(newAtom, ha.getShieldMap());
			atomList.add(newAtom);
		}
		return atomList;
	}

	public void initShields(Atom atom, Map<ObjectType, Integer> storedShieldMap) {
		for (ObjectType type : storedShieldMap.keySet()) {
			for (int i = 0; i < storedShieldMap.get(type); i++) {
				addShield(atom, type);
			}
		}
	}

	private void addShield(Atom atom, ObjectType shieldType) {
		if (shieldType.equals(ObjectType.ETA)) {
			atom = new EtaShield(atom);
		} else if (shieldType.equals(ObjectType.THETA)) {
			atom = new ThetaShield(atom);
		} else if (shieldType.equals(ObjectType.ZETA)) {
			atom = new ZetaShield(atom);
		} else if (shieldType.equals(ObjectType.LOTA)) {
			atom = new LotaShield(atom);
		}

	}

	private void initObjectsOnPanel(List<HelperObject> kuvidObjectsOnPanel) {
		for (HelperObject ho : kuvidObjectsOnPanel) {
			if (ho instanceof HelperMolecule) {
				Molecule mol = MoleculeFactory.getInstance().createObject(ho.getType());
				mol.setMoleculeCenter(new Point(ho.getCenterPosition().getX(), ho.getCenterPosition().getY()));
			} else if (ho instanceof HelperPowerup) {
				Powerup pw = PowerupFactory.getInstance().createObject(ho.getType());
				pw.setPowerupCenter(new Point(ho.getCenterPosition().getX(), ho.getCenterPosition().getY()));
			} else if (ho instanceof HelperReactionBlocker) {
				Rb rb = ReactionBlockerFactory.getInstance().createObject(ho.getType());
				rb.setRbCenter(new Point(ho.getCenterPosition().getX(), ho.getCenterPosition().getY()));
			}

		}
	}

}
