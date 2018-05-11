<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>List Adverts</title>


<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>



</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	
			   <div style="text-align: center;">
	

	<a href="${contextPath}/user/">Home</a><br/>

	<table border="1" cellpadding="5" cellspacing="5">
		<tr>
			<td><b>ADVERT TITLE</b></td>
			<td><b>MESSAGE</b></td>
			<td><b>POSTED BY</b></td>
			<td><b>CATEGORIES</b></td>
		</tr>
		<c:forEach var="adv" items="${adverts}">
			<tr>
				<td>${adv.title}</td>
				<td>${adv.message}</td>
				<td>${adv.user.username}</td>
				<td><c:forEach var="categ" items="${adv.categories}">
                    	${categ} , 
                    </c:forEach></td>
			</tr>
		</c:forEach>
	</table>
	
	</div>
</body>
</html>