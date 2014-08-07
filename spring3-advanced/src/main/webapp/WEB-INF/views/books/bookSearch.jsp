<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!doctype html>
<html>
<head>
<title>Welcome to Books</title>
</head>
<body>
	<h1>Search by title</h1>
	<form:form commandName="bookSearch">
		<form:input path="title" />
		<input type="submit" value="Search" />
	</form:form>
</body>
</html>
