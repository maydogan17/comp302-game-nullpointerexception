package domain.saveload;

import java.io.File;
import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;

import domain.saveload.helperobjects.HelperGame;

public class SaveLoadToFile {

	public void saveToFile(HelperGame helperGame) {
		try {

			// Writing to a file
			// File file=new File("saves/myfile.json");
			File file = new File("saves/" + helperGame.getUsername() + ".json");
			ObjectMapper objectMapper = new ObjectMapper();
			objectMapper.writeValue(file, helperGame);

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public void loadFromFile(String username, LoaderListener loaderListener) {

		HelperGame helperGame = null;

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			helperGame = objectMapper.readValue(new File("saves/" + username + ".json"), HelperGame.class);
		} catch (IOException e) {
			System.out.println("Username has not found.");
		}

		GameLoadHelper gameLoadHelper = new GameLoadHelper(helperGame, loaderListener);
		gameLoadHelper.initGame();

	}

}
