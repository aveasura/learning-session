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
    <title>Reg</title>
</head>
<body>

<form action="${pageContext.request.contextPath}/registration" method="post">
    <label for="username">write you name</label>
    <input type="text" id="username" name="name" required>
    <button type="submit">send</button>
</form>

</body>
</html>
