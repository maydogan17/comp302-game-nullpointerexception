package ui.frame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.Timer;

import domain.KuvidSystem;
import domain.inventory.FallingObjectInventory;
import domain.kuvidgame.KuVidGame;
import domain.kuvidgame.NotEnoughMoleculesException;
import domain.kuvidgame.NotEnoughPowerupsException;
import domain.kuvidgame.NotEnoughReactionBlockerException;
import domain.objects.KuvidObject;
import domain.saveload.SaverListener;
import ui.FactoryInUI;
import ui.inaction.MoleculeInAction;
import ui.inaction.ObjectInAction;
import ui.inaction.PowerupInAction;
import ui.inaction.ReactionBlockerInAction;
import ui.inaction.ShootableInAction;
import ui.inaction.ShooterInAction;

@SuppressWarnings("serial")
public class SkyPanel extends JPanel implements KeyListener, ActionListener, SaverListener {

	protected static final int PANEL_WIDTH = 800;
	protected static final int PANEL_HEIGHT = 800;

	private ShooterInAction sh;
	private KuvidSystem kuvidSystem = new KuvidSystem();

	private List<MoleculeInAction> moleculeInActionsInPanel = new ArrayList<>();
	private List<PowerupInAction> powerupInActionsInPanel = new ArrayList<>();
	private List<ReactionBlockerInAction> rbInActionsInPanel = new ArrayList<>();
	// this list is created to check if atom hits to a molecule

	private List<ObjectInAction> objectInActionsOnPanel = new ArrayList<>();
	private List<ObjectInAction> toBeRemovedObjects = new ArrayList<>();

	// private AtomInAction atomInActionInBullet;
	private ShootableInAction shootableInActionInBullet;

	private int secondPerMolecule = KuVidGame.getSecondPerMolecule(); // easy as default
	private int secondPerPowerup = KuVidGame.getSecondPerPowerup(); // easy as default

	private int secondPerRb = KuVidGame.getSecondPerRb(); // easy as default

	//// sounds
	private AudioClip hitClip;
	private AudioClip changeClip;
	private AudioClip bilalClip;
	private AudioClip uwuClip;
	private AudioClip requiemClip;
	private AudioClip deepClip;
	private AudioClip deliClip;

	private String songToBePlayed;

	private double timePassed = 0;

	Timer timer = new Timer(25, this);

	public SkyPanel() {

		FactoryInUI.getInstance().setSkyPanel(this);

		this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		this.addKeyListener(this);

		//// sound
		initializeSounds();
//		bilalClip.loop();
		playSelectedSong(KuVidGame.getInstance().getSongName());

		sh = new ShooterInAction(this);
		timer.start();

	}

	public SkyPanel(boolean timerStart) {
		FactoryInUI.getInstance().setSkyPanel(this);

		this.setPreferredSize(new Dimension(PANEL_WIDTH, PANEL_HEIGHT));
		this.addKeyListener(this);

		if (timerStart)
			timer.start();
	}

	public void addObjectInAction(ObjectInAction obj) {
		if (obj instanceof MoleculeInAction) {
			moleculeInActionsInPanel.add((MoleculeInAction) obj);
		} else if (obj instanceof PowerupInAction) {
			powerupInActionsInPanel.add((PowerupInAction) obj);
		} else if (obj instanceof ReactionBlockerInAction) {
			rbInActionsInPanel.add((ReactionBlockerInAction) obj);
		}
		objectInActionsOnPanel.add(obj);
	}

	public void removeObjectInAction(ObjectInAction obj) {
		toBeRemovedObjects.add(obj);
	}

	public void paintComponent(Graphics g) {

		super.paintComponent(g);
		Graphics2D g2D = (Graphics2D) g;

		for (ObjectInAction a : getObjectInActionsOnPanel()) { // drawing atoms on panel
			a.draw(g2D);
		}

		sh.draw(g2D);

	}

	public void startTimer() {
		timer.start();
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();

		if (key == 80) {
			stopAllSongs();
			timer.stop();
			KuVidGame.getInstance().getClock().stop();
			new PauseScreen(this);
		}
		if (key == 82) {
			timer.start();
			KuVidGame.getInstance().getClock().start();
		}

		if (timer.isRunning()) {
			switch (key) {
			case 37:
				kuvidSystem.moveShooterLeft();
				break;
			case 39:
				kuvidSystem.moveShooterRight();
				break;
			case 65:
				kuvidSystem.rotateShooterLeft();
				break;
			case 68:
				kuvidSystem.rotateShooterRight();
				break;
			case 67:
				changeClip.play();
				kuvidSystem.pickRandomAtom();
				break;
			case 66:
				new BlenderFrame();
				break;
			case 38:
				hitClip.play();
				kuvidSystem.shoot();
				break;
			}
		}

	}

	public void initializeSounds() {
		try {
			hitClip = Applet.newAudioClip(new URL("file:sounds/hit.wav"));
			changeClip = Applet.newAudioClip(new URL("file:sounds/change.wav"));
			bilalClip = Applet.newAudioClip(new URL("file:sounds/bilal.wav"));
			uwuClip = Applet.newAudioClip(new URL("file:sounds/uwu.wav"));
			requiemClip = Applet.newAudioClip(new URL("file:sounds/requiem.wav"));
			deepClip = Applet.newAudioClip(new URL("file:sounds/deep.wav"));
			deliClip = Applet.newAudioClip(new URL("file:sounds/delivah.wav"));
		} catch (MalformedURLException murle) {
			System.out.println(murle);
		}
	}

	public void playSelectedSong(String song) {
		if (song.equals("Bilal")) {
			bilalClip.loop();
		} else if (song.equals("None")) {

		} else if (song.equals("Requiem")) {
			requiemClip.loop();
		} else if (song.equals("Deep Turkish")) {
			deepClip.loop();
		} else if (song.equals("Deli Vahit")) {
			deliClip.loop();
		} else {
			uwuClip.loop();
		}
	}

	public void stopAllSongs() {
		bilalClip.stop();
		requiemClip.stop();
		deepClip.stop();
		uwuClip.stop();
		deepClip.stop();
		deliClip.stop();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// drawing atoms on the panel
		// drawing molecules on the panel
		FallingObjectInventory foi = KuVidGame.getInstance().getFallInv();
		this.setTimePassed(getTimePassed() + 25);
		if (getTimePassed() % secondPerMolecule == 0) { // set to the easy mode as default
			try {
				foi.sendMolecule();
			} catch (NotEnoughMoleculesException ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}

		}
		if (getTimePassed() % secondPerPowerup == 0) {
			try {
				foi.sendPowerups();
			} catch (NotEnoughPowerupsException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		if (getTimePassed() % secondPerRb == 0) {
			try {
				foi.sendReactionBlockers();
			} catch (NotEnoughReactionBlockerException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

		for (ObjectInAction m : objectInActionsOnPanel) {
			m.getObject().doAction();
		}

		objectInActionsOnPanel.removeAll(toBeRemovedObjects);

		if (KuVidGame.getInstance().endGame()) {
			stopAllSongs();
			new EndGameScreen();
			timer.stop();
			KuVidGame.getInstance().getClock().stop();
		}

		repaint();
	}

	public double getTimePassed() {
		return timePassed;
	}

	public void setTimePassed(double timePassed) {
		this.timePassed = timePassed;
	}

	public List<ObjectInAction> getObjectInActionsOnPanel() {
		return objectInActionsOnPanel;
	}

	public void setObjectInActionsOnPanel(List<ObjectInAction> objectsOnPanel) {
		this.objectInActionsOnPanel = objectsOnPanel;
	}

	public List<ObjectInAction> getToBeRemovedObjects() {
		return toBeRemovedObjects;
	}

	public void setToBeRemovedObjects(List<ObjectInAction> toBeRemovedObjects) {
		this.toBeRemovedObjects = toBeRemovedObjects;
	}

	public List<MoleculeInAction> getMoleculeInActionsInPanel() {
		return moleculeInActionsInPanel;
	}

	public void setMoleculeInActionsInPanel(List<MoleculeInAction> moleculeInActionsInPanel) {
		this.moleculeInActionsInPanel = moleculeInActionsInPanel;
	}

	public ShootableInAction getShootableInActionInBullet() {
		return shootableInActionInBullet;
	}

	public void setShootableInActionInBullet(ShootableInAction shootableInActionInBullet) {
		this.shootableInActionInBullet = shootableInActionInBullet;
	}

	public List<PowerupInAction> getPowerupInActionsInPanel() {
		return powerupInActionsInPanel;
	}

	public void setPowerupInActionsInPanel(List<PowerupInAction> powerupInActionsInPanel) {
		this.powerupInActionsInPanel = powerupInActionsInPanel;
	}

	public List<ReactionBlockerInAction> getRbInActionsInPanel() {
		return rbInActionsInPanel;
	}

	public void setRbInActionsInPanel(List<ReactionBlockerInAction> rbInActionsInPanel) {
		this.rbInActionsInPanel = rbInActionsInPanel;
	}

	@Override
	public List<KuvidObject> publishObjectsInPanel() {
		
		List<KuvidObject> objectsOnPanel = new ArrayList<>();
		for(ObjectInAction obj: getObjectInActionsOnPanel()) {
			objectsOnPanel.add(obj.getObject());
		}
		return objectsOnPanel;
	}

	public String getSongToBePlayed() {
		return songToBePlayed;
	}

	public void setSongToBePlayed(String songToBePlayed) {
		this.songToBePlayed = songToBePlayed;
	}

}
