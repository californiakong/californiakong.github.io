package com.cs360.brycezimbelman.mochamoment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.facebook.AccessToken;
import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.login.LoginManager;
import com.facebook.login.LoginResult;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Collections;

public class LoginActivity extends AppCompatActivity {

    CallbackManager callbackManager = CallbackManager.Factory.create();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in);

        AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();

        if (!isLoggedIn) {
            LoginManager.getInstance().logInWithReadPermissions(this, Collections.singletonList("public_profile"));
        }


        Button logInButton = findViewById(R.id.log_in);
        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Execute Log In Button
                EditText username = findViewById(R.id.username);
                EditText password = findViewById(R.id.password);
                Context context = LoginActivity.this;
                SharedPreferences sharedPref = context.getSharedPreferences(
                        getString(R.string.preference_file_key), Context.MODE_PRIVATE);
                if (username.getText().toString().equals(sharedPref.getString(getString(R.string.username), null)) && password.getText().toString().equals(sharedPref.getString(getString(R.string.password), null))) {
                    Intent mainMenuIntent = new Intent(LoginActivity.this, MainMenuActivity.class);
                    startActivity(mainMenuIntent);
                }
                else {
                    Toast.makeText(LoginActivity.this, "Account Not Found", Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button createAccount = findViewById(R.id.create_account);
        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Execute create an account button
                Intent createAccountIntent = new Intent(LoginActivity.this, CreateAccountActivity.class);
                startActivity(createAccountIntent);
            }
        });

        LoginManager.getInstance().registerCallback(callbackManager,
                new FacebookCallback<LoginResult>() {
                    @Override
                    public void onSuccess(LoginResult loginResult) {
                        // On successful login or if already logged in continue to main menu
                        Intent mainMenuIntent = new Intent(LoginActivity.this, MainMenuActivity.class);
                        startActivity(mainMenuIntent);
                    }

                    @Override
                    public void onCancel() {
                        // Display message if log-in is cancelled
                        Toast.makeText(LoginActivity.this, "Log-In Canceled", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(FacebookException exception) {
                        // Display message if log in fails
                        Toast.makeText(LoginActivity.this, "Log-In Failed", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        callbackManager.onActivityResult(requestCode, resultCode, data);
        super.onActivityResult(requestCode, resultCode, data);
    }

}
