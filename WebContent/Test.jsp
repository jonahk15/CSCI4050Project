<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
	import="java.util.Calendar"
	import="csci4050.JavaTest"
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>BookBay | JSP Test</title>
</head>
<body>
	<h1>Current Date: <%= Calendar.getInstance().getTime() %></h1>
	
	<form action="JavaTest" method="post">
		<button type="submit">Submit</button>
	</form>
</body>
</html>