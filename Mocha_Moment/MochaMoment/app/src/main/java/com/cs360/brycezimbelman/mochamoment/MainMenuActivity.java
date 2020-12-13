package com.cs360.brycezimbelman.mochamoment;

import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static android.os.Environment.DIRECTORY_PICTURES;
import static android.os.Environment.getExternalStoragePublicDirectory;


public class MainMenuActivity extends AppCompatActivity {

    static final int REQUEST_TAKE_PHOTO = 1;
    String currentPhotoPath;
    int imageSaved = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);


        Button menuButton = findViewById(R.id.menu);
        menuButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // execute menu button
                Intent coffeeMenuIntent = new Intent(MainMenuActivity.this, CoffeeMenuActivity.class);
                startActivity(coffeeMenuIntent);
            }
        });

        Button orderButton = findViewById(R.id.add_order);
        orderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // execute order button
                Intent orderIntent = new Intent(MainMenuActivity.this, OrderActivity.class);
                startActivity(orderIntent);
            }
        });

        Button directionButton = findViewById(R.id.directions);
        directionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            // execute directions button
            public void onClick(View v) {
                Uri gmmIntentUri = Uri.parse("geo:42.39529,89.01502?q=" + Uri.encode("1121 Center Ave, Janesville"));
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                mapIntent.setPackage("com.google.android.apps.maps");
                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });

        Button companyButton = findViewById(R.id.company);
        companyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            // Execute company button
            public void onClick(View v) {
                Intent ourCompany = new Intent(MainMenuActivity.this, AboutActivity.class);
                startActivity(ourCompany);
            }
        });

        // Take picture with camera
        Button photoButton = findViewById(R.id.photo);
        photoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            // execute take picture feature
            public void onClick(View v) {
            dispatchTakePictureIntent();
            galleryAddPic();
            }

        });
    }

    @Override
    public void onResume() {
        super.onResume();
        // Only displays when returning from camera feature
        if (imageSaved == 1) {
            Toast.makeText(MainMenuActivity.this, "Picture Saved", Toast.LENGTH_SHORT).show();
            imageSaved = 0;
        }
    }

    // Sets up file and calls camera API
    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Ensure that there's a camera activity to handle the intent
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            // Create the File where the photo should go
            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {
                // Error occurred while creating the File
                System.out.println("Error: Unable to create file");
            }
            // Continue only if the File was successfully created
            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(this,
                        "com.cs360.android.fileprovider",
                        photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);
                imageSaved = 1;
            }
        }
    }

    private File createImageFile() throws IOException {
        // Create an image file name
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getExternalStoragePublicDirectory(DIRECTORY_PICTURES);
        File image = File.createTempFile(
                imageFileName,  /* prefix */
                ".jpg",         /* suffix */
                storageDir      /* directory */
        );

        // Save a file: path for use with ACTION_VIEW intents
        currentPhotoPath = image.getAbsolutePath();
        return image;
    }

    // Makes image accessible to other applications
    private void galleryAddPic() {
        Intent mediaScanIntent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        File f = new File(currentPhotoPath);
        Uri contentUri = Uri.fromFile(f);
        mediaScanIntent.setData(contentUri);
        this.sendBroadcast(mediaScanIntent);
    }
}

