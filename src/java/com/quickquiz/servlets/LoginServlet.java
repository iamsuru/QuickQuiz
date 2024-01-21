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

//        try {
//            Thread.sleep(5000);
//        } catch (InterruptedException ex) {
//            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
//        }
        String identifier = req.getParameter("loginIdentifier");
        String password = req.getParameter("password");

        LoginUser user = new LoginUser(DatabaseConnector.makeConnection());

        user.doAuthentication(identifier, password, req, resp);
    }
}
