package edu.iastate.linux.git.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class UProfileChangeActivity extends AppCompatActivity {

    TextView changeInput, confirmInput;
    String userChangeInput, userConfirmedInput;
    Button changesButton;
    RadioGroup rg;
    RadioButton rb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_uprofile_change);

        changeInput = findViewById(R.id.changedInfoText);
        confirmInput = findViewById(R.id.classStartText);
        changesButton = findViewById(R.id.changeButton);
        rg = findViewById(R.id.rgroup);

        changesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userChangeInput = changeInput.getText().toString();
                userConfirmedInput = confirmInput.getText().toString();
                if(userChangeInput.equals(userConfirmedInput) && userChangeInput.length() != 0){
                    try {
                        changeUserInformation();
                        changeInput.setText("");
                        confirmInput.setText("");
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "Make sure your confirmation is the same!", Toast.LENGTH_LONG).show();
                }
            }
        });
    }
    private void changeUserInformation() throws JSONException {
            rb = findViewById(rg.getCheckedRadioButtonId());
            final String changedVal = String.valueOf(rb.getText()).toLowerCase();


        JSONObject tempObj = new JSONObject();
        tempObj.put("id", CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getId());
        tempObj.put("username", CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getUserName());
        if(changedVal.equals("email"))
        {
            tempObj.put("password",CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getUserPassword());
            tempObj.put("email",userChangeInput);
            CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().setEmail(userChangeInput);
            Log.d("newUser",CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().toString());
        }
        else {
            tempObj.put("password",userChangeInput);
            tempObj.put("email",CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getEmail());
            CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().setPassword(userChangeInput);
            Log.d("newUser",CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().toString());
        }
        tempObj.put("type",CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getUserType());
        tempObj.put("name",CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getRealName());


            Log.d("objectSent",tempObj.toString());
           // Log.d("objectSentType",tempObj.getClass().toString());
            //Then to update this serverside
            JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.POST, URLConstants.JSON_URL, tempObj, new Response.Listener<JSONObject>() {

                @Override
                public void onResponse(JSONObject response) {
                    Log.d("Danrysresponse",response.toString());

                }

            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    error.printStackTrace();
                    Log.d("Nateserror",error.toString());
                    // Toast.makeText(RegistrationActivity.this, "Error...", Toast.LENGTH_SHORT).show();
                }
            });
            VolleySingleton.getInstance(UProfileChangeActivity.this).addToRequestQue(jsonObject);


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
                Intent i1 = new Intent(UProfileChangeActivity.this, SettingsActivity.class);
                startActivity(i1);
                return(true);
            case R.id.options:
                Intent i2 = new Intent(UProfileChangeActivity.this, OptionsActivity.class);
                startActivity(i2);
                return(true);
            case R.id.map:
                Intent i3 = new Intent(UProfileChangeActivity.this, GoogleMapActivity.class);
                startActivity(i3);
                return(true);
            case R.id.chatroom:
                Intent i5 = new Intent(UProfileChangeActivity.this, ChatRoom.class);
                startActivity(i5);
                return(true);
            case R.id.userprofile:
                Intent i6 = new Intent(UProfileChangeActivity.this,UserProfileActivity.class);
                startActivity(i6);
                return(true);
            case R.id.exit:
                finish();
                System.exit(0);
                return(true);
            case R.id.log_out:
                Intent i4 = new Intent(UProfileChangeActivity.this, LoginActivity.class);
                startActivity(i4);
                return(true);
        }
        return super.onOptionsItemSelected(item);
    }
}
