package com.example.nirmiter.volleypractice;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class NextActivity extends AppCompatActivity {
    Button button;
    EditText Name;
    EditText Email;
    String server_url = "serverURLgoeshere/phpfilenamegoeshere";
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        button = (Button) findViewById(R.id.servBTN);
        Name = (EditText) findViewById(R.id.insertName);
        Email = (EditText) findViewById(R.id.insertEmail);
        builder = new AlertDialog.Builder(NextActivity.this);
        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                final String name, email;
                name = Name.getText().toString();
                email = Email.getText().toString();
                StringRequest stringRequest = new StringRequest(Request.Method.POST,server_url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response){
                    builder.setTitle("Server Response");
                        builder.setMessage("Response :"+response);
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Name.setText("");
                                Email.setText("");
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }, new Response.ErrorListener(){
                    @Override
                    public void onErrorResponse(VolleyError error){
                        error.printStackTrace();
                        Toast.makeText(NextActivity.this,"Error...",Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError{
                        Map<String,String> params = new HashMap<String,String>();
                        params.put("name",name);
                        params.put("email",email);
                        return params;
                    }
                };

                VolleySingleton.getInstance(NextActivity.this).addToRequestQue(stringRequest);
            }
        });
    }
}
