<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/WEB-INF/tld/spring.tld" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>Electives Courses</title>

    <!-- Bootstrap css -->
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.min.css" />"/>
    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/bootstrap.techie.css" />"/>

    <link rel="stylesheet" type="text/css" href="<c:url value="/resources/css/style.css" />"/>

    <meta http-equiv="content-type" content="text/html; charset=UTF-8">

    <link href="https://fonts.googleapis.com/css?family=Comfortaa" rel="stylesheet">

    <%--<link href='https://fonts.googleapis.com/css?family=Lato:400,300,400italic,700,900' rel='stylesheet'--%>
          <%--type='text/css'>--%>

    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <meta name="description" content="Electives Web practice project">
    <meta name="keywords" content="Electives">
    <meta name="author" content="EPAM Practice Trainees">

</head>

<body>
<div class="container">
    <div class="row">
        <div class="col-sm-8 col-lg-8">
            <h1><a href="${pageContext.request.contextPath}/" style="text-decoration: none;">Electives</a></h1>
        </div>
        <div class="col-sm-4 col-lg-4">
            <sec:authorize access="!isAuthenticated()">
                <a href="<c:url value="/login" />"><div class="loginBtn" style="text-decoration: none;"><s:message code="SignIn" /></div></a>
                <a href="${pageContext.request.contextPath}/registration"><div class="loginBtn" style="text-decoration: none;"><s:message code="Registration" /></div></a>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <a href="<c:url value="/logout" />"><div class="loginBtn" style="text-decoration: none;"><s:message code="SignOut" /></div></a>
                <a href="${pageContext.request.contextPath}/profile"><div class="loginBtn" style="text-decoration: none;"><s:message code="Profile" /></div></a>
            </sec:authorize>
            <sec:authorize access="hasRole('ROLE_ADMIN')">
                <a href="${pageContext.request.contextPath}/adminpanel"><div class="loginBtn" style="text-decoration: none;"><s:message code="Admin_Panel" /></div></a>
            </sec:authorize>
        </div>

        <div class="col-sm-12 col-lg-12">
            <div class="text-right">
                <a href="" id="en_locale">English</a> | <a id="ru_locale" >Russian</a>
            </div>
            <div class="text-right">
                <s:message code="Cur_Language"/> : ${pageContext.response.locale}
            </div>
        </div>
    </div>