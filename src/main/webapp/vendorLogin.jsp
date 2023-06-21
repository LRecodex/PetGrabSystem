<%-- 
    Document   : customerLogin
    Created on : 15 Jun 2023, 7:21:12 pm
    Author     : yoonj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

    </head>
    <body>
        <!-- Start Navbar -->
        <jsp:include page="navbar.jsp" />
        <!-- End Navbar -->        
        <br><br>
        <!-- Start Content -->
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <h1 style='text-align: center'>Login Vendor</h1>
                    <form action="VendorServlet">
                        <input value="login" name="action" type="hidden">
                        <div class="mb-3">
                            <label class="form-label">Username</label>
                            <input type="text" class="form-control" name="username" placeholder="Enter your username">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Password</label>
                            <input type="password" class="form-control" name="password" placeholder="Enter your password">
                        </div>
                        <p style="color:red"><a href="vendorForm.jsp">Register</a>
                        
                        <br>
                        <div class="d-grid gap-2">
                            <button type="submit" class="btn btn-success">Login</button>                            
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </body>
</html>
