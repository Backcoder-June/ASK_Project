<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ASK</title>
    <link rel="stylesheet" href="${path}/css/header.css">
    <script src="${path}/js/jquery-3.6.0.min.js"></script>

<script>
    $(document).ready(function () {
    });
</script>


</head>
<body>

HELLO INTELLIJ
HELLO GRADLE
HELLO JSP
HELLO WAR
HELLO Backcoder!

모델값 : ${all}

<form action="http://localhost:8080/writeboard/">
    <button type="submit" class="btn btn-primary">글쓰기</button>
</form>

<c:forEach items="${all}" var="each">

<table class="table">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Title</th>
    </tr>
    </thead>
    <tbody>

    <tr>
        <th>${each.id}</th>
        <td><a href="http://localhost:8080/test/${each}">${each.title}</a></td>
    </tr>

    </tbody>
</table>
</c:forEach>


</body>
</html>
