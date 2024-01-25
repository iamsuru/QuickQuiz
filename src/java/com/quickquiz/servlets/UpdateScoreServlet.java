/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.quickquiz.servlets;

import com.quickquiz.entities.Scores;
import com.quickquiz.entities.UserSchema;
import com.quickquiz.helper.DatabaseConnector;
import com.quickquiz.mongodb.UpdateUserScore;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.http.*;

@MultipartConfig
public class UpdateScoreServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            // Parse parameters to the appropriate data type
            int score = Integer.parseInt(req.getParameter("score"));
            int previousScore = Integer.parseInt(req.getParameter("previousScore"));
            int highestScore = Integer.parseInt(req.getParameter("highestScore"));
            String username = req.getParameter("username");

            if (score > highestScore) {
                highestScore = score;
            }
            previousScore = score;

            Scores userScore = new Scores(username, previousScore, highestScore);

            UpdateUserScore updateUserScore = new UpdateUserScore(DatabaseConnector.makeConnection());
            updateUserScore.updateScore(userScore, req, resp);
        } catch (NumberFormatException e) {
            // Handle the case where parsing fails (e.g., if the parameters are not numeric)
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid numeric parameters");
        }
    }

}
