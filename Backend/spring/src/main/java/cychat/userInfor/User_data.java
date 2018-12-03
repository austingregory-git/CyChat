package cychat.userInfor;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface User_data extends CrudRepository<UserInfor , Integer> {
	
//	@Query("SELECT AVG(u.Age) from UserInfor u")
//	int AveageAge();
//	
//	@Query(value = "SELECT max(u.Age) from user_infor u ",nativeQuery = true)
//	int maxAge();
	
	@Query(value = "SELECT * FROM user_infor u WHERE u.Name = ?1", nativeQuery = true)
	List<UserInfor> getnameV(String name);
	
	@Query(value = "SELECT * FROM user_infor u WHERE u.username = ?1" , nativeQuery = true)
	List<UserInfor> getUsername(String Username);
	
//	@Query(value = "SELECT * FROM user_infor u WHERE u.Age =  ?1" , nativeQuery = true)
//	List<UserInfor> getSameAge(int age);
	
	@Query(value = "SELECT max(u.from) from friend u" , nativeQuery = true)
	int Getfriend();	
	
	
//	@Modifying
//	@Query(value = "INSERT INTO cychat.friend (from,to) VALUES (:Ufrom , :Uto)" , nativeQuery = true)
//	@Transactional
//	void addFriend(@Param("Ufrom")int Ufrom ,@Param("Uto") int Uto);
	
//	@Modifying
//	@Query(value = "INSERT INTO user_infor (id, username, password, Name, Age, Type) VALUES (123, rr, rr, rr, 21, rr)" , nativeQuery = true)
//	void AddU();
	
}
