package cychat.friend;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface FriendList extends CrudRepository<Friend , Integer> {

	@Query(value = "SELECT * FROM friend u WHERE (u.sender = ?1 OR u.reciver = ?1)" , nativeQuery = true)
	public List<Friend> findFriend(int id);

	@Query(value = "SELECT * FROM friend u WHERE (u.sender = ?1 AND u.reciver = ?2) OR (u.sender = ?2 AND u.reciver = ?1)" , nativeQuery = true)
	public List<Friend> isfriend(int sender, int reciver);
	
}
