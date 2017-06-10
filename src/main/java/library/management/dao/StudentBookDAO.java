package library.management.dao;

import java.util.List;

import library.management.beans.StudentBookBean;

public interface StudentBookDAO {

	public void issueBook(StudentBookBean studentBookBean);
	public void reIssueBook(StudentBookBean studentBookBean);
	public void retunBook(StudentBookBean studentBookBean);
	public void rateUpdate(StudentBookBean studentBookBean);
	public void rateUpdateAll(StudentBookBean studentBookBean);
	public void setFine(StudentBookBean studentBookBean);
	public List<StudentBookBean> showAll();
	public StudentBookBean checkIfBookNotReturned(int book_id, String email);
}
