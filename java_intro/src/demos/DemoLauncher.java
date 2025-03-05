package demos;

import java.util.Scanner;

public class DemoLauncher {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    int choice;

    do {
      System.out.println("\n=== Menu des démonstrations ===");
      System.out.print("(0)Quitter ");
      System.out.print("(1)Démo des voitures ");
      System.out.print("(2)Démo de la calculatrice ");
      System.out.print("(3)Démo Pair/Impair ");
      System.out.println("");

      System.out.print("Votre choix : ");
      while (!scanner.hasNextInt()) { // Vérifie que l'entrée est bien un entier
        System.out.println("Erreur : Veuillez entrer un nombre valide.");
        scanner.next(); // Vide l'entrée incorrecte
        System.out.print("Votre choix : ");
      }
      choice = scanner.nextInt();

      switch (choice) {
        case 0:
          System.out.println("Fermeture du programme.");
          break;
        case 1:
          CarDemo.run();
          break;
        case 2:
          CalculatriceDemo.run(scanner);
          break;
        case 3:
          PairImpairDemo.run(scanner);
          break;
        default:
          System.out.println("Choix invalide. Veuillez réessayer.");
      }
    } while (choice != 0); // Continue tant que l'utilisateur ne choisit pas "Quitter"

    scanner.close();
  }
}
