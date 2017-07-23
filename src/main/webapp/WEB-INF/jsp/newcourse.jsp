<%--
  Created by IntelliJ IDEA.
  User: Sergey Petrov
  Date: 23.07.2017
  Time: 16:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>New Course</title>
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

    <div class="container">
        <div class="title_page_wrap">
            <span class="title_page">Add new course</span>
        </div>
        <form name="newcourse" action="addcourse" method="POST" >
            <div>
                Name: <input type="text" name="courseName" /> <input type="submit" value="Add" class="addBtn">
            </div>
        </form>
    </div>

</body>
</html>
