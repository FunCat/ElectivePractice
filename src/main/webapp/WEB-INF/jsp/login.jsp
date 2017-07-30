<%--
  Created by IntelliJ IDEA.
  User: Sergey Petrov
  Date: 26.07.2017
  Time: 9:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Login</title>
    <meta name="_csrf" content="${_csrf.token}"/>
    <meta name="_csrf_header" content="${_csrf.headerName}"/>
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
    <form:form action="${pageContext.request.contextPath}/login" method="POST">
        <table>
            <tr>
                <td>Login:</td>
                <td><input class="userLogin" type="text" name="username"/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input class="userPassword" type="password" name="password"/></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center"><input class="myMediumBtn" type="submit" value="Login"/></td>
            </tr>
        </table>
    </form:form>
    <div class="result">
        <c:if test="${not empty error}">
            <div>${error}</div>
        </c:if>
    </div>
</div>

</body>
</html>