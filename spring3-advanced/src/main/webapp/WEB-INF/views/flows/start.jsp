<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Welcome to the first Flow ! Click to continue
	<form:form>
		<input type="hidden" name="_flowExecutionKey"
			value="${flowExecutionKey}" />
		<input type="submit" name="_eventId_next" value="Next" />
	</form:form>
</body>
</html>