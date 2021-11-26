package domain.saveload;

import domain.saveload.helperobjects.HelperGame;

public interface SaverLoaderAdapter {
	
	public abstract void save(HelperGame helperGame);
	public abstract void load(String username, LoaderListener loaderListener);
}
