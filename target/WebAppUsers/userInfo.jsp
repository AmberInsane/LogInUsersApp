<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="name" value="userList" />
<html>
<head>
    <title>Информация</title>
</head>
<body>
<form name="userInfo" method="post" action="userAction">
    <h4>Пользователи:</h4>
    <table border="1">
        <tr>
            <td>№</td>
            <td>Имя</td>
            <td>Фамилия</td>
            <td>Пол</td>
            <td>Адрес</td>
            <td>Изменить</td>
            <td>Удалить</td>
        </tr>
        <c:forEach var="tempUser" items="${sessionScope[name]}" varStatus="сounter">
            <tr>
                <td>${сounter.count}</td>
                <td><c:out value="${tempUser.firstName}"/></td>
                <td><c:out value="${tempUser.lastName}"/></td>
                <td><c:out value="${tempUser.sex}"/></td>
                <td><c:out value="${tempUser.address}"/></td>
                <td><a href="registrationForm.jsp?id=${u.getId()}">Удалить</a></td>
                <td><a href="deleteuser.jsp?id=${u.getId()}">Изменить</a></td></tr>
            </tr>
        </c:forEach>
    </table>

    <input type="submit" name="logOut" value="Выход">
</form>
</body>
</html>
