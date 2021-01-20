
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>채팅방</title>
    <script src="/ws/webjars/jquery/3.1.1-1/jquery.min.js"></script>
    <script src="/ws/webjars/sockjs-client/1.0.2/sockjs.min.js"></script>
    <script src="/ws/webjars/stomp-websocket/2.3.3/stomp.min.js"></script>
    <script src="/ws/webjars/axios/0.19.2/dist/axios.min.js"></script>
</head>
<body>
	<div><span>${chatRoom.getName()} 채팅방입니다.</span><input type="button" id="enterButton" value="연결하기" /></div>
	<div><span>이름: </span><input type="text" id="name" /></div>
	<div><span>메시지: </span><input type="text" id="messageArea" /><input type="button" id="sendButton" value="전송" /></div>
	<div id="chattingArea"></div>
</body>
</html>
<script>
var stompClient = null;
const connect = () => {
	if (stompClient !== null) {
        stompClient.disconnect();
    }
	const socket = new SockJS('/ws/socket'); 
	stompClient = Stomp.over(socket);
	
	stompClient.connect({}, function(frame) { 
		console.log('connected: ' + frame);
		subscribe('/topic/${chatRoom.getRoomId()}');
		alert("연결 성공");
	}, function(error){
		console.log(error);
	});
}
const disconnect = () => {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
}
const subscribe = (path) => {
	stompClient.subscribe(path, function(response) {
		console.log('응답: ' + response);
		const message = JSON.parse(response.body);
	    $("#chattingArea").html($("#chattingArea").html() + '<div>'+ message.sender +' - ' + message.message + '</div>'); // 채팅 업데이트
	}); 
}

const sendMessage = (type, name, message) => {
	//chattingPub으로 해당 메시지를 publish 요청한다.
	stompClient.send("/app/chattingPub",{}, JSON.stringify({type: type,roomId: '${chatRoom.getRoomId()}', sender: name, message: message})); 
}

$(function() {
	//init//
	connect(); //  시작하자마자 연결
	////////
	$("#enterButton").click(() => {
		connect();
	});
	$("#sendButton").click(() => {
		const msg = $("#messageArea").val();
		const name = $("#name").val();
		sendMessage('TALK',name,msg);
	});
});
</script>
