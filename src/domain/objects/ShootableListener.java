package domain.objects;

import java.util.List;

import domain.objects.molecule.Molecule;
import domain.objects.reactionblocker.Rb;

public interface ShootableListener {
	public void updateShootableInActionInSkyPanel();
	public void onLocationChangeEvent();
	public void onRemoveEvent();
	public List<Molecule> getMoleculesInPanel();
	public List<Rb> getReactionBlockersInPanel();
}
