package library.management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
//import org.springframework.dao.DuplicateKeyException;
//import org.apache.commons.dbcp2.BasicDataSource;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.fasterxml.jackson.core.type.TypeReference;

import library.management.beans.BookRepoBean;
import library.management.beans.LibraryUserBean;
import library.management.beans.StudentRepoBean;
import library.management.beans.Utils;
import library.management.dao.*;
import library.management.service.BookService;
import library.management.service.LoginService;

import javax.sql.DataSource;

import java.util.Date;
import java.util.List;

import static java.lang.System.exit;

import java.text.SimpleDateFormat;



@SpringBootApplication
@PropertySource(value = {"classpath:application.properties"})
@ComponentScan("library.management")
public class ApplicationContext implements CommandLineRunner{
	
//	@Autowired BCryptPasswordEncoder passwordEncoder;
	@Autowired DataSource dataSource;
	
	@Autowired UserDAO userDAO;
	@Autowired UserRoleDAO userRoleDAO;
	@Autowired BookDAO bookDAO;

	@Autowired BookRepoBean book;
	@Autowired StudentRepoBean student;
	@Autowired LibraryUserBean bean1;
	
	@Autowired BookService bookService;
	@Autowired LoginService loginService;
	
	@Autowired Utils getUtils;
	
	public static void main(String[] args) throws Exception {
        SpringApplication.run(ApplicationContext.class, args);
    }

	@Override
    public void run(String... args) throws Exception {

		System.out.println("****************************************************************************************");
		
//		System.out.println(userRoleDAO.findRoleID("ADMIN"));
//		
//		bean1.setRole(userRoleDAO.findRoleID("STUDENT"));
//		bean1.setName("Sourav");
//		bean1.setEmail("sourav@gmail.com");
//		bean1.setPassword(passwordEncoder.encode("123456"));
//		bean1.setActive_till(new SimpleDateFormat("yyyy-MM-dd").parse("2017-07-15"));
//		try{
//			userDAO.addUser(bean1);
//		}catch(DuplicateKeyException de){
//			System.out.println("Provide different email id");
//		}
//		bean1.setRole(userRoleDAO.findRoleID("STUDENT"));
//		bean1.setName("Shreya");
//		bean1.setEmail("shreya@gmail.com");
//		bean1.setPassword(passwordEncoder.encode("123456"));
//		bean1.setActive_till(new SimpleDateFormat("yyyy-MM-dd").parse("2017-07-15"));
//		try{
//			userDAO.addUser(bean1);
//		}catch(DuplicateKeyException de){
//			System.out.println("Provide different email id");
//		}
//		bean1.setRole(userRoleDAO.findRoleID("STUDENT"));
//		bean1.setName("Sujit");
//		bean1.setEmail("sujit@gmail.com");
//		bean1.setPassword(passwordEncoder.encode("123456"));
//		bean1.setActive_till(new SimpleDateFormat("yyyy-MM-dd").parse("2017-07-15"));
//		try{
//			userDAO.addUser(bean1);
//		}catch(DuplicateKeyException de){
//			System.out.println("Provide different email id");
//		}
		
//		for(LibraryUserBean userBean : userDAO.listUser()){
//			System.out.println(userBean.getName() + userBean.getPassword());
//			System.out.println(passwordEncoder.matches("123456", userBean.getPassword()));
//		}
		
//		userDAO.deleteUser(userDAO.listUser().get(0));
//		System.exit(0);
		
//		userRoleDAO.createRole("Super User");
		
//		book.setName("Introduction to JAVA");
//		book.setCategory("ECE,IT");
//		book.setSub_category("Programming");
//		book.setAuthor("MKYONG");
//		book.setCount(15);
//		bookDAO.createEntry(book);
//		
//		book.setName("Introduction to Spring");
//		bookDAO.createEntry(book);
//		
//		book.setName("Introduction to MVC");
//		bookDAO.createEntry(book);
		
//		System.out.println("Size " + bookDAO.searchAll().size());
//		for(BookRepoBean br : bookDAO.searchAll()){
//			System.out.println("Name " + br.getName());
//		}
		
//		book.setCount(300);
//		book.setId(11);
//		bookDAO.updateCount(book);
//		
//		for(BookRepoBean br : bookDAO.searchAll()){
//			System.out.println("Count " + br.getCount());
//		}
		
//		bookDAO.deleteEntry(15);
//		
//		for(BookRepoBean br : bookDAO.searchViaParam("name","JAVA")){
//			System.out.println("Name " + br.getName());
//		}
		
//		user = userDAO.checkViaEmail("abc@gmail.com");
//		System.out.println(user.getName());
		
		
		/**
		 * book issue test
		 */
//		book.setId(1);
//		student.setEmail("sandip@gmail.com");
//		System.out.println(bookService.issueBook(student, book));
		
//		System.out.println(passwordEncoder.encode("123456"));
//		String data =getUtils.objectToJSON(userDAO.listUser());
//		System.out.println(data);
//		List<LibraryUserBean> userlist = getUtils.jSONToObject(new TypeReference<List<LibraryUserBean>>(){},data);
//		for(LibraryUserBean lub : userlist){
//			System.out.println(lub.getName());
//		}
		
//		Date date1 = getUtils.getDate(0);
//		Date date2 = getUtils.getDate(5);
//		System.out.println("Dates : 1="+date1 + " 2=" + date2);
//		System.out.println("Date diff " + getUtils.getDateDiff(date1, date2));
		
		
		
//		System.exit(0);
		System.out.println("****************************************************************************************");
    }
}