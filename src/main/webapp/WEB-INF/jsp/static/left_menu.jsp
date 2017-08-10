<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="s" uri="/WEB-INF/tld/spring.tld" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<p class="lead text-muted"><s:message code="Menu"/></p>
<ul class="nav nav-tabs nav-stacked">
    <li class="${requestScope['javax.servlet.forward.request_uri']=='/usercourses' ? 'active' : ''}" ><a href="${pageContext.request.contextPath}/usercourses"><s:message code="My_Courses"/></a></li>
    <sec:authorize access="hasRole('ROLE_TEACHER')">
    <li class="${requestScope['javax.servlet.forward.request_uri']=='/teacher/managecourses' ? 'active' : ''}"><a href="${pageContext.request.contextPath}/teacher/managecourses"><s:message code="Manage_Courses"/></a>
    </li>
    </sec:authorize>
    <li class="${requestScope['javax.servlet.forward.request_uri']=='/profile' ? 'active' : ''}"><a href="${pageContext.request.contextPath}/profile"><s:message code="Edit_your_account"/></a></li>
</ul>