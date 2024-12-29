<%--
  Created by IntelliJ IDEA.
  User: aveasura
  Date: 25.12.2024
  Time: 1:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>Account</title>
</head>
<body>

<h1>Добро пожаловать, ${sessionScope.username}.</h1>

<p>Страница аккаунта</p>
<p>разный текст 1</p>
<p>разный текст 2</p>
<p>разный текст 3</p>
<br/>

<form action="${pageContext.request.contextPath}/account/logout" method="post">
    <button type="submit">Выйти из аккаунта</button>
</form>

<form action="${pageContext.request.contextPath}/account/delete" method="post">
    <button type="submit">Удалить аккаунт</button>
</form>
<hr/>

<ul>
    <li><a href="${pageContext.request.contextPath}/home">На главную страницу</a></li>
    <li><a href="${pageContext.request.contextPath}/account/update">Редактировать профиль</a></li>
</ul>

</body>
</html>