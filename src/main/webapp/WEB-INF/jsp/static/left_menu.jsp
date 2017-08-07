<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="s" uri="/WEB-INF/tld/spring.tld" %>

<p class="lead text-muted"><s:message code="Menu" /></p>
<ul class="nav nav-tabs nav-stacked">
    <li><a href="${pageContext.request.contextPath}/usercourses"><s:message code="My_Courses" /></a></li>
    <sec:authorize access="hasRole('ROLE_TEACHER')">
            <li><a href="${pageContext.request.contextPath}/teacher/managecourses"><s:message code="Manage_Courses" /></a></li>
        </sec:authorize>
    <li><a href="${pageContext.request.contextPath}/profile"><s:message code="Edit_your_account" /></a></li>
</ul>