package demos

import java.util.Scanner

fun main() {
  val scanner = Scanner(System.`in`)
  var choice: Int

  do {
    println("\n### Menu des démonstrations ###")
    println("(0) Quitter ")
    println("(1) Démo des personnes ")
    println("(2) Démo des voitures ")
    println("(3) Démo de la calcultrice ")
    println("(4) Démo des livres ")

    print("Votre choix : ")

    while (!scanner.hasNextInt()) {
      println("Erreur : Veuillez entrer un nombre valide.")
      scanner.next()
      print("Votre choix : ")
    }

    choice = scanner.nextInt()
    println("")

    when (choice) {
      0 -> {
        println("Fermeture du programme.")
      }
      1 -> {
        PersonDemo.run()
      }
      2 -> {
        CarDemo.run()
      }
      3 -> {
        CalculatorDemo.run(scanner)
      }
      4 -> {
        BookDemo.run()
      }
      else -> {
        println("Choix invalide. Veuillez réessayer.")
      }
    }
  } while (choice != 0)

  scanner.close()
}
