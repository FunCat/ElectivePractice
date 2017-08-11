<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/WEB-INF/tld/spring.tld" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="static/header.jsp"/>
<div class="col-sm-4 col-lg-4">
    <h3><s:message code="Users" /></h3>
</div>

<table class="table table-striped" data-effect="fade">
    <thead>
    <tr>
        <th class="columSort" data-columsorting="1"><s:message code="User_Name"/></th>
        <th class="columSort" data-columsorting="2"><s:message code="User_Lastname"/></th>
        <th class="columSort columDesc" data-columsorting="3"><s:message code="User_Login"/></th>
        <th></th>
    </tr>
    </thead>
    <tbody id="usersList">
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.firstname}</td>
            <td>${user.lastname}</td>
            <td>${user.login}</td>
            <td>
                <a class='btn btn-primary btn-sm' href="${pageContext.request.contextPath}/activate?id=${user.id}"><s:message code="Add_teacher_role" /></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<jsp:include page="static/footer.jsp"/>