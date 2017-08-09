<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="/WEB-INF/tld/spring.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<jsp:include page="static/header.jsp"/>

<div class="">
    <div class="col-md-2">
        <jsp:include page="static/left_menu.jsp"/>
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
                <c:forEach var="item" items="${courses}">
                    <tr>
                        <td>${item.name}</td>
                        <td><a href="${pageContext.request.contextPath}/teacher?id=${item.teacher.id}">${item.teacher.firstname} ${item.teacher.lastname}</a></td>
                        <td><fmt:formatDate pattern="dd MMM yyyy" value="${item.startDate}" /></td>
                        <td><fmt:formatDate pattern="dd MMM yyyy" value="${item.endDate}" /></td>
                        <td>
                            <a class='btn btn-primary btn-sm' href="${pageContext.request.contextPath}/courseinfo?id=${item.id}"><s:message code="More" /></a>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>
    <div class="text-center">
        <ul class="pagination">
            <li class="" id="prevPage">«</li>
            <c:forEach var="i" begin="1" end="${numOfPages}">
                <li class="page" id="${i}" >
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
<script>
    function getCoursesPage() {
        getCoursesPageDefault("/partuser");
    }
</script>
