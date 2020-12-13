package com.cs360.brycezimbelman.mochamoment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class CheckoutActivity extends AppCompatActivity {
    TextView espressoView;
    TextView mochaView;
    TextView frostView;
    TextView espressoPriceBox;
    TextView mochaPriceBox;
    TextView frostPriceBox;
    TextView espressoQuantityBox;
    TextView mochaQuantityBox;
    TextView frostQuantityBox;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkout);
        espressoView = findViewById(R.id.order_espresso);
        mochaView = findViewById(R.id.order_mocha);
        frostView = findViewById(R.id.order_frost);
        espressoPriceBox = findViewById(R.id.espresso_price);
        mochaPriceBox = findViewById(R.id.mocha_price);
        frostPriceBox = findViewById(R.id.frost_price);
        espressoQuantityBox = findViewById(R.id.espresso_quantity);
        mochaQuantityBox = findViewById(R.id.mocha_quantity);
        frostQuantityBox = findViewById(R.id.frost_quantity);
        readEspresso();
        readMocha();
        readFrost();

        Button callButton = findViewById(R.id.order_call);
        callButton.setOnClickListener(new View.OnClickListener() {
            // Execute phone call button
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:6082957085"));
                // Ensure that there's a phone call activity to handle the intent
                if (callIntent.resolveActivity(getPackageManager()) != null) {
                    // Error if permission is not granted
                    if (ActivityCompat.checkSelfPermission(CheckoutActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(CheckoutActivity.this, "Call Permission Not Granted", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    startActivity(callIntent);
                }
            }
        });
    }

    public void readEspresso() {
        ItemDBHandler dbHandler = new ItemDBHandler(this, null);
        String orderEspresso = "Espresso";
        String espresso = dbHandler.readEspresso(orderEspresso);

        if (espresso != null) {
            espressoView.setText(R.string.espresso);
            espressoPriceBox.setText(R.string.price);
            espressoQuantityBox.setText(espresso);
        }
    }

    public void readFrost() {
        ItemDBHandler dbHandler = new ItemDBHandler(this, null);
        String orderFrost = "Frost";
        String frost = dbHandler.readFrost(orderFrost);

        if (frost != null) {
            frostView.setText(R.string.frost);
            frostPriceBox.setText(R.string.frost_price);
            frostQuantityBox.setText(frost);
        }
    }

    public void readMocha() {
        ItemDBHandler dbHandler = new ItemDBHandler(this, null);
        String orderMocha = "Mocha";
        String mocha = dbHandler.readMocha(orderMocha);

        if (mocha != null) {
            mochaView.setText(R.string.mocha);
            mochaPriceBox.setText(R.string.price);
            mochaQuantityBox.setText(mocha);
        }
    }

    public void deleteOrder(View view) {
        ItemDBHandler dbHandler = new ItemDBHandler(this, null);
        String deleteEspresso = "Espresso";
        String deleteFrost = "Frost";
        String deleteMocha = "Mocha";
        boolean result1 = dbHandler.deleteEspresso(deleteEspresso);
        boolean result2 = dbHandler.deleteFrost(deleteFrost);
        boolean result3 = dbHandler.deleteMocha(deleteMocha);

        if (result1 || result2 || result3) {
            espressoView.setText("");
            espressoPriceBox.setText("");
            espressoQuantityBox.setText("");
            mochaView.setText("");
            mochaPriceBox.setText("");
            mochaQuantityBox.setText("");
            frostView.setText("");
            frostPriceBox.setText("");
            frostQuantityBox.setText("");
        }
    }
}
