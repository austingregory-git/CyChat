package cychat.course;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "course") 
public class Course {
	
	@Id @GeneratedValue
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "students")
	private String students;	
	
	public Course()
	{}
	
	public Course(int id, String name, String students) {
		super();
		this.id = id;
		this.name = name;
		this.students = students;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStudents() {
		return students;
	}

	public void setStudents(String students) {
		this.students = students;
	}
	 
	
	
	
}
