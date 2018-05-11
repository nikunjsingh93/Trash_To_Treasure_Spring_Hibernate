<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="com.captcha.botdetect.web.servlet.Captcha"%>




<!DOCTYPE html>
<html>
<head>
<title>Add User Form</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


</head>
<body>

		   <div  class="container">
		   
		       <div class="col-md-2 col-md-offset-5">



	<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	<a href="${contextPath}">Go Back</a><br/>

	<h2>Register a New User</h2>

	<form:form action="${contextPath}/user/register" commandName="user"
		method="post">

		
				<h3>First Name:
				<form:input path="firstName" size="30" required="required" />
					<font color="red"><form:errors path="firstName" /></font></h3>
		
				<h3>Last Name:
				<form:input path="lastName" size="30" required="required" />
					<font color="red"><form:errors path="lastName" /></font></h3>
			
				<h3>User Name:
				<form:input path="username" size="30" required="required" />
					<font color="red"><form:errors path="username" /></font></h3>
		
				<h3>Password:
				<form:password path="password" size="30"
						required="required" /> <font color="red"><form:errors
							path="password" /></font></h3>
			
				<h3>Email Id:
				<form:input path="email.emailAddress" size="30"
						type="email" required="required" /> <font color="red"><form:errors
							path="email.emailAddress" /></font></h3>
			
				<label for="captchaCode" class="prompt">Retype the characters from the picture:</label> 
				
				
				
				<%
					// Adding BotDetect Captcha to the page
					Captcha captcha = Captcha.load(request, "CaptchaObject");
					captcha.setUserInputID("captchaCode");

					String captchaHtml = captcha.getHtml();
					out.write(captchaHtml);
				%> 
				
				
				<input id="captchaCode" type="text" name="captchaCode" required="required"/>
				
				<br><br>
			
				<input type="submit" value="Register User" />
			

	</form:form>
	
	</div>
	
	</div>

</body>
</html>