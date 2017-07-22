<%--
  Created by IntelliJ IDEA.
  User: Sergey Petrov
  Date: 21.07.2017
  Time: 22:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
    <form name="edit" action="edit?id=${courseId}" method="POST" >
        <input type="text" name="courseName" value="${courseName}" />
        <input type="submit" value="Update" />
    </form>
</body>
</html>
