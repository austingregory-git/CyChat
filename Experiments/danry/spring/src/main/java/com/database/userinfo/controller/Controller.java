package com.database.userinfo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller
{

	@Autowired
	private Server server_userinfo;

	@RequestMapping("/database")
	public List<UserInfo> call()
	{
		return server_userinfo.findAll();
	}

	@RequestMapping("/database/{id}")
	public Optional<UserInfo> Find(@PathVariable int id)
	{
		return server_userinfo.findById(id);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/database")
	public void Add(@RequestBody UserInfo user)
	{
		server_userinfo.save(user);
	}

}
