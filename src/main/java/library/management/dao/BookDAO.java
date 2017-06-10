package library.management.dao;

import java.util.List;

import library.management.beans.BookRepoBean;

public interface BookDAO {
	void createEntry(BookRepoBean book);
	public BookRepoBean searchViaId(int id);
	List<BookRepoBean> searchViaParam(String queryVia,String bookParam);
	List<BookRepoBean> searchAll();
	void updateBook(BookRepoBean book);
	void deleteEntry(int id);
}
