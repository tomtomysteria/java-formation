package models.car;

public class ElectricCar extends Car {
  private int batteryCapacity;

  public ElectricCar(String brand, int year, int batteryCapacity) {
    super(brand, year);
    this.batteryCapacity = batteryCapacity;
  }

  // Soit j'override displayInfo
  @Override
  public void displayInfo() {
    super.displayInfo();
    System.out.println("Battery Capacity: " + batteryCapacity + " kWh");
  }

  // Soit je veux un nom différent
  // public void displayElectricCarInfo() {
  // displayInfo();
  // System.out.println("Battery Capacity: " + batteryCapacity + " kWh");
  // }
}
