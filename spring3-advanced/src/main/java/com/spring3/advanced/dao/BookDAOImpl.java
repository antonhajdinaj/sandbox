package com.spring3.advanced.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.spring3.advanced.domain.Book;

@Repository
public class BookDAOImpl implements BookDAO {

	@PersistenceContext
	EntityManager em;

	@Override
	public Long storeBook(Book book) {
		em.persist(book);
		return book.getId();
	}

	@Override
	public Book getBookById(Long id) {
		return em.find(Book.class, id);
	}

	@Override
	public Book getBookByIsbn(String isbn) {
		List<Book> results = em.createNamedQuery(Book.BY_ISBN, Book.class)
				.setParameter("isbn", isbn).getResultList();
		return results.size() > 0 ? results.get(0) : null;
	}

	@Override
	public List<Book> getBooksByCategory(String category) {
		return em.createNamedQuery(Book.BY_CATEGORY, Book.class)
				.setParameter("category", category).getResultList();
	}

	@Override
	public List<Book> getAllBooks() {
		return em.createNamedQuery(Book.ALL, Book.class).getResultList();
	}

	@Override
	public Book updateBook(Book book) {
		return em.merge(book);
	}

	@Override
	public void deleteBook(Long id) {
		em.remove(em.find(Book.class, id));
	}

	@Override
	public List<Book> getBooksByTitle(String title) {
		return em.createNamedQuery(Book.BY_TITLE, Book.class)
				.setParameter("title", title).getResultList();
	}

	@Override
	public void addBook(Book book) {
		em.persist(book);
	}

}
