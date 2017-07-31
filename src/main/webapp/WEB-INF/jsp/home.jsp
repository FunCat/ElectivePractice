<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
	<title>Home page</title>
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
	<link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
	<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet" type="text/css" />
	<link href="<c:url value="/resources/css/main.css" />" rel="stylesheet" type="text/css" />
</head>
<body>
	<jsp:include page="/WEB-INF/jsp/template/header.jsp" />


	<a href="" onclick="">Click</a>
	<div>${pageContext.request.contextPath}</div>
</body>
</html>
<%--<script src="${pageContext.request.contextPath}/WEB-INF/js/jquery-3.2.1.js"></script>--%>
<script src='<c:url value="/resources/js/jquery-3.2.1.js"/>'></script>
<script src='<c:url value="/resources/js/bootstrap.min.js"/>'></script>
