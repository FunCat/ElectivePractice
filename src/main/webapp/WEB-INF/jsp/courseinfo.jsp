<%--
  Created by Crash
  Date: 02.08.2017
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="s" uri="/WEB-INF/tld/spring.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
        <div><a class="myMediumBtn" href= "${pageContext.request.contextPath}/login"  role="button">Записаться</a></div>
    </sec:authorize>

    <sec:authorize access="isAuthenticated() && hasRole('ROLE_USER')">
    <div id = "subdiv">
        <c:choose>
            <c:when test="${userAlreadyRegistredForCourse == 'true'}">
                <div id = "unsubscribe" class='myMediumBtn' onclick="unsubscribe()">Отписаться</div>
            </c:when>
            <c:otherwise>
                <div id = "subscribe" class='myMediumBtn' onclick="subscribe()">Записаться</div>
            </c:otherwise>
        </c:choose>
    </div>
    </sec:authorize>
    <div id = "subscribeResult"></div>

</div>
<jsp:include page="static/footer.jsp"/>

<script> var id = "${course.id}";</script>
<script src='<c:url value="/resources/js/subscribe.js"/>'></script>