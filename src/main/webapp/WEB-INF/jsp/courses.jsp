<%--
	Created by IntelliJ IDEA.
	User: Sergey Petrov
	Date: 21.07.2017
	Time: 19:49
	To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Courses</title>
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
					<li><a class="active" href="${pageContext.request.contextPath}/courses">Courses</a></li>
					<li><a href="${pageContext.request.contextPath}/about">About</a></li>
					<li><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="addCourse_wrap">
		<a href="${pageContext.request.contextPath}/newcourse"><div class="addCourse_btn">Добавить курс</div></a>
	</div>

	<div id="mainBlock" class="container">
		<table class="course_table">
			<tr>
				<th class="col-md-6">Название курса</th>
				<th class="col-md-2">Преподаватель</th>
				<th class="col-md-2">Начало курса</th>
				<th class="col-md-2"></th>
			</tr>
			<c:forEach var="item" items="${listCourses}">
				<tr>
					<td class="col-md-5">${item.name}</td>
					<td class="col-md-2">${item.id}</td>
					<td class="col-md-3">${item.createDate}</td>
					<td class="col-md-2"><a href="${pageContext.request.contextPath}/editcourse?id=${item.id}"><input type="button" value="Edit" class="editbtn"/></a></td>
				</tr>
			</c:forEach>
		</table>
	</div>
<s:message code="Courses"/>



</body>
</html>
<script src='<c:url value="/resources/js/jquery-3.2.1.js"/>'></script>
