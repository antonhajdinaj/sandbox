package com.java.advanced.dao;

import java.util.Collection;

import com.java.advanced.domain.Book;

public interface BookDAO {

	public Collection<Book> findAllBooks();

	public Integer saveBook(Book b);

	public boolean updateBook(Book b);

	public boolean deleteBook(Book b);
}
