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

public class AddClassActivity extends AppCompatActivity {
    TextView startTime, endTime, className;
    Button update;
    RadioGroup rg;
    RadioButton rb;
    String timeStart, timeEnd, cname, weekDay;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_class);

        startTime = findViewById(R.id.classStartText);
        endTime = findViewById(R.id.classEndText);
        className = findViewById(R.id.classNameText);
        update = findViewById(R.id.changeButton);
        rg = findViewById(R.id.rgroup);

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               timeStart = startTime.getText().toString();
               timeEnd = endTime.getText().toString();
               cname = className.getText().toString();
                rb = findViewById(rg.getCheckedRadioButtonId());
                weekDay = String.valueOf(rb.getText());
                if (timeStart.length() > 0 && timeEnd.length() > 0 && cname.length() > 0)
                {
                    addClass();
                    startTime.setText("");
                    endTime.setText("");
                    className.setText("");
                }
                else {
                    Toast.makeText(getApplicationContext(), "Make sure you fill out all the fields!", Toast.LENGTH_LONG).show();
                }
            }
        });

    }


    private void addClass()
    {
        try {
            JSONObject tempObj = new JSONObject();
            tempObj.put("classname", cname);
            tempObj.put("starttime",timeStart);
            tempObj.put("endtime",timeEnd);
            tempObj.put("weekday",weekDay);

            JsonObjectRequest jsonObject = new JsonObjectRequest(Request.Method.POST, URLConstants.CLASS_DISPLAY_URL + CurrentLoggedInUser.getInstance(getApplicationContext()).getUser().getId(), tempObj, new Response.Listener<JSONObject>() {

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
            VolleySingleton.getInstance(AddClassActivity.this).addToRequestQue(jsonObject);
        }
        catch (JSONException e){
            e.printStackTrace();
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
                Intent i1 = new Intent(AddClassActivity.this, SettingsActivity.class);
                startActivity(i1);
                return(true);
            case R.id.options:
                Intent i2 = new Intent(AddClassActivity.this, OptionsActivity.class);
                startActivity(i2);
                return(true);
            case R.id.map:
                Intent i3 = new Intent(AddClassActivity.this, GoogleMapActivity.class);
                startActivity(i3);
                return(true);
            case R.id.groupchatroom:
                Intent i5 = new Intent(AddClassActivity.this, GroupConversationsActivity.class);
                startActivity(i5);
                return(true);
            case R.id.userprofile:
                Intent i6 = new Intent(AddClassActivity.this,UserProfileActivity.class);
                startActivity(i6);
                return(true);
            case R.id.exit:
                finish();
                System.exit(0);
                return(true);
            case R.id.log_out:
                Intent i4 = new Intent(AddClassActivity.this, LoginActivity.class);
                startActivity(i4);
                return(true);
        }
        return super.onOptionsItemSelected(item);
    }
}
