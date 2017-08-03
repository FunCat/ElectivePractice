<%--
	Created by IntelliJ IDEA.
	User: Sergey Petrov
	Date: 21.07.2017
	Time: 19:49
	To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<jsp:include page="static/header.jsp"/>

    <div class="">
		<div class="col-md-2">
			<p class="lead text-muted">Меню</p>
            <ul class="nav nav-tabs nav-stacked">
              	<li><a href="${pageContext.request.contextPath}/user/mycourses">Мои курсы</a></li>
				<sec:authorize access="hasRole('ROLE_TEACHER')">
					<li><a href="${pageContext.request.contextPath}/teacher/managecourses">Управление курсами</a></li>
				</sec:authorize>
				<li><a href="${pageContext.request.contextPath}/user/profile">Редактирование аккаунта</a></li>
            </ul>
		</div>


		<div class="col-md-5">
	        <div class="title_page_wrap">
	            <span class="title_page">Edit your account:</span>
	        </div>

			<table>
				<tr>
					<td>Name:</td>
					<td><input type="text" class="userName" value="${userProfile.firstname}" /></td>
				</tr>
				<tr>
					<td>Lastname:</td>
					<td><input type="text" class="userLastname" value="${userProfile.lastname}" /></td>
				</tr>
				<tr>
					<td>Middlename:</td>
					<td><input type="text" class="userSurname" value="${userProfile.surname}" /></td>
				</tr>
				<tr>
					<td>Login:</td>
					<td><input type="text" class="userLogin" value="${userProfile.login}" /></td>
				</tr>
				<tr>
					<td>Birthday:</td>
					<td><input type="text" class="userBirthday" value="${userProfile.birthday}" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" value="Edit" class="editBtnProfile myMediumBtn"></td>
				</tr>
			</table>
			<div class="resultProfile"></div>
		</div>

		<div class="col-md-5">
			<div class="title_page_wrap">
				<span class="title_page">Change password:</span>
			</div>
			<table>
				<tr>
					<td>Now password:</td>
					<td><input type="password" class="nowPassword" /></td>
				</tr>
				<tr>
					<td>New password:</td>
					<td><input type="password" class="newPassword" /></td>
				</tr>
				<tr>
					<td>Repeat new password:</td>
					<td><input type="password" class="newPassword2" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" value="Edit" class="editBtnPassword myMediumBtn"></td>
				</tr>
			</table>
			<div class="resultPassword"></div>

		</div>
    </div>

<jsp:include page="static/footer.jsp"/>
<script src='<c:url value="/resources/js/profile.js"/>'></script>
