<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Home page</title>
	<link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
	<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" type="text/css" />
	<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet" type="text/css" />
</head>
<body>

	<nav class="navbar navbar-fixed-top">
		<div class="container">
			<div class="navbar-header">
				Site name
			</div>

			<div id="navbar">
				<ul class="nav">
					<li><a class="active" href="${pageContext.request.contextPath}/">Home</a></li>
					<li><a href="${pageContext.request.contextPath}/courses">Courses</a></li>
					<li><a href="${pageContext.request.contextPath}/about">About</a></li>
					<li><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<a href="" onclick="">Click</a>
	<div>${pageContext.request.contextPath}</div>
</body>
</html>
<%--<script src="${pageContext.request.contextPath}/WEB-INF/js/jquery-3.2.1.js"></script>--%>
<script src='<c:url value="/resources/js/jquery-3.2.1.js"/>'></script>
<script src='<c:url value="/resources/js/bootstrap.min.js"/>'></script>
