<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link href="<c:url value="/resources/css/loginpage.css" />" rel="stylesheet">
 <link href="<c:url value="/resources/css/util.css" />" rel="stylesheet">
<title>SSONGK CRUD LOGIN</title>
</head>
<body class="background-wrapper">
<div class="header-wrapper"><span>SSONGK CRUD</span></div>
<div class="body-wrapper">
	<form class="login-wrapper" action="/basic/login/loginRequest" method="post">
		<div class="login-header-wrapper"><span>LOGIN</span></div>
		<div class="login-body-wrapper">
			<input type="text" class="grid-item" name="memberId" />
			<input type="password" class="grid-item" name="memberPassword" />
			<button class="grid-item"><span>로그인</span></button>
		</div>
		<div class="login-footer-wrapper"><a href="/basic/login/register"><span>회원가입</span></a></div>
	</form>
</div>
</body>
</html>