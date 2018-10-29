package edu.iastate.linux.git.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import edu.iastate.linux.git.ui.Utils.LetterImageView;

public class MyWeekActivity extends AppCompatActivity {

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
                Intent i1 = new Intent(MyWeekActivity.this, HomeActivity.class);
                startActivity(i1);
            }
        });

        contactsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2 = new Intent(MyWeekActivity.this, ContactsActivity.class);
                startActivity(i2);
            }
        });

        notifsB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i3 = new Intent(MyWeekActivity.this, NotificationsActivity.class);
                startActivity(i3);
            }
        });
    }

    private void initViews() {
        lv = (ListView) findViewById(R.id.listMyWeek);
    }

    private void initListView() {
        String[] week = getResources().getStringArray(R.array.weekArray);

        //WeekAdapter wa = new WeekAdapter(this, R.layout.my_week_activity_item, week);
        //lv.setAdapter(wa);

        /*lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch(position) {
                    case 0: break;
                    case 1: break;
                    case 2: break;
                    case 3: break;
                    case 4: break;
                    case 5: break;
                    case 6: break;
                    default: break;
                }
            }
        });
        */

    }

    public class WeekAdapter extends ArrayAdapter {

        private int resource;
        private Context mContext;
        private LayoutInflater lf;
        private String[] week = new String[] {};

        public WeekAdapter(Context context, int resource, String[] obj) {
            super(context, resource, obj);
            this.resource = resource;
            this.week = obj;
            lf = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            ViewHolder vh;
            if(convertView == null) {
                vh = new ViewHolder();
                convertView = lf.inflate(resource, null);
                vh.imageLogo = (LetterImageView) convertView.findViewById(R.id.imageLetter);
                vh.textMyWeek = (TextView) convertView.findViewById(R.id.textMain);
                convertView.setTag(vh);
            }
            else {
                vh = (ViewHolder) convertView.getTag();
            }
            vh.imageLogo.setOval(true);
            vh.imageLogo.setLetter(week[position].charAt(0));
            vh.textMyWeek.setText(week[position]);

            return convertView;
        }

        class ViewHolder {
            private LetterImageView imageLogo;
            private TextView textMyWeek;
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
                Intent i1 = new Intent(MyWeekActivity.this, SettingsActivity.class);
                startActivity(i1);
                return(true);
            case R.id.options:
                Intent i2 = new Intent(MyWeekActivity.this, OptionsActivity.class);
                startActivity(i2);
                return(true);
            case R.id.map:
                Intent i3 = new Intent(MyWeekActivity.this, GoogleMapActivity.class);
                startActivity(i3);
                return(true);
            case R.id.schedule:
                Intent i4 = new Intent(MyWeekActivity.this, ScheduleActivity.class);
                startActivity(i4);
                return(true);
            case R.id.exit:
                finish();
                System.exit(0);
                return(true);
            case R.id.log_out:
                Intent i5 = new Intent(MyWeekActivity.this, LoginActivity.class);
                startActivity(i5);
                return(true);
        }
        return super.onOptionsItemSelected(item);
    }
}
