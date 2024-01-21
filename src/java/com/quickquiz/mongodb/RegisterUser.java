package com.quickquiz.mongodb;

import com.quickquiz.helper.DatabaseConnector;
import com.mongodb.MongoWriteException;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.IndexOptions;
import com.quickquiz.entities.UserSchema;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.bson.Document;

public class RegisterUser {

    private MongoDatabase database;

    public RegisterUser(MongoDatabase database) {
        this.database = database;
    }

    //update data
    public void registerUser(UserSchema newUser, HttpServletResponse resp) throws IOException {
        if (database != null) {
            try {
                MongoCollection<Document> user = database.getCollection("user");
                user.createIndex(new Document("email", 1), new IndexOptions().unique(true));
                user.createIndex(new Document("username", 1), new IndexOptions().unique(true));

                Document userDocument = new Document()
                        .append("name", newUser.getName())
                        .append("email", newUser.getEmail())
                        .append("username", newUser.getUsername())
                        .append("gender", newUser.getGender())
                        .append("password", newUser.getPassword())
                        .append("created_on", newUser.getCurrent());

                user.insertOne(userDocument);

                MongoCollection<Document> score = database.getCollection("scores");

                Document userScore = new Document()
                        .append("username", newUser.getUsername())
                        .append("highest_score", 0)
                        .append("previous_score", 0);
                
                score.insertOne(userScore);

                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("Registered Successfully");
            } catch (MongoWriteException e) {
                if (e.getCode() == 11000) {
                    //Duplicate handling
                    String errorMessage = e.getMessage();
                    int startIdx = errorMessage.indexOf("dup key: {") + "dup key: {".length();
                    int endIdx = errorMessage.indexOf("}", startIdx);
                    String details = errorMessage.substring(startIdx, endIdx);
                    String[] detailsArray = details.split(":");
                    resp.setStatus(e.getCode());
                    resp.getWriter().write(detailsArray[0].trim());
                } else {
                    resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                    resp.getWriter().write("Error during document insertion: " + e.getMessage());
                }
            } catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("Unexpected error during document insertion: " + e.getMessage());
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error connecting to the database. Please try again later.");
        }
    }
}
