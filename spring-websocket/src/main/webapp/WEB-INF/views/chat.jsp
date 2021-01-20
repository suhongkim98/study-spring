
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
    <script src="/ws/webjars/jquery/3.1.1-1/jquery.min.js"></script>
    <script src="/ws/webjars/axios/0.19.2/dist/axios.min.js"></script>
</head>
<body>
	<div>
		<span>방 생성</span>
		<div><input id="roomName" type="text"/><input id="addRoomButton" type="button" value="생성" /></div>
	</div>
	<br/>
	<br/>
	<div>
		<span>리스트</span><input id="selectAllButton" type="button" value="업데이트" />
		<div id="roomList"></div>
	</div>
</body>
</html>
<script>
const updateList = () => {
	axios.get('http://localhost:8090/ws/chatting/rooms').then((response)=>{
	    console.log(response.data); // 불러오기 성공 시
	    //리스트 업데이트
	    const list = response.data.map((row) => {
	    	return '<div> <span>방 이름: ' + row.name +" 방 번호: " + row.roomId + '</span> <a href="/ws/chatting/enter/' + row.roomId + '">입장하기</a></div>'
	    	});
	    $("#roomList").html(list);
	}).catch((error)=>{
	    console.log(error);
	});
}
$(function() {
	//init//
	updateList(); //시작하자마자 리스트 업데이트 
	////////
	
	$("#selectAllButton").click(function() {
		//리스트 업데이트
		updateList();
	});
	
	$("#addRoomButton").click(function() {
		//방생성
		const name = $("#roomName").val();
		
		const params = new URLSearchParams();
		params.append('name', name);
		axios.post('http://localhost:8090/ws/chatting/createRoom', params).then((response)=>{
		    console.log(response.data);
		    alert("방 생성 완료!");
		    updateList();//리스트 업데이트
		}).catch((error)=>{
		    console.log(error);
		});
		
	});
})
</script>
