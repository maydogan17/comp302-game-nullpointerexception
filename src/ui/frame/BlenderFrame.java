package ui.frame;

import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import domain.KuvidSystem;

@SuppressWarnings("serial")
public class BlenderFrame extends JFrame {
	private static final int FRAME_WIDTH = 350;
	private static final int FRAME_HEIGHT = 120;

	private KuvidSystem kuvidSystem = new KuvidSystem();

	private JPanel blender;
	private JLabel source;
	private JLabel sourceRank;
	private JLabel target;
	private JLabel targetRank;
	private JButton blendBreak;
	private JButton cancel;

	private int sourceAtomRank = 1;
	private int targetAtomRank = 1;

	private ArrayList<Integer> atomRankList = new ArrayList<>(Arrays.asList(1, 1));

	private KeyListener listener;

	Timer timer = new Timer(40, new ActionListener() {
		@Override
		public void actionPerformed(ActionEvent ae) {
			updateRanks();
		}
	});

	public BlenderFrame() {
		setSize(FRAME_WIDTH, FRAME_HEIGHT);
		setTitle("Blender");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setResizable(false);
		setLayout(new BorderLayout());
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize(); // for popping in the center of the screen
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		setVisible(true);
		setFocusable(true);

		initListener();
		this.addKeyListener(listener);

		initBlenderPanel();
		add(blender, BorderLayout.CENTER);

		timer.start();

	}

	private void initBlenderPanel() {

		blender = new JPanel();

		source = new JLabel("Source Atom:");
		source.setFont(new Font("Times New Roman", Font.BOLD, 17));
		blender.add(source);

		sourceRank = new JLabel("Unselected");
		sourceRank.setFont(new Font("Tahoma", Font.PLAIN, 17));
		blender.add(sourceRank);

		JSeparator separator = new JSeparator();
		separator.setOrientation(SwingConstants.VERTICAL);
		blender.add(separator);

		target = new JLabel("Target Atom:");
		target.setFont(new Font("Times New Roman", Font.BOLD, 17));
		blender.add(target);

		targetRank = new JLabel("Unselected");
		targetRank.setFont(new Font("Tahoma", Font.PLAIN, 17));
		blender.add(targetRank);

		blendBreak = new JButton("Blend / Break");
		blendBreak.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				kuvidSystem.activateBlender(sourceAtomRank, targetAtomRank);
				try {
					AudioClip clip = Applet.newAudioClip(
					new URL("file:sounds/blend.wav"));
					clip.play();
					} catch (MalformedURLException murle) {
					System.out.println(murle);
					}
				blendBreak.setFocusable(false);
				requestFocus();

			}
		});
		blender.add(blendBreak);

		cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				timer.stop();
				setVisible(false);
				dispose();
			}

		});
		blender.add(cancel);
	}

	public void updateRanks() {
		int size = atomRankList.size();

		if (size % 2 == 0) {
			sourceAtomRank = atomRankList.get(size - 2);
			targetAtomRank = atomRankList.get(size - 1);
		} else if (size % 2 == 1) {
			sourceAtomRank = atomRankList.get(size - 1);
			targetAtomRank = atomRankList.get(size - 2);
		}

		sourceRank.setText(Integer.toString(sourceAtomRank));
		targetRank.setText(Integer.toString(targetAtomRank));
	}

	private void initListener() {
		listener = new KeyListener() {

			@Override
			public void keyPressed(KeyEvent e) {

				int key = e.getKeyCode();

				switch (key) {
				case 49:
					atomRankList.add(1);
					break;
				case 50:
					atomRankList.add(2);
					break;
				case 51:
					atomRankList.add(3);
					break;
				case 52:
					atomRankList.add(4);
					break;
				}
			}

			@Override
			public void keyReleased(KeyEvent arg0) {

			}

			@Override
			public void keyTyped(KeyEvent arg0) {

			}

		};
	}

	public int getSourceAtomRank() {
		return sourceAtomRank;
	}

	public int getTargetAtomRank() {
		return targetAtomRank;
	}

}
