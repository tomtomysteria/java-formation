package demos;

import models.book.*;

public class BookDemo {
  private BookDemo() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
  }

  public static void run() {
    System.out.println("=== Démo des livres ===");

    Book myBook = new Book("The Great Gatsby", "F. Scott Fitzgerald");
    EBook myEBook = new EBook("The Great Gatsby", "F. Scott Fitzgerald", 2.5);
    PrintedBook myPrintedEBook = new PrintedBook("The Great Gatsby", "F. Scott Fitzgerald", 0.8);

    System.out.print("Livre: ");
    myBook.displayInfo();

    System.out.print("\nEBook: ");
    myEBook.displayInfo();

    System.out.print("\nLivre imprimé: ");
    myPrintedEBook.displayInfo();

    System.out.println("");
  }
}
