<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

<meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>



</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

   <div style="text-align: center;">

		<a href="${contextPath}/user/">Home</a><br/>


<h1>Item Detail</h1>

<c:set var="temp" value="${param.tname}" />


	<c:forEach var="adv" items="${adverts}">
	
   <c:if test="${adv.title == temp}">
                    
                    
       <img src="<c:url value = "/images/${adv.id}.jpg"/>" height="300px" width="300px" alt="ItemPic">             
                    
     
      <h2>Name:    ${adv.title}</h2>
      
      <c:set var="temptitle" value="${adv.title}" />
      
      <c:set var="tempuserto" value="${adv.user.username}" />
      

       <h2>Condition:    ${adv.message}</h2>
	  <h2>Posted by:    ${adv.user.username}</h2>
	<h2>Tags:    <c:forEach var="categ" items="${adv.categories}">
                  	${categ} , 
         </c:forEach></h2>
                    	 
        </c:if>
                    
	</c:forEach>
	
	
	<c:if test="${sessionScope.user.username != 'admin'}"> 
	
	
	<h4><a href="${contextPath}/message/add?titlei=${temptitle}&useri=${tempuserto}" >Click here to Ask Seller if this Item is Still Available</a></h4> <br />
	
	
	</c:if>
	
	</div>
	
	

</body>
</html>