<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<meta charset="UTF-8">
 	<link href="<c:url value="/resources/css/home.css" />" rel="stylesheet">
 	<link href="<c:url value="/resources/css/write.css" />" rel="stylesheet">
 	<link href="<c:url value="/resources/css/util.css" />" rel="stylesheet">
	<title>Home</title>
 	
 	<script type="text/javascript" src="//code.jquery.com/jquery-1.11.0.min.js"></script> <!-- 제이쿼리 사용 -->
	<script type="text/javascript" src="/basic/resources/se2/js/service/HuskyEZCreator.js" charset="utf-8"></script>
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
			<form id="noticeWriteForm" class="write-form" action="/basic/course/write/writeRequest" method="post">
			<input type="hidden" name="courseIdx" value=${courseIdx} />
			<div class="form-header">
				<div><span>제목</span></div>
				<div><input type="text" name="title" /></div>
			</div>
			<div class="form-body">
				<div><span>내용</span></div>
				<div><textarea name="content" id="smartEditor" rows="10" cols="120"></textarea></div>
			</div>
			<div class="form-footer">
				<input type="button" id="saveButton" value="저장" />
				<input type="button" id="cancelButton" value="취소" />
			</div>
			</form>
		</div>
	</div>
</body>
</html>

 	
<script type="text/javascript"> <!-- 스마트 에디터 -->
	var oEditors = [];
	nhn.husky.EZCreator.createInIFrame({
	oAppRef: oEditors,
	elPlaceHolder: "smartEditor",
	sSkinURI: "/basic/resources/se2/SmartEditor2Skin.html",
	fCreator: "createSEditor2"
	});
	
	$(function() {
		$("#saveButton").click(function() {
			oEditors.getById["smartEditor"].exec("UPDATE_CONTENTS_FIELD", []); <!-- 값 적용 -->
			
			const content = document.getElementById("smartEditor").value;
			if(content == "" || content == null || content == '&nbsp;' || content == '<br>' || content == '<br/>' || content == '<p>&nbsp;</p>'){ 
				alert("본문을 작성해주세요."); 
				oEditors.getById["smartEditor"].exec("FOCUS"); //포커싱 
				return; 
			}
			const result = confirm("발행 하시겠습니까?");
			if(result){ 
				alert("발행 완료!"); 
				$("#noticeWriteForm").submit(); 
			}else{ 
				return; 
			}
		});
		
		$("#cancelButton").click(function() {
			window.history.back();
		});
	})
</script>



