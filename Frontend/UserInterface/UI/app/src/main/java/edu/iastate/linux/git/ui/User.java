package edu.iastate.linux.git.ui;

public class User {
    private int id, age;
    private String userName, realName, userPassword, userType;

    public User(int id, String username, String password, int age, String realName)
    {
        this.id = id;
        this.userName = username;
        this.userPassword = password;
        this.age = age;
        this.realName = realName;
        //this.userType = userType;
    }
    //test method for user
    public String toString()
    {
        String x = Integer.toString(id) + ", " + userName + ", " + userPassword + ", " + Integer.toString(age) + ", " + realName;
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
    public int getAge()
    {
        return age;
    }
    public String getRealName()
    {
        return realName;
    }
    public String getUserType()
    {
        return userType;
    }
}
