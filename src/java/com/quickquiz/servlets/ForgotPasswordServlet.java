package com.quickquiz.servlets;

import com.quickquiz.entities.UserSchema;
import com.quickquiz.helper.DatabaseConnector;
import com.quickquiz.helper.PasswordValidator;
import com.quickquiz.mongodb.ForgotPassword;
import com.quickquiz.mongodb.RegisterUser;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;
import java.time.LocalDateTime;
import java.util.logging.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.mindrot.jbcrypt.BCrypt;

@MultipartConfig
public class ForgotPasswordServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            Logger.getLogger(LoginServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        //check user exists or not?
        UserSchema existingUser = new UserSchema(email, password);

        ForgotPassword obj = new ForgotPassword(DatabaseConnector.makeConnection());
//        if yes then update
        if (obj.userExistOrNot(existingUser)) {
            PasswordValidator validator = new PasswordValidator();
            if (validator.isValidPassword(password, resp)) {
                String salt = BCrypt.gensalt();
                String hashPassword = BCrypt.hashpw(password, salt);

                existingUser.setPassword(hashPassword);
                obj.updatePassword(existingUser, resp);
            }
        } else {//Say not exists
            resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
            resp.getWriter().write("Email does not exist " + existingUser.getEmail());
        }
    }
}
