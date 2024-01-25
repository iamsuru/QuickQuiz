package com.quickquiz.helper;

import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class DatabaseConnector {

    private static String url = "mongodb+srv://iamsuru:2YlzVqFU8jZ8leQs@cluster0.lybaan6.mongodb.net/?retryWrites=true&w=majority";
    private static MongoClientSettings setting = MongoClientSettings.builder()
            .applyConnectionString(new ConnectionString(url))
            .build();

    private static MongoClient mongoClient;
    private static MongoDatabase database;

    public static MongoDatabase makeConnection() {
        try {
            if (mongoClient == null) {
                mongoClient = MongoClients.create(setting);
                database = mongoClient.getDatabase("QuickQuiz");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return database;
    }

//    private static final String MONGO_HOST = "localhost";
//    private static final int MONGO_PORT = 27017;
//
//    private static MongoClient mongoClient;
//    private static MongoDatabase database;
//
//    public static MongoDatabase makeConnection() {
//        try {
//            if (mongoClient == null) {
//                mongoClient = new MongoClient(MONGO_HOST, MONGO_PORT);
//                database = mongoClient.getDatabase("QuickQuiz");
//            }
//            return database;
//        } catch (Exception e) {
//            return null;
//        }
//    }
//
    public static void closeConnection() {
        mongoClient.close();
    }
}
