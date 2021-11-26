package domain;

import domain.factories.ObjectType;
import domain.kuvidgame.KuVidGame;
import domain.saveload.GameSaveLoaderSingleton;
import domain.saveload.LoaderListener;
import domain.saveload.SaverListener;
import domain.shooter.WrongShieldTypeException;

public class KuvidSystem {

	public KuvidSystem() {
	}

	public void moveShooterRight() {
		KuVidGame.getInstance().getShooter().moveRight();
	}

	public void moveShooterLeft() {
		KuVidGame.getInstance().getShooter().moveLeft();
	}

	public void rotateShooterLeft() {
		KuVidGame.getInstance().getShooter().rotateLeft();

	}

	public void rotateShooterRight() {
		KuVidGame.getInstance().getShooter().rotateRight();

	}

	public void pickRandomAtom() {
		KuVidGame.getInstance().getShooter().getBullet().pickRandomAtom();
	}

	public void activateBlender(int sourceType, int targetType) {
		KuVidGame.getInstance().getInv().getBlender().runBlender(sourceType, targetType);
	}

	public void getClock() {
		KuVidGame.getInstance().getClock();
	}

	public void shoot() {
		KuVidGame.getInstance().getShooter().getBullet().shootShootable();
	}
	
	public void addPowerup(ObjectType type) {
		
	}
	
	public void addShield(ObjectType shieldType) {
		try {
			KuVidGame.getInstance().getShooter().getBullet().addShield(shieldType);
		} catch (WrongShieldTypeException e) {
			e.printStackTrace();
		}
	}
	
	public void loadPowerupToBullet(ObjectType type) {
		KuVidGame.getInstance().getShooter().getBullet().setPowerupToBullet(type);
	}
	
	public void saveGame(SaverListener saverListener, String type) {
		GameSaveLoaderSingleton.getInstance().saveGame(saverListener, type);
		
	}
	
	public void loadGame(String username, String type, LoaderListener loaderListener) {
		GameSaveLoaderSingleton.getInstance().loadGame(username, type, loaderListener);
	}
	
}
