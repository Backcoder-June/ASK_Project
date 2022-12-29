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
    <link rel="stylesheet" href="${path}/css/product.css">
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

<jsp:include page="/WEB-INF/views/header.jsp"/>


<br>
<a href="/allboard">전체</a>
<a href="/allboard/백엔드">백엔드</a>
<a href="/allboard/프론트엔드">프론트엔드</a>
<a href="/allboard/DB">DB</a>
<a href="/allboard/배포">배포</a>
<br>
<br>

<form action="/searchedList" method="get">
    <select name="searchOption">
        <option>제목</option>
        <option>닉네임</option>
    </select>
    <input type="text" name="searchKeyword">
    <input type="submit" value="검색">
</form>



<br>
<form action="writeboard/">
    <button type="submit" class="btn btn-primary">글쓰기</button>
</form>


<div class="pricing-box-container">
<c:forEach items="${all}" var="each">
	<div class="pricing-box text-center">
		<h5>${each.category}</h5>
		<p class="price"><a href="/oneboard/${each.id}">${each.title}</a></p>
		<ul class="features-list">
			<li><strong>도네이션</strong> ${each.donation}</li>
            <li><strong>예상시간</strong> ${each.asktime}</li>
            <li><strong>작성자 </strong> ${each.userid}</li>
            <li>${each.createdtime}</li>
		</ul>
		<button id="chatbtn" class="btn-primary">Get Started</button>
	</div>
</c:forEach>
</div>
<%--
	<div class="pricing-box pricing-box-bg-image text-center">
		<h5>Premium</h5>
		<p class="price"><sup>$</sup>39<sub>/mo</sub></p>
		<ul class="features-list">
			<li><strong>5</strong> Project</li>
			<li><strong>20</strong> Team Members</li>
			<li><strong>100</strong> Personal Projects</li>
			<li><strong>15,000</strong> Messages</li>
		</ul>
		<button class="btn-primary">Get Started</button>
	</div>

--%>











<jsp:include page="/WEB-INF/views/footer.jsp"/>
</body>
</html>
