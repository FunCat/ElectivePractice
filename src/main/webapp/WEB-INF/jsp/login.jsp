<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="s" uri="/WEB-INF/tld/spring.tld" %>

<jsp:include page="static/header.jsp"/>

<div class="container loginBlock" style="width: 350px;">
    <div class="title_page_wrap">
        <span class="title_page"><s:message code="SignIn" /></span>
    </div>
    <form:form action="${pageContext.request.contextPath}/login" method="POST">
        <table>
            <tr>
                <td style="min-width: 100px"><s:message code="Login" />:</td>
                <td><input class="userLogin" type="text" name="username"/></td>
            </tr>
            <tr>
                <td><s:message code="Password" />:</td>
                <td><input class="userPassword" type="password" name="password"/></td>
            </tr>
            <tr>
                <td colspan="2" style="text-align: center"><input class="myMediumBtn" type="submit" value="<s:message code="SignIn" />"/></td>
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
<script>
    $(document).ready(function(){
        $('.userLogin').focus();
    })
</script>