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

<form action="nicknameRegisterProcess" method="post">
  <input type="text" name="nickname" maxlength="10" minlength="2" placeholder="닉네임을 입력해 주세요." required>
  <input type="submit" value="닉네임 등록">
</form>






</body>
</html>
