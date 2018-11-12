package edu.iastate.linux.git.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.text.TextUtils;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class LoginActivity extends AppCompatActivity {

    private TextView mTextMessage;
    String friendID;
    TextView userName, password;
    Button login, register;
    Spinner menu;
    // String url = "http://proj309-ds-01.misc.iastate.edu:8080/user";
    boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        userName = findViewById(R.id.usernameLogin);
        password = findViewById(R.id.passwordLogin);
        login = (Button) findViewById(R.id.loginButton);
        register = (Button) findViewById(R.id.registrationButton);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //Adding this so we can progress while server is down, else delete and default to the conditional
                // Intent i1 = new Intent(LoginActivity.this, HomeActivity.class);
                // startActivity(i1);
                if (properLogin()) {
                    // flag = false;

                    Intent i1 = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(i1);
                }
                else{
                    //Toast.makeText(getApplicationContext(), "Make sure you're using a valid username and password", Toast.LENGTH_LONG).show();

                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(LoginActivity.this, RegistrationActivity.class);
                startActivity(i2);
            }
        });

    }
    /*
    public String getFriendID(){

        final String friendName = "";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URLConstants.JSON_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONArray jsonArray = response;
                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject employee = jsonArray.getJSONObject(i);
                                //Log.d("object",employee.toString());
                                if (friendName.equals(employee.getString("name"))) {
                                    friendID = Integer.toString(employee.getInt("id"));


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
        VolleySingleton.getInstance(LoginActivity.this).addToRequestQue(request);
        return friendID;
    }
    */


    public boolean properLogin()
    {
        final String loginName = userName.getText().toString();
        final String loginPass = password.getText().toString();

        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URLConstants.JSON_URL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONArray jsonArray = response;
                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject employee = jsonArray.getJSONObject(i);
                                //Log.d("object",employee.toString());
                                if (loginName.equals(employee.getString("username")) && loginPass.equals(employee.getString("password"))) {
                                    Log.d("object",employee.toString());
                                    flag = true;
                                    //Then make sure that they get logged in

                                    //creating a new user object
                                    User user = new User(
                                            employee.getInt("id"),
                                            employee.getString("username"),
                                            employee.getString("password"),
                                            employee.getString("type"),
                                            employee.getString("email"),
                                            employee.getString("name")
                                    );

                                    //storing the user in shared preferences
                                    CurrentLoggedInUser.getInstance(getApplicationContext()).userLogin(user);
                                    Log.d("loggedinfo",CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().toString());
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
        VolleySingleton.getInstance(LoginActivity.this).addToRequestQue(request);
        return flag;

    }
}


