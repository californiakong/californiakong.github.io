package com.cs360.brycezimbelman.mochamoment;

public class Mocha {
    // Initialize variables
    private String var_mocha;
    private String var_price;
    private String var_type;
    private String var_user;
    private String var_quantity;

    Mocha() {

    }

    public Mocha(String mocha, String price, String type, String user, String quantity) {
        // Sets up database entries
        this.var_mocha = mocha;
        this.var_price = price;
        this.var_type = type;
        this.var_user = user;
        this.var_quantity = quantity;
    }

    // Sets data into database
    void setMocha(String mocha) { this.var_mocha = mocha;}

    // Returns database results
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