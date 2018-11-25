package edu.iastate.linux.git.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
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
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ContactsActivity extends AppCompatActivity {

    private TextView friendslist;
    private String temp = "";
    private Button addFriend;
    private TextView friendIDNumber;
    private String friendTempNum = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts);

        ImageButton homeB = (ImageButton) findViewById(R.id.homeButton);
        ImageButton contactsB = (ImageButton) findViewById(R.id.contactsButton);
        ImageButton notifsB = (ImageButton) findViewById(R.id.notificationsButton);
        addFriend = findViewById(R.id.addFriendButton);
        friendIDNumber = findViewById(R.id.friendIDEditText);



        friendslist = (TextView)findViewById(R.id.friendDisplay);
        friendslist.setMovementMethod(new ScrollingMovementMethod());
        displayFriends();

        addFriend.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                friendTempNum = friendIDNumber.getText().toString();
                if(friendTempNum.length() > 0)
                {
                    addNewFriend();
                    friendIDNumber.setText("");

                    displayFriends();
                }
                else
                    {
                        Toast.makeText(getApplicationContext(), "Add their ID number!", Toast.LENGTH_SHORT).show();
                     }

            }
        });


        homeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(ContactsActivity.this, HomeActivity.class);
                startActivity(i1);
            }
        });

        contactsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(ContactsActivity.this, ContactsActivity.class);
                startActivity(i2);
            }
        });

        notifsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(ContactsActivity.this, NotificationsActivity.class);
                startActivity(i3);
            }
        });
    }

    private void displayFriends()
    {
        friendslist.setText("");

        String newURL = URLConstants.FRIEND_DISPLAY_URL + CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getId();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, newURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONArray jsonArray = response;
                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject employee = jsonArray.getJSONObject(i);
                                temp += "Friend " + i + ": \n" + "    UserID:" + employee.getInt("id") + "\n" + "    UserName: " + employee.getString("username") + "\n" +
                                        "    UserType: " + employee.getString("type") + "\n" + "    UserEmail: " + employee.getString("email") + "\n" + "    Name: " + employee.getString("name") + "\n\n\n";
                                Log.d("employee",employee.toString());

                            }
                            friendslist.setText(temp);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                        catch (IllegalStateException e){
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
        VolleySingleton.getInstance(ContactsActivity.this).addToRequestQue(request);
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
                Intent i1 = new Intent(ContactsActivity.this, SettingsActivity.class);
                startActivity(i1);
                return(true);
            case R.id.options:
                Intent i2 = new Intent(ContactsActivity.this, OptionsActivity.class);
                startActivity(i2);
                return(true);
            case R.id.map:
                Intent i3 = new Intent(ContactsActivity.this, GoogleMapActivity.class);
                startActivity(i3);
                return(true);
            case R.id.chatroom:
                Intent i5 = new Intent(ContactsActivity.this, ChatRoom.class);
                startActivity(i5);
                return(true);
            case R.id.userprofile:
                Intent i6 = new Intent(ContactsActivity.this,UserProfileActivity.class);
                startActivity(i6);
                return(true);
            case R.id.exit:
                finish();
                System.exit(0);
                return(true);
            case R.id.log_out:
                Intent i4 = new Intent(ContactsActivity.this, LoginActivity.class);
                startActivity(i4);
                return(true);
        }
        return super.onOptionsItemSelected(item);
    }

    private void addNewFriend()
    {

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URLConstants.ADD_FRIEND + CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getId() + "/" + friendTempNum,
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


                VolleySingleton.getInstance(ContactsActivity.this).addToRequestQue(stringRequest);
    }

}
