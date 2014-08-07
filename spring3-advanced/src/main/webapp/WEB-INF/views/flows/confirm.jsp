<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form modelAttribute="book">
		<input type="hidden" name="_flowExecutionKey"
			value="${flowExecutionKey}" />
			
		Title : ${book.title}
		<br />
		ISBN : ${book.isbn}
		<br />
		Category : ${book.category}
		<br />
		Author : ${book.author}
		<br />
		Price : ${book.price}
		<br />
		Pages : ${book.pages}

		<input type="submit" name="_eventId_previous" value="Previous" />
		<input type="submit" name="_eventId_submit" value="submit" />
		
	</form:form>

</body>
</html>