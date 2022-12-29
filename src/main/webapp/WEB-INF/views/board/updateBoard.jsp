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
    <link rel="stylesheet" href="${path}/css/header.css">
    <link rel="stylesheet" href="${path}/css/table.css">
    <script src="${path}/js/jquery-3.6.0.min.js"></script>
<script>
    $(document).ready(function () {

    });
</script>
</head>

<body>
<div class="mt-5 mb-5" align="center">
    <h1>
        <a href="/test/"> 게시판 보기 </a>
    </h1>
</div>
<form class="container" action="/updateboard/{id}" method="post" >
    <div class="mb-3 mt-3 form-group">
        <label class="form-label">제목</label>
        <input type="text" class="form-control" name="title" value="${update.title}" required>
    </div>
    <div class="mb-3 form-group">
        <label class="form-label">내용</label>
        <textarea class="form-control" rows="5" name="contents" required>${update.contents}</textarea>
    </div>
    <div class="mb-3 form-group">
        카테고리
        <select id="categorySelect" name="category" required>
            <option value="백엔드">백엔드</option>
            <option value="프론트엔드">프론트엔드</option>
            <option value="DB">DB</option>
            <option value="배포">배포</option>
        </select>
        <br>
        도네이션
        <select id="donationSelect" name="donation" required>
            <option value="자율도네">자율도네</option>
            <option value="도네요청">도네요청</option>
            <option value="지식나눔">지식나눔</option>
        </select>
        <br>
        예상 소요시간
        <select id="asktimeSelect" name="asktime" required>
            <option value="1~15분">1~15분</option>
            <option value="15~30분">15~30분</option>
            <option value="30~60분">30~60분</option>
            <option value="60분 이상">60분 이상</option>
        </select>
    </div>
    <button type="submit" class="btn btn-primary">작성</button>
    <input type="hidden" name="id" value="${update.id}">
</form>


<script>
    /** 글 작성 시 선택한 카테고리, 도네, 시간 option 유지 **/
        $("#categorySelect").val("${update.category}").attr("selected","selected");
        $("#donationSelect").val("${update.donation}").attr("selected","selected");
        $("#asktimeSelect").val("${update.asktime}").attr("selected","selected");





</script>

</body>
</html>
