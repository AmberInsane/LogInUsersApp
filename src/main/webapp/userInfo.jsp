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
    ${user.userInfo.firstName} ${user.userInfo.lastName}<br>

    <table border="1">
        <tr>
            <td>№</td>
            <td>e-mail</td>
            <td>Имя</td>
            <td>Фамилия</td>
            <td>Пол</td>
            <td>Адрес</td>
            <c:if test="${edit}">
                <td>Изменить</td>
            </c:if>
            <c:if test="${delete}">
                <td>Удалить</td>
            </c:if>
        </tr>
        <c:forEach var="tempUser" items="${sessionScope[name]}" varStatus="сounter">
            <td>${сounter.count}</td>
            <td><c:out value="${tempUser.email}"/></td>
            <td><c:out value="${tempUser.userInfo.firstName}"/></td>
            <td><c:out value="${tempUser.userInfo.lastName}"/></td>
            <td><c:out value="${tempUser.userInfo.sex}"/></td>
            <td><c:out value="${tempUser.userInfo.address}"/></td>
            <c:if test="${edit}">
                <td><a href="userAction?action=edit&id=${tempUser.getId()}">Изменить</a></td>
            </c:if>
            <c:if test="${delete}">
                <td><a href="<c:url value="/delete.jsp?id=${tempUser.getId()}"/>">Удалить</a></td>
            </c:if>
            </tr>
        </c:forEach>
    </table>

    <input type="submit" name="action" value="logOut">
</form>
</body>
</html>
