package com.example.health;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MedicationDetails extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medication_details);
        TextView medicationDetailsTextView = findViewById(R.id.medicationDetailsTextView);

        Intent intent = getIntent();
        if (intent != null) {
            String medicationDetails = intent.getStringExtra("medicationDetails");
            medicationDetailsTextView.setText(medicationDetails);
        }
    }
}