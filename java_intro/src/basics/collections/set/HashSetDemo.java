package basics.collections.set;

import java.util.HashSet;

public class HashSetDemo {
  public static void main(String[] args) {
    HashSet<String> couleurs = new HashSet<>();
    couleurs.add("Rouge");
    couleurs.add("Bleu");
    couleurs.add("Rouge"); // Ignoré
    couleurs.add("Violet");
    couleurs.add("Jaune");

    System.out.println("Couleurs : " + couleurs);
  }
}
