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

<h1>Личный кабинет пользователя ${sessionScope.username}.</h1>

<p>Это страница аккаунта</p>
<a>...........тут будет разная инфа..............</a>
<br/>
<hr/>

<a href="${pageContext.request.contextPath}/home">Go to home</a><br/>
<a href="${pageContext.request.contextPath}/registration">Тест - попытка зайти на /registration при активной
    сессии</a><br/>
<a href="${pageContext.request.contextPath}/account">Тест - попытка зайти на /account из меню /account</a><br/>

<hr/>
<form action="${pageContext.request.contextPath}/account" method="post">
<button type="submit">Удалить аккаунт</button>

</form>

</body>
</html>