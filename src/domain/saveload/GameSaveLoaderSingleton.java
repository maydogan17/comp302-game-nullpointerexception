package domain.saveload;

public class GameSaveLoaderSingleton {

	private static GameSaveLoaderSingleton instance = null;

	public GameSaveLoaderSingleton() {

	}

	public static GameSaveLoaderSingleton getInstance() {

		if (instance == null) {
			instance = new GameSaveLoaderSingleton();
		}
		return instance;
	}

	public void saveGame(SaverListener saverListener, String type) {
		GameSaverLoader gameSaverLoader = new GameSaverLoader(saverListener, type);
		gameSaverLoader.save();
	}

	public void loadGame(String username, String type, LoaderListener loaderListener) {
		GameSaverLoader gameSaverLoader = new GameSaverLoader(username, type);
		gameSaverLoader.load(loaderListener);
	}

}
