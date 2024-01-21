package com.quickquiz.helper;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bson.Document;

public class FetchCategoryFromDatabase extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String collectionName = req.getParameter("categoryChoice");
        collectionName = collectionName.toLowerCase();
        System.out.println(collectionName);
        List<Document> category = getQuizDataFromMongoDB(collectionName);

        String categoryJSON = convertListToJSON(category);
        resp.setContentType("application/json");
        resp.getWriter().write(categoryJSON);
    }

    private List<Document> getQuizDataFromMongoDB(String collectionName) {
        List<Document> quizDataList = new ArrayList<>();

        MongoDatabase database = DatabaseConnector.makeConnection();
        System.out.println(database.getName());
        MongoCollection<Document> collection = database.getCollection(collectionName);
        FindIterable<Document> iterDoc = collection.find();
        Iterator<Document> it = iterDoc.iterator();

        while (it.hasNext()) {
            Document document = it.next();
//            System.out.println(document);
            quizDataList.add(document);
        }
//        System.out.println(quizDataList);

        return quizDataList;
    }

    private String convertListToJSON(List<Document> category) {
        System.out.println(category);
        StringBuilder jsonBuilder = new StringBuilder("[");
        for (Document d : category) {
            jsonBuilder.append(d.toJson()).append(",");
        }
        if (category.size() > 0) {
            jsonBuilder.deleteCharAt(jsonBuilder.length() - 1); // Remove the last comma
        }
        jsonBuilder.append("]");
        return jsonBuilder.toString();
    }
}
