<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error!!!</title>
        <link href="stylesheet/bootstrap.css" rel="stylesheet" type="text/css"/>
        <link href="stylesheet/errorpage.css" rel="stylesheet" type="text/css"/>
        <link rel="shortcut icon" href="./images/favicon.png" type="image/x-icon">
    </head>
    <body>
        <div class="container d-flex flex-column justify-content-center align-items-center vh-100 text-center">
            <img src="./images/warning.png" alt="error_image!" class="img-fluid"/>
            <h3 class="mt-2 display-3">Error!</h3>
            <h4 class="mt-2 display-4">Something Went Wrong.</h4>
            <% if (exception == null) { %>
            <h6 class="mt-2 mb-3 display-5">Page not Found</h6>
            <% } else { %>
            <h6><%= exception.getMessage() %></h6>
            <% } %>

            <a href="/QuickQuiz" class="btn btn-outline-primary mt-2" role="button"><span class="span">Go to Home</span></a>
        </div>
    </body>
</html>

<!--
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class MongoClientConnectionExample {
    public static void main(String[] args) {
        String connectionString = "mongodb+srv://iamsuru:<password>@quickquiz.iisckrr.mongodb.net/?retryWrites=true&w=majority";


        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .build();

        // Create a new client and connect to the server
        try (MongoClient mongoClient = MongoClients.create(settings)) {
            try {
                // Send a ping to confirm a successful connection
                MongoDatabase database = mongoClient.getDatabase("admin");
                database.runCommand(new Document("ping", 1));
                System.out.println("Pinged your deployment. You successfully connected to MongoDB!");
            } catch (MongoException e) {
                e.printStackTrace();
            }
        }
    }
}-->
