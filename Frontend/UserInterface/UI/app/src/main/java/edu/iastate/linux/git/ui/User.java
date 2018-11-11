package edu.iastate.linux.git.ui;

public class User {
    private int id;
    private String userName, realName, userPassword, userType, userEmail;

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
    public void setEmail(String x){
        this.userEmail = x;
    }
    public void setPassword(String x){
        this.userPassword = x;
    }
}
