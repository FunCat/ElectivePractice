<%--
	Created by IntelliJ IDEA.
	User: Sergey Petrov
	Date: 21.07.2017
	Time: 19:49
	To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
	<jsp:include page="../static/header.jsp"/>

    <div class="container">
		<div class="col-md-2">
			<p class="lead text-muted">Меню</p>
            <ul class="nav nav-tabs nav-stacked">
              <li><a href="#">Мои курсы</a></li>
              <li><a href="#">Управление курсами</a></li>
              <li><a href="${pageContext.request.contextPath}/user/profile">Редактирование аккаунта</a></li>
            </ul>
		</div>


		<div class="col-md-10">
	        <div class="title_page_wrap">
	            <span class="title_page">Edit your account:</span>
	        </div>

			<table>
				<tr>
					<td>Name:</td>
					<td><input type="text" class="userFirstname" value="${userFirstname}" /></td>
				</tr>
				<tr>
					<td>Lastname:</td>
					<td><input type="text" class="userLastname" value="${userLastname}" /></td>
				</tr>
				<tr>
					<td>Middlename:</td>
					<td><input type="text" class="userMiddlename" value="${userMiddlename}" /></td>
				</tr>
				<tr>
					<td>Login:</td>
					<td><input type="text" class="userLogin" value="${userLogin}" /></td>
				</tr>
				<tr>
					<td>Birthday:</td>
					<td><input type="text" class="userBirthday" value="${userBirthday}" /></td>
				</tr>
				<tr>
					<td colspan="2"><input type="button" value="Edit" class="editBtn myMediumBtn"></td>
				</tr>
			</table>
			<div class="result"></div>
		</div>
    </div>

<jsp:include page="../static/footer.jsp"/>
<script src='<c:url value="/resources/js/cabinet.js"/>'></script>
