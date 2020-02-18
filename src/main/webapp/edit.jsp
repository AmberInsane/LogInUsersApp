<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Изменить пользователя</title>
</head>
<body>
<h4>Изменение пользователя</h4>
<form name="update" action="userAction" method="post" onsubmit="return validation()">
    <br> Имя:<br>
    <input type="text" name="firstName" value="${user.userInfo.firstName}">
    <br>
    Фамилия:<br>
    <input type="text" name="lastName" value="${user.userInfo.lastName}">
    <br>
    Адрес:<br>
    <input type="text" name="address" value="${user.userInfo.address}">
    <br>
    <input type="checkbox" name="isSuper" <c:if test="${all}">checked="checked"</c:if>/> Супер-пользователь
    <br>
    <input type="checkbox" name="isShow" <c:if test="${show}">checked="checked"</c:if>/> Просмотр
    <br>
    <input type="checkbox" name="isEdit" <c:if test="${edit}">checked="checked"</c:if>/> Радактирование
    <br>
    <input type="checkbox" name="isDelete" <c:if test="${delete}">checked="checked"</c:if>/> Удаление
    <br>
    <input type="hidden" name="id" value="${param.id}">
    <input type="submit" name="action" value="update">
    <input type="submit" name="action" value="cancel">
</form>
</body>
</html>
<script>
    function validation() {
        var form = document.forms["update"];
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
        value = form["address"].value;
        if (value == null || value == "") {
            alert("Введте адрес");
            return false;
        }
    }
</script>
