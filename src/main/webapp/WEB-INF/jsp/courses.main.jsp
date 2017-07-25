<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/WEB-INF/tld/spring.tld" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://fonts.googleapis.com/css?family=Raleway" rel="stylesheet">
    <link rel = "stylesheet" type = "text/css" href = "resources/css/style.css" />
    <link rel = "stylesheet" type = "text/css" href = "resources/css/bootstrap.min.css" />
    <link rel = "stylesheet" type = "text/css" href = "resources/css/bootstrap.techie.css" />
</head>
<body>
<h1>Main Courses</h1>
<nav class="navbar navbar-inverse" role="navigation">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex2-collapse">
            <span class="sr-only">Toggle navigation</span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">Brand</a>
    </div>

    <!-- Collect the nav links, forms, and other content for toggling -->
    <div class="collapse navbar-collapse navbar-ex2-collapse">
        <ul class="nav navbar-nav">
            <li class="active"><a href="#">Link</a></li>
            <li><a href="#">Link</a></li>
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b class="caret"></b></a>
                <ul class="dropdown-menu">
                    <li><a href="#">Action</a></li>
                    <li><a href="#">Another action</a></li>
                    <li class="divider"></li>
                    <li class="dropdown-header">Dropdown header</li>
                    <li><a href="#">Separated link</a></li>
                    <li><a href="#">One more separated link</a></li>
                </ul>
            </li>
        </ul>
        <form class="navbar-form navbar-right" role="search">
            <div class="form-group">
                <input type="text" class="form-control" placeholder="Search">
            </div>
        </form>
    </div><!-- /.navbar-collapse -->
</nav>

<ul id="coursesList">
    <c:forEach var="item" items="${courses}">
        <li>${item.id} | ${item.name}</li>
    </c:forEach>
</ul>
<s:message code="Courses"/>

<button id="prevPage"> <<< </button>
<button id="nextPage"> >>> </button>
</body>
</html>
<script>
    var contextPath = "${pageContext.request.contextPath}";
</script>
<script src="resources/js/jquery-3.2.1.js"></script>
<script src='<c:url value="/resources/js/navigation.js"/>'></script>
<script src='<c:url value="/resources/js/bootstrap.min.js"/>'></script>
<script src='<c:url value="/resources/js/typeahead.min.js"/>'></script>

<%--<script src="js/jquery-3.2.1.js"></script>--%>
<%--<script src="js/navigation.js"></script>--%>
<%--<script src="./js/jquery-3.2.1.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script>--%>