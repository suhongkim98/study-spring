<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta charset="UTF-8">
 	<link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
 	<link href="<c:url value="/resources/css/boardView.css" />" rel="stylesheet">
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
		<div class="board-total-wrapper">
			<div class="board-wrapper">
				<div class="board-header">
					<span>${board.getTitle()}</span>
				</div>
				<div class="board-body-wrapper">
					<div class="board-body-header">
					 <div><span>작성자: </span><span>${board.getMember().getMemberName()}</span></div>
					 <div class="board-body-header-date">
						 <div><span>작성일: </span><span>${board.getDate()}</span></div>
						 <div><span>조회수: </span><span>${board.getViews()}</span></div>
					 </div>
					</div>
					<div class="board-body">
						<div>
							${board.getContent()}
						</div>
					</div>
					<!-- 삭제버튼은 본인한테만 보이게 -->
					<c:if test="${member.getIdx() == board.getMember().getIdx()}">
						<a class="button"><span>삭제</span></a>
       				</c:if>
					
				</div>
			</div>
			<div class="input-comment-wrapper">
			</div>
			<div class="comment-wrapper">
			</div>
		</div>
	</div>
</body>
</html>
