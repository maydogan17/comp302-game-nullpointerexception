package domain.saveload;

import java.util.ArrayList;
import java.util.List;

import domain.inventory.FallingObjectInventory;
import domain.inventory.Inventory;
import domain.kuvidgame.KuVidGame;
import domain.objects.KuvidObject;
import domain.objects.atoms.Atom;
import domain.objects.molecule.Molecule;
import domain.objects.powerups.Powerup;
import domain.objects.reactionblocker.Rb;
import domain.saveload.helperobjects.HelperAtom;
import domain.saveload.helperobjects.HelperGame;
import domain.saveload.helperobjects.HelperMolecule;
import domain.saveload.helperobjects.HelperPoint;
import domain.saveload.helperobjects.HelperPowerup;
import domain.saveload.helperobjects.HelperReactionBlocker;

public class GameSaveHelper {
	
	
	private HelperGame helperGame;
	
	
	public GameSaveHelper(KuVidGame kuvidGame, SaverListener saverListener, Inventory inv, FallingObjectInventory fallInv) {
		helperGame = new HelperGame();
		initKuvidGameValues(kuvidGame);
		initKuvidObjectsOnPanel(saverListener);
		initStoredObjectsInInventory(inv);
		initRemainingObjectsInFallingInventory(fallInv);
		
	}
	
	public void initKuvidGameValues(KuVidGame kuvidGame) {
		System.out.println(kuvidGame.getHealth());
		helperGame.setL(KuVidGame.getL());
		helperGame.setUsername(kuvidGame.getUserName());
		helperGame.setScore(kuvidGame.getScore());
		helperGame.setHealth(kuvidGame.getHealth());
		helperGame.setDifficulty(kuvidGame.getDifficulty());
		helperGame.setRemainingSeconds(kuvidGame.getRemainingSeconds());
		helperGame.setLinearSpinning(kuvidGame.isLinearSpinning());
		helperGame.setAlphaMolStruct(kuvidGame.getAlphaMolStruct());
		helperGame.setBetaMolStruct(kuvidGame.getBetaMolStruct());
		helperGame.setWindowHeight(kuvidGame.getWindowHeight());
		helperGame.setWindowWidth(kuvidGame.getWindowWidth());
		helperGame.setSongName(kuvidGame.getSongName());
		helperGame.setRemainingMolNumber(KuVidGame.getMolNumber());
		helperGame.setRemainingPowNumber(KuVidGame.getPowNumber());
		helperGame.setRemainingRbNumber(KuVidGame.getRbNumber());

	}
	
	public void initKuvidObjectsOnPanel(SaverListener saverListener) {
		List<KuvidObject> kuvidObjectsOnPanel = new ArrayList<KuvidObject>();
		List<HelperMolecule> moleculesOnPanel = new ArrayList<HelperMolecule>();
		List<HelperPowerup> powerupsOnPanel = new ArrayList<HelperPowerup>();
		List<HelperReactionBlocker> reactionBlockersOnPanel = new ArrayList<HelperReactionBlocker>();

		kuvidObjectsOnPanel.addAll(saverListener.publishObjectsInPanel());
		
		
		for(KuvidObject obj : kuvidObjectsOnPanel) {
			
			if(obj instanceof Molecule) {
				HelperMolecule hmol = new HelperMolecule();
				hmol.setType(obj.getObjDescr().getType());
				hmol.setCenterPosition(new HelperPoint(obj.getObjectHitbox().getCenter()));
				moleculesOnPanel.add(hmol);
			}else if(obj instanceof Powerup) {
				Powerup pw = (Powerup) obj;
				
				if(pw.isFalling()) {
					HelperPowerup hpw = new HelperPowerup();
					hpw.setType(obj.getObjDescr().getType());
					hpw.setCenterPosition(new HelperPoint(obj.getObjectHitbox().getCenter()));
					powerupsOnPanel.add(hpw);
				}
			}else if(obj instanceof Rb) {
				HelperReactionBlocker hrb = new HelperReactionBlocker();
				hrb.setType(obj.getObjDescr().getType());
				hrb.setCenterPosition(new HelperPoint(obj.getObjectHitbox().getCenter()));
				reactionBlockersOnPanel.add(hrb);
			}

		}
		
		helperGame.setMoleculesOnPanel(moleculesOnPanel);
		helperGame.setPowerupsOnPanel(powerupsOnPanel);
		helperGame.setReactionBlockersOnPanel(reactionBlockersOnPanel);
		
	}
	
	public void initStoredObjectsInInventory(Inventory inv) {
		helperGame.setStoredAtomsMap(inv.getStoredAtomsMap());
		helperGame.setStoredPWMap(inv.getStoredPWMap());
		helperGame.setStoredShieldMap(inv.getStoredShieldMap());
		
		List<HelperAtom> helperAtomList = new ArrayList<>();
		for(Atom atom : inv.getAtomList()) {
			HelperAtom ha = new HelperAtom();
			ha.setType(atom.getAtomDescr().getType());
			ha.setShieldMap(atom.getShieldMap());
			helperAtomList.add(ha);
			
		}
		helperGame.setAtomList(helperAtomList);
		
		
		
	}
	
	public void initRemainingObjectsInFallingInventory(FallingObjectInventory fallInv) {
		helperGame.setRemainingMolMap(fallInv.getStoredMolMap());
		helperGame.setRemainingPowMap(fallInv.getStoredPowMap()); 
		helperGame.setRemainingRbMap(fallInv.getStoredRbMap());
	}
	
	public HelperGame gameToHelperGame() {
		
		return helperGame;
	}

}
