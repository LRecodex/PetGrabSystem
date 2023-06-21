<%-- 
    Document   : invalid
    Created on : 19 Jun 2023, 2:01:31 pm
    Author     : yoonj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>INVALID</title>
        <script>
            setTimeout(function () {
                window.location.href = "homepage.jsp";
            }, 5000); // Redirect after 5 seconds
        </script>
    </head>
    <body>
        <h2>Invalid</h2>
        <p>Invalid username or password. Redirecting back to the login page...</p>
    </body>
</body>
</html>
