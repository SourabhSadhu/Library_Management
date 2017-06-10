package library.management.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import library.management.beans.*;
import library.management.dao.*;

public class BookService {

	// Beans
	@Autowired BookRepoBean bookRepoBean;
	@Autowired StudentRepoBean studentRepoBean;
	@Autowired StudentBookBean studentBookBean;
	@Autowired Utils getUtils;
	//DAOs
	@Autowired BookDAO bookDAO;
	@Autowired StudentRepoDAO studentDAO;
	@Autowired StudentBookDAO studentBookDAO;
	
	public List<Object> initCheck(String studentEmail, int bookId){
		List<Object> data = new ArrayList<Object>();
//		System.out.println("Init"+studentEmail + " | " + bookId);
		studentRepoBean = studentDAO.searchStudent(studentEmail);
//		System.out.println("Student creds " + studentRepoBean.getName()+ " | " + studentRepoBean.getEmail() + " | " + studentRepoBean.getId());
		if(null != studentRepoBean){
			data.add(studentRepoBean);
			bookRepoBean = bookDAO.searchViaId(bookId);
//			System.out.println("Book creds "+bookRepoBean.getName() + " Count " + bookRepoBean.getCount());
			if(null != bookRepoBean){
				data.add(bookRepoBean);
			}
		}
		return data;
	}
	
	public long fineCalculate(StudentBookBean bean){
		long fine = 0;
		fine = getUtils.getDateDiff(bean.getRenew_date(), getUtils.getDate(0)) * bean.getRate();
		return fine;
	}
	
	public List<BookRepoBean> getBookList(){
		return bookDAO.searchAll();
	}
	
	public List<BookRepoBean> getBookListByParam(String paramName, String paramValue){
		return bookDAO.searchViaParam(paramName, paramValue);
	}
	
	public String issueBook(String studentEmail,int bookId){
		List<Object> data = this.initCheck(studentEmail,bookId);
		if(null != data && data.size() == 2){
			studentRepoBean = (StudentRepoBean) data.get(0);
			bookRepoBean = (BookRepoBean) data.get(1);
			if(null != bookRepoBean && bookRepoBean.getCount() < bookRepoBean.getTotal()){
//				System.out.println("Max allowed books" + studentRepoBean.getBook_count() +" taken "+ studentRepoBean.getMax_books());
				if(studentRepoBean.getBook_count() < studentRepoBean.getMax_books()){
//					System.out.println("Not return check for "+bookRepoBean.getId() + " | " + studentRepoBean.getEmail() + " | " + studentRepoBean.getId());
					studentBookBean = studentBookDAO.checkIfBookNotReturned(bookRepoBean.getId(), studentRepoBean.getEmail());
					if(null != studentBookBean && !studentBookBean.isReturned()){
						return "Student already owns this book";
					}else{
						bookRepoBean.setCount(bookRepoBean.getCount()+1);
						bookDAO.updateBook(bookRepoBean);
						
						studentBookBean = new StudentBookBean();
						studentBookBean.setBook_id(bookRepoBean.getId());
						studentBookBean.setEmail(studentRepoBean.getEmail());
						studentBookBean.setStudent_id(studentRepoBean.getId());
						studentBookBean.setDue(0);
						studentBookBean.setFine(0);
						studentBookBean.setIssue_date(getUtils.getDate(0));
						studentBookBean.setRenew_date(getUtils.getDate(3));
						studentBookBean.setReturned(false);
						studentBookDAO.issueBook(studentBookBean);
						
						studentRepoBean.setBook_count(studentRepoBean.getBook_count() + 1);
						studentDAO.updateEntry(studentRepoBean);
						
						return bookRepoBean.getName() + " issued successfully for " + studentRepoBean.getEmail();
					}
				}else return "Student ineligible to be issued";
			}else return "Book unavailable";
		}
		return "Unable to issue the book";
	}
	
	
	public String reIssueBook(String studentEmail, int bookId){
		List<Object> data = this.initCheck(studentEmail, bookId);
		if(null != data && data.size() == 2){
			studentRepoBean = (StudentRepoBean) data.get(0);
			bookRepoBean = (BookRepoBean) data.get(1);
			studentBookBean = studentBookDAO.checkIfBookNotReturned(bookRepoBean.getId(), studentRepoBean.getEmail());
			if(null == studentBookBean || studentBookBean.isReturned()){
				return "Student does not owns this book";
			}else{
				studentBookBean.setReturned(true);
				studentBookDAO.retunBook(studentBookBean);
				
				studentBookBean = new StudentBookBean();
				studentBookBean.setBook_id(bookRepoBean.getId());
				studentBookBean.setEmail(studentRepoBean.getEmail());
				studentBookBean.setStudent_id(studentRepoBean.getId());
				studentBookBean.setDue(0);
				studentBookBean.setFine(0);
				studentBookBean.setIssue_date(getUtils.getDate(0));
				studentBookBean.setRenew_date(getUtils.getDate(3));
				studentBookBean.setReturned(false);
				studentBookDAO.issueBook(studentBookBean);
				
				return bookRepoBean.getName() + " issued successfully to " + studentRepoBean.getName();
			}
		}
		return "Unable to re-issue book";
	}
	
	public String returnBook(String studentEmail, int bookId){
		List<Object> data = this.initCheck(studentEmail, bookId);
		if(null != data && data.size() == 2){
			studentRepoBean = (StudentRepoBean) data.get(0);
			bookRepoBean = (BookRepoBean) data.get(1);
			studentBookBean = studentBookDAO.checkIfBookNotReturned(bookRepoBean.getId(), studentRepoBean.getEmail());
			if(null == studentBookBean || studentBookBean.isReturned()){
				return "Student does not owns this book";
			}else{
				studentBookBean.setReturned(true);
				studentBookDAO.retunBook(studentBookBean);
				
				return bookRepoBean.getName() + " returned successfully by " + studentRepoBean.getName();
			}
		}
		return "Unable to return book";
	}
	
}
