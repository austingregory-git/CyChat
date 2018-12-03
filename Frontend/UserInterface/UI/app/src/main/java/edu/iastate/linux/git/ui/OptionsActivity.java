package edu.iastate.linux.git.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class OptionsActivity extends AppCompatActivity {

    private TextView mTextMessage, uidentry,groupname,groupid;
    private String ustatus, identry,grpname,grpid;
    private Button creategroup, usergroupadd;

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
        setContentView(R.layout.activity_options);

        ImageButton homeB = (ImageButton) findViewById(R.id.homeButton);
        ImageButton contactsB = (ImageButton) findViewById(R.id.contactsButton);
        ImageButton notifsB = (ImageButton) findViewById(R.id.notificationsButton);
        uidentry = findViewById(R.id.userIDTxt);
        groupname = findViewById(R.id.groupNameTXT);
        groupid = findViewById(R.id.groupIDTxt);
        creategroup = findViewById(R.id.createGroupButton);
        usergroupadd = findViewById(R.id.addUserButton);
        ustatus = CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getUserType();
        creategroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grpname = groupname.getText().toString();
                grpid = groupid.getText().toString();
                if(ustatus.equals("Professor") && grpname.length() >= 0 && grpid.length() >= 0){
                    createRoom();
                    groupname.setText("");
                    groupid.setText("");
                }
                else{
                    Toast.makeText(OptionsActivity.this, "Fill out group name and ID fields. Professors only!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        usergroupadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                identry = uidentry.getText().toString();
                grpid = groupid.getText().toString();
                if((ustatus.equals("Professor") || (ustatus.equals("TA"))) && identry.length() >= 0 && grpid.length() >= 0){
                        addToGroup();
                        uidentry.setText("");
                        groupid.setText("");
                }
                else{
                    Toast.makeText(OptionsActivity.this, "Fill out group and user ID fields. No students!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        homeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(OptionsActivity.this, HomeActivity.class);
                startActivity(i1);
            }
        });

        contactsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(OptionsActivity.this, ContactsActivity.class);
                startActivity(i2);
            }
        });

        notifsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(OptionsActivity.this, NotificationsActivity.class);
                startActivity(i3);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topright_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.settings:
                Intent i1 = new Intent(OptionsActivity.this, SettingsActivity.class);
                startActivity(i1);
                return(true);
            case R.id.options:
                Intent i2 = new Intent(OptionsActivity.this, OptionsActivity.class);
                startActivity(i2);
                return(true);
            case R.id.map:
                Intent i3 = new Intent(OptionsActivity.this, GoogleMapActivity.class);
                startActivity(i3);
                return(true);
            case R.id.schedule:
                Intent i4 = new Intent(OptionsActivity.this, ScheduleActivity.class);
                startActivity(i4);
                return(true);
            case R.id.exit:
                finish();
                System.exit(0);
                return(true);
            case R.id.log_out:
                Intent i5 = new Intent(OptionsActivity.this, LoginActivity.class);
                startActivity(i5);
                return(true);
            case R.id.userprofile:
                Intent i6 = new Intent(OptionsActivity.this, UserProfileActivity.class);
                startActivity(i6);
                return(true);
        }
        return super.onOptionsItemSelected(item);
    }
    private void createRoom(){
        //what to send here?
        try{
            final JSONObject registerRoom = new JSONObject();
            registerRoom.put("id",Integer.parseInt(grpid) );
            registerRoom.put("name", grpname);
            registerRoom.put("creater",CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getId());
            registerRoom.put("manager","");

            JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.POST, URLConstants.CREATE_GROUP_URL,registerRoom , new Response.Listener<JSONObject>() {

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
            VolleySingleton.getInstance(OptionsActivity.this).addToRequestQue(jsonObject);
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }


    }
    private void addToGroup()
    {
        //TODO fix

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLConstants.ADD_FRIEND + identry + "/" + grpid  ,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the first 500 characters of the response string.
                        Toast.makeText(getApplicationContext(), "Friend added!", Toast.LENGTH_SHORT).show();
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Your friend couldn't be found!", Toast.LENGTH_LONG).show();
            }
        });


        VolleySingleton.getInstance(OptionsActivity.this).addToRequestQue(stringRequest);
    }
    }

