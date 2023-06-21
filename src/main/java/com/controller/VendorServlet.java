package com.controller;

import com.model.Customer;
import com.model.Order;
import petgrab.dao.PetShopDAO;
import com.model.PetShop;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import petgrab.dao.CustomerDAO;

public class VendorServlet extends HttpServlet {

    private PetShopDAO petShopDAO;
    private CustomerDAO custDAO;
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public void init() {
        petShopDAO = new PetShopDAO();
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
                    insertVendor(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateVendor(request, response);
                    break;
                case "delete":
                    deleteVendor(request, response);
                    break;
                case "list":
                    showAll(request, response);
                    break;
                    case "login":
                    processLogin(request, response);
                    break;
                    case "showOrder":
                    showOrder(request, response);
                    break;
                    case "accept":
                    acceptOrder(request, response);
                    break;
                    case "decline":
                    declineOrder(request, response);
                    break;
                default:
                    showHomePage(request, response);
                    break;

            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    private void processLogin(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        String un = request.getParameter("username");
        String pw = request.getParameter("password");

        PetShop pet = petShopDAO.selectVendorByUsername(un, pw);
        if (pet != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("petsessionid", pet.getShopid());
            session.setAttribute("pet", pet);
            RequestDispatcher rd = request.getRequestDispatcher("vendorMain.jsp");
            rd.forward(request, response);
        } else {
            RequestDispatcher rd = request.getRequestDispatcher("invalid.jsp");
            rd.forward(request, response);
        }
    }
    private void acceptOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int orderid = Integer.parseInt(request.getParameter("orderid"));
        petShopDAO.acceptOrder(orderid);
        showOrder(request,response);
    }
    private void declineOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int orderid = Integer.parseInt(request.getParameter("orderid"));
        petShopDAO.declineOrder(orderid);
        showOrder(request,response);
    }
    private void showOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();
              
        int shopid = (int) session.getAttribute("petsessionid");
        List <Order> listOrder= petShopDAO.selectAllOrderByShopId(shopid);
        request.setAttribute("list", listOrder);
        RequestDispatcher dispatcher = request.getRequestDispatcher("orderVendor.jsp");     
        dispatcher.forward(request, response);
    }
    private void registerForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("vendorForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showHomePage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("vendorForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        PetShop vendor = petShopDAO.selectPetshop(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("vendorForm.jsp");
        request.setAttribute("vendor", vendor);
        dispatcher.forward(request, response);
    }

    private void insertVendor(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String shopname = request.getParameter("shopname");
        String shopaddress = request.getParameter("shopaddress");
        String phonenum = request.getParameter("phonenum");
        
        PetShop petShop = new PetShop(username, password, shopname, shopaddress, phonenum);
        petShopDAO.insert(petShop);
        response.sendRedirect("vendorLogin.jsp");
    }

    private void updateVendor(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String shopname = request.getParameter("shopname");
        String shopaddress = request.getParameter("shopaddress");
        String phonenum = request.getParameter("phonenum");
        byte imagepetshop = Byte.parseByte(request.getParameter("shopimage"));

        PetShop petshop = new PetShop(username, password, shopname, shopaddress, phonenum);
        petShopDAO.update(petshop);
        response.sendRedirect("list");
    }

    private void deleteVendor(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("ID:" + id);
        petShopDAO.delete(id);
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

    private void showAll(HttpServletRequest request, HttpServletResponse response) {
    }

}
