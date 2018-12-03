package cychat.course;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface CourseList extends CrudRepository<Course, Integer>
{
	@Query(value = "SELECT * FROM course c WHERE c.student = ?1" , nativeQuery = true)
	public List<Course> findBystudentID(int student); 
}
