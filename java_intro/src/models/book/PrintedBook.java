package models.book;

public class PrintedBook extends Book {
  private double weight;

  public PrintedBook(String title, String author, double weight) {
    super(title, author);
    this.weight = weight;
  }

  @Override
  public void displayInfo() {
    super.displayInfo();
    System.out.print(", Poids: " + weight + " kg");
  }
}
