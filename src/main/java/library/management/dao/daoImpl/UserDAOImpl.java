package library.management.dao.daoImpl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import library.management.beans.*;
import library.management.dao.UserDAO;

@Repository
public class UserDAOImpl implements UserDAO {

	@Autowired
	JdbcTemplate jdbcTemplate;
//	@Autowired	private BCryptPasswordEncoder bCryptPasswordEncoder;
	@Autowired
	LibraryUserBean user;

	static String tableName = "library_user";
	static String listUserSql = "select * from " + tableName + " order by role";
	static String addUserSql = "insert into " + tableName + "(role,name,email,password,active_till) values(?,?,?,?,?)";
	static String deleteUserSql = "update " + tableName + " set status = 'DEACTIVE' where id = ?";
	static String updateUserSql = "update " + tableName
			+ " set name = ?, email = ?, password = ?, active_till = ? where id = ?";
	static String queryViaEmail = "select * from " + tableName + " where email = ?";

	public List<LibraryUserBean> listUser() throws DataAccessException {
		List<LibraryUserBean> result = jdbcTemplate.query(listUserSql,
				new BeanPropertyRowMapper<LibraryUserBean>(LibraryUserBean.class));
		return result;
	}

	public void addUser(LibraryUserBean user) throws DataAccessException{
		jdbcTemplate.update(addUserSql, new Object[] { user.getRole(), user.getName(), user.getEmail(),
				user.getPassword(), user.getActive_till() });
	}

	public void updateUser(LibraryUserBean user) throws DataAccessException {
		jdbcTemplate.update(updateUserSql, new Object[] { user.getName(), user.getEmail(), user.getPassword(),
				user.getActive_till(), user.getId() });
	}

	@Override
	public void deleteUser(int id) throws DataAccessException {
		jdbcTemplate.update(deleteUserSql, id);
	}

	@Override
	public LibraryUserBean checkViaEmail(String email) throws DataAccessException {
			List<LibraryUserBean> user = jdbcTemplate.query(queryViaEmail, new Object[] { email },
					new BeanPropertyRowMapper<LibraryUserBean>(LibraryUserBean.class));
			if(null == user) return null;
			else return user.get(0);
	}

}
