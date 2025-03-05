package demos;

import models.Car;
import models.ElectricCar;

public class CarDemo {
  public static void run() {
    System.out.println("=== Démo des voitures ===");

    Car myCar = new Car("Toyota", 2018);
    myCar.displayInfo();

    ElectricCar myElectricCar = new ElectricCar("Tesla", 2022, 500);
    myElectricCar.displayInfo();
  }
}
