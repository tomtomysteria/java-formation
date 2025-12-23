package demos;

import java.util.Map;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class TelephoneDirectoryDemo {
  private TelephoneDirectoryDemo() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
  }

  public static void run(Scanner scanner) {
    System.out.println("=== Démo de l'annuaire téléphonique ===");

    Map<String, String> directory = new LinkedHashMap<>();
    directory.put("John", "0123456789");
    directory.put("Jane", "9876543210");
    directory.put("Bruno", "1234567890");

    System.out.print("Entrez un nom : ");

    if (!scanner.hasNext()) {
      System.out.println("\nInterruption détectée. Fermeture du programme.");
      System.exit(0); // Quitte proprement le programme
    }

    String name = scanner.next();

    try {
      System.out.println(name + " : " + getPhoneNumber(directory, name));
    } catch (NoSuchElementException e) {
      System.out.println("Erreur : " + e.getMessage());
    }

  }

  public static String getPhoneNumber(Map<String, String> directory, String name) {
    if (!directory.containsKey(name)) {
      throw new NoSuchElementException("Le contact '" + name + "' n'existe pas dans l'annuaire !");
    }
    return directory.get(name);
  }

}
