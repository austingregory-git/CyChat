package edu.iastate.linux.git.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class UserProfileActivity extends AppCompatActivity {

    EditText userID, userName, realName, userEmail, userClassification;
    Button changeInfo,addClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userID = findViewById(R.id.TextDisplayID);
        userName = findViewById(R.id.TextDisplayUsername);
        realName = findViewById(R.id.TextDisplayName);
        userEmail = findViewById(R.id.TextDisplayEmail);
        userClassification = findViewById(R.id.TextDisplayClassification);
        changeInfo = findViewById(R.id.infoChangeButton);
        addClass = findViewById(R.id.addClassButton);

        userID.setText(Integer.toString(CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getId()));
        userName.setText(CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getUserName());
        realName.setText(CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getRealName());
        userEmail.setText(CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getEmail());
        userClassification.setText(CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getUserType());

        userID.setFocusable(false);
        userName.setFocusable(false);
        realName.setFocusable(false);
        userEmail.setFocusable(false);
        userClassification.setFocusable(false);

        addClass.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i3 = new Intent(UserProfileActivity.this, AddClassActivity.class);
                startActivity(i3);
            }
        });

        changeInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i3 = new Intent(UserProfileActivity.this, UProfileChangeActivity.class);
                startActivity(i3);
            }
        });
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
                Intent i1 = new Intent(UserProfileActivity.this, SettingsActivity.class);
                startActivity(i1);
                return(true);
            case R.id.options:
                Intent i2 = new Intent(UserProfileActivity.this, OptionsActivity.class);
                startActivity(i2);
                return(true);
            case R.id.map:
                Intent i3 = new Intent(UserProfileActivity.this, GoogleMapActivity.class);
                startActivity(i3);
                return(true);
            case R.id.chatroom:
                Intent i5 = new Intent(UserProfileActivity.this, ChatRoom.class);
                startActivity(i5);
                return(true);
            case R.id.userprofile:
                Intent i6 = new Intent(UserProfileActivity.this,UserProfileActivity.class);
                startActivity(i6);
                return(true);
            case R.id.exit:
                finish();
                System.exit(0);
                return(true);
            case R.id.log_out:
                Intent i4 = new Intent(UserProfileActivity.this, LoginActivity.class);
                startActivity(i4);
                return(true);
        }
        return super.onOptionsItemSelected(item);
    }
}
