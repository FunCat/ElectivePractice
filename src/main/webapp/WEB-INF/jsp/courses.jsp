<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/WEB-INF/tld/spring.tld" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
        <th><s:message code="Course_Name"/></th>
        <th><s:message code="Course_Teacher"/></th>
        <th><s:message code="Start_Date"/></th>
        <th><s:message code="End_Date"/></th>
        <th></th>
    </tr>
    </thead>
    <tbody id="coursesList">
    <c:forEach var="item" items="${courses}">
        <tr>
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

<jsp:include page="static/i18n.jsp"/>
<jsp:include page="static/footer.jsp"/>
<script src='<c:url value="/resources/js/pagination.js"/>'></script>
<script src='<c:url value="/resources/js/jquery-ui.min.js"/>'></script>
<script>
    function getCoursesPage() {
        getCoursesPageDefaultPagination("/coursestag?term=" + $("#tags").val());
    }

    $(document).ready(function () {
        $("#tags").change(function () {
            a.start = 0;
            getCoursesPageDefaultPagination("/coursestag?term=" + $("#tags").val());
        });
    })
</script>
