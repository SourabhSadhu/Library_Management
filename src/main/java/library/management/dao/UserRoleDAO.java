package library.management.dao;

import java.util.List;

import library.management.beans.UserRoleBean;

public interface UserRoleDAO {
	int findRoleID(String role);
	String findRoleName(int id);
	void createRole(String roleName);
	List<UserRoleBean> getAllRole();
}
