package library.management.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import library.management.beans.*;
import library.management.dao.*;

@Service
public class CustomService {

	@Autowired UserRoleDAO roleDAO;
	@Autowired UserDAO userDAO;
	@Autowired StudentRepoDAO studentDAO;
	@Autowired BookDAO bookDAO;
	@Autowired StudentBookDAO recordDAO;
	
	@Autowired UserRoleBean roleBean;
	@Autowired LibraryUserBean userBean;
	@Autowired StudentRepoBean studentBean;
	@Autowired BookRepoBean bookBean;
	@Autowired StudentBookBean recordBean;
	@Autowired Utils getUtils;
	
	
	public List<StudentBookBean> getRecordList(){
		this.studentBookFineUpdate();
		List<StudentBookBean> recordBeanList = recordDAO.showAll();
		for (int iter = 0; iter < recordBeanList.size(); iter++) {
			recordBeanList.get(iter).setBook_name(getMap(bookDAO.searchAll()).get(recordBeanList.get(iter).getBook_id()));
			recordBeanList.get(iter).setStudent_name(getMap(studentDAO.searchAll()).get(recordBeanList.get(iter).getStudent_id()));
		}
		return recordBeanList;
	}
	
	public List<LibraryUserBean> getUserRoleList(){
		List<LibraryUserBean> recordBeanList = userDAO.listUser();
		for (int iter = 0; iter < recordBeanList.size(); iter++) {
			recordBeanList.get(iter).setRole_name(getMap(roleDAO.getAllRole()).get(recordBeanList.get(iter).getRole()));
		}
		return recordBeanList;
	}
	
	

	public <T> Map<Integer,String> getMap(List<T> beanType){
		Map<Integer,String> data = new HashMap<>();
		if(beanType.get(0) instanceof UserRoleBean){
			for(T b1 : beanType){
				data.put(((UserRoleBean) b1).getId(), ((UserRoleBean) b1).getRole());
			}
		}else if(beanType.get(0) instanceof StudentRepoBean){
			for(T b1 : beanType){
				data.put(((StudentRepoBean) b1).getId(), ((StudentRepoBean) b1).getName());
			}
		}else if(beanType.get(0) instanceof BookRepoBean){
			for(T b1 : beanType){
				data.put(((BookRepoBean) b1).getId(), ((BookRepoBean) b1).getName());
			}
		}
		return data;
	}
	
	
	public List<String> getStudentArraylist(){
		List<String> data = new ArrayList<>();
		for(LibraryUserBean b1: userDAO.listUser()){
			if(b1.getRole() == 3){
				data.add(b1.getEmail());
			}
		}
		return data;
	}
	
	public List<String> getBookArraylist(){
		List<String> data = new ArrayList<>();
		for(BookRepoBean b1: bookDAO.searchAll()){
			data.add(b1.getName());
		}
		return data;
	}
	
	
	public List<Object> populatePortalDetails(String email, String bookName){
		this.studentBookFineUpdate();
		List<Object> data = new ArrayList<Object>();
		data.add(studentDAO.searchStudent(email));
		List<BookRepoBean> bookList = bookDAO.searchViaParam("name",bookName);
		data.add(bookList.get(0));
		recordBean = recordDAO.checkIfBookNotReturned(bookList.get(0).getId(), email);
		data.add(recordBean);
		return data;
	}
	
	public void studentBookFineUpdate(){
		List<StudentBookBean> recordBeanList = recordDAO.showAll();
		for(int iter = 0; iter< recordBeanList.size(); iter++){
			long due = getUtils.getDateDiff(recordBeanList.get(iter).getRenew_date(), getUtils.getDate(0));
			if(due > 0){
				recordBeanList.get(iter).setDue((int)due);
				recordBeanList.get(iter).setFine((int)(due * recordBeanList.get(iter).getRate()));
				recordDAO.setFine(recordBeanList.get(iter));
			}
		}
	}
	
	
	
	
	
	
	
}
