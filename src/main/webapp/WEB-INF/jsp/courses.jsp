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
                    <%--<sec:authorize access="isAuthenticated()">--%>
                        <%--<td>--%>
                        <%--</td>--%>
                    <%--</sec:authorize>--%>
                </div>

                <div id = "coursesList">
                <c:forEach var="item" items="${courses}">
                    <%--<a href="${pageContext.request.contextPath}/profile">--%>
                    <a href="https://i.mycdn.me/image?id=804610547057&ts=00000000a600000226&plc=WEB&tkn=*67ht7wJA2wSO4acSdFqPasgmxnU&fn=sqr_288">
                        <div class = "line">
                            <div class = "cell"><input value="${item.name}" /></div>
                            <div class = "cell"><input value="${item.teacher.lastname}" /></div>
                            <div class = "cell"><input value="${item.startDate}" /></div>
                            <div class = "cell"><input value="${item.endDate}" /></div>
                            <%--<sec:authorize access="isAuthenticated()"><td>--%>
                                <%--<a class="btn btn-xs btn-success edit_course" id="" href="#" role="button">Редактировать</a>--%>
                                <%--</td>--%>
                            <%--</sec:authorize>--%>
                        </div>
                    </a>
                </c:forEach>
            </div>
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