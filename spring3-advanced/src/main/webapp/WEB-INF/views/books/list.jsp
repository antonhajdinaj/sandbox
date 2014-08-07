<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html>
<head>
<title>Welcome to Books</title>
</head>
<body>
	<h1>List of Books</h1>
	<table border="1">
		<tr>
			<th>id</th>
			<th>title</th>
			<th>author</th>
			<th>category</th>
			<th>Action</th>
		</tr>
		<c:url value="/" var="contextRoot" />
		<c:forEach var="book" items="${books}">
			<tr>
				<td><a href="${contextRoot}book/${book.id}">${book.id}</a></td>
				<td>${book.title}</td>
				<td>${book.author}</td>
				<td>${book.category}</td>
				<td><a href="delete/${book.id}">Delete</a></td>
			</tr>
		</c:forEach>
	</table>
	<h1>Search by category</h1>
	<form method="post" action="${contextRoot}search">
		Category <input type="text" name="category" /> <input type="submit"
			value="Search" />
	</form>

	<a href="${contextRoot}searchByTitle">Search by title</a>
	<br />
	<a href="${contextRoot}books/add">Add a book</a>
	<br />
	<c:url value="/createNewBook.flow" var="url" />
	<a href="${url}">Start flow here</a>
	<a href="${contextRoot}sendMessage">Send message</a>
	
</body>
</html>
