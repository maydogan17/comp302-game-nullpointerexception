package domain.kuvidgame;

public enum DifficultyLevels {
	EASY(1000), MEDIUM(500), HARD(250);
	
	int secondPerMolecule;

	
	private DifficultyLevels(int secondPerMolecule) {
		this.secondPerMolecule = secondPerMolecule;
	}
}
