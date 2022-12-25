<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="path" value="${pageContext.request.contextPath}" />
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="_csrf_parameter" content="${_csrf.parameterName}" />
  <meta name="_csrf_header" content="${_csrf.headerName}" />
  <meta name="_csrf" content="${_csrf.token}" />
  <title>지식을 구하다</title>
  <link rel="stylesheet" href="${path}/css/login.css">
  <link rel="stylesheet" href="${path}/css/table.css">
  <script src="${path}/js/jquery-3.6.0.min.js"></script>
  <script>
    $(document).ready(function () {

    });
  </script>
</head>

<body>


<div class="main-container">
  <div class="login-container2">
    <div class="signup-box-title">
      <span>지구 &nbsp; 회원가입</span>
    </div>

    <form name="form" action="joinProcess" method="post" class="signup-container2">
      <table class="type09">
        <tr>
          <th>이메일</th>
          <td>
            <input type="text" name="email" id="email"
                     placeholder="이메일을 입력해 주세요." required>
            <button class="signup-check-button" type="button" id="id-btn"
                    value="중복확인" onclick="idcheck()">중복확인</button> <span id="id_check"></span>
          </td>
        </tr>
        <tr>
          <th>비밀번호</th>
          <td>
            <input type=password name="pw" id="pw"
                     placeholder="비밀번호를 입력해 주세요." required oninput="pwcheck()"> <span id="pw_check"></span>
          </td>
        </tr>
        <tr>
          <th>비밀번호 확인</th>
          <td>
            <input type=password name="pw2" id="pw2"
                     placeholder="비밀번호를 한번 더 입력해 주세요." required oninput="pw2check()">
            <span id="pw2_check"></span>
          </td>
        </tr>

        <tr>
          <th>닉네임</th>
          <td><input type=text name="nickname" id="nickname" placeholder="닉네임을 입력해 주세요."
                     required>
            <button class="signup-check-button" type="button" id="name-btn"
                    value="중복확인" onclick="namecheck()">중복확인</button> <span id="name_check"></span>

          </td>
        </tr>
      <%--  <tr>
          <th>이메일</th>
          <td><input type=text name="email" id="email" placeholder="이메일을 입력해 주세요."
                     required oninput="emailcheck()"> <span id="email_check"></span>

          </td>
        </tr>
--%>

        <tr>
          <td colspan="2">
            <button type=submit class="signup-button" id="signup_btn"
                    name="signup_btn" <%--onclick="check()"--%> disabled>회원가입</button>
          </td>
        </tr>
      </table>

      <input type="hidden" name="role" value="USER">
    </form>

  </div>
</div>
<script>
  let pw = $('#pw');
  let pw2 = $('#pw2');
  let userid = $('#userid');
  let email = $('#email');
  let nickname = $('#nickname');
  let btn = $('#signup_btn');
  let id_check = false;
  let pw_check = false;
  let pw2_check = false;
  let phone_check = false;
  let email_check = false;
  let name_check = false;
  let address = "";

  function idcheck() {
    var email = $('#email').val();

    var regId= /^[\w-\.]+@([\w-]+\.)+[\w-]{2,4}$/;
    // var regId = /^[A-Za-z0-9]{6,20}$/;
    if (email == '') {
      $('#id_check').text("아이디를 입력해주세요");
      $('#id_check').css("color", "red");
      id_check = false;
      btn.attr('disabled', true);
    } else {
      var header = $("meta[name='_csrf_header']").attr('content');
      var token = $("meta[name='_csrf']").attr('content');

      $.ajax({
        url: "idCheck",
        beforeSend: function(xhr){
          if(token && header) {
          xhr.setRequestHeader(header, token);}},
        type: 'post',
        data: { "email": email },
        success: function (data) {
          if (data == 'true') {
            $('#id_check').text("이미 가입된 아이디입니다");
            $('#id_check').css("color", "red");
            id_check = false;
            btn.attr('disabled', true);
          } else {
            if (!regId.test(email)) {
              $('#id_check').text("이메일 주소를 정확히 입력해 주세요.");
              $('#id_check').css("color", "red");
              id_check = false;
              btn.attr('disabled', true);
            } else {
              $('#id_check').text("✅사용 가능한 아이디입니다");
              $('#id_check').css("color", "rgb(116,232,0)");
              id_check = true;
              btn.attr('disabled', false);
              /* 	if (id_check == true && pw_check == true && pw2_check == true && phone_check == true && email_check == true && name_check == true) {
                      btn.attr('disabled', false);
                  }*/
            }
          }
        }
      }); //ajax
    }
  } // idCheck
  function pwcheck() {
    var pw = $('#pw').val();
    if (pw == '') {
      $('#pw_check').text("패스워드를 입력해 주세요.");
      $('#pw_check').css("color", "red");
      pw_check = false;
      btn.attr('disabled', true);
    } else if (pw.length < 8 || pw.length > 17) {
      $('#pw_check').text("패스워드 길이는 8자이상 16자이하 입니다");
      $('#pw_check').css("color", "red");
      pw_check = false;
      btn.attr('disabled', true);
    } else if (pw.search(/\s/) != -1) {
      $('#pw_check').text("패스워드는 공백을 포함할 수 없습니다");
      $('#pw_check').css("color", "red");
      pw_check = false;
      btn.attr('disabled', true);
    } else {
      $('#pw_check').text("✅패스워드 사용가능");
      $('#pw_check').css("color", "rgb(116,232,0)");
      pw_check = true;
      btn.attr('disabled', false);
      /* if (id_check == true && pw_check == true && pw2_check == true && phone_check == true && email_check == true && name_check == true) {
          btn.attr('disabled', false);
      } */
    }
  }
  function pw2check() {
    if($('#pw').val() != ''){
      pwcheck();
    }
    var pw2 = $('#pw2').val();
    var pw = $('#pw').val();
    if (pw2 == '') {
      $('#pw2_check').text("패스워드확인을 입력해 주세요.");
      $('#pw2_check').css("color", "red");
      pw2_check = false;
      btn.attr('disabled', true);
    } else if (pw2 == pw) {
      $('#pw2_check').text("✅패스워드가 일치합니다.");
      $('#pw2_check').css("color", "rgb(116,232,0)");
      btn.attr('disabled', false);
      pw2_check = true;
      /* if (id_check == true && pw_check == true && pw2_check == true && phone_check == true && email_check == true && name_check == true) {
          btn.attr('disabled', false);
      } */
    } else {
      $('#pw2_check').text("패스워드가 일치하지 않습니다.");
      $('#pw2_check').css("color", "red");
      pw2_check = false;
      btn.attr('disabled', true);
    }
  }

  function namecheck() {

    var regName = /^[A-Za-z0-9가-힣]{2,10}$/;
    var name = $('#nickname').val();
    if (name == '') {
      $('#name_check').text("닉네임을 입력해 주세요.");
      $('#name_check').css("color", "red");
      name_check = false;
      btn.attr('disabled', true);
    } else {
      $.ajax({
        url: "nicknameCheck",
        type: 'post',
        data: { "name": name },
        success: function (data) {
          if (data == 'true') {
            $('#name_check').text("이미 가입된 닉네임 입니다.");
            $('#name_check').css("color", "red");
            id_check = false;
            btn.attr('disabled', true);
          } else {
            if (!regName.test(name)) {
              $('#name_check').text("닉네임은 2~10글자 사이로 입력해 주세요.");
              $('#name_check').css("color", "red");
              id_check = false;
              btn.attr('disabled', true);
            } else {
              $('#name_check').text("✅사용 가능한 닉네임 입니다");
              $('#name_check').css("color", "rgb(116,232,0)");
              id_check = true;
              btn.attr('disabled', false);
              /* 	if (id_check == true && pw_check == true && pw2_check == true && phone_check == true && email_check == true && name_check == true) {
                      btn.attr('disabled', false);
                  }*/
            }
          }
        }
      }); //ajax




    }

    /*if(name.length > 1) {
      name_check = true;
      $('#name_check').text("✅");
      btn.attr('disabled', false)
    }*/

  }


  function check(){
    if($("#address").val()==''){
      btn.attr('disabled', true);
      alert("주소를 입력해 주세요.")
    }else{
      alert("BILLY 회원가입이 완료되었습니다.")
    }
  }




</script>

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
