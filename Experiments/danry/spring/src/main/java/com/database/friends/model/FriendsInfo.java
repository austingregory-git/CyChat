package com.database.friends.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import com.database.userinfo.controller.*;

@Entity
public class FriendsInfo
{

	@Id
	private int id;
	private UserInfo user;

	public FriendsInfo(int id, UserInfo user)
	{
		this.id = id;
		this.user = user;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	public UserInfo getUser()
	{
		return user;
	}

	public void setUsername(UserInfo user)
	{
		this.user = user;
	}

}
