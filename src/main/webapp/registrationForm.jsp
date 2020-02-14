<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="user" class="com.tms.bean.User" scope="session"/>
<jsp:setProperty name="user" property="*"/>

<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<form name="registration" action="registration" method="post" onsubmit="return validation()">
    Имя пользователя:<br>
    <input type="text" name="name">
    <br>
    Пароль:<br>
    <input type="password" name="password">
    <br>
    <br>

    <br> Имя:<br>
    <input type="text" name="firstName">
    <br>
    Фамилия:<br>
    <input type="text" name="lastName">
    <br>
    Пол:<br>
    <input type="radio" name="sex" value="мужской"> мужской
    <input type="radio" name="sex" value="женский"> женский
    <input type="radio" name="sex" value="нет"> не хочу говорить
    <br>
    Адрес:<br>
    <input type="text" name="address">
    <br>
    <input type="checkbox" name="isAdmin"/> Администратор
    <br>
    <input type="submit" value="OK">
</form>
</body>
</html>
<script>
    function validation() {
        var form = document.forms["registration"];
        var value = form["name"].value;
        if (value == null || value == "") {
            alert("Введите имя пользователя");
            return false;
        }
        var regex = new RegExp("^[a-zA-Z0-9_]+$");
        if (!regex.test(value)) {
            alert("Имя пользователя может состоять только из английских букв, цифр и знака _");
            return false;
        }

        value = form["password"].value;
        if (value == null || value == "") {
            alert("Введите пароль");
            return false;
        }
        value = form["firstName"].value;
        if (value == null || value == "") {
            alert("Введите имя");
            return false;
        }
        value = form["lastName"].value;
        if (value == null || value == "") {
            alert("Введите фамилию");
            return false;
        }
        value = form["sex"].value;
        if (value == null || value == "") {
            alert("Выберите пол");
            return false;
        }
        value = form["address"].value;
        if (value == null || value == "") {
            alert("Введте адрес");
            return false;
        }
    }
</script>

