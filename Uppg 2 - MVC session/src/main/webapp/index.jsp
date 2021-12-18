<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
// Kollar om det redan finns en session igång
if (session.getAttribute("user") != null) {
	// Om det finns skickas man till GET i LoginController!
	RequestDispatcher rd = request.getRequestDispatcher("Login");
	rd.forward(request, response);
}
%>






<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inloggningssidan</title>
</head>
<body>

<!-- Skickar till POST i LoginController!-->
	<form action="<%=request.getContextPath()%>/Login" method="post">
			Användarnamn:<input name="uname" type="text">
			Lösenord:<input name="pass" type="password">
			<input type="submit" value="Logga in">
	</form>
</body>
</html>
