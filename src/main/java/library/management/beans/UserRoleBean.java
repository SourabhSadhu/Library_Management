package library.management.beans;

//import javax.persistence.Column;
//import javax.persistence.Entity;
//import javax.persistence.Id;
//import javax.persistence.Table;

import org.springframework.stereotype.Service;

//@Entity
//@Table(name="user_role")
@Service
public class UserRoleBean {
	
//	@Id
//	@Column(name="id")
	private int id;
	
//	@Column(name="role")
	private String role;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
	
	
}
