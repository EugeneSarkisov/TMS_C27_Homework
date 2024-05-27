<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Success!</h1>
<p>Name: <%= request.getAttribute("name")%></p>
<p>Age: <%= request.getAttribute("age")%></p>
</body>
</html>