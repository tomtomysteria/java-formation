package basics.collections.list;

import java.util.ArrayList;

public class ArrayListDemo {
  public static void main(String[] args) {
    ArrayList<String> fruits = new ArrayList<>();
    fruits.add("Pomme");
    fruits.add("Orange");
    fruits.add("Banane");
    fruits.add("Orange");

    System.out.println("Liste des fruits : " + fruits);
  }
}
