package basics.collections.set;

import java.util.Set;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.TreeSet;

public class SetDemo {
  public static void main(String[] args) {
    Set<String> couleurs1 = new HashSet<>();
    couleurs1.add("Rouge");
    couleurs1.add("Bleu");
    couleurs1.add("Rouge"); // Ignoré
    couleurs1.add("Violet");
    couleurs1.add("Jaune");
    System.out.println("Couleurs (HashSet) : " + couleurs1);

    Set<String> couleurs2 = new LinkedHashSet<>();
    couleurs2.add("Rouge");
    couleurs2.add("Bleu");
    couleurs2.add("Rouge"); // Ignoré
    couleurs2.add("Violet");
    couleurs2.add("Jaune");
    System.out.println("Couleurs (LinkedHashSet) : " + couleurs2 + " => équivalent à ArrayList sans doublons");

    Set<String> couleurs3 = new TreeSet<>();
    couleurs3.add("Rouge");
    couleurs3.add("Bleu");
    couleurs3.add("Rouge"); // Ignoré
    couleurs3.add("Violet");
    couleurs3.add("Jaune");
    System.out.println("Couleurs (TreeSet) : " + couleurs3);
  }
}
