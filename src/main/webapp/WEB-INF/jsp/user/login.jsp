<%--
  Created by IntelliJ IDEA.
  User: Sergey Petrov
  Date: 26.07.2017
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Login</title>
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
					<li><a href="${pageContext.request.contextPath}/">Home</a></li>
					<li><a href="${pageContext.request.contextPath}/courses">Courses</a></li>
					<li><a href="${pageContext.request.contextPath}/about">About</a></li>
					<li><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<div class="title_page_wrap">
			<span class="title_page">Login</span>
		</div>
		<table>
			<tr>
				<td>Login:</td>
				<td><input class="userLogin" type="text" name="userLogin"/></td>
			</tr>
			<tr>
                   <td>Password:</td>
				<td><input class="userPassword" type="password" name="userPassword"/></td>
			</tr>
			<tr>
				<td colspan="2" style="text-align: center"><input id="loginCheck" class="myMediumBtn" type="button" value="Login"/></td>
			</tr>
		</table>
        <div class="result"></div>
	</div>

</body>
</html>
<script src='<c:url value="/resources/js/jquery-3.2.1.js"/>'></script>
<script src='<c:url value="/resources/js/login.js"/>'></script>
