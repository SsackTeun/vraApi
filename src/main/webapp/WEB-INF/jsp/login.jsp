<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<link rel="stylesheet" href="css/login.css">
<script src="js/login.js"></script>
<body>
<div class="loginpage">
    <div class="loginlogo">
        <img src="img/loginlogo/loginlogo.png">
    </div>
    <div class="loginfield" style="padding: 30px 0px 0px 0px;">
        <label class="loginlabel">사용자 이름</label>
        <input type="text" class="logininput" id="username" name="username" title="사용자 이름">
    </div>
    <div class="loginfield">
        <label class="loginlabel">암호</label>
        <input type="password" class="logininput" title="암호" id="password" name="password">
    </div>
    <div class="loginchk">
        <button type="button" onclick="loginBtn()" name="action" id="signIn" class="loginbutton"/> 로그인
            <br><br><a href="" class="forgotPasswd" id="forgotPasswd">암호를 잊은 경우</a>
            <br><br><a href="" class="changeUserDomain" id="changeUserDomain">다른 도메인으로 변경</a>
    </div>
    <div class="vmwarelogo">
        <img src="img/loginlogo/vmwarelogo.png" style="width: 75px; height: 20px">
    </div>
</div>
</body>
</html>