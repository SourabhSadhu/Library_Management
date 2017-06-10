package library.management.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import library.management.beans.LibraryUserBean;
import library.management.dao.UserDAO;

@Service
public class LoginService {

//	@Autowired	BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired	UserDAO userDAO;
	

	public LibraryUserBean userValidate(String email, String password) {

		LibraryUserBean user = userDAO.checkViaEmail(email);
		if (null != user) {
			String encPass = user.getPassword();
			if (encPass.equals(password)) {
				System.out.println("Logged in user " + user.getName() + " | " + user.getEmail());
				return user;
			}else System.out.println("Invalid password");
		}
		System.out.println("Invalid user");
		return null;
	}


	public List<LibraryUserBean> listAllUser() {
		return userDAO.listUser();
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
