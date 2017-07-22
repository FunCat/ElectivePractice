<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/WEB-INF/tld/spring.tld" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel = "stylesheet"
          type = "text/css"
          href = "css/style.css" />
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
<script src="js/jquery-3.2.1.js"></script>
<%--<script src="./js/jquery-3.2.1.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script>--%>