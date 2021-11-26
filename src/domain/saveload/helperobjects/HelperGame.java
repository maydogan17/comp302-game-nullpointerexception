package domain.saveload.helperobjects;

import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonProperty;

import domain.factories.ObjectType;
import domain.kuvidgame.DifficultyLevels;
import domain.objects.molecule.MoleculeStruct;

public class HelperGame {

	// KuvidGame Values //
	private int L;
	private String username;
	private double score;
	private int health;
	private DifficultyLevels difficulty;
	private int remainingSeconds;
	private String songName;

	private int remainingMolNumber;
	private int remainingPowNumber;
	private int remainingRbNumber;

	private boolean isLinearSpinning;
	private MoleculeStruct alphaMolStruct;
	private MoleculeStruct betaMolStruct;
	private int windowWidth;
	private int windowHeight;

	// Inventory Values //

	private Map<ObjectType, Integer> storedAtomsMap;
	private Map<ObjectType, Integer> storedPWMap;
	private Map<ObjectType, Integer> storedShieldMap;

	// Atoms instances are stored;
	private List<HelperAtom> atomList;

	// FallingObjectInventory //

	private Map<ObjectType, Integer> remainingMolMap;
	private Map<ObjectType, Integer> remainingPowMap;
	private Map<ObjectType, Integer> remainingRbMap;

	// SkyPanel Values //

	// Kuvid Objects that was on the panel
	private List<HelperMolecule> moleculesOnPanel;
	private List<HelperPowerup> powerupsOnPanel;
	private List<HelperReactionBlocker> reactionBlockersOnPanel;

	public HelperGame() {

	}

	public int getL() {
		return L;
	}

	public void setL(int l) {
		L = l;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getHealth() {
		return health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public DifficultyLevels getDifficulty() {
		return difficulty;
	}

	public void setDifficulty(DifficultyLevels difficulty) {
		this.difficulty = difficulty;
	}

	public int getRemainingSeconds() {
		return remainingSeconds;
	}

	public void setRemainingSeconds(int remainingSeconds) {
		this.remainingSeconds = remainingSeconds;
	}

	@JsonProperty(value = "isLinearSpinning")
	public boolean isLinearSpinning() {
		return isLinearSpinning;
	}

	public void setLinearSpinning(boolean isLinearSpinning) {
		this.isLinearSpinning = isLinearSpinning;
	}

	public MoleculeStruct getAlphaMolStruct() {
		return alphaMolStruct;
	}

	public void setAlphaMolStruct(MoleculeStruct alphaMolStruct) {
		this.alphaMolStruct = alphaMolStruct;
	}

	public MoleculeStruct getBetaMolStruct() {
		return betaMolStruct;
	}

	public void setBetaMolStruct(MoleculeStruct betaMolStruct) {
		this.betaMolStruct = betaMolStruct;
	}

	public int getWindowWidth() {
		return windowWidth;
	}

	public void setWindowWidth(int windowWidth) {
		this.windowWidth = windowWidth;
	}

	public int getWindowHeight() {
		return windowHeight;
	}

	public void setWindowHeight(int windowHeight) {
		this.windowHeight = windowHeight;
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

	public Map<ObjectType, Integer> getStoredShieldMap() {
		return storedShieldMap;
	}

	public void setStoredShieldMap(Map<ObjectType, Integer> storedShieldMap) {
		this.storedShieldMap = storedShieldMap;
	}

	public List<HelperAtom> getAtomList() {
		return atomList;
	}

	public void setAtomList(List<HelperAtom> atomList) {
		this.atomList = atomList;
	}

	public Map<ObjectType, Integer> getRemainingMolMap() {
		return remainingMolMap;
	}

	public void setRemainingMolMap(Map<ObjectType, Integer> remainingMolMap) {
		this.remainingMolMap = remainingMolMap;
	}

	public Map<ObjectType, Integer> getRemainingPowMap() {
		return remainingPowMap;
	}

	public void setRemainingPowMap(Map<ObjectType, Integer> remainingPowMap) {
		this.remainingPowMap = remainingPowMap;
	}

	public Map<ObjectType, Integer> getRemainingRbMap() {
		return remainingRbMap;
	}

	public void setRemainingRbMap(Map<ObjectType, Integer> remainingRbMap) {
		this.remainingRbMap = remainingRbMap;
	}

	public List<HelperMolecule> getMoleculesOnPanel() {
		return moleculesOnPanel;
	}

	public void setMoleculesOnPanel(List<HelperMolecule> moleculesOnPanel) {
		this.moleculesOnPanel = moleculesOnPanel;
	}

	public List<HelperPowerup> getPowerupsOnPanel() {
		return powerupsOnPanel;
	}

	public void setPowerupsOnPanel(List<HelperPowerup> powerupsOnPanel) {
		this.powerupsOnPanel = powerupsOnPanel;
	}

	public List<HelperReactionBlocker> getReactionBlockersOnPanel() {
		return reactionBlockersOnPanel;
	}

	public void setReactionBlockersOnPanel(List<HelperReactionBlocker> reactionBlockersOnPanel) {
		this.reactionBlockersOnPanel = reactionBlockersOnPanel;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

	public int getRemainingMolNumber() {
		return remainingMolNumber;
	}

	public void setRemainingMolNumber(int remainingMolNumber) {
		this.remainingMolNumber = remainingMolNumber;
	}

	public int getRemainingPowNumber() {
		return remainingPowNumber;
	}

	public void setRemainingPowNumber(int remainingPowNumber) {
		this.remainingPowNumber = remainingPowNumber;
	}

	public int getRemainingRbNumber() {
		return remainingRbNumber;
	}

	public void setRemainingRbNumber(int remainingRbNumber) {
		this.remainingRbNumber = remainingRbNumber;
	}

}
