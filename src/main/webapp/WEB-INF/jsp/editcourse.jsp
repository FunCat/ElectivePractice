<%--
  Created by IntelliJ IDEA.
  User: Sergey Petrov
  Date: 21.07.2017
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Edit course</title>
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
                    <li><a class="active" href="${pageContext.request.contextPath}/courses">Courses</a></li>
                    <li><a href="${pageContext.request.contextPath}/about">About</a></li>
                    <li><a href="${pageContext.request.contextPath}/contact">Contact</a></li>
                </ul>
            </div>
        </div>
    </nav>

    <form name="edit" action="edit?id=${courseId}" method="POST" >
        <input type="text" name="courseName" value="${courseName}" />
        <input type="submit" value="Update" />
    </form>
</body>
</html>
