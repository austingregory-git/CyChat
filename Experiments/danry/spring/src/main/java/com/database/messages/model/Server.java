package com.database.messages.model;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Server
{

	@Autowired
	private MessagesHistory data;

	public long count()
	{
		return data.count();
	}
	
	public void delete(MessageInfo entity)
	{
		data.delete(entity);
	}
	
	public void deleteAll()
	{
		data.deleteAll();
	}
	
	public void deleteAll(List<MessageInfo> entities)
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
	
	public List<MessageInfo> findAll()
	{
		return (List<MessageInfo>) data.findAll();
	}
	
	public List<MessageInfo> findAllById(Iterable<Integer> ids)
	{
		return (List<MessageInfo>) data.findAllById(ids);
	}

	public Optional<MessageInfo> findById(int id)
	{
		return data.findById(id);
	}

	public MessageInfo save(MessageInfo user)
	{
		return data.save(user);
	}
	
	public <S> List<MessageInfo> saveAll(List<MessageInfo> users)
	{
		return (List<MessageInfo>) data.saveAll(users);
	}

}
