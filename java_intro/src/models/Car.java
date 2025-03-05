package models;

public class Car {
  // Attributes
  private String brand;
  private int year;

  // Constructor
  public Car(String brand, int year) {
    this.brand = brand;
    this.year = year;
  }

  // Getter methods
  public String getBrand() {
    return brand;
  }

  public int getYear() {
    return year;
  }

  // Method
  public void displayInfo() {
    System.out.println("Car Brand: " + brand + ", Year: " + year);
  }
}
