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

public class SetCategoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter p = resp.getWriter();
        MongoDatabase database = DatabaseConnector.makeConnection();
        MongoCollection<Document> collection = database.getCollection("sports quizzes");


//        collection.insertMany(quizData);
        p.println("inserted");
    }

    private static Document createQuestion(String question, String[] choices, String correctAnswer) {
        return new Document("question", question)
                .append("choices", Arrays.asList(choices))
                .append("correct_answer", correctAnswer);
    }
}

//sprts
//        List<Document> quizData = Arrays.asList(
//                createQuestion("In basketball, what is the 'Triangle Offense,' and which legendary coach popularized its use?",
//                        new String[]{"Phil Jackson", "Pat Riley", "Gregg Popovich", "Red Auerbach"}, "Phil Jackson"),
//                createQuestion("Which athlete holds the record for the most gold medals won in a single Olympic Games, and in which year did this remarkable feat occur?",
//                        new String[]{"Michael Phelps (2008)", "Usain Bolt (2012)", "Larisa Latynina (1964)", "Simone Biles (2016)"}, "Larisa Latynina (1964)"),
//                createQuestion("Who was the first cricketer to achieve the rare feat of scoring a triple century in Test cricket, and against which team did this historic innings take place?",
//                        new String[]{"Sir Don Bradman (Australia)", "Brian Lara (England)", "Virender Sehwag (South Africa)", "Chris Gayle (Pakistan)"}, "Brian Lara (England)"),
//                createQuestion("Which Formula 1 driver holds the record for the most Grand Prix victories in a single season, and in which year did this record-breaking performance occur?",
//                        new String[]{"Ayrton Senna (1988)", "Michael Schumacher (2004)", "Lewis Hamilton (2019)", "Sebastian Vettel (2013)"}, "Lewis Hamilton (2019)"),
//                createQuestion("Among male golfers, who holds the record for the most major championships won, and how many majors does he have to his name?",
//                        new String[]{"Tiger Woods (15)", "Jack Nicklaus (18)", "Rory McIlroy (5)", "Phil Mickelson (6)"}, "Jack Nicklaus (18)"),
//                createQuestion("Margaret Court is one of the greatest female tennis players in history. How many Grand Slam singles titles did she win throughout her career?",
//                        new String[]{"21", "24", "18", "16"}, "24"),
//                createQuestion("Which country won the FIFA World Cup in 1966, and who was the top goal scorer of the tournament?",
//                        new String[]{"Brazil (Pele)", "England (Geoff Hurst)", "West Germany (Gerd Muller)", "Italy (Luigi Riva)"}, "England (Geoff Hurst)"),
//                createQuestion("In the sport of figure skating, who is the only skater to have achieved a perfect score of 6.0 from all judges in every segment of a competition, and in which year did this remarkable accomplishment occur?",
//                        new String[]{"Yuzuru Hanyu (2018)", "Katarina Witt (1988)", "Michelle Kwan (2002)", "Torvill and Dean (1984)"}, "Torvill and Dean (1984)"),
//                createQuestion("What is the current world record for the men's marathon, who set it, and in which marathon did this record-breaking run take place?",
//                        new String[]{"Eliud Kipchoge (2:01:39, Berlin Marathon 2018)", "Kenenisa Bekele (2:01:41, Berlin Marathon 2019)", "Haile Gebrselassie (2:03:59, London Marathon 2008)", "Dennis Kipruto Kimetto (2:02:57, Berlin Marathon 2014)"},
//                        "Dennis Kipruto Kimetto (2:02:57, Berlin Marathon 2014)"),
//                createQuestion("Who was the first Major League Baseball player to achieve 500 career home runs, and which team was he playing for when he reached this milestone?",
//                        new String[]{"Babe Ruth (New York Yankees)", "Willie Mays (San Francisco Giants)", "Hank Aaron (Milwaukee Braves)", "Barry Bonds (San Francisco Giants)"}, "Hank Aaron (Milwaukee Braves)")
//        );

//Gk
//        MongoCollection<Document> collection = database.getCollection("general knowledge quizzes");
//        List<Document> quizData = Arrays.asList(
//                createQuestion("What is the largest desert in the world by area?",
//                        new String[]{"Sahara Desert", "Gobi Desert", "Antarctic Desert", "Arabian Desert"},
//                        "Antarctic Desert"),
//                createQuestion("Which chemical element has the symbol 'Au' on the periodic table?",
//                        new String[]{"Silver", "Gold", "Platinum", "Uranium"},
//                        "Gold"),
//                createQuestion("Who is the only U.S. president to have served more than two terms in office?",
//                        new String[]{"Franklin D. Roosevelt", "John F. Kennedy", "Ronald Reagan", "George Washington"},
//                        "Franklin D. Roosevelt"),
//                createQuestion("In what year did the European Union (EU) adopt the Euro as its official currency?",
//                        new String[]{"1999", "2001", "2004", "2007"},
//                        "1999"),
//                createQuestion("Which river is the longest in the world?",
//                        new String[]{"Nile River", "Amazon River", "Yangtze River", "Mississippi River"},
//                        "Amazon River"),
//                createQuestion("Who discovered penicillin?",
//                        new String[]{"Alexander Fleming", "Louis Pasteur", "Marie Curie", "Jonas Salk"},
//                        "Alexander Fleming"),
//                createQuestion("Which mountain is the highest peak in North America?",
//                        new String[]{"Mount McKinley (Denali)", "Mount Everest", "Mount Elbrus", "Mount Kilimanjaro"},
//                        "Mount McKinley (Denali)"),
//                createQuestion("In what year did the Berlin Airlift take place during the Cold War?",
//                        new String[]{"1945", "1948", "1953", "1961"},
//                        "1948"),
//                createQuestion("What is the capital city of South Korea?",
//                        new String[]{"Beijing", "Tokyo", "Seoul", "Hanoi"},
//                        "Seoul"),
//                createQuestion("Who was the first woman to win a Nobel Prize?",
//                        new String[]{"Marie Curie", "Jane Goodall", "Rosalind Franklin", "Mother Teresa"},
//                        " Marie Curie")
//        );
//createQuestion("", 
//                        new String[]{"","","",},
//                        ""),
//Geography
//        MongoCollection<Document> collection = database.getCollection("geography & places quizzes");
//        List<Document> quizData = Arrays.asList(
//                createQuestion("Which two countries are separated by the Strait of Gibraltar?",
//                        new String[]{"Spain and Morocco", "Italy and Tunisia", "Greece and Turkey", "France and Algeria"},
//                        "Spain and Morocco"),
//                createQuestion("What is the highest active volcano in the world?",
//                        new String[]{"Mount St. Helens", "Mount Vesuvius", "Cotopaxi", "Mount Etna"},
//                        "Mount Etna"),
//                createQuestion("Which African country is the most populous?",
//                        new String[]{"Nigeria", "Ethiopia", "Egypt", "South Africa"},
//                        "Nigeria"),
//                createQuestion("Lake Baikal, the deepest freshwater lake in the world, is located in which country?",
//                        new String[]{"Russia", "Canada", "Mongolia", "Kazakhstan"},
//                        "Russia"),
//                createQuestion("The city of Petra, known for its rock-cut architecture, is located in which modern-day country?",
//                        new String[]{"Jordan", "Egypt", "Lebanon", "Iraq"},
//                        "Jordan"),
//                createQuestion("What is the capital city of Kazakhstan?",
//                        new String[]{"Astana", "Tashkent", "Almaty", "Bishkek"},
//                        "Astana"),
//                createQuestion("In which ocean is the Mariana Trench, the deepest part of the world's oceans?",
//                        new String[]{"Atlantic Ocean", "Indian Ocean", "Southern Ocean", "Pacific Ocean"},
//                        "Pacific Ocean"),
//                createQuestion("The ancient city of Machu Picchu is located in which South American country?",
//                        new String[]{"Brazil", "Peru", "Colombia", "Ecuador"},
//                        "Peru"),
//                createQuestion("What is the capital city of Iceland?",
//                        new String[]{"Helsinki", "Reykjavik", "Oslo", "Copenhagen"},
//                        "Reykjavik"),
//                createQuestion("The Great Victoria Desert is located in which country?",
//                        new String[]{"Australia", "South Africa", "Argentina", "Saudi Arabia"},
//                        "Australia")
//        );
