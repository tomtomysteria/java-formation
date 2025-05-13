package basics.exceptions;

public class ThrowWithTryCatchDemo {
  public static void validateAge(int age) {
    if (age < 18) {
      throw new IllegalArgumentException("Âge insuffisant !"); // Lève l'erreur → signaler un problème
    }
    System.out.println("Accès autorisé.");
  }

  public static void main(String[] args) {
    // Gère l'erreur → empêcher un plantage.
    // Le bloc catch s'exécute seulement si l’exception levée correspond au type
    // d’exception capturée.
    try {
      validateAge(15); // Lève (throw) une exception
    } catch (IllegalArgumentException e) {
      System.err.println("Erreur: " + e.getMessage());
    }
  }
}
