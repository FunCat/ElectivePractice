<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/WEB-INF/tld/spring.tld" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>

<jsp:include page="header.jsp"/>

<div class="container">
    <h1><s:message code="404_ERROR" /></h1>
</div>

<jsp:include page="footer.jsp"/>