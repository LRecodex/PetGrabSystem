<%-- 
    Document   : customerForm
    Created on : 15 Jun 2023, 6:29:40 pm
    Author     : yoonj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        <br><br>
        <!-- Start Content -->
        <div class="container col-md-5">
            <div class="card-body">
                <c:if test="${customer ==null}">
                    <form class="form-control mb-3" action="CustomerServlet">   
                        <input value="insert" name="action" type="hidden">
                        <h1>Register</h1>
                    </c:if>
                    <c:if test="${customer !=null}">
                        <form class="form-control mb-3" action="CustomerServlet">
                            <input value="update" name="action" type="hidden">
                            <input value="${customer.custid}" name="custid" type="hidden">
                            <h1>Update</h1>
                        </c:if>
                        <div class="mb-3">
                            <label class="form-label">Username</label>
                            <input type="text" name="username" class="form-control" value="${customer.username}" placeholder="Create your username">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Password</label>
                            <input type="password" class="form-control" name="password" value="${customer.password}" placeholder="Create your password">
                        </div>
                        <div class="mb-3">
                            <label class="form-label ">Name</label>
                            <input type="text" class="form-control" name="name" value="${customer.name}" placeholder="Enter your name">
                        </div>
                        <div class="mb-3">
                            <label class="form-label ">Email address</label>
                            <input type="email" class="form-control" name="email" value="${customer.email}" placeholder="name@example.com">
                        </div>
                        <div class="mb-3">
                            <label class="form-label">Address</label>
                            <textarea class="form-control" name="address" value="${customer.address}" rows="3"></textarea>
                        </div>
                        <div class="mb-3">
                            <label class="form-label ">Phone Number</label>
                            <input type="text" name="phonenum" class="form-control" value="${customer.phonenum}" placeholder="e.g:012-3456789">
                        </div>
                        <button type="submit" class="btn btn-success">Save</button>
                        <button type="reset" class="btn btn-success">Cancel</button>
                    </form>

            </div>

        </div>
    </body>
</html>
