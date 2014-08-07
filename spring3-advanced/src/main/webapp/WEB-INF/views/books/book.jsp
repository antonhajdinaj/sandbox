<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!doctype html>
<html>
<head>
<title>Welcome to Books</title>
</head>
<body>
	<h1>Book</h1>
	${book.isbn}
	<br /> ${book.title}
	<br /> ${book.author}
	<br /> ${book.category}
	<br />

</body>
</html>
