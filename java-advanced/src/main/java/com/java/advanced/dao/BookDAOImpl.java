package com.java.advanced.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.java.advanced.domain.Book;
import com.java.advanced.exception.ErrorLogger;
import com.java.advanced.temp.JDBCTemplate;

public class BookDAOImpl implements BookDAO {

	JDBCTemplate template = new JDBCTemplate();

	public static final String URL = "jdbc:mysql://localhost:3306/test";

	public BookDAOImpl() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			ErrorLogger.INSTANCE.handleError(e);
		}
	}

	@Override
	public Collection<Book> findAllBooks() {

		List<Book> books = new ArrayList<>();
		try (Connection connection = getConnection();) {
			Statement statement = connection.createStatement();

			ResultSet rs = statement
					.executeQuery("select id, isbn, title, author, publisher, category, pages, price from Book");

			while (rs.next()) {
				books.add(new Book(rs.getLong("id"), rs.getString("isbn"), rs
						.getString("title"), rs.getString("author"), rs
						.getString("publisher"), rs.getString("category"), rs
						.getString("pages"), rs.getInt("price")));
			}

		} catch (SQLException e) {
			ErrorLogger.INSTANCE.handleError(e);
		}
		return books;
	}

	@Override
	public Integer saveBook(Book b) {

		try (Connection connection = getConnection();) {

			PreparedStatement ps = connection
					.prepareStatement(
							"INSERT INTO Book(isbn, title, author, publisher, category, pages, price) VALUES(?, ?, ?, ?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			ps.setString(1, b.getIsbn());
			ps.setString(2, b.getTitle());
			ps.setString(3, b.getAuthor());
			ps.setString(4, b.getPublisher());
			ps.setString(5, b.getCategory());
			ps.setString(6, b.getPages());
			ps.setInt(7, b.getPrice());

			ps.executeUpdate();
			ResultSet rsGeneratedKeys = ps.getGeneratedKeys();
			if (rsGeneratedKeys.next()) {
				return rsGeneratedKeys.getInt(1);
			}
		} catch (SQLException e) {
			ErrorLogger.INSTANCE.handleError(e);
		}
		return null;
	}

	private Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL, "root", "");
	}

	@Override
	public boolean updateBook(Book b) {
		int executeUpdate = 0;
		try (Connection connection = getConnection();) {

			PreparedStatement ps = connection
					.prepareStatement("UPDATE Book set id = ?, isbn = ?, title = ?, author = ?, publisher = ?, category = ?, pages = ?, price = ? where id = ?");
			ps.setLong(1, b.getId());
			ps.setString(2, b.getIsbn());
			ps.setString(3, b.getTitle());
			ps.setString(4, b.getAuthor());
			ps.setString(5, b.getPublisher());
			ps.setString(6, b.getCategory());
			ps.setString(7, b.getPages());
			ps.setInt(8, b.getPrice());
			ps.setLong(9, b.getId());

			executeUpdate = ps.executeUpdate();

		} catch (SQLException e) {
			ErrorLogger.INSTANCE.handleError(e);
		}
		return executeUpdate > 0;
	}

	@Override
	public boolean deleteBook(Book b) {
		int executeUpdate = 0;
		try (Connection connection = getConnection();) {

			PreparedStatement ps = connection
					.prepareStatement("DELETE FROM Book where id = ?");
			ps.setLong(1, b.getId());

			executeUpdate = ps.executeUpdate();

		} catch (SQLException e) {
			ErrorLogger.INSTANCE.handleError(e);
		}
		return executeUpdate > 0;
	}
}
