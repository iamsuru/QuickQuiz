<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>QuickQuiz</title>
        <link rel="stylesheet" href="./stylesheet/bootstrap.css">
        <link href="stylesheet/index.css" rel="stylesheet" type="text/css"/>
        <link rel="shortcut icon" href="./images/favicon.png" type="image/x-icon">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.min.css" integrity="sha512-DTOQO9RWCH3ppGqcWaEA1BIZOC6xxalwEsw9c2QQeAIftl+Vegovlnee1c9QX4TctnWMn13TZye+giMm8e2LwA==" crossorigin="anonymous" referrerpolicy="no-referrer" />
    </head>
    <body>
        <%@include file="Navbar.jsp" %>

        <div id="fullScreenLoader" class="full-screen-loader">
            <div class="text-center">
                <span class="loader"></span>
                <p id="paragraph"></p>
            </div>
        </div>

        <div style="margin-top: 40px !important;" class="container flex-container">
            <%@include file="Preview.jsp" %>
            <div class="container form-container">
                <form id="forgotPasswordForm" method="post" action="ForgotPasswordServlet">
                    <div class="form-floating mb-3">
                        <input type="email" name="email" class="form-control" id="email" placeholder="" autocomplete="off" required="true">
                        <label for="email">Email*</label>
                    </div>

                    <div class="form-floating mb-3">
                        <input type="password" name="password" class="form-control" id="password" placeholder="" autocomplete="off"
                               required>
                        <label for="password">New Password*</label>
                    </div>

                    <div class="form-floating mb-3">
                        <input type="password" name="confirm_password" class="form-control" id="confirm_password" placeholder="" autocomplete="off"
                               required>
                        <label for="confirm_password">Repeat Password*</label>
                    </div>

                    <div class="d-grid gap-2">
                        <button class="btn btn-outline-success submit-btn" type="submit">Change Password<i style="margin-left: 5px;" class="fa fa-key"></i></button>
                    </div>

                    <div class="mt-3 forgot">
                        <a style="color:blue;" href="#">Forgot Password?</a>
                        <hr style="height:2px; width:auto; border-width:0;background-color:rgb(255, 255, 255)">
                    </div>
                </form>
                <span>Don't have an account? &nbsp;<a href="registration">Create</a></span>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.7.1.min.js" integrity="sha256-/JqT3SQfawRcv/BIHPThkBvs0OEvtFFmqPF/lYI/Cxo=" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"
                integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.min.js"
                integrity="sha384-BBtl+eGJRgqQAUMxJ7pMwbEyER4l1g+O15P+16Ep7Q9Q+zqX6gSbd85u4mG4QzX+"
        crossorigin="anonymous"></script>
        <script src="scripts/sweet-alert.js" type="text/javascript"></script>
        <script src="scripts/forgot_password.js" type="text/javascript"></script>
    </body>
</html>
