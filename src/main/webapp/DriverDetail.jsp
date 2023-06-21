<%-- 
    Document   : DriverDetail
    Created on : 21 Jun 2023, 12:04:51 pm
    Author     : yoonj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

        <style>
            /* Add custom styles here */
            .jumbotron {
                background-image: url('images/logo.png');
                background-size: cover;
                background-position: center;
                color: white;
                padding: 100px;
            }

            .services {
                background-color: #f5f5f5;
                padding: 50px;
            }

            .services .icon {
                font-size: 48px;
                color: #ff6b6b;
                margin-bottom: 20px;
            }
            .c{
                text-align: center;
            }
            .display-4 {
                font-size: calc(1.475rem + 2.7vw);
                font-weight: 300;
                line-height: 1.2;
                color: black;
            }
            .container1{

            }
            table, th ,tr{
                
                text-align: center;
                border: 1px solid black;
            }
            table{
                margin-left: auto;
                margin-right: auto;
                width: 60%;
                
            }
            
        </style>
    </head>
    <body>
        <jsp:include page="navbar.jsp" />
        <br><br>
        
        <div class="row">
            <div class="container">
                <h3 class="text-center">Driver's Details For Order ID: <c:out value="${orderid}"/></h3>                
                <br>
                <c:if test="${driver.driverid ==0}">
                    <h1 class='text-center'>Order is not ready</h1>
                </c:if>
                <c:if test="${driver.driverid != 0}">
                    <table>
                        
                        <tr>
                            <th>Name</th>
                            <th><c:out value="${driver.name}"/></th>
                        </tr>
                        <tr>
                            <th>Email</th>
                            <th><c:out value="${driver.email}"/></th>
                        </tr>
                        <tr>
                            <th>Phone Number</th>
                            <th><c:out value="${driver.phonenum}"/></th>
                        </tr>
                        <tr>
                            <th>No Plate</th>
                            <th><c:out value="${driver.noplate}"/></th>
                        </tr>
                    </table>
                </c:if>
                
                <br>
                <table style="border:0px" > 
                    <tr style="border:0px">
                    <th style="border:0px">
                    <a class="btn btn-warning" href="CustomerServlet?action=showOrder" >Back</a>
                    </th>
                    </tr>
                </table>
                
            </div>
        </div>
    </body>
</html>
