package domain.kuvidgame;

import java.text.DecimalFormat;

import domain.catalog.Catalog;
import domain.hitbox.Dimension;
import domain.hitbox.Point;
import domain.inventory.Blender;
import domain.inventory.FallingObjectInventory;
import domain.inventory.Inventory;
import domain.objects.molecule.MoleculeStruct;
import domain.shooter.Shooter;

public class KuVidGame {

	private static KuVidGame instance;
	private static int windowWidth = 800; // default
	private static int windowHeight = 800; // default
	private MoleculeStruct AlphaMolStruct;
	private MoleculeStruct BetaMolStruct;
	private boolean isLinearSpinning;
	private static int L;
	private static DifficultyLevels difficulty;
	private int fallRate;
	private Clock clock;
	private Inventory inv;
	private Catalog catalog;
	private Shooter shooter;
	private FallingObjectInventory fallInv;
	private double score;
	private static int secondPerMolecule;
	private static int secondPerPowerup;
	private static int secondPerRb;
	private static int molNumber = 100;
	private static int powNumber = 20;
	private String userName;
	private static int rbNumber = 10;
	private static int remainingSeconds;
	private String songName;

	private KuVidGame() {

	}

	public void createClock() {

		int minute = (int) (remainingSeconds) / 60;
		int second = (int) (remainingSeconds) % 60;
		clock = new Clock(minute, second);
	}

	public static void setValuesAccordingToDifficulty(DifficultyLevels diff) {
		difficulty = diff;
		secondPerMolecule = diff.secondPerMolecule;
		int molAmount = molNumber;
		double totalSecond = (molAmount * secondPerMolecule / 1000);

		secondPerPowerup = (int) ((totalSecond / getPowNumber()) * 1000);
		secondPerRb = (int) ((totalSecond / getRbNumber()) * 1000);
		secondPerPowerup = (int) (25 * (Math.ceil((double) secondPerPowerup / 25)));
		secondPerRb = (int) (25 * (Math.ceil((double) secondPerRb / 25)));

		remainingSeconds = (int) (5 + totalSecond + (windowHeight / L));
	}

	public static void setValuesAccordingToDifficultyAndRemainingSeconds(int remainingSecs, DifficultyLevels diff) {
		difficulty = diff;

		secondPerMolecule = (int) ((remainingSecs / getMolNumber()) * 1000);
		secondPerMolecule = (int) (25 * (Math.ceil((double) secondPerMolecule / 25)));
		secondPerPowerup = (int) ((remainingSecs / getPowNumber()) * 1000);
		secondPerRb = (int) ((remainingSecs / getRbNumber()) * 1000);
		secondPerPowerup = (int) (25 * (Math.ceil((double) secondPerPowerup / 25)));
		secondPerRb = (int) (25 * (Math.ceil((double) secondPerRb / 25)));

		remainingSeconds = remainingSecs;
	}

	public static KuVidGame getInstance() {
		if (instance == null) {
			instance = new KuVidGame();
			instance.createInv(0, 0);
			instance.createShooter();
			instance.createCatalog();
			instance.createClock();
		}
		return instance;
	}

	public static void reset() {
		// instance = new KuVidGame();
		instance = null;

	}

	public int getRemainingSeconds() {
		return getClock().getRemainingSeconds();
	}

	public void putBulletToAtom() {
		instance.getShooter().getBullet().getRandomAtomFromInv();
	}

	public void createShooter() {
		this.setShooter(
				new Shooter(5, 10, new Dimension(getL() / 4, getL()), new Point(400, 800 - getL() / 2), 100, getL()));
		this.getShooter().createBullet(getL());
	}

	public void createInv(int atom, int shield) {
		inv = new Inventory(atom, shield);
	}

	public void createCatalog() {
		catalog = new Catalog();
	}

	public void createFallingObjectsInventory(int mol, int pow, int rb) {
		fallInv = new FallingObjectInventory(mol, pow, rb);
	}

	public boolean endGame() {
		if (getClock().isZero()) {
			return true;
		}
		if (getShooter().getHealth() <= 0) {
			return true;
		}
		return false;
	}

	public void addscore(double score) {
		setScore(this.score + score);
	}

	public String scoreToBePrinted() {

		DecimalFormat df = new DecimalFormat("#.##");
		return df.format(getScore());

	}

	public void createBlender() {
		this.getInv().setBlender(new Blender());
	}

	public boolean isLinearSpinning() {
		return isLinearSpinning;
	}

	public void setLinearSpinning(boolean isLinearSpinning) {
		this.isLinearSpinning = isLinearSpinning;
	}

	public int getHealth() {
		return getShooter().getHealth();

	}

	public void setHealth(int health) {
		getShooter().setHealth(health);

	}

	public static int getL() {
		return L;
	}

	public static void setL(int l) {
		L = l;
	}

	public DifficultyLevels getDifficulty() {
		return difficulty;
	}

	@SuppressWarnings("static-access")
	public void setDifficulty(DifficultyLevels difficulty) {
		this.difficulty = difficulty;
	}

	public int getFallRate() {
		return fallRate;
	}

	public void setFallRate(int fallRate) {
		this.fallRate = fallRate;
	}

	public Clock getClock() {
		return clock;
	}

	public void setClock(Clock clock) {
		this.clock = clock;
	}

	public Inventory getInv() {
		return inv;
	}

	public void setInv(Inventory inv) {
		this.inv = inv;
	}

	public Catalog getCatalog() {
		return catalog;
	}

	public void setCatalog(Catalog catalog) {
		this.catalog = catalog;
	}

	public static int getSecondPerMolecule() {
		return secondPerMolecule;
	}

	public static void setSecondPerMolecule(int secondPerMolecule) {
		KuVidGame.secondPerMolecule = secondPerMolecule;
	}

	public static int getSecondPerPowerup() {
		return secondPerPowerup;
	}

	public static void setSecondPerPowerup(int secondPerPowerup) {
		KuVidGame.secondPerPowerup = secondPerPowerup;
	}

	public static int getSecondPerRb() {
		return secondPerRb;
	}

	public static void setSecondPerRb(int secondPerRb) {
		KuVidGame.secondPerRb = secondPerRb;
	}

	public Shooter getShooter() {
		return shooter;
	}

	public static int getMolNumber() {
		return molNumber;
	}

	public static void setMolNumber(int mol) {
		molNumber = mol;
	}

	public static int getPowNumber() {
		return powNumber;
	}

	public static void setPowNumber(int powNumber) {
		KuVidGame.powNumber = powNumber;
	}

	public static int getRbNumber() {
		return rbNumber;
	}

	public static void setRbNumber(int rbNumber) {
		KuVidGame.rbNumber = rbNumber;
	}

	public void setShooter(Shooter shooter) {
		this.shooter = shooter;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public FallingObjectInventory getFallInv() {
		return fallInv;
	}

	public void setFallInv(FallingObjectInventory fallInv) {
		this.fallInv = fallInv;
	}

	public int getWindowWidth() {
		return windowWidth;
	}

	@SuppressWarnings("static-access")
	public void setWindowWidth(int windowWidth) {
		this.windowWidth = windowWidth;
	}

	public int getWindowHeight() {
		return windowHeight;
	}

	@SuppressWarnings("static-access")
	public void setWindowHeight(int windowHeight) {
		this.windowHeight = windowHeight;
	}

	public MoleculeStruct getAlphaMolStruct() {
		return AlphaMolStruct;
	}

	public void setAlphaMolStruct(MoleculeStruct alphaMolStruct) {
		AlphaMolStruct = alphaMolStruct;
	}

	public MoleculeStruct getBetaMolStruct() {
		return BetaMolStruct;
	}

	public void setBetaMolStruct(MoleculeStruct betaMolStruct) {
		BetaMolStruct = betaMolStruct;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getSongName() {
		return songName;
	}

	public void setSongName(String songName) {
		this.songName = songName;
	}

}
