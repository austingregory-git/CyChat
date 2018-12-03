package edu.iastate.linux.git.ui;

import android.content.Context;
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

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import edu.iastate.linux.git.ui.Utils.LetterImageView;

public class ContactsActivity extends AppCompatActivity {

    private TextView friendslist;
    private String temp = "";
    private Button addFriend;
    private TextView friendIDNumber;
    private String friendTempNum = "";


    private TextView mTextMessage;
    private RecyclerView rv;
    public static ArrayList<String> contactNames = new ArrayList<String>();
    public static ArrayList<String> contactDet = new ArrayList<String>();

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
        setContentView(R.layout.activity_contacts);

        initButtonNavigation();
        //initViews();
        //initListView();

    }

    private void initButtonNavigation() {
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


    private void displayFriends() {
        friendslist.setText("");

        String newURL = URLConstants.FRIEND_DISPLAY_URL + CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getId();
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, newURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            JSONArray jsonArray = response;
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject employee = jsonArray.getJSONObject(i);
                                temp += "Friend " + i + ": \n" + "    UserID:" + employee.getInt("id") + "\n" + "    UserName: " + employee.getString("username") + "\n" +
                                        "    UserType: " + employee.getString("type") + "\n" + "    UserEmail: " + employee.getString("email") + "\n" + "    Name: " + employee.getString("name") + "\n\n\n";
                                Log.d("employee", employee.toString());

                            }
                            friendslist.setText(temp);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        } catch (IllegalStateException e) {
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
        /*
    private void initViews() {
        rv = (RecyclerView) findViewById(R.id.recyclerViewContacts);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
    }

    private void initListView() {
        //String[] contactNames = getResources().getStringArray(R.array.contactNames);
        //String[] contactDet = getResources().getStringArray(R.array.contactDet);

        String newURL = URLConstants.FRIEND_DISPLAY_URL + CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getId();

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, newURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for(int i=0; i<response.length(); i++) {
                                JSONObject obj = response.getJSONObject(i);

                                if(!contactNames.contains(obj.getString("name"))) {
                                    contactNames.add(obj.getString("name"));
                                }

                                if(!contactDet.contains(obj.getString("username"))) {
                                    contactDet.add(obj.getString("username"));
                                }

                                //Log.d("FromMessage", chatFromMsg.get(0));

                            }
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
        requestQueue.add(request);

        ContactsAdapter mAdapter = new ContactsAdapter(ContactsActivity.this, contactNames, contactDet);
        rv.setAdapter(mAdapter);
    }


    public class ContactsAdapter extends RecyclerView.Adapter<ContactsAdapter.MyViewHolder> {

        private Context context;
        //private LayoutInflater lf;
        //private TextView title, desc;
        private LetterImageView liv;
        private ArrayList<String> contactNames, contactDet;

        public ContactsAdapter(Context context, ArrayList<String> names, ArrayList<String> det) {
            this.context = context;
            this.contactNames = names;
            this.contactDet = det;
        }

        @NonNull
        @Override
        public ContactsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // inflate the item Layout
            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);
            MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder

            return vh;
        }

        @Override
        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
            holder.name.setText(contactNames.get(position));
            holder.det.setText(contactDet.get(position));
            holder.liv.setOval(true);
            holder.liv.setLetter(contactNames.get(position).charAt(0));
        }

        @Override
        public int getItemCount() {
            return contactNames.size();
        }

        class MyViewHolder extends RecyclerView.ViewHolder {
            TextView name;// init the item view's
            TextView det;
            LetterImageView liv;
            public MyViewHolder(View itemView) {
                super(itemView);
                // get the reference of item view's
                name = (TextView) itemView.findViewById(R.id.textContactName);
                det = (TextView) itemView.findViewById(R.id.textContactDet);
                liv = (LetterImageView) itemView.findViewById(R.id.imageContact);

            }
        }

    }*/

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
            case R.id.schedule:
                Intent i4 = new Intent(ContactsActivity.this, ScheduleActivity.class);
                startActivity(i4);
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
                Intent i5 = new Intent(ContactsActivity.this, LoginActivity.class);
                startActivity(i5);
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
