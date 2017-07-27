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
                    <li><a href="${pageContext.request.contextPath}/user/home">Home</a></li>
                    <li><a href="${pageContext.request.contextPath}/user/main">Courses</a></li>
                    <li><a href="${pageContext.request.contextPath}/about">About</a></li>
                    <li><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <div class="container">
        <div class="title_page_wrap">
            <span class="title_page">Edit your account:</span>
        </div>

		<table>
			<tr>
				<td>Name:</td>
				<td><input type="text" class="userFirstname" value="${userFirstname}" /></td>
			</tr>
			<tr>
				<td>Lastname:</td>
				<td><input type="text" class="userLastname" value="${userLastname}" /></td>
			</tr>
			<tr>
				<td>Middlename:</td>
				<td><input type="text" class="userMiddlename" value="${userMiddlename}" /></td>
			</tr>
			<tr>
				<td>Login:</td>
				<td><input type="text" class="userLogin" value="${userLogin}" /></td>
			</tr>
			<tr>
				<td>Birthday:</td>
				<td><input type="text" class="userBirthday" value="${userBirthday}" /></td>
			</tr>
			<tr>
				<td colspan="2"><input type="button" value="Edit" class="editBtn myMediumBtn"></td>
			</tr>
		</table>
		<div class="result"></div>
    </div>



<s:message code="Courses"/>
</body>
</html>
<script src='<c:url value="/resources/js/jquery-3.2.1.js"/>'></script>
<script src='<c:url value="/resources/js/cabinet.js"/>'></script>
