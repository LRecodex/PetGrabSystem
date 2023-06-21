/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petgrab.dao;

import com.model.Customer;
import com.model.Order;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {

    Connection connection = null;
    private final String jdbcURL = "jdbc:mysql://localhost:3306/petgrabsystem";
    private final String jdbcUsername = "root";
    private final String jdbcPassword = "admin";
    private static final String INSERT_CUSTOMER_SQL = "INSERT INTO customer(username,password,name,email,address,phonenum) VALUES (?,?,?,?,?,?);";
    private static final String SELECT_CUSTOMER_BY_ID = "SELECT id,username,passwords,name,email,address,phonenum FROM customer WHERE id=?";
    private static final String BOOK_ORDER_CUSTOMER = "INSERT INTO orders(custid,shopid,petname,petage,petgender,purposeofvisit,time,date,status) VALUES (?,?,?,?,?,?,?,?,?)";
    private static final String LIST_ORDER = "SELECT * FROM orders WHERE custid=?";
    private static final String UPDATE_CUSTOMER_SQL = "UPDATE customer SET username=?,passwords=?,name=?,email=?,address=?,phonenum=? WHERE id=?";

    public CustomerDAO() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return connection;
    }
    
    public List<Order> selectOrderByCustId(int id) {
        List<Order> orders = new ArrayList<>();

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(LIST_ORDER)) {
            ps.setInt(1,id);
            
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();

            while(rs.next()) {
                int orderid = rs.getInt("orderid");
                int custid = rs.getInt("custid");
                int driverid = rs.getInt("driverid");
                int shopid = rs.getInt("shopid");
                String petname = rs.getString("petname");
                String petage = rs.getString("petage");
                String petgender = rs.getString("petgender");
                String purposeofvisit = rs.getString("purposeofvisit");
                String time = rs.getString("time");
                String date = rs.getString("date");
                String status = rs.getString("status");
                byte picture = rs.getByte("picture");
                orders.add(new Order(orderid,custid, driverid, shopid, petname, petage,petgender,purposeofvisit,time,date,status,picture));
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    
    public void insertOderFromCustomer(Order order) throws SQLException {
        System.out.println(BOOK_ORDER_CUSTOMER);

        try {
            Connection connection = getConnection();
            PreparedStatement ps = connection.prepareStatement(BOOK_ORDER_CUSTOMER);

            ps.setInt(1, order.getCustid());
            ps.setInt(2, order.getShopid());
            ps.setString(3, order.getPetname());
            ps.setString(4, order.getPetage());
            ps.setString(5, order.getPetgender());
            ps.setString(6, order.getPurposeofvisit());
            ps.setString(7, order.getTime());
            ps.setString(8, order.getDate());
            ps.setString(9, order.getStatus());
            System.out.println(ps);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertCustomer(Customer cust) throws SQLException {
        System.out.println(INSERT_CUSTOMER_SQL);

        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_CUSTOMER_SQL);

            preparedStatement.setString(1, cust.getUsername());
            preparedStatement.setString(2, cust.getPassword());
            preparedStatement.setString(3, cust.getName());
            preparedStatement.setString(4, cust.getEmail());
            preparedStatement.setString(5, cust.getAddress());
            preparedStatement.setString(6, cust.getPhonenum());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    public Customer selectCustomerByUsername(String un,String pw) {
        Customer cust = null;

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement("SELECT * FROM customer WHERE username=? AND password=?")) {
            ps.setString(1,un);
            ps.setString(2,pw);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                int custid = rs.getInt("custid");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phonenum = rs.getString("phonenum");
                cust = new Customer(custid, un, pw, name, email, address, phonenum);
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cust;
    }

    public Customer selectCustomer(int id) {
        Customer cust = null;

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(SELECT_CUSTOMER_BY_ID)) {
            ps.setInt(1, id);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String username = rs.getString("name");
                String password = rs.getString("password");
                String name = rs.getString("name");
                String email = rs.getString("email");
                String address = rs.getString("address");
                String phonenum = rs.getString("phonenum");
                cust = new Customer(id, username, password, name, email, address, phonenum);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cust;
    }

    
    

    public boolean updateCustomer(Customer cust) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_CUSTOMER_SQL)) {
            preparedStatement.setString(1, cust.getUsername());
            preparedStatement.setString(2, cust.getPassword());
            preparedStatement.setString(3, cust.getName());
            preparedStatement.setString(4, cust.getEmail());
            preparedStatement.setString(5, cust.getAddress());
            preparedStatement.setString(6, cust.getPhonenum());
            preparedStatement.setInt(7, cust.getCustid());
            System.out.println(preparedStatement);
            rowUpdated = preparedStatement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
}
