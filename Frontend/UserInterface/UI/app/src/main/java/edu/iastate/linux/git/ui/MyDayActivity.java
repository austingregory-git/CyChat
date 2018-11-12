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
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    public static String[] mondayTime;
    public static String[] tuesdayTime;
    public static String[] wednesdayTime;
    public static String[] thursdayTime;
    public static String[] fridayTime;
    public static String[] saturdayTime;
    public static String[] sundayTime;
    public static String[] sel_day;
    public static String[] sel_time;
    public static String[] tempSub;
    public static String[] tempTime;



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
        Monday = getResources().getStringArray(R.array.Monday);
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
        sundayTime = getResources().getStringArray(R.array.sundayTime);

        String newURL = "http://www.json-generator.com/api/json/get/ceyeFecdCG?indent=2";
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, newURL, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        tempSub = new String[response.length()];
                        tempTime = new String[response.length()];
                        try {
                            for(int i=0; i<response.length(); i++) {
                                JSONObject obj = response.getJSONObject(i);
                                tempSub[i] = obj.getString("Subject");
                                tempTime[i] = obj.getString("Time");
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


        String currDay = MyWeekActivity.sharedPreferences.getString(MyWeekActivity.selectedDay, null);

        if(currDay.equalsIgnoreCase("Monday")) {
            sel_day = tempSub;
            sel_time = tempTime;
        }
        else if(currDay.equalsIgnoreCase("Tuesday")) {
            sel_day = Tuesday;
            sel_time = tuesdayTime;
        }
        else if(currDay.equalsIgnoreCase("Wednesday")) {
            sel_day = Wednesday;
            sel_time = wednesdayTime;
        }
        else if(currDay.equalsIgnoreCase("Thursday")) {
            sel_day = Thursday;
            sel_time = thursdayTime;
        }
        else if(currDay.equalsIgnoreCase("Friday")) {
            sel_day = Friday;
            sel_time = fridayTime;
        }
        else if(currDay.equalsIgnoreCase("Saturday")) {
            sel_day = Saturday;
            sel_time = saturdayTime;
        }
        else {
            sel_day = Sunday;
            sel_time = sundayTime;
        }

        SimpleAdapter sa = new SimpleAdapter(this, sel_day, sel_time);
        lv.setAdapter(sa);


    }

    public class SimpleAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater lf;
        private TextView subject, time;
        private LetterImageView liv;
        private String[] subjectArray;
        private String[] timeArray;

        public SimpleAdapter(Context context, String[] subjectArray, String[] timeArray) {
            mContext = context;
            this.subjectArray = subjectArray;
            this.timeArray = timeArray;
            lf = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return subjectArray.length;
        }

        @Override
        public Object getItem(int position) {
            return subjectArray[position];
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

            subject.setText(subjectArray[position]);
            time.setText(timeArray[position]);

            liv.setOval(true);
            liv.setLetter(subjectArray[position].charAt(0));

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
        }
        return super.onOptionsItemSelected(item);
    }
}
