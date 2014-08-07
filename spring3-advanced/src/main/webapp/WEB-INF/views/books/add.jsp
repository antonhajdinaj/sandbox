<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%! @SuppressWarnings("rawtypes") %>
<!doctype html>
<html>
<head>
<title>Add a book</title>
</head>
<body>
	<h1>Add a book</h1>
	<form:form commandName="book">
		Title <form:input path="title" /> <form:errors path="title" cssclass="error"/>
		<br/>
		ISBN <form:input path="isbn" /> <form:errors path="isbn" cssclass="error"/>
		<br/>
		Category <form:input path="category" /> <form:errors path="category" cssclass="error"/>
		<br/>
		Author <form:input path="author" /> <form:errors path="author" cssclass="error"/>
		<br/>
		Price <form:input path="price" /> <form:errors path="price" cssclass="error"/>
		<br/>
		Pages <form:input path="pages" /> <form:errors path="pages" cssclass="error"/>

		<input type="submit" value="Add" /> 
	</form:form>
</body>
</html>
