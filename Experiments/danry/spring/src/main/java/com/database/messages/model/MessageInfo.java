package com.database.messages.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class MessageInfo
{

	@Id
	private int id;
	private long time;
	private String message;

	public MessageInfo(int id, String message)
	{
		this.id = id;
		this.time = System.currentTimeMillis();
		this.message = message;
	}

	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}
	
	public long getTime()
	{
		return this.time;
	}
	
	public void setTime()
	{
		this.time = System.currentTimeMillis();
	}
	
	public String getMessage()
	{
		return this.message;
	}
	
	public void setMessage(String message)
	{
		this.message = message;
	}

}
