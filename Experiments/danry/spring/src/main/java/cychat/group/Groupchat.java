package cychat.group;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface Groupchat extends CrudRepository<Group , Integer>{
	
	@Query(value = "SELECT * FROM grouphistory u WHERE u.groupid = ?1 " , nativeQuery = true)
	public List<Group> findGroupid(int id);
}
