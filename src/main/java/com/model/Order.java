package com.model;

public class Order {

    private int orderid;
    private int custid;
    private int driverid;
    private int shopid;
    private String petname;
    private String petage;
    private String petgender;
    private String purposeofvisit;
    private String time;
    private String date;
    private String status;
    private byte picture;
    

    public Order() {

    }

    public Order(int orderid, int custid, int driverid, int shopid, String petname, String petage, String petgender, String purposeofvisit, String time, String date, String status, byte picture) {
        this.orderid = orderid;
        this.custid = custid;
        this.driverid = driverid;
        this.shopid = shopid;
        this.petname = petname;
        this.petage = petage;
        this.petgender = petgender;
        this.purposeofvisit = purposeofvisit;
        this.time = time;
        this.date = date;
        this.status = status;
        this.picture = picture;
    }

    
    public Order(int custid, int shopid, String petname, String petage, String petgender, String purposeofvisit, String time, String date, String status) {
        this.custid = custid;
        this.shopid = shopid;
        this.petname = petname;
        this.petage = petage;
        this.petgender = petgender;
        this.purposeofvisit = purposeofvisit;
        this.time = time;
        this.date = date;
        this.status = status;

    }

    public Order( int custid, int driverid, int shopid, String petname, String petage, String petgender, String purposeofvisit,String time,String date,String status, byte picture) {
        
        this.custid = custid;
        this.driverid = driverid;
        this.shopid = shopid;
        this.petname = petname;
        this.petage = petage;
        this.petgender = petgender;
        this.purposeofvisit = purposeofvisit;
        this.time = time;
        this.date = date;
        this.status = status;
        this.picture = picture;
    }
    public Order(int orderid, int custid, int driverid, int shopid, String petname, String petage, String petgender, String purposeofvisit, String status, byte picture) {
        this.orderid = orderid;
        this.custid = custid;
        this.driverid = driverid;
        this.shopid = shopid;
        this.petname = petname;
        this.petage = petage;
        this.petgender = petgender;
        this.purposeofvisit = purposeofvisit;
        this.time = time;
        this.date = date;
        this.status = status;
        this.picture = picture;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getCustid() {
        return custid;
    }

    public void setCustid(int custid) {
        this.custid = custid;
    }

    public int getDriverid() {
        return driverid;
    }

    public void setDriverid(int driverid) {
        this.driverid = driverid;
    }

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public String getPetname() {
        return petname;
    }

    public void setPetname(String petname) {
        this.petname = petname;
    }

    public String getPetage() {
        return petage;
    }

    public void setPetage(String petage) {
        this.petage = petage;
    }

    public String getPetgender() {
        return petgender;
    }

    public void setPetgender(String petgender) {
        this.petgender = petgender;
    }

    public String getPurposeofvisit() {
        return purposeofvisit;
    }

    public void setPurposeofvisit(String purposeofvisit) {
        this.purposeofvisit = purposeofvisit;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public byte getPicture() {
        return picture;
    }

    public void setPicture(byte picture) {
        this.picture = picture;
    }

}
