package basics.collections.map;

import java.util.HashMap;

public class HashMapDemo {
  public static void main(String[] args) {
    HashMap<String, Integer> ages = new HashMap<>();
    ages.put("Alice", 25);
    ages.put("Bob", 30);

    System.out.println("Âge de Bob : " + ages.get("Bob"));
  }
}
