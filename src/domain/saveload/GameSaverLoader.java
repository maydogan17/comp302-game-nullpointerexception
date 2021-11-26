package domain.saveload;

import domain.kuvidgame.KuVidGame;

public class GameSaverLoader {

	private SaverListener saverListener;
	private String username;

	private SaverLoaderAdapter saveLoadService;

	public GameSaverLoader(SaverListener sl, String type) {
		saverListener = sl;
		if (type.equals("file")) {
			saveLoadService = new SaveLoadToFileAdapter();
		} else if (type.equals("database")) {
			saveLoadService = new SaveLoadToDatabaseAdapter();
		} else {
			System.out.println("Adapter type has not been found.");
		}
	}

	public GameSaverLoader(String username, String type) {
		this.username = username;

		if (type.equals("file")) {
			saveLoadService = new SaveLoadToFileAdapter();
		} else if (type.equals("database")) {
			saveLoadService = new SaveLoadToDatabaseAdapter();
		} else {
			System.out.println("Adapter type has not been found.");
		}
	}

	public void save() {
		GameSaveHelper saveHelper = new GameSaveHelper(KuVidGame.getInstance(), saverListener,
				KuVidGame.getInstance().getInv(), KuVidGame.getInstance().getFallInv());
		saveLoadService.save(saveHelper.gameToHelperGame());
	}

	public void load(LoaderListener loaderListener) {
		saveLoadService.load(username, loaderListener);
	}

}
