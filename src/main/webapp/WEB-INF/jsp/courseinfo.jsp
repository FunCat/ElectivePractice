<%--
  Created by Crash
  Date: 02.08.2017
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="static/header.jsp"/>

<h1>${course.name}</h1>
<h2>${course.teacher.name}</h2>
<h3>${course.teacher.lastname}</h3>
<h4>${course.startDate}</h4>
<h4>${course.startDate.year} ${course.startDate.month} ${course.startDate.day} </h4>
<p>${course.startDate}</p>
<jsp:include page="static/footer.jsp"/>
