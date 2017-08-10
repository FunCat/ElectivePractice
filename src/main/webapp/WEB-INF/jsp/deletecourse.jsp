<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/WEB-INF/tld/spring.tld" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:include page="static/header.jsp"/>
<div class="col-sm-4 col-lg-4">
    <h3><s:message code="Delete_Course"/></h3>
</div>
<div style="clear: both;" ></div>

<div class="col-md-2">
    <p class="lead text-muted"><s:message code="Menu" /></p>
    <ul class="nav nav-tabs nav-stacked">
        <li><a href="${pageContext.request.contextPath}/user/mycourses"><s:message code="My_Courses" /></a></li>
        <sec:authorize access="hasRole('ROLE_TEACHER')">
            <li><a href="${pageContext.request.contextPath}/teacher/managecourses"><s:message code="Manage_Courses" /></a></li>
        </sec:authorize>
        <li><a href="${pageContext.request.contextPath}/user/profile"><s:message code="Edit_your_account" /></a></li>
    </ul>

    <a class="btn btn-primary" href="#"><s:message code="Add_Course" /></a>
</div>
<div  class="col-md-10 text-center" >
    <sec:authorize access="hasRole('ROLE_TEACHER')">
        Do you really want to delete course?
        <a class="btn btn-danger btn-large" href="${pageContext.request.contextPath}/teacher/cancelcourse?courseid=${course.id}">
            <s:message code="Yes"/>
        </a>
        <a class="btn btn-primary btn-large" href="${pageContext.request.contextPath}/courseinfo?id=${course.id}">
            <s:message code="No"/>
        </a>
    </sec:authorize>
</div>


<jsp:include page="static/footer.jsp"/>
<jsp:include page="static/i18n.jsp"/>
<script src='<c:url value="/resources/js/teacherCourses.js"/>'></script>
<script src='<c:url value="/resources/js/pagination.js"/>'></script>