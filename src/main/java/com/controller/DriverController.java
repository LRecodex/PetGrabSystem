/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.controller;

import petgrab.dao.DriverDAO;
import com.model.Driver;
import com.model.Order;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.annotation.MultipartConfig;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import petgrab.dao.PetShopDAO;

/**
 *
 * @author MUHAMMAD FAUZUL AZIM BIN IMRAN HAYAT
 */
@WebServlet("/DriverController/")
@MultipartConfig
public class DriverController extends HttpServlet {

    private DriverDAO dao;
    private PetShopDAO petShopDAO;

    @Override
    public void init() {
        dao = new DriverDAO();
        petShopDAO = new PetShopDAO();
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        try {
            switch (action) {
                case "insert":
                    insertDriver(request, response);
                    break;
                case "update":
                    updateDriver(request, response);
                    break;
                case "delete":
                    deleteDriver(request, response);
                    break;
                case "edit":
                    editForm(request, response);
                    break;
                case "login":
                    login(request, response); // Handle login action
                    break;
                case "logout":
                    logout(request, response);
                    break;
                case "order":
                    listOrder(request, response);
                    break;
                case "accept":
                    acceptOrder(request, response);
                    break;
                case "decline":
                    declineOrder(request, response);
                    break;
                case "delivered":
                    orderDelivered(request, response);
                    break;
                case "addproof":
                    addpic(request, response);
                    break;
                default:
                    viewList(request, response);
                    break;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DriverController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    private void addpic(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String orderidParam = request.getParameter("orderid");
        
        if (orderidParam != null && !orderidParam.isEmpty()) {
            int orderid = Integer.parseInt(orderidParam);

            Part filePart = request.getPart("file");
            InputStream fileContent = filePart.getInputStream();
            byte[] fileData = fileContent.readAllBytes();
            dao.addProof(fileData, orderid);
        }

        listOrder(request, response);
    }

    private void orderDelivered(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int orderid = Integer.parseInt(request.getParameter("orderid"));
        dao.deliveredOrder(orderid);
        listOrder(request, response);
    }

    private void acceptOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int orderid = Integer.parseInt(request.getParameter("orderid"));
        int driverid = Integer.parseInt(request.getParameter("driverid"));

        dao.acceptOrder(driverid, orderid);
        listOrder(request, response);

    }

    private void declineOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int orderid = Integer.parseInt(request.getParameter("orderid"));
        petShopDAO.declineOrder(orderid);
        listOrder(request, response);
    }

    public void listOrder(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        HttpSession session = request.getSession();
        Driver dr = (Driver) session.getAttribute("account");
        request.setAttribute("sesi", dr);
        List<Order> listOrder = petShopDAO.selectAllOrder();
        request.setAttribute("list", listOrder);
        RequestDispatcher dispatcher = request.getRequestDispatcher("DriverOrder.jsp");
        dispatcher.forward(request, response);
    }

    public void insertDriver(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean success = false;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phonenum = request.getParameter("phonenum");
        String noplate = request.getParameter("noplate");
        String status = "Active";

        Driver driver = new Driver(username, password, name, email, address, phonenum, noplate, status);
        success = dao.AddDriver(driver);
        if (success = true) {
            System.out.println("Driver Insertion Successful");
            request.setAttribute("message", "Registration success!");
            RequestDispatcher rd = request.getRequestDispatcher("/homepage.jsp");
            rd.forward(request, response);
        } else {
            System.out.println("Driver Insertion is Denied");
        }
    }

    public void updateDriver(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean success = false;

        int driverid = Integer.parseInt(request.getParameter("driverid"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("address");
        String phonenum = request.getParameter("phonenum");
        String noplate = request.getParameter("noplate");
        String status = request.getParameter("status");

        Driver driver = new Driver(driverid, username, password, name, email, address, phonenum, noplate, status);
        success = dao.UpdateDriver(driver);

        if (success = true) {
            System.out.println("Driver Update Successful");
            HttpSession session = request.getSession();
            session.setAttribute("account", driver);
            response.sendRedirect("DriverAccount.jsp");
        } else {
            System.out.println("Driver Update is Denied");
        }
    }

    public void deleteDriver(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        boolean success = false;
        int driverid = Integer.parseInt(request.getParameter("driverid"));
        success = dao.DeleteDriver(driverid);

        if (success = true) {
            System.out.println("Delete Successful");
            response.sendRedirect("homepage.jsp");
        }
        System.out.println("Delete Unsuccessful");
    }

    public void editForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        int driverid = Integer.parseInt(request.getParameter("driverid"));
        Driver existingDriver = dao.SelectDriverById(driverid);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/DriverAccount.jsp");
        request.setAttribute("driver", existingDriver);
        dispatcher.forward(request, response);
    }

    public void viewList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {

        List<Driver> driver = dao.SelectAllDriver();
        RequestDispatcher rd = request.getRequestDispatcher("driverList.jsp");
        request.setAttribute("driverlist", driver);
        rd.forward(request, response);
    }

    public void login(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Call the DAO method to verify the login credentials
        boolean loginSuccessful = dao.verifyLogin(username, password);

        Driver driver = dao.SelectDriverByUsername(username);

        if (loginSuccessful == true) {
            // Redirect to a success page
            HttpSession session = request.getSession();
            session.setAttribute("account", driver);
            RequestDispatcher rd = request.getRequestDispatcher("/DriverMainpage.jsp");
            rd.forward(request, response);
        } else {
            // Redirect to a failure page
            response.sendRedirect("Authentication.jsp");
        }
    }

    public void logout(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        request.setAttribute("message", "Log Out success!");
        RequestDispatcher rd = request.getRequestDispatcher("/homepage.jsp");
        rd.forward(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    

}
