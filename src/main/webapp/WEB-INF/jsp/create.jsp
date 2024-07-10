<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="create" method="post" enctype="application/x-www-form-urlencoded">
    <input name="employee_id" type="number">
    <br><br>
    <input name="first_name" type="text">
    <br><br>
    <input name="last_name" type="text">
    <br><br>
    <input name="email" type="text">
    <br><br>
    <input name="phone_number" type="text">
    <br><br>
    <input name="hire_date" type="date">
    <br><br>
    <input name="salary" type="number">
    <br><br>
    <input name="commission_pct" type="number" placeholder="1.0" step="0.01" min="0" max="1" >
    <br><br>
    <input name="department_id" type="number">
    <br><br>
    <button type="submit">Save Data</button>
</form>
</body>
</html>
