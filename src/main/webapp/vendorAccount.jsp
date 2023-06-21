<%-- 
    Document   : DriverNavbar
    Created on : 18 Jun 2023, 11:42:51 pm
    Author     : MUHAMMAD FAUZUL AZIM BIN IMRAN HAYAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            html, body {
                height: 100%;
                margin: 0;
                padding: 0;
            }

            .page-content {
                margin: 20px;
                padding: 20px;
            }

            .card-title {
                font-size: 24px;
                margin-bottom: 20px;
            }

            .card-title .fas {
                margin-right: 10px;
            }

            .form-group {
                margin-bottom: 20px;
            }

            .form-group label {
                font-weight: bold;
            }

            .form-group input {
                width: 100%;
                padding: 10px;
                font-size: 16px;
                border-radius: 5px;
                border: 1px solid #ccc;
            }
            .input-group {
                position: relative;
                display: flex;
                flex-wrap: wrap;
                align-items: stretch;
                width: 100%;
            }

            .input-group .form-select {
                flex-grow: 1;
                border-radius: 5px;
                border: 1px solid #ccc;
                padding: 10px;
                font-size: 16px;
            }

            .input-group .form-select:disabled {
                background-color: #f8f9fa;
                cursor: not-allowed;
            }
        </style>
    </head>
    <body>
       <jsp:include page="vendorSidebar.jsp"/>
        <!-- Scripts -->
        <div class="page-content">
           
         
            <div class="container my-4">
                <div class="card my-4">
                    <div class="card-body">
                        <div class="d-flex justify-content-between align-items-center mb-3">
                            <h3 class="card-title"><i class="fas fa-file"></i>Account Information</h3>
                            <button onclick="toggleEdit()" class="btn btn-primary">
                                <i class="fas fa-edit"></i> Edit
                            </button>
                        </div>
                        <form class="form-control mb-3" action="VendorServlet">                      
                                <h1>Update Vendor</h1>
                                <input type="hidden" name="action" value="update"> 
                            <div class="mb-3">
                                <input type="hidden" name="shopid" value="${vendor.shopid}">
                                <label class="form-label">Username</label>
                                <input type="text" name="username" class="form-control" value="${vendor.username}" placeholder="Service Name">
                            </div>
                                <div class="mb-3">
                                <label class="form-label">Password</label>
                                <input type="password" name="password" class="form-control" value="${vendor.password}" placeholder="Enter your password">
                            </div>
                                <div class="mb-3">
                                <label class="form-label">Shop Name</label>
                                <input type="text" name="shopname" class="form-control" value="${vendor.shopname}" placeholder="Service Name">
                            </div>
                            
                            <div class="mb-3">
                                <label class="form-label ">Address</label>
                                <input type="text" class="form-control" name="shopaddress" value="${vendor.shopaddress}" placeholder="Enter your address">
                            </div>
                                <div class="mb-3">
                                <label class="form-label">Phone</label>
                                <input type="text" class="form-control" name="phonenum" value="${vendor.phonenum}" placeholder="Phone No:0124468899">
                            </div>
                                                        
                                <button type="submit" class="btn btn-success">Save</button>
                                <button type="reset" class="btn btn-success">Cancel</button>
                        </form>
                    </div>
                </div>
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
