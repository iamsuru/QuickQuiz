package com.quickquiz.mongodb;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.quickquiz.entities.UserSchema;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import org.bson.Document;

public class ForgotPassword {

    private MongoDatabase database;

    public ForgotPassword(MongoDatabase database) {
        this.database = database;
    }

    public void updatePassword(UserSchema existingUser, HttpServletResponse resp) throws IOException {
        if (database != null) {
            MongoCollection<Document> user = database.getCollection("user");
            Document updatePassword = new Document("$set", new Document("password", existingUser.getPassword()));
            Document query = new Document("email", existingUser.getEmail());
            user.updateOne(query, updatePassword);

            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write("Password Reset Successfully.");

        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error connecting to the database. Please try again later.");
        }
    }

    public boolean userExistOrNot(UserSchema existingUser) {
        MongoCollection<Document> user = database.getCollection("user");

        Document emailAuthenticate = new Document("email", existingUser.getEmail());

        FindIterable<Document> emailIterable = user.find(emailAuthenticate);

        MongoCursor<Document> emailAuth = emailIterable.iterator();
        if (emailAuth.hasNext()) {
            return true;
        }
        return false;
    }
}
