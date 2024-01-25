package com.quickquiz.servlets;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.quickquiz.helper.DatabaseConnector;
import java.io.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.bson.Document;

public class QuestionUploader extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter p = resp.getWriter();
        p.println("started");
        MongoDatabase database = DatabaseConnector.makeConnection();
        p.println(database);
        MongoCollection<Document> collection = database.getCollection("pot luck quizzes");
        List<Document> quizData = Arrays.asList(
               createQuestion(
                        "Who painted the Mona Lisa?",
                        new String[]{"Vincent van Gogh", "Leonardo da Vinci", "Pablo Picasso", "Claude Monet"},
                        "Leonardo da Vinci"
                ),
                createQuestion(
                        "What is the capital city of Australia?",
                        new String[]{"Sydney", "Melbourne", "Canberra", "Brisbane"},
                        "Canberra"
                ),
                createQuestion(
                        "Which planet is known as the 'Red Planet'?",
                        new String[]{"Earth", "Mars", "Venus", "Jupiter"},
                        "Mars"
                ),
                createQuestion(
                        "Who wrote the play 'Romeo and Juliet'?",
                        new String[]{"William Shakespeare", "Jane Austen", "Charles Dickens", "F. Scott Fitzgerald"},
                        "William Shakespeare"
                ),
                createQuestion(
                        "In what year did the Titanic sink?",
                        new String[]{"1912", "1905", "1921", "1933"},
                        "1912"
                ),
                createQuestion(
                        "What is the largest ocean on Earth?",
                        new String[]{"Atlantic Ocean", "Indian Ocean", "Southern Ocean", "Pacific Ocean"},
                        "Pacific Ocean"
                ),
                createQuestion(
                        "Which famous scientist developed the theory of general relativity?",
                        new String[]{"Isaac Newton", "Albert Einstein", "Stephen Hawking", "Galileo Galilei"},
                        "Albert Einstein"
                ),
                createQuestion(
                        "What is the main ingredient in guacamole?",
                        new String[]{"Tomato", "Avocado", "Onion", "Lime"},
                        "Avocado"
                ),
                createQuestion(
                        "Who is the lead vocalist of the band 'Queen'?",
                        new String[]{"Freddie Mercury", "Bono", "Chris Martin", "Mick Jagger"},
                        "Freddie Mercury"
                ),
                createQuestion(
                        "What is the currency of Japan?",
                        new String[]{"Yuan", "Won", "Ringgit", "Yen"},
                        "Yen"
                )
        );
        collection.insertMany(quizData);
        p.println("inserted");
    }

    private static Document createQuestion(String question, String[] choices, String correctAnswer) {
        return new Document("question", question)
                .append("choices", Arrays.asList(choices))
                .append("correct_answer", correctAnswer);
    }
}