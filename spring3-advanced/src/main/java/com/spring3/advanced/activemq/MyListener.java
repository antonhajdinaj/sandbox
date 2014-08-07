package com.spring3.advanced.activemq;

import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;

import org.springframework.beans.factory.annotation.Autowired;

import com.spring3.advanced.domain.Book;
import com.spring3.advanced.service.BookService;

public class MyListener implements MessageListener {

	@Autowired
	BookService bookService;

	@Override
	public void onMessage(Message message) {
		System.out.println("Hello world");
		if (message instanceof ObjectMessage) {
			ObjectMessage objectMessage = (ObjectMessage) message;
			try {
				@SuppressWarnings("unchecked")
				List<Book> books = (List<Book>) objectMessage.getObject();

				for (Book book : books) {
					System.out.println("Add book " + book.getIsbn());
					bookService.addBook(book);
				}
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}
	}
}
