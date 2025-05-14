package models.person

class Person(val name: String, val age: Int) {
  fun introduce() {
    println("Bonjour, je m'appelle $name et j'ai $age ans.")
  }
}
