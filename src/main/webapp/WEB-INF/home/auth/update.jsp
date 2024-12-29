<%--
  Created by IntelliJ IDEA.
  User: aveasura
  Date: 29.12.2024
  Time: 17:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>update</title>
</head>
<body>

<h1>Тут можно обновить данные пользователя</h1>

<form action="${pageContext.request.contextPath}/account/update" method="post">
    <label for="username">Введите новый логин</label>
    <input type="text" id="username" name="username" placeholder="new login" required><br/><br/>

    <label for="password">Введите новый пароль</label>
    <input type="password" id="password" name="password" placeholder="new password" required><br/>

    <br/><hr/>
    <button type="submit">Обновить</button>
</form>

<hr/>
<a href="${pageContext.request.contextPath}/account">В личный кабинет</a>
<a href="${pageContext.request.contextPath}/home">На главную страницу</a>
</body>
</html>