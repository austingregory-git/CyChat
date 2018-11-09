package edu.iastate.linux.git.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class UserProfileActivity extends AppCompatActivity {

    EditText userID, userName, realName, userEmail, userClassification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userID = findViewById(R.id.TextDisplayID);
        userName = findViewById(R.id.TextDisplayUsername);
        realName = findViewById(R.id.TextDisplayName);
        userEmail = findViewById(R.id.TextDisplayEmail);
        userClassification = findViewById(R.id.TextDisplayClassification);

        userID.setText(Integer.toString(CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getId()));
        userName.setText(CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getUserName());
        realName.setText(CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getRealName());
        //TODO implement this method and the proper user object
        userEmail.setText(CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getEmail());
        userClassification.setText(CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getUserType());
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
