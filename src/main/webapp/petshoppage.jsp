<%-- 
    Document   : petshoppage
    Created on : 19 Jun 2023, 1:25:35 am
    Author     : yoonj
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Shop</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
        <style>
            .card-body{
                margin: 5%;
            }
            .card{
                margin-left: auto;
                margin-right: auto;
                margin-bottom: 5%;
            }
            .clr{
                padding: 5%;
                background-color: #555;
            }
        </style>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <br><br>

        <div class="container">
            <div class="card clr">
                <div class="row">
                <div class="card">  
                    <h3>Shop Name :<c:out value='${pet.shopname}'/></h3>
                    <h4>Shop Address:<c:out value='${pet.shopaddress}'/></h4>
                    </div>
                    <div class="card">
                        <ul>
                            <c:forEach var="serv" items="${service}">
                                <li> <c:out value="${serv.description}"/>&nbsp;&nbsp;&nbsp;&nbsp;RM<c:out value="${serv.price}"/></li>
                                </c:forEach>
                            <p>Contact Shop : <c:out value='${pet.phonenum}'/></p>

                            <c:if test="${customersessionid !=null}">
                                <a href="CustomerServlet?action=showBookForm&shopid=<c:out value='${pet.shopid}'/>" class="btn btn-primary btn-lg">Book Now</a>
                            </c:if>
                            <c:if test="${customersessionid ==null}">
                                <a href="customerLogin.jsp" class="btn btn-primary btn-lg">Log In to Book Order</a>
                            </c:if>
                        </ul>
                    </div>
                </div>
            </div>                           
        </div>
    </body>
</html>
