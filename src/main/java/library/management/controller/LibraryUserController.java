package library.management.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
//import javax.ws.rs.Path;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import library.management.beans.BookRepoBean;
import library.management.beans.LibraryUserBean;
import library.management.beans.StringResponse;
import library.management.beans.StudentBookBean;
import library.management.beans.StudentRepoBean;
import library.management.dao.BookDAO;
import library.management.dao.StudentBookDAO;
import library.management.dao.StudentRepoDAO;
import library.management.dao.UserDAO;
import library.management.service.*;

@Controller
public class LibraryUserController {
	
	@Autowired UserService userService;
	@Autowired BookService bookService;
	@Autowired CustomService customService;
	@Autowired StringResponse resp;
	@Autowired StudentRepoDAO studentRepoDAO;
	@Autowired UserDAO userDAO;
	@Autowired BookDAO bookDAO;
	
	static String SUCCESS = "success";
	static String ERROR = "error";

	@RequestMapping(value="/getUserList", method=RequestMethod.GET)
	@ResponseBody
	public List<LibraryUserBean> getUserList(){
		System.out.println("*******************Get User List**************************");
		return customService.getUserRoleList();
	}

	@RequestMapping(value="/createUser", method=RequestMethod.POST, headers = {"Content-Type=application/json"})
	@ResponseBody
	public StringResponse createUser (@RequestBody LibraryUserBean user){
		System.out.println("*******************Create User POST**************************");
		System.out.println(user.getEmail() + " | " + user.getActive_till() + " | " + user.getCreation_date());
		String data = userService.createUser(user);
		resp.setResponseCode(userService.createUser(user).contains(SUCCESS) ? SUCCESS : ERROR);
		resp.setResponse(data);
		return resp;
	}
	
	@RequestMapping(value="/editUser", method=RequestMethod.POST, headers = {"Content-Type=application/json"})
	@ResponseBody
	public StringResponse editUser (@RequestBody LibraryUserBean user){
		System.out.println("*******************Edit User POST**************************");
		System.out.println(user.getEmail() + " | " + user.getActive_till() + " | " + user.getCreation_date());
		String data = userService.updateUser(user);
		resp.setResponseCode(data.contains(SUCCESS) ? SUCCESS : ERROR);
		resp.setResponse(data);
		return resp;
	}
	
	
	@RequestMapping(value="/deleteUser", method=RequestMethod.GET)
	@ResponseBody
	public StringResponse deleteUser(@RequestParam("id") int id){
		System.out.println("*******************Delete User GET**************************");
		System.out.println(id);
		String data = userService.deleteUser(id);
		resp.setResponseCode(data.contains(SUCCESS) ? SUCCESS : ERROR);
		resp.setResponse(data);
		return resp;
	}
	
	
	
	
	@RequestMapping(value="/getStudentList", method=RequestMethod.GET)
	@ResponseBody
	public List<StudentRepoBean> getStudentList(){
		System.out.println("*******************Get Student List**************************");
		return studentRepoDAO.searchAll();
	}

	@RequestMapping(value="/createStudent", method=RequestMethod.POST, headers = {"Content-Type=application/json"})
	@ResponseBody
	public void createStudent (@RequestBody StudentRepoBean student){
		System.out.println("*******************Create Student POST**************************");
		System.out.println(student.getEmail() + " | " + student.getName());
		studentRepoDAO.createEntry(student);
	}
	
	@RequestMapping(value="/editStudent", method=RequestMethod.POST, headers = {"Content-Type=application/json"})
	@ResponseBody
	public void editStudent (@RequestBody StudentRepoBean student){
		System.out.println("*******************Edit Student POST**************************");
		System.out.println(student.getEmail() + " | " + student.getName());
		studentRepoDAO.updateEntry(student);
	}
	
	@RequestMapping(value="/deleteStudent", method=RequestMethod.GET)
	@ResponseBody
	public void deleteStudent(@RequestParam("id") int id){
		System.out.println("*******************Delete Student GET**************************");
		System.out.println(id);
		studentRepoDAO.deleteStudent(id);
	}
	
	
	
	
	
	
	@RequestMapping(value="/getBookList", method=RequestMethod.GET)
	@ResponseBody
	public List<BookRepoBean> getBookList(){
		System.out.println("*******************Get Book List**************************");
		return bookDAO.searchAll();
	}

	@RequestMapping(value="/createBook", method=RequestMethod.POST, headers = {"Content-Type=application/json"})
	@ResponseBody
	public void createBook (@RequestBody BookRepoBean book){
		System.out.println("*******************Create Book POST**************************");
		System.out.println(book.getName() + " | " + book.getCategory());
		bookDAO.createEntry(book);
	}
	
	@RequestMapping(value="/editBook", method=RequestMethod.POST, headers = {"Content-Type=application/json"})
	@ResponseBody
	public void editBook (@RequestBody BookRepoBean book){
		System.out.println("*******************Edit Book POST**************************");
		System.out.println(book.getName() + " | " + book.getCategory());
		bookDAO.updateBook(book);
	}
	
	@RequestMapping(value="/deleteBook", method=RequestMethod.GET)
	@ResponseBody
	public void deleteBook(@RequestParam("id") int id){
		System.out.println("*******************Delete Book GET**************************");
		System.out.println(id);
		bookDAO.deleteEntry(id);
	}
	
	
	
	
	@RequestMapping(value="/getRecordList", method=RequestMethod.GET)
	@ResponseBody
	public List<StudentBookBean> getRecordList(){
		System.out.println("*******************Get Record List**************************");
		return customService.getRecordList();
	}
	
	
	@RequestMapping(value="/getArraylist", method=RequestMethod.GET)
	@ResponseBody
	public List<List<String>> getArraylist(){
		System.out.println("*******************Get Portal List**************************");
		List<List<String>> data = new ArrayList<List<String>>();
		data.add(customService.getStudentArraylist());
		data.add(customService.getBookArraylist());
		return data;
	}
	
	@RequestMapping(value="/portalDetails", method=RequestMethod.GET)
	@ResponseBody
	public List<Object> portalDetails(@RequestParam("query") String query){
		String[] data = query.split(",");
		if (null != data[0] && data[0].length() > 0 && null != data[1] && 0 < data[1].length()) {
			return customService.populatePortalDetails(data[0], data[1]);
		} else
			return null;
	}
	
	
	
	@RequestMapping(value="/portalIssueBook", method=RequestMethod.GET)
	@ResponseBody
	public StringResponse portalIssueBook(@RequestParam("query") String query){
		System.out.println("portalIssueBook " + query);
		String[] data = query.split(",");
		String email = data[0];
		int bookId = Integer.parseInt(data[1]);
		String retData = bookService.issueBook(email, bookId);
		System.out.println("Response : " + retData);
		if(retData.contains("success")){
			resp.setResponseCode("success");
		}else{
			resp.setResponseCode("error");
		}
		resp.setResponse(retData);
		return resp;
	}
	
	@RequestMapping(value="/portalReIssueBook", method=RequestMethod.GET)
	@ResponseBody
	public StringResponse portalReIssueBook(@RequestParam("query") String query){
		System.out.println("portalIssueBook " + query);
		String[] data = query.split(",");
		String email = data[0];
		int bookId = Integer.parseInt(data[1]);
		String retData = bookService.reIssueBook(email, bookId);
		System.out.println("Response : " + retData);
		if(retData.contains("success")){
			resp.setResponseCode("success");
		}else{
			resp.setResponseCode("error");
		}
		resp.setResponse(retData);
		return resp;
	}
	
	
	@RequestMapping(value="/portalReturnBook", method=RequestMethod.GET)
	@ResponseBody
	public StringResponse portalReturnBook(@RequestParam("query") String query){
		System.out.println("portalIssueBook " + query);
		String[] data = query.split(",");
		String email = data[0];
		int bookId = Integer.parseInt(data[1]);
		String retData = bookService.returnBook(email, bookId);
		System.out.println("Response : " + retData);
		if(retData.contains("success")){
			resp.setResponseCode("success");
		}else{
			resp.setResponseCode("error");
		}
		resp.setResponse(retData);
		return resp;
	}
	
	
}
