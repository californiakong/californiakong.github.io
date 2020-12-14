package com.cs360.brycezimbelman.mochamoment;

 class Espresso {
     // Initialize variables
    private String var_espresso;
    private String var_price;
    private String var_type;
    private String var_user;
    private String var_quantity;

     Espresso() {

    }

    // Sets up database entries
    public Espresso(String espresso, String price, String type ,String user, String quantity) {
        this.var_espresso = espresso;
        this.var_price = price;
        this.var_type = type;
        this.var_user = user;
        this.var_quantity = quantity;
    }


    // Sets data into database
    void setEspresso(String espresso) { this.var_espresso = espresso;}

    // Returns database results
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
