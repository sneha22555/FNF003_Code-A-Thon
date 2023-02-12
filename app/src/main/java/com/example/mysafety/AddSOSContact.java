package com.example.mysafety;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class AddSOSContact extends AppCompatActivity {

    Button submitdetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_soscontact);

        submitdetails = findViewById(R.id.submitSOSdetails);
        submitdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(AddSOSContact.this, "Friend Added", Toast.LENGTH_SHORT).show();
            }
        });
    }
}