package ui.frame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

import domain.kuvidgame.KuVidGame;
import ui.FactoryInUI;
import ui.StartHelper;

@SuppressWarnings("serial")
public class EndGameScreen extends JFrame {

	private static final int FRAME_WIDTH = 300;
	private static final int FRAME_HEIGHT = 150;

	private JPanel endGame;
	private JLabel CongratsTxt;
	private JLabel userName;
	private JLabel playerName;
	private JLabel scoreTxt;
	private JLabel score;
	private JButton Restart;
	private JButton Exit;

	private AudioClip endClip;

	public EndGameScreen() {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("End Game");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); // for popping in the center of the screen
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		setVisible(true);
		setFocusable(true);
		initializeSounds();
		endClip.play();

		initEndGamePanel();
		add(endGame, BorderLayout.CENTER);

	}

	public void initializeSounds() {
		try {
			endClip = Applet.newAudioClip(new URL("file:sounds/end.wav"));
		} catch (MalformedURLException murle) {
			System.out.println(murle);
		}
	}

	private void initEndGamePanel() {

		endGame = new JPanel();
		endGame.setBackground(new Color(181, 234, 215));
		// endGame.setLayout(new GridLayout(3, 1));

		CongratsTxt = new JLabel("Congratulations, you finished the game");
		CongratsTxt.setFont(new Font("Times New Roman", Font.BOLD, 17));

		// ImageIcon gammaAtomIcon = new ImageIcon("images/gammaAtom.png");
		// CongratsTxt.setIcon(gammaAtomIcon);
		endGame.add(CongratsTxt);

		userName = new JLabel("Username:");
		userName.setFont(new Font("Times New Roman", Font.BOLD, 17));
		endGame.add(userName);

		playerName = new JLabel(KuVidGame.getInstance().getUserName());
		playerName.setFont(new Font("Tahoma", Font.PLAIN, 17));
		endGame.add(playerName);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		endGame.add(separator);

		scoreTxt = new JLabel("Score:");
		scoreTxt.setFont(new Font("Times New Roman", Font.BOLD, 17));
		endGame.add(scoreTxt);

		score = new JLabel(KuVidGame.getInstance().scoreToBePrinted());
		score.setFont(new Font("Tahoma", Font.PLAIN, 17));
		endGame.add(score);

		Restart = new JButton("Restart");
		Restart.setBackground(new Color(204, 226, 203));
		Restart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				// KuVidGameFrame.dispose();

				Restart.setFocusable(false);
				// requestFocus();

				endClip.stop();
				FactoryInUI.getInstance().reset();
				KuVidGame.reset();
				StartHelper.kuvidGameFrame.reset();

				FactoryInUI.getInstance();
				new SettingsWindow();

				setVisible(false);
				dispose();
			}
		});
		endGame.add(Restart);
		Exit = new JButton("Exit");
		Exit.setBackground(new Color(223, 199, 193));
		Exit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {

				System.exit(0);
			}

		});
		endGame.add(Exit);

	}

}
