package com.example.nirmiter.loginactivity;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity {

    private EditText name;
    private EditText password;
    private Button login;
    private Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        name = (EditText) findViewById(R.id.nameTXT);
        password = (EditText) findViewById(R.id.passTXT);
        login = (Button) findViewById(R.id.logBTN);
        register = (Button) findViewById(R.id.regBTN);


        register.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v){
                openRegActivity();
            }
        });
        login.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                validate(name.getText().toString(), password.getText().toString());
            }
        });
    }


    public void openRegActivity()
    {
        Intent intent = new Intent(this, RegActivity.class);
        startActivity(intent);
    }

    private void validate(String userName, String userPassword)
    {
        if((userName == "Admin") && (userPassword == "1234"))
        {
            Intent intent = new Intent(LoginActivity.this,ChatActivity.class);
            startActivity(intent);
        }

    }
}
