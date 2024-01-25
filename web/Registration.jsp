<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <link rel="shortcut icon" href="./images/favicon.png" type="image/x-icon">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Registration - QuickQuiz</title>
        <link rel="stylesheet" href="./stylesheet/bootstrap.css">
        <link href="stylesheet/registration.css" rel="stylesheet" type="text/css"/>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>

    <body>
        <%@include file="Navbar.jsp" %>

        <div id="fullScreenLoader" class="full-screen-loader">
            <div class="text-center">
                <span class="loader"></span>
                <p id="paragraph" style="color: #fff;">Getting things ready!<br>Have patience...</p>
            </div>
        </div>

        <div class="container flex-container">
            <div class="form-container">
                <form id="registrationForm" method="POST" action="RegisterServlet" >
                    <div class="form-floating mb-3">
                        <input type="text" name="name" class="form-control" id="name" placeholder="" autocomplete="off" required>
                        <label for="name">Name*</label>
                    </div>

                    <div class="form-floating mb-3">
                        <input type="email" name="email" class="form-control" id="email" placeholder="" autocomplete="off" required>
                        <label for="email">Email*</label>
                        <span class="float-start ms-3 mb-3" id="emailDuplicateLabel" style="display:none; color: red;font-size: 13px">Duplicate Field</span>
                    </div>

                    <div class="form-floating mb-1">
                        <input type="text" name="username" class="form-control" id="username" placeholder="" autocomplete="off" required>
                        <label for="username">Username*</label>
                        <span class="float-start ms-3 mb-3" id="usernameDuplicateLabel" style="display:none; color: red;font-size: 13px">username already taken by someone!</span><br>
                    </div>

                    <div class="gender mb-3">
                        <label>Gender*</label> &nbsp; &nbsp; &nbsp;
                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="gender" id="male" value="Male">
                            <label class="form-check-label" for="male">Male</label>
                        </div>

                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="gender" id="Female" value="Female">
                            <label class="form-check-label" for="Female">Female</label>
                        </div>

                        <div class="form-check form-check-inline">
                            <input class="form-check-input" type="radio" name="gender" id="Other" value="Other">
                            <label class="form-check-label" for="Other">Other</label>
                        </div>
                    </div>

                    <div class="form-floating mb-3">
                        <input type="password" name="password" class="form-control" id="password" placeholder="" autocomplete="off"
                               required>
                        <label for="password">Create Password*</label>
                    </div>

                    <div class="form-floating mb-3">
                        <input type="password" name="confirm_password" class="form-control" id="confirm_password" placeholder="" autocomplete="off"
                               required>
                        <label for="confirm_password">Repeat Password*</label>
                    </div>

                    <div class="d-grid gap-2">
                        <button class="btn btn-outline-success submit-btn" type="submit">Register yourself<i style="margin-left: 5px;" class="fa fa-user-plus"></i></button>
                    </div>
                </form>
            </div>
            <%@include file="Preview.jsp" %>
        </div>
        <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
                integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>
        <script src="scripts/sweet-alert.js" type="text/javascript"></script>
        <script src="scripts/registration.js" type="text/javascript"></script>
    </body>

</html>