<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<jsp:include page="static/header.jsp"/>

<div class="container loginBlock" style="width: 300px;">
    <div class="title_page_wrap">
        <span class="title_page">Login</span>
    </div>
    <form:form action="${pageContext.request.contextPath}/login" method="POST">
        <table>
            <tr>
                <td>Login:</td>
                <td><input class="userLogin" type="text" name="username"/></td>
            </tr>
            <tr>
                <td>Password:</td>
                <td><input class="userPassword" type="password" name="password"/></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center"><input class="myMediumBtn" type="submit" value="Login"/></td>
            </tr>
        </table>
    </form:form>
    <div class="result">
        <c:if test="${not empty error}">
            <div>${error}</div>
        </c:if>
    </div>
</div>

<jsp:include page="static/footer.jsp"/>