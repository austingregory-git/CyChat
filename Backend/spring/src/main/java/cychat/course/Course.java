package cychat.course;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "course") 
public class Course {
	
	@Column(name = "id")
	@Id @GeneratedValue
	private int id;
	
	@Column(name = "student")
	private int student;
	
	@Column(name = "subject")
	private String subject;
	
	@Column(name = "time")
	private String time;
	
	@Column(name = "day")
	private String day;
	
	public Course()
	{}

	public Course(int student, String subject, String time, String day) {
		super();
		this.student = student;
		this.subject = subject;
		this.time = time;
		this.day = day;
	}

	public int getStudent() {
		return student;
	}

	public void setStudent(int student) {
		this.student = student;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	
	
	
	 
	
	
	
}
