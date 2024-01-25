package com.quickquiz.mongodb;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.quickquiz.entities.Scores;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import org.bson.Document;

public class UpdateUserScore {

    private MongoDatabase database;

    public UpdateUserScore(MongoDatabase database) {
        this.database = database;
    }

    public void updateScore(Scores userScore, HttpServletRequest req, HttpServletResponse resp) throws IOException {
        if (database != null) {
            MongoCollection<Document> scores = database.getCollection("scores");
            Document updateScores = new Document("$set", new Document("highest_score", userScore.getHighest_score()).append("previous_score", userScore.getPrevious_score()));

            Document query = new Document("username", userScore.getUsername());

            scores.updateOne(query, updateScores);
            HttpSession session = req.getSession();
            session.removeAttribute("userScore");

            session.setAttribute("userScore", userScore);
            resp.getWriter().write("Updated");
        }
    }
}
