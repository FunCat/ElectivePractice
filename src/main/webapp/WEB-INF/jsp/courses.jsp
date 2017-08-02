<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/WEB-INF/tld/spring.tld" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="static/header.jsp"/>
            <h3>
                <s:message code="Courses"/>
            </h3>

            <div class="table table-striped" data-effect="fade">
                <div class = "headLine">
                    <div class = "cell">Course Name</div>
                    <div class = "cell">Teacher</div>
                    <div class = "cell">Start Date</div>
                    <div class = "cell">End Date</div>
                </div>
                <div id = "coursesList"></div>
            </div>

            <div class="text-center">
               <ul class="pagination">
                    <li class="">
                        <a href="#" id="prevPage">«</a>
                    </li>
                    <c:forEach var="i" begin="1" end="${numOfPages}">
                        <li class="page" id="${i}" >
                            <a href="#" ><c:out value="${i}"/></a>
                        </li>
                    </c:forEach>
                    <li class="">
                        <a href="#" id="nextPage">»</a>
                    </li>
                </ul>

            </div>




<jsp:include page="static/footer.jsp"/>

<%--<script src="js/jquery-3.2.1.js"></script>--%>
<%--<script src="js/navigation.js"></script>--%>
<%--<script src="./js/jquery-3.2.1.js"></script>--%>
<%--<script src="${pageContext.request.contextPath}/js/jquery-3.2.1.js"></script>--%>