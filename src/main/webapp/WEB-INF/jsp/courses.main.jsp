<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/WEB-INF/tld/spring.tld" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Main Courses</h1>

<ul>
    <c:forEach var="item" items="${courses}">
        <li>${item.id} | ${item.name}</li>
    </c:forEach>
</ul>
<s:message code="Courses"/>
</body>
</html>
<script src="${pageContext.request.contextPath}/WEB-INF/js/jquery-3.2.1.js"></script>
<script src='<c:url value="/WEB-INF/js/jquery-3.2.1.js"/>'></script>