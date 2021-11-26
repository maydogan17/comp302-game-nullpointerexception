package ui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import domain.KuvidSystem;
import domain.kuvidgame.KuVidGame;

@SuppressWarnings("serial")
public class PauseScreen extends JFrame {

	private static final int FRAME_WIDTH = 500;
	private static final int FRAME_HEIGHT = 100;

	private JPanel pausePanel;

	// private JButton saveButton;
	private JButton fileButton;
	private JButton databaseButton;
	private JButton cancelButton;

	private SkyPanel skyPanel;

	private KuvidSystem controller = new KuvidSystem();

	public PauseScreen(SkyPanel skyPanel) {
		this.skyPanel = skyPanel;

		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Pause Game");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);

		setResizable(false);
		// setLayout(new BorderLayout());
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); // for popping in the center of the screen
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		setVisible(true);
		setFocusable(true);

		initPauseGamePanel();
		add(pausePanel, BorderLayout.CENTER);
	}

	private void initPauseGamePanel() {
		pausePanel = new JPanel();
		pausePanel.setBackground(new Color(181, 234, 215));

		pausePanel.setLayout(new GridLayout(1, 3));

		fileButton = new JButton("Save to File");
		fileButton.setBackground(new Color(203, 170, 203));
		fileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.saveGame(skyPanel, "file");
				fileButton.setFocusable(false);
				requestFocus();
				setVisible(false);
				dispose();
				skyPanel.timer.start();
				KuVidGame.getInstance().getClock().start();

			}
		});
		fileButton.setAlignmentX(BOTTOM_ALIGNMENT);
		pausePanel.add(fileButton);

		databaseButton = new JButton("Save to Database");
		databaseButton.setBackground(new Color(203, 170, 203));
		databaseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.saveGame(skyPanel, "database");
				databaseButton.setFocusable(false);
				requestFocus();
				setVisible(false);
				dispose();
				skyPanel.timer.start();
				KuVidGame.getInstance().getClock().start();
			}
		});

		pausePanel.add(databaseButton);

		cancelButton = new JButton("Resume Game");
		cancelButton.setBackground(new Color(204, 226, 203));
		cancelButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				cancelButton.setFocusable(false);
				requestFocus();
				setVisible(false);
				dispose();
				skyPanel.timer.start();
				KuVidGame.getInstance().getClock().start();

			}
		});
		cancelButton.setAlignmentX(BOTTOM_ALIGNMENT);
		pausePanel.add(cancelButton);

	}

}
