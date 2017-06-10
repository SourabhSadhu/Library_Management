package library.management.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import library.management.beans.UserRoleBean;
import library.management.dao.UserRoleDAO;

@Repository
public class UserRoleDAOImpl implements UserRoleDAO{

	@Autowired
	JdbcTemplate jdbcTemplate;
	
	@Override
	public int findRoleID(String role) {
		String sql = "SELECT id FROM user_role WHERE role = ?";
		return jdbcTemplate.queryForObject(sql,new Object[]{role},Integer.class);
	}

	@Override
	public void createRole(String roleName) {
		String sql  = "insert into user_role (role) values (?)";
		System.out.println(jdbcTemplate.update(sql,roleName));
	}

	@Override
	public String findRoleName(int id) {
		String sql = "SELECT role FROM user_role WHERE id = ?";
		return jdbcTemplate.queryForObject(sql,new Object[]{id},String.class);
	}
	
	@Override
	public List<UserRoleBean> getAllRole(){
		String sql = "select * from user_role";
		return jdbcTemplate.query(sql, new BeanPropertyRowMapper<UserRoleBean>(UserRoleBean.class));
	}
	
}
