<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:useBean id="user" class="com.tms.bean.User" scope="session"/>
<jsp:setProperty name="user" property="*"/>

<html>
<head>
    <title>Добро пожаловать!</title>
</head>
<body>
<form name="login" action="login" method="post">
    Имя пользователя:<br>
    <input type="text" name="name">
    <br>
    Пароль:<br>
    <input type="password" name="password">
    <br>
    <a href="registrationForm.jsp">Регистрация</a>
    <input type="submit" name="logIn" value="Вход">
</form>
<c:if test="${error != null}">
    <font color="red">${error}</font><br>
</c:if>
</body>
</html>


