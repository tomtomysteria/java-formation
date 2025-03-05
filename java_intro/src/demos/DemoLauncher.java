package demos;

import java.util.Scanner;

public class DemoLauncher {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int choice;

    do {
      System.out.println("\n### Menu des démonstrations ###");
      System.out.println("(0) Quitter ");
      System.out.println("(1) Démo des voitures ");
      System.out.println("(2) Démo de la calculatrice ");
      System.out.println("(3) Démo Pair/Impair ");
      System.out.println("(4) Démo Employés ");
      System.out.println("(5) Démo des livres ");

      System.out.print("Votre choix : ");

      while (!scanner.hasNextInt()) { // Vérifie que l'entrée est bien un entier
        System.out.println("Erreur : Veuillez entrer un nombre valide.");
        scanner.next(); // Vide l'entrée incorrecte
        System.out.print("Votre choix : ");
      }
      choice = scanner.nextInt();
      System.out.println("");

      switch (choice) {
        case 0:
          System.out.println("Fermeture du programme.");
          break;
        case 1:
          CarDemo.run();
          break;
        case 2:
          CalculatorDemo.run(scanner);
          break;
        case 3:
          EvenOddDemo.run(scanner);
          break;
        case 4:
          EmployeeDemo.run();
          break;
        case 5:
          BookDemo.run();
          break;
        default:
          System.out.println("Choix invalide. Veuillez réessayer.");
      }
    } while (choice != 0); // Continue tant que l'utilisateur ne choisit pas "Quitter"

    scanner.close();
  }
}
