package demos;

import java.util.InputMismatchException;
import java.util.Scanner;

public class SecureCalculatorDemo {
  private SecureCalculatorDemo() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
  }

  public static void run(Scanner scanner) {
    System.out.println("=== Démo de la calculatrice sécurisée ===");

    // Demande à l'utilisateur d'entrer deux nombres
    int firstNumber = enterValidInput("Entrez le premier nombre : ", scanner);
    int secondNumber = enterValidInput("Entrez le deuxième nombre : ", scanner);

    try {
      int division = intDivision(firstNumber, secondNumber);
      System.out.println("Division : " + division);
    } catch (ArithmeticException e) {
      System.out.println("Erreur : " + e.getMessage());
    }
  }

  private static int intDivision(int firstNumber, int secondNumber) {
    if (secondNumber == 0) {
      throw new ArithmeticException("Division par zéro !");
    }
    int result = firstNumber / secondNumber;
    return result;
  }

  private static int enterValidInput(String label, Scanner scanner) {
    int number = 0;
    boolean isValidInput = false;

    while (!isValidInput) {
      try {
        System.out.print(label);

        // Vérifier si une entrée est disponible
        if (!scanner.hasNext()) {
          System.out.println("\nInterruption détectée. Fermeture du programme.");
          System.exit(0); // Quitte proprement le programme
        }

        number = scanner.nextInt();
        isValidInput = true;
      } catch (InputMismatchException e) {
        System.out.println("Erreur : Vous devez entrer un entier.");
        scanner.nextLine();
      }
    }

    return number;
  }

}
