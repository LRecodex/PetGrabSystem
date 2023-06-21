<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <style>
            /* Custom Navbar Styles */
            .navbar {
                background-color: #333;
                padding: 10px;
            }
            .navbar-brand {
                color: #fff;
                font-size: 24px;
                font-weight: bold;
            }
            .navbar-brand:hover {
                color: #a1cdf1;
            }
            .navbar-nav .nav-link {
                color: #fff;
                margin-right: 10px;
            }
            .navbar-nav .nav-link:hover {
                color: #a1cdf1;
            }
            .dropdown-menu {
                background-color: #333;
            }
            .dropdown-item {
                color: #fff;
            }
            .dropdown-item:hover {
                background-color: #a1cdf1;
            }
        </style>
    </head>
    <body>
        <nav class="navbar navbar-expand-lg">
            <div class="container-fluid">
                <a class="navbar-brand" href="http://localhost:8080/PetGrabSystem/homepage.jsp">PetGrab</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarScroll" aria-controls="navbarScroll" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarScroll">
                    <ul class="navbar-nav me-auto my-2 my-lg-0 navbar-nav-scroll" style="--bs-scroll-height: 100px;">
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="http://localhost:8080/PetGrabSystem/homepage.jsp">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="http://localhost:8080/PetGrabSystem/aboutus.jsp">About</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="http://localhost:8080/PetGrabSystem/Customer?action=listPetShop">Service</a>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Driver
                            </a>
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="http://localhost:8080/PetGrabSystem/Authentication.jsp">Log in</a></li>
                            </ul>
                        </li>
                         <c:if test="${customer ==null}">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Get Started!
                            </a>
                            
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="http://localhost:8080/PetGrabSystem/customerForm.jsp">Sign up</a></li>
                                <li><a class="dropdown-item" href="http://localhost:8080/PetGrabSystem/customerLogin.jsp">Log in</a></li>
                            </ul>
                        </li>
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                Vendor
                            </a>
                            
                            <ul class="dropdown-menu">
                                <li><a class="dropdown-item" href="http://localhost:8080/PetGrabSystem/vendorForm.jsp">Sign up</a></li>
                                <li><a class="dropdown-item" href="http://localhost:8080/PetGrabSystem/vendorLogin.jsp">Log in</a></li>
                            </ul>
                        </li>
                        </c:if>
                        <c:if test="${customer !=null}">
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="CustomerServlet?action=logout">Logout</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" aria-current="page" href="CustomerServlet?action=showOrder">My Order</a>
                        </li>
                        </c:if>
                        
                    </ul>
             
                </div>
            </div>
        </nav>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js" integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS" crossorigin="anonymous"></script>
    </body>
</html>
