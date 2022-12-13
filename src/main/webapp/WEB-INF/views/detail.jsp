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
<table class="table">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Title</th>
        <th scope="col">Content</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th>${findone.id}</th>
        <td>${findone.title}</td>
        <td>${findone.contents}</td>
    </tr>
    </tbody>
</table>

<br>

<form action="../delete/${findone.id}" method="get">
    <input type="submit" value="삭제하기">
</form>

<form action="../update/${findone.id}" method="get">
    <input type="submit" value="수정하기">
</form>





</body>
</html>
