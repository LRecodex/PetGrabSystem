package petgrab.dao;

import com.model.Customer;
import com.model.Order;
import com.model.PetShop;
import com.model.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PetShopDAO {

    Connection connection = null;
    private String jdbcURL = "jdbc:mysql://localhost/petgrabsystem";
    private String jdbcUsername = "root";
    private String jdbcPassword = "admin";
    private static final String INSERT_PETSHOP_SQL = "INSERT INTO petshop(username,password,shopname,shopaddress,phonenum) VALUES (?,?,?,?,?)";
    private static final String SELECT_PETSHOP_BY_ID = "SELECT * FROM petshop WHERE shopid=?";
    private static final String SELECT_PETSHOP_BY_UN_PW = "SELECT * FROM petshop WHERE username=? AND password=?";
    private static final String SELECT_ALL_PETSHOP = "SELECT * FROM petshop";
    private static final String SELECT_ALL_ORDER_SHOPID = "SELECT * FROM orders WHERE shopid=?";
    private static final String SELECT_ALL_SERVICE = "SELECT * FROM service WHERE shopid=?";
    private static final String SELECT_ALL_ORDER="SELECT * FROM orders";
    private static final String UPDATE_PETSHOP_SQL = "UPDATE petshop SET username=?,password=?,shopname=?,shopaddress=?,phonenum=? WHERE shopid=?";
    private static final String UPDATE_STATUS_ACCEPT = "UPDATE orders SET status='Waiting for driver...'  WHERE orderid=?";
    private static final String UPDATE_STATUS_DECLINE = "UPDATE orders SET status='Declined'  WHERE orderid=?";
    private static final String DELETE_PETSHOP_SQL = "DELETE FROM petshop where id=?";
    

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
    public PetShop selectVendorByUsername(String un,String pw) {
        PetShop pet = null;

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(SELECT_PETSHOP_BY_UN_PW)) {
            ps.setString(1,un);
            ps.setString(2,pw);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();

            if(rs.next()) {
                int shopid = rs.getInt("shopid");
                
                
                String shopname = rs.getString("shopname");    
                String shopaddress = rs.getString("shopaddress"); 
                String phonenum = rs.getString("phonenum");
                pet = new PetShop(shopid,un,pw,shopname,shopaddress, phonenum);
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pet;
    }
     public void acceptOrder(int id) {
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(UPDATE_STATUS_ACCEPT)) {           
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
     public void declineOrder(int id) {
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(UPDATE_STATUS_DECLINE)) {           
            ps.setInt(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
     public List<Order> selectAllOrderByShopId(int id) {
        List<Order> orders = new ArrayList<>();

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(SELECT_ALL_ORDER_SHOPID)) {         
            ps.setInt(1, id);
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
                byte[] picture = rs.getBytes("picture");
                orders.add(new Order(orderid,custid, driverid, id, petname, petage,petgender,purposeofvisit,time,date,status,picture));
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    public List<Order> selectAllOrder() {
        List<Order> orders = new ArrayList<>();

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(SELECT_ALL_ORDER)) {         
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
                byte[] picture = rs.getBytes("picture");
                orders.add(new Order(orderid,custid, driverid, shopid, petname, petage,petgender,purposeofvisit,time,date,status,picture));
                
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return orders;
    }
    
    public void insert(PetShop petShop) {

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(INSERT_PETSHOP_SQL)) {
            ps.setString(1, petShop.getUsername());
            ps.setString(2, petShop.getPassword());
            ps.setString(3, petShop.getShopname());
            ps.setString(4, petShop.getShopaddress());
            ps.setString(5, petShop.getPhonenum());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(PetShop petShop) {

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(UPDATE_PETSHOP_SQL)) {
            ps.setString(1, petShop.getUsername());
            ps.setString(2, petShop.getPassword());
            ps.setString(3, petShop.getShopname());
            ps.setString(4, petShop.getShopaddress());
            ps.setString(5, petShop.getPhonenum());           
            ps.setInt(6, petShop.getShopid());
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public PetShop selectPetshop(int id) {
        PetShop pet = null;

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(SELECT_PETSHOP_BY_ID)) {
            ps.setInt(1, id);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                String username = rs.getString("username");
                String password = rs.getString("password");
                String shopname = rs.getString("shopname");
                String shopaddress = rs.getString("shopaddress");
                String phonenum = rs.getString("phonenum");              
                pet = new PetShop(id, username, password, shopname, shopaddress, phonenum);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pet;
    }

    public List<PetShop> selectAllPetShop() {
        List<PetShop> pet = new ArrayList<>();

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(SELECT_ALL_PETSHOP);) {
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int shopid = rs.getInt("shopid");
                String username = rs.getString("username");
                String password = rs.getString("password");
                String shopname = rs.getString("shopname");
                String shopaddress = rs.getString("shopaddress");
                String phonenum = rs.getString("phonenum");
                
                pet.add(new PetShop(shopid, username, password, shopname, shopaddress, phonenum));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pet;
    }

    public List<Service> selectAllService2(int id) {
        List<Service> ser = new ArrayList<>();

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(SELECT_ALL_SERVICE);) {
            System.out.println(ps);
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                
                int shopid = rs.getInt("shopid");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");

                ser.add(new Service(id, shopid, name,description, price));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ser;
    }

    public Service selectAllService(int id) {
        Service ser = null;

        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(SELECT_ALL_SERVICE)) {
            ps.setInt(1, id);
            System.out.println(ps);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                int shopid = rs.getInt("shopid");
                String name = rs.getString("name");
                String description = rs.getString("description");
                double price = rs.getDouble("price");
                ser = new Service(id, shopid,name, description, price);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ser;
    }

    public void delete(int id) {
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(DELETE_PETSHOP_SQL)) {
            ps.setInt(1, id);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
