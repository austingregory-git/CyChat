package com.example.austin.spinnerwithtoasttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

public class SpinnerWithToast extends AppCompatActivity {
    Spinner spin;
    Button buttonSpinVal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_spinner_with_toast);
        spin = (Spinner) findViewById(R.id.spinOptions);
        buttonSpinVal = (Button) findViewById(R.id.buttonSpinVal);
        loadSpinner();
    }

    private void loadSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.spinner_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_spinner_with_toast, menu);
        return true;
    }

    public void displayVal(View v) {
        Toast.makeText(this, spin.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
    }
}
