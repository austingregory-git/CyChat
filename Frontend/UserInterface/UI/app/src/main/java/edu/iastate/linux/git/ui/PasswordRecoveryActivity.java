package edu.iastate.linux.git.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PasswordRecoveryActivity extends AppCompatActivity {

    String passSend;
    String first,second;
    TextView firstemail, secondemail;
    Button confirm, ret;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_recovery);
        firstemail = findViewById(R.id.emailEntry);
        secondemail = findViewById(R.id.emailConfirm);
        confirm = findViewById(R.id.sendEmailButton);
        ret = findViewById(R.id.returnbtn);

        ret.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent i2 = new Intent(PasswordRecoveryActivity.this, LoginActivity.class);
                 startActivity(i2);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                first = firstemail.getText().toString();
                second = secondemail.getText().toString();
                if(first.equals(second) && first.length() > 0){
                    firstemail.setText("");
                    secondemail.setText("");
                    passwordRetrieval();
                   // Toast.makeText(getApplicationContext(), "Email Sent!", Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(getApplicationContext(), "Make sure your emails match!", Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void passwordRetrieval(){

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URLConstants.JSON_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONArray jsonArray = response;
                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject employee = jsonArray.getJSONObject(i);
                                //Log.d("object",employee.toString());
                                if (first.equals(employee.getString("email"))) {
                                    Log.d("object",employee.toString());
                                    passSend = employee.getString("password");
                                    Log.d("objects",passSend);
                                    //Then make sure that they get logged in

                                }

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener()

        {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();

            }
        });
        VolleySingleton.getInstance(PasswordRecoveryActivity.this).addToRequestQue(request);

        //Then send the email to their stored email address
        sendMail();

    }

    private void sendMail(){
        Intent i = new Intent(Intent.ACTION_SEND);
        i.setType("message/rfc822");
        //i.setType("text/plain");
        i.putExtra(Intent.EXTRA_EMAIL  , new String[]{first});
        i.putExtra(Intent.EXTRA_SUBJECT, "Password Retrieval for Cychat");
        i.putExtra(Intent.EXTRA_TEXT   , "Hello! You requested to have your password emailed to you on our app! Here it is! \n\n Password: " + passSend);
        try {
            startActivity(Intent.createChooser(i, "Send mail..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getApplicationContext(), "There are no email clients installed.", Toast.LENGTH_SHORT).show();
        }
    }
}
