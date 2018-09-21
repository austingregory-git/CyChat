package com.example.nirmiter.teststuff

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Button button = (Button) findViewById(R.id.button)
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                EditText firstNumEditText = (EditTextcd) findViewById(R.id.editText);
                EditText secondNumEditText = (EditText) findViewById(R.id.editText2);
                TextView resultTextView = (TextView) findViewById(R.id.textView);

                int num1 = Integer.parseInt(firstNumEditText.getText().toString());
                int num2 = Integer.parseInt(secondNumEditText.getText().toString());
                int result = num1 - num2;

                resultTextView.setText(result);

            }
        }

    }
}
