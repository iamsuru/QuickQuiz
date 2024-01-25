<%@page import="com.quickquiz.entities.UserSchema" %>
<%@page import="com.quickquiz.entities.Scores" %>
<%@page errorPage="error" %>
<%
    UserSchema user = (UserSchema)session.getAttribute("currentUser");
    if(user==null){
        response.sendRedirect("/QuickQuiz");
        return;
    }
    Scores currentUserScore = (Scores)session.getAttribute("userScore");
    System.out.println("jsp " + currentUserScore.getUsername()+ " " + currentUserScore.getPrevious_score() + " " + currentUserScore.getHighest_score());
%>

<%@page contentType="text/html" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>QuickQuiz</title>
        <link rel="shortcut icon" href="./images/favicon.png" type="image/x-icon">
        <link rel="stylesheet" href="./stylesheet/bootstrap.css">
        <link href="stylesheet/homepanel.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>


    <body>

        <header>
            <nav class="navbar navbar-expand-sm">
                <div class="container">
                    <a class="navbar-brand" href="/QuickQuiz">
                        <h1>QuickQuiz</h1>
                    </a>
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                            data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false"
                            aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse justify-content-end" id="navbarNavAltMarkup">
                        <div class="navbar-nav">
                            <a class="nav-link active" aria-current="page" href="home-panel"><i style="margin-right: 5px" class="fa-solid fa-house-user"></i>Home</a>
                            <a class="nav-link active" aria-current="page" id="currentUsername"><i style="margin-right: 5px" class="fa-solid fa-circle-user"></i><%= user.getUsername()%></a>
                            <a class="nav-link active" aria-current="page" href="LogoutServlet"><i style="margin-right: 5px" class="fa fa-sign-out"></i>Sign out</a>
                        </div>
                    </div>
                </div>
            </nav>
        </header>
        <div id="fullScreenLoader" class="full-screen-loader">
            <div class="text-center">
                <span class="loader"></span>
                <p id="paragraph" style="color: #fff;">Getting things ready!<br>Have patience...</p>
            </div>
        </div>
        <main>
            <div class="container category mt-5 mb-5">
                <div class="row">
                    <div class="col-md-6 d-flex justify-content-center align-items-center">
                        <button class="btnn" style="background-color: #E90E0E;">Geography & Places Quizzes</button>
                    </div>

                    <div class="col-md-6 d-flex justify-content-center align-items-center">
                        <button class="btnn" style="background-color: #9ddd16;">General Knowledge Quizzes</button>
                    </div>

                    <div class="col-md-6 d-flex justify-content-center align-items-center">
                        <button class="btnn" style="background-color: #36DBA9;">Sports Quizzes</button>
                    </div>

                    <div class="col-md-6 d-flex justify-content-center align-items-center">
                        <button class="btnn" style="background-color: #CF18B2;">History Quizzes</button>
                    </div>

                    <div class="col-md-6 d-flex justify-content-center align-items-center">
                        <button class="btnn" style="background-color: #287B76;">Music Quizzes</button>
                    </div>

                    <div class="col-md-6 d-flex justify-content-center align-items-center">
                        <button class="btnn" style="background-color: #13DFBA;">Food & Drink Quizzes</button>
                    </div>

                    <div class="col-md-6 d-flex justify-content-center align-items-center">
                        <button class="btnn" style="background-color: #E0871E;">Science & Nature Quizzes</button>
                    </div>

                    <div class="col-md-6 d-flex justify-content-center align-items-center">
                        <button class="btnn" style="background-color: #23C751;">Word Play Quizzes</button>
                    </div>

                    <div class="col-md-6 d-flex justify-content-center align-items-center">
                        <button class="btnn" style="background-color: #FFC46C;">Computer Quizzes</button>
                    </div>

                    <div class="col-md-6 d-flex justify-content-center align-items-center">
                        <button class="btnn" style="background-color: #6E9BF0;">Pot Luck Quizzes</button>
                    </div>
                </div>
            </div>

            <div class="info_box">
                <div class="info-title"><span>Some Rules of this Quiz</span></div>
                <div class="info-list">
                    <div class="info">1. You will have only <span> 30 seconds </span> to answer question.</div>
                    <div class="info">2. Once you select your answer, it can't be undone.</div>
                    <div class="info">3. You can't select any option once time goes off.</div>
                    <div class="info">4. You'll get points on the basis of your correct answers.</div>
                </div>
                <div class="buttons">
                    <button class="btn btn-outline-primary" id="exitBtn">Exit Quiz</button>
                    <button class="btn btn-primary" id="hideRuleBoxBtn">Continue</button>
                </div>
            </div>

            <div class="container main-display mt-5">
                <div id="quiz">
                    <h5><span id="countDown"></span></h5>
                    <p id="question" class="mt-5"></p>
                    <div class="row">
                        <div class="col-sm-6 d-flex justify-content-center">
                            <button class="options-btn" id="btn0"><span id="choice0"></span></button>
                        </div>
                        <div class="col-sm-6 d-flex justify-content-center">
                            <button class="options-btn" id="btn1"><span id="choice1"></span></button>
                        </div>
                        <div class="col-sm-6 d-flex justify-content-center">
                            <button class="options-btn" id="btn2"><span id="choice2"></span></button>
                        </div>
                        <div class="col-sm-6 d-flex justify-content-center">
                            <button class="options-btn" id="btn3"><span id="choice3"></span></button>
                        </div>
                    </div>

                    <footer class="fixed-bottom">
                        <hr>
                        <p id="progress">Question 0 of 5</p>
                    </footer>
                </div>
            </div>

            <div class="result_box">
                <div class="info-title d-flex justify-content-center">
                    <h2>Score Table</h2>
                </div>
                <div class="info-list d-flex flex-column align-items-center">
                    <div class="info">
                        <h5 style="color: green;">Highest Score <span id="highestScore"><%= currentUserScore.getHighest_score()%></span>
                            <sup id="sup" class="d-none" style="font-size: 15px; background-clip: text; color: transparent; background-image: linear-gradient(to right, yellow, red); display: inline;">(Updated!)</sup>
                        </h5>
                    </div>
                    <div class="info">
                        <h5 style="color: #E99506;">Current Score <span id="currentScore"></span></h5>
                    </div>
                    <div class="info">
                        <h5 style="color: red;">Previous Score <span id="previousScore"><%= currentUserScore.getPrevious_score()%></span></h5>
                    </div>
                </div>
                <div class="buttons">
                    <button class="btn btn-primary" id="btnToHomePanel">Continue</button>
                </div>
            </div>


        </main>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
                integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>
        <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
        <script src="scripts/sweet-alert.js" type="text/javascript"></script>
        <script src="scripts/home-panel.js" type="text/javascript"></script>
    </body>

</html>