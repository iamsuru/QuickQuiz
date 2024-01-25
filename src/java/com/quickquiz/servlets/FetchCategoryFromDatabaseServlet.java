package com.quickquiz.servlets;

import com.quickquiz.helper.DatabaseConnector;
import com.quickquiz.mongodb.FetchCategoryFromDatabase;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.List;
import org.bson.Document;

public class FetchCategoryFromDatabaseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String collectionName = req.getParameter("categoryChoice");
        collectionName = collectionName.toLowerCase();

        FetchCategoryFromDatabase obj = new FetchCategoryFromDatabase(DatabaseConnector.makeConnection());

        List<Document> quizData = obj.getQuizDataFromMongoDB(collectionName);

        String categoryJSON = obj.convertListToJSON(quizData);

        resp.setContentType("application/json");
        resp.getWriter().write(categoryJSON);
    }

}
