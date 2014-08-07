package com.spring3.advanced.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Properties;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.JpaVendorAdapter;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.Database;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import com.spring3.advanced.dao.BookDAO;
import com.spring3.advanced.dao.BookDAOImpl;
import com.spring3.advanced.domain.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
@Transactional
@ActiveProfiles(value = "test")
public class BookDAOImplTestCase {

	@Configuration
	@Profile("test")
	static class BookDAOTestConfig {
		@Bean
		public BookDAO bookDAO() {
			return new BookDAOImpl();
		}

		@Bean
		public LocalContainerEntityManagerFactoryBean entityManagerFactoryBean() {
			final LocalContainerEntityManagerFactoryBean factoryBean = new LocalContainerEntityManagerFactoryBean();
			factoryBean.setDataSource(dataSource());
			factoryBean
					.setPackagesToScan(new String[] { "com.spring3.advanced.domain" });

			final JpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter() {
				{
					setDatabase(Database.H2);
					setDatabasePlatform("org.hibernate.dialect.H2Dialect");
					setShowSql(true);
					setGenerateDdl(true);
				}
			};

			final Properties additionalProperties = new Properties();
			additionalProperties.setProperty("hibernate.hbm2ddl.auto",
					"create-drop");

			factoryBean.setJpaVendorAdapter(vendorAdapter);
			factoryBean.setJpaProperties(additionalProperties);

			return factoryBean;
		}

		@Bean
		public DataSource dataSource() {
			EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
			EmbeddedDatabase db = builder.setType(EmbeddedDatabaseType.H2)
					.build();
			return db;
		}

		@Bean
		public JpaTransactionManager transactionManager() {
			final JpaTransactionManager transactionManager = new JpaTransactionManager();
			transactionManager
					.setEntityManagerFactory(entityManagerFactoryBean()
							.getObject());

			return transactionManager;
		}

	}

	@Autowired
	BookDAO bookDAO;

	private Book createNewBook() {
		Book book = new Book();
		book.setIsbn("9780886775278");
		book.setTitle("Black Sun Rising");
		book.setAuthor("C.S. Friedman");
		book.setCategory("Science Fiction");
		book.setPages(new Integer(586));
		book.setPrice(new Double(8.99));
		return book;
	}

	@Test
	public void testStoreBook() {
		Book book = createNewBook();
		assertNull("Book id expected to be null!", book.getId());
		bookDAO.storeBook(book);
		assertNotNull("Book id not expected to be null!", book.getId());
	}

	@Test
	public void testGetBookById() {
		Book book = bookDAO.getBookById(1L);
		assertNotNull("Book not expected to be null!", book);
		assertEquals("Expected to retrieve book with ID 1L!", 1L, book.getId()
				.longValue());
	}

	@Test
	public void testGetBookByIsbn() {
		Book book = bookDAO.getBookByIsbn("9780553801477");
		assertNotNull("Book not expected to be null!", book);
		assertEquals("Expected to retrieve book with isbn 9780553801477!",
				"9780553801477", book.getIsbn());
		book = bookDAO.getBookByIsbn("0000000000000");
		assertNull("Book expected to be null!", book);
	}

	@Test
	public void testGetBooksByCategory() {
		List<Book> books = bookDAO.getBooksByCategory("Fantasy");
		assertNotNull("List of books not expected to be null!", books);
		assertEquals("List of books returned an incorrect number of results!",
				3, books.size());
	}

	@Test
	public void testGetAllBooks() {
		List<Book> books = bookDAO.getAllBooks();
		assertNotNull("List of books not expected to be null!", books);
		assertTrue("List of books returned an incorrect number of results!",
				books.size() > 0);
	}

	@Test
	public void testUpdateBook() {
		Book book = bookDAO.getBookById(1L);
		assertNotNull("Book not expected to be null!", book);
		assertNotNull("Book price not expected to be null!", book.getPrice());
		double price = book.getPrice().doubleValue();
		book.setPrice(new Double(10.0));
		book = bookDAO.updateBook(book);
		assertNotNull("Book not expected to be null!", book);
		assertEquals("Expected to retrieve book with ID 1L!", 1L, book.getId()
				.longValue());
		assertNotEquals("Price expected to be different!", price, book
				.getPrice().doubleValue());
		assertEquals("Price expected to be 10.0!", 10.0, book.getPrice()
				.doubleValue(), 0.01);
	}

	@Test
	public void testDeleteBook() {
		Book book = bookDAO.getBookById(1L);
		assertNotNull("Book not expected to be null", book);
		bookDAO.deleteBook(1L);
		book = bookDAO.getBookById(1L);
		assertNull("Book expected to be null!", book);
	}

}
