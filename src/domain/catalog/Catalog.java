package domain.catalog;

import java.util.HashMap;
import java.util.Map;

import domain.catalog.atom.AlphaAtomDescription;
import domain.catalog.atom.AtomDescription;
import domain.catalog.atom.BetaAtomDescription;
import domain.catalog.atom.GammaAtomDescription;
import domain.catalog.atom.SigmaAtomDescription;
import domain.catalog.molecule.MoleculeDescription;
import domain.catalog.molecule.linear.LinearAlphaMoleculeDescription;
import domain.catalog.molecule.linear.LinearBetaMoleculeDescription;
import domain.catalog.molecule.trigonal.GammaMoleculeDescription;
import domain.catalog.molecule.trigonal.SigmaMoleculeDescription;
import domain.catalog.molecule.trigonal.TrigonalAlphaMoleculeDescription;
import domain.catalog.molecule.trigonal.TrigonalBetaMoleculeDescription;
import domain.catalog.powerup.AlphaPwDescription;
import domain.catalog.powerup.BetaPwDescription;
import domain.catalog.powerup.GammaPwDescription;
import domain.catalog.powerup.PowerupDescription;
import domain.catalog.powerup.SigmaPwDescription;
import domain.catalog.rb.AlphaRBDescription;
import domain.catalog.rb.BetaRBDescription;
import domain.catalog.rb.GammaRBDescription;
import domain.catalog.rb.RbDescription;
import domain.catalog.rb.SigmaRBDescription;
import domain.factories.ObjectType;
import domain.objects.molecule.MoleculeStruct;

public class Catalog {

	private Map<ObjectType, AtomDescription> atomDescrMap = new HashMap<ObjectType, AtomDescription>();
	private Map<ObjectType, PowerupDescription> pwDescrMap = new HashMap<ObjectType, PowerupDescription>();

	private Map<ObjectType, MoleculeDescription> triMolDescrMap = new HashMap<ObjectType, MoleculeDescription>();
	private Map<ObjectType, MoleculeDescription> linMolDescrMap = new HashMap<ObjectType, MoleculeDescription>();

	private Map<ObjectType, RbDescription> rbDescrMap = new HashMap<ObjectType, RbDescription>();

	public Catalog() {
		loadCatalog();

	}

	public AtomDescription getAtomDescription(ObjectType type) {
		return atomDescrMap.get(type);
	}

	public PowerupDescription getPowerupDescription(ObjectType type) {
		return pwDescrMap.get(type);
	}

	public RbDescription getRbDescription(ObjectType type) {
		return rbDescrMap.get(type);
	}

	public MoleculeDescription getMoleculeDescription(ObjectType type, MoleculeStruct alphaMolStruct,
			MoleculeStruct betaMolStruct) {
		if (type == ObjectType.ALPHA_M) {
			if (alphaMolStruct == MoleculeStruct.TRIGONAL) {
				return triMolDescrMap.get(type);
			} else {
				return linMolDescrMap.get(type);
			}
		} else if (type == ObjectType.BETA_M) {
			if (betaMolStruct == MoleculeStruct.TRIGONAL) {
				return triMolDescrMap.get(type);
			} else {
				return linMolDescrMap.get(type);
			}
		} else {
			return triMolDescrMap.get(type);
		}
	}

	public void loadCatalog() {
		atomDescrMap.put(ObjectType.ALPHA, new AlphaAtomDescription());
		atomDescrMap.put(ObjectType.BETA, new BetaAtomDescription());
		atomDescrMap.put(ObjectType.GAMMA, new GammaAtomDescription());
		atomDescrMap.put(ObjectType.SIGMA, new SigmaAtomDescription());

		pwDescrMap.put(ObjectType.ALPHA_POW, new AlphaPwDescription());
		pwDescrMap.put(ObjectType.BETA_POW, new BetaPwDescription());
		pwDescrMap.put(ObjectType.GAMMA_POW, new GammaPwDescription());
		pwDescrMap.put(ObjectType.SIGMA_POW, new SigmaPwDescription());

		triMolDescrMap.put(ObjectType.ALPHA_M, new TrigonalAlphaMoleculeDescription());
		triMolDescrMap.put(ObjectType.BETA_M, new TrigonalBetaMoleculeDescription());
		triMolDescrMap.put(ObjectType.GAMMA_M, new GammaMoleculeDescription());
		triMolDescrMap.put(ObjectType.SIGMA_M, new SigmaMoleculeDescription());

		linMolDescrMap.put(ObjectType.ALPHA_M, new LinearAlphaMoleculeDescription());
		linMolDescrMap.put(ObjectType.BETA_M, new LinearBetaMoleculeDescription());

		rbDescrMap.put(ObjectType.ALPHA_RB, new AlphaRBDescription());
		rbDescrMap.put(ObjectType.BETA_RB, new BetaRBDescription());
		rbDescrMap.put(ObjectType.GAMMA_RB, new GammaRBDescription());
		rbDescrMap.put(ObjectType.SIGMA_RB, new SigmaRBDescription());

	}

}
