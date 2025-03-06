package basics.exceptions;

public class FinallyDemo {
  public static void main(String[] args) {
    try {
      int[] numbers = { 1, 2, 3 };
      System.out.println(numbers[5]); // Erreur ici !
    } catch (ArrayIndexOutOfBoundsException e) {
      System.out.println("Erreur : Index hors limites !");
    } finally {
      System.out.println("Fin de l'exécution.");
    }
  }
}
