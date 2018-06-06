<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <style>
        table, td, th {
            border: 1px solid black;
        }
        table {
            border-collapse: collapse;
        }
    </style>
    <title>Телефонный справочник</title></head>
<body>
<h2>Телефонный справчник JSTL</h2>
<table style="border: 1px solid black;border-collapse: collapse;">
    <thead>
    <tr>
        <th>Таб. номер</th>
        <th>Имя</th>
        <th>Фамилия</th>
        <th>Отчество</th>
        <th>Дата рождения</th>
        <th>Номер телефона</th>
    </tr>
    </thead>
    <c:forEach var="person" items="${requestScope.personList}">
        <tr>
            <td><c:out value="${person.id}"></c:out></td>
            <td><c:out value="${person.firstName}"></c:out></td>
            <td><c:out value="${person.lastName}"></c:out></td>
            <td><c:out value="${person.middleName}"></c:out></td>
            <td><c:out value="${person.dateBirth}"></c:out></td>
            <td><c:out value="${person.phoneNumber}"></c:out></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>