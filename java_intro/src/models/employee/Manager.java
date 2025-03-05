package models.employee;

public class Manager extends Employee {
  private int teamSize;

  public Manager(String name, double salary, int teamSize) {
    super(name, salary);
    this.teamSize = teamSize;
  }

  @Override
  public void displayInfo() {
    super.displayInfo();
    System.out.print(", Taille de l'équipe: " + teamSize);
  }
}
