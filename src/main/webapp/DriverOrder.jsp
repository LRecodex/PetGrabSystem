<%-- 
    Document   : DriverOrder
    Created on : 19 Jun 2023, 12:32:26 am
    Author     : MUHAMMAD FAUZUL AZIM BIN IMRAN HAYAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Base64"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Table Example</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            html, body {
                height: 100%;
                margin: 0;
                padding: 0;
            }
            .page-content {
                margin-left: 250px;
                padding: 20px;
            }
            table {
                width: 100%;
                margin-bottom: 1rem;
                color: #212529;
                background-color: #fff;
                border-collapse: collapse;
            }

            table th,
            table td {
                padding: 0.75rem;
                vertical-align: top;
                border-top: 1px solid #dee2e6;
            }

            table thead th {
                vertical-align: bottom;
                border-bottom: 2px solid #dee2e6;
            }
            table tbody tr:hover {
                background-color: #f5f5f5;
            }
        </style>
    </head>
    <body>
        <%@include file="DriverNavbar.jsp" %>
        <div class="page-content">
            <div class="container-fluid">
                <h1>List of Order</h1><hr>
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
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%-- Add your table data here --%>
                        <c:forEach var="order" items="${list}">
                            <tr>
                                <td><c:out value="${order.orderid}" /></td>
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
                                <td id="action-${order.orderid}">
                                    <c:choose>
                                        <c:when test="${order.status eq 'Delivered'}">
                                            <form enctype="multipart/form-data" action="DriverController?action=addproof" method="post">
                                                <div class="form-group">
                                                    <input type="hidden" class="form-control" name="orderid" value="<c:out value="${order.orderid}"/>" readonly>
                                                </div>
                                                <div class="form-group">
                                                    <label for="fileInput-${order.orderid}">Choose a file:</label>
                                                    <input type="file" class="form-control-file" id="fileInput-${order.orderid}" name="file" required>
                                                </div>
                                                <button type="submit" class="btn btn-primary">Submit</button>
                                            </form> 
                                        </c:when>
                                        <c:otherwise>
                                            <c:choose>
                                                <c:when test="${order.status eq 'Accepted'}">
                                                    <a href="DriverController?action=delivered&orderid=${order.orderid}&driverid=${sesi.driverid}" class="btn btn-success">Delivered</a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="DriverController?action=accept&orderid=${order.orderid}&driverid=${sesi.driverid}" class="btn btn-primary">Accept</a>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>

                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
