<%-- 
    Document   : vendorForm
    Created on : 17 Jun 2023, 3:06:57 pm
    Author     : Hp
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vendor Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">

    </head>
    <body>
        <jsp:include page="navbar.jsp"/>
        <br><br>
        <!-- Start Content -->
        <div class="container col-md-5">
            <div class="card">
                

                    <c:if test="${vendor ==null}">
                        <form class="form-control mb-3" action="VendorServlet">  
                            <input value="insert" name="action" type="hidden">
                            <h1>Create Vendor</h1>
                        </c:if>
                        <c:if test="${vendor !=null}">
                            <form class="form-control mb-3" action="update">                      
                                <h1>Update Vendor</h1>
                            </c:if>
                                
                            <div class="mb-3">
                                <label class="form-label">Username</label>
                                <input type="text" name="username" class="form-control" placeholder="Service Name">
                            </div>
                                <div class="mb-3">
                                <label class="form-label">Password</label>
                                <input type="password" name="password" class="form-control" placeholder="Enter your password">
                            </div>
                                <div class="mb-3">
                                <label class="form-label">Shop Name</label>
                                <input type="text" name="shopname" class="form-control" placeholder="Service Name">
                            </div>
                            
                            <div class="mb-3">
                                <label class="form-label ">Address</label>
                                <input type="text" class="form-control" name="shopaddress" placeholder="Enter your address">
                            </div>
                                <div class="mb-3">
                                <label class="form-label">Phone</label>
                                <input type="text" class="form-control" name="phonenum" placeholder="Phone No:0124468899">
                            </div>
                                                        
                                <button type="submit" class="btn btn-success">Save</button>
                                <button type="reset" class="btn btn-success">Cancel</button>
                        </form>

                
            </div>
        </div>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js" integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS" crossorigin="anonymous"></script>
    </body>
</html>
