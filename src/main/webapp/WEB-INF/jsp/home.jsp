<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>home</h1>

<a href="" onclick="">Click</a>
<div>${pageContext.request.contextPath}</div>
</body>
</html>
<%--<script src="${pageContext.request.contextPath}/WEB-INF/js/jquery-3.2.1.js"></script>--%>
<script src='<c:url value="/WEB-INF/js/jquery-3.2.1.js"/>'></script>