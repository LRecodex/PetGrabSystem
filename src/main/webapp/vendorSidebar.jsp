<%-- 
    Document   : vendorSidebar
    Created on : 21 Jun 2023, 6:09:53 pm
    Author     : yoonj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vendor Page</title>
        
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
                        <a href="VendorServlet?action=edit">
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
                        <a href="VendorServlet?action=showListServiceByShopId&shopid=${petsessionid}">
                            <i class="fas fa-file-alt"></i>
                            <span class="nav-text">Service</span>
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
    </body>
</html>
