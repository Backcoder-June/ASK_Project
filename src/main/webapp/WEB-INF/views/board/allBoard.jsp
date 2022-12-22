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
        let sessionNickName = '${sessionScope.sessiondto.nickname}';
        let sessionEmail = '${sessionScope.sessiondto.email}';
        if (sessionEmail !='' && sessionNickName == '') {
            alert("사용하실 닉네임을 등록해 주세요.");
            location.replace("/nicknameRegister");
        }
        $("#userNickname").html("GUEST");
        if(sessionNickName!=''){
            $("#userNickname").html(sessionNickName);
            $("#logout").html('<a href="/logout">로그아웃</a>');
        }
    });
</script>


</head>
<body>

<span id="userNickname"></span> 님 환영합니다! <br>
<br>
<a href="/chatList">채팅리스트</a>
<br>


<div id="logout"></div>

<br>
<form action="http://localhost:8080/writeboard/">
    <button type="submit" class="btn btn-primary">글쓰기</button>
</form>


<table border="5">
    <tr>
        <th>ID</th>
        <th>Title</th>
        <th>Writer</th>
    </tr>

<c:forEach items="${all}" var="each">
    <tr>
        <th>${each.id}</th>
        <th><a href="http://localhost:8080/oneboard/${each.id}">${each.title}</a></th>
        <th>${each.userid}</th>
    </tr>
</c:forEach>
</table>


</body>
</html>
