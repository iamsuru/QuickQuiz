package com.quickquiz.mongodb;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.quickquiz.helper.DatabaseConnector;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.bson.Document;

public class FetchCategoryFromDatabase {

    private MongoDatabase database;

    public FetchCategoryFromDatabase(MongoDatabase database) {
        this.database = database;
    }

    public List<Document> getQuizDataFromMongoDB(String collectionName) {
        List<Document> quizDataList = new ArrayList<>();

        MongoDatabase database = DatabaseConnector.makeConnection();
        System.out.println(database.getName());
        MongoCollection<Document> collection = database.getCollection(collectionName);
        FindIterable<Document> iterDoc = collection.find();
        Iterator<Document> it = iterDoc.iterator();

        while (it.hasNext()) {
            Document document = it.next();
            quizDataList.add(document);
        }
        return quizDataList;
    }

    public String convertListToJSON(List<Document> category) {
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
