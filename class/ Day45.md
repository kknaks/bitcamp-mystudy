# 네트워크
<div style="margin-left: 20px;">
<h2>네트워크 기본개념</h2>
<div style="margin-left: 10px;">
<h3> 네트워크의 개념</h3>
<div style="margin-left: 10px;">
<ul>
<li> 클라이언트 : UI를 처리하고 업무 처리 로직을 처리하며 해당 업무를 서버에 요청하는 객체 </li>
<li> 서버 : 데이터를 처리 하고 파일 입출력을 담당 요청에 응답하는 객체 </li>

![image](https://github.com/user-attachments/assets/79c64ce6-e68a-4e82-b74b-5400c7c3c927)
</ul>
</div>

<h3> 네트워크 사용법</h3>
<div style="margin-left: 10px;">
<ul>
<li> 클라이언트 : Socket을 생성하여 통신 </li>
<li> 서버 : ServerSocket을 생성하고 Socket을 통해 통신 </li>
<li> 실제 데이터 전송은 OutStream -> 랜카드 -> InputStream으로 이루어진다. </li>


![image](https://github.com/user-attachments/assets/0fa44a2c-8f5d-4ffd-9cc7-1109cb1af1d4)
</ul>
</div>
</div>

<h2>네트워크 클래스 사용법</h2>
<div style="margin-left: 10px;">
<h3> ServerSocket/Socket</h3>
<div style="margin-left: 10px;">
<ul>
<li> ServerSocker : 서버에서 받을 입구 생성 </li>
<li> port번호 : 통신 식별 변호</li>
<li> 대기열크기 : 클라이언트 최대 접속수</li>
<li> 서버측 포트 번호는 App에서 설정한다.</li>
<li> Socket : 입구에 대한 위치 </li>

```java
import java.net.ServerSocket;
// 소켓 설정
ServerSocket serverSocket = new ServerSocket((int) 포트번호,(int) 대기열크기);
System.out.println("서버 실행중...");
// 소켓 접속
Socket socket = serverSocket.accept();
System.out.println("클라이언트 접속");
```
</ul>
</div>

<h3> Socket</h3>
<div style="margin-left: 10px;">
<ul>
<li> Socker : 서버로 들어갈 주소 설정 </li>
<li> IP주소 : 포트 전까지 컴퓨터의 위치</li>
<li> 클라이언트측 포트 번호는 운영체제가 저장한다.</li>
<li> IP주소는 건물의 주소, 포트번호는 상세 주소라고 생각하면 된다.</li>
<li> IP주소: 서울 강남구 강남대로94길 20 삼오빌딩</li>
<li> 포트번호: 5층 비트캠프 501호</li>


```java
import java.net.ServerSocket;
// 소켓 접속
Socket socket = new Socket("IP주소",(int) 포트번호);
```
</ul>
</div>

<h3> 네트워크 사용규칙</h3>
<div style="margin-left: 10px;">
<ul>
<li> 네트워크 사용규칙을 프로토콜이라 한다. </li>

<img width="861" alt="image" src="https://github.com/user-attachments/assets/430ed34b-9d44-4c0f-92d4-feb41b67a34f">

<li> APP에서의 통신과정은 통신 객체를 만들어서 통신을 한다.</li>
<li> ORB : Object Request Broker 서버/클라이언트의 결과를 송수신</li>
<li> Stub : 클라이언트 측 브로커</li>
<li> Skeleton : 서버 측 브로커</li>
<li> Remote Object : 실제 데이터를 처리하는 업무를 수행하고 결과를 브로커에 전달</li>

![image](https://github.com/user-attachments/assets/17716f94-4236-466d-85fa-9c7a888f18b5)
</ul>
</div>
</div>

<h2> myApp에 적용하기 </h2>
<div style="margin-left: 10px;">
<h3> myApp구조 변경</h3>
<div style="margin-left: 10px;">
<ul>
<li> 클라이언트 : 메뉴를 처리하고 데이터를 입력받는다. </li>
<li> 서버 : 엑셀을 불러오고 각 리스트를 만들어서 관리하며, 클라이언트의 요청을 리스트에 반영하고 저장한다. </li>

![image](https://github.com/user-attachments/assets/79a1e9b7-e634-4112-97e3-6dae8473bcba)
</ul>
</div>

<h3> 소스파일 변경하기</h3>
<div style="margin-left: 10px;">
<ul>
<li> Git소스 파일 참고 </li>

</ul>
</div>
</div>
</div>

