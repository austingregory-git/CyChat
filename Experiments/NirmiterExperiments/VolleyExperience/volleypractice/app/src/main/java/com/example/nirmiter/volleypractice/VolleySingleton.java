package com.example.nirmiter.volleypractice;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class VolleySingleton {
    private static VolleySingleton myInstance;
    private RequestQueue myRequestQueue;

    private VolleySingleton(Context context){
        myRequestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized VolleySingleton getInstance(Context context){
       //Checks to see if there is an instance of requestQueue. If there is not, creates
        //A new one and returns it else returns
        if(myInstance == null){
            myInstance = new VolleySingleton(context);
        }
        return myInstance;
    }

    public RequestQueue getRequestQueue(){
        return myRequestQueue;
    }

    public <T> void addToRequestQue(Request<T> request){
        myRequestQueue.add(request);

    }
}
