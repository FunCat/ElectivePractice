<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="static/header.jsp"/>

<div class="container loginBlock" style="width: 300px;">
    <div class="title_page_wrap">
        <span class="title_page">Registration</span>
    </div>
    <table>
        <tr>
            <td>Login:</td>
            <td><input class="userLogin" type="text" name="login"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input class="userPassword" type="password" name="password"/></td>
        </tr>
        <tr>
            <td>Password:</td>
            <td><input class="userPassword2" type="password" name="password2"/></td>
        </tr>
        <tr>
            <td>Firstname:</td>
            <td><input class="userName" type="text" name="firstname"/></td>
        </tr>
        <tr>
            <td>Lastname:</td>
            <td><input class="userLastname" type="text" name="lastname"/></td>
        </tr>
        <tr>
            <td>Surname:</td>
            <td><input class="userSurname" type="text" name="surname"/></td>
        </tr>
        <tr>
            <td>Birthday:</td>
            <td><input class="userBirthday" type="text" name="birthday"/></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center"><input id="registrationBtn" class="myMediumBtn" type="button" value="Registration"/></td>
        </tr>
    </table>
    <div class="result"></div>
</div>

<jsp:include page="static/footer.jsp" />
<script src='<c:url value="/resources/js/registration.js"/>'></script>