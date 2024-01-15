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

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(DatabaseConnector.makeConnection("localhost", 27017)){
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            MongoDatabase database = DatabaseConnector.getDatabase();
            MongoCollection<Document> users = database.getCollection("user");
            
            Document authenticate =new Document("email",email);
            FindIterable<Document> iterable = users.find(authenticate);
            
            try(MongoCursor<Document> auth = iterable.iterator()){
                if(auth.hasNext()){
                    Document user = auth.next();
                    String storedPassword = user.getString("password");
                    
                    if(password.equals(storedPassword)){
                        resp.setStatus(HttpServletResponse.SC_OK);
                        resp.getWriter().write("Login successful. Welcome, " + user.getString("username") + "!");
                    }
                    else{
                        resp.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                        resp.getWriter().write("Invalid Credentials.");
                    }
                }
                else{
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("User does not exist.");
                }
            }
        }
        else{
            resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            resp.getWriter().write("Error connecting to the database. Please try again later.");
        }
    }

}
