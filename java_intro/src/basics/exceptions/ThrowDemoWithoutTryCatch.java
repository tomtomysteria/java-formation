package basics.exceptions;

public class ThrowDemoWithoutTryCatch {
  public static void validateAge(int age) {
    if (age < 18) {
      throw new IllegalArgumentException("Âge insuffisant !");
    }
    System.out.println("Accès autorisé.");
  }

  public static void main(String[] args) {
    validateAge(15); // Lève une exception
  }
}
