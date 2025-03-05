package models.book;

public class Book {
  private String title;
  private String author;

  public Book(String title, String author) {
    this.title = title;
    this.author = author;
  }

  public void displayInfo() {
    System.out.print(title + ", Auteur: " + author);
  }
}
