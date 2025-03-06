package basics;

public class ThrowDemoWithTryCatch {
  public static void validateAge(int age) {
    if (age < 18) {
      throw new IllegalArgumentException("Âge insuffisant !");
    }
    System.out.println("Accès autorisé.");
  }

  public static void main(String[] args) {
    try {
      validateAge(15); // Lève (throw) une exception
    } catch (IllegalArgumentException e) {
      System.err.println("Erreur: " + e.getMessage());
    }
  }
}
