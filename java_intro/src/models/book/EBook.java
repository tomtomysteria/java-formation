package models.book;

public class EBook extends Book {
  private double fileSize;

  public EBook(String title, String author, double fileSize) {
    super(title, author);
    this.fileSize = fileSize;
  }

  @Override
  public void displayInfo() {
    super.displayInfo();
    System.out.print(", Taille du fichier: " + fileSize + " Mo");
  }
}
