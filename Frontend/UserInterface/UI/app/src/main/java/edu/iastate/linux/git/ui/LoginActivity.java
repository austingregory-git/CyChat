package edu.iastate.linux.git.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    private TextView mTextMessage;
    Button b1, b2;
    EditText uname, pword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        b1 = (Button) findViewById(R.id.loginButton);
        b2 = (Button) findViewById(R.id.registrationButton);
        uname = (EditText) findViewById(R.id.username);
        pword = (EditText) findViewById(R.id.password);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if((uname.getText().toString().equals("admin") &&
                    pword.getText().toString().equals("admin")) ||
                        (uname.getText().toString().equals("agregory") &&
                    pword.getText().toString().equals("waffles")) ||
                        (uname.getText().toString().equals("nirmiter") &&
                    pword.getText().toString().equals("pancakes"))) {
                    Intent i1 = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i1);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Can't log you in with what you entered", Toast.LENGTH_SHORT).show();
                }
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(i2);
            }
        });

    }

}
