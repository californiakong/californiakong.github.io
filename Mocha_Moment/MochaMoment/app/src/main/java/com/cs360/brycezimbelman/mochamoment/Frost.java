package com.cs360.brycezimbelman.mochamoment;

public class Frost {
    private String var_frost;
    private String var_price;
    private String var_type;
    private String var_user;
    private String var_quantity;

    Frost() {

    }

    public Frost( String frost, String price, String type, String user, String quantity) {
        this.var_frost = frost;
        this.var_price = price;
        this.var_type = type;
        this.var_user = user;
        this.var_quantity = quantity;
    }


    void setFrost(String frost) { this.var_frost = frost;}
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
