package demos;

import models.car.*;

public class CarDemo {
  private CarDemo() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
  }

  public static void run() {
    System.out.println("=== Démo des voitures ===");

    Car myCar = new Car("Toyota", 2018);
    myCar.displayInfo();

    ElectricCar myElectricCar = new ElectricCar("Tesla", 2022, 500);
    myElectricCar.displayInfo();
  }
}
