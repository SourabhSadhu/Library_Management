package library.management.dao.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import library.management.beans.BookRepoBean;
import library.management.dao.BookDAO;

@Repository
public class BookDAOImpl implements BookDAO{

	static String tableName = "book_repo";
	static String insertBook = "insert into " + tableName + " (name,author,category,sub_category,count,total) values (?,?,?,?,?,?)";
	static String searchAllSql = "select * from " + tableName + " order by id";
	static String searchBookSql = "select * from " + tableName + " where id = ?";
	static String searchViaName = "select * from " + tableName + " where name LIKE ?";
	static String searchViaAuthor = "select * from " + tableName + " where author LIKE ?";
	static String searchViaCategory = "select * from " + tableName + " where category LIKE ?";
	static String searchViaSubCategory = "select * from " + tableName + " where sub_category LIKE ?";	
	static String updateEntry = "update " + tableName + " set name = ?, author = ?, category = ?, sub_category = ?, count = ?, total = ? where id = ?";
	static String deleteEntry= "delete from " + tableName + " where id = ?";
	
	@Autowired JdbcTemplate jdbcTemplate;
	@Autowired BookRepoBean book;
	
	@Override
	public void createEntry(BookRepoBean book) {
		jdbcTemplate.update(insertBook, new Object[]{book.getName(),book.getAuthor(),
				book.getCategory(),book.getSub_category(),book.getCount(),book.getTotal() });
	}

	@Override
	public List<BookRepoBean> searchViaParam(String queryVia,String bookParam) {
		System.out.println("Named Query "+queryVia+"||"+bookParam);
		List<BookRepoBean> result;
		switch(queryVia){
		case "name":
			result = jdbcTemplate.query(searchViaName, new Object[]{"%"+bookParam+"%"}, new BeanPropertyRowMapper<BookRepoBean>(BookRepoBean.class));
			break;
		case "author":
			result = jdbcTemplate.query(searchViaAuthor, new Object[]{"%"+bookParam+"%"}, new BeanPropertyRowMapper<BookRepoBean>(BookRepoBean.class));
			break;		
		case "category":
			result = jdbcTemplate.query(searchViaCategory, new Object[]{"%"+bookParam+"%"}, new BeanPropertyRowMapper<BookRepoBean>(BookRepoBean.class));
			break;
		case "sub_category":
			result = jdbcTemplate.query(searchViaSubCategory, new Object[]{"%"+bookParam+"%"}, new BeanPropertyRowMapper<BookRepoBean>(BookRepoBean.class));
			break;
		default:
			result = null;
		}
		return result;
	}

	@Override
	public List<BookRepoBean> searchAll() {
		return jdbcTemplate.query(searchAllSql, new BeanPropertyRowMapper<BookRepoBean>(BookRepoBean.class));
	}

	@Override
	public BookRepoBean searchViaId(int id) {
		return jdbcTemplate.queryForObject(searchBookSql, new Object[]{id}, new BeanPropertyRowMapper<BookRepoBean>(BookRepoBean.class));
	}
	
	@Override
	public void updateBook(BookRepoBean book) {
		jdbcTemplate.update(updateEntry,new Object[]{ book.getName(), book.getAuthor(), book.getCategory(), book.getSub_category(), book.getCount(), book.getTotal() ,book.getId()});
	}

	@Override
	public void deleteEntry(int id) {
		jdbcTemplate.update(deleteEntry,id);
	}

}
