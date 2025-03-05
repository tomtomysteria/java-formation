package demos;

import java.util.InputMismatchException;
import java.util.Scanner;

public class EvenOddDemo {
  private EvenOddDemo() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
  }

  public static void run(Scanner scanner) {
    System.out.println("=== Démo Pair/Impair ===");

    int number = 0;
    boolean validInput = false;

    // Boucle pour redemander une entrée correcte
    while (!validInput) {
      try {
        System.out.print("Entrez un nombre : ");

        // Vérifier si une entrée est disponible
        if (!scanner.hasNext()) {
          System.out.println("\nInterruption détectée. Fermeture du programme.");
          System.exit(0); // Quitte proprement le programme
        }

        number = scanner.nextInt(); // Lecture d'un entier
        validInput = true; // Si la saisie est correcte, on sort de la boucle
      } catch (InputMismatchException e) {
        System.out.println("Erreur : Vous devez entrer un nombre entier.");
        scanner.next(); // Nettoie l'entrée incorrecte
      }
    }

    // Vérification pair/impair
    if (number % 2 == 0) {
      System.out.println("Le nombre est pair");
    } else {
      System.out.println("Le nombre est impair");
    }
  }
}
