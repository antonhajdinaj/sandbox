<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%! @SuppressWarnings("rawtypes") %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<form:form modelAttribute="book" >
		<input type="hidden" name="_flowExecutionKey"
			value="${flowExecutionKey}" />
		Category <form:input path="category" /> <form:errors path="category" cssclass="error"/>
		<br/>
		Price <form:input path="price" /> <form:errors path="price" cssclass="error"/>
		<br/>
		Pages <form:input path="pages" /> <form:errors path="pages" cssclass="error"/>

		<input type="submit" name="_eventId_previous" value="Previous" />

		<input type="submit" name="_eventId_next" value="Next" />
	</form:form>

</body>
</html>