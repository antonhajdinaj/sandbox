package com.spring3.advanced.service;

import java.util.List;

import com.spring3.advanced.domain.Book;

public interface BookService {
	List<Book> getAllBooks();

	void deleteBook(Long id);

	Book findBook(Long bookId);

	List<Book> findBookByCategory(String category);

	List<Book> findBookByELCategory(String category);

	
	List<Book> findBooksByTitle(String title);

	void addBook(Book book);
}
