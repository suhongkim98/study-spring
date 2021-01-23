## spring-websocket-redis
spring websocket 프로젝트에 redis를 연동해봅니다.<br/>

```
클라이언트와 서버는 stomp pub/sub으로 구독관계를 맺고
서버와 redis는 redis pub/sub으로 구독관계를 맺습니다.

클라이언트가 서버로 publish요청한 메시지는 서버에서 받아 redis로 publish 요청하고
redis는 메시지를 받아 자신을 구독하고 있는 서버들에게 해당 메시지를 publish합니다.

redis로부터 메시지를 받은 서버들은 자신을 구독하고 있는 클라이언트들에게 메시지를 publish합니다.
```
