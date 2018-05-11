<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Trash To Treasure</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>



</head>
<body>

   	<c:set var="contextPath" value="${pageContext.request.contextPath}"/>
   
		   <div style="text-align: center;">
		   
		   <h1  class="text-danger">Northeastern Trash To Treasure</h1>
		   
		   <br>

	
	
		<c:set var="cook" value="${pageContext.request.contextPath}"/>
	
	
	
	<h2>Login</h2>
	<form action="${contextPath}/user/login" method="post">
	
		
		    <h3>Username:
		    <input name="username" size="30" required="required" /></h3>
		
		    <h3>Password:
		    <input type="password" name="password" size="30" required="required"/>
		    <br><br>
			    <input type="submit" value="Login" /></h3>
	
	</form>
	
		<h3><a href="${contextPath}/user/register">Register a new User</a><br/></h3>
	
	
	</div>


</body>
</html>

