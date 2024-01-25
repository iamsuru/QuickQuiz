package com.quickquiz.servlets;

import com.quickquiz.helper.DatabaseConnector;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.quickquiz.entities.UserSchema;
import com.quickquiz.mongodb.LoginUser;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bson.Document;
import org.mindrot.jbcrypt.BCrypt;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        String identifier = req.getParameter("loginIdentifier");
        String password = req.getParameter("password");

        LoginUser user = new LoginUser(DatabaseConnector.makeConnection());

        Document foundUser = user.doAuthentication(identifier, password, req, resp);
        if (foundUser != null) {
            resp.setStatus(HttpServletResponse.SC_OK);
            //getting details
            UserSchema currentUser = new UserSchema();

            currentUser.setName(foundUser.getString("name"));
            currentUser.setEmail(foundUser.getString("email"));
            currentUser.setUsername(foundUser.getString("username"));
            currentUser.setGender(foundUser.getString("gender"));

            if (currentUser != null) {
                //collectiong scores
                user.ScoreCollection(currentUser, req);
                HttpSession session = req.getSession();
                session.setAttribute("currentUser", currentUser);
            }
            resp.getWriter().write("Login successful. Welcome, " + currentUser.getName() + "!");
        }
    }
}
