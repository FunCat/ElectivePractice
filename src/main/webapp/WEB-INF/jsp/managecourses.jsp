<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/WEB-INF/tld/spring.tld" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:include page="static/header.jsp"/>
<div class="col-sm-4 col-lg-4">
    <h3><s:message code="Manage_Courses"/></h3>
</div>
<div style="clear: both;" ></div>

<div class="col-md-2">
    <jsp:include page="static/left_menu.jsp"/>
</div>
<div  class="col-md-10" >

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
        <c:forEach var="item" items="${courses}">
            <tr>
                <td>
                    <c:if test="${item.status eq 'ACTIVE'}">
                        <div class="active_status dote"></div>
                    </c:if>
                    <c:if test="${item.status eq 'CANCELED'}">
                        <div class="canceled_status dote"></div>
                    </c:if>
                    <c:if test="${item.status eq 'ARCHIVE'}">
                        <div class="archive_status dote"></div>
                    </c:if>
                        ${item.name}
                </td>
                <td>
                    <a href="${pageContext.request.contextPath}/teacher?id=${item.teacher.id}">${item.teacher.firstname} ${item.teacher.lastname}</a>
                </td>
                <td><fmt:formatDate pattern="dd MMM yyyy" value="${item.startDate}" /></td>
                <td><fmt:formatDate pattern="dd MMM yyyy" value="${item.endDate}" /></td>
                <td>
                    <a class="btn btn-primary btn-sm" href="${pageContext.request.contextPath}/courseinfo?id=${item.id}"><s:message code="More" /></a>
                    <%--<a class="btn btn-danger btn-sm"  href="${pageContext.request.contextPath}/teacher/deletecourse?courseid=${item.id}"><s:message code="Delete" /></a>--%>
                </td>
            </tr>

        </c:forEach>
        </tbody>
    </table>
    <div class="text-left">
        <a class="btn btn-primary" href="${pageContext.request.contextPath}/addcourse"><s:message code="Add_Course" /></a>
    </div>
    <div class="text-center">
        <ul class="pagination">
            <li class="" id="prevPage">«</li>
            <c:forEach var="i" begin="1" end="${numOfPages}">
                <li class="page" id="${i}">
                    <c:out value="${i}"/>
                </li>
            </c:forEach>
            <li class="" id="nextPage">»</li>
        </ul>
    </div>
</div>

<jsp:include page="static/footer.jsp"/>
<jsp:include page="static/i18n.jsp"/>
<script src='<c:url value="/resources/js/pagination.js"/>'></script>
<script src='<c:url value="/resources/js/teacherCourses.js"/>'></script>