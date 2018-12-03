package edu.iastate.linux.git.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
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
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.security.acl.Group;
import java.util.ArrayList;
import java.util.Arrays;

import edu.iastate.linux.git.ui.Utils.ChatAdapter;
import edu.iastate.linux.git.ui.Utils.GroupChatAdapter;
import edu.iastate.linux.git.ui.Utils.LetterImageView;

import static edu.iastate.linux.git.ui.MyWeekActivity.selectedDay;

import java.nio.file.attribute.UserPrincipal;

public class GroupConversationsActivity extends AppCompatActivity {

    private TextView mTextMessage;
    private RecyclerView rv;
    public static SharedPreferences sharedPreferences;
    public static String selectedConversation = "MY_CONVO";
    public static ArrayList<String> groupNames = new ArrayList<String>();
    public static ArrayList<String> groupStringID = new ArrayList<String>();
    public static ArrayList<Integer> groupID = new ArrayList<Integer>();
    private int chatType;

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
        setContentView(R.layout.activity_group_conversations);

        initButtonNavigation();
        initViews();
        initListView();


    }

    private void initButtonNavigation() {
        ImageButton homeB = (ImageButton) findViewById(R.id.homeButton);
        ImageButton contactsB = (ImageButton) findViewById(R.id.contactsButton);
        ImageButton notifsB = (ImageButton) findViewById(R.id.notificationsButton);

        homeB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i1 = new Intent(GroupConversationsActivity.this, HomeActivity.class);
                startActivity(i1);
            }
        });

        contactsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(GroupConversationsActivity.this, ContactsActivity.class);
                startActivity(i2);
            }
        });

        notifsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(GroupConversationsActivity.this, NotificationsActivity.class);
                startActivity(i3);
            }
        });
    }

    private void initViews() {
        rv = (RecyclerView) findViewById(R.id.recyclerViewGroupChat);
        sharedPreferences = getSharedPreferences("MY_GROUP", MODE_PRIVATE);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
    }

    private void initListView() {
        //String[] chatNames = getResources().getStringArray(R.array.chatNames);
        //String[] chatMsg = getResources().getStringArray(R.array.chatMsg);

        //String newURL = "http://www.json-generator.com/api/json/get/bVRpsiJaOG?indent=2";
        //String testURL = "http://pastebin.com/raw/Em972E5s";
        String newURL = "http://proj309-ds-01.misc.iastate.edu:8080/group/" + CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getId();
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, newURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for(int i=0; i<response.length(); i++) {
                                JSONObject obj = response.getJSONObject(i);
                                if(!groupNames.contains(obj.getString("name")) /*&& (obj.getString("name").length() != 0)*/) {
                                    groupNames.add(obj.getString("name"));
                                }
                                if(!groupID.contains(obj.getInt("id"))) {
                                    groupID.add(obj.getInt("id"));
                                    groupStringID.add(Integer.toString(obj.getInt("id")));
                                }

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


        GroupChatAdapter mAdapter = new GroupChatAdapter(GroupConversationsActivity.this, groupNames, groupStringID);
        rv.setAdapter(mAdapter);

        mAdapter.setOnItemClickListener(new GroupChatAdapter.ClickListener() {
            @Override
            public void OnItemClick(int position, View v) {
                sharedPreferences.edit().putInt(selectedConversation, groupID.get(position)).apply();
                startActivity(new Intent(GroupConversationsActivity.this, GroupChatActivity.class));
            }
        });

    }


//    public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.MyViewHolder> {
//
//        private Context context;
//        private String[] chatNames, chatMsg;
//        public static edu.iastate.linux.git.ui.Utils.ChatAdapter.ClickListener clickListener;
//
//        public ChatAdapter(Context context, String[] names, String[] msg) {
//            this.context = context;
//            this.chatNames = names;
//            this.chatMsg = msg;
//        }
//
//        public void setOnItemClickListener(edu.iastate.linux.git.ui.Utils.ChatAdapter.ClickListener clickListener) {
//            ChatAdapter.clickListener = clickListener;
//        }
//
//        @NonNull
//        @Override
//        public ChatAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//            // inflate the item Layout
//            View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.chat_item, parent, false);
//            MyViewHolder vh = new MyViewHolder(v); // pass the view to View Holder
//
//            return vh;
//        }
//
//        @Override
//        public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
//            holder.name.setText(chatNames[position]);
//            holder.msg.setText(chatMsg[position]);
//            holder.liv.setOval(true);
//            holder.liv.setLetter(chatNames[position].charAt(0));
//        }
//
//        @Override
//        public int getItemCount() {
//            return chatNames.length;
//        }
//
//        public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//            public TextView name;// init the item view's
//            public TextView msg;
//            public LetterImageView liv;
//            public MyViewHolder(View itemView) {
//                super(itemView);
//                itemView.setOnClickListener(this);
//                // get the reference of item view's
//                name = (TextView) itemView.findViewById(R.id.textChatName);
//                msg = (TextView) itemView.findViewById(R.id.textChatMsg);
//                liv = (LetterImageView) itemView.findViewById(R.id.imageChat);
//
//            }
//
//            @Override
//            public void onClick(View v) {
//                clickListener.onItemClick(getAdapterPosition(), v);
//            }
//        }
//    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topright_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.settings:
                Intent i1 = new Intent(GroupConversationsActivity.this, SettingsActivity.class);
                startActivity(i1);
                return(true);
            case R.id.options:
                Intent i2 = new Intent(GroupConversationsActivity.this, OptionsActivity.class);
                startActivity(i2);
                return(true);
            case R.id.map:
                Intent i3 = new Intent(GroupConversationsActivity.this, GoogleMapActivity.class);
                startActivity(i3);
                return(true);
            case R.id.schedule:
                Intent i4 = new Intent(GroupConversationsActivity.this, ScheduleActivity.class);
                startActivity(i4);
                return(true);
            case R.id.exit:
                finish();
                System.exit(0);
                return(true);
            case R.id.log_out:
                Intent i5 = new Intent(GroupConversationsActivity.this, LoginActivity.class);
                startActivity(i5);
                return(true);
            case R.id.userprofile:
                Intent i6 = new Intent(GroupConversationsActivity.this,UserProfileActivity.class);
                startActivity(i6);
                return(true);
            case R.id.groupchatroom:
                Intent i7 = new Intent(GroupConversationsActivity.this, GroupConversationsActivity.class);
                startActivity(i7);
                return(true);
        }
        return super.onOptionsItemSelected(item);
    }
}
