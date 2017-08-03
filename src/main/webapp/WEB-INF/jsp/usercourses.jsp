<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="/WEB-INF/tld/spring.tld" %>

<jsp:include page="static/header.jsp"/>

<div class="">
    <div class="col-md-2">
        <p class="lead text-muted"><s:message code="Menu" /></p>
        <ul class="nav nav-tabs nav-stacked">
            <li><a href="${pageContext.request.contextPath}/usercourses"><s:message code="My_Courses" /></a></li>
            <sec:authorize access="hasRole('ROLE_TEACHER')">
                <li><a href="${pageContext.request.contextPath}/teacher/managecourses"><s:message code="Manage_Courses" /></a></li>
            </sec:authorize>
            <li><a href="${pageContext.request.contextPath}/profile"><s:message code="Edit_your_account" /></a></li>
        </ul>
    </div>
    <div class="col-md-10">
        <table class="table table-striped" data-effect="fade">
            <thead>
            <tr>
                <th><s:message code="Course_Name"/></th>
                <th><s:message code="Course_Teacher" /></th>
                <th><s:message code="Start_Date"/></th>
                <th><s:message code="End_Date"/></th>
                <th> </th>
            </tr>
            </thead>
            <tbody id="coursesList">
            </tbody>
        </table>
    </div>
    <div class="text-center">
        <ul class="pagination">
            <li class="">
                <a href="#" id="prevPage">«</a>
            </li>
            <c:forEach var="i" begin="1" end="${numOfPages}">
                <li class="page" id="${i}" >
                    <a href="#" ><c:out value="${i}"/></a>
                </li>
            </c:forEach>
            <li class="">
                <a href="#" id="nextPage">»</a>
            </li>
        </ul>
    </div>
</div>

<jsp:include page="static/footer.jsp"/>
<script src='<c:url value="/resources/js/profile.js"/>'></script>
<script src='<c:url value="/resources/js/usercourses.js"/>'></script>