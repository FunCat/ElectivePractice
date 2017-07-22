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
					<li><a class="active" href="${pageContext.request.contextPath}/all">Courses</a></li>
					<li><a href="${pageContext.request.contextPath}/about">About</a></li>
					<li><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
				</ul>
			</div>
		</div>
	</nav>

	<div class="container">
		<table class="course_table">
			<tr>
				<th class="col-md-6">Название курса</th>
				<th class="col-md-2">Преподаватель</th>
				<th class="col-md-2">Начало курса</th>
				<th class="col-md-2"></th>
			</tr>
			<c:forEach var="item" items="${listCourses}">
				<form name="editcourse" action="editcourse?id=${item.id}" method="POST">
					<tr>
						<td class="col-md-5">${item.name}</td>
						<td class="col-md-2">${item.id}</td>
						<td class="col-md-3">${item.createDate}</td>
						<td class="col-md-2"><input type="submit" value="Edit" class="editbtn" /></td>
					</tr>
				</form>
			</c:forEach>
		</table>
	</div>
<s:message code="Courses"/>


<form name="newcourse" action="newcourse" method="POST" >
	<div>
		<p>Add course</p>
		<label>Name:</label> <input type="text" name="courseName" />
		<label>&nbsp;</label> <input type="submit" value="Add" class="btn">
	</div>
</form>

</body>
</html>
