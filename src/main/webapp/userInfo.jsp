<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="name" value="userList"/>
<html>
<head>
    <title>Информация</title>
</head>
<body>
<form name="userInfo" method="post" action="userAction">
    <h3>Приветствую Вас</h3>
    ${user.email}
    ${user.userInfo.firstName} ${user.userInfo.lastName}<br>

        <h4>Пользователи:</h4>
        <table border="1">
            <tr>
                <td>№</td>
                <td>e-mail</td>
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
                    <td><c:out value="${tempUser.email}"/></td>
                    <td><c:out value="${tempUser.userInfo.firstName}"/></td>
                    <td><c:out value="${tempUser.userInfo.lastName}"/></td>
                    <td><c:out value="${tempUser.userInfo.sex}"/></td>
                    <td><c:out value="${tempUser.userInfo.address}"/></td>
                    <td><a href="registrationForm.jsp?id=${tempUser.getId()}/>">Изменить</a></td>
                    <td><a href="<c:url value="/delete.jsp?id=${tempUser.getId()}"/>">Удалить</a></td>
                </tr>
            </c:forEach>
        </table>


    <input type="submit" name="action" value="logOut">
</form>
</body>
</html>
