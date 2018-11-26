package edu.iastate.linux.git.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import edu.iastate.linux.git.ui.Utils.ChatAdapter;
import edu.iastate.linux.git.ui.Utils.ConvoAdapter;
import edu.iastate.linux.git.ui.Utils.LetterImageView;

import static edu.iastate.linux.git.ui.MyDayActivity.Monday;

public class ChatActivity extends AppCompatActivity {

    private RecyclerView rv;
    public static String[] AustinMessages;
    public static String[] NateMessages;
    public static String[] XiuyuanMessages;
    public static String[] ZhiMessages;

    public static String sel_convo;
    public static ArrayList<String> chatFromMsg = new ArrayList<String>();
    public static ArrayList<String> chatFromTime = new ArrayList<String>();
    public static ArrayList<String> chatToMsg = new ArrayList<String>();
    public static ArrayList<String> chatToTime = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

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
                Intent i1 = new Intent(ChatActivity.this, HomeActivity.class);
                startActivity(i1);
            }
        });

        contactsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(ChatActivity.this, ContactsActivity.class);
                startActivity(i2);
            }
        });

        notifsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(ChatActivity.this, NotificationsActivity.class);
                startActivity(i3);
            }
        });
    }

    private void initViews() {
        rv = (RecyclerView) findViewById(R.id.chatRecyclerView);
        //sharedPreferences = getSharedPreferences("MY_CONVO", MODE_PRIVATE);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
    }

    private void initListView() {
        AustinMessages = getResources().getStringArray(R.array.AustinMessages);
        NateMessages = getResources().getStringArray(R.array.NateMessages);
        XiuyuanMessages = getResources().getStringArray(R.array.XiuyuanMessages);
        ZhiMessages = getResources().getStringArray(R.array.ZhiMessages);

        String newURL = "http://www.json-generator.com/api/json/get/bUQybWKZvm?indent=2";

        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, newURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for(int i=0; i<response.length(); i++) {
                                JSONObject obj = response.getJSONObject(i);
                                sel_convo = obj.getString("Name");

                                if(!chatFromMsg.contains(obj.getString("FromMessage").split("\\+")[0])) {
                                    String[] fromMsgArr = obj.getString("FromMessage").split("\\+");
                                    Collections.addAll(chatFromMsg, fromMsgArr);
                                }

                                if(!chatToMsg.contains(obj.getString("ToMessage").split("\\+")[0])) {
                                    String[] toMsgArr = obj.getString("ToMessage").split("\\+");
                                    Collections.addAll(chatToMsg, toMsgArr);
                                }

                                if(!chatFromTime.contains(obj.getString("FromTime").split("\\+")[0])) {
                                    String[] fromTimeArr = obj.getString("FromTime").split("\\+");
                                    Collections.addAll(chatFromTime, fromTimeArr);
                                }

                                if(!chatToTime.contains(obj.getString("ToTime").split("\\+")[0])) {
                                    String[] toTimeArr = obj.getString("ToTime").split("\\+");
                                    Collections.addAll(chatToTime, toTimeArr);
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

        String currConvo = HomeActivity.sharedPreferences.getString(HomeActivity.selectedConversation, null);

        ConvoAdapter mAdapter = new ConvoAdapter(ChatActivity.this, sel_convo, chatToMsg, chatFromMsg, chatToTime, chatFromTime);
        rv.setAdapter(mAdapter);

    }

    /*public class ConvoAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater lf;
        private TextView message;//, time;
        private LetterImageView liv;
        private String[] messageArray;
        //private String[] timeArray;

        public SimpleAdapter(Context context, String[] messageArray) {
            mContext = context;
            this.messageArray = messageArray;
            //this.timeArray = timeArray;
            lf = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return messageArray.length;
        }

        @Override
        public Object getItem(int position) {
            return messageArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = lf.inflate(R.layout.my_day_activity_item, null);
            }

            message = (TextView) convertView.findViewById(R.id.textSubject);
            //time = (TextView) convertView.findViewById(R.id.textTime);
            liv = (LetterImageView) convertView.findViewById(R.id.imageDayDetails);

            message.setText(messageArray[position]);
            //time.setText(timeArray[position]);

            liv.setOval(true);
            liv.setLetter(messageArray[position].charAt(0));

            return convertView;
        }
    }
    */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.topright_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.settings:
                Intent i1 = new Intent(ChatActivity.this, SettingsActivity.class);
                startActivity(i1);
                return(true);
            case R.id.options:
                Intent i2 = new Intent(ChatActivity.this, OptionsActivity.class);
                startActivity(i2);
                return(true);
            case R.id.map:
                Intent i3 = new Intent(ChatActivity.this, GoogleMapActivity.class);
                startActivity(i3);
                return(true);
            case R.id.schedule:
                Intent i4 = new Intent(ChatActivity.this, ScheduleActivity.class);
                startActivity(i4);
                return(true);
            case R.id.exit:
                finish();
                System.exit(0);
                return(true);
            case R.id.log_out:
                Intent i5 = new Intent(ChatActivity.this, LoginActivity.class);
                startActivity(i5);
                return(true);
        }
        return super.onOptionsItemSelected(item);
    }
}
