package com.spring3.advanced.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.RandomUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.spring3.advanced.activemq.JmsMessageSender;
import com.spring3.advanced.domain.Book;
import com.spring3.advanced.service.BookService;

@Controller
public class BookController {

	@Autowired
	BookService bookService;

	@Autowired
	JmsMessageSender jmsMessageSender;

	@RequestMapping("/books/list")
	public String getAllBooks(Model model) {
		List<Book> books = bookService.getAllBooks();
		model.addAttribute("books", books);
		return "books/list";
	}

	@RequestMapping("/")
	public String home() {
		return "redirect:/books/list";
	}

	@RequestMapping("/books/delete/{id}")
	public String deleteBook(@PathVariable Long id) {
		bookService.deleteBook(id);
		return home();
	}

	@RequestMapping("/book/{id}")
	public String showBook(@PathVariable Long id, Model model) {
		Book book = bookService.findBook(id);
		model.addAttribute("book", book);
		return "books/book";
	}

	@RequestMapping(value = "/search", method = RequestMethod.POST)
	public String findBook(@RequestParam String category, Model model) {
		List<Book> books = bookService.findBookByELCategory(category);
		model.addAttribute("books", books);
		return "/books/list";
	}

	@RequestMapping(value = "/searchByTitle", method = RequestMethod.GET)
	public String prepareSearchForm(Model model) {
		BookSearch bookSearch = new BookSearch();
		model.addAttribute("bookSearch", bookSearch);
		return "/books/bookSearch";
	}

	@RequestMapping(value = "/searchByTitle", method = RequestMethod.POST)
	public String handleSearch(@ModelAttribute BookSearch bookSearch,
			BindingResult result, Model model) {
		List<Book> books = bookService.findBooksByTitle(bookSearch.getTitle());
		model.addAttribute("books", books);
		return "/books/list";
	}

	@RequestMapping(value = "/books/add", method = RequestMethod.GET)
	public String prepareAddForm(Model model) {
		Book book = new Book();
		model.addAttribute("book", book);
		return "/books/add";
	}

	@RequestMapping(value = "/books/add", method = RequestMethod.POST)
	public String handleAddForm(@Valid @ModelAttribute Book book,
			BindingResult result, Model model) {
		if (result.hasErrors()) {
			return "books/add";
		}
		bookService.addBook(book);
		return home();
	}

	@RequestMapping("/sendMessage")
	public String sendMessage() {
//		jmsMessageSender.sayHello();
		List<Book> books = getRandomBooks();
		jmsMessageSender.saveBooks(books);
		return home();
	}

	private List<Book> getRandomBooks() {
		List<Book> books = new ArrayList<>();
		String chars = "azertyuiopqsdfghjklmwxcvbn";
		for (int i = 0; i < 10; i++) {
			System.out.println("AAAAAAAAAAAAAAAAA = " + RandomStringUtils.random(5, chars));
			books.add(new Book(RandomStringUtils.random(13, chars), RandomStringUtils
					.random(5, chars), RandomStringUtils.random(5, chars) + " "
					+ RandomStringUtils.random(5, chars)));
		}
		return books;
	}

}
