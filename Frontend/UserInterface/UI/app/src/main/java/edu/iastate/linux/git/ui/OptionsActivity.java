package edu.iastate.linux.git.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class OptionsActivity extends AppCompatActivity {

    private TextView mTextMessage,studIDinput,grpIDinput,groupname;
    private Button addStud,addGroup;
    private String utype, studID,grpID,group;

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

        studIDinput = findViewById(R.id.studentIDTxt);
        grpIDinput = findViewById(R.id.groupIDText);
        groupname = findViewById(R.id.groupNameText);
        addStud = findViewById(R.id.addStudentBtn);
        addGroup = findViewById(R.id.createGroupButton);
        utype = CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getUserType();
        addStud.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grpID = grpIDinput.getText().toString();
                studID = studIDinput.getText().toString();
                if(utype.equals("TA") || utype.equals("Professor") && !areEmpty(grpID,studID)){

                }
                else{
                    Toast.makeText(getApplicationContext(), "You don't have authorization to do that!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        addGroup.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                grpID = grpIDinput.getText().toString();
                group = groupname.getText().toString();
                if(utype.equals("Professor") && !areEmpty(grpID,group)){

                }
                else{
                    Toast.makeText(getApplicationContext(), "You don't have authorization to do that!", Toast.LENGTH_SHORT).show();
                }
            }
        });
        ImageButton homeB = (ImageButton) findViewById(R.id.homeButton);
        ImageButton contactsB = (ImageButton) findViewById(R.id.contactsButton);
        ImageButton notifsB = (ImageButton) findViewById(R.id.notificationsButton);

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

    private boolean areEmpty(String s, String y){
        if (s.length() >= 0 && y.length() >= 0){
            return false;
        }
        return true;
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
        }
        return super.onOptionsItemSelected(item);
    }
}
