# 쓰레드
## 멀티스레드의 기본개념
### 멀티스레드의 개념
  - 쓰레드 : 하나의 코드 실행흐름을 말한다.
  - 멀티태스킹 : 두가지 이상의 작업을 동시에 처리하는 것을 말하며, 운영체제는 멀티 프로세스를 생성해서 처리한다.
  - 멀티쓰레드 : 두가지 이상의 코드 실행 흐름을 말한다.
    ![image](https://github.com/user-attachments/assets/789e6a74-db34-4870-9c0a-41e4d822841b)
### 멀티프로세스과 멀티쓰레드의 특징
  - 멀티프로세스 : 서로 독립적이며 오류가 발생해도 서로 영향을 미치지 않는다.
  - 멀티쓰레드 : 프로세스 내부에서 생성되어, 하나의 쓰레드에서 예외를 발생시키면, 프로세스가 종료된다.

### 메인쓰레드 
  - main()메소드를 실행하면 메인 쓰레드가 실행되고, main() ~ return 까지 코드가 순차적으로 시행된다.
  - 싱글쓰레드에서는 메인 쓰레드가 종료되면 프로세스가 종료된다.
  - 멀티쓰레드에서는 작업중인 쓰레드가 있으면, 메인쓰레드는 종료되지 않는다.

    ![image](https://github.com/user-attachments/assets/74df1975-3cbe-4d27-8772-ac0a49999575)

### 작업 쓰레드 생성과 실행
  - 멀티쓰레드로 실행 하는 프로그램에서는 병렬 작업의 갯수를 고려하여 쓰레드를 생성하여야 한다.
    ![image](https://github.com/user-attachments/assets/81e41ced-6c42-4e7e-8a6c-d19c8e60e3cc)

    #### Thread 클래스로 직접생성
    - Thread클래스로 부터 작업 쓰레드 생성하는 방법은 다음과 같다.
      ```java 
       Thread thread = new Thread(Runnable taget);
      ```
    - Runnable은 인터페이스이다. run을 실행할 구현체를 생성하여 사용한다.
      ```java 
      Class Task implements Runnable{
        @Override
        public void run(){
          //쓰레드가 실행할 코드
        }
      }
      ```
    - Interface를 통해 구현체를 생성하여 객체를 생성하고 Thread객체를 생성한다.  
      ```java 
      Runnable task = new Task();
      Thread thread = new Thread(task);
      ```
    - 구현 객체를 생성할 수 있지만, 익명클래스를 활용하여 생성 할 수도 있으며, 실무에서는 이 방법이 활용도가 높다.
        ```java
        Thread task = new Thread(new Runnable(){
            @Override
            public void run(){
                //쓰레드가 실행할 부분
          }
        }
       ```
    - thread의 시작은 start()를 호출하여 실행한다.
      ```java
      thread.start(); 
      ```
    - Thread의 작업은 병렬로 작업을 수행하는 특징이 있다.
    - 메인쓰레드만 사용하면, 다음 작업은 순차적으로 실행된다.
      ```java
      package ch14.sec03.exam01;
        
        import java.awt.Toolkit;
        
        public class BeepPrintExample {
        public static void main(String[] args) {
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        for(int i=0; i<5; i++) {		
        toolkit.beep();
        try { Thread.sleep(500); } catch(Exception e) {}
        }
        
                for(int i=0; i<5; i++) {
                    System.out.println("띵");
                    try { Thread.sleep(500); } catch(Exception e) {}
                }
            }
        }
      ```
    - 동시에 작업을 수행 할 수 없지만 쓰레드를 사용하면 출력과 비프음 발생을 동시에 진행 할 수 있다.
      ```java
      package ch14.sec03.exam01;

      import java.awt.Toolkit;

      public class BeepPrintExample {
        public static void main(String[] args) {
          Thread thread = new Thread(new Runnable() {
            @Override
             public void run() {
              Toolkit toolkit = new Toolkit();
              for(int i=0; i<5; i++) {
                toolkit.beep();
                try { Thread.sleep(500); } catch(Exception e) {}
                }
              }
          }).start();
         
        for(int i=0; i<5; i++) {
           System.out.println("띵");
           try { Thread.sleep(500); } catch(Exception e) {}
        }
       }
      }
      ```

    #### Thread 자식클래스로 생성
    - Thread의 자식클래스로 상속받아 Override를 구현 후 직접 start()를 호출하여 사용한다.
      ```java 
      class WorkerThread extends Thread{
        @Override
        public void run(){
          //스레드가 실행할 코드 
        }
      }
      
      public class Exam{
        public static void main(String[] args){
          Thread workerThread = new WorkerThread();
          workerThread.start();
        }
      }
      ```
    - 마찬가지로 익명클래스로 바로 생성해서 사용한다.
      ```java
      public class Exam{
        public static void main(String[] args){
          Thread workerThread = new Thread(){
            @Override
            void run(){
              //쓰레드가 실행할 코드 
              } 
            }.start();
          }
        }
      ```
    - 위의 예제를 상속 방식으로 변경하면 다음과 같다.
      ```java
      package ch14.sec03.exam01;
      import java.awt.Toolkit;

      public class BeepPrintExample {
        public static void main(String[] args) {
          Thread thread = new Thread() {
              @Override
              public void run() {
              Toolkit toolkit = new Toolkit();
              for(int i=0; i<5; i++) {
                toolkit.beep();
                try { Thread.sleep(500); } catch(Exception e) {}
              }
            }
          }.start();

          for(int i=0; i<5; i++) {
            System.out.println("띵");
            try { Thread.sleep(500); } catch(Exception e) {}
          }
        }
      }
      ```
  
    #### 쓰레드의 이름
    - 작업 쓰레드의 이름은 Thread-n을 가진다.
    - 쓰레드의 이름을 설정 하기 위해서 setName(), 호출하기 위해서는 getName()을 사용한다.
    ```java 
    Thread thread = new Thread();
    thread.setName("작업쓰레드");
    System.out.println(thread.getName());
    ```
    
    #### 쓰레드의 상태
    - 쓰레드는 객체가 생성되서 바로 실행 되는 것이 아니라, CPU스케쥴링에 따라 시행된다. 
    - **NEW** : 쓰레드 객체를 생성 한 상태
    - **RUNNABLE** : Thread객체를 start()하여 쓰레드가 실행 가능한 상태
    - **RUNNING** : cpu에서 점유한 부분이 run() 메서드를 통해 실행 하는 상태
    - **WATING** : 쓰레드가 실행할 수 없는 상태
    - **TERMINATED** : 쓰레드의 실행이 완료된 상태
      ![image](https://github.com/user-attachments/assets/e2206f9d-e695-4ce1-bb57-3cb276f69796)

#### 주어진 시간 동안 일시정지
- sleep()
  - 실행 중인 쓰레드를 멈추는 메서드 단위는 millis이다.
  - 실행 대기 중에 메서드가 호출되면 InerruptedException이 발생한다.
      ```java
        package ch14.sec05.exam01;
        import java.awt.Toolkit;
        
        public class SleepExample {
            public static void main(String[] args) {
                Toolkit toolkit = Toolkit.getDefaultToolkit();		
                for(int i=0; i<10; i++) {
                    toolkit.beep();
                    try {
                      Thread.sleep(3000);
                    } catch(InterruptedException e) {			
                    }		
                }
            }
        }
      ```
    
- join()
  - 쓰레드가 독립적으로 시행 되기 때문에 다른 쓰레드의 값을 참조 할 수 없다.
  - 참조하기 위해서는 참조하는 값을 생성하는 쓰레드의 종료를 기다려야한다. 
  - 쓰레드안에 다른 쓰레드의 실행과 Join메서드를 사용하면 가능하다.
    ```java
    package ch14.sec05.exam02;
    
    public class JoinExample {
      public static void main(String[] args) {
        //메인 쓰레드 시작
        SumThread sumThread = new SumThread();
        // 작업 쓰레드 시작
        sumThread.start();
        try {
          //작업 쓰레드가 종료 될때 까지 메인 쓰레드는 기다림
          sumThread.join();
        } catch (InterruptedException e) {
          
        }
        // 종료된 작업쓰레드의 결과값을 메인 쓰레드가 참조
        System.out.println("1~100 합: " + sumThread.getSum());
    
        class SumThread extends Thread {
          private long sum;
    
          public long getSum() {
            return sum;
          }
    
          public void setSum(long sum) {
            this.sum = sum;
          }
    
          @Override
          public void run() {
            for (int i = 1; i <= 100; i++) {
              sum += i;
            }
          }
        }
      }
    }
    ```

#### 다른 쓰레드에게 실행 양보
- yield

### 쓰레드 동기화
- 동기화 메소드 및 블록 선언
- wait()
- notify()

### 쓰레드 안전종료
- 조건이용
- interrupt()메서드 이용

### 데몬쓰레드 

