package domain.factories;

import domain.catalog.Catalog;
import domain.catalog.molecule.MoleculeDescription;
import domain.hitbox.Point;
import domain.kuvidgame.KuVidGame;
import domain.objects.molecule.Molecule;

public class MoleculeFactory implements ObjectFactory<Molecule> {

	private static MoleculeFactory moleculeFactory = null;
	private FactoryListener factoryListener;

	public static MoleculeFactory getInstance() {
		if (moleculeFactory == null) {
			moleculeFactory = new MoleculeFactory();
		}
		return moleculeFactory; 
	}

	@Override
	public void setFactoryListener(FactoryListener fl) {
		factoryListener = fl;
	}

	public FactoryListener getFactoryListener() {
		return factoryListener;
	}
	
	public Molecule createMoleculeWithPosition(ObjectType type, Point initalPosition) {
		Molecule mol = createMoleculeWithoutAddingToPanel(type);
		mol.getObjectHitbox().setCenter(initalPosition);
		getFactoryListener().addMoleculeInActionToSkyPanel(mol); // adding atom to panel if it is wanted to be drawn
		return mol;
	}

	private Molecule createMolecule(ObjectType type) {
		// this method created molecule with given type and draws to the panel.
		Molecule mol = createMoleculeWithoutAddingToPanel(type);
		getFactoryListener().addMoleculeInActionToSkyPanel(mol); // adding atom to panel if it is wanted to be drawn
		return mol;

	}
	
	public Molecule createMoleculeWithoutAddingToPanel(ObjectType type) {
		// this method only created the molecule instance does not draw it to the panel.
		Catalog gameCatalog = KuVidGame.getInstance().getCatalog();// getting catalog
		MoleculeDescription molDescr = gameCatalog.getMoleculeDescription(type, KuVidGame.getInstance().getAlphaMolStruct(), 
				KuVidGame.getInstance().getBetaMolStruct()); // getting description in type and structs are given
		return createMoleculeWithDescription(molDescr);
		
	}
	
	private Molecule createMoleculeWithDescription(MoleculeDescription molDescr) {
		//This method creates molecule instance with given description.
		Molecule mol = new Molecule(molDescr, molDescr.createHitBoxWithInformationInDescription()); // creating molecule but position is no set
		return mol;
	}

	@Override
	public Molecule createObject(ObjectType type) {
		return createMolecule(type);
	}

}
