package domain.saveload;

import static com.mongodb.client.model.Filters.eq;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bson.Document;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

import domain.saveload.helperobjects.HelperGame;

public class SaveLoadToDatabase {

	private MongoCollection<Document> collection;

	public SaveLoadToDatabase() {
		Logger mongoLogger = Logger.getLogger("org.mongodb.driver");
		mongoLogger.setLevel(Level.SEVERE); // e.g. or Log.WARNING, etc.
		MongoClient mongoClient = MongoClients
				.create("mongodb+srv://comp302_user:comp302_password@sandbox.v2mqr.mongodb.net/"); // uri connection to
																									// the server
		MongoDatabase database = mongoClient.getDatabase("Comp302"); // selecting the database
		collection = database.getCollection("NullPointerExceptionCollection"); // collection
	}

	public void saveToDatabase(HelperGame helperGame) {

		ObjectMapper objectMapper = new ObjectMapper();
		String gameString = null;
		try {
			gameString = objectMapper.writeValueAsString(helperGame);
		} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		if (gameExists(collection, helperGame.getUsername())) {
			collection.updateOne(Filters.eq("username", helperGame.getUsername()), Updates.set("gameInfo", gameString));
		} else {
			Document doc = new Document();
			doc.append("username", helperGame.getUsername());
			doc.append("gameInfo", gameString);
			collection.insertOne(doc);
		}

	}

	public void loadFromDatabase(String username, LoaderListener loaderListener) {
		if (gameExists(collection, username)) {
			Document found = collection.find(eq("username", username)).first();
			String gameString = found.get("gameInfo").toString();

			HelperGame helperGame = null;

			ObjectMapper objectMapper = new ObjectMapper();
			try {
				helperGame = objectMapper.readValue(gameString, HelperGame.class);
			} catch (IOException e) {
				e.printStackTrace();
				System.out.println("Username has not found.");
			}

			GameLoadHelper gameLoadHelper = new GameLoadHelper(helperGame, loaderListener);
			gameLoadHelper.initGame();
		}
	}

	public static boolean gameExists(MongoCollection<Document> c, String username) {
		FindIterable<Document> iterable = c.find(new Document("username", username));
		return iterable.first() != null;
	}
}
