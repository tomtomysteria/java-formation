package demos;

import java.util.Scanner;

public class CalculatriceDemo {
  private CalculatriceDemo() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
  }

  public static void run(Scanner scanner) {
    System.out.println("=== Démo de la calculatrice ===");

    // Demande à l'utilisateur d'entrer deux nombres
    double firstNumber = enterValidInput("Entrez le premier nombre : ", scanner);
    double secondNumber = enterValidInput("Entrez le deuxième nombre : ", scanner);

    double addition = firstNumber + secondNumber;
    double subtraction = firstNumber - secondNumber;
    double multiplication = firstNumber * secondNumber;
    double division = secondNumber != 0 ? firstNumber / secondNumber : Double.NaN;

    System.out.println("Addition : " + formatResult(addition));
    System.out.println("Soustraction : " + formatResult(subtraction));
    System.out.println("Multiplication : " + formatResult(multiplication));
    if (secondNumber != 0) {
      System.out.println("Division : " + formatResult(division));
    } else {
      System.out.println("Division : Impossible (division par zéro)");
    }
  }

  private static double enterValidInput(String label, Scanner scanner) {
    double number = 0;
    boolean isValidInput = false;

    while (!isValidInput) {
      try {
        System.out.print(label);

        // Vérifier si une entrée est disponible
        if (!scanner.hasNext()) {
          System.out.println("\nInterruption détectée. Fermeture du programme.");
          System.exit(0); // Quitte proprement le programme
        }

        number = parseUserInput(scanner.next());
        isValidInput = true;
      } catch (NumberFormatException e) {
        System.out.println("Erreur : Vous devez entrer un nombre.");
        scanner.nextLine();
      }
    }

    return number;
  }

  // Méthode pour convertir l'entrée utilisateur avec prise en charge de "," et
  // "."
  private static double parseUserInput(String input) {
    // Remplacer une virgule par un point si besoin
    input = input.replace(',', '.');
    return Double.parseDouble(input);
  }

  // Format the result to remove decimal part if it's an integer
  private static String formatResult(double result) {
    if (result == (int) result) {
      return String.valueOf((int) result); // Display as an integer if possible
    } else {
      return String.valueOf(result); // Display as a decimal if necessary
    }
  }
}
