package com.database.messages.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Server
{

	@Autowired
	private UserData data;

	public long count()
	{
		return data.count();
	}
	
	public void delete(UserInfo entity)
	{
		data.delete(entity);
	}
	
	public void deleteAll()
	{
		data.deleteAll();
	}
	
	public void deleteAll(List<UserInfo> entities)
	{
		data.deleteAll(entities);
	}
	
	public void deleteById(int id)
	{
		data.deleteById(id);
	}
	
	public boolean existsById(int id)
	{
		return data.existsById(id);
	}
	
	public List<UserInfo> findAll()
	{
		return (List<UserInfo>) data.findAll();
	}
	
	public List<UserInfo> findAllById(Iterable<Integer> ids)
	{
		return (List<UserInfo>) data.findAllById(ids);
	}

	public Optional<UserInfo> findById(int id)
	{
		return data.findById(id);
	}

	public UserInfo save(UserInfo user)
	{
		return data.save(user);
	}
	
	public <S> List<UserInfo> saveAll(List<UserInfo> users)
	{
		return (List<UserInfo>) data.saveAll(users);
	}

}
