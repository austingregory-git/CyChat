package com.database.friends.controller;

import java.util.List;
import java.util.Optional;

import com.database.friends.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FriendsController
{

	@Autowired
	private Server server_userinfo;

	@RequestMapping("/database/friends")
	public List<FriendsInfo> call()
	{
		return server_userinfo.findAll();
	}

	@RequestMapping("/database/friends/{id}")
	public Optional<FriendsInfo> Find(@PathVariable int id)
	{
		return server_userinfo.findById(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/database/friends")
	public void Add(@RequestBody FriendsInfo user)
	{
		server_userinfo.save(user);
	}

}
