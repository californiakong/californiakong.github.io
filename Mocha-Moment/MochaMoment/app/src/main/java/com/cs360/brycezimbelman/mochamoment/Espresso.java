package com.cs360.brycezimbelman.mochamoment;

 class Espresso {
    private String var_espresso;
    private String var_price;
    private String var_type;
    private String var_user;
    private String var_quantity;

     Espresso() {

    }

    public Espresso(String espresso, String price, String type ,String user, String quantity) {
        this.var_espresso = espresso;
        this.var_price = price;
        this.var_type = type;
        this.var_user = user;
        this.var_quantity = quantity;
    }


    void setEspresso(String espresso) { this.var_espresso = espresso;}
    void setPrice(String price) {
        this.var_price = price;
    }
    void setType(String type) {
        this.var_type = type;
    }
    void setUser(String user) {
        this.var_user = user;
    }
    void setQuantity(String quantity) { this.var_quantity = quantity; }

    String getEspresso() { return this.var_espresso; }
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
