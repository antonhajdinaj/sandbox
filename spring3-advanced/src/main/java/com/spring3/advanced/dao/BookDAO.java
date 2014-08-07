package com.spring3.advanced.dao;

import java.util.List;

import com.spring3.advanced.domain.Book;

public interface BookDAO {
	Long storeBook(Book book);

	Book getBookById(Long id);

	Book getBookByIsbn(String isbn);

	List<Book> getBooksByCategory(String category);

	List<Book> getAllBooks();

	Book updateBook(Book book);

	void deleteBook(Long id);

	List<Book> getBooksByTitle(String title);

	void addBook(Book book);
}
