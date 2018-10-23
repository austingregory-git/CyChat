package com.mysqlserver.controll;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Server
{

	@Autowired
	private User_data data;

	public long count()
	{
		return data.count();
	}
	
	public void delete(UserInfor entity)
	{
		data.delete(entity);
	}
	
	public void deleteAll()
	{
		data.deleteAll();
	}
	
	public void deleteAll(List<UserInfor> entities)
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
	
	public List<UserInfor> findAll()
	{
		return (List<UserInfor>) data.findAll();
	}
	
	public List<UserInfor> findAllById(Iterable<Integer> ids)
	{
		return (List<UserInfor>) data.findAllById(ids);
	}

	public Optional<UserInfor> findById(int id)
	{
		return data.findById(id);
	}

	public UserInfor save(UserInfor user)
	{
		return data.save(user);
	}
	
	public <S> List<UserInfor> saveAll(List<UserInfor> users)
	{
		return (List<UserInfor>) data.saveAll(users);
	}

}
