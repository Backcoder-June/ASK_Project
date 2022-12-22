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
        let sessionid = '${sessionScope.sessiondto.email}';


        // 채팅 data submit
        function chatSubmit(e) {
            document.getElementById('chatSubmit_form').submit();
        }
        });

    </script>
</head>

<body>
<table class="table">
    <thead>
    <tr>
        <th scope="col">ID</th>
        <th scope="col">Writer</th>
        <th scope="col">Title</th>
        <th scope="col">Content</th>
    </tr>
    </thead>
    <tbody>
    <tr>
        <th>${oneBoard.id}</th>
        <th>${oneBoard.userid}</th>
        <td>${oneBoard.title}</td>
        <td>${oneBoard.contents}</td>
    </tr>
    </tbody>
</table>

<br>

<form action="/deleteboard/${oneBoard.id}" method="get">
    <input type="submit" value="삭제하기">
</form>

<form action="/updateboard/${oneBoard.id}" method="get">
    <input type="submit" value="수정하기">
</form>

<!-- 채팅버튼 -->
<div class="product-detail-chatbutton">
    <c:if test="${sessionScope.sessiondto.email != oneBoard.userid && not empty sessionScope.sessiondto.email }">
        <form id="chatSubmit_form" action="/chatMessage" method="GET">
            <a id="chatLink" href="javascript:{}" onclick="chatSubmit()">
                <input type="hidden" name="buyerId" value="${sessionScope.sessiondto.email}" />
                <input type="hidden" name="sellerId" value="${oneBoard.userid}" />
                <input type="hidden" name="pr_id" value="${oneBoard.id}" />
                <input type="hidden" name="pr_title" value="${oneBoard.title}" />

                <button class="chat-on-button" id="btn_chat">채팅하기</button>
            </a>
        </form>
    </c:if>

    <!-- 세션 없을 때, 가짜 채팅버튼 -->
    <c:if test="${empty sessionScope.sessiondto.email }">
        <button class="chat-on-button" id="noSession_FakeChatBTN">채팅하기fake</button>
    </c:if>

    <!-- 자기가 올린 물품일 때, 가짜 채팅버튼 -->
    <c:if test="${sessionScope.sessiondto.email == oneBoard.userid && not empty sessionScope.sessiondto.email }">
        <button class="chat-on-button" id="Owner_FakeChatBTN">채팅하기fake2</button>
    </c:if>

</div>



<script>




</script>



</body>
</html>
