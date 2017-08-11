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
            <c:if test="${course.status eq 'ACTIVE'}" >
                <div class="active_status dote"></div>
                <span class=""><s:message code="Status"/>: <s:message code="Active"/></span>
            </c:if>
            <c:if test="${course.status eq 'CANCELED'}">
                <div class="canceled_status dote"></div>
                <span class=""><s:message code="Status"/>: <s:message code="Canceled"/></span>
            </c:if>
            <c:if test="${course.status eq 'ARCHIVE'}">
                <div class="archive_status dote"></div>
                <span class=""><s:message code="Status"/>: <s:message code="Archived"/></span>
            </c:if>
            <div class="teacherName">
                <s:message code="Course_Teacher"/>: <b> ${course.teacher.firstname} ${course.teacher.lastname}</b>
            </div>
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
            <c:if test="${isUserCreatorOfTheCourse == 'false' && course.status eq 'ACTIVE'}">
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
            </c:if>
        </div>
    </sec:authorize>


    <c:if test="${userAlreadyRegistredForCourse == 'true'}">
    <table class="table table-striped" data-effect="fade">
        <tr>
    <td>${userGroup.groupId.student.firstname}</td>
    <td>${userGroup.groupId.student.lastname}</td>
    <td>${userGroup.grade}</td>
    <td>${userGroup.review}</td>
        </tr>
    </table>
    </c:if>


    <c:choose>
    <c:when test="${isUserCreatorOfTheCourse == 'true'}">
    <div class="editButtons">
        <c:if test="${course.status eq 'ACTIVE'}">
        <a class='btn btn-primary btn-large' href="${pageContext.request.contextPath}/editcourse?courseid=${course.id}">
            <s:message code="Edit"/>
        </a>
            <a class='btn btn-primary btn-large'
               href="${pageContext.request.contextPath}/teacher/сompletecourse?courseid=${course.id}">
                <s:message code="CompleteCourse"/>
            </a>
            <a class='btn btn-danger btn-large'
               href="${pageContext.request.contextPath}/teacher/deletecourse?courseid=${course.id}">
                <s:message code="CancelCourse"/>
            </a>
        </c:if>
    </div>
    <div style="clear: both; "></div>
    <div id="subscribeResult"></div>

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
                        <c:if test="${course.status eq 'ARCHIVE'}">
                        <a href="#" class="btn btn-warning btn-sm edit" attr_id="${loop.index}">
                            <s:message code="Edit"/>
                        </a>
                        <a href="#" class="btn btn-danger btn-sm hidden save" student_id="${item.groupId.student.id}"
                           attr_id="${loop.index}">
                            <s:message code="Save"/>
                        </a>
                        </c:if>
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

    </c:when>
    </c:choose>
</div>
<script> var id = "${course.id}";</script>

<jsp:include page="static/footer.jsp"/>
<jsp:include page="static/i18n.jsp"/>
<script src='<c:url value="/resources/js/courseStudents.js"/>'></script>
<script src='<c:url value="/resources/js/pagination.js"/>'></script>
<script src='<c:url value="/resources/js/subscribe.js"/>'></script>