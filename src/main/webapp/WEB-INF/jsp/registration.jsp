<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/WEB-INF/tld/spring.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="static/header.jsp"/>

<div class="container loginBlock" style="width: 460px;">
    <div class="title_page_wrap">
        <span class="title_page"><s:message code="Registration" /></span>
    </div>
    <table>
        <tr>
            <td class="td_min_width"><s:message code="Login" />:<span class="requiredFiled">*</span></td>
            <td><input class="userLogin" type="text" name="login" placeholder="ivan"  /></td>
        </tr>
        <tr>
            <td><s:message code="Password" />:<span class="requiredFiled">*</span></td>
            <td><input class="userPassword" type="password" name="password"/></td>
        </tr>
        <tr>
            <td><s:message code="Repeat_password" />:<span class="requiredFiled">*</span></td>
            <td><input class="userPassword2" type="password" name="password2"/></td>
        </tr>
        <tr>
            <td><s:message code="Firstname" />:<span class="requiredFiled">*</span></td>
            <td><input class="userName" type="text" name="firstname" placeholder="Ivan" /></td>
        </tr>
        <tr>
            <td><s:message code="Lastname" />:<span class="requiredFiled">*</span></td>
            <td><input class="userLastname" type="text" name="lastname" placeholder="Ivanov"/></td>
        </tr>
        <tr>
            <td><s:message code="Surname" />:</td>
            <td><input class="userSurname" type="text" name="surname"  placeholder="Ivanovich"/></td>
        </tr>
        <tr>
            <td><s:message code="Birthday" />:<span class="requiredFiled">*</span></td>
            <td><input class="userBirthday" type="text" name="birthday" placeholder="01/01/1990"/></td>
        </tr>
        <tr>
            <td colspan="2" style="text-align: center">
                <div><span class="requiredFiled">*</span> - <s:message code="Required_fields" />.</div>
                <input id="registrationBtn" class="myMediumBtn" type="button" value="<s:message code="Registration" />"/><br/>
            </td>
        </tr>
    </table>
    <div class="result"></div>
</div>

<jsp:include page="static/footer.jsp" />
<script src='<c:url value="/resources/js/registration.js"/>'></script>