package library.management.dao.daoImpl;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;
import library.management.beans.LibraryUserBean;

public class UserBeanRowMapper implements RowMapper<LibraryUserBean>{

	@Override
	public LibraryUserBean mapRow(ResultSet rs, int arg1) throws SQLException {
		LibraryUserBean bean = new LibraryUserBean();
		bean.setId(rs.getInt("id"));
		bean.setRole(rs.getInt("role"));
		bean.setName(rs.getString("name"));
		bean.setEmail(rs.getString("email"));
		bean.setPassword(rs.getString("password"));
		bean.setCreation_date(rs.getDate("creation_date"));
		bean.setActive_till(rs.getDate("active_till"));
		return bean;
	}

}
