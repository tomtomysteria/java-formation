package models.employee;

public class Employee {
  private String name;
  private double salary;

  public Employee(String name, double salary) {
    this.name = name;
    this.salary = salary;
  }

  public void displayInfo() {
    System.out.print(name + ", Salaire: " + Math.round(salary));
  }
}
