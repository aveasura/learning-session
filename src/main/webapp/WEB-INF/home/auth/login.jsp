<%--
  Created by IntelliJ IDEA.
  User: aveasura
  Date: 27.12.2024
  Time: 15:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>Login</title>
</head>
<body>

<h1>Вход в аккаунт</h1>

<%--Отобразить ошибку--%>
<c:if test="${not empty error}">
    <div style="color: red;">
        <strong>${error}</strong>
    </div>
</c:if>

<form action="${pageContext.request.contextPath}/login" method="post">
    <label for="username">Введите ваш логин</label>
    <input type="text" id="username" name="username" placeholder="username" required><br/><br/>

    <label for="password">Введите пароль от аккаунта</label>
    <input type="password" id="password" name="password" placeholder="password" required><br/><br/>

    <button type="submit">Войти</button>
</form>
<hr/>

<ul>
    <li><a href="${pageContext.request.contextPath}/home">Вернуться</a></li>
    <li><a href="${pageContext.request.contextPath}/registration">Зарегистрироваться</a></li>
</ul>

</body>
</html>
