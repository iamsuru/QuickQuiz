/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.quickquiz.servlets;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.quickquiz.helper.DatabaseConnector;
import java.io.*;
import java.util.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.bson.Document;

public class HomePanelServlet extends HttpServlet {

    private String selectedCategory;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html lang=\"en\">");

            out.println("<head>");
            out.println("<meta charset=\"UTF-8\">");
            out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
            out.println("<title>QuickQuiz</title>");
            out.println("<link rel=\"shortcut icon\" href=\"./images/favicon.png\" type=\"image/x-icon\">");
            out.println("<link rel=\"stylesheet\" href=\"./stylesheet/bootstrap.css\">");
            out.println("<script src=\"scripts/home-panel.js\" type=\"text/javascript\"></script>");
            out.println("<link href=\"stylesheet/homepanel.css\" rel=\"stylesheet\" type=\"text/css\"/>");
            out.println("</head>");

            out.println("<body>");

            out.println("<header>");
            out.println("<nav class=\"navbar navbar-expand-lg\">");
            out.println("<div class=\"container\">");
            out.println("<a class=\"navbar-brand\" href=\"/QuickQuiz\">");
            out.println("<h1>QuickQuiz</h1>");
            out.println("</a>");
            out.println("<button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\"");
            out.println("data-bs-target=\"#navbarNavAltMarkup\" aria-controls=\"navbarNavAltMarkup\" aria-expanded=\"false\"");
            out.println("aria-label=\"Toggle navigation\">");
            out.println("<span class=\"navbar-toggler-icon\"></span>");
            out.println("</button>");
            out.println("<div class=\"collapse navbar-collapse justify-content-end\" id=\"navbarNavAltMarkup\">");
            out.println("<div class=\"navbar-nav\">");
            out.println("<a class=\"nav-link active\" aria-current=\"page\" href=\"home-panel\">Home</a>");
            out.println("<a class=\"nav-link active\" aria-current=\"page\"><%= currentUser.getUsername() %></a>");
            out.println("<a class=\"nav-link active\" aria-current=\"page\" href=\"LogoutServlet\">Sign out</a>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
            out.println("</nav>");
            out.println("</header>");

            out.println("<main>");
            out.println("<div class=\"container category mt-5 mb-5\">");
            out.println("<div class=\"row\">");

            out.println(generateQuizButton("Geography & Places Quizzes", "#E90E0E"));
            out.println(generateQuizButton("General Knowledge Quizzes", "#9ddd16"));
            out.println(generateQuizButton("Sports Quizzes", "#36DBA9"));
            out.println(generateQuizButton("History Quizzes", "#CF18B2"));
            out.println(generateQuizButton("Music Quizzes", "#287B76"));
            out.println(generateQuizButton("Food & Drink Quizzes", "#13DFBA"));
            out.println(generateQuizButton("Science & Nature Quizzes", "#E0871E"));
            out.println(generateQuizButton("Word Play Quizzes", "#23C751"));
            out.println(generateQuizButton("Computer Quizzes", "#FFC46C"));
            out.println(generateQuizButton("Pot Luck Quizzes", "#6E9BF0"));
            out.println("</div>");
            out.println("</div>");

            out.println("<div class=\"info_box\">");
            out.println("<div class=\"info-title\"><span>Some Rules of this Quiz</span></div>");
            out.println("<div class=\"info-list\">");
            out.println("<div class=\"info\">1. You will have only <span> 30 seconds </span> to answer question.</div>");
            out.println("<div class=\"info\">2. Once you select your answer, it can't be undone.</div>");
            out.println("<div class=\"info\">3. You can't select any option once time goes off.</div>");
            out.println("<div class=\"info\">4. You'll get points on the basis of your correct answers.</div>");
            out.println("</div>");
            out.println("<div class=\"buttons\">");
            out.println("<button class=\"btn btn-outline-primary\" id=\"exitBtn\">Exit Quiz</button>");
            out.println("<button class=\"btn btn-primary\" id=\"hideRuleBoxBtn\">Continue</button>");
            out.println("</div>");
            out.println("</div>");

            //getting questions ans showing them
            List<Document> data = getQuizDataFromMongoDB("geography & places quizzes");

            Random random = new Random();

            Document randomQuestion = data.get(random.nextInt(data.size()));

            // Extract question, choices, and correct answer from the document
            if (selectedCategory != null) {
                System.out.println(selectedCategory);
                String question = randomQuestion.getString("question");
                List<String> choices = (List<String>) randomQuestion.get("choices");
                String correctAnswer = randomQuestion.getString("correct_answer");
                out.println("<div class=\"container main-display mt-5\">");
                out.println("<div id=\"quiz\">");
                out.println("<h5><span id=\"countDown\"></span></h5>");
                out.println("<p id=\"question\" class=\"mt-5\">" + question + "</p>");
                out.println("<div class=\"row\">");
                for (int i = 0; i < choices.size(); i++) {
                    out.println("<div class=\"col-sm-6 d-flex justify-content-center\">");
                    out.println("<button class=\"options-btn\" id=\"btn" + i + "\"><span id=\"choice" + i + "\">" + choices.get(i) + "</span></button>");
                    out.println("</div>");
                }
                out.println("</div>");
                out.println("<footer class=\"fixed-bottom\">");
                out.println("<hr>");
                out.println("<p id=\"progress\">Question x of y</p>");
                out.println("</footer>");
                out.println("</div>");
                out.println("</div>");
            } else {
                System.out.println("not selected");
            }

            out.println("<div class=\"result_box\">");
            out.println("<div class=\"info-title d-flex justify-content-center\">");
            out.println("<h2>Score Table</h2>");
            out.println("</div>");
            out.println("<div class=\"info-list d-flex flex-column align-items-center\">");
            out.println("<div class=\"info\">");
            out.println("<h5 style=\"color: green;\">Highest Score <span>50</span></h5>");
            out.println("</div>");
            out.println("<div class=\"info\">");
            out.println("<h5 style=\"color: #E99506;\">Current Score <span>30</span></h5>");
            out.println("</div>");
            out.println("<div class=\"info\">");
            out.println("<h5 style=\"color: red;\">Previous Score <span>20</span></h5>");
            out.println("</div>");
            out.println("</div>");
            out.println("<div class=\"buttons\">");
            out.println("<button class=\"btn btn-primary\" id=\"btnToHomePanel\">Continue</button>");
            out.println("</div>");
            out.println("</div>");

            out.println("</main>");
            out.println("<script src=\"https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js\" integrity=\"sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r\" crossorigin=\"anonymous\"></script>");

            out.println("<script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js\" integrity=\"sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+\" crossorigin=\"anonymous\"></script>");

            out.println("</body>");
            out.println("</html>");
        }
    }

    private String generateQuizButton(String buttonText, String backgroundColor) {
        return "<div class=\"col-md-6 d-flex justify-content-center align-items-center\">"
                + "<button class=\"btnn\" style=\"background-color: " + backgroundColor + ";\">" + buttonText + "</button>"
                + "</div>";
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

            // Add the document to the list
            quizDataList.add(document);
        }
//        System.out.println(quizDataList);

        return quizDataList;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        this.selectedCategory = request.getParameter("categoryChoice");
        if (selectedCategory != null) {
            this.selectedCategory = selectedCategory.toLowerCase();
            response.getWriter().write("got " + selectedCategory);
        } else {
            System.out.println("Category not selected server");
        }
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
