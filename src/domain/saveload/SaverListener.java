package domain.saveload;

import java.util.List;

import domain.objects.KuvidObject;

public interface SaverListener {

	public abstract List<KuvidObject> publishObjectsInPanel();
}
