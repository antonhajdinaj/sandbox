<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%! @SuppressWarnings("rawtypes") %>
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
		Title <form:input path="title" /> <form:errors path="title" cssclass="error"/>
		<br/>
		ISBN <form:input path="isbn" /> <form:errors path="isbn" cssclass="error"/>
		<br/>

		Author <form:input path="author" /> <form:errors path="author" cssclass="error"/>
		<br/>
		<input type="submit" name="_eventId_next" value="Next" />
	</form:form>

</body>
</html>