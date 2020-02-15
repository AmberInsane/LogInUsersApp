<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="user" class="com.tms.bean.User" scope="session"/>
<jsp:setProperty name="user" property="*"/>

<html>
<head>
    <title>Регистрация</title>
</head>
<body>

<form name="registration" action="userAction" method="post" onsubmit="return validation()">
    e-mail:<br>
    <input type="text" name="email" value="ololo@mail.ru">
    <br>
    Пароль:<br>
    <input type="password" name="password" value="1111">
    <br>
    <br>

    <br> Имя:<br>
    <input type="text" name="firstName" value="Mary">
    <br>
    Фамилия:<br>
    <input type="text" name="lastName" value="Insane">
    <br>
    Пол:<br>
    <input type="radio" name="sex" value="мужской" checked="1"> мужской
    <input type="radio" name="sex" value="женский"> женский
    <input type="radio" name="sex" value="нет"> не хочу говорить
    <br>
    Адрес:<br>
    <input type="text" name="address" value="1111">
    <br>
    <input type="checkbox" name="isSuper" checked="1"/> Супер-пользователь
    <br>
    <input type="submit" name="action" value="save">
</form>
</body>
</html>
<script>
    function validation() {
        var form = document.forms["registration"];
        var value = form["email"].value;
        if (value == null || value == "") {
            alert("Введите e-mail пользователя");
            return false;
        }

        var pattern =  new RegExp(/^([a-z0-9_\.-])+@[a-z0-9-]+\.([a-z]{2,4}\.)?[a-z]{2,4}$/i);
        if (!pattern.test(value)) {
            alert("Неверный формат электронной почты");
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

