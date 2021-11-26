package ui;

import domain.saveload.LoaderListener;
import ui.frame.KuVidGameFrame;

public class StartHelper implements LoaderListener {
	public static KuVidGameFrame kuvidGameFrame =null;

	public static void start() {
		kuvidGameFrame = new KuVidGameFrame();
		
	}

	@Override
	public void startGameFrame() {
		start();
		kuvidGameFrame.getSky().startTimer();
		
	}

	@Override
	public void initSounds() {
		kuvidGameFrame.getSky().initializeSounds();
		
	}
}
