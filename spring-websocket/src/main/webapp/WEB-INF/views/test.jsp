
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
	<title>Home</title>
    <script src="/ws/webjars/jquery/3.1.1-1/jquery.min.js"></script>
    <script src="/ws/webjars/sockjs-client/1.0.2/sockjs.min.js"></script>
    <script src="/ws/webjars/stomp-websocket/2.3.3/stomp.min.js"></script>
</head>
<body>
<h1>웹소켓 테스트 페이지입니다.</h1>
<h2>이 페이지는 웹소켓-stomp 개념을 알기 위한 테스트 페이지입니다.</h2>
<h2>메시지는 콘솔창에서 주고받는걸 볼 수 있습니다.</h2>
<h3>주석처리된 설명 확인해주세요</h3>
<input type="button" value="소켓 연결" id="connectBtn" />
<div>
<span>이름: </span><input type="text" value="" id="nameArea" />
</div>
<input type="text" value="" id="textArea" />
<input type="button" value="메시지 전송" id="sendBtn" />
</body>
</html>
<script>

/* 클라이언트 단은 크게 연결, 연결종료, 구독, 전송으로 나누어진다.*/
var stompClient = null;
const connect = () => {
	//소켓통신 연결 요청 함수
	if (stompClient !== null) {
        stompClient.disconnect();
    }
	const socket = new SockJS('/ws/socket'); // endPoint 설정
	stompClient = Stomp.over(socket);
	
	stompClient.connect({}, function(frame) { //연결시도
		console.log('connected: ' + frame);//연결 성공
		alert("연결 성공");
		subscribe('/topic/test'); //해당 경로로 구독한다 topic은 구독 prefix이다. test로 구독하겠다는 의미이다.
	}, function(error){
		//연결 실패
		console.log(error);
	});
}

const disconnect = () => {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
}

const subscribe = (path) => {
	//해당 path로 구독하고 해당 경로로 들어오는 메시지를 받는다.
	stompClient.subscribe(path, function(response) {
		//연결에 성공한 경우 구독으로 메시지가 들어올 때마다 response를 받아온다.
		console.log('응답: ' + response);
		const message = JSON.parse(response.body); // 메시지
	}); 
}

const sendMessage = (type, message) => {
	const name = $("#nameArea").val();
	//서버에 메시지를 publish 요청하는 함수이다.
	
	//첫번째 매개변수는 스프링 컨트롤러 메시지 매핑경로이다. /app은 stomp prefix 규칙으로 지정한 경로이다. 그 뒤가 진짜 매핑주소
	//두번째 매개변수는 서버에 추가로 보내고자 하는 stomp 헤더
	//세번째 매개변수는 서버에 추가로 보내고자 하는 stomp 바디
	stompClient.send("/app/testPub",{}, JSON.stringify({type: type, sender: name, message: message})); //testPub경로로 해당 메시지를 publish 요청한다. 서버는 메시지매핑에서 잡아 처리한다.
}

$(function() {
	$("#connectBtn").click(function() {
		connect();
	});
	$("#sendBtn").click(function() {
		const msg = $("#textArea").val();
		sendMessage('TALK',msg);
	});
})
</script>

