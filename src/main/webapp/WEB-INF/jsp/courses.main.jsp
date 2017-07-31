<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="/WEB-INF/tld/spring.tld" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:include page="static/header.jsp"/>


            <h3>
                <s:message code="Courses"/>
            </h3>

            <%--<ul id="coursesList">--%>
                <%--<c:forEach var="item" items="${courses}">--%>
                    <%--<li>${item.id} | ${item.name}</li>--%>
                <%--</c:forEach>--%>
            <%--</ul>--%>

            <sec:authorize access="!isAuthenticated()">
                <p><a class="btn btn-lg btn-success" href="<c:url value="/login" />" role="button">Войти</a></p>
            </sec:authorize>
            <sec:authorize access="isAuthenticated()">
                <p>Ваш логин: <sec:authentication property="principal.username" /></p>
                <p><a class="btn btn-lg btn-danger" href="<c:url value="/logout" />" role="button">Выйти</a></p>

            </sec:authorize>

            <table class="table table-striped" data-effect="fade">
                <thead>
                <tr>
                    <th>#</th>
                    <th>Course Name</th>
                    <th>Start Date</th>
                    <th>Teacher</th>
                    <sec:authorize access="isAuthenticated()">
                        <td>

                        </td>
                    </sec:authorize>
                </tr>
                </thead>
                <tbody id="coursesList">
                <c:forEach var="item" items="${courses}">
                    <tr>
                        <td><input value="${item.id}" disabled /></td>
                        <td><input value="${item.name}" disabled/></td>
                        <td><input value="${item.startDate}" disabled/></td>
                        <td><input value="${item.teacher.lastname}" disabled/></td>
                        <sec:authorize access="isAuthenticated()">
                        <td>
                            <a class="btn btn-xs btn-success edit_course" id="" href="#" role="button">Редактировать</a>
                        </td>
                        </sec:authorize>

                    </tr>
                </c:forEach>
                </tbody>
            </table>




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