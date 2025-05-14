package models.book

open class Book(private val title: String, private val author: String) {
  open fun displayInfo() {
    print("$title, Auteur: $author")
  }
}
