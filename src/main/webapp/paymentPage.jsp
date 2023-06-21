<%-- 
    Document   : paymentPage
    Created on : 20 Jun 2023, 10:50:37 pm
    Author     : yoonj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Base64"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <style>
            .qr-code-img {
                max-width: 100%;
            }
        </style>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <br><br>
        <div class="container">
            <div class="row">
                <div class="col-md-4">
                    <h2 class="text-center">Payment QR Code</h2>
                    <img src="images/paymentqr.jpg" alt="Payment QR Code" class="qr-code-img mx-auto d-block">
                </div>
                <div class="col-md-8">
                    <h2 class="text-center">Order History</h2>
                    <table class="table table-bordered table-hover">
                        <thead class="thead-dark">
                            <tr>
                                <th>Order ID</th>
                                <th>Customer ID</th>
                                <th>Driver ID</th>
                                <th>Shop ID</th>
                                <th>Pet Name</th>
                                <th>Pet Age</th>
                                <th>Pet Gender</th>
                                <th>Purpose of Visit</th>
                                <th>Time</th>
                                <th>Date</th>
                                <th>Status</th>
                                <th>Picture</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="order" items="${list}">
                                <%-- Add your table data here --%>
                                <tr>
                                    td><c:out value="${order.orderid}" /></td>
                                    <td><c:out value="${order.custid}" /></td>
                                    <td><c:out value="${order.driverid}" /></td>
                                    <td><c:out value="${order.shopid}" /></td>
                                    <td><c:out value="${order.petname}" /></td>
                                    <td><c:out value="${order.petage}" /></td>
                                    <td><c:out value="${order.petgender}" /></td>
                                    <td><c:out value="${order.purposeofvisit}" /></td>
                                    <td><c:out value="${order.time}" /></td>
                                    <td><c:out value="${order.date}" /></td>
                                    <td><c:out value="${order.status}" /></td>
                                    <td>
                                        <c:set var="base64Picture" value="${order.getBase64Picture()}"/>
                                        <c:if test="${not empty base64Picture}">
                                            <img src="data:image/png;base64,${base64Picture}" width="100" height="100" />
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                </div>
            </div>
        </div>
    </body>
</html>
