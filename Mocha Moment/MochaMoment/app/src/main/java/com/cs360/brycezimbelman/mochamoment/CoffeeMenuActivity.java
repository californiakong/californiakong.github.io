package com.cs360.brycezimbelman.mochamoment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import androidx.appcompat.app.AppCompatActivity;

public class CoffeeMenuActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coffee_menu);

        ImageButton logoButton = findViewById(R.id.logo_button);
        logoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Execute logo button
                Intent mainMenuIntent = new Intent(CoffeeMenuActivity.this, MainMenuActivity.class);
                startActivity(mainMenuIntent);

            }
        });

        Button backButton = findViewById(R.id.go_back);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent mainMenuIntent = new Intent(CoffeeMenuActivity.this, MainMenuActivity.class);
                startActivity(mainMenuIntent);
            }
        });
    }

}
