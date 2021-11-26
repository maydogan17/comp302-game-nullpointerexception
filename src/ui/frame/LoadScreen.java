package ui.frame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import domain.KuvidSystem;
import ui.StartHelper;

@SuppressWarnings("serial")
public class LoadScreen extends JFrame {
	private static final int FRAME_WIDTH = 350;
	private static final int FRAME_HEIGHT = 150;

	private JPanel loadGame;
	private JLabel userName;
	private JTextField playerName;
	private JButton fileButton;
	private JButton databaseButton;


	private SettingsWindow settingsWindow;

	public LoadScreen(SettingsWindow settingsWindow) {

		this.settingsWindow = settingsWindow;
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Load Game");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		setResizable(false);
		// setLayout(new BorderLayout());
		this.setLayout(new BoxLayout(this.getContentPane(), BoxLayout.PAGE_AXIS));
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); // for popping in the center of the screen
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		setVisible(true);
		setFocusable(true);

		initLoadGamePanel();
		add(loadGame, BorderLayout.CENTER);
	}

	private void initLoadGamePanel() {
		loadGame = new JPanel();
		loadGame.setBackground(new Color(181, 234, 215));

		loadGame.setLayout(new GridLayout(2, 2));

		userName = new JLabel("Enter user name: ");
		userName.setFont(new Font("Times New Roman", Font.BOLD, 17));
		userName.setAlignmentX(TOP_ALIGNMENT);
		loadGame.add(userName);

		playerName = new JTextField();
		playerName.setSize(new Dimension(200, 25));
		loadGame.add(playerName);
		playerName.setAlignmentX(TOP_ALIGNMENT);

		fileButton = new JButton("Load from File");
		fileButton.setBackground(new Color(203, 170, 203));
		fileButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				StartHelper starter = new StartHelper();
				String username = playerName.getText().trim();
				File game = new File("saves/" + username + ".json");
				if (game.exists()) {
					KuvidSystem system = new KuvidSystem();
					system.loadGame(username, "file", starter);
				} else {
					JOptionPane.showMessageDialog(null, "Username has not found.");
				}
				fileButton.setFocusable(false);
				requestFocus();
				setVisible(false);
				settingsWindow.dispose();
				dispose();
			}
		});
		fileButton.setAlignmentX(BOTTOM_ALIGNMENT);
		loadGame.add(fileButton);

		databaseButton = new JButton("Load from Database");
		databaseButton.setBackground(new Color(203, 170, 203));
		databaseButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {

				StartHelper starter = new StartHelper();
				String username = playerName.getText().trim();
				KuvidSystem system = new KuvidSystem();
				system.loadGame(username, "database", starter);

				databaseButton.setFocusable(false);
				requestFocus();
				setVisible(false);
				settingsWindow.dispose();
				dispose();
			}
		});

		loadGame.add(databaseButton);

	}

}
