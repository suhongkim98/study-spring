<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta charset="UTF-8">
 	<link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
 	<link href="<c:url value="/resources/css/util.css" />" rel="stylesheet">
	<title>Home</title>
</head>
<body class="total-wrapper"> <!-- grid -->
	<div class="header-wrapper grid-item">
		<span style="font-size: 2.5rem; color: white; font-weight: 700;">SSONGK CRUD</span>
		<div><span style="font-size: 2rem; color: white; font-weight: 700;">${member.getMemberName()}</span><a href="/basic/login/logoutRequest">로그아웃</a></div>
	</div>
	<div class="nav-wrapper grid-item">
		<a>내 정보</a>
		<a>자율강좌</a>
	</div>
	<div class="main-wrapper grid-item">
		<span id="show-course-title">강좌 전체 보기</span>
		<c:forEach items="${courses}" var="course">
		<a href="/basic/course?courseIdx=${course.getIdx()}" style="text-decoration: none;">
		<div class="course-item">
			<div class="course-box"><span class="course-box-title">자율강좌</span></div>
			<span class="course-item-title">${course.getTitle()}</span>
		</div>
		</a>
    	</c:forEach>
	</div>
</body>
</html>
