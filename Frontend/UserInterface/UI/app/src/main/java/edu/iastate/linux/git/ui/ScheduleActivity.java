package edu.iastate.linux.git.ui;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ScheduleActivity extends AppCompatActivity {

    private ListView lv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_schedule);

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
                Intent i1 = new Intent(ScheduleActivity.this, HomeActivity.class);
                startActivity(i1);
            }
        });

        contactsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(ScheduleActivity.this, ContactsActivity.class);
                startActivity(i2);
            }
        });

        notifsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(ScheduleActivity.this, NotificationsActivity.class);
                startActivity(i3);
            }
        });
    }

    private void initViews() {
        lv = (ListView) findViewById(R.id.listMain);
    }

    private void initListView() {
        String[] title = getResources().getStringArray(R.array.scheduleArray);
        String[] desc = getResources().getStringArray(R.array.descriptionArray);

        SimpleAdapter sa = new SimpleAdapter(this, title, desc);
        lv.setAdapter(sa);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0: {
                        Intent i = new Intent(ScheduleActivity.this, MyWeekActivity.class);
                        startActivity(i);
                        break;
                    }
                    case 1: {
                        Intent i = new Intent(ScheduleActivity.this, CaldroidActivity.class);
                        startActivity(i);
                        break;
                    }
                    case 2: {
                        Intent i = new Intent(ScheduleActivity.this, EventsActivity.class);
                        startActivity(i);
                        break;
                    }
                    default: break;
                }
            }
        });

    }

    public class SimpleAdapter extends BaseAdapter {

        private Context mContext;
        private LayoutInflater lf;
        private TextView title, desc;
        private ImageView iv;
        private String[] titleArray;
        private String[] descArray;

        public SimpleAdapter(Context context, String[] title, String[] desc) {
            mContext = context;
            titleArray = title;
            descArray = desc;
            lf = LayoutInflater.from(context);
        }

        @Override
        public int getCount() {
            return titleArray.length;
        }

        @Override
        public Object getItem(int position) {
            return titleArray[position];
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            if(convertView == null) {
                convertView = lf.inflate(R.layout.schedule_activity_item, null);
            }

            title = (TextView) convertView.findViewById(R.id.textMain);
            desc = (TextView) convertView.findViewById(R.id.textDescription);
            iv = (ImageView) convertView.findViewById(R.id.imageMain);

            title.setText(titleArray[position]);
            desc.setText(descArray[position]);

            if(titleArray[position].equalsIgnoreCase("my week")) {
                iv.setImageResource(R.drawable.schedule);
            }

            else if(titleArray[position].equalsIgnoreCase("calendar")) {
                iv.setImageResource(R.drawable.calendar);
            }

            else if(titleArray[position].equalsIgnoreCase("events near me")) {
                iv.setImageResource(R.drawable.events);
            }

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
                Intent i1 = new Intent(ScheduleActivity.this, SettingsActivity.class);
                startActivity(i1);
                return(true);
            case R.id.options:
                Intent i2 = new Intent(ScheduleActivity.this, OptionsActivity.class);
                startActivity(i2);
                return(true);
            case R.id.map:
                Intent i3 = new Intent(ScheduleActivity.this, GoogleMapActivity.class);
                startActivity(i3);
                return(true);
            case R.id.schedule:
                Intent i4 = new Intent(ScheduleActivity.this, ScheduleActivity.class);
                startActivity(i4);
                return(true);
            case R.id.exit:
                finish();
                System.exit(0);
                return(true);
            case R.id.log_out:
                Intent i5 = new Intent(ScheduleActivity.this, LoginActivity.class);
                startActivity(i5);
                return(true);
        }
        return super.onOptionsItemSelected(item);
    }
}
