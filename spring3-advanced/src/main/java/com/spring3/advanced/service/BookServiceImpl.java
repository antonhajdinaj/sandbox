package com.spring3.advanced.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring3.advanced.dao.BookDAO;
import com.spring3.advanced.domain.Book;

@Service
@Transactional
public class BookServiceImpl implements BookService {

	@Autowired
	BookDAO bookDAO;

	@Override
	public List<Book> getAllBooks() {
		return bookDAO.getAllBooks();
	}

	@Override
	public void deleteBook(Long bookId) {
		bookDAO.deleteBook(bookId);
	}

	@Override
	public Book findBook(Long bookId) {
		return bookDAO.getBookById(Long.valueOf(bookId));
	}

	@Override
	public List<Book> findBookByCategory(String category) {
		return bookDAO.getBooksByCategory(category);
	}

	@Override
	public List<Book> findBooksByTitle(String title) {
		return bookDAO.getBooksByTitle(title);
	}

	@Override
	public void addBook(Book book) {
		bookDAO.addBook(book);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Book> findBookByELCategory(String category) {
//		ExpressionParser parser = new SpelExpressionParser();
//		StandardEvaluationContext context = new StandardEvaluationContext();
//		context.setRootObject(bookDAO);
//		context.setVariable("category", category);
//		return (List<Book>)parser.parseExpression("getAllBooks().?[Category == #category]").getValue(context);

		 ExpressionParser parser = new SpelExpressionParser();
		 StandardEvaluationContext context = new StandardEvaluationContext();
		 context.setVariable("books", getAllBooks());
		 context.setVariable("category", category);
		 return
		 (List<Book>)parser.parseExpression("#books.?[Category == #category]").getValue(context);

	}

}
