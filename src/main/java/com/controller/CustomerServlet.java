package com.controller;

import com.DAO.CustomerDAO;
import com.DAO.PetShopDAO;
import com.model.Customer;
import com.model.PetShop;
import com.model.Service;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/")
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
        String action = request.getServletPath();
        try {
            switch (action) {
                case "/new":
                    registerForm(request, response);
                    break;
                case "/insert":
                    insertCustomer(request, response);
                    break;
                case "/edit":
                    showEditForm(request, response);
                    break;
                case "/update":
                    updateCustomer(request, response);
                    break;
                case "/listPetShop":
                    listPetShop(request, response);
                    break;
                case "/listPetShopById":
                    listPetShopById(request, response);
                    break;
                default:
                    showHomePage(request, response);
                    break;

            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
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
        int id = Integer.parseInt(request.getParameter("id"));
        Customer existingCustomer = custDAO.selectCustomer(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("CustomerForm.jsp");
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
        response.sendRedirect("list");
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String position = request.getParameter("position");

        response.sendRedirect("list");
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
