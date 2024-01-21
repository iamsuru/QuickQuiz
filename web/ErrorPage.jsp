<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page isErrorPage="true" %>
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
            <img src="./images/warning.png" alt="error_image!"class="img-fluid"/>
            <h3 class="mt-2 display-5">Error! <br>Something Went Wrong.<br><%=  exception %></h3>

            <a href="/QuickQuiz" class="btn btn-outline-primary mt-2">Home</a>
        </div>
    </body>
</html>
