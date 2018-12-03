package cychat.chathistory;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface History extends CrudRepository<ChatHistory , Integer>
{
	@Query(value = "SELECT * FROM chathistory u WHERE (u.sender = ?1 AND u.reciver = ?2) OR (u.sender = ?2 AND u.reciver = ?1)" , nativeQuery = true)
	public List<ChatHistory> findHistory(int sender , int reciver);
	
	@Query(value = "SELSCT * FROM chathistory u Where (u.sender = >1)" , nativeQuery = true)
	public List<ChatHistory> getmessage(int sender);
}
