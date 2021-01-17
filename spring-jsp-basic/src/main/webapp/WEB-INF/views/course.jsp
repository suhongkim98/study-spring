<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta charset="UTF-8">
 	<link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
 	<link href="<c:url value="/resources/css/course.css" />" rel="stylesheet">
 	<link href="<c:url value="/resources/css/util.css" />" rel="stylesheet">
	<title>Course</title>
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
		<div class="main-inner-background">
			<div class="board-header board-grid"><span>번호</span><span>제목</span><span>작성자</span><span>작성일</span><span>조회수</span></div>
			
			<c:forEach items="${boards}" var="board">
				<!--<a href="/basic/course/view?boardIdx=${board.getIdx()}" style="text-decoration: none;"></a>  -->
				<div class="board-grid board-item">
					<span>${board.getIdx()}</span>
					<a href="/basic/course/view?boardIdx=${board.getIdx()}" class="button"><span>${board.getTitle() }</span></a>
					<span>${board.getMember().getMemberName() }</span>
					<span>${board.getDate() }</span>
					<span>${board.getViews() }</span>
				</div>
	    	</c:forEach>
			<div style="position: relative;"><a class="write-board-button" href="/basic/course/write?courseIdx=${courseIdx}">글쓰기</a></div>
		</div>
	</div>
</body>
</html>
