package com.cs360.brycezimbelman.mochamoment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreateAccountActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_account);

        ImageButton logoButton = findViewById(R.id.logo_button);
        logoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Execute logo button
                Intent loginIntent = new Intent(CreateAccountActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });


        Button accountButton = findViewById(R.id.createAccount);
        // Execute create account button
        accountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Initialize username EditText to retrieve username
                EditText newUsername = findViewById(R.id.create_username);
                // Initialize password EditText to retrieve password
                EditText newPassword = findViewById(R.id.create_password);

                // Checks if username is entered
                if (newUsername.getText().toString().isEmpty()) {
                    Toast.makeText(CreateAccountActivity.this, "Enter valid username", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Checks if password is entered
                if(newPassword.getText().toString().isEmpty()) {
                    Toast.makeText(CreateAccountActivity.this, "Enter valid password", Toast.LENGTH_SHORT).show();
                    return;
                }

                Context context = CreateAccountActivity.this;
                // Initialize preferences
                SharedPreferences sharedPref = context.getSharedPreferences(
                        getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPref.edit();
                // Save username
                editor.putString(getString(R.string.username), newUsername.getText().toString());
                // Save password
                editor.putString(getString(R.string.password), newPassword.getText().toString());
                editor.apply();
                Toast.makeText(CreateAccountActivity.this, "Account Created", Toast.LENGTH_SHORT).show();
                Intent loginIntent = new Intent(CreateAccountActivity.this, LoginActivity.class);
                startActivity(loginIntent);
            }
        });
    }
}
