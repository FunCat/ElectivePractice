<%@ page import="com.epam.electives.model.Course" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/WEB-INF/tld/spring.tld" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="ACTIVE_COURSE">
    <%= Course.Status.ACTIVE.name() %>
</c:set>
<jsp:include page="static/header.jsp"/>
<div class="col-sm-4 col-lg-4">
    <h3><s:message code="Courses"/></h3>
</div>

<div class="col-sm-8 col-lg-8">
    <div class="wrap_search_box">
        <input id="tags" placeholder="<s:message code="SearchByNameCourse" />...">
    </div>
</div>

<table class="table table-striped" data-effect="fade">
    <thead>
    <tr>
        <th></th>
        <th class="columSort" data-columsorting="1"><s:message code="Course_Name"/></th>
        <th class="columSort" data-columsorting="2"><s:message code="Course_Teacher"/></th>
        <th class="columSort columDesc" data-columsorting="3"><s:message code="Start_Date"/></th>
        <th class="columSort" data-columsorting="4"><s:message code="End_Date"/></th>
        <th></th>
    </tr>
    </thead>
    <tbody id="coursesList">
    <c:forEach var="item" items="${courses}">
        <tr>
            <td>
                <c:choose>
                    <c:when test="${item.status eq 'ACTIVE'}" >
                        <div class="active_status"></div>
                    </c:when>
                    <c:otherwise>
                        <div class="arhive_status"></div>
                    </c:otherwise>
                </c:choose>
            </td>
            <td>${item.name}</td>
            <td>
                <a href="${pageContext.request.contextPath}/teacher?id=${item.teacher.id}">${item.teacher.firstname} ${item.teacher.lastname}</a>
            </td>
            <td><fmt:formatDate pattern="dd MMM yyyy" value="${item.startDate}"/></td>
            <td><fmt:formatDate pattern="dd MMM yyyy" value="${item.endDate}"/></td>
            <td>
                <a class='btn btn-primary btn-sm' href="${pageContext.request.contextPath}/courseinfo?id=${item.id}"><s:message code="More" /></a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div class="text-center">
    <ul class="pagination">
        <li id='prevPage' onclick='prevPage()'>«</li>
        <c:forEach var="i" begin="1" end="${numOfPages}">
            <li class='page' id="${i}" onclick='numPage(this)'>${i}</li>
        </c:forEach>
        <li id='nextPage' onclick='nextPage()'>»</li>
    </ul>

</div>

<jsp:include page="static/footer.jsp"/>
<jsp:include page="static/i18n.jsp"/>
<script src='<c:url value="/resources/js/pagination.js"/>'></script>
<script src='<c:url value="/resources/js/sortingCourses.js"/>'></script>
<script>
    var columSorting = 3;
    var desc = false;

    function getCoursesPage() {
        getCoursesPageDefaultPagination("/coursestag?columSorting=" + columSorting + "&desc=" + desc + "&term=" + $("#tags").val());
    }

    $(document).ready(function () {
        $("#tags").change(function () {
            a.start = 0;
            getCoursesPageDefaultPagination("/coursestag?columSorting=" + columSorting + "&desc=" + desc + "&term=" + $("#tags").val());
        });
    })
</script>
