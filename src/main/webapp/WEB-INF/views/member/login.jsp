<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="user-scalable=no, initial-scale=1, minimum-scale=1.0, maximum-scale=1.0, width=device-width">
  <title>지식을 구하다</title>
  <link rel="stylesheet" href="${path}/css/login.css">
  <link rel="stylesheet" href="${path}/css/table.css">
  <script src="${path}/js/jquery-3.6.0.min.js"></script>



  <style>
    @media screen and (max-width: 767px) {

      * {
        margin : 0;
        padding: 0;
        width: auto;
      }
      body {
        width: auto;
      }

      .loginTitle {
        margin-bottom: 20px;
        margin-top: 10px;
      }

      table.loginInfoTable {
        margin-left: 0;
      }



    }
  </style>




  <script>
    $(document).ready(function () {
      let rememberId = document.querySelector('.rememeber-login-id');
      let remembercheck = document.querySelector('.remember-id');
      let loginButton = document.querySelector('.login-button');

      let count = 0;
      let localId = [];
      remembercheck.addEventListener('click', function(){
        count++;
        console.log(remembercheck.value);
        console.log(count);
      })
      loginButton.addEventListener('click', function(e){
        if(count%2 !=0 ){
          localId = [];
          localId.push(rememberId.value);
          console.log(localId);
          localStorage.setItem('ID', localId);
          rememberId.value = localStorage.getItem('ID');
        }
      })
      rememberId.value = localStorage.getItem('ID');


      function kakaoLogin(){
        alert("카카오 로그인 서비스");
      }
    });
  </script>




</head>

<body>
<div class="main-container">

  <div class="login-container2">

    <div class="login-box">
      <div class="loginTitle">로그인</div>
      <form class="login-box-form" action="loginProcess" method="post">
        <input class="login-input rememeber-login-id" type="text" name="email" id="email" autocomplete="off" placeholder="ID"><br>
        <input class="login-input" type="password" name="pw" id="pw" autocomplete="off" placeholder="PASSWORD"><br>
        <div class="id-check-box"><input class="remember-id mb-2" type="checkbox"><span>아이디 기억하기</span>
        </div>
        <c:if test="${param.get('error')}">
          <div id="loginFailMessage">
              ${exception}
          </div>
        </c:if>
        <br>
        <input class="login-button" type="submit" id="btn" value="  지구 로그인" >
      </form>

      <%--  <a id="kakao-login-btn" href="javascript:kakaoLogin();" >
          <img src="${path}/pictures/Kakao_logo.jpg"> 카카오 로그인
        </a>--%>

      <div class="login-box-form">
      <div class="oauth-login-button">
          <a href="/login/google">구글 로그인</a>
      </div>
      <div class="oauth-login-button">
        <a href="/login/naver">네이버 로그인</a>
      </div>
      </div>


      <div class="join_findInfoBTN">
        <table class="loginInfoTable">
          <tr>
            <th>아직 지구 회원이 아니신가요?</th>
            <td><a href="join"><button class="find-idpw-button" type="button">회원가입</button></a></td>
          </tr>
          <tr>
            <th>아이디를 잃어버리셨나요?</th>
            <td>    <a href="findid" style="font-size:15px;"><button class="find-idpw-button" type="button">아이디 찾기</button></a></td>
          </tr>
          <tr>
            <th>비밀번호를 잃어버리셨나요?</th>
            <td><a href="findpw" style="font-size:15px;"><button class="find-idpw-button" type="button">비밀번호 찾기</button></a></td>
          </tr>
        </table>

      </div>

    </div>
  </div>
  <!-- content-section -->
</div>



<script
        src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"
        integrity="sha384-oBqDVmMz9ATKxIep9tiCxS/Z9fNfEXiDAYTujMAeBAsjFuCZSmKbSSUnQlmh/jp3"
        crossorigin="anonymous"></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.min.js"
        integrity="sha384-7VPbUDkoPSGFnVtYi0QogXtr74QeVeeIs99Qfg5YCF+TidwNdjvaKZX19NZ/e6oz"
        crossorigin="anonymous"></script>



</body>
</html>
