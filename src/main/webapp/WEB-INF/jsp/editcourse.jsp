<%--
  Created by Crash
  Date: 02.08.2017
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/WEB-INF/tld/spring.tld" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="static/header.jsp"/>
<div class = "container">
    <h1 class="nameCourseInfo"><s:message code="EditCourse" /></h1>
    <table>
        <tr>
            <td class="td_min_width"><s:message code="Course_Name" />:</td>
            <td><input type="text" value="Example course name" /></td>
        </tr>
        <tr>
            <td><s:message code="Start_Date" />:</td>
            <td><input type="text" value="04/08/2017" /></td>
        </tr>
        <tr>
            <td><s:message code="End_Date" />:</td>
            <td><input type="text" value="04/08/2017" /></td>
        </tr>
    </table>

    <div class="description">
        <textarea rows="10">${course.description}</textarea>
    </div>

    <div class='myMediumBtn' style="float: right;">Сохранить</div>
    <div id="resultRequest"></div>

</div>
<jsp:include page="static/footer.jsp"/>
