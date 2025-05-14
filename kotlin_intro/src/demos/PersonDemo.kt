package demos

import models.person.*

object PersonDemo {
  fun run() {
    println("=== Démo des personnes ===")
    val person = Person("Alice", 25)
    person.introduce()
  }
}
