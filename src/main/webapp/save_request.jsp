<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<form action="save_request" method="post" enctype="application/x-www-form-urlencoded">
    <input name="name">
    <br><br>
    <input name="age" type="number" min="1">
    <br><br>
    <input type="submit" value="Submit">
</form>
</body>
</html>
