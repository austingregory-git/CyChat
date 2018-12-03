package edu.iastate.linux.git.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.ArrayList;

import edu.iastate.linux.git.ui.Utils.LetterImageView;

public class MyDayActivity extends AppCompatActivity {

    private ListView lv;
    public static String[] Monday;
    public static String[] Tuesday;
    public static String[] Wednesday;
    public static String[] Thursday;
    public static String[] Friday;
    public static String[] Saturday;
    public static String[] Sunday;
    public static ArrayList<String> mondayTime = new ArrayList<String>();
    public static ArrayList<String> tuesdayTime = new ArrayList<String>();
    public static ArrayList<String> wednesdayTime = new ArrayList<String>();
    public static ArrayList<String> thursdayTime = new ArrayList<String>();
    public static ArrayList<String> fridayTime = new ArrayList<String>();
    public static ArrayList<String> saturdayTime = new ArrayList<String>();
    public static ArrayList<String> sundayTime = new ArrayList<String>();
    public static ArrayList<String> mondaySub = new ArrayList<String>();
    public static ArrayList<String> tuesdaySub = new ArrayList<String>();
    public static ArrayList<String> wednesdaySub = new ArrayList<String>();
    public static ArrayList<String> thursdaySub = new ArrayList<String>();
    public static ArrayList<String> fridaySub = new ArrayList<String>();
    public static ArrayList<String> saturdaySub = new ArrayList<String>();
    public static ArrayList<String> sundaySub = new ArrayList<String>();
    public static ArrayList<String> sel_day;
    public static ArrayList<String> sel_time;
    public static ArrayList<String> tempSub = new ArrayList<String>();
    public static ArrayList<String> tempTime = new ArrayList<String>();




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_day);

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
                Intent i1 = new Intent(MyDayActivity.this, HomeActivity.class);
                startActivity(i1);
            }
        });

        contactsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(MyDayActivity.this, ContactsActivity.class);
                startActivity(i2);
            }
        });

        notifsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(MyDayActivity.this, NotificationsActivity.class);
                startActivity(i3);
            }
        });
    }

    private void initViews() {
        lv = (ListView) findViewById(R.id.listMyDay);
    }

    private void initListView() {
        /*Monday = getResources().getStringArray(R.array.Monday);
        Tuesday = getResources().getStringArray(R.array.Tuesday);
        Wednesday = getResources().getStringArray(R.array.Wednesday);
        Thursday = getResources().getStringArray(R.array.Thursday);
        Friday = getResources().getStringArray(R.array.Friday);
        Saturday = getResources().getStringArray(R.array.Saturday);
        Sunday = getResources().getStringArray(R.array.Sunday);

        mondayTime = getResources().getStringArray(R.array.mondayTime);
        tuesdayTime = getResources().getStringArray(R.array.tuesdayTime);
        wednesdayTime = getResources().getStringArray(R.array.wednesdayTime);
        thursdayTime = getResources().getStringArray(R.array.thursdayTime);
        fridayTime = getResources().getStringArray(R.array.fridayTime);
        saturdayTime = getResources().getStringArray(R.array.saturdayTime);
        sundayTime = getResources().getStringArray(R.array.sundayTime);*/

        //String newURL = "http://www.json-generator.com/api/json/get/ceyeFecdCG?indent=2";
        //String newURL = "http://www.json-generator.com/api/json/get/cdORCPfemq?indent=2";
        //String newURL = "http://www.json-generator.com/api/json/get/cuuTfuQWcy?indent=2";
        String newURL = "http://proj309-ds-01.misc.iastate.edu:8080/subject/" + CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getId();
        //String testURL = "http://pastebin.com/raw/Em972E5s";
        RequestQueue requestQueue = Volley.newRequestQueue(getApplicationContext());
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, newURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {

                        try {
                            for(int i=0; i<response.length(); i++) {
                                JSONObject obj = response.getJSONObject(i);
                                String day = obj.getString("day");
                                if(day.equalsIgnoreCase("monday")) {
                                    if(!mondaySub.contains(obj.getString("subject"))) {
                                        mondaySub.add(obj.getString("subject"));
                                    }
                                    if(!mondayTime.contains(obj.getString("time"))) {
                                        mondayTime.add(obj.getString("time"));
                                    }
                                }
                                else if(day.equalsIgnoreCase("tuesday")) {
                                    if(!tuesdaySub.contains(obj.getString("subject"))) {
                                        tuesdaySub.add(obj.getString("subject"));
                                    }
                                    if(!tuesdayTime.contains(obj.getString("time"))) {
                                        tuesdayTime.add(obj.getString("time"));
                                    }
                                }
                                else if(day.equalsIgnoreCase("Wednesday")) {
                                    if(!wednesdaySub.contains(obj.getString("subject"))) {
                                        wednesdaySub.add(obj.getString("subject"));
                                    }
                                    if(!wednesdayTime.contains(obj.getString("time"))) {
                                        wednesdayTime.add(obj.getString("time"));
                                    }
                                }
                                else if(day.equalsIgnoreCase("Thursday")) {
                                    if(!thursdaySub.contains(obj.getString("subject"))) {
                                        thursdaySub.add(obj.getString("subject"));
                                    }
                                    if(!thursdayTime.contains(obj.getString("time"))) {
                                        thursdayTime.add(obj.getString("time"));
                                    }
                                }
                                else if(day.equalsIgnoreCase("Friday")) {
                                    if(!fridaySub.contains(obj.getString("subject"))) {
                                        fridaySub.add(obj.getString("subject"));
                                    }
                                    if(!fridayTime.contains(obj.getString("time"))) {
                                        fridayTime.add(obj.getString("time"));
                                    }
                                }
                                else if(day.equalsIgnoreCase("Saturday")) {
                                    if(!saturdaySub.contains(obj.getString("subject"))) {
                                        saturdaySub.add(obj.getString("subject"));
                                    }
                                    if(!saturdayTime.contains(obj.getString("time"))) {
                                        saturdayTime.add(obj.getString("time"));
                                    }
                                }
                                else if(day.equalsIgnoreCase("Sunday")) {
                                    if(!sundaySub.contains(obj.getString("subject"))) {
                                        sundaySub.add(obj.getString("subject"));
                                    }
                                    if(!sundayTime.contains(obj.getString("time"))) {
                                        sundayTime.add(obj.getString("time"));
                                    }
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


        String currDay = MyWeekActivity.sharedPreferences.getString(MyWeekActivity.selectedDay, null);

        if(currDay.equalsIgnoreCase("Monday")) {
            sel_day = (ArrayList<String>) mondaySub.clone();
            sel_time = (ArrayList<String>) mondayTime.clone();
        }
        else if(currDay.equalsIgnoreCase("Tuesday")) {
            sel_day = (ArrayList<String>) tuesdaySub.clone();
            sel_time = (ArrayList<String>) tuesdayTime.clone();
        }
        else if(currDay.equalsIgnoreCase("Wednesday")) {
            sel_day = (ArrayList<String>) wednesdaySub.clone();
            sel_time = (ArrayList<String>) wednesdayTime.clone();
        }
        else if(currDay.equalsIgnoreCase("Thursday")) {
            sel_day = (ArrayList<String>) thursdaySub.clone();
            sel_time = (ArrayList<String>) thursdayTime.clone();
        }
        else if(currDay.equalsIgnoreCase("Friday")) {
            sel_day = (ArrayList<String>) fridaySub.clone();
            sel_time = (ArrayList<String>) fridayTime.clone();
        }
        else if(currDay.equalsIgnoreCase("Saturday")) {
            sel_day = (ArrayList<String>) saturdaySub.clone();
            sel_time = (ArrayList<String>) saturdayTime.clone();
        }
        else {
            sel_day = (ArrayList<String>) sundaySub.clone();
            sel_time = (ArrayList<String>) sundayTime.clone();
        }

        if(sel_day != null && sel_time != null) {
            SimpleAdapter sa = new SimpleAdapter(this, sel_day, sel_time);
            lv.setAdapter(sa);
        }


    }

    public class SimpleAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater lf;
        private TextView subject, time;
        private LetterImageView liv;
        private ArrayList<String> subjectArray;
        private ArrayList<String> timeArray;

        public SimpleAdapter(Context context, ArrayList<String> subjectArray, ArrayList<String> timeArray) {
            mContext = context;
            this.subjectArray = subjectArray;
            this.timeArray = timeArray;
            lf = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return subjectArray.size();
        }

        @Override
        public Object getItem(int position) {
            return subjectArray.get(position);
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

            subject = (TextView) convertView.findViewById(R.id.textSubject);
            time = (TextView) convertView.findViewById(R.id.textTime);
            liv = (LetterImageView) convertView.findViewById(R.id.imageDayDetails);

            subject.setText(subjectArray.get(position));
            time.setText(timeArray.get(position));

            liv.setOval(true);
            liv.setLetter(subjectArray.get(position).charAt(0));

            return convertView;
        }
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
                Intent i1 = new Intent(MyDayActivity.this, SettingsActivity.class);
                startActivity(i1);
                return(true);
            case R.id.options:
                Intent i2 = new Intent(MyDayActivity.this, OptionsActivity.class);
                startActivity(i2);
                return(true);
            case R.id.map:
                Intent i3 = new Intent(MyDayActivity.this, GoogleMapActivity.class);
                startActivity(i3);
                return(true);
            case R.id.schedule:
                Intent i4 = new Intent(MyDayActivity.this, ScheduleActivity.class);
                startActivity(i4);
                return(true);
            case R.id.exit:
                finish();
                System.exit(0);
                return(true);
            case R.id.log_out:
                Intent i5 = new Intent(MyDayActivity.this, LoginActivity.class);
                startActivity(i5);
                return(true);
            case R.id.userprofile:
                Intent i6 = new Intent(MyDayActivity.this, UserProfileActivity.class);
                startActivity(i6);
                return(true);
        }
        return super.onOptionsItemSelected(item);
    }
}
