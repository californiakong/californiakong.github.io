package com.cs360.brycezimbelman.mochamoment;

public class Frost {
    // Initialize variables
    private String var_frost;
    private String var_price;
    private String var_type;
    private String var_user;
    private String var_quantity;

    Frost() {

    }

    // Sets up database entries
    public Frost( String frost, String price, String type, String user, String quantity) {
        this.var_frost = frost;
        this.var_price = price;
        this.var_type = type;
        this.var_user = user;
        this.var_quantity = quantity;
    }


    // Sets data into database
    void setFrost(String frost) { this.var_frost = frost;}

    // Returns database results
    String getFrost() { return this.var_frost; }
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
