package library.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import library.management.beans.LibraryUserBean;
import library.management.beans.StringResponse;
import library.management.dao.UserDAO;

@Service
public class UserService {

	@Autowired UserDAO userDAO;
	
	public List<LibraryUserBean> getUserList() {
		return userDAO.listUser();
	}
	
	public String createUser(LibraryUserBean user){
		try{
			userDAO.addUser(user);
			return user.getName() + " added successfully";
		}catch (DataAccessException e){
			if(e instanceof DuplicateKeyException){
				return "User email already exists";
			}
			return "Error occured" + e.getMessage();
		}
	}
	
	public String updateUser(LibraryUserBean user){
		try{	
			userDAO.updateUser(user);
			return user.getName() + " updated successfully";
		}catch (DataAccessException e){
			return "Error occured" + e.getMessage();
		}
	}
	
	public String deleteUser(int user_id){
		try{
			userDAO.deleteUser(user_id);
			return "Deleted successfully";
		}catch (DataAccessException e){
			return "Error occured" + e.getMessage();
		}
	}
}
