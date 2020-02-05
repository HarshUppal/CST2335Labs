package com.example.cst2335labs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

public class ProfileActivity extends AppCompatActivity {
    static final int REQUEST_IMAGE_CAPTURE = 1;
    ImageButton mImageButton;
    Button goToChatButton;

    public static final String onCreate  = "onCreate";
    public static final String onStart   = "onStart";
    public static final String onResume  = "onResume";
    public static final String onPause   = "onPause";
    public static final String onStop    = "onStop";
    public static final String onDestroy = "onDestroy";
    public static final String onActivityResult = "onActivityResult";
    public static final String ACTIVITY_NAME = "PROFILE_ACTIVITY";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Log.e(ACTIVITY_NAME, "In function: " + onCreate);

        mImageButton = (ImageButton)findViewById(R.id.picButton);

        mImageButton.setOnClickListener( (new View.OnClickListener() {
            public final void onClick(View it) {
                dispatchTakePictureIntent();
            }
        }));

        goToChatButton = (Button)findViewById(R.id.goToChatButton);

        goToChatButton.setOnClickListener( (new View.OnClickListener() {
            public final void onClick(View it) {
                dispatchChatRoomIntent();
            }
        }));

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(ACTIVITY_NAME, "In function: " + onStart);

        Intent fromMain = getIntent();
        String emailAddress = fromMain.getStringExtra("EMAIL");

        EditText editText = findViewById(R.id.yourEmailEdit);

        editText.setText(emailAddress);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(ACTIVITY_NAME, "In function: " + onResume);
    }

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    private void dispatchChatRoomIntent() {
        Intent goToChat = new Intent(ProfileActivity.this, ChatRoomActivity.class);
        startActivity(goToChat);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.e(ACTIVITY_NAME, "In function: " + onActivityResult);

        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageButton.setImageBitmap(imageBitmap);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(ACTIVITY_NAME, "In function: " + onPause);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(ACTIVITY_NAME, "In function: " + onStop);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(ACTIVITY_NAME, "In function: " + onDestroy);
    }
}