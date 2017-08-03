<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="s" uri="/WEB-INF/tld/spring.tld" %>

<jsp:include page="static/header.jsp"/>

    <div class="">
		<div class="col-md-2">
			<p class="lead text-muted"><s:message code="Menu" /></p>
            <ul class="nav nav-tabs nav-stacked">
              	<li><a href="${pageContext.request.contextPath}/user/mycourses"><s:message code="My_Courses" /></a></li>
				<sec:authorize access="hasRole('ROLE_TEACHER')">
					<li><a href="${pageContext.request.contextPath}/teacher/managecourses"><s:message code="Manage_Courses" /></a></li>
				</sec:authorize>
				<li><a href="${pageContext.request.contextPath}/user/profile"><s:message code="Edit_your_account" /></a></li>
            </ul>
		</div>


		<div class="col-md-5">
	        <div class="title_page_wrap">
	            <span class="title_page"><s:message code="Edit_your_account"/>:</span>
	        </div>

			<table>
				<tr>
					<td><s:message code="Firstname" />:</td>
					<td><input type="text" class="userName" value="${userProfile.name}" placeholder="Ivan" /></td>
				</tr>
				<tr>
					<td><s:message code="Lastname" />:</td>
					<td><input type="text" class="userLastname" value="${userProfile.lastname}" placeholder="Ivanov" /></td>
				</tr>
				<tr>
					<td><s:message code="Surname" />:</td>
					<td><input type="text" class="userSurname" value="${userProfile.surname}" placeholder="Ivanovich" /></td>
				</tr>
				<tr>
					<td><s:message code="Login" />:</td>
					<td><input type="text" class="userLogin" value="${userProfile.login}" placeholder="ivan" /></td>
				</tr>
				<tr>
					<td><s:message code="Birthday" />:</td>
					<td><input type="text" class="userBirthday" pattern="(0[1-9]|[12][0-9]|3[01])[-/.](0[1-9]|1[012])[-/.](19|20)\d\d" value="${userProfile.birthday}" placeholder="01/01/1990" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" value="<s:message code="Edit" />" class="editBtnProfile myMediumBtn"></td>
				</tr>
			</table>
			<div class="resultProfile"></div>
		</div>

		<div class="col-md-5">
			<div class="title_page_wrap">
				<span class="title_page"><s:message code="Change_password" />:</span>
			</div>
			<table>
				<tr>
					<td><s:message code="Now_password" />:</td>
					<td><input type="password" class="nowPassword" /></td>
				</tr>
				<tr>
					<td><s:message code="New_password" />:</td>
					<td><input type="password" class="newPassword" /></td>
				</tr>
				<tr>
					<td><s:message code="Repeat_new_password" />:</td>
					<td><input type="password" class="newPassword2" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" value="<s:message code="Edit" />" class="editBtnPassword myMediumBtn"></td>
				</tr>
			</table>
			<div class="resultPassword"></div>

		</div>
    </div>

<jsp:include page="static/footer.jsp"/>
<script src='<c:url value="/resources/js/profile.js"/>'></script>
