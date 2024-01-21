<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page errorPage="ErrorPage.jsp"%>
<%@page import="com.quickquiz.entities.UserSchema" %>
<%
    UserSchema currentUser = (UserSchema)session.getAttribute("currentUser");
    if(currentUser != null){
        response.sendRedirect("home-panel");
    }
%>
<!DOCTYPE html>
<html lang="en">

    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>QuickQuiz</title>
        <link rel="stylesheet" href="./stylesheet/bootstrap.css">
        <link href="stylesheet/index.css" rel="stylesheet" type="text/css"/>
        <link rel="shortcut icon" href="./images/favicon.png" type="image/x-icon">
    </head>

    <body>
        <%@include file="Navbar.jsp" %>

        <div id="fullScreenLoader" class="full-screen-loader">
            <div class="text-center">
                <span class="loader"></span>
                <p id="paragraph"></p>
            </div>
        </div>

        <div class="container flex-container">
            <%@include file="Preview.jsp" %>
            <div class="container form-container">
                <form id="LoginForm" method="post" action="DoAuthenticate">
                    <div class="form-floating mb-3">
                        <input type="text" name="loginIdentifier" class="form-control" id="email" placeholder="" autocomplete="off" required="true">
                        <label for="email">Email or Username</label>
                    </div>

                    <div class="form-floating mb-3">
                        <input type="password" name="password" class="form-control" id="password" placeholder="" autocomplete="off" required="true">
                        <label for="password">Password</label>
                    </div>

                    <div class="d-grid gap-2">
                        <button class="btn btn-outline-primary submit-btn" type="submit">Login</button>
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
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script>
            $(document).ready(function () {
                $('#LoginForm').submit(function (event) {
                    event.preventDefault();
                    $('#fullScreenLoader').css('display', 'flex');
                    var formData = $(this).serialize(); // Serialize form data
                    $.ajax({
                        type: 'POST',
                        url: 'LoginServlet',
                        data: formData,
                        success: function (data, textStatus, jqXHR) {
                            $('#fullScreenLoader').css('display', 'none');
                            if (jqXHR.status === 200) {
                                let timerInterval;
                                Swal.fire({
                                    position: "center",
                                    icon: "success",
                                    showConfirmButton: false,
                                    text: data,
                                    timer: 3000,
                                    didOpen: () => {
                                        timerInterval = setInterval(() => {

                                        }, 100);
                                    },
                                    willClose: () => {
                                        clearInterval(timerInterval);
                                    }
                                }).then((result) => {
                                    if (result.dismiss === Swal.DismissReason.timer) {

                                        $('#paragraph').text("Redirecting...");
                                        $('#fullScreenLoader').css('display', 'flex');
                                        setTimeout(function () {
                                            $('#fullScreenLoader').css('display', 'none');
                                            $('#paragraph').text("");
                                            window.location = "home-panel";
                                        }, 1000);
                                    }
                                })
                            } else {
                                let timerInterval;
                                Swal.fire({
                                    position: "center",
                                    icon: "error",
                                    showConfirmButton: false,
                                    text: data,
                                    timer: 3000,
                                    didOpen: () => {
                                        timerInterval = setInterval(() => {

                                        }, 100);
                                    },
                                    willClose: () => {
                                        clearInterval(timerInterval);
                                    }
                                })
                            }
                        },
                        error: function (jqXHR, textStatus, errorThrown) {
                            $('#fullScreenLoader').css('display', 'none');
                            if (jqXHR.status === 401) {
                                let timerInterval;
                                Swal.fire({
                                    position: "center",
                                    icon: "error",
                                    showConfirmButton: false,
                                    text: jqXHR.responseText,
                                    timer: 3000,
                                    didOpen: () => {
                                        timerInterval = setInterval(() => {

                                        }, 100);
                                    },
                                    willClose: () => {
                                        clearInterval(timerInterval);
                                    }
                                })
                            } else if (jqXHR.status === 404) {
                                let timerInterval;
                                Swal.fire({
                                    position: "center",
                                    icon: "warning",
                                    showConfirmButton: false,
                                    text: jqXHR.responseText,
                                    timer: 3000,
                                    didOpen: () => {
                                        timerInterval = setInterval(() => {

                                        }, 100);
                                    },
                                    willClose: () => {
                                        clearInterval(timerInterval);
                                    }
                                })
                            } else if (jqXHR.status === 500) {
                                let timerInterval;
                                Swal.fire({
                                    position: "center",
                                    icon: "error",
                                    showConfirmButton: false,
                                    text: jqXHR.responseText,
                                    timer: 3000,
                                    didOpen: () => {
                                        timerInterval = setInterval(() => {

                                        }, 100);
                                    },
                                    willClose: () => {
                                        clearInterval(timerInterval);
                                    }
                                })
                            }
                        }
                    });
                });
            });
        </script>

    </body>

</html>