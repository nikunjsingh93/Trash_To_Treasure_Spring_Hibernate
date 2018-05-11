<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>User Home</title>


<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>




<script>
	function ajaxEvent() {

		var xmlHttp;
		try // Firefox, Opera 8.0+, Safari
		{
			xmlHttp = new XMLHttpRequest();
		} catch (e) {
			try // Internet Explorer
			{
				xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
			} catch (e) {
				try {
					xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
				} catch (e) {
					alert("Your browser does not support AJAX!");
					return false;
				}
			}
		}

		xmlHttp.onreadystatechange = function() {
			if (xmlHttp.readyState == 4) {
				document.getElementById("coursediv").innerHTML = xmlHttp.responseText;
			}
		}

		var queryString = document.getElementById("queryString").value;

		xmlHttp.open("POST", "../user/login.htm?course=" + queryString, true);
		xmlHttp.send();
		xmlHttp.stop();

	}
</script>

</head>
<body>


	<div style="text-align: center;">
	
	
<div> 
		<h1 class="text-danger">Welcome To Northeastern Trash To Treasure
			Portal</h1>
</div>


		<h1>Hi, ${user.firstName}</h1>



		<a href="${contextPath}/me/user/logout">Logout</a> <br />
		<br>





		<c:set var="contextPath" value="${pageContext.request.contextPath}" />


		<c:if test="${sessionScope.user.username != 'admin'}">


			<a href="${contextPath}/category/add">Add Tags</a>
			<br />
			<a href="${contextPath}/advert/add">Add Your Items to Sell</a>
			<br />

		</c:if>


		<c:if test="${sessionScope.user.username != 'admin'}">


			<br />
			<a href="${contextPath}/message/list">View Messages</a>
		</c:if>

		<c:if test="${sessionScope.user.username == 'admin'}">
			<br />
			<a href="${contextPath}/message/list">View Flagged Messages</a>

		</c:if>


		<br /> <br /> Search Items: <input type="text" id="queryString"
			size="30" onkeyup="ajaxEvent()" />

	</div>





	<div id="coursediv" style="text-align: center;">


		<h2>Here are the list available to buy</h2>
		<br>


		<c:forEach var="adv" items="${adverts}">

			<c:set var="temptitle" value="${adv.title}" />

			<c:set var="tempuserto" value="${adv.user.username}" />



			<c:url var="imageUrl" value='/resources/${adv.id}.jpg' />
			
			<img src="<c:url value = "/images/${adv.id}.jpg"/>" height="300px" width="300px" alt="ItemPic">


			<h4>${adv.title}</h4>

			<h4>
				<a href="${contextPath}/user/detail?tname=${adv.title}">Click
					for Info</a>
			</h4>

			<c:if test="${sessionScope.user.username != 'admin'}">

				<h4>
					<a
						href="${contextPath}/message/adminadd?titlei=${temptitle}&useri=${tempuserto}">Report
						inappropriate</a>
				</h4>


			</c:if>

			<c:if test="${sessionScope.user.username == 'admin'}">

				<h4>
					<a href="${contextPath}/advert/delete${adv.id}">Delete Post</a>
				</h4>


			</c:if>


			<br>
			<br>

		</c:forEach>



	</div>


</body>
</html>