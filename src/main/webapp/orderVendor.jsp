

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Pet Grab Vendor</title>

        <!-- Google Font: Source Sans Pro -->
        <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Source+Sans+Pro:300,400,400i,700&display=fallback">
        <!-- Font Awesome -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/css/bootstrap.min.css">
        <style>
            html, body {
                height: 100%;
                margin: 0;
                padding: 0;
            }

            .sidebar {
                height: 100%;
                width: 250px;
                position: fixed;
                top: 0;
                left: 0;
                background-color: yellow;
                overflow-x: hidden;
                padding-top: 20px;
                transition: width 0.3s;
            }

            .sidebar:hover {
                width: 280px;
                
            }

            .sidebar-header {
                padding: 10px 20px;
            }

            .sidebar-header strong {
                color: #333;
                font-size: 1.5rem;
                font-weight: 600;
            }

            .sidebar ul.components {
                padding: 20px 0;
            }

            .sidebar ul li {
                margin-bottom: 10px;
            }

            .sidebar ul li a {
                display: flex;
                align-items: center;
                color: #555;
                padding: 10px 20px;
                text-decoration: none;
            }

            .sidebar ul li a i {
                margin-right: 10px;
                font-size: 1.2rem;
            }

            .sidebar ul li a:hover {
                background-color: yellowgreen;
                color: #333;
            }

            .sidebar.active {
                width: 280px;
            }

            .page-content {
                margin-left: 250px;
                padding: 20px;
            }
        </style>
    </head>
    <body>
        <!-- Sidebar -->
        <div class="sidebar">
            <nav id="sidebar">
                <div class="sidebar-header">
                    <img src="Image/pet grab.png" alt="Logo" class="rounded-circle" width="50px"><!-- Logo -->
                    <a href="#">
                        <strong>Pet Grab</strong>
                    </a>
                </div>
                <ul class="list-unstyled components">
                    <li>
                        <a href="vendorMain.jsp">
                            <i class="fas fa-tachometer-alt"></i>
                            <span class="nav-text">Dashboard</span>
                        </a>
                    </li>
                    <li>
                        <a href="vendorAccount.jsp">
                            <i class="fas fa-user"></i>
                            <span class="nav-text">Account</span>
                        </a>
                    </li>
                    <li>
                        <a href="VendorServlet?action=showOrder">
                            <i class="fas fa-file-invoice-dollar"></i>
                            <span class="nav-text">Order</span>
                        </a>
                    </li>
                    
                    <li>
                        <a href="DriverController?action=logout">
                            <i class="fas fa-sign-out-alt"></i>
                            <span class="nav-text">Sign Out</span>
                        </a>
                    </li>
                </ul>
            </nav>
        </div>
        <!-- Scripts -->
        
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
                        
                        <c:forEach var="order" items="${list}">
                        <%-- Add your table data here --%>
                        <tr>
                            <td><c:out value="${order.orderid}"/></td>
                            <td><c:out value="${order.custid}"/></td>
                            <td><c:out value="${order.driverid}"/></td>
                            <td><c:out value="${order.shopid}"/></td>
                            <td><c:out value="${order.petname}"/></td>
                            <td><c:out value="${order.petage}"/></td>
                            <td><c:out value="${order.petgender}"/></td>
                            <td><c:out value="${order.purposeofvisit}"/></td>
                            <td><c:out value="${order.time}"/></td>
                            <td><c:out value="${order.date}"/></td>
                            <td><c:out value="${order.status}"/></td>
                            <td><img src="path_to_image.jpg" alt="Pet Picture" width="100"></td>
                            <td><a href="VendorServlet?action=accept&orderid=<c:out value='${order.orderid}'/>" >Accept</a>&nbsp;&nbsp;&nbsp;&nbsp;
                                <a href="VendorServlet?action=decline&orderid=<c:out value='${order.orderid}'/>" >Decline</a></td>
                        </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        
        <!-- Scripts -->
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap/5.3.0/js/bootstrap.bundle.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#sidebarCollapse').on('click', function () {
                    $('.sidebar').toggleClass('active');
                });
            });
        </script>
    </body>
</html>
