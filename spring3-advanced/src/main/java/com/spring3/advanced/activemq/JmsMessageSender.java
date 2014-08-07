package com.spring3.advanced.activemq;

import java.io.Serializable;
import java.util.List;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;

import com.spring3.advanced.domain.Book;

@Service
public class JmsMessageSender {
	@Autowired
	private JmsTemplate jmsTemplate;

	public void sayHello() {
		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createTextMessage("Hello, World!");
			}
		});
	}

	public void saveBooks(final List<Book> books) {
		jmsTemplate.send(new MessageCreator() {
			public Message createMessage(Session session) throws JMSException {
				return session.createObjectMessage((Serializable) books);
			}
		});
		
	}
}
