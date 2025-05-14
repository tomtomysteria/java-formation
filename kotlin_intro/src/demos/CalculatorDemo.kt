package demos

import java.util.Scanner
import kotlin.system.exitProcess

object CalculatorDemo {
  fun run(scanner: Scanner) {
    println("=== Démo de la calculatrice ===")

    val firstNumber = enterValidInput("Entrez le premier nombre : ", scanner)
    val secondNumber = enterValidInput("Entrez le deuxième nombre : ", scanner)

    val addition = firstNumber + secondNumber
    val subtraction = firstNumber - secondNumber
    val multiplication = firstNumber * secondNumber
    val division = if (secondNumber != 0.0) firstNumber / secondNumber else Double.NaN

    println("Addition : ${formatResult(addition)}")
    println("Soustraction : ${formatResult(subtraction)}")
    println("Multiplication : ${formatResult(multiplication)}")
    if (secondNumber != 0.0) {
      println("Division : ${formatResult(division)}")
    } else {
      println("Division : Impossible (division par zéro)")
    }
  }

  private fun enterValidInput(label: String, scanner: Scanner): Double {
    while (true) {
      try {
        print(label)

        if (!scanner.hasNext()) {
          println("\nInterruption détectée. Fermeture du programme.")
          exitProcess(0)
        }

        val input = scanner.next()
        return parseUserInput(input)
      } catch (e: NumberFormatException) {
        println("Erreur : Vous devez entrer un nombre.")
        scanner.nextLine()
      }
    }
  }

  private fun parseUserInput(input: String): Double {
    return input.replace(',', '.').toDouble()
  }

  private fun formatResult(result: Double): String {
    return if (result == result.toInt().toDouble()) {
      result.toInt().toString()
    } else {
      result.toString()
    }
  }
}
