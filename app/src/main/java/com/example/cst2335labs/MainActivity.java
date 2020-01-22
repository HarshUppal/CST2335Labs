package com.example.cst2335labs;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.snackbar.Snackbar;

import static com.google.android.material.snackbar.Snackbar.make;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_grid);
        final CheckBox cb = (CheckBox) findViewById(R.id.checkbox);

        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb.isChecked()) {
                    Toast.makeText(MainActivity.this, "Here is more information", Toast.LENGTH_LONG).show();
                }
            }
        });



        final Switch switch1 = (Switch) findViewById(R.id.switch1);
        switch1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, final boolean isChecked) {
                String text = null;
                if (isChecked) {
                    text = " on";
                    switch1.setChecked(true);
                } else {
                    text = " off";
                    switch1.setChecked(false);
                }
                make(buttonView, "the switch is now " + text, Snackbar.LENGTH_LONG).setAction("UNDO", new View.OnClickListener() {
                    public void onClick(View v) {
                        if (isChecked) {
                            switch1.setChecked(false);
                        } else
                            switch1.setChecked(true);
                        make(findViewById(R.id.switch1), " ", Snackbar.LENGTH_LONG).show();


                    }

                }).show();


            }
        });
    }
}