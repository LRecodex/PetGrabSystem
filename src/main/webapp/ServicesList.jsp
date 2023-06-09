<%-- 
    Document   : ServicesList.jsp
    Created on : 15 Jun 2023, 6:29:40 pm
    Author     : yoonj
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vendor Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
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
         <jsp:include page="vendorSidebar.jsp"/>
        
        <br><br>
        <!-- Start Content -->
        <div class="container">
            <div class="card">
                <a href='VendorServlet?action=createServiceForm&shopid=${shopid}'>Add New Service</a>
                <div class="card-body col-md-6">
                    <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Service ID</th>                          
                            <th>Name</th>
                            <th>Description</th>
                            <th>Price</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="services" items="${serv}">
                            <tr>
                                <td>
                                    <c:out value="${services.serviceid}"/>
                                </td>                               
                                <td>
                                    <c:out value="${services.name}"/>
                                </td>
                                <td>
                                    <c:out value="${services.description}"/>
                                </td>
                                <td>
                                    <c:out value="${services.price}"/> &nbsp;&nbsp;
                                </td>
                                <td><a href="VendorServlet?action=editservice&serviceid=${services.serviceid}&shopid=${services.shopid}" class="btn btn-warning">Edit</a> &nbsp;&nbsp;&nbsp;&nbsp;<!-- comment -->
                                    <a href="VendorServlet?action=deleteservice&serviceid=${services.serviceid}&shopid=${services.shopid}" class="btn btn-secondary">Delete</a>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
                </div>
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js" integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS" crossorigin="anonymous"></script>
    </body>
</html>
