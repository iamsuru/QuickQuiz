<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>QuickQuiz</title>
    <link rel="shortcut icon" href="./images/favicon.png" type="image/x-icon">
    <link rel="stylesheet" href="./stylesheet/bootstrap.css">
    <style>
        body {
            background-color: rgb(25, 24, 51) !important;
        }

        .navbar {
            background-color: rgba(0, 0, 0, 0.2);
        }

        .nav-link {
            color: #fff !important;
        }

        .navbar-toggler {
            background-color: aliceblue;
        }

        @font-face {
            font-family: "Lemon-Regular";
            src: url("./fonts/Lemon-Regular.ttf");
        }

        h1 {
            font-family: "Lemon-Regular" !important;
            color: #fff !important;
            font-size: 30px !important;
        }

        /*main tag*/

        .category {
            display: flex;
            justify-content: center;
        }

        .btnn {
            width: 100%;
            height: 70px;
            margin: 10px 50px 10px 50px;
            border: 2px solid black;
            border-radius: 5px;
            font-size: 17px;
            cursor: pointer;
            font-weight: 700;
        }

        .btnn:hover {
            background-color: #008000 !important;
            color: aliceblue !important;
        }

        /*Rule box*/

        .info_box,
        .result_box {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19);
        }

        .info_box.activeInfo,
        .result_box.activeInfo {
            opacity: 1;
            z-index: 5;
            pointer-events: auto;
            transform: translate(-50%, -50%) scale(1);
        }

        .info_box,
        .result_box {
            width: 540px;
            background: #fff;
            border-radius: 5px;
            transform: translate(-50%, -50%) scale(0.9);
            opacity: 0;
            pointer-events: none;
            transition: all 0.3s ease;
        }

        .info_box .info-title,
        .result_box .info-title {
            height: 60px;
            width: 100%;
            border-bottom: 1px solid lightgrey;
            display: flex;
            align-items: center;
            padding: 0 30px;
            border-radius: 5px 5px 0 0;
            font-size: 20px;
            font-weight: 600;
        }

        .info_box .info-list,
        .result_box .info-list {
            padding: 15px 30px;
        }

        .info_box .info-list .info,
        .result_box .info-list.info {
            margin: 5px 0;
            font-size: 17px;
        }

        .info_box .info-list .info span,
        .result_box .info-list .inf span {
            font-weight: 600;
            color: #3a90df;
        }

        .info_box .buttons,
        .result_box .buttons {
            height: 60px;
            display: flex;
            align-items: center;
            justify-content: flex-end;
            padding: 0 30px;
            border-top: 1px solid lightgrey;
        }

        .info_box .buttons button,
        .result_box .buttons button {
            margin: 0 5px;
            height: 40px;
            width: 100px;
            font-size: 16px;
            font-weight: 500;
            cursor: pointer;
            border: none;
            outline: none;
            border-radius: 5px;
            border: 1px solid #3a90df;
            transition: all 0.3s ease;
        }

        .btn {
            width: fit-content !important;
            display: flex;
            justify-content: center;
            align-items: center;
        }

        /* Responsive adjustments */
        @media (max-width: 768px) {

            .info_box,
            .result_box {
                width: 80%;
            }

            .info_box .info-title,
            .result_box .info-title {
                font-size: 18px;
            }

            .info_box .info-list .info {
                font-size: 14px;
            }

            .info_box .buttons button {
                height: 30px;
                width: 80px;
                font-size: 14px;
            }

            #question {
                font-size: 15px !important;
            }

            .options-btn {
                font-size: 10px !important;
            }
        }

        /* @media (max-width: 576px) {
  .info_box {
    width: 90%;
  }

  .info_box .info-title {
    font-size: 16px;
  }

  .info_box .info-list .info {
    font-size: 12px;
  }

  .info_box .buttons button {
    height: 25px;
    width: 70px;
    font-size: 12px;
  }

  #question {
    font-size: 15px !important;
  }

  .options-btn {
    font-size: 10px !important;
  }
} */

        h5 {
            color: aliceblue;
            text-align: center !important;
        }

        .main-display {
            display: none;
            justify-content: center;
            align-items: center;
            height: 65vh;
        }

        .options-btn {
            background-color: #3a90df;
            width: 250px;
            font-size: 15px;
            color: #fff;
            border: 1px solid #1d3c6a;
            margin: 10px 0px 10px 0px;
            padding: 10px 10px;
            border-radius: 5px;
        }

        #question {
            font-size: 20px;
            color: aliceblue;
            text-align: center;
        }

        #progress {
            color: #c22853;
            font-size: 18px;
            text-align: center;
        }

        hr {
            border: 1px solid #fff;
            margin-top: 50px;
        }

        #countDown {
            color: green;
        }

        F
    </style>
</head>


<body>
    <header>
        <nav class="navbar navbar-expand-lg">
            <div class="container">
                <a class="navbar-brand" href="#">
                    <h1>QuickQuiz</h1>
                </a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse"
                    data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false"
                    aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse justify-content-end" id="navbarNavAltMarkup">
                    <div class="navbar-nav">
                        <a class="nav-link active" aria-current="page" href="#">Home</a>
                        <a class="nav-link" href="#">Sign Out</a>
                    </div>
                </div>
            </div>
        </nav>
    </header>

    <main>
        <div class="container category mt-5 mb-5">
            <div class="row">
                <div class="col-md-6 d-flex justify-content-center align-items-center">
                    <button class="btnn" style="background-color: #E90E0E;">Geography & Places
                        Quizzes</button>
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
                        <button class="options-btn" id="btn0"><span id="choice0">Option A</span></button>
                    </div>
                    <div class="col-sm-6 d-flex justify-content-center">
                        <button class="options-btn" id="btn1"><span id="choice1">Option A</span></button>
                    </div>
                    <div class="col-sm-6 d-flex justify-content-center">
                        <button class="options-btn" id="btn2"><span id="choice2">Option A</span></button>
                    </div>
                    <div class="col-sm-6 d-flex justify-content-center">
                        <button class="options-btn" id="btn3"><span id="choice3">Option A</span></button>
                    </div>
                </div>

                <footer class="fixed-bottom">
                    <hr>
                    <p id="progress">Question x of y</p>
                </footer>
            </div>
        </div>

        <div class="result_box">
            <div class="info-title d-flex justify-content-center">
                <h2>Score Table</h2>
            </div>
            <div class="info-list d-flex flex-column align-items-center">
                <div class="info">
                    <h5 style="color: green;">Highest Score <span>50</span></h5>
                </div>
                <div class="info">
                    <h5 style="color: #E99506;">Current Score <span>30</span></h5>
                </div>
                <div class="info">
                    <h5 style="color: red;">Previous Score <span>20</span></h5>
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
    <script src="./scripts/script.js"></script>
</body>

</html>