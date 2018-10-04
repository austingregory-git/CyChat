package com.example.nirmiter.volleypractice;

import android.app.AlertDialog;
import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.NoConnectionError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView mTextViewResult;
    private RequestQueue mQueue;
    private Button buttonParse;
    private Button nextPage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextViewResult = findViewById(R.id.textDisplay);
        buttonParse =  (Button) findViewById(R.id.jsonBTN);
        nextPage = findViewById(R.id.pageBTN);

       //mQueue = Volley.newRequestQueue(this);
        mQueue = VolleySingleton.getInstance(this).getRequestQueue();

        buttonParse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                jsonParse();
            }
        });
        nextPage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                toNextActivity();
            }
        });
    }

    private void jsonParse() {
        String url = "https://api.myjson.com/bins/nh6ew";

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("employees");
                            for(int i = 0; i < jsonArray.length(); i++){
                                JSONObject employee = jsonArray.getJSONObject(i);
                                String fetchedString = employee.getString("name") + " " + employee.getString("password") +
                                        employee.getString("contact") + employee.getString("country");

                                mTextViewResult.append(fetchedString + " , ");
                            }
                        } catch (JSONException e) {
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
        mQueue.add(request);


    }
    private void toNextActivity(){
        Intent intent = new Intent(this,NextActivity.class);
        startActivity(intent);

    }
}




