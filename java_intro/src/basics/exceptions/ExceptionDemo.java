package basics.exceptions;

public class ExceptionDemo {
  public static void main(String[] args) {
    try {
      int result = 10 / 0; // Division par zéro !
      System.out.println("Résultat : " + result);
    } catch (ArithmeticException e) {
      System.out.println("Erreur : Division par zéro !");
    }
  }
}
