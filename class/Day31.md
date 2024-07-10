# SOLID Pattern
## SOLID Pattern 이란 ?
- 객체지향 프로그래밍 및 설계에서 중요한 다섯가지 원칙
	### 1. Single Responsibility Principle (SRP, 단일 책임 원칙)
    - 원칙: 하나의 클래스는 하나의 책임
    - 의미: 각 클래스는 하나의 기능만 가져야 한다.
    - 예: 사용자 정보를 처리하는 클래스는 사용자 데이터베이스 접근과 같은 역할만 한다.

	### 2. Open/Closed Principle (OCP, 개방/폐쇄 원칙)
	- 원칙: 엔티티(클래스, 모듈, 함수 등)는 확장에는 열려 있어야 하고, 수정에는 닫혀 있어야 한다.
	- 의미: 새로운 기능을 추가할 때 기존 코드를 변경하지 않고도 확장할 수 있어야 한다.
	- 예: 새로운 기능을 추가할 때 기존 코드를 수정하지 않고 새로운 클래스를 추가하여 기능을 확장한다.

	### 3. Liskov Substitution Principle (LSP, 리스코프 치환 원칙)
	- 원칙: 서브타입은 언제나 자신의 기반 타입으로 교체할 수 있어야 한다.
	- 의미: 프로그램의 객체는 정확성을 깨트리지 않고 자식 클래스의 인스턴스로 대체할 수 있어야 한다.
	- 예: Rectangle 클래스를 상속받은 Square 클래스는 Rectangle 클래스로 교체해도 문제 없이 작동해야 한다.

	### 4. Interface Segregation Principle (ISP, 인터페이스 분리 원칙)
	- 원칙: 특정 클라이언트를 위한 인터페이스 여러 개가 범용 인터페이스 하나보다 낫다.
	- 의미: 클라이언트가 자신이 사용하지 않는 메서드에 의존하지 않도록 인터페이스를 분리해야 한다.
	- 예: 거대한 인터페이스 하나보다는 각 기능별로 인터페이스를 여러 개 만드는 것이 좋다.

	### 5. Dependency Inversion Principle (DIP, 의존 역전 원칙)
	- 원칙: 고수준 모듈은 저수준 모듈에 의존해서는 안 된다. 둘 다 추상화에 의존해야 한다.
	- 의미: 구체적인 구현이 아니라 추상화된 인터페이스나 상위 클래스에 의존해야 한다.
	- 예: 데이터베이스 접근 로직을 직접 클래스에 구현하는 대신, 인터페이스를 통해 접근하여 다른 데이터베이스로 쉽게 교체할 수 있도록 한다.

## SOLID 적용하기
- 예제로 DIP 개념 파악하기
    ### 1. 기존 클래스 UML
    ![image-6](https://github.com/kknaks/bitcamp-mystudy/assets/118641096/156e4a66-8444-4eb4-823d-a9ddead4ee91)
    - 기존 Switch클래스는 FluoLamp클래스만 객체로 사용 가능하다.
    - <u>고수준 모듈이 저수준 모듈에 의존적이다. 
    - 구체적인 구현이 아니라 추상화된 인터페이스나 상위클래스에 의존해야한다.</u>
    ### 2. 상위클래스로 같은 타입 만들기
    ![image-7](https://github.com/kknaks/bitcamp-mystudy/assets/118641096/bd4eb971-3b31-42dc-ba88-491e0e6f1836)
    - Lamp의 공통적 속성을 활용(Generic)하여 부모클래스를 생성한다.
    - Lamp의 다형성을 활용하여 모두 제어 가능하다.
    - Swtich는 lamp의 스위치 역할만 가능하다.
    - <u>Switch의 새로운 기능 추가 시, Open/Closed Principle을 충족시키지 못한다.</u>
    ### 3. 고수준의 상위클래스 만들기
    ![image-8](https://github.com/kknaks/bitcamp-mystudy/assets/118641096/c9461207-7bba-4d71-8a56-875db82b7fe2)

    - 더 높은 수준의 상위클래스로 상속받으면 Switch로 제어가능
    - 상속의 클래스가 복잡해진다.
    - Switch로 제어안해도 되는 코드도 강제로 사용해야한다.
    - <u>Interface Segregation Principle을 충족시키지 못한다.</u>

    ### 4. ICP(Interface Segregation Principle) 전환
    ![image-9](https://github.com/kknaks/bitcamp-mystudy/assets/118641096/3fa5d7ec-c502-4320-ba61-fc31edbfde4d)
    - Switch클래스에서 사용할 Interface를 생성한다. 
    - 제품군(Lamp, Fan)별 세부기능 가진 자식클래스를 생성하고 인터페이스를 구현한다.
    - 새로운 제품군이 들어오면 Switch클래스 내부에서 코드 수정이 필요하다. 
    - <u>Dependency Inversion Principle을 충족시키지 못한다.</u>

    ### DIP(Dependency Inversion Principle) 수정
    ![image-10](https://github.com/kknaks/bitcamp-mystudy/assets/118641096/16607853-31de-41e4-a01b-187afd7e7f08)
    - ICP를 충족하는 인터페이스를 생성한다.
    - 인터페이스를 직접적으로 생성하지 않는다( = 의존객체를 만들지 않는다.)
    - 외부에서 객체를 생성해서 대입을 받는다.


## 실습프로젝트에 적용해보기
- 실습 프로젝트 문제점
   ### 1. App() 파일
   - processMenu에서 menuTitle값이 수정되면 App()의 메서드도 역시 수정을 해야한다. 
   - DIP를 적용하여 외부에서 값을 대입하면 의존성을 탈피 할 수 있다.
    <details>
    <summary> 코드 접기/펴기</summary>

    ```java
    public class App {

        String[] mainMenus = new String[] {"회원", "프로젝트", "게시판", "공지사항", "도움말", "종료"};
        UserCommand userCommand = new UserCommand("회원");
        BoardCommand boardCommand = new BoardCommand("게시판");
        BoardCommand noticeCommand = new BoardCommand("공지사항");
        ProjectCommand projectCommand = new ProjectCommand("프로젝트", userCommand.getUserList());
        HelpCommand helpCommand = new HelpCommand();

        public static void main(String[] args) {
            new App().execute();
        }

        void execute() {
            // 생략 //
        }

        void processMenu(String menuTitle) {
            switch (menuTitle) {
            case "회원":
                userCommand.execute();
                break;
            case "프로젝트":
                projectCommand.execute();
                break;
            case "게시판":
                boardCommand.execute();
                break;
            case "공지사항":
                noticeCommand.execute();
                break;
            case "도움말":
                helpCommand.execute();
                break;
            default:
                System.out.printf("%s 메뉴의 명령을 처리할 수 없습니다.\n", menuTitle);
            }
        }

        void printMenu() {
            // 생략 //
        }

        boolean isValidateMenu(int menuNo, String[] menus) {
            // 생략 // 
        }

        String getMenuTitle(int menuNo, String[] menus) {
            // 생략 //
        }
    }
    ```
    </details>
      
        


 - 코드 수정하기
    ### 1. hashMap구조 만들기
    ```java
    Map<String, Command> commandMap = new HashMap<>();
    ```
    - String과 Command를 받을 수 있는 Map구조를 선언한다.
    - String에는 메뉴명을 대입한다.
    - Command는 인터페이스로 다형성을 이용하여 구현체를 대입한다. 

    ### 2. 생성자 만들기
    ```java
    public App() {
    commandMap.put("회원", new UserCommand("회원"));
    commandMap.put("게시판", new BoardCommand("게시판"));
    commandMap.put("공지사항", new BoardCommand("공지사항"));
    UserCommand userCommand = new UserCommand("회원");
    commandMap.put("프로젝트", new ProjectCommand("회원", userCommand.getUserList()));
    commandMap.put("도움말", new HelpCommand());
    }
    ```
    - put메서드를 이용하여 {Key : 메뉴명, Value : 구현체}를 대입한다.

    ### 3. processMenu 수정하기 
    ```java 
    switch (menuTitle) {
        case "회원":
        userCommand.execute();
        break;
        case "프로젝트":
        projectCommand.execute();
        break;
        case "게시판":
        boardCommand.execute();
        break;
        case "공지사항":
        noticeCommand.execute();
        break;
        case "도움말":
        helpCommand.execute();
        break;
    }
    ```
    - command 메소드로 넘어가기 위한 switch문을 다음과 같이 수정한다. 

    ```java
    void processMenu(String menuTitle) {
        Command command = commandMap.get(menuTitle);
        if (command == null) {
        System.out.printf("%s 메뉴의 명령을 처리할 수 없습니다.\n", menuTitle);
        return;
        }
        command.execute();
    }
    ```

    ### 4. App()에서 List 타입 넘기기
    - 지금까지 각 command클래스에서 List를 만들어서 객체를 담았다. 
    - App()의 List와 Command의 List 다른 객체를 참조하여 상호 호환이 불가하였다.
    - App()에서 List를 생성하고 매개변수로 Comman에 넘긴다.(OCP원칙)
    - App()에는 각 Command에서 사용할 객체를 생성하고, Command에서는 생성자를 통해 객체를 받는다.
    ```java
    ArrayList userList = new ArrayList();
    LinkedList projectList = new LinkedList();
    LinkedList noticeList = new LinkedList();
    ArrayList boardList = new ArrayList();

    public App() {
        commandMap.put("회원", new UserCommand("회원", userList));
        commandMap.put("게시판", new BoardCommand("게시판", boardList));
        commandMap.put("공지사항", new BoardCommand("공지사항", noticeList));
        commandMap.put("프로젝트", new ProjectCommand("회원", projectList, userList));
        commandMap.put("도움말", new HelpCommand());
    }

    //각 Command에서 생성자도 수정
    ```


# Stack과 Queue
## Stack과 Queue의 개념
- 스택과 큐는 컴퓨터 사이언스에서 사용되는 데이터 구조이다.
- Stack : 스택은 LIFO(Last In, First Out) 구조를 따르며 마지막에 삽입된 요소가 가장 먼저 삭제되는 방식이다. 스택은 마치 한쪽 끝에서만 요소를 넣거나 뺄 수 있다.
<img width="1250" alt="image-11" src="https://github.com/kknaks/bitcamp-mystudy/assets/118641096/474ccc08-ec92-489d-8108-55853e8f638d">
- Queue : 큐는 FIFO(First In, First Out) 구조를 따르며 처음에 삽입된 요소가 가장 먼저 삭제되는 방식이다. 큐는 마치 줄을 서서 기다리는 형태이다.
<img width="870" alt="image-12" src="https://github.com/kknaks/bitcamp-mystudy/assets/118641096/54dbc068-afdc-4d09-a15e-fe448a45f9a8">

## LinkedList로 Stack과 Queue구현하기 
- Stack 구현하기
   ```java 
    public class Stack extends LinkedList {
        //push구현하기
        public void push(Object obj) {
            add(obj);
        }
        //pop구현하기
        public Object pop() {
            return remove(size() - 1);
        }
        //empty구현하기
        public boolean isEmpty() {
            return size() == 0;
        }
    }
   ```
- Queue 구현하기
   ```java 
    public class Queue extends LinkedList {
        //push구현하기
        public void offer(Object obj) {
            add(obj);
        }
        //pop구현하기
        public Object poll() {
            return remove(0);
        }
        //empty구현하기
        public boolean isEmpty() {
            return size() == 0;
        }
    }
   ```

## 실습프로젝트에 적용하기
- Stack 적용하기
    ### 1. 전체 흐름도
    - Stack은 Prompt에서 menuPath를 설정
    ![image-13](https://github.com/kknaks/bitcamp-mystudy/assets/118641096/ef047dcc-37a7-433c-8b3b-0315aae71003)

    ### 2. App() 수정하기
    - 필드에 Stack menuPath를 설정한다.
    - execute에 "메인" push한다.
    - processMenu에서 menuPath를 매개변수로 넘긴다.
    <details>
    <summary> 코드 접기/펴기</summary>

    ```java
    public class App {
        Stack menuPath = new Stack();
        // 이하 필드 생략 // 
        public App() { /* 생략 */  }

        public static void main(String[] args) {
            new App().execute();
        }

        void execute() {
            menuPath.push("메인");
            /* 생략 */  
        }

        void processMenu(String menuTitle) {
            /* 생략 */  
            command.execute(menuPath);
        }

        void processMenu(String menuTitle)  { /* 생략 */  }

        boolean isValidateMenu(int menuNo, String[] menus)  { /* 생략 */  }

        String getMenuTitle(int menuNo, String[] menus)  { /* 생략 */  }
    }
    ```
    </details>

    ### 3. interface, AbstractCommand, HelpCommand 수정하기
    - 각 클래스에 매개변수 선언
    <details>
    <summary> 코드 접기/펴기</summary>

    ```java
    //Command interface
    public interface Command {
    void execute(Stack menuPath);
    }

    //AbstractCommand의 execute()
    public void execute(Stack menuPath) {
        menuPath.push(menuTitle);
        printMenus();
        while (true) {
            String command = Prompt.input(String.format("메인/%s>", menuTitle));
            if (command.equals("menu")) {
                printMenus();
                continue;
            } else if (command.equals("9")) { // 이전 메뉴 선택
                menuPath.pop();
                break;
            }
            try {
                int menuNo = Integer.parseInt(command);
                String menuName = getMenuTitle(menuNo);
                if (menuName == null) {
                System.out.println("유효한 메뉴 번호가 아닙니다.");
                continue;
                }
                processMenu(menuName);
            } catch (NumberFormatException ex) {
                System.out.println("숫자로 메뉴 번호를 입력하세요.");
            }
        }
    }
    //helpCommand
    public class HelpCommand implements Command {
        public void execute(Stack menuPath) {
            System.out.println("도움말입니다!");
        }
    }

    ```
    </details>

    ### 4. Stack 호출 메서드 작성
    - for문을 순회하면서 Stack의 배열을 탐색한다.
    - String타입은 immutable하기 때문에 StringBuffer(필드) 또는 StringBuilder(메서드안)를 사용한다.
    - menuPath 호출이 필요한 App, AbstractCommand에 적용한다.
    - String command = Prompt.input("%s>",getMenuTitle(menuPath)) 부분을 수정한다.
    <details>
    <summary> 코드 접기/펴기</summary>

    ```java
    private String getMenuTitle(Stack menuPath){
        StringBuilder strBuilder = new StringBuilder();
        for(int i = 0; i < menuPath.size(); i++){
            if (strBuilder.length() > 0){
                strBuilder.append("/");
            }
            strBuilder.append(menuPath.get(i));
        }
        return strBuilder.toString();
    }
    ```
    </details>

    ### 
    
- Queue 적용하기
    ### 1. 전체흐름도
    - Prompt.input()을 호출시에 Queue에 offer을 실행한다.
    - Queue를 차례대로 출력하는 메소드를 만든다.
    - 검색기록 출력 Command에 출력메소드를 넘긴다.
    ![image-14](https://github.com/kknaks/bitcamp-mystudy/assets/118641096/7744afad-bd32-4f1c-a3d4-9c61ab8b3606)

    ### 2. input메서드 수정
    - 기존 input메서드를 넘겨받은 매개변수와 키보드 입력변수 합쳐서 Queue에 offer
    - 검색기록이 20개를 넘기면, 기존 앞에서 부터 삭제
    ```java
        public static String input(String format, Object... args) {
            String promptTitle = String.format(format + " ", args);
            System.out.print(promptTitle);
            String input = keyboardScanner.nextLine();
            if (promptTitle.endsWith(">")) {
                inputQueue.offer(promptTitle + input);
            }
            if (inputQueue.size() > 20) {
                inputQueue.poll();
            }
            return input;
        }
    ```
    ### 3. 출력 메서드 작성
    - Queue를 돌면서 들어온 순서대로 출력
    ```java
        public static void printHistory() {
            System.out.println("[명령내역]-----------------------------");
            for (int i = 0; i < inputQueue.size(); i++) {
                System.out.println(inputQueue.get(i));
                }
            System.out.println("-------------------------------------끝");
        }
    ```
    ### 4. 호출 클래스 작성
    - HistoryCommand 클래스 생성
    ```java
    public class HistoryCommand implements Command{
        @Override
        public void execute(Stack menuTitle){
            Prompt.printHistory();
        }
    }
    ```
