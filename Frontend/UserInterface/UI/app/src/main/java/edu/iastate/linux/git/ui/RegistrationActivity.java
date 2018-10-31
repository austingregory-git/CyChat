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
    private TextView userAge;
    private TextView userID;
    RadioGroup rg;
    RadioButton rb;
    private String url = "http://proj309-ds-01.misc.iastate.edu:8080/user";
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
        userAge = findViewById(R.id.regAge);
        userID = findViewById(R.id.regId);
        rg = (RadioGroup) findViewById(R.id.rgroup);
        regB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    registerUser();
                //Commenting out what I know works for our registration activity to try and get a single user
//
//                if (realName.length() > 0 && userName.length() > 0 && userPass.length() > 0 && userAge.length() > 0 && userID.length() > 0) {
//                   // if (isStringInt(userAge.toString()) && isStringInt(userID.toString())) {
//                        try {
//                            String tempage = userAge.getText().toString();
//                            int tempAge = Integer.parseInt(tempage);
//                            String tempid = userID.getText().toString();
//                            int tempID = Integer.parseInt(tempid);
//                            String name = realName.getText().toString();
//                            String pass = userPass.getText().toString();
//                            String yousername = userName.getText().toString();
//                            JSONObject registerUser = new JSONObject();
//                            registerUser.put("id", tempID);
//                            registerUser.put("username", yousername);
//                            //Log.d("usertag",yousername);
//                            registerUser.put("password", pass);
//                            //Log.d("passtag",registerUser.toString());
//                            Log.d("passtag",pass);
//                            registerUser.put("age", tempAge);
//                            registerUser.put("name", name);
//                            //Log.d("nametag",registerUser.toString());
//                            Log.d("nametag",name);
//
//                            Log.d("jsonfull",registerUser.toString());
//                            Toast.makeText(RegistrationActivity.this, "MakeJSONObject", Toast.LENGTH_SHORT).show();
//
//                            //TODO Bug Fix, End of input at character 0 but bug not affecting functionality
//                            JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.POST, url, registerUser, new Response.Listener<JSONObject>() {
//
//                                @Override
//                                public void onResponse(JSONObject response) {
//                                    Log.d("Danrysresponse",response.toString());
//                                    Toast.makeText(getApplicationContext(), "Response: " + response.toString(), Toast.LENGTH_SHORT).show();
//                                }
//
//
//                            }, new Response.ErrorListener() {
//                                @Override
//                                public void onErrorResponse(VolleyError error) {
//                                    error.printStackTrace();
//                                    Log.d("Nateserror",error.toString());
//                                   // Toast.makeText(RegistrationActivity.this, "Error...", Toast.LENGTH_SHORT).show();
//                                }
//                            });
//                            //What is the use of this HashMap?
//                            /*
//                        {
//                            @Override
//                            public Map<String, String> getHeaders() throws AuthFailureError {
//                            final Map<String, String> headers = new HashMap<>();
//                            headers.put("Authorization", "Basic " + "c2FnYXJAa2FydHBheS5jb206cnMwM2UxQUp5RnQzNkQ5NDBxbjNmUDgzNVE3STAyNzI=");//put your token here
//                            return headers;
//                        }
//                        };*/
//                            VolleySingleton.getInstance(RegistrationActivity.this).addToRequestQue(jsonObject);
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
//                        Intent i1 = new Intent(RegistrationActivity.this, WelcomeActivity.class);
//                        startActivity(i1);
//                    }
//                }
//           // }
//        });


            }
        });
    }
    private void registerUser() {
        final int iD = Integer.parseInt(userID.getText().toString().trim());
        final String username = userName.getText().toString().trim();
        final String password = userPass.getText().toString().trim();
        final int userage = Integer.parseInt(userAge.getText().toString().trim());
        final String realname = realName.getText().toString().trim();

        rb = findViewById(rg.getCheckedRadioButtonId());
        final String userStatus = String.valueOf(rb.getText());

        Log.d("userstatus",userStatus);
        // && Integer.toString(iD) != ""
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
            registerUser.put("age",userage);
            registerUser.put("name",realname);

            //creating a new user object
            User user = new User(
                    registerUser.getInt("id"),
                    registerUser.getString("username"),
                    registerUser.getString("password"),
                    registerUser.getInt("age"),
                    registerUser.getString("name")
                    //registerUser.getString("usertype")
                                    );

            //storing the user in shared preferences
            CurrentLoggedInUser.getInstance(getApplicationContext()).userLogin(user);
            Log.d("UserID",Integer.toString(CurrentLoggedInUser.getInstance(this).getUser().getId()));
            Log.d("UserName",CurrentLoggedInUser.getInstance(this).getUser().getUserName());
            Log.d("UserPass",CurrentLoggedInUser.getInstance(this).getUser().getUserPassword());
            Log.d("UserAge",Integer.toString(CurrentLoggedInUser.getInstance(this).getUser().getAge()));
            //Now for the object request
            JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.POST, url, registerUser, new Response.Listener<JSONObject>() {

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
    /*
    public void rbclick(View v)
    {
        int radiobuttonid = rg.getCheckedRadioButtonId();
        rb = (RadioButton) findViewById(radiobuttonid);

        Toast.makeText(getBaseContext(),rb.getText(),Toast.LENGTH_LONG).show();
    }
    */
}

