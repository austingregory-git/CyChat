package edu.iastate.linux.git.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

        userID.setText(CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getId());
        userName.setText(CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getUserName());
        realName.setText(CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getRealName());
        //TODO implement this method and the proper user object
       // userEmail.setText(CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getEmail());
        userClassification.setText(CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getUserType());
    }
}
