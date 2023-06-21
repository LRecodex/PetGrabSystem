package com.controller;

import petgrab.dao.ServicesDAO;
import com.model.Service;
import jakarta.servlet.RequestDispatcher;
import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.sql.SQLException;

public class ServiceServlet extends HttpServlet {

    private ServicesDAO serviceDao;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    public void init() {
        serviceDao = new ServicesDAO();
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
                    insertService(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "update":
                    updateService(request, response);
                    break;
                case "delete":
                    deleteService(request, response);
                    break;
                default:
                    showList(request, response);
                    break;

            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void registerForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("serviceForm.jsp");
        dispatcher.forward(request, response);
    }

    private void showList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("ServiceServlet?action");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Service service = serviceDao.selectService(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("serviceForm.jsp");
        request.setAttribute("service", service);
        dispatcher.forward(request, response);
    }

    private void insertService(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int shopid = Integer.parseInt(request.getParameter("shopid"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        Service service = new Service(shopid, name, description, price);
        serviceDao.insertServices(service);
        response.sendRedirect("ServiceServlet?action=googoogaa");
    }

    private void updateService(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        int shopid = Integer.parseInt(request.getParameter("shopid"));
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Double price = Double.parseDouble(request.getParameter("price"));
        Service service = new Service(shopid, name, description, price);
        serviceDao.updateService(service);
        response.sendRedirect("list");
    }

   private void deleteService(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        System.out.println("ID:" + id);
        serviceDao.deleteServices(id);
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
