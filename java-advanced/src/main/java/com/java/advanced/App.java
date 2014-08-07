package com.java.advanced;

import java.util.Collection;

import com.java.advanced.dao.BookDAO;
import com.java.advanced.dao.BookDAOImplTemplate;
import com.java.advanced.domain.Book;

/**
 * Hello world!
 * 
 */
public class App {

	private static BookDAO bookDAO = new BookDAOImplTemplate();

	public static void main(String[] args) {

		// show all the Books
		printAllTheBooks();

		Book book = insertNewBook();

		// update that book
		updateBook(book);

		// show all the Books
		printAllTheBooks();

		// delete book
		deleteBook();

		// show all the Books
		printAllTheBooks();
	}

	private static void deleteBook() {
		// delete the book with id 2
		System.out.println("Delete the book with id = 6");
		Book bookToDelete = new Book();
		bookToDelete.setId(6);

		System.out.println("Result = " + bookDAO.deleteBook(bookToDelete));
	}

	private static void updateBook(Book book) {
		System.out.println("Update the book with id 2, set the auhor to Anton Hajdinaj");
		book.setAuthor("Anton Hajdinaj");
		book.setId(2);
		System.out.println("Result = " + bookDAO.updateBook(book));

		System.out.println("------------------------------------------------------------------");
	}

	private static Book insertNewBook() {
		// insert a new book
		System.out.println("Insert a new Book:");
		Book book = new Book("isbn", "title", "author", "publisher", "category", "pages", 35);
		System.out.println("Book to create = " + book);

		System.out.println("generated id of newly created book = " + bookDAO.saveBook(book));

		System.out.println("------------------------------------------------------------------");
		return book;
	}

	private static void printAllTheBooks() {
		Collection<Book> books = bookDAO.findAllBooks();
		System.out.println("Show all the books");
		for (Book book : books) {
			System.out.println(book);
		}
		System.out.println("------------------------------------------------------------------");
	}

}
