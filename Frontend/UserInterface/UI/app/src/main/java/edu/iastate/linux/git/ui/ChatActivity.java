package edu.iastate.linux.git.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

import edu.iastate.linux.git.ui.Utils.LetterImageView;

import static edu.iastate.linux.git.ui.MyDayActivity.Monday;

public class ChatActivity extends AppCompatActivity {

    private ListView lv;
    public static String[] AustinMessages;
    public static String[] NateMessages;
    public static String[] XiuyuanMessages;
    public static String[] ZhiMessages;

    public static String[] sel_convo;

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
        lv = (ListView) findViewById(R.id.listMyDay);
    }

    private void initListView() {
        AustinMessages = getResources().getStringArray(R.array.AustinMessages);
        NateMessages = getResources().getStringArray(R.array.NateMessages);
        XiuyuanMessages = getResources().getStringArray(R.array.XiuyuanMessages);
        ZhiMessages = getResources().getStringArray(R.array.ZhiMessages);

        if((MyWeekActivity.sharedPreferences.getString(HomeActivity.selectedConversation, null) != null)) {
            String currConvo = MyWeekActivity.sharedPreferences.getString(HomeActivity.selectedConversation, null);

            if(currConvo.equalsIgnoreCase("AustinMessages")) {
                sel_convo = AustinMessages;
            }
            else if(currConvo.equalsIgnoreCase("NateMessages")) {
                sel_convo = NateMessages;
            }
            else if(currConvo.equalsIgnoreCase("XiuyuanMessages")) {
                sel_convo = XiuyuanMessages;
            }
            else if(currConvo.equalsIgnoreCase("ZhiMessages")) {
                sel_convo = ZhiMessages;
            }
        }


        SimpleAdapter sa = new SimpleAdapter(this, sel_convo);
        lv.setAdapter(sa);


    }

    public class SimpleAdapter extends BaseAdapter {

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
