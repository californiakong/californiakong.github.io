package com.cs360.brycezimbelman.mochamoment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class OrderActivity extends AppCompatActivity {
    EditText nameBox;
    EditText espressoQuantityBox;
    EditText frostQuantityBox;
    EditText mochaQuantityBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);

        nameBox = findViewById(R.id.order_name);
        espressoQuantityBox = findViewById(R.id.espressoQuantityBox);
        frostQuantityBox = findViewById(R.id.frostQuantityBox);
        mochaQuantityBox = findViewById(R.id.mochaQuantityBox);

        Button checkoutButton = findViewById(R.id.checkout);
        checkoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            // executes when checkout button is pressed
            public void onClick(View view) {
                Intent checkoutIntent = new Intent(OrderActivity.this, CheckoutActivity.class);
                startActivity(checkoutIntent);
            }
        });
    }

    // add espresso to order
    public void addEspresso(View view) {
        // Checks if name is given
        if (nameBox.getText().toString().isEmpty()) {
            Toast.makeText(OrderActivity.this, "Add name to order", Toast.LENGTH_SHORT).show();
            return;
        }

        // Checks if quantity given
        if (espressoQuantityBox.getText().toString().isEmpty()) {
            Toast.makeText(OrderActivity.this, "Add quantity to order", Toast.LENGTH_SHORT).show();
            return;
        }

        ItemDBHandler dbHandler = new ItemDBHandler(this, null);
        String orderEspresso = "Espresso";
        String espressoPrice = "$3.99";
        String espressoType = "Hot";
        String espressoUser = nameBox.getText().toString();
        String espressoQuantity = espressoQuantityBox.getText().toString();
        Espresso espresso = new Espresso(orderEspresso, espressoPrice, espressoType, espressoUser, espressoQuantity);

        dbHandler.addEspresso(espresso);
    }

    // add frost to order
    public void addFrost(View view) {
        // Checks if name is given
        if (nameBox.getText().toString().isEmpty()) {
            Toast.makeText(OrderActivity.this, "Add name to order", Toast.LENGTH_SHORT).show();
            return;
        }

        // Checks if quantity given
        if (frostQuantityBox.getText().toString().isEmpty()) {
            Toast.makeText(OrderActivity.this, "Add quantity to order", Toast.LENGTH_SHORT).show();
            return;
        }

        ItemDBHandler dbHandler = new ItemDBHandler(this, null);
        String orderFrost = "Frost";
        String frostPrice = "$3.99";
        String frostType = "Cold";
        String frostUser = nameBox.getText().toString();
        String frostQuantity = frostQuantityBox.getText().toString();
        Frost frost = new Frost(orderFrost, frostPrice, frostType, frostUser, frostQuantity);
        dbHandler.addFrost(frost);
    }

    // add mocha to order
    public void addMocha(View view) {
        // Checks if name is given
            if (nameBox.getText().toString().isEmpty()) {
                Toast.makeText(OrderActivity.this, "Add name to order", Toast.LENGTH_SHORT).show();
                return;
        }

        // Checks if quantity given
        if (mochaQuantityBox.getText().toString().isEmpty()) {
            Toast.makeText(OrderActivity.this, "Add quantity to order", Toast.LENGTH_SHORT).show();
            return;
        }

        ItemDBHandler dbHandler = new ItemDBHandler(this, null);
        String orderMocha = "Mocha";
        String mochaPrice = "$4.99";
        String mochaType = "Hot";
        String mochaUser = nameBox.getText().toString();
        String mochaQuantity = mochaQuantityBox.getText().toString();
        Mocha mocha = new Mocha(orderMocha, mochaPrice, mochaType, mochaUser, mochaQuantity);
        dbHandler.addMocha(mocha);
    }
}
