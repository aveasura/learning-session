<%--
  Created by IntelliJ IDEA.
  User: aveasura
  Date: 25.12.2024
  Time: 1:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>

<h1>Страница регистрации нового пользователя</h1>

<%--Отобразить ошибку--%>
<c:if test="${not empty error}">
    <div style="color: red;">
        <strong>${error}</strong>
    </div>
</c:if>

<form action="${pageContext.request.contextPath}/registration" method="post">
    <label for="username">Укажите логин для регистрации</label>
    <input type="text" id="username" name="username" placeholder="login" required><br/><br/>

    <label for="password">Задайте пароль для аккаунта</label>
    <input type="password" id="password" name="password" placeholder="password" required><br/><br/>

    <button type="submit">Зарегистрироваться</button>
</form>
<hr/>

<ul>
    <li><a href="${pageContext.request.contextPath}/home">Вернуться</a></li>
    <li><a href="${pageContext.request.contextPath}/login">Войти</a></li>
</ul>

</body>
</html>
