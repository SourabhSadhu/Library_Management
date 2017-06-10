package library.management.dao.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import library.management.beans.StudentRepoBean;
import library.management.dao.StudentRepoDAO;
@Repository
public class StudentRepoDAOImpl implements StudentRepoDAO{

	@Autowired JdbcTemplate jdbcTemplate; 
	
	static String tableName = "student_repo";
	static String createEntrySql = "insert into " + tableName + "(name,email,year,stream,roll,max_books) values(?,?,?,?,?,?)";
	static String updateEntrySql = "update " + tableName + " set "	+ "name = ? , email = ? , year = ? , stream = ? , roll = ? , max_books = ? , book_count = ? where id = ?";
	static String searchAllSql = "select * from " + tableName;
	static String searchStudentSql = "select * from " + tableName + " where email = ?";
	static String deleteStudentSql = "delete from " + tableName + " where id = ?";

	@Override
	public void createEntry(StudentRepoBean student) {
		jdbcTemplate.update(createEntrySql, new Object[] { student.getName(), student.getEmail(), student.getYear(),
				student.getStream(), student.getRoll(), student.getMax_books() });
	}

	@Override
	public void updateEntry(StudentRepoBean student) {
		jdbcTemplate.update(updateEntrySql, new Object[] { student.getName(), student.getEmail(), student.getYear(),
				student.getStream(), student.getRoll(), student.getMax_books(),student.getBook_count(),
				student.getId()});
	}

	@Override
	public List<StudentRepoBean> searchAll() {
		return jdbcTemplate.query(searchAllSql, new BeanPropertyRowMapper<StudentRepoBean>(StudentRepoBean.class));
	}

	@Override
	public StudentRepoBean searchStudent(String email) {
		return jdbcTemplate.queryForObject(searchStudentSql, new Object[]{email}, new BeanPropertyRowMapper<StudentRepoBean>(StudentRepoBean.class));
	}

	@Override
	public void deleteStudent(int id) {
		jdbcTemplate.update(deleteStudentSql, id);
	}

}
