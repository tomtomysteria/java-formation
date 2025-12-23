package basics.fundamentals;

public class VariablesDemo {
  public static void main(String[] args) {
    int age = 25;
    double price = 19.99;
    char letter = 'A';
    String message = "Hello, Java!";
    boolean isJavaFun = true;

    System.out.println("Age: " + age);
    System.out.println("Prix: " + price);
    System.out.println("Lettre: " + letter);
    System.out.println("Message: " + message);
    System.out.println("Java est amusant ? " + isJavaFun);

    String name = null;
    System.out.println("Nom: " + name);
    // On conditionne l'accès à la méthode length() pour éviter une
    // NullPointerException
    if (name != null) {
      System.out.println("Longueur du nom: " + name.length());
    } else {
      System.out.println("name est null (pas de longueur)");
    }

  }
}
