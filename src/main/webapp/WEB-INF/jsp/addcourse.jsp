<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/WEB-INF/tld/spring.tld" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<jsp:include page="static/header.jsp"/>
<div class = "container">
    <h1 class="nameCourseInfo"><s:message code="Add_Course" /></h1>
    <table>
        <tr>
            <td class="td_min_width"><s:message code="Course_Name" />:</td>
            <td><input class="nameCourse" type="text"  placeholder="Course name"/></td>
        </tr>
        <tr>
            <td><s:message code="Start_Date" />:</td>
            <td><input class="startDateCourse" type="text" placeholder="01/01/2017" /></td>
        </tr>
        <tr>
            <td><s:message code="End_Date" />:</td>
            <td><input class="endDateCourse" type="text" placeholder="01/01/2017" /></td>
        </tr>
    </table>

    <div class="description">
        <textarea class="descriptionCourse" rows="10" placeholder="Describe your course..." ></textarea>
    </div>

    <div id="addCourse" class='myMediumBtn' style="float: right;"><s:message code="Add_Course" /></div>
    <div id="resultRequest"></div>

</div>

<jsp:include page="static/i18n.jsp"/>
<jsp:include page="static/footer.jsp"/>
<script src='<c:url value="/resources/js/editcourse.js"/>'></script>