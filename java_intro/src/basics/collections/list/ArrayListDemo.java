package basics.collections.list;

import java.util.ArrayList;

public class ArrayListDemo {
  public static void main(String[] args) {
    ArrayList<String> couleurs = new ArrayList<>();
    couleurs.add("Rouge");
    couleurs.add("Bleu");
    couleurs.add("Rouge");
    couleurs.add("Violet");
    couleurs.add("Jaune");
    System.out.println("Couleurs (ArrayList) : " + couleurs + " => équivalent à LinkedHashSet avec doublons");

  }
}
