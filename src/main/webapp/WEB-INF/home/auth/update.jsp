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
    <title>Update</title>
</head>
<body>

<h1>Страница для изменения данных в профиле</h1>

<form action="${pageContext.request.contextPath}/account/update" method="post">
    <label for="username">Введите новый логин</label>
    <input type="text" id="username" name="username" placeholder="new login" required><br/><br/>

    <label for="password">Введите новый пароль</label>
    <input type="password" id="password" name="password" placeholder="new password" required><br/>

    <br/>
    <button type="submit">Обновить</button>
</form>
<hr/>

<ul>
    <li><a href="${pageContext.request.contextPath}/account">В личный кабинет</a></li>
    <li><a href="${pageContext.request.contextPath}/home">На главную страницу</a></li>
</ul>

</body>
</html>