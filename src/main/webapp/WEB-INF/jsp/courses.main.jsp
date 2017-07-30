<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/WEB-INF/tld/spring.tld" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Electives Courses</title>
    <link rel="stylesheet" type="text/css" href="resources/css/style.css"/>
    <!-- Bootstrap css -->
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.min.css"/>
    <link rel="stylesheet" type="text/css" href="resources/css/bootstrap.techie.css"/>

    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <link href='https://fonts.googleapis.com/css?family=Lato:400,300,400italic,700,900' rel='stylesheet'
          type='text/css'>

    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="description" content="Electives Web practice project">
    <meta name="keywords" content="Electives">
    <meta name="author" content="EPAM Practice Trainees">

</head>

<body>

<div class="container">
    <div class="row">
        <div class="col-sm-12 col-lg-12">
            <h1>Electives</h1>
            <nav class="navbar navbar-inverse" role="navigation">
                <!-- Brand and toggle get grouped for better mobile display -->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse"
                            data-target=".navbar-ex2-collapse">
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
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Dropdown <b
                                    class="caret"></b></a>
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

            <h3>
                <s:message code="Courses"/>
            </h3>

            <%--<ul id="coursesList">--%>
                <%--<c:forEach var="item" items="${courses}">--%>
                    <%--<li>${item.id} | ${item.name}</li>--%>
                <%--</c:forEach>--%>
            <%--</ul>--%>

            <sec:authorize access="!isAuthenticated()">
                <p><a class="btn btn-lg btn-success" href="<c:url value="/login" />" role="button">Войти</a></p>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <p>Ваш логин: <sec:authentication property="principal.username" /></p>
                <p><a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a></p>

            </sec:authorize>

            <table class="table table-striped" data-effect="fade">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Course Name</th>
                    <th>Start Date</th>
                    <th>Teacher</th>
                </tr>
                </thead>
                <tbody id="coursesList">
                <c:forEach var="item" items="${courses}">
                    <tr>
                        <td>${item.id}</td>
                        <td>${item.name}</td>
                        <td>${item.startDate}</td>
                        <td>${item.teacher.lastname}</td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>




            <div class="text-center">
               <ul class="pagination">
                    <li class="">
                        <a href="#" id="prevPage">«</a>
                    </li>
                    <c:forEach var="i" begin="1" end="${numOfPages}">
                        <li class="page" id="${i}" ><a href="#" ><c:out value="${i}"/></a></li>
                    </c:forEach>
                    <li class="">
                        <a href="#" id="nextPage">»</a>
                    </li>
                </ul>

            </div>

        </div> <!-- /container -->

        <footer class="text-center">
            <p>Made by Trainees from EPAM</p>
            <div class="credits">
                <!--
                    All the links in the footer should remain intact.
                    You can delete the links only if you purchased the pro version.
                    Licensing information: https://bootstrapmade.com/license/
                    Purchase the pro version form: https://bootstrapmade.com/buy/?theme=Techie
                -->
                <a href="https://bootstrapmade.com/">Bootstrap Themes</a> by <a href="https://bootstrapmade.com/">BootstrapMade</a>
            </div>
        </footer>
    </div>
</div>
</body>
</html>
<script>
    var contextPath = "${pageContext.request.contextPath}";
    var numOfPages = "${numOfPages}";
</script>
<script src="resources/js/jquery-3.2.1.js"></script>
<script src='<c:url value="/resources/js/navigation.js"/>'></script>
<script src='<c:url value="/resources/js/bootstrap.min.js"/>'></script>
<script src='<c:url value="/resources/js/typeahead.min.js"/>'></script>

<%--<script src="js/jquery-3.2.1.js"></script>--%>
<%--<script src="js/navigation.js"></script>--%>
<%--<script src="./js/jquery-3.2.1.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script>--%>