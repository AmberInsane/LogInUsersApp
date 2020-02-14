<%@ page contentType="text/html;charset=UTF-8" %>
<%@taglib  prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="user" class="com.tms.bean.User" scope="session"/>
<jsp:setProperty name="user" property="*"/>

<html>
<head>
    <title>Регистрация</title>
</head>
<body>
<%@page import="com.tms.bean.User,com.tms.model.UserDao"%>

<%
    String id = request.getParameter("id");
    String action = request.getParameter("action");
    User u = null;
    if (!action.equals("")) {
        u = UserDao.getRecordById(Long.parseLong(id)).get();
    }
%>

<form name="registration" action="registration" method="post" onsubmit="return validation()">
    e-mail:<br>
    <input type="text" name="email">
    1111
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

