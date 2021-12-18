<%@page import="beans.UserBean"%>  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Welcome page</title>
</head>
<body>  
<p>You are successfully logged in!</p>  


<%
  
  // kollar om det inte finns n�gon User i session
  if(session.getAttribute("user") == null){
	
	// Skickar till Logout servlet om det inte finns n�gon user bean i session
	RequestDispatcher rd = request.getRequestDispatcher("Logout");
	rd.forward(request, response);
	
	
  } else {
	  
	  
	// Men om det finns...
	
	// Vi h�mtar information fr�n Userbean
	UserBean bean = (UserBean) request.getAttribute("user");
	
	  
	  //Skriver ut v�lkomostfras till user. F�r namn genom getName
	  out.print("<h1>Welcome, " + bean.getName() + "</h1>");
	
	  // Om inte FavoriteSnack inte �r tom --> printa ut favorite snack
	  if(bean.getFavoriteSnack() != null){
		out.print("<p>Your favorite snack is " + bean.getFavoriteSnack() + ".</p>");
	  }
	  if(bean.getFavoriteMonth() != null){
			out.print("<p>Your favorite month is " + bean.getFavoriteMonth() + ".</p>");
		  }
  }
%>

<!-- l�gger in snack i Userbean. g�r via loginservlet, GET metoden -->
<form action="<%= request.getContextPath() %>/Login" method="get">  
   	Favorite snack:<input type="text" name="snack"/> 
   	Favorite month:<input type="text" name="month"/> 
   	<input type="submit" value="send"/>  
</form>

<!-- Post metod till Logout servlet. F�r att logga ut.  -->
<p>press this button to log out: </p>
<form action="<%= request.getContextPath() %>/Logout" method="post"> 
   	<input type="submit" value="logout"/>  
</form>

</body>
</html>