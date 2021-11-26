package domain.objects.molecule;

import java.util.List;

import domain.objects.reactionblocker.Rb;

public interface MoleculeListener {

	public void onLocationChangeEvent();

	public void onRemoveEvent();

	public void onRotationEvent();

	public List<Rb> getReactionBlockersInPanel();

}
