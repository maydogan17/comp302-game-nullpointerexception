package ui.frame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import domain.KuvidSystem;
import domain.factories.ObjectType;
import domain.inventory.Inventory;
import domain.kuvidgame.KuVidGame;

@SuppressWarnings("serial")
public class KuVidGameFrame extends JFrame implements ActionListener {

	private static int FRAME_WIDTH = 1200; //
	private static int FRAME_HEIGHT = 830;// top bar adds 20 in mac 40 in windows
	private KuvidSystem kuvidSystem = new KuvidSystem();

	public static int getFrameHeight() {
		return FRAME_HEIGHT;
	}

	public static int getFrameWidth() {
		return FRAME_WIDTH;
	}

	public static int getSkyPanelWidth() {
		return FRAME_WIDTH - 400;
	}

	public static int getSkyPanelHeight() {
		return FRAME_HEIGHT - 30;
	}

	ImageIcon kuvidIcon = new ImageIcon("images/kuvid.png");
	ImageIcon shooterIcon = new ImageIcon("images/shooter.png");
	ImageIcon heartIcon = new ImageIcon("images/heart.png");
	ImageIcon clockIcon = new ImageIcon("images/clock.png");
	ImageIcon blenderIcon = new ImageIcon("images/blender.png");

	ImageIcon alphaPwIcon = new ImageIcon("images/alphaPw.png");
	ImageIcon betaPwIcon = new ImageIcon("images/betaPw.png");
	ImageIcon sigmaPwIcon = new ImageIcon("images/sigmaPw.png");
	ImageIcon gammaPwIcon = new ImageIcon("images/gammaPw.png");

	ImageIcon etaShieldIcon = new ImageIcon("images/eta.png");
	ImageIcon lotaShieldIcon = new ImageIcon("images/lota.png");
	ImageIcon thetaShieldIcon = new ImageIcon("images/theta.png");
	ImageIcon zetaShieldIcon = new ImageIcon("images/zeta.png");

	ImageIcon alphaAtomIcon = new ImageIcon("images/alphaAtom.png");
	ImageIcon betaAtomIcon = new ImageIcon("images/betaAtom.png");
	ImageIcon sigmaAtomIcon = new ImageIcon("images/sigmaAtom.png");
	ImageIcon gammaAtomIcon = new ImageIcon("images/gammaAtom.png");

	private JPanel informationPanel;

	private JPanel statics;
	private JPanel powerups;
	private JPanel atoms;

	private JLabel score;
	private JLabel time;
	private JLabel heart;

	private JLabel alphaPw;
	private JLabel betaPw;
	private JLabel sigmaPw;
	private JLabel gammaPw;

	private JLabel etaShield;
	private JLabel lotaShield;
	private JLabel thetaShield;
	private JLabel zetaShield;

	private JLabel playerName;
	private JLabel atomEfficiency;
	private JLabel etaShieldStats;
	private JLabel lotaShieldStats;
	private JLabel thetaShieldStats;
	private JLabel zetaShieldStats;

	private JLabel alphaAtom;
	private JLabel betaAtom;
	private JLabel sigmaAtom;
	private JLabel gammaAtom;

	private JButton alphaPwButton;
	private JButton betaPwButton;
	private JButton sigmaPwButton;
	private JButton gammaPwButton;
	private JButton etaButton;
	private JButton lotaButton;
	private JButton thetaButton;
	private JButton zetaButton;

	private JLabel blender;

	private SkyPanel sky;

	Timer timer = new Timer(25, this);
	
	private AudioClip clickClip;

	public KuVidGameFrame() {

		this.setSize(FRAME_WIDTH, FRAME_HEIGHT);
		this.setTitle("KuVid Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setIconImage(kuvidIcon.getImage());
		// this.setLayout(new BorderLayout());
		setLayout(null);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); // for popping in the center of the screen
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		// for popping in the center ot the screen
		
		
		//////sounds
		initializeSounds();

		sky = new SkyPanel();
		sky.setBackground(new Color(171, 222, 230));
		this.addKeyListener(sky);
		this.add(sky);
		sky.setBounds(0, 0, SkyPanel.PANEL_WIDTH, SkyPanel.PANEL_HEIGHT);

		initStaticsPanel();
		initPowerupPanel();
		// initShieldPanel();
		initAtomsPanel();
		initInformationPanel();

		this.add(informationPanel);
		informationPanel.setBounds(800, 0, 400, 800);
		this.setVisible(true);

		timer.start();

	}
	
	public void initializeSounds() {
		try {
			clickClip = Applet.newAudioClip(
			new URL("file:sounds/buttonClick.wav"));
			} catch (MalformedURLException murle) {
			System.out.println(murle);
			}
	}

	private void initInformationPanel() {
		// Information Panel is the panel at the right side to give inventory and other
		// information.

		informationPanel = new JPanel();
		informationPanel.setLayout(new GridLayout(3, 0));
		informationPanel.setBackground(Color.black);
		informationPanel.setPreferredSize(new Dimension(150, 100));

		informationPanel.add(statics);
		informationPanel.add(powerups);
		// informationPanel.add(shield);
		informationPanel.add(atoms);

	}

	private void initStaticsPanel() {
		// Panel showing score, health left and time left

		statics = new JPanel();
		statics.setLayout(new GridLayout(5, 2));
		statics.setBackground(new Color(204, 226, 203));
		statics.setBorder(BorderFactory.createLineBorder(Color.black));

		score = new JLabel("Score: ");
		score.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		time = new JLabel();
		time.setIcon(clockIcon);
		time.setText("09:55");
		time.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		heart = new JLabel();
		heart.setIcon(heartIcon);
		heart.setText(Integer.toString(KuVidGame.getInstance().getShooter().getHealth()));
		heart.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		playerName = new JLabel("");

		atomEfficiency = new JLabel("Atom Efficiency: ");

		etaShieldStats = new JLabel();
		etaShieldStats.setIcon(etaShieldIcon);
		etaShieldStats.setText(Integer.toString(0));
		etaShieldStats.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		lotaShieldStats = new JLabel();
		lotaShieldStats.setIcon(lotaShieldIcon);
		lotaShieldStats.setText(Integer.toString(0));
		lotaShieldStats.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		thetaShieldStats = new JLabel();
		thetaShieldStats.setIcon(thetaShieldIcon);
		thetaShieldStats.setText(Integer.toString(0));
		thetaShieldStats.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		zetaShieldStats = new JLabel();
		zetaShieldStats.setIcon(zetaShieldIcon);
		zetaShieldStats.setText(Integer.toString(0));
		zetaShieldStats.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		statics.add(score);
		statics.add(time);
		statics.add(heart);
		statics.add(playerName);
		statics.add(etaShieldStats);
		statics.add(lotaShieldStats);
		statics.add(thetaShieldStats);
		statics.add(zetaShieldStats);
		statics.add(atomEfficiency);
	}

	private void initPowerupPanel() {
		// Panel showing powerup amounts

		powerups = new JPanel();
		powerups.setLayout(new GridLayout(4, 4));
		powerups.setBackground(new Color(203, 170, 203));
		powerups.setBorder(BorderFactory.createLineBorder(Color.black));

		alphaPw = new JLabel();
		alphaPw.setIcon(alphaPwIcon);
		alphaPw.setText(Integer.toString(KuVidGame.getInstance().getInv().getStoredPWMap().get(ObjectType.ALPHA_POW)));
		alphaPw.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		alphaPwButton = new JButton("alpha");
		alphaPwButton.setBackground(new Color(255, 154, 162));
		alphaPwButton.setSize(50, 50);
		alphaPwButton.setFocusable(false);
		alphaPwButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				clickClip.play();
				kuvidSystem.loadPowerupToBullet(ObjectType.ALPHA_POW);
			}
		});

		betaPw = new JLabel();
		betaPw.setIcon(betaPwIcon);
		betaPw.setText(Integer.toString(KuVidGame.getInstance().getInv().getStoredPWMap().get(ObjectType.BETA_POW)));
		betaPw.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		betaPwButton = new JButton("beta");
		betaPwButton.setBackground(new Color(255, 183, 178));
		betaPwButton.setSize(50, 50);
		betaPwButton.setFocusable(false);
		betaPwButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				clickClip.play();
				kuvidSystem.loadPowerupToBullet(ObjectType.BETA_POW);
			}
		});

		sigmaPw = new JLabel();
		sigmaPw.setIcon(sigmaPwIcon);
		sigmaPw.setText(Integer.toString(KuVidGame.getInstance().getInv().getStoredPWMap().get(ObjectType.SIGMA_POW)));
		sigmaPw.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		sigmaPwButton = new JButton("sigma");
		sigmaPwButton.setBackground(new Color(255, 218, 193));
		sigmaPwButton.setSize(50, 50);
		sigmaPwButton.setFocusable(false);
		sigmaPwButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				clickClip.play();
				kuvidSystem.loadPowerupToBullet(ObjectType.SIGMA_POW);
			}
		});

		gammaPw = new JLabel();
		gammaPw.setIcon(gammaPwIcon);
		gammaPw.setText(Integer.toString(KuVidGame.getInstance().getInv().getStoredPWMap().get(ObjectType.GAMMA_POW)));
		gammaPw.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		gammaPwButton = new JButton("gamma");
		gammaPwButton.setBackground(new Color(226, 240, 203));
		gammaPwButton.setSize(50, 50);
		gammaPwButton.setFocusable(false);
		gammaPwButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				clickClip.play();
				kuvidSystem.loadPowerupToBullet(ObjectType.GAMMA_POW);
			}
		});

		powerups.add(alphaPw);
		powerups.add(alphaPwButton);
		powerups.add(betaPw);
		powerups.add(betaPwButton);
		powerups.add(sigmaPw);
		powerups.add(sigmaPwButton);
		powerups.add(gammaPw);
		powerups.add(gammaPwButton);

		etaShield = new JLabel();
		etaShield.setIcon(etaShieldIcon);
		etaShield.setText(Integer.toString(KuVidGame.getInstance().getInv().getStoredShieldMap().get(ObjectType.ETA)));
		etaShield.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		etaButton = new JButton("eta");
		etaButton.setBackground(new Color(181, 234, 215));
		etaButton.setSize(50, 50);
		etaButton.setFocusable(false);
		etaButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				clickClip.play();
				kuvidSystem.addShield(ObjectType.ETA);
			}
		});

		lotaShield = new JLabel();
		lotaShield.setIcon(lotaShieldIcon);
		lotaShield
				.setText(Integer.toString(KuVidGame.getInstance().getInv().getStoredShieldMap().get(ObjectType.LOTA)));
		lotaShield.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		lotaButton = new JButton("lota");
		lotaButton.setBackground(new Color(199, 206, 234));
		lotaButton.setSize(50, 50);
		lotaButton.setFocusable(false);
		lotaButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				clickClip.play();
				kuvidSystem.addShield(ObjectType.LOTA);
			}
		});

		thetaShield = new JLabel();
		thetaShield.setIcon(thetaShieldIcon);
		thetaShield
				.setText(Integer.toString(KuVidGame.getInstance().getInv().getStoredShieldMap().get(ObjectType.THETA)));
		thetaShield.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		thetaButton = new JButton("theta");
		thetaButton.setBackground(new Color(216, 183, 162));
		thetaButton.setSize(50, 50);
		thetaButton.setFocusable(false);
		thetaButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				clickClip.play();
				kuvidSystem.addShield(ObjectType.THETA);
			}
		});

		zetaShield = new JLabel();
		zetaShield.setIcon(zetaShieldIcon);
		zetaShield
				.setText(Integer.toString(KuVidGame.getInstance().getInv().getStoredShieldMap().get(ObjectType.ZETA)));
		zetaShield.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
		zetaButton = new JButton("zeta");
		zetaButton.setBackground(new Color(191, 191, 191));
		zetaButton.setSize(50, 50);
		zetaButton.setFocusable(false);
		zetaButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				clickClip.play();
				kuvidSystem.addShield(ObjectType.ZETA);
			}
		});

		powerups.add(etaShield);
		powerups.add(etaButton);
		powerups.add(lotaShield);
		powerups.add(lotaButton);
		powerups.add(thetaShield);
		powerups.add(thetaButton);
		powerups.add(zetaShield);
		powerups.add(zetaButton);

	}

	private void initAtomsPanel() {
		// Panel showing atom amounts and blender

		atoms = new JPanel();
		atoms.setLayout(new BoxLayout(atoms, BoxLayout.PAGE_AXIS));
		atoms.setBackground(new Color(223, 199, 193));
		atoms.setBorder(BorderFactory.createLineBorder(Color.black));

		alphaAtom = new JLabel();
		alphaAtom.setIcon(alphaAtomIcon);
		alphaAtom.setText(Integer.toString(KuVidGame.getInstance().getInv().getStoredAtomsMap().get(ObjectType.ALPHA)));
		alphaAtom.setAlignmentX(Component.CENTER_ALIGNMENT);
		alphaAtom.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		betaAtom = new JLabel();
		betaAtom.setIcon(betaAtomIcon);
		betaAtom.setText(Integer.toString(KuVidGame.getInstance().getInv().getStoredAtomsMap().get(ObjectType.BETA)));
		betaAtom.setAlignmentX(Component.CENTER_ALIGNMENT);
		betaAtom.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		sigmaAtom = new JLabel();
		sigmaAtom.setIcon(sigmaAtomIcon);
		sigmaAtom.setText(Integer.toString(KuVidGame.getInstance().getInv().getStoredAtomsMap().get(ObjectType.SIGMA)));
		sigmaAtom.setAlignmentX(Component.CENTER_ALIGNMENT);
		sigmaAtom.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		gammaAtom = new JLabel();
		gammaAtom.setIcon(gammaAtomIcon);
		gammaAtom.setText(Integer.toString(KuVidGame.getInstance().getInv().getStoredAtomsMap().get(ObjectType.GAMMA)));
		gammaAtom.setAlignmentX(Component.CENTER_ALIGNMENT);
		gammaAtom.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		blender = new JLabel();
		blender.setAlignmentX(Component.CENTER_ALIGNMENT);
		blender.setIcon(blenderIcon);

		atoms.add(blender);
		atoms.add(alphaAtom);
		atoms.add(betaAtom);
		atoms.add(gammaAtom);
		atoms.add(sigmaAtom);
	}

	private void updateText() {
		Inventory inv = KuVidGame.getInstance().getInv();
		Map<ObjectType, Integer> shields = KuVidGame.getInstance().getShooter().getBullet().getStoredAtom().getShieldMap();
		alphaAtom.setText(Integer.toString(inv.getStoredAtomsMap().get(ObjectType.ALPHA)));
		betaAtom.setText(Integer.toString(inv.getStoredAtomsMap().get(ObjectType.BETA)));
		sigmaAtom.setText(Integer.toString(inv.getStoredAtomsMap().get(ObjectType.SIGMA)));
		gammaAtom.setText(Integer.toString(inv.getStoredAtomsMap().get(ObjectType.GAMMA)));

		etaShield.setText(Integer.toString(inv.getStoredShieldMap().get(ObjectType.ETA)));
		lotaShield.setText(Integer.toString(inv.getStoredShieldMap().get(ObjectType.LOTA)));
		thetaShield.setText(Integer.toString(inv.getStoredShieldMap().get(ObjectType.THETA)));
		zetaShield.setText(Integer.toString(inv.getStoredShieldMap().get(ObjectType.ZETA)));

		etaShieldStats.setText(Integer.toString(shields.get(ObjectType.ETA)));
		lotaShieldStats.setText(Integer.toString(shields.get(ObjectType.LOTA)));
		thetaShieldStats.setText(Integer.toString(shields.get(ObjectType.THETA)));
		zetaShieldStats.setText(Integer.toString(shields.get(ObjectType.ZETA)));

		alphaPw.setText(Integer.toString(inv.getStoredPWMap().get(ObjectType.ALPHA_POW)));
		betaPw.setText(Integer.toString(inv.getStoredPWMap().get(ObjectType.BETA_POW)));
		sigmaPw.setText(Integer.toString(inv.getStoredPWMap().get(ObjectType.SIGMA_POW)));
		gammaPw.setText(Integer.toString(inv.getStoredPWMap().get(ObjectType.GAMMA_POW)));

		atomEfficiency.setText("Atom Stability: " + KuVidGame.getInstance().getShooter().getBulletEfficiency());
		score.setText("Score: " + KuVidGame.getInstance().scoreToBePrinted());
		heart.setText("" + KuVidGame.getInstance().getShooter().getHealth());
		playerName.setText(KuVidGame.getInstance().getUserName());

	}

	public void reset() {
		dispose();
		timer.stop();
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		updateText();
		time.setText(KuVidGame.getInstance().getClock().getTime());
	}

	public SkyPanel getSky() {
		return sky;
	}

	public void setSky(SkyPanel sky) {
		this.sky = sky;
	}

}
