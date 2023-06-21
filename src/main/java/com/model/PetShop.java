
package com.model;

public class PetShop {
    private int shopid;
    private String username;
    private String password;
    private String shopname;
    private String shopaddress;
    private String phonenum;
    

    public PetShop(){
        
    }
    public PetShop(String username, String password, String shopname, String shopaddress, String phonenum) {
        super();
        this.username = username;
        this.password = password;
        this.shopname = shopname;
        this.shopaddress = shopaddress;
        this.phonenum = phonenum;
        
    }
    public PetShop(int shopid, String username, String password, String shopname, String shopaddress, String phonenum) {
        super();
        this.shopid = shopid;
        this.username = username;
        this.password = password;
        this.shopname = shopname;
        this.shopaddress = shopaddress;
        this.phonenum = phonenum;
        
    }


    

    public int getShopid() {
        return shopid;
    }

    public void setShopid(int shopid) {
        this.shopid = shopid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getShopname() {
        return shopname;
    }

    public void setShopname(String shopname) {
        this.shopname = shopname;
    }

    public String getShopaddress() {
        return shopaddress;
    }

    public void setShopaddress(String shopadress) {
        this.shopaddress = shopadress;
    }

    public String getPhonenum() {
        return phonenum;
    }

    public void setPhonenum(String phonenum) {
        this.phonenum = phonenum;
    }
    
    
}
