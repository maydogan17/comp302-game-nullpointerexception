package domain.factories;

import domain.objects.Shootable;
import domain.objects.molecule.Molecule;
import domain.objects.reactionblocker.Rb;

public interface FactoryListener {

	public void addMoleculeInActionToSkyPanel(Molecule mol);

	public void addShootableInActionToSkyPanel(Shootable shootable);

	public void addRbInActionToSkyPanel(Rb rb);

}
