package edu.iastate.linux.git.ui;

import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

public class RegistrationActivity extends AppCompatActivity {
     TextView mTextMessage;
    private static final String PROTOCOL_CHARSET = "utf-8";
    private TextView  realName;
    private TextView  userName;
    private TextView userPass;
    private TextView userEmail;
    private TextView userType;
    private TextView userID;
    RadioGroup rg;
    RadioButton rb;
   // private String url = "http://proj309-ds-01.misc.iastate.edu:8080/user";
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:
                    mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        Button regB = (Button) findViewById(R.id.registerButton);
        realName = findViewById(R.id.regName);
        userName = findViewById(R.id.regUsername);
        userPass = findViewById(R.id.regPassword);
        userEmail = findViewById(R.id.regEmail);
        userID = findViewById(R.id.regId);
        rg = (RadioGroup) findViewById(R.id.rgroup);
        regB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    registerUser();

            }
        });
    }

    private void registerUser() {
        final int iD = Integer.parseInt(userID.getText().toString().trim());
        final String username = userName.getText().toString().trim();
        final String password = userPass.getText().toString().trim();
        final String useremail = userEmail.getText().toString().trim();
        final String realname = realName.getText().toString().trim();

        rb = findViewById(rg.getCheckedRadioButtonId());
        final String userType = String.valueOf(rb.getText());

        Log.d("userstatus",userType);

        //first we will do the validations
        Log.d("identity",Integer.toString(iD));
        if (Integer.toString(iD) == "" || !isStringInt(Integer.toString(iD)))
        {
            Log.d("identity",Integer.toString(iD));
            userID.setError("Please enter an integer");
            userID.requestFocus();
            return;
        }

        /*
        if (TextUtils.isDigitsOnly(Integer.toString(iD)))
        {
            Log.d("identity",Integer.toString(iD));
            userID.setError("Please enter an integer");
            userID.requestFocus();
            return;
        }
        if (TextUtils.isEmpty(username)) {
            userName.setError("Please enter username");
            userName.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            userPass.setError("Please enter your email");
            userPass.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(Integer.toString(userage)) || isStringInt(Integer.toString(userage)) )
        {
            userAge.setError("Please enter an integer");
            userAge.requestFocus();
            return;
        }

        if (TextUtils.isEmpty(realname)) {
            realName.setError("Enter your real name ");
            realName.requestFocus();
            return;
        }
        */
        try{
            final JSONObject registerUser = new JSONObject();
            registerUser.put("id", iD);
            registerUser.put("username", username);
            registerUser.put("password",password);
            registerUser.put("email",useremail);
            registerUser.put("type",userType);
            registerUser.put("name",realname);


            Log.d("email",registerUser.toString());
            //creating a new user object
            User user = new User
                    (
                    registerUser.getInt("id"),
                    registerUser.getString("username"),
                    registerUser.getString("password"),
                    registerUser.getString("email"),
                    registerUser.getString("type"),
                    registerUser.getString("name")
                    );
            Log.d("email",user.getEmail().getClass().getName());
            Log.d("email",registerUser.getString("email").getClass().getName());
            //storing the user in shared preferences
            CurrentLoggedInUser.getInstance(getApplicationContext()).userLogin(user);
            Log.d("UserID",Integer.toString(CurrentLoggedInUser.getInstance(this).getUser().getId()));
            Log.d("UserName",CurrentLoggedInUser.getInstance(this).getUser().getUserName());
            Log.d("UserPass",CurrentLoggedInUser.getInstance(this).getUser().getUserPassword());

            //Now for the object request
            JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.POST, URLConstants.JSON_URL, registerUser, new Response.Listener<JSONObject>() {

                                @Override
                                public void onResponse(JSONObject response) {
                                    Log.d("Danrysresponse",response.toString());
                                    Toast.makeText(getApplicationContext(), "Response: " + response.toString(), Toast.LENGTH_SHORT).show();
                                }


                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    error.printStackTrace();
                                    Log.d("Nateserror",error.toString());
                                   // Toast.makeText(RegistrationActivity.this, "Error...", Toast.LENGTH_SHORT).show();
                                }
                            });
            VolleySingleton.getInstance(RegistrationActivity.this).addToRequestQue(jsonObject);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        Intent i1 = new Intent(RegistrationActivity.this, WelcomeActivity.class);
        startActivity(i1);

    }

    public boolean isStringInt(String s)
    {
        try
        {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException ex)
        {
            Toast.makeText(RegistrationActivity.this, "ID number and Age must be numbers", Toast.LENGTH_SHORT).show();
            return false;
        }
    }

}

