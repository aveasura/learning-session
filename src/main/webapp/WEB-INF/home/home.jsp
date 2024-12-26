<%--
  Created by IntelliJ IDEA.
  User: aveasura
  Date: 25.12.2024
  Time: 0:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>Home page</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/home" method="get">
    <h1>site main page!</h1>

</form>

<hr/>
<a href="${pageContext.request.contextPath}/home">home</a>
<br/>
<a href="${pageContext.request.contextPath}/registration">registration</a>
<br/>
<a href="${pageContext.request.contextPath}/login">login</a>

</body>
</html>
