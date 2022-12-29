<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="user-scalable=no, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, width=device-width">
    <title>ASK</title>
    <link rel="stylesheet" href="${path}/css/table.css">
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
        <a href="/allboard/"> 게시판 보기 </a>
    </h1>
</div>
<form class="container" action="/writeboard" method="post">
    <div class="mb-3 mt-3 form-group">
        <label class="form-label">제목</label>
        <input type="text" class="form-control" name="title" required>
    </div>
    <div class="mb-3 form-group">
        <label class="form-label">내용</label>
        <textarea class="form-control" rows="5" name="contents" required></textarea>
    </div>
    <div class="mb-3 form-group">
        카테고리
        <select name="category" required="required">
            <option>백엔드</option>
            <option>프론트엔드</option>
            <option>DB</option>
            <option>배포</option>
        </select>
        <br>
        도네이션
        <select name="donation" required>
            <option>자율도네</option>
            <option>도네요청</option>
            <option>지식나눔</option>
        </select>
        <br>
        예상 소요시간
        <select name="asktime" required>
            <option>1~15분</option>
            <option>15~30분</option>
            <option>30~60분</option>
            <option>60분 이상</option>
        </select>
    </div>
    <input type="hidden" value="${sessionScope.sessiondto.nickname}" name="userid">
    <button type="submit" class="btn btn-primary">작성</button>
</form>


</body>
</html>
