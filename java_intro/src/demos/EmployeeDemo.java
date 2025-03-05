package demos;

import models.employee.*;

public class EmployeeDemo {
  // Private constructor to prevent instantiation
  private EmployeeDemo() {
    throw new UnsupportedOperationException("This is a utility class and cannot be instantiated.");
  }

  public static void run() {
    System.out.println("=== Démo Employés ===");

    Employee employee = new Employee("John Doe", 2000);
    Manager manager = new Manager("Jane Smith", 1500, 5);
    Developer developer = new Developer("Bruno Lampion", 3000, "Java");

    System.out.print("Employé: ");
    employee.displayInfo();

    System.out.print("\nManageur: ");
    manager.displayInfo();

    System.out.print("\nDéveloppeur: ");
    developer.displayInfo();
    System.out.println("");
  }
}
