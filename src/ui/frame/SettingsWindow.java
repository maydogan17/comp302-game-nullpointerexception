package ui.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.KuvidSystem;
import domain.kuvidgame.DifficultyLevels;
import domain.kuvidgame.KuVidGame;
import domain.objects.molecule.MoleculeStruct;
import ui.StartHelper;

@SuppressWarnings("serial")
public class SettingsWindow extends JFrame implements ActionListener {
	// Building mode ui

	private final Color bgColor = new Color(227, 253, 253);

	private JPanel nums;
	private JPanel molStructs;
	private JPanel difficulty;
	private JPanel song;
	private JPanel buttons;

	private JTextField atomNum;
	private JTextField molNum;
	private JTextField rbNum;
	private JTextField pwNum;
	private JTextField lUnit;
	private JTextField shieldNum;
	private JTextField userName;
	// initial values.
	private int atomAmount = 100;
	private int molAmount = 100;
	private int rbAmount = 10;
	private int pwAmount = 20;
	private int L = KuVidGameFrame.getSkyPanelHeight() / 10;
	private int shieldAmount = 8;
//	private String diff = "Easy";
	private String name = "Excalibur";
//	private String songName = "Bilal";

	private JCheckBox alphaLinear;
	private JCheckBox betaLinear;
	private JCheckBox spinning;

	private JComboBox<String> difficultyLevels;
	private JComboBox<String> songBox;

	private JButton Load;
	private JButton start;

	private SettingsWindow instance; // This is not for singleton implementation

	public SettingsWindow() {
		instance = this;
		this.setSize(new Dimension(500, 325));
		this.setTitle("KuVid Game Settings");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		this.setResizable(false);
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		this.setVisible(true);

		initDiffPanel();
		initAmountPanel();
		initButtons();
		initMolStructPanel();
		initSongPanel();

		
		this.add(difficulty);
		this.add(song);
		this.add(nums);
		this.add(molStructs);
		this.add(buttons);
		this.setVisible(true);

	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initSongPanel() {
		song = new JPanel();
		song.setBackground(bgColor);
		song.setLayout(new GridLayout(1, 1));
		
		String[] songs = {"None", "Bilal", "UwU", "Requiem", "Deli Vahit", "Deep Turkish" };
		songBox = new JComboBox(songs);
		
		JLabel songLabel = new JLabel("  Choose song: ");
		songBox.setBackground(bgColor);
		
		difficulty.add(songLabel);
		difficulty.add(songBox);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void initDiffPanel() {
		// Panel to choose difficulty levels

		difficulty = new JPanel();
		difficulty.setBackground(bgColor);
		difficulty.setLayout(new GridLayout(1, 1));
		// difficulty.setBounds(0,0,500,25);

		String[] difficulties = { "Easy", "Normal", "Hard" };
		difficultyLevels = new JComboBox(difficulties);

		JLabel diffLabel = new JLabel("  Choose difficulty: ");

		difficultyLevels.setBackground(bgColor);

		difficulty.add(diffLabel);
		difficulty.add(difficultyLevels);
	}

	public void initAmountPanel() {
		// Panel to specify amounts and numbers

		nums = new JPanel();
		nums.setBackground(bgColor);
		nums.setLayout(new GridLayout(7, 2));
		// nums.setBounds(0,25,500,125);

		userName = new JTextField();
		userName.setSize(new Dimension(50, 25));

		atomNum = new JTextField();
		atomNum.setSize(new Dimension(50, 25));

		molNum = new JTextField();
		molNum.setSize(new Dimension(50, 25));

		rbNum = new JTextField();
		rbNum.setSize(new Dimension(50, 25));

		pwNum = new JTextField();
		pwNum.setSize(new Dimension(50, 25));

		lUnit = new JTextField();
		lUnit.setSize(new Dimension(50, 25));

		shieldNum = new JTextField();
		shieldNum.setSize(new Dimension(50, 25));

		atomNum.setBackground(bgColor);
		molNum.setBackground(bgColor);
		rbNum.setBackground(bgColor);
		pwNum.setBackground(bgColor);
		lUnit.setBackground(bgColor);
		shieldNum.setBackground(bgColor);
		userName.setBackground(bgColor);

		JLabel userNameLabel = new JLabel("Enter Username: ");
		JLabel atomLabel = new JLabel("Enter desired atom amount: ");
		JLabel molLabel = new JLabel("Enter desired molecule amount: ");
		JLabel pwLabel = new JLabel("Enter desired powerup amount: ");
		JLabel rbLabel = new JLabel("Enter desired reaction blocker amount: ");
		JLabel lLabel = new JLabel("Enter desired L unit: ");
		JLabel shieldLabel = new JLabel("Enter desired shield amount: ");

		nums.add(userNameLabel);
		nums.add(userName);
		nums.add(atomLabel);
		nums.add(atomNum);
		nums.add(molLabel);
		nums.add(molNum);
		nums.add(pwLabel);
		nums.add(pwNum);
		nums.add(rbLabel);
		nums.add(rbNum);
		nums.add(lLabel);
		nums.add(lUnit);
		nums.add(shieldLabel);
		nums.add(shieldNum);

	}

	public void initMolStructPanel() {
		// Panel to decide molecule structures

		molStructs = new JPanel();
		molStructs.setBackground(bgColor);
		molStructs.setLayout(new GridLayout(1, 3));
		// molStructs.setBounds(0,150,500,50);

		alphaLinear = new JCheckBox();
		alphaLinear.setText("<html>Linear alpha molecule.</html>");

		betaLinear = new JCheckBox();
		betaLinear.setText("<html>Linear beta molecule.</html>");

		spinning = new JCheckBox();
		spinning.setText("<html>Spinning<br/>otherwise stationary.</html>");

		alphaLinear.setBackground(bgColor);
		betaLinear.setBackground(bgColor);
		spinning.setBackground(bgColor);
		molStructs.add(alphaLinear);
		molStructs.add(betaLinear);
		molStructs.add(spinning);
	}

	public void initButtons() {
		// Panel for buttons

		buttons = new JPanel();
		buttons.setBackground(bgColor);
		buttons.setLayout(new GridLayout(1, 1));
		// buttons.setBounds(0,200,500,50);

		Load = new JButton();
		Load.setText("Load");
		Load.setFocusable(false);
		Load.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				new LoadScreen(instance);
			}
		});

		start = new JButton();
		start.setText("Start");
		start.setFocusable(false);
		start.addActionListener(this);

		start.setBackground(new Color(166, 236, 168));
		Load.setBackground(new Color(255, 81, 71));

		buttons.add(Load);
		buttons.add(start);
	}

	public int toInt(JTextField num) {
		try {
			return Integer.parseInt(num.getText());
		}
		catch (NumberFormatException e) {
			return 0;
		}
	}

	public void assignValues() {

		if (!lUnit.getText().equals("")) {
			if (toInt(lUnit) >= 10 && toInt(lUnit) <= 200) {
				L = toInt(lUnit);
			} else {
				System.out.println("Invalid L, system initiates the default value.");
			}
		}
		KuVidGame.setL(L);

		if (!atomNum.getText().equals("")) {
			if (!(toInt(atomNum) <= 0) && !(toInt(atomNum) > 800)) {
				atomAmount = toInt(atomNum);
			}
		}

		if (!molNum.getText().equals("")) {
			if (!(toInt(molNum) <= 0) && !(toInt(molNum) > 800)) {
				molAmount = toInt(molNum);
			}
		}
		KuVidGame.setMolNumber(molAmount);

		if (!pwNum.getText().equals("")) {
			if (!(toInt(pwNum) <= 0) && !(toInt(pwNum) > 800)) {
				pwAmount = toInt(pwNum);
			}
		}
		KuVidGame.setPowNumber(pwAmount);

		if (!rbNum.getText().equals("")) {
			if (!(toInt(rbNum) <= 0) && !(toInt(rbNum) > 800)) {
				rbAmount = toInt(rbNum);
			}
		}
		KuVidGame.setRbNumber(rbAmount);

		if (!shieldNum.getText().equals("")) {
			if (!(toInt(shieldNum) <= 0) && !(toInt(shieldNum) > 800)) {
				shieldAmount = toInt(shieldNum);
			}
		}

		if (difficultyLevels.getSelectedItem().toString().equals("Easy")) {
			KuVidGame.setValuesAccordingToDifficulty(DifficultyLevels.EASY);
		} else if (difficultyLevels.getSelectedItem().toString().equals("Normal")) {
			KuVidGame.setValuesAccordingToDifficulty(DifficultyLevels.MEDIUM);
		} else {
			KuVidGame.setValuesAccordingToDifficulty(DifficultyLevels.HARD);
		}
		
		if(songBox.getSelectedItem().toString().equals("Bilal")) {
			KuVidGame.getInstance().setSongName("Bilal");
		}
		else if(songBox.getSelectedItem().toString().equals("UwU")) {
			KuVidGame.getInstance().setSongName("UwU");
		}
		else if(songBox.getSelectedItem().toString().equals("Requiem")) {
			KuVidGame.getInstance().setSongName("Requiem");
		}
		else if(songBox.getSelectedItem().toString().equals("Deep Turkish")) {
			KuVidGame.getInstance().setSongName("Deep Turkish");
		}
		else if(songBox.getSelectedItem().toString().equals("Deli Vahit")) {
			KuVidGame.getInstance().setSongName("Deli Vahit");
		}
		else if(songBox.getSelectedItem().toString().equals("None")) {
			KuVidGame.getInstance().setSongName("None");
		}

		if (alphaLinear.isSelected()) {
			KuVidGame.getInstance().setAlphaMolStruct(MoleculeStruct.LINEAR);
		} else {
			KuVidGame.getInstance().setAlphaMolStruct(MoleculeStruct.TRIGONAL);
		}

		if (betaLinear.isSelected()) {
			KuVidGame.getInstance().setBetaMolStruct(MoleculeStruct.LINEAR);
		} else {
			KuVidGame.getInstance().setBetaMolStruct(MoleculeStruct.TRIGONAL);
		}

		if (spinning.isSelected()) {
			KuVidGame.getInstance().setLinearSpinning(true);
		}

		if (!userName.getText().equals("")) {
			name = userName.getText();
		}
		KuVidGame.getInstance().setUserName(name);

	}

	@SuppressWarnings("unused")
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		assignValues();

		KuvidSystem kuvidSystem = new KuvidSystem(); // for that being created in the beginning of the game

		KuVidGame.getInstance().setWindowHeight(KuVidGameFrame.getFrameHeight());
		KuVidGame.getInstance().setWindowWidth(SkyPanel.PANEL_WIDTH);
		KuVidGame.getInstance().createInv(atomAmount, shieldAmount);
		KuVidGame.getInstance().createFallingObjectsInventory(molAmount, pwAmount, rbAmount);
		KuVidGame.getInstance().createShooter();
		KuVidGame.getInstance().getClock().start();
		KuVidGame.getInstance().createBlender();
		StartHelper.start();
		KuVidGame.getInstance().putBulletToAtom();

		this.setVisible(false);
		this.dispose();
	}
	
	public void disposeAfterLoad() {
		this.setVisible(false);
		this.dispose();
	}

}
