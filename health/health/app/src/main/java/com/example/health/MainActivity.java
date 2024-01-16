package com.example.health;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.SystemClock;
import java.util.ArrayList;
import android.widget.TimePicker;

public class MainActivity extends AppCompatActivity {
    private EditText medicationName, dose;
    private Button addMedication;
    private ListView medicationList;
    private TimePicker timePicker;
    public class Medication {
        private String name;
        private String doses;
        private int hour;
        private int minute;

        public Medication(String name, String dose, int hour, int minute) {
            this.name = name;
            this.doses = doses;
            this.hour = hour;
            this.minute = minute;
        }
        public String getName() {
            return name;
        }
        public String getDose() {
            return doses;
        }
        public int getHour() {
            return hour;
        }

        public int getMinute() {
            return minute;
        }
    }
    private ArrayList<Medication> medications;
    private ArrayAdapter<Medication> medicationAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        medicationName = findViewById(R.id.medicationName);
        dose = findViewById(R.id.dose);
        addMedication = findViewById(R.id.addMedication);
        medicationList = findViewById(R.id.medicationList);
        timePicker = findViewById(R.id.timePicker);

        medications = new ArrayList<>();
        medicationAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, medications);
        medicationList.setAdapter(medicationAdapter);

        addMedication.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = medicationName.getText().toString();
                String doses = dose.getText().toString();
                int hour = timePicker.getCurrentHour();
                int minute = timePicker.getCurrentMinute();
                if (name.isEmpty() || doses.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                    return;
                }

                Medication medication = new Medication(name, doses,hour,minute);
                medications.add(medication);
                medicationAdapter.notifyDataSetChanged();
                medicationName.getText().clear();
                dose.getText().clear();
                String confirmationMessage = "Medication added successfully:\n\nMedication Name: " + name + "\nDose: " + doses+ "\nTime: " + hour + ":" + minute;
                showToast(confirmationMessage);
                openMedicationDetailsActivity(confirmationMessage);

            }
        });
    }
    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    private void openMedicationDetailsActivity(String details) {
        Intent intent = new Intent(this, MedicationDetails.class);
        intent.putExtra("medicationDetails", details);
        startActivity(intent);
    }

    }


