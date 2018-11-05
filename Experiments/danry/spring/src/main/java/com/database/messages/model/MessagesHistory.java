package com.database.messages.model;

import org.springframework.data.repository.CrudRepository;

public interface MessagesHistory extends CrudRepository<MessageInfo, Integer>
{

}
