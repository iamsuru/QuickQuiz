package com.quickquiz.helper;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class DatabaseConnector {

    private static final String MONGO_HOST = "localhost";
    private static final int MONGO_PORT = 27017;

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static MongoDatabase makeConnection() {
        try {
            if (mongoClient == null) {
                mongoClient = new MongoClient(MONGO_HOST, MONGO_PORT);
                database = mongoClient.getDatabase("QuickQuiz");
            }
            return database;
        } catch (Exception e) {
            return null;
        }
    }

    public static void closeConnection() {
        mongoClient.close();
    }
}
