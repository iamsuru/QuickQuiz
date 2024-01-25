package com.quickquiz.mongodb;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.quickquiz.entities.Scores;
import com.quickquiz.entities.UserSchema;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.bson.Document;
import org.mindrot.jbcrypt.BCrypt;

public class LoginUser {

    private MongoDatabase database;
    private Document foundUser = null;

    public LoginUser(MongoDatabase database) {
        this.database = database;
    }

    public Document doAuthentication(String identifier, String password, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        UserSchema currentUser = null;
        if (database != null) {
            try {
                MongoCollection<Document> user = database.getCollection("user");

                Document emailAuthenticate = new Document("email", identifier);
                Document usernameAuthenticate = new Document("username", identifier);

                FindIterable<Document> emailIterable = user.find(emailAuthenticate);
                FindIterable<Document> usernameIterable = user.find(usernameAuthenticate);

                MongoCursor<Document> emailAuth = emailIterable.iterator();
                MongoCursor<Document> usernameAuth = usernameIterable.iterator();

                if (emailAuth.hasNext() || usernameAuth.hasNext()) {
                    foundUser = emailAuth.hasNext() ? emailAuth.next() : usernameAuth.next();
                    String storedPassword = foundUser.getString("password");
                    if (BCrypt.checkpw(password, storedPassword)) {
                        return foundUser;
                    } else {
                        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        resp.getWriter().write("Invalid Credentials.");
                        return null;
                    }
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("User does not exist.");
                    return foundUser;
                }
            } catch (Exception e) {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("Unexpected error during document insertion: " + e.getMessage());
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error connecting to the database. Please try again later.");
            return foundUser;
        }
        return foundUser;
    }

    public void ScoreCollection(UserSchema currentUser, HttpServletRequest req) {
        MongoCollection<Document> scores = database.getCollection("scores");
        Document username = new Document("username", currentUser.getUsername());
        FindIterable<Document> usernameIterable = scores.find(username);
        MongoCursor<Document> usernameCursor = usernameIterable.iterator();

        if (usernameCursor.hasNext()) {
            Document foundUser = usernameCursor.next();
            Scores currentUserScore = new Scores();
            currentUserScore.setHighest_score(foundUser.getInteger("highest_score"));
            currentUserScore.setPrevious_score(foundUser.getInteger("previous_score"));
            currentUserScore.setUsername(foundUser.getString("username"));
            System.out.println(currentUserScore.getUsername() + " " + currentUserScore.getPrevious_score() + " " + currentUserScore.getHighest_score());
            HttpSession session = req.getSession();
            session.setAttribute("userScore", currentUserScore);
        }
    }
}
