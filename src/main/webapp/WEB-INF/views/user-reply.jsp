<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Reply To Customer</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>



</head>
<body>

   <c:set var="useri" value="${param.forItem}" />
   
      <c:set var="titlei" value="${param.fromUser}" />
   
   
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	
	
			   <div style="text-align: center;">
	

	<a href="${contextPath}/user/">Home</a><br/>

	<h2>Reply To Customer</h2>


	<form:form action="${contextPath}/message/reply" method="post" commandName="message">

		<table align="center">
			<tr>
				<td>Message:</td>
				<td><form:input path="message" size="30" required="required" /> <font color="red"><form:errors
							path="message" /></font></td>
							
				<td><form:hidden path="fromPersonId"
						value="${sessionScope.user.username}" /></td>
						
				<td><form:hidden path="itemName"
						value="${param.forItem}" /></td>
			    
			    <td><form:hidden path="toPersonId"
						value="${param.fromUser}" /></td>	
									
			</tr>

			<tr>
				<td colspan="2"><br><input type="submit" value="Send" /></td>
			</tr>
		</table>

	</form:form>
	
	</div>

</body>
</html>