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
    <title>Home</title>
</head>
<body>

<h1>Главная страница сайта</h1>
<br/>
<h1>Создай таблицу</h1>
<form action="${pageContext.request.contextPath}/createtable" method="post">

    <p>Пока нет миграций, придется нажимать на кнопку для создания таблицы юзеров</p>
    <p>Нажать один раз, потом все будет работать</p>

    <button type="submit">Создать</button>
</form>

<p>разный текст 1</p>
<p>разный текст 2</p>
<p>разный текст 3</p>
<p>разный текст 4</p>
<hr/>

<ul>
    <li><a href="${pageContext.request.contextPath}/registration">Зарегистрироваться</a></li>
    <li><a href="${pageContext.request.contextPath}/login">Войти</a></li>
</ul>

</body>
</html>