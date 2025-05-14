package demos

import models.book.Book
import models.book.EBook

object BookDemo {
  fun run() {
    println("=== Démo des livres ===")

    val myBook = Book("The Great Gatsby", "F. Scott Fitzgerald")
    val myEBook = EBook("The Great Gatsby", "F. Scott Fitzgerald", 2.5)

    print("Livre: ")
    myBook.displayInfo()

    print("\nEBook: ")
    myEBook.displayInfo()

    println()
  }
}
