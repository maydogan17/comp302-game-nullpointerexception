package domain.objects.reactionblocker;

import java.util.List;

import domain.objects.KuvidObject;

public interface RbListener {

	public void onLocationChangeEvent();

	public void onRemoveEvent();

	public List<KuvidObject> getObjectsInPanel();

}
