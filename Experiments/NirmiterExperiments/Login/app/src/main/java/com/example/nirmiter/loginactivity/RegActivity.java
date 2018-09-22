package com.example.nirmiter.loginactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RegActivity extends AppCompatActivity {

    Button click;
   public static TextView data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reg);

        click = (Button) findViewById(R.id.jsonBTN);
        data = (TextView) findViewById(R.id.jsonTXT);

        click.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view){
                fetchData process = new fetchData();
                process.execute();
            }
        });
    }
}
