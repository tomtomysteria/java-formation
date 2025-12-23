package basics.exceptions;

public class FinallyDemo {
  public static void main(String[] args) {
    try {
      int[] numbers = { 1, 2, 3 };
      System.out.println(numbers[5]); // Erreur ici !
    } catch (ArrayIndexOutOfBoundsException e) { // s’exécute uniquement si une ArrayIndexOutOfBoundsException est levée
                                                 // dans le try

      System.out.println("Erreur : Index hors limites !");
    } finally { // s’exécute toujours, qu'une exception soit levée ou non
      System.out.println("Fin de l'exécution.");
    }
  }
}
