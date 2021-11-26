package domain.saveload;

import domain.saveload.helperobjects.HelperGame;

public class SaveLoadToFileAdapter implements SaverLoaderAdapter{
	
	private SaveLoadToFile slf;
	
	public SaveLoadToFileAdapter() {
		slf = new SaveLoadToFile();
	}

	@Override
	public void save(HelperGame helperGame) {
		slf.saveToFile(helperGame);
		
	}

	@Override
	public void load(String username,LoaderListener loaderListener) {
		slf.loadFromFile(username,loaderListener);
		
	}

}
