package com.controller;

import petgrab.dao.CustomerDAO;
import petgrab.dao.PetShopDAO;
import com.model.Customer;
import com.model.Order;
import com.model.PetShop;
import com.model.Service;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;


@WebServlet("/Customer")
public class CustomerServlet extends HttpServlet {

    private CustomerDAO custDAO;
    private PetShopDAO petshopDAO;
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    public void init() {
        custDAO = new CustomerDAO();
        petshopDAO = new PetShopDAO();
        
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");

        try {
            switch (action) {
                case "new":
                    registerForm(request, response);
                    break;
                case "insert":
                    insertCustomer(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateCustomer(request, response);
                    break;
                case "listPetShop":
                    listPetShop(request, response);
                    break;
                case "listPetShopById":
                    listPetShopById(request, response);
                    break;
                case "login":
                    processLogin(request, response);
                    break;
                case "logout":
                    processLogout(request, response);
                    break;
                case "showBookForm":
                    showBookForm(request, response);
                    break;
                    case "book":
                    insertBooking(request, response);
                    break;
                    case "showOrder":
                    showOrder(request, response);
                    break;
                default:
                    showHomePage(request, response);
                    break;

            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    private void showOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();        
        int custid = (int) session.getAttribute("customersessionid");
        
        List <Order> listOrder= custDAO.selectOrderByCustId(custid);
        request.setAttribute("list", listOrder);
        RequestDispatcher dispatcher = request.getRequestDispatcher("paymentPage.jsp");     
        dispatcher.forward(request, response);
    }
    private void insertBooking(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        
        int custid = (int) session.getAttribute("customersessionid");
        int shopid = (int) session.getAttribute("shopid");
        String petname = request.getParameter("petname");
        String petage = request.getParameter("petage");
        String petgender = request.getParameter("petgender");
        String pov = request.getParameter("purposeofvisit");
        String date = request.getParameter("date");
        String time = request.getParameter("time");
        String status = request.getParameter("status");
        Order order = new Order(custid,shopid,petname,petage,petgender,pov,time,date,status);
        custDAO.insertOderFromCustomer(order);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("CustomerServlet?action=showOrder");     
        dispatcher.forward(request, response);
    }
    private void showBookForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession(true);
        int shopid = Integer.parseInt(request.getParameter("shopid"));
        session.setAttribute("shopid", shopid);
        
        RequestDispatcher dispatcher = request.getRequestDispatcher("bookingForm.jsp");
        
        dispatcher.forward(request, response);
    }
    private void processLogout(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            // Invalidate the session to end the user's session
            session.invalidate();
        }
        response.sendRedirect("customerLogin.jsp");
    }

    private void processLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String un = request.getParameter("username");
        String pw = request.getParameter("password");

        Customer cust = custDAO.selectCustomerByUsername(un, pw);
        if (cust != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("customersessionid", cust.getCustid());
            session.setAttribute("customer", cust);
            RequestDispatcher rd = request.getRequestDispatcher("homepage.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("invalid.jsp");
            rd.forward(request, response);
        }
    }

    private void listPetShopById(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        PetShop pets = petshopDAO.selectPetshop(id);
        List<Service> service = petshopDAO.selectAllService2(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("petshoppage.jsp");
        request.setAttribute("pet", pets);
        request.setAttribute("service", service);
        dispatcher.forward(request, response);
    }

    private void listPetShop(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String username = (String) session.getAttribute("username");
        List<PetShop> listPetShop = petshopDAO.selectAllPetShop();
        request.setAttribute("vendor", listPetShop);
        RequestDispatcher dispatcher = request.getRequestDispatcher("service.jsp");
        dispatcher.forward(request, response);
    }

    private void registerForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("registerForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("customerLogin.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
        
        int id = (int)session.getAttribute("customersessionid");
        Customer existingCustomer = custDAO.selectCustomer(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("customerForm.jsp");
        request.setAttribute("Customer", existingCustomer);
        dispatcher.forward(request, response);
    }

    private void insertCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phonenum = request.getParameter("phonenum");
        Customer cust = new Customer(username, password, name, email, address, phonenum);
        custDAO.insertCustomer(cust);
        response.sendRedirect("customerLogin.jsp");
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("custid"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phonenum = request.getParameter("phonenum");
        Customer cust = new Customer(id,username,password,name,email,address,phonenum);
        custDAO.updateCustomer(cust);
        response.sendRedirect("customerForm.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
