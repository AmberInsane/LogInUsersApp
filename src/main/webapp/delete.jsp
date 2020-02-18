<%--
  Created by IntelliJ IDEA.
  User: Amber
  Date: 17.02.2020
  Time: 23:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" isELIgnored="false" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Удаление</title>
</head>
<body>
<form name="delete" action="userAction" method="post">
    <h4>Вы уверены, что хотите удалить запись?</h4>

    <input type="hidden" name="id" value="${param.id}">
    <input type="submit" name="action" value="delete">
    <input type="submit" name="action" value="cancel">
</form>
</body>
</html>
