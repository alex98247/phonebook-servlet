<%@ page import="com.digdes.school.phonebook.model.Person" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" %>
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
    <title>Телефонный справочник Request attribute</title></head>
<body>
<h2>Телефонный справчник 2</h2>
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
    <%

        List<Person> list = (List<Person>) request.getAttribute("personList");
        for (Person p : list) {%>
    <tr>
        <td><%=p.getId()%>
        </td>
        <td><%=p.getFirstName()%>
        </td>
        <td><%=p.getLastName()%>
        </td>
        <td><%=p.getMiddleName()%>
        </td>
        <td><%=p.getDateBirth()%>
        </td>
        <td><%=p.getPhoneNumber()%>
        </td>
    </tr>
    <%
        }
    %>
</table>
</body>
</html>