package domain.saveload;

import domain.saveload.helperobjects.HelperGame;

public class SaveLoadToDatabaseAdapter implements SaverLoaderAdapter {

	private SaveLoadToDatabase sld;

	public SaveLoadToDatabaseAdapter() {
		sld = new SaveLoadToDatabase();
	}

	@Override
	public void save(HelperGame helperGame) {
		sld.saveToDatabase(helperGame);

	}

	@Override
	public void load(String username, LoaderListener loaderListener) {
		// TODO Auto-generated method stub
		sld.loadFromDatabase(username, loaderListener);
	}

}
