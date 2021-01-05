<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
 <link href="<c:url value="/resources/css/loginpage.css" />" rel="stylesheet">
 <link href="<c:url value="/resources/css/util.css" />" rel="stylesheet">
<title>SSONGKDEV CRUD REGISTER</title>
</head>
<body class="background-wrapper">
<div class="header-wrapper"><span>SSONGK CRUD</span></div>
<div class="center">
	<div class="background-modal">
		<form class="register-form" action="/basic/login/registerRequest" method="post">
			<div class="modal-header"><span>회원가입</span></div>
			<div class="modal-body">
			<div class="register-from-item"><span>이름</span><input type="text" name="memberName"/></div>
			<div class="register-from-item"><span>아이디</span><input type="text" name="memberId"/></div>
			<div class="register-from-item"><span>비밀번호</span><input type="password" name="memberPassword"/></div>
			</div>
			<div class="modal-footer">
				<input type="submit" value="회원가입"/>
			</div>
		</form>
	</div>
</div>
</body>
</html>