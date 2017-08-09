<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="s" uri="/WEB-INF/tld/spring.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>

<jsp:include page="static/header.jsp"/>
<div class="container">
    <div class="headInfo">
        <div class="leftBlockInfo">
            <h1 class="nameCourseInfo">${course.name}</h1>
            <div class="teacherName"><h3
                    class="teacherCourseInfo">${course.teacher.firstname} ${course.teacher.lastname}</h3></div>
        </div>
        <div class="rightBlockInfo">
            <div class="calendar">
                <div class="pageCalendar">
                    ${course.dateToString(course.startDate)}
                </div>
                <div class="separator">-</div>
                <div class="pageCalendar">
                    ${course.dateToString(course.endDate)}
                </div>
            </div>
        </div>
    </div>

    <div class="description">${course.description}</div>

    <sec:authorize access="!isAuthenticated()">
        <div><a class="myMediumBtn" href="${pageContext.request.contextPath}/login"><s:message code="Subscribe" /></a></div>
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_USER')">
        <div id="subdiv">
            <c:choose>
                <c:when test="${userAlreadyRegistredForCourse == 'true'}">
                    <div id="unsubscribe" class='myMediumBtn' onclick="unsubscribe()">
                        <s:message code="Unsubscribe"/>
                    </div>
                </c:when>
                <c:otherwise>
                    <div id="subscribe" class='myMediumBtn' onclick="subscribe()">
                        <s:message code="Subscribe" />
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </sec:authorize>

    <sec:authorize access="hasRole('ROLE_TEACHER')">
        <div>
            <a class='myMediumBtn' href="${pageContext.request.contextPath}/editcourse?courseid=${course.id}">
                <s:message code="Edit"/>
            </a>
        </div>
    </sec:authorize>

    <div id="subscribeResult"></div>
    <sec:authorize access="hasRole('ROLE_TEACHER')">

        <div class="col-sm-4 col-lg-4">
            <h3><s:message code="Students"/></h3>
        </div>

        <table class="table table-striped" data-effect="fade">
            <thead>
            <tr>
                <th><s:message code="Firstname"/></th>
                <th><s:message code="Lastname"/></th>
                <th><s:message code="Rating"/></th>
                <th><s:message code="Review"/></th>
                <th width="20%"></th>
            </tr>
            </thead>
            <tbody id="studentList">

            <c:forEach var="item" items="${group}" varStatus="loop">
                <tr>
                    <td>${item.groupId.student.firstname}</td>
                    <td>${item.groupId.student.lastname}</td>
                    <td><input id="grade_${loop.index}" disabled value="${item.grade}"/></td>
                    <td><input id="review_${loop.index}" disabled value="${item.review}"/></td>
                    <td>
                        <a href="#" class="btn btn-warning btn-sm edit" attr_id="${loop.index}">
                            <s:message code="Edit"/>
                        </a>
                        <a href="#" class="btn btn-danger btn-sm hidden save" student_id="${item.groupId.student.id}"
                           attr_id="${loop.index}">
                            <s:message code="Save"/>
                        </a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        <div class="result"></div>
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

    </sec:authorize>
</div>
<script> var id = "${course.id}";</script>

<jsp:include page="static/footer.jsp"/>
<jsp:include page="static/i18n.jsp"/>
<script src='<c:url value="/resources/js/courseStudents.js"/>'></script>
<script src='<c:url value="/resources/js/pagination.js"/>'></script>
<script src='<c:url value="/resources/js/subscribe.js"/>'></script>