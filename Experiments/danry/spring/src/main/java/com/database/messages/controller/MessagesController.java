package com.database.messages.controller;

import java.util.List;
import java.util.Optional;

import com.database.messages.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessagesController
{

	@Autowired
	private Server server_userinfo;

	@RequestMapping("/database/messages")
	public List<MessageInfo> call()
	{
		return server_userinfo.findAll();
	}

	@RequestMapping("/database/messages/{id}")
	public Optional<MessageInfo> Find(@PathVariable int id)
	{
		return server_userinfo.findById(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "//database/messages")
	public void Add(@RequestBody MessageInfo message)
	{
		server_userinfo.save(message);
	}

}
