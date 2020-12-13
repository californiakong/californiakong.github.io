package com.cs360.brycezimbelman.mochamoment;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

public class AboutActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.our_company);

        ImageButton logoButton = findViewById(R.id.logo_button);
        logoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Execute logo button
                Intent mainMenuIntent = new Intent(AboutActivity.this, MainMenuActivity.class);
                startActivity(mainMenuIntent);
            }
        });

        Button callButton = findViewById(R.id.call);
        callButton.setOnClickListener(new View.OnClickListener() {
            // Execute phone call button
            @Override
            public void onClick(View view) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:6082957085"));
                // Ensure that there's a phone call activity to handle the intent
                if (callIntent.resolveActivity(getPackageManager()) != null) {
                    // Error if permission is not granted
                    if (ActivityCompat.checkSelfPermission(AboutActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                        Toast.makeText(AboutActivity.this, "Call Permission Not Granted", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    startActivity(callIntent);
                }
            }
        });

        Button messageButton = findViewById(R.id.message);
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            // Execute send message button
            public void onClick(View view) {
                Intent messageIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts(
                        "mailto","steve@mochamoment.com", null));
                // Ensure that there's an email activity to handle the intent
                if (messageIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(messageIntent);
                }
            }
        });

        Button facebookPageButton = findViewById(R.id.facebook_page);
        facebookPageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            // Execute visit facebook page button
            public void onClick(View view) {
                Intent facebookPageIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.facebook.com/MochaMoment/"));
                startActivity(facebookPageIntent);
            }
        });

        Button reviewButton = findViewById(R.id.review);
        reviewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            // Execute leave a review button
            public void onClick(View view) {
                Intent reviewIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://search.google.com/local/writereview?placeid=ChIJie3Ci50ZBogRRkquat1yZ8Y"));
                startActivity(reviewIntent);
            }
        });
    }
}