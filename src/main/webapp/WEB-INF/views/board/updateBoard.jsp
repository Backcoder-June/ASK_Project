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
<div class="mt-5 mb-5" align="center">
    <h1>
        <a href="http://localhost:8080/test/"> 게시판 보기 </a>
    </h1>
</div>
<form class="container" action="/updateboard/{id}" method="post" >
    <div class="mb-3 mt-3 form-group">
        <label class="form-label">제목</label>
        <input type="text" class="form-control" name="title" value="${update.title}">
    </div>
    <div class="mb-3 form-group">
        <label class="form-label">내용</label>
        <textarea class="form-control" rows="5" name="contents">${update.contents}</textarea>
    </div>
    <button type="submit" class="btn btn-primary">작성</button>
    <input type="hidden" name="id" value="${update.id}">
</form>


</body>
</html>
