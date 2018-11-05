package com.database.friends.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Server
{

	@Autowired
	private FriendsList data;

	public long count()
	{
		return data.count();
	}
	
	public void delete(FriendsInfo entity)
	{
		data.delete(entity);
	}
	
	public void deleteAll()
	{
		data.deleteAll();
	}
	
	public void deleteAll(List<FriendsInfo> entities)
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
	
	public List<FriendsInfo> findAll()
	{
		return (List<FriendsInfo>) data.findAll();
	}
	
	public List<FriendsInfo> findAllById(Iterable<Integer> ids)
	{
		return (List<FriendsInfo>) data.findAllById(ids);
	}

	public Optional<FriendsInfo> findById(int id)
	{
		return data.findById(id);
	}

	public FriendsInfo save(FriendsInfo user)
	{
		return data.save(user);
	}
	
	public <S> List<FriendsInfo> saveAll(List<FriendsInfo> users)
	{
		return (List<FriendsInfo>) data.saveAll(users);
	}

}
