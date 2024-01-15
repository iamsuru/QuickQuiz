package com.servlet;

import com.mongodb.DatabaseConnector;
import com.mongodb.MongoWriteException;
import com.mongodb.client.*;
import com.mongodb.userSchema;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import com.mongodb.client.result.*;
import org.bson.Document;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (DatabaseConnector.makeConnection("localhost", 27017)) {
            String name = req.getParameter("name");
            String email = req.getParameter("email");
            String username = req.getParameter("username");
            String gender = req.getParameter("gender");
            String password = req.getParameter("password");
            String confirm_password = req.getParameter("confirm_password");
            MongoDatabase database = DatabaseConnector.getDatabase();
            MongoCollection<Document> users = database.getCollection("user");

            userSchema newUser = new userSchema();
            newUser.setName(name);
            newUser.setEmail(email);
            newUser.setUsername(username);
            newUser.setGender(gender);
            newUser.setPassword(password);

            Document userDocument = new Document()
                    .append("name", newUser.getName())
                    .append("email", newUser.getEmail())
                    .append("username", newUser.getUsername())
                    .append("gender", newUser.getGender())
                    .append("password", newUser.getPassword());

            try {
                users.insertOne(userDocument);
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("Registered Successfully");
            } catch (MongoWriteException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("Error during document insertion: " + e.getMessage());
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
