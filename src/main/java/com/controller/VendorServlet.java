package com.controller;

import com.model.Order;
import petgrab.dao.PetShopDAO;
import com.model.PetShop;
import com.model.Service;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.sql.SQLException;
import java.util.List;
import petgrab.dao.ServicesDAO;

public class VendorServlet extends HttpServlet {

    private PetShopDAO petShopDAO;
    private ServicesDAO servicesDAO;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    @Override
    public void init() {
        petShopDAO = new PetShopDAO();
        servicesDAO = new ServicesDAO();
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
                     case "createServiceForm":
                    createServiceForm(request, response);
                    break;
                case "insertService":
                    insertService(request, response);
                    break;
                case "showListServiceByShopId":
                    showListServiceByShopId(request, response);
                    break;
                    case "showListServiceByServiceId":
                    showListServiceByServiceId(request, response);
                    break;
                case "editservice":
                    showEditServiceForm(request, response);
                    break;
                case "deleteservice":
                    deleteService(request, response);
                    break;
                    case "updateService":
                    updateService(request, response);
                    break;
                default:
                    showHomePage(request, response);
                    break;

            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } 
    }

    private void deleteService(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        
        int id = Integer.parseInt(request.getParameter("serviceid"));
        int shopid = Integer.parseInt(request.getParameter("shopid"));
        request.setAttribute("shopid", shopid);
        System.out.println("ID:" + id);
        servicesDAO.deleteServices(id);
        response.sendRedirect("VendorServlet?action=showListServiceByShopId");
    }
    private void showListServiceByServiceId(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        
        int serviceid = Integer.parseInt(request.getParameter("serviceid"));
      
        List<Service> service = petShopDAO.selectAllServiceByServiceId(serviceid);
        request.setAttribute("serv", service);
        RequestDispatcher rd = request.getRequestDispatcher("ServicesList.jsp");
        rd.forward(request, response);
    }

    private void showListServiceByShopId(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession();
        int shopid = (int) session.getAttribute("petsessionid");
        List<Service> service = petShopDAO.selectAllServiceByShopId(shopid);
        request.setAttribute("serv", service);
        RequestDispatcher rd = request.getRequestDispatcher("ServicesList.jsp");
        rd.forward(request, response);
    }

    private void createServiceForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
        RequestDispatcher rd = request.getRequestDispatcher("serviceForm.jsp");
        rd.forward(request, response);
    }
    private void insertService(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int shopid = Integer.parseInt(request.getParameter("shopid"));
        String name = request.getParameter("name");             
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));

        Service ser = new Service(shopid,name,description,price);
        petShopDAO.insertService(ser);
        response.sendRedirect("ServicesList.jsp");
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
        showOrder(request, response);
    }

    private void declineOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        int orderid = Integer.parseInt(request.getParameter("orderid"));
        petShopDAO.declineOrder(orderid);
        showOrder(request, response);
    }

    private void showOrder(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession();

        int shopid = (int) session.getAttribute("petsessionid");
        List<Order> listOrder = petShopDAO.selectAllOrderByShopId(shopid);
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
        HttpSession session = request.getSession();

        int shopid = (int) session.getAttribute("petsessionid");
        PetShop vendor = petShopDAO.selectPetshop(shopid);
        request.setAttribute("vendor", vendor);
        RequestDispatcher dispatcher = request.getRequestDispatcher("vendorAccount.jsp");
        dispatcher.forward(request, response);
    }
    private void showEditServiceForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {   
        int serviceid = Integer.parseInt(request.getParameter("serviceid"));
        Service ser = petShopDAO.selectAllService(serviceid);
        request.setAttribute("service", ser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("serviceEditForm.jsp");
        dispatcher.forward(request, response);
    }

    private void updateService(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int serviceid = Integer.parseInt(request.getParameter("serviceid"));
        int shopid = Integer.parseInt(request.getParameter("shopid"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        

        Service service = new Service(serviceid, shopid, name, description, price);
        petShopDAO.updateService(service);
        response.sendRedirect("vendorMain.jsp");
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
        int id = Integer.parseInt(request.getParameter("shopid"));
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String shopname = request.getParameter("shopname");
        String shopaddress = request.getParameter("shopaddress");
        String phonenum = request.getParameter("phonenum");

        PetShop petshop = new PetShop(id, username, password, shopname, shopaddress, phonenum);
        petShopDAO.update(petshop);
        response.sendRedirect("VendorServlet?action=edit");
    }

    private void deleteVendor(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("serviceid"));
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

    private void editService(HttpServletRequest request, HttpServletResponse response) {
        
    }

}
