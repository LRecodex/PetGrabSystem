<%-- 
    Document   : serviceForm
    Created on : 17 Jun 2023, 3:15:51 pm
    Author     : Hp
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
        <!-- Navbar -->
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
                        <a href="VendorServlet?action=showService">
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
        <!-- End Navbar -->
        <br><br>
        <!-- Start Content -->
        <div class="container">
            <div class="card">
                <div class="card-body col-md-6">              
                        <form class="form-control mb-3" action="ServiceServlet">  
                             <input type="hidden" name="action" value="insert">
                            <h1>Create Service</h1>                                                                      
                                <input type="hidden" name="shopid" value="${petsessionid}" >                            
                            <div class="mb-3">
                                <label class="form-label">Name</label>
                                <input type="text" class="form-control" name="name" placeholder="Service Name">
                            </div>
                            <div class="mb-3">
                                <label class="form-label">Description</label>
                                <input type="text" class="form-control" name="description" placeholder="Service Detail">
                            </div>
                            <div class="mb-3">
                                <label class="form-label ">Price</label>
                                <input type="text" class="form-control" name="price" placeholder="Price Needed">
                            </div>
                                <button type="submit" class="btn btn-success">Save</button>
                                <button type="reset" class="btn btn-success">Cancel</button>
                        </form>

                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js" integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS" crossorigin="anonymous"></script>
    </body>
</html>
