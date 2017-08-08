<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/WEB-INF/tld/spring.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<jsp:include page="static/header.jsp"/>

    <div class="container">
        <h1><s:message code="403_ERROR" /></h1>
        <h3><s:message code="Hi" /> ${userProfile.login}, <s:message code="MessageAccessDenied" /> </h3>
    </div>

<jsp:include page="static/footer.jsp"/>