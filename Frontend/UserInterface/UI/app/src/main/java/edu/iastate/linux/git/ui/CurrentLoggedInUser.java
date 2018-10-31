package edu.iastate.linux.git.ui;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

public class CurrentLoggedInUser {

    private static final String SHARED_PREF_NAME = "simplifiedcodingsharedpref";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_AGE = "age";
    private static final String KEY_REALNAME = "name";
    private static final String KEY_ID = "id";
    private static final String KEY_PASSWORD = "password";

    private static CurrentLoggedInUser myInstance;
    private static Context myContext;

    private CurrentLoggedInUser(Context context)
    {
        this.myContext = context;
    }

    public static synchronized CurrentLoggedInUser getInstance(Context context) {
        if (myInstance == null) {
            myInstance = new CurrentLoggedInUser(context);
        }
        return myInstance;
    }

    //method to let the user login
    //this method will store the user data in shared preferences
    public void userLogin(User user) {
        SharedPreferences sharedPreferences = myContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(KEY_ID, user.getId());
        editor.putString(KEY_USERNAME, user.getUserName());
        editor.putString(KEY_PASSWORD, user.getUserPassword());
        editor.putInt(KEY_AGE, user.getAge());
        editor.putString(KEY_REALNAME, user.getRealName());
        editor.apply();
    }


    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = myContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null) != null;
    }

    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = myContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getInt(KEY_ID, -1),
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getString(KEY_PASSWORD,null),
                sharedPreferences.getInt(KEY_AGE, -1),
                sharedPreferences.getString(KEY_REALNAME, null)
        );
    }
    //this method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = myContext.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        myContext.startActivity(new Intent(myContext, LoginActivity.class));
    }
}
