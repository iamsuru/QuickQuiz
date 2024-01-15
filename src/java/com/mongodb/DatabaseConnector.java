package com.mongodb;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoDatabase;

public class DatabaseConnector {

    private static final String DATABASE_NAME = "QuickQuiz";
    private static String MONGO_HOST;
    private static int MONGO_PORT;

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static boolean makeConnection(String MONGO_HOST, int MONGO_PORT) {
        mongoClient = new MongoClient(MONGO_HOST, MONGO_PORT);
        database = mongoClient.getDatabase(DATABASE_NAME);
        return true;
    }

    public static MongoDatabase getDatabase() {
        return database;
    }

    public static void closeConnection() {
        mongoClient.close();
    }
}
