package com.example.cst2335labs;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.snackbar.Snackbar;
import static com.google.android.material.snackbar.Snackbar.make;

public class MainActivity extends AppCompatActivity {
    String emailAddress;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_lab3);

        SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        emailAddress = sp.getString(TEXT, "");
        editText = findViewById(R.id.email);
        editText.setText(emailAddress);

        Button loginButton = findViewById(R.id.button);

        loginButton.setOnClickListener( (new View.OnClickListener() {
            public final void onClick(View it) {
                onPause();
                SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
                emailAddress = sp.getString(TEXT, "");

                Intent goToProfile = new Intent(MainActivity.this, ProfileActivity.class);
                goToProfile.putExtra("EMAIL",emailAddress);
                startActivity(goToProfile);
            }
        }));
    }

    protected void onPause() {
        super.onPause();

        SharedPreferences sp = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();

        editor.putString(TEXT, editText.getText().toString());

        editor.apply();

        Toast.makeText(this, "Saved", Toast.LENGTH_SHORT).show();
    }
}