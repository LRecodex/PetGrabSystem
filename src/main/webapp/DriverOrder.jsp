<%-- 
    Document   : DriverOrder
    Created on : 19 Jun 2023, 12:32:26 am
    Author     : MUHAMMAD FAUZUL AZIM BIN IMRAN HAYAT
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                            <th>Status</th>
                            <th>Picture</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%-- Add your table data here --%>
                        <tr>
                            <td>1</td>
                            <td>1001</td>
                            <td>2001</td>
                            <td>3001</td>
                            <td>Max</td>
                            <td>5</td>
                            <td>Male</td>
                            <td>Checkup</td>
                            <td>Completed</td>
                            <td><img src="path_to_image.jpg" alt="Pet Picture" width="100"></td>
                        </tr>
                        <tr>
                            <td>2</td>
                            <td>1002</td>
                            <td>2002</td>
                            <td>3002</td>
                            <td>Bella</td>
                            <td>3</td>
                            <td>Female</td>
                            <td>Vaccination</td>
                            <td>Pending</td>
                            <td><img src="path_to_image.jpg" alt="Pet Picture" width="100"></td>
                        </tr>
                        <%-- Add more rows as needed --%>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>