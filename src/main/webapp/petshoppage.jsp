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
            .card {
                max-width: 500px;
                margin: 0 auto;
                margin-top: 50px;
            }
        </style>
    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <br><br>

        <div class="card">
            <div class="card-body">
                <h5 class="card-title"><c:out value='${pet.shopname}'/></h5>
                <p class="card-text"><c:out value='${pet.shopaddress}'/></p>
                <p class="card-text"><c:out value='${pet.phonenum}'/></p>
                <h6 class="card-subtitle mb-2 text-muted">Services:</h6>
                <ul class="list-group list-group-flush">
                    <c:forEach var="serv" items="${service}">
                        <li> <c:out value="${serv.description}"/>&nbsp;&nbsp;&nbsp;&nbsp;RM<c:out value="${serv.price}"/></li>
                        </c:forEach>



                </ul>
            </div>
            <c:if test="${customersessionid !=null}">
                <a href="CustomerServlet?action=showBookForm&shopid=<c:out value='${pet.shopid}'/>" class="btn btn-primary btn-lg">Book Now</a>
            </c:if>
            <c:if test="${customersessionid ==null}">
                <a href="customerLogin.jsp" class="btn btn-primary btn-lg">Log In to Book Order</a>
            </c:if>
        </div>

    </body>
</html>
