package com.java.advanced.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;

import com.java.advanced.domain.Book;
import com.java.advanced.temp.JDBCTemplate;
import com.java.advanced.temp.Mapper;

public class BookDAOImplTemplate implements BookDAO {

	JDBCTemplate jdbcTemplate = new JDBCTemplate();

	@Override
	public Collection<Book> findAllBooks() {
		return jdbcTemplate.executeQ("select id, isbn, title, author, publisher, category, pages, price from Book", null, new BookMapper());
	}

	@Override
	public Integer saveBook(Book b) {
		return jdbcTemplate.executeUpdateWithKeys(
				"INSERT INTO Book(isbn, title, author, publisher, category, pages, price) VALUES(?, ?, ?, ?, ?, ?, ?)",
				new Object[] { b.getIsbn(), b.getTitle(), b.getAuthor(), b.getPublisher(), b.getCategory(), b.getPages(), b.getPrice() },
				new BookMapper());
	}

	@Override
	public boolean updateBook(Book b) {
		return jdbcTemplate.executeUpdateWithKeys(
				"UPDATE Book set id = ?, isbn = ?, title = ?, author = ?, publisher = ?, category = ?, pages = ?, price = ? where id = ?",
				new Object[] { b.getId(), b.getIsbn(), b.getTitle(), b.getAuthor(), b.getPublisher(), b.getCategory(), b.getPages(), b.getPrice(),
						b.getId() }, new BookMapper()) != null;
	}

	@Override
	public boolean deleteBook(Book b) {
		return jdbcTemplate.executeUpdateWithKeys("DELETE FROM Book where id = ?", new Object[] { b.getId() }, new BookMapper()) != null;
	}

	private class BookMapper implements Mapper<Book> {
		@Override
		public Book result(ResultSet rs) throws SQLException {
			Book book = new Book();
			book.setId(rs.getLong("id"));
			book.setAuthor(rs.getString("author"));
			book.setCategory(rs.getString("category"));
			book.setIsbn(rs.getString("isbn"));
			book.setTitle(rs.getString("title"));
			book.setPrice(rs.getInt("price"));
			book.setPublisher(rs.getString("publisher"));
			return book;
		}
	}

}
