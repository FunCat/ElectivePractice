<%--
  Created by Crash
  Date: 02.08.2017
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/WEB-INF/tld/spring.tld" %>

<jsp:include page="static/header.jsp"/>
<div class = "container">
    <div class="headInfo">
        <div class="leftBlockInfo">
            <h1 class="nameCourseInfo">${course.name}</h1>
            <div class="teacherName"><h3 class="teacherCourseInfo">${course.teacher.firstname} ${course.teacher.lastname}</h3></div>
        </div>
        <div class="rightBlockInfo">
            <div class="calendar">
                <div class="pageCalendar">
                    03 Августа
                </div>
                <div class="separator">-</div>
                <div class="pageCalendar">
                    03 Августа
                </div>
                <%--<h4>${course.dateToString(course.startDate)} ${course.dateToString(course.endDate)} </h4>--%>
            </div>

        </div>
    </div>



    <div class="description">${course.description}</div>

    <sec:authorize access="!isAuthenticated()">
        <div><a class="myMediumBtn" href= "${pageContext.request.contextPath}/login"  role="button">Записаться</a></div>
    </sec:authorize>

    <sec:authorize access="isAuthenticated() && hasRole('ROLE_USER')">
        <c:choose>
            <c:when test="${userAlreadyRegistredForCourse == 'true'}">
                <div><a class='myMediumBtn' href= "http://i.playground.ru/i/89/48/62/10/pix/image.jpg"  role="button">Отписаться</a></div>
                <br />
            </c:when>
            <c:otherwise>
                <div><a class='myMediumBtn' href= "http://i.playground.ru/i/89/48/62/10/pix/image.jpg"  role="button">Записаться</a></div>
                <br />
            </c:otherwise>
        </c:choose>
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_TEACHER')">

        <div class="col-sm-4 col-lg-4">
            <h3><s:message code="Students"/></h3>
        </div>

        <table class="table table-striped" data-effect="fade">
            <thead>
            <tr>
                <th><s:message code="Firstname"/></th>
                <th><s:message code="Lastname" /></th>
                <th><s:message code="Rating"/></th>
                <th><s:message code="Review"/></th>
                <%--<th><s:message code="End_Date"/></th>--%>
                <th> </th>
            </tr>
            </thead>
            <tbody id="coursesList">

            <c:forEach var="item" items="${group}">
                <tr>
                    <td>${item.groupId.student.firstname}</td>
                    <td>${item.groupId.student.lastname}</td>
                    <td><input type="text" disabled value="${item.grade}" /></td>
                    <td><input type="text" disabled value="${item.review}" /></td>
                    <td>
                        <a class="btn btn-danger btn-sm" href="/ElectiveEPAM/courseinfo?id=1" role="button"><s:message code="Edit" /></a>
                        <a class="btn btn-danger btn-sm hidden" href="/ElectiveEPAM/courseinfo?id=1" role="button"><s:message code="Save" /></a>
                    </td>
                    <%--<td><fmt:formatDate pattern="dd MMM yy" value="${item.startDate}" /></td>--%>
                    <%--<td><fmt:formatDate pattern="dd MMM yy" value="${item.endDate}" /></td>--%>
                    <%--<td>--%>
                        <%--<a class='myMediumBtn' href="${pageContext.request.contextPath}/courseinfo?id=${item.id}" role='button'>Подробнее</a>--%>
                    <%--</td>--%>

                </tr>
            </c:forEach>
            </tbody>
        </table>

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

    </sec:authorize>
</div>
<jsp:include page="static/footer.jsp"/>
