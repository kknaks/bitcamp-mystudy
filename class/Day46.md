# 네트워크 패턴
<div style="margin-left: 20px;">
<h2> 네트워크 입출력</h2>

<div style="margin-left: 10px;">
<h3> 네트워크의 기초</h3>
<div style="margin-left: 10px;">
<ul>
<li> 네트워크 : 여러컴퓨터들을 통신회선으로 연결한 것을 말한다.</li>
<ul style="padding-left: 10px">
<li> LAN(Local Area Network) : 가정, 회사, 건물 등 특정영역에 존재하는 여러컴퓨터들을 연결한 것을 말한다.</li>
<li> WAN(Wide Area Network) : LAN들을 연결한 것을 말한다.</li>
</ul>
<li> 서버와 클라이언트 </li>
<ul style="padding-left: 10px">
<li> 서버 : 서비스를 제공하는 프로그램을 말한다.</li>
<li> 클라이언트 : 서비스를 요청하는 프로그램을 말한다.</li>
</ul>
<li> IP 주소 : 컴퓨터의 고유 주소로 LAN카드 마다 할당된다.</li>
<ul style="padding-left: 10px">
<li> IP 주소를 알아야 상호 통신이 가능하다.</li>
<li> IP 주소를 모르면 DNS서버에 접근하여 IP주소를 검색한다.</li>
</ul>
<li>Port 번호 : IP주소 하나로 다양한 서버를 구축가능하다.</li>
<ul style="padding-left: 10px">
<li> 이 경우 클라이언트가 어떤 서버와 통신을 해야하는 지를 구별 해주는 번호가 port 번호이다.</li>
<li> 클라이언트의 경우도 서버와 연결하면 port번호가 생기는 이는 운영체제에서 자동으로 할당한다.</li>
</ul>
</ul>
</div>

<h3> IP주소 얻기</h3>
<div style="margin-left: 10px;">
<ul>
<li> Java에서는 IP주소를 사용하기 위해 java.net패키지의 InetAddress로 표현한다.</li>

```java
InetAddress ia = InetAddress.getLocalHost();
```
<li> 도메인을 통해 IP주소를 얻을 수 있다. </li>

```java
InetAddress ia = InetAddress.getByName(String domainName);
InetAddress[] iaArr = InetAddress.getAllByName(String domainName);
```

<li> InetAddress 객체에서 IP주소를 얻기 위해서는 getHostAddress() 메서드를 사용한다. </li>

```java
String IP = InetAddress.getHostAddress();
```
</ul>
</div>

<h3> TCP네트워킹</h3>
<div style="margin-left: 10px;">
<ul>
<li> IP주소로 프로그램들이 통신할 때는 약속된 데이터 전송 규약을 사용한다.</li>
<li> 이 규약을 전송용 프로토콜 이라 부르며, TCP와 UDP가 있다.</li>
<li> Transmission Control Protocol은 연결형 프로토콜로 상대방이 연결된 상태에서 데이터를 송수신한다.</li>
<li> 클라이언트가 연결을 요처하고 서버가 연결을 수락하면 통신 회선이 고정되고 이 회선을 통해 데이터를 송수신한다.</li>
<li> 고정된 회선을 사용하기 때문에 데이터가 순서대로 전달되며 손실이 발생하지 않는다.</li>
<li> TCP서버</li>
<ul>
<li> TCP서버를 개발 하려면 ServerSocket과 Socket이 필요하다. </li>

```java
import java.net.InetSocketAddress;
import java.net.ServerSocket;// port번호를 직접 바인딩하여 생성

ServerSocket serverSocket = new ServerSocket("Port번호");

// 기본생성 후 port를 바인딩 하여 생성
ServerSocket serverSocket = new ServerSocket();
serverSocket.bind( new InetSocketAddress("Port번호"));

//특정 IP에만 할당하는 방법
ServerSocket serverSocket = new ServerSocket();
serverSocket.bind( new InetSocketAddress("IP주소","Port번호"));
```
<li> ServerSocket을 생성하면 client Socket의 연결을 수락하기 전까지 블로킹이 된다.</li>
<li> accept()을 통해 Sokcet을 연결 할 수 있다.</li>

```java 
Socket socket = serverSocket.accept();
```
<li> 연결된 클라이언트의 IP주소와 Port번호를 확인하기 위해서는 연결된 소켓 주소를 불러와야한다.</li>

```java 
import java.net.InetSocketAddress;

InetSocketAddress isa = (InetSocketAddress) socket.getRemotSocketAddress();
String clientIp = isa.getHostString();
String portNo = isa.getPort();
```
<li> 연결이 종료되면 serverSocket을 close 해야한다.</li>

```java
serverSocket.close();
```
</ul>
<li> TCP클라이언트</li>
<ul>
<li> 클라이언트가 TCP서버에 접속하려면 Socket에 IP주소와 포트 번호를 입력하면된다. </li>

```java
// IP와 포트번호를 통해 생성
Socket socket = new Socket("IP","Port");

//도메인을 통해 생성
Socket socket = new Socket(InetAddress.getByName("domainName"),50001);

//connect를 통해 접속 후 생성
Socket socket = new Socket();
socket.connect(new InetAddress.getByName("domainName"),50001);

```
<li> 연결 요청 시 두가지 예외가 발생 할 수 있다. UnknownHostException/ IOException</li>

<li> 연결이 종료되면 serverSocket을 close 해야한다.</li>

```java
socket.close(); 
```
</ul>
</ul>
</div>

</div>
</div>
