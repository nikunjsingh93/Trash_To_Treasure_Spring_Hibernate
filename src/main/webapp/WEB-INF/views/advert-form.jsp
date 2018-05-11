<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>Add Advert Form</title>




</head>
<body>
	<c:set var="contextPath" value="${pageContext.request.contextPath}" />
	
	   <div style="text-align: center;">
	

	<a href="${contextPath}/user/">Home</a><br/>

	<h2>Post a New Item</h2>


	<form:form action="${contextPath}/advert/add" method="post"
		commandName="advert" enctype="multipart/form-data">

				<h4>Posted By:
				${sessionScope.user.username}</h4>
				<form:hidden path="postedBy"
						value="${sessionScope.user.personID}" />

				<h4>Tags:
				<form:select path="categories" items="${categories}"
						multiple="true" required="required" /></h4>

				<h4>Item Name:
				<form:input type="text" path="title" size="30" required="required"/></h4>

				<h4>Description:
				<form:input type="text" path="message" size="30" required="required"/></h4>
			
                 <h4>Pick image:
                 <form:input type="file" path="data" size="50"  /></h4>

				<input type="submit" value="Post Item" />
		

	</form:form>
	
	
	</div>

</body>
</html>