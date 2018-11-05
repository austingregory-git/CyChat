package com.database.userinfo.controller;

import org.springframework.data.repository.CrudRepository;

public interface UserData extends CrudRepository<UserInfo, Integer>
{

}
