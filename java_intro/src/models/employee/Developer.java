package models.employee;

public class Developer extends Employee {
  private String programmingLanguage;

  public Developer(String name, double salary, String programmingLanguage) {
    super(name, salary);
    this.programmingLanguage = programmingLanguage;
  }

  @Override
  public void displayInfo() {
    super.displayInfo();
    System.out.print(", Language de programmation: " + programmingLanguage);
  }

}
