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







</head>
<body>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

	   <div style="text-align: center;">

   		   
   		   

<a href="${contextPath}/user/">Home</a><br/>


<c:if test="${sessionScope.user.username == 'admin'}">

 <h1>Here are your Flagged messages from users</h1>
 
 
 </c:if>
 
  <c:if test="${sessionScope.user.username != 'admin'}">




    <h1>Here are your messages:</h1>
    
    
   </c:if>    
	
	 <c:set var="tempuserto" value="${sessionScope.user.username}" />
	
	
	<table border="1" cellpadding="5" cellspacing="5"  align="center">
		<tr>
			<td><b>Message</b></td>
			
			<td><b>For Item</b></td>
			
		    <td><b>Message From</b></td>
		    
		     <td><b>Reply</b></td>
		    
		</tr>
		<c:forEach var="adv" items="${adverts}">
		
		<c:if test="${adv.toPersonId == tempuserto}">
		
			<tr>
				<td>${adv.message}</td>
				
				<c:set var="tempForItem" value="${adv.itemName}" />
				<td>${adv.itemName}</td>
				
				<c:set var="tempFromUser" value="${adv.fromPersonId}" />
                <td>${adv.fromPersonId}</td>
                
                
                <td><a href="${contextPath}/message/reply?forItem=${tempForItem}&fromUser=${tempFromUser}">Reply via Message</a></td>
		    
                
       	</tr>
       	
       	 </c:if>
		</c:forEach>
	
	</table>
	

</div>

</body>
</html>