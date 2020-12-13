package com.cs360.brycezimbelman.mochamoment;

public class Mocha {
    private String var_mocha;
    private String var_price;
    private String var_type;
    private String var_user;
    private String var_quantity;

    Mocha() {

    }

    public Mocha(String mocha, String price, String type, String user, String quantity) {
        this.var_mocha = mocha;
        this.var_price = price;
        this.var_type = type;
        this.var_user = user;
        this.var_quantity = quantity;
    }

    void setMocha(String mocha) { this.var_mocha = mocha;}
    void setPrice(String price) {
        this.var_price = price;
    }
    public void setType(String type) {
        this.var_type = type;
    }
    public void setUser(String user) {
        this.var_user = user;
    }
    public void setQuantity(String quantity) {
        this.var_quantity = quantity;
    }

    String getMocha() { return this.var_mocha; }
    String getPrice() {
        return this.var_price;
    }
    String getType() {
        return this.var_type;
    }
    String getUser() {
        return this.var_user;
    }
    String getQuantity() {
        return this.var_quantity;
    }

}