package basics.collections.map;

import java.util.Map;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.TreeMap;

public class MapDemo {
  public static void main(String[] args) {
    Map<String, Integer> ages = new HashMap<>();
    ages.put("Walter", 12);
    ages.put("Alice", 25);
    ages.put("Bob", 30);
    System.out.println("Âge de Bob : " + ages.get("Bob"));
    System.out.println("Personnes (HashMap) : " + ages);

    Map<String, Integer> ages2 = new LinkedHashMap<>();
    ages2.put("Walter", 12);
    ages2.put("Alice", 25);
    ages2.put("Bob", 30);
    System.out.println("Personnes (LinkedHashMap) : " + ages2);

    Map<String, Integer> ages3 = new TreeMap<>();
    ages3.put("Walter", 12);
    ages3.put("Alice", 25);
    ages3.put("Bob", 30);
    System.out.println("Personnes (TreeMap) : " + ages3);
  }
}
