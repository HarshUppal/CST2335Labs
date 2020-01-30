package com.example.cst2335labs;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class ProfileActivity extends AppCompatActivity {

    static final int REQUEST_IMAGE_CAPTURE = 1;
    private EditText emailText;

    public static final String onCreate  = "onCreate";
    public static final String onStart   = "onStart";
    public static final String onResume  = "onResume";
    public static final String onPause   = "onPause";
    public static final String onStop    = "onStop";
    public static final String onDestroy = "onDestroy";
    public static final String onActivityResult = "onActivityResult";



    private ImageButton mImageButton;
    public static final String ACTIVITY_NAME = "PROFILE_ACTIVITY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        emailText = (EditText) findViewById(R.id.emailTextID);
        mImageButton =(ImageButton) findViewById(R.id.imageButtonID);

        Intent intent = getIntent();
        emailText.setText(intent.getStringExtra("emailFromLastActivity"));

        mImageButton.setOnClickListener(e->{
            dispatchTakePictureIntent();
        });
        Log.e(ACTIVITY_NAME,"onCreate");

    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(ACTIVITY_NAME,"onActivityResult");
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageButton.setImageBitmap(imageBitmap);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(ACTIVITY_NAME, "In function: " + onStart);

        Intent fromMain = getIntent();
        String emailAddress = fromMain.getStringExtra("EMAIL");

        EditText editText = findViewById(R.id.emailTextID);

        editText.setText(emailAddress);
    }



    @Override
    protected void onResume() {

        super.onResume();
        Log.e(ACTIVITY_NAME,"onResume");
    }

    @Override
    protected void onStop() {

        super.onStop();
        Log.e(ACTIVITY_NAME,"onStop");
    }

    @Override
    protected void onDestroy() {

        super.onDestroy();
        Log.e(ACTIVITY_NAME,"onDestroy");
    }


}