<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Add Tags</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>



</head>
<body>

   <div style="text-align: center;">


	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}/user/">Home</a><br/>

	<h2>Add a New Tag</h2>
	


	<form:form action="${contextPath}/category/add" method="post" commandName="category">

<br>
			
				<h4>Tag Name:</h4>
				<form:input path="title" size="30" required="required" /> <font color="red"><form:errors
							path="title" /></font>
			

			
				<input type="submit" value="Create Tag" />
			
	

	</form:form>
	
	</div>

</body>
</html>