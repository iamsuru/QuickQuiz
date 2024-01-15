package com.servlet;

import com.mongodb.DatabaseConnector;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import org.bson.Document;
import org.mindrot.jbcrypt.BCrypt;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (DatabaseConnector.makeConnection("localhost", 27017)) {
            String loginIdentifier = req.getParameter("loginIdentifier");
            String password = req.getParameter("password");
            MongoDatabase database = DatabaseConnector.getDatabase();
            MongoCollection<Document> users = database.getCollection("user");

            Document emailAuthenticate = new Document("email", loginIdentifier);
            Document usernameAuthenticate = new Document("username", loginIdentifier);

            FindIterable<Document> emailIterable = users.find(emailAuthenticate);
            FindIterable<Document> usernameIterable = users.find(usernameAuthenticate);

            try (MongoCursor<Document> emailAuth = emailIterable.iterator(); MongoCursor<Document> usernameAuth = usernameIterable.iterator()) {
                if (emailAuth.hasNext() || usernameAuth.hasNext()) {
                    Document user = emailAuth.hasNext() ? emailAuth.next() : usernameAuth.next();
                    String storedPassword = user.getString("password");
                    if (BCrypt.checkpw(password, storedPassword)) {
                        resp.setStatus(HttpServletResponse.SC_OK);
                        resp.getWriter().write("Login successful. Welcome, " + user.getString("username") + "!");
                    } else {
                        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        resp.getWriter().write("Invalid Credentials.");
                    }
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("User does not exist.");
                }
            }
        } else {
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error connecting to the database. Please try again later.");
        }
    }

}
