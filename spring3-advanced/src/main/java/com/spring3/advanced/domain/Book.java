package com.spring3.advanced.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Version;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.spring3.advanced.validation.AuthorName;

@Entity
@NamedQueries({
		@NamedQuery(name = Book.BY_ISBN, query = "select b from Book b where b.isbn = :isbn"),
		@NamedQuery(name = Book.BY_CATEGORY, query = "select b from Book b where b.category = :category"),
		@NamedQuery(name = Book.BY_TITLE, query = "select b from Book b where b.title like :title"),
		@NamedQuery(name = Book.ALL, query = "select b from Book b") })
public class Book implements Serializable {
	private static final long serialVersionUID = 1L;
	public static final String BY_ISBN = "Book.byIsbn";
	public static final String BY_CATEGORY = "Book.byCategory";
	public static final String ALL = "Book.all";
	public static final String BY_TITLE = "Book.byTitle";

	@Id
	@GeneratedValue
	private Long id;
	@Version
	private Long version;

	@Column(length = 13, nullable = false, unique = true)
	@NotBlank
	@Size(min = 13, max = 13)
	private String isbn;

	@Column(length = 255, nullable = false)
	@Size(max = 255)
	@NotBlank
	private String title;

	@Column(length = 100, nullable = false)
	@NotBlank
	@Size(max = 100)
	@AuthorName
	private String author;

	@Column(length = 50)
	@Size(max = 50)
	private String category;
	private Integer pages;

	@Min(0)
	private Double price;

	public Book() {
		super();
	}

	public Book(String isbn, String title, String author) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.author = author;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getPages() {
		return pages;
	}

	public void setPages(Integer pages) {
		this.pages = pages;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

}
