<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html;charset=UTF-8" language="java" %>

<script type="text/javascript">
    var i18nStrings = [];
    <c:forEach var="i18nKey" items="${i18nKeys}">
        i18nStrings["${i18nKey}"] = '<spring:message code="${i18nKey}"  javaScriptEscape='true'/>';
    </c:forEach>
</script>