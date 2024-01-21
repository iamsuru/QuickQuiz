package com.quickquiz.servlets;

import com.mongodb.client.MongoDatabase;
import com.quickquiz.entities.UserSchema;
import com.quickquiz.helper.DatabaseConnector;
import com.quickquiz.mongodb.RegisterUser;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.io.*;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.mindrot.jbcrypt.BCrypt;

public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        String name = req.getParameter("name");
        String email = req.getParameter("email");
        String username = req.getParameter("username");
        String gender = req.getParameter("gender");
        String password = req.getParameter("password");
        String salt = BCrypt.gensalt();
        String hashPassword = BCrypt.hashpw(password, salt);

        UserSchema newUser = new UserSchema(name, email, username, gender, hashPassword, LocalDateTime.now());

        RegisterUser user = new RegisterUser(DatabaseConnector.makeConnection());

        user.registerUser(newUser, resp);
    }
}
