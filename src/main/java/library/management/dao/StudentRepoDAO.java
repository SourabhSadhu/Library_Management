package library.management.dao;

import java.util.List;

import library.management.beans.StudentRepoBean;

public interface StudentRepoDAO {
	void createEntry(StudentRepoBean student);
	void updateEntry(StudentRepoBean student);
	List<StudentRepoBean> searchAll();
	StudentRepoBean searchStudent(String email);
	public void deleteStudent(int id);
}
