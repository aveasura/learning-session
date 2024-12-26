<%--
  Created by IntelliJ IDEA.
  User: aveasura
  Date: 25.12.2024
  Time: 1:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>

<h1>Для того чтобы зарегистрироваться, выполните следующие действия:</h1>

<form action="${pageContext.request.contextPath}/registration" method="post">
    <label for="username">Укажите логин для регистрации:</label>
    <input type="text" id="username" name="username" placeholder="login" required><br/><br/>

    <label for="password">Задайте пароль:</label>
    <input type="password" id="password" name="password" placeholder="password" required><br/><br/>

    <hr/>
    <button type="submit">Зарегистрироваться</button>
</form>

</body>
</html>
