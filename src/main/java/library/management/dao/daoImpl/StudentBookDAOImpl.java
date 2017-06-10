package library.management.dao.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import library.management.beans.StudentBookBean;
import library.management.dao.StudentBookDAO;

@Repository
public class StudentBookDAOImpl implements StudentBookDAO{

	@Autowired JdbcTemplate jdbcTemplate;
	
	static String tableName = "student_book_repo";
	static String issueBook = "insert into " + tableName
			+ "(book_id,student_id,email,issue_date,renew_date,due,rate,fine,returned) values(?,?,?,?,?,?,?,?,?)";
	static String reIssueBook = "update " + tableName + " set renew_date = ? where id = ?";
	static String returnBook = "update " + tableName + " set returned = true where id = ?";
	static String rateUpdate = "update " + tableName + " set rate = ? where id = ?";
	static String rateUpdateAll = "update " + tableName + " set rate = ?";
	static String fineUpdate = "update " + tableName + " set due = ?, fine = ? where book_id = ? AND student_id = ? AND issue_date = ?"; 
	static String showAll = "select * from " + tableName + " where returned = 'false'";
	static String getStudentBookBean = "select * from " + tableName + " where book_id = ? AND email = ? AND returned = 'false'";
	
	@Override
	public void issueBook(StudentBookBean studentBookBean) {
		jdbcTemplate.update(issueBook, new Object[] { studentBookBean.getBook_id(), studentBookBean.getStudent_id(),
				studentBookBean.getEmail(), studentBookBean.getIssue_date(), studentBookBean.getRenew_date(),
				studentBookBean.getDue(),studentBookBean.getRate(),studentBookBean.getFine(),
				studentBookBean.isReturned()});
	}

	@Override
	public void reIssueBook(StudentBookBean studentBookBean) {
		jdbcTemplate.update(reIssueBook,new Object[]{studentBookBean.getRenew_date(),studentBookBean.getId()});
	}

	@Override
	public void retunBook(StudentBookBean studentBookBean) {
		jdbcTemplate.update(returnBook,new Object[]{studentBookBean.getId()});
	}

	@Override
	public void rateUpdate(StudentBookBean studentBookBean) {
		jdbcTemplate.update(rateUpdate,new Object[]{studentBookBean.getRate(),studentBookBean.getId()});
	}

	@Override
	public void rateUpdateAll(StudentBookBean studentBookBean) {
		jdbcTemplate.update(rateUpdateAll,new Object[]{studentBookBean.getRenew_date()});
	}

//	@Override
//	public double fineCalculate(int id) {
//		return 0;
//	}

	@Override
	public List<StudentBookBean> showAll() {
		return jdbcTemplate.query(showAll, new BeanPropertyRowMapper<StudentBookBean>(StudentBookBean.class));
	}

	@Override
	public StudentBookBean checkIfBookNotReturned(int book_id,String email) {
		List<StudentBookBean> list = jdbcTemplate.query(getStudentBookBean, new Object[] { book_id, email },
				new BeanPropertyRowMapper<StudentBookBean>(StudentBookBean.class));
		if (list.isEmpty() || list.size() > 1)
			return null;
		else
			return list.get(0);
	}

	@Override
	public void setFine(StudentBookBean bean) {
		jdbcTemplate.update(fineUpdate, new Object[]{bean.getDue(), bean.getFine(), bean.getBook_id(), bean.getStudent_id(), bean.getIssue_date()});
	}

}
