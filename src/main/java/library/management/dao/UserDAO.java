package library.management.dao;

import java.util.List;

import library.management.beans.LibraryUserBean;

public interface UserDAO {
	public List<LibraryUserBean> listUser();
	public void addUser(LibraryUserBean user);
	public void updateUser(LibraryUserBean user);
	public void deleteUser(int id);
	public LibraryUserBean checkViaEmail(String email);
}
