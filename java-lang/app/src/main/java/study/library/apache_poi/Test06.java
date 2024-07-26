package study.library.apache_poi;

import java.util.HashMap;
import java.util.Map;

public class Test06 {
  public static void main(String[] args) {
    // Map 생성 및 데이터 추가
    Map<String, Integer> map = new HashMap<>();
    map.put("apple", 10);
    map.put("banana", 20);
    map.put("cherry", 30);

    // For-each loop를 사용하여 키-값 쌍 출력
    for (Map.Entry<String, Integer> entry : map.entrySet()) {
      String key = entry.getKey();
      Integer value = entry.getValue();
      System.out.println("Key: " + key + ", Value: " + value);
    }
  }
}
