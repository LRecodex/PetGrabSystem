/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package petgrab.dao;

import com.model.Driver;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MUHAMMAD FAUZUL AZIM BIN IMRAN HAYAT
 */
public class DriverDAO {

    //jdbc info
    private String user = "root";
    private String pass = "admin";
    private String jdbcUrl = "jdbc:mysql://localhost:3306/petgrabsystem";

    //SQL Queries
    private final String ADD_DRIVER = "INSERT INTO driver (username, password, name, email, address, phonenum, noplate, status) VALUES (?,?,?,?,?,?,?,?)";
    private final String UPDATE_DRIVER = "UPDATE driver SET username=?, password=?, name=?, email=?, address=?, phonenum=?, noplate=?, status=? WHERE driverid=?";
    private final String DELETE_DRIVER = "DELETE FROM driver WHERE driverid=?";
    private final String SELECT_BY_username = "SELECT * FROM driver WHERE username=?";
    private final String SELECT_BY_ID = "SELECT * FROM driver WHERE driverid=?";
    private final String SELECT_ALL_DRIVER = "SELECT * FROM driver";
    private final String VERIFY_LOGIN = "SELECT * FROM driver WHERE username = ? AND password = ?";
    private final String UPDATE_STATUS_ACCEPT = "UPDATE orders SET status='Order Accepted',driverid=?  WHERE orderid=?";
    private final String UPDATE_STATUS_DECLINE = "UPDATE orders SET status='Declined'  WHERE orderid=?";

    //Create Connection
    public Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(jdbcUrl, user, pass);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }

    public void acceptOrder(int driverid,int orderid) {
        try (Connection connection = getConnection(); PreparedStatement ps = connection.prepareStatement(UPDATE_STATUS_ACCEPT)) {                      
            ps.setInt(1, driverid);
            ps.setInt(2, orderid);
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
    //Add Driver
    public boolean AddDriver(Driver driver) {
        boolean status = false;

        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(ADD_DRIVER);
            ps.setString(1, driver.getUsername());
            ps.setString(2, driver.getPassword());
            ps.setString(3, driver.getName());
            ps.setString(4, driver.getEmail());
            ps.setString(5, driver.getAddress());
            ps.setString(6, driver.getPhonenum());
            ps.setString(7, driver.getNoplate());
            ps.setString(8, driver.getStatus());

            status = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    //Update Driver
    public boolean UpdateDriver(Driver driver) {
        boolean status = false;
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(UPDATE_DRIVER);
            ps.setString(1, driver.getUsername());
            ps.setString(2, driver.getPassword());
            ps.setString(3, driver.getName());
            ps.setString(4, driver.getEmail());
            ps.setString(5, driver.getAddress());
            ps.setString(6, driver.getPhonenum());
            ps.setString(7, driver.getNoplate());
            ps.setString(8, driver.getStatus());
            ps.setInt(9, driver.getDriverid());
            status = ps.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return status;
    }

    //Delete Driver
    public boolean DeleteDriver(int driverid) throws SQLException {
        boolean status = false;
        try (Connection con = getConnection()) {
            PreparedStatement ps = con.prepareStatement(DELETE_DRIVER);
            ps.setInt(1, driverid);
            status = ps.executeUpdate() > 0;
        }
        return status;
    }

    //Select All Driver
    public List<Driver> SelectAllDriver() throws SQLException {
        List<Driver> list = new ArrayList<>();
        try (Connection con = getConnection(); Statement s = con.createStatement();) {
            ResultSet rs = s.executeQuery(SELECT_ALL_DRIVER);
            while (rs.next()) {
                Driver driver = new Driver();

                driver.setDriverid(rs.getInt("driverid"));
                driver.setUsername(rs.getString("username"));
                driver.setPassword(rs.getString("password"));
                driver.setName(rs.getString("name"));
                driver.setEmail(rs.getString("email"));
                driver.setAddress(rs.getString("address"));
                driver.setPhonenum(rs.getString("phonenum"));
                driver.setNoplate(rs.getString("noplate"));
                driver.setStatus(rs.getString("status"));

                list.add(driver);
            }
        }
        return list;
    }

    //Select Driver with driver id
    public Driver SelectDriverById(int driverid) throws SQLException {
        Driver driver = new Driver();
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(SELECT_BY_ID)) {
            ps.setInt(1, driverid);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                driver.setDriverid(rs.getInt("driverid"));
                driver.setUsername(rs.getString("username"));
                driver.setPassword(rs.getString("password"));
                driver.setName(rs.getString("name"));
                driver.setEmail(rs.getString("email"));
                driver.setAddress(rs.getString("address"));
                driver.setPhonenum(rs.getString("phonenum"));
                driver.setNoplate(rs.getString("noplate"));
                driver.setStatus(rs.getString("status"));
            }
        }
        return driver;
    }

    //Select Driver with username
    public Driver SelectDriverByUsername(String username) throws SQLException {
        Driver driver = new Driver();
        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(SELECT_BY_username)) {
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                driver.setDriverid(rs.getInt("driverid"));
                driver.setUsername(rs.getString("username"));
                driver.setPassword(rs.getString("password"));
                driver.setName(rs.getString("name"));
                driver.setEmail(rs.getString("email"));
                driver.setAddress(rs.getString("address"));
                driver.setPhonenum(rs.getString("phonenum"));
                driver.setNoplate(rs.getString("noplate"));
                driver.setStatus(rs.getString("status"));
            }
        }
        return driver;
    }

    //Verify login
    public boolean verifyLogin(String username, String password) {

        boolean loginSuccessful = false;

        try (Connection con = getConnection(); PreparedStatement ps = con.prepareStatement(VERIFY_LOGIN)) {

            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet resultSet = ps.executeQuery();

            if (resultSet.next()) {
                // User with the provided username and password exists in the database
                loginSuccessful = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return loginSuccessful;

    }

}
