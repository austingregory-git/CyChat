package edu.iastate.linux.git.ui;

import android.util.Log;

public class User {
    private int id;
    private String userName, realName, userPassword, userType, userEmail;
<<<<<<< HEAD
=======
    public String friendstring;
>>>>>>> aac5fcb10e5176c70433856d6fd0ba8b4187eb4d

    public User(int id, String username, String password, String type,String email, String realName)
    {
        this.id = id;
        this.userName = username;
        this.userPassword = password;
        this.userEmail = email;
        this.realName = realName;
        this.userType = type;
    }
    //test method for user
    public String toString()
    {
        String x = Integer.toString(id) + ", " + userName + ", " + userPassword + ", " + userType + ", "+ userEmail + ", " + realName;
        return x;
    }
    public int getId()
    {
        return id;
    }

    public String getUserName()
    {
        return userName;
    }
    public String getUserPassword()
    {
        return userPassword;
    }
    public String getEmail()
    {
        return userEmail;
    }
    public String getRealName()
    {
        return realName;
    }
    public String getUserType()
    {
        return userType;
    }
    public void changeFriend(String s){
        this.friendstring = s;
        Log.d("friend",friendstring);
    }
    public String getFriend(){
        return friendstring;
    }
}
