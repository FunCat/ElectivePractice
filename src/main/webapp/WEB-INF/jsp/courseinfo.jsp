<%--
  Created by Crash
  Date: 02.08.2017
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
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

</div>
<jsp:include page="static/footer.jsp"/>
