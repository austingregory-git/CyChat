package com.database.messages.controller;

import org.springframework.data.repository.CrudRepository;

public interface UserData extends CrudRepository<UserInfo, Integer>
{

}
